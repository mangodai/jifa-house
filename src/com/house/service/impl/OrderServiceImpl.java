package com.house.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.dao.OrderMapper;
import com.house.entity.Order;
import com.house.entity.Page;
import com.house.entity.UserOrder;
import com.house.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{

	@Autowired
	private OrderMapper mapper;
	
	@Override
	public int addOrder(Order order) {
		return mapper.addOrder(order);
	}

	@Override
	public List<UserOrder> findAllOrder(Page page) {
		List<UserOrder> orders = mapper.findAllOrder(page);
		if (orders != null && orders.size() != 0) {
			for (UserOrder order : orders) {
                Calendar now = Calendar.getInstance();
				Date payTime = order.getOrderTime();
				if (payTime != null) {
					int payDay = payTime.getDate();
					int nowDay = now.get(Calendar.DAY_OF_MONTH);
					if (nowDay > payDay) {
                        int actualMaximum = now.getActualMaximum(Calendar.DAY_OF_MONTH);
                        order.setAlertTime("还有" + (nowDay + actualMaximum - payDay) + "天要交租了");
					} else if (nowDay < payDay) {
						order.setAlertTime("还有" + (payDay - nowDay) + "天要交租了");
					} else {
						order.setAlertTime("今天要交租了");
					}
				}
			}
		}
		return orders;
	}


    @Override
	public int getOrderCount(int uID) {
		return mapper.getOrderCount(uID);
	}

	@Override
	public int deleteOrder(int oID) {
		return mapper.deleteOrder(oID);
	}

}
