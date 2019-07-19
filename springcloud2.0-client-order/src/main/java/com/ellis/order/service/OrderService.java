package com.ellis.order.service;

import java.util.List;

import com.ellis.order.entity.Order;

public interface OrderService {
	Order getOrderByID(String orderId);
	List<Order> findList();
	int addOrder(Order order);
	int updateOrder(Order order);
	int deleteById(String orderId);
}
