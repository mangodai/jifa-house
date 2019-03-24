package com.house.controller;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.house.entity.House;
import com.house.entity.Users;
import com.house.service.IHouserService;
import com.house.service.IUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserController {
	
	@Autowired
	private IUserService service;
	@Autowired
	private IHouserService dao;
	
	@RequestMapping("/toUserSystem")
	public String toUserSystemPage() {
		return "customer.jsp";
	}
	
	@RequestMapping("/toUserRentalPage")
	public String toUserRentalPage() {
		return "myrental.jsp";
	}

	@Autowired
	private IHouserService iHouserService;

	@RequestMapping("/welcome")
	public String toWelcomePage(HttpServletRequest req) {
		List<House> homeInfos = iHouserService.findHomeInfo();
		HashMap<String, Object> result = new HashMap<>();
		if (homeInfos != null) {
			Map<String, List<House>> collect = homeInfos.stream().collect(Collectors.groupingBy(House::getHouseType));
			if (collect == null) {
				collect = new HashMap<>();
			}
			result.put("legendData", collect.keySet());

			Map<String, Object> tmp2 = new HashMap<>(collect.size());
			ArrayList<Map> tmp3 = new ArrayList<>();
			collect.forEach((i, j) -> {
				Map<String, Object> tmp = new HashMap<>();
				tmp.put("name", i);
				tmp.put("value", j.size());
				tmp3.add(tmp);
				tmp2.put(i, Boolean.TRUE);
			});
			result.put("seriesData", tmp3);
			result.put("selected", tmp2);
		}
		req.setAttribute("data", JSON.toJSONString(result));

		return "welcome.jsp";
	}
	
	@RequestMapping("/toUpdateHousePage")
	public String toUpdatePage(int hID,HttpServletRequest request) {
		House house = dao.findHouseDetailsById(hID);
		request.getSession().setAttribute("House", house);
		return "updatehouse.jsp";
	}
	
	@RequestMapping("/updateUserPwd")
	@ResponseBody
	public String updateUserPwd(String id,String newPwd,String oldPwd) {
		Users oldUser = new Users();
		oldUser.setuID(Integer.parseInt(id));
		oldUser.setuPassword(oldPwd);
		Users checkUser = service.checkOldPwd(oldUser);
		if(checkUser!=null) {
			Users newUser = new Users();
			newUser.setuID(Integer.parseInt(id));
			newUser.setuPassword(newPwd);
			int n = service.updateUserPwd(newUser);
			if(n>0) {
				return "OK";
			}
		}
		return "FAIL";
	}
}
