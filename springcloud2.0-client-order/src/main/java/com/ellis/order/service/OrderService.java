package com.ellis.order.service;

import java.util.List;
import java.util.Map;

import com.ellis.order.entity.Order;

public interface OrderService {
	Order getOrderByID(String id);
	List<Order> findList();
	int addOrder(Order order);
	int updateOrder(Order order);
	int deleteById(String id);
	Map<String, Object> findListPage(Map<String, Object> params,int start,int end);
	
}
