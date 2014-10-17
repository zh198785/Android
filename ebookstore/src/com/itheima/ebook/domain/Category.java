package com.itheima.ebook.domain;

public class Category {
	
	/*
	 表名	t_category	
	字段	类型	描述
	id			varchar(32)	主键
	categoryName	varchar(50)	分类名称
	description	varchar(200)	分类描述

	 */
	private String id;
	private String categoryName;
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
