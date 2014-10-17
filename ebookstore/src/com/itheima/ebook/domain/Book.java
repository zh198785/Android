package com.itheima.ebook.domain;

public class Book {
	/*
	 表名	t_book	
		字段	类型	描述
		id	varchar(32)	主键
		title	varchar(50)	标题
		price	double(6,2)	价格，最大值：9999.99
		
		author	varchar(50)	作者
		quantity	int	数量
		category_id	varchar(32)	分类表对应的外键
		
		imgUrl	varchar(200)	上传图片的路径
		description	varchar(500)	大纲(介绍)

	 */
	
	private String id;
	private String title;
	private Double price;
	
	private String author;
	private int quantity;
	private String category_id;
	
	private String imgUrl;
	private String description;
	
	//条件查询 价格区间值
	private String startPrice;
	private String endPrice;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}
	public String getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}

	
	

}
