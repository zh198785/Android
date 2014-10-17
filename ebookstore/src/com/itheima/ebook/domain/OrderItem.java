package com.itheima.ebook.domain;

/**
 * 订单项：订单中某一个书籍封装对象
 * @author lt
 *
 */
public class OrderItem {
	/*
	 表名	t_order_item	订单项表
		字段	类型	描述
		id	varchar(32)	主键
		book_id	varchar(32)	书籍表对应的外键
		order_id	varchar(32)	订单表对应的外籍
		quantity	int	购买数量
		price	double(7,2)	小计

	 */
	private String id;
	private int quantity;
	private Double price;
	
	// 多对一 ： 多个订单项 购买 同【一本书籍】
	private Book book;
	// 多对一： 多个订单项 属于 同【一个订单】
	private Order order;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	

	
}
