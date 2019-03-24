package com.house.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.house.entity.House;
import com.house.service.IHouserService;

@Controller
public class AddHouseController {
	
	@Autowired
	private IHouserService service;

	@RequestMapping("/MultipleUpload")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("file") List<MultipartFile> file, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String,Object>();
        String dirPath = req.getServletContext().getRealPath("/file/");
		String tmp = (String) req.getSession().getAttribute(IMGS);
		if (tmp == null) {
			tmp = "";
		}
		StringBuffer sb = new StringBuffer(tmp);
		if (!file.isEmpty() && file.size() > 0) {
			for (MultipartFile f : file) {
				try {
					// 文件名
					String filename = UUID.randomUUID() + f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));
					// 存储虚拟路径
					File filePath = new File(dirPath);
					if (!filePath.exists()) {
						filePath.mkdirs();
					}
					//上传
                    File actualFile = new File(dirPath + filename);
                    f.transferTo(actualFile);
					sb.append("/file/");
					sb.append(filename);
					sb.append("~");
                    System.out.println("update file , path : " + actualFile.getAbsolutePath());
				} catch (Exception e) {
					map.put("code", 1);
					map.put("msg", "上传失败");
					map.put("detailImg", sb.toString());
					e.printStackTrace();
				}
			}
			map.put("detailImg", sb.toString());
			map.put("code", 0);
			map.put("msg", "上传成功");
		}
		req.getSession().setAttribute(IMGS, sb.toString());
		return map;
	}

	final static String IMGS = "detailImages";

	@RequestMapping("/singleUpload")
	@ResponseBody
	public Map<String, Object> singleUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
        String dirPath = req.getServletContext().getRealPath("/file/");
		try {
			String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String filename = UUID.randomUUID() + suffixName;
			File filePath = new File(dirPath);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			//创建虚拟路径存储
			String simplePath = dirPath + filename;
			map.put("image", "file/" + filename);
            File actualFile = new File(simplePath);
            file.transferTo(actualFile);
            System.out.println("update file , path : " + actualFile.getAbsolutePath());
            map.put("code", 0);
			map.put("msg", "上传成功");
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "上传失败");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/addHouse")
	public String addHouse( HttpServletRequest req) {
		return "addhouse.jsp";
	}
	
	@RequestMapping("/addHouseRecord")
	@ResponseBody
	public String addHouse(House house, HttpServletRequest req) {
		if(house.getPublisher()==null||"".equals(house.getPublisher())) {
			house.setPublisher("管理员");
		}
		if (house.getHouseDetailsImg() == null) {
			house.setHouseDetailsImg("");
		}
		if (house.getHouseImage() == null) {
            house.setHouseImage("");
        }
		int n = service.addNewHouse(house);
		req.getSession().removeAttribute(IMGS);
		if(n>0) {
//			detailsPath.delete(0,detailsPath.length());
			return "OK";
		}
		return "FAIL";
	}
}
