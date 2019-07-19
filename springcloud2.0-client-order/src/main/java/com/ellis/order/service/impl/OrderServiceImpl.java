package com.ellis.order.service.impl;

import java.util.List;

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
	public Order getOrderByID(String orderId) {
		// TODO Auto-generated method stub
		return orderDao.getOrderByID(orderId);
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
	public int deleteById(String orderId) {
		// TODO Auto-generated method stub
		int result = orderDao.deleteById(orderId);
		//int i = 1/0;
		return result;
	}
}
