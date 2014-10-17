package com.itheima.ebook.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 购物车
 * @author lt
 */
public class Cart {
	
	private Double price;		//总价
	// 一对多：一个购物车 可以 拥有【多个购物项】 -- 使用Map，快速查询
	// * key : 书籍的id ; value : 购买的某一本书
	private Map<String, CartItem> data = new HashMap<String, CartItem>();
	
	/**
	 * 添加
	 * @param book
	 */
	public void addBook(Book book) {
		/*
		 * ## 如果没有 数量默认1
		 * ## 如果有 累加+1
		 */
		//1 通过book id 获得对应对象
		CartItem cartItem = data.get(book.getId());
		//2 没有 第一次
		if(cartItem == null){
			cartItem = new CartItem();
			cartItem.setBook(book);
			cartItem.setQuantity(1);
			data.put(book.getId(), cartItem);
		} else {
			//有 第二次及之后
			cartItem.setQuantity(cartItem.getQuantity() + 1);
		}
	}
	
	/**
	 * 移除 ，如果数量大于2 -1 ， 如果等于1将移除数据
	 * @param bookId
	 */
	public void removeBook(String bookId) {
		//1 通过book id 获得对应对象
		CartItem cartItem = data.get(bookId);
		//2 如果有再操作
		if(cartItem != null){
			int quantity = cartItem.getQuantity();
			if(quantity >= 2){
				cartItem.setQuantity(quantity - 1) ;
			} else {
				data.remove(bookId);
			}
		}
	}
	
	
	// 总价：所有小计的和
	public Double getPrice() {
		Double sum = 0.0;
		for(Map.Entry<String,CartItem> entry : data.entrySet()){
			CartItem cartItem = entry.getValue();
			sum += cartItem.getPrice();
		}
		this.price = sum;
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Map<String, CartItem> getData() {
		return data;
	}
	public void setData(Map<String, CartItem> data) {
		this.data = data;
	}


	
	
	
	

}
