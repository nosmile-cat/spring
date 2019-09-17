package com.ellis.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ellis.order.dao.OrderDao;
import com.ellis.order.entity.Order;
import com.ellis.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao orderDao;
	@Override
	public Order getOrderByID(String id) {
		// TODO Auto-generated method stub
		return orderDao.getOrderByID(id);
	}
	
	@Override
	public List<Order> findList() {
		// TODO Auto-generated method stub
		return orderDao.findList();
	}
	
	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		int result = orderDao.addOrder(order);
		//int i = 1/0;//测试事务管理
		return result;
	}
	
	@Override
	public int updateOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.updateOrder(order);
	}
	
	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		int result = orderDao.deleteById(id);
		//int i = 1/0;
		return result;
	}
	
	@Override
	public Map<String, Object> findListPage(Map<String, Object> params, int start, int end) {
		// TODO Auto-generated method stub
		int totalRst=orderDao.queryTotal(params);
		params.put("start", start);
		params.put("end", end);
		List<Order> data=orderDao.findListPage(params);
		Map<String,Object>result=new HashMap<String, Object>();
		result.put("data", data);
		result.put("total", totalRst);
		return result;
		
	}
}
