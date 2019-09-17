package com.ellis.order.entity;

public class Order {
	private String id;
	private String orderName;
	private String price;
	
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String id, String orderName, String price) {
		super();
		this.id = id;
		this.orderName = orderName;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		return "Order [id=" + id + ", orderName=" + orderName + ", price=" + price + "]";
	}
	
}
