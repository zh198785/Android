package com.itheima.ebook.domain;

/**
 * 购物项对象CartItem (购买某一本书籍封装对象)
 * @author lt
 *
 */
public class CartItem {
	
	private Book book;		//购买的书籍
	private int quantity;	//购买数量
	private Double price;	//小计(单价*数量)
	
	
	//获得小计的值
	public Double getPrice() {
		this.price = this.book.getPrice() * this.quantity;
		return price;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	

}
