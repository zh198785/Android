package com.itheima.ebook.service;

import java.util.List;

import com.itheima.ebook.domain.Book;
import com.itheima.ebook.domain.Cart;
import com.itheima.ebook.domain.Category;
import com.itheima.ebook.domain.Order;
import com.itheima.ebook.domain.Page;
import com.itheima.ebook.domain.User;

public interface IBusinessService {
	
	/**
	 * 添加分类
	 * @param category
	 */
	public void addCategory(Category category);
	
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAllCategory();
	
	
	/**
	 * 注册用户
	 * @param user
	 */
	public void register(User user);
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user);

	/**
	 * 添加书籍
	 * @param book
	 */
	public void addBook(Book book);

	/**
	 * 查询指定分类的书籍
	 * @param categoryId
	 * @return
	 */
	public List<Book> findAllBookWithCategory(String categoryId);
	
	/**
	 * 查询指定分类,带有条件的所有书籍
	 * @param categoryId
	 * @param conditionBook
	 * @return
	 */
	public List<Book> findAllBookWithCategory(String categoryId,Book conditionBook);
	
	/**
	 * 查询指定分类,带有条件的所有书籍的分页数据
	 * @param categoryId
	 * @param conditionBook
	 * @param pageNum 
	 * @param pageSize
	 * @return
	 */
	public Page<Book> findAllBookWithCategory(String categoryId,Book conditionBook,int pageNum,int pageSize);

	/**
	 * 添加指定书籍到购物车
	 * @param bookId
	 * @param cart
	 */
	public void addBookToCart(String bookId, Cart cart);

	/**
	 * 移除指定书籍到购物车
	 * @param bookId
	 * @param cart
	 */
	public void removeBookToCart(String bookId, Cart cart);

	/**
	 * 下订单
	 * @param cart 购物车
	 * @param loginUser 登录用户
	 * @return
	 */
	public Order addOrder(Cart cart, User loginUser);

}
