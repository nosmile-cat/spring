package com.ellis.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ellis.order.entity.Order;

@Repository
public interface OrderDao {
	Order getOrderByID(String id);
	
	List<Order> findList();
	
	int addOrder(Order order);
	
	int updateOrder(Order order);
	
	int deleteById(String id);
	
	List<Order> findListPage(Map<String, Object> params);
	
	int queryTotal(Map<String, Object> params);
}
