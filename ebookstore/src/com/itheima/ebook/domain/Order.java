package com.itheima.ebook.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {
	/*
	 表名	t_order	
		字段	类型	描述
		id	varchar(32)	主键,订单号
		createDate	date	创建时间
		price	double(8,2)	总价
		user_id	varchar(32)	用户表对应外键
		status	varchar(10)	状态：
				1 保存成功，但未付款
				2 付款成功，但没有发货
				3 发货中，没有收货
				4 收货了，没有结单
				5 结单

	 */
	private String id;
	private Date createDate;
	private Double price;
	private String status;
	
	// 多对一：多个订单 对应 【一个用户】
	private User user;
	
	// 一对多：一个订单 拥有 【多个订单项】--容器 无序不重复Set
	private Set<OrderItem> orderItemSet = new HashSet<OrderItem>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}

	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}
	
	
	
	
	
	
	
	
	

}
