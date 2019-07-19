package com.ellis.order.entity;

public class Order {
	private String orderId;
	private String orderName;
	private String price;
	
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String orderId, String orderName, String price) {
		super();
		this.orderId = orderId;
		this.orderName = orderName;
		this.price = price;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderName=" + orderName + ", price=" + price + "]";
	}
	
}
