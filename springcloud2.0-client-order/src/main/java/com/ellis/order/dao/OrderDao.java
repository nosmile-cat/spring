package com.ellis.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ellis.order.entity.Order;

@Repository
public interface OrderDao {
	Order getOrderByID(String orderId);
	
	List<Order> findList();
	
	int addOrder(Order order);
	
	int updateOrder(Order order);
	
	int deleteById(String orderId);
}
