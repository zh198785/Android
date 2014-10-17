package com.itheima.ebook.service.impl;

import java.util.List;
import java.util.Map;

import com.itheima.ebook.dao.IBookDao;
import com.itheima.ebook.dao.ICategoryDao;
import com.itheima.ebook.dao.IOrderDao;
import com.itheima.ebook.dao.IUserDao;
import com.itheima.ebook.domain.Book;
import com.itheima.ebook.domain.Cart;
import com.itheima.ebook.domain.CartItem;
import com.itheima.ebook.domain.Category;
import com.itheima.ebook.domain.Order;
import com.itheima.ebook.domain.OrderItem;
import com.itheima.ebook.domain.Page;
import com.itheima.ebook.domain.User;
import com.itheima.ebook.service.IBusinessService;
import com.itheima.ebook.utils.StringUtils;

public class BusinessServiceImpl implements IBusinessService{

	// 方案1：接口实现类
//	private ICategoryDao categoryDao = new CategoryDaoImpl();
//	private IUserDao userDao = new UserDaoImpl();
	
	// 方法2： 从Bean工厂获得 -- IoC：控制反转：将对象创建的权利反转给工厂。(让工厂去创建对象)
//	private ICategoryDao categoryDao = (ICategoryDao) BeanFactory.getBean("categoryDaoId");
//	private IUserDao userDao = BeanFactory.getBean("userDaoId",IUserDao.class);
//	private IBookDao bookDao = BeanFactory.getBean("bookDaoId", IBookDao.class);
	
	// 方案3： DI 依赖注入,必须setter方法
	private ICategoryDao categoryDao;
	private IUserDao userDao;
	private IBookDao bookDao;
	private IOrderDao orderDao;
	
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	
	@Override
	public void addCategory(Category category) {
		//设置服务器自动生成数据
		category.setId(StringUtils.getUUID());
		categoryDao.save(category);
	}

	@Override
	public List<Category> findAllCategory() {
		return categoryDao.findAll();
	}

	@Override
	public void register(User user) {
		user.setId(StringUtils.getUUID());
		user.setRole_id("普通用户"); //预留扩展，默认值
		// 将密码加密
		user.setPassword(StringUtils.getMD5Value(user.getPassword()));
		userDao.save(user);
	}

	@Override
	public User login(User user) {
		// 将秘密加密之后再查询
		String password = StringUtils.getMD5Value(user.getPassword());
		return userDao.find(user.getUsername(), password);
	}

	@Override
	public void addBook(Book book) {
		book.setId(StringUtils.getUUID());
		bookDao.save(book);
	}

	@Override
	public List<Book> findAllBookWithCategory(String categoryId) {
		return bookDao.findAllWithCategory(categoryId);
	}

	@Override
	public List<Book> findAllBookWithCategory(String categoryId,
			Book conditionBook) {
		return bookDao.findAllWithCategory(categoryId,conditionBook);
	}

	@Override
	public Page<Book> findAllBookWithCategory(String categoryId, Book conditionBook,
			int pageNum, int pageSize) {
		// 1 查询总记录数
		int totalRecord = bookDao.getTotalRecord(categoryId,conditionBook);
		Page<Book> page = new Page<Book>(pageNum, pageSize, totalRecord);
		List<Book> data = bookDao.findAllWithCategory(categoryId, conditionBook,page.getStartIndex(),page.getPageSize());
		page.setData(data);
		return page;
	}
	@Override
	public void addBookToCart(String bookId, Cart cart) {
		//通过id查询书籍
		Book book = bookDao.findById(bookId);
		//添加
		cart.addBook(book);
	}
	@Override
	public void removeBookToCart(String bookId, Cart cart) {
		cart.removeBook(bookId);
	}
	/**
	 *  购物车Cart，页面中购买的东西
	 *  购物项CartItem，购物车中的每一项
	 *  订单Order，数据来自购物车
	 *  订单项OrderItem，数据来自购物项
	 */
	@Override
	public Order addOrder(Cart cart, User loginUser) {
		Order order = new Order();
		//给订单进行数据封装 -- 数据来自购物车
		// 1 订单号
		order.setId(StringUtils.getUUID());
		// 2 创建时间 -- 可以不写 Timestamp 时间戳
		// 3 总价
		order.setPrice(cart.getPrice());
		// 4 状态，默认1
		order.setStatus("1");
		// 5 用户，购物者
		order.setUser(loginUser);
		// 6 给多个订单项封装数据
		// * 遍历购物项
		for(Map.Entry<String,CartItem> entry:cart.getData().entrySet()){
			// 6.1 创建订单项
			OrderItem orderItem = new OrderItem();
			// 6.2 封装数据
			// * 购物项
			CartItem cartItem = entry.getValue();
			orderItem.setId(StringUtils.getUUID());
			// * 购买数量
			orderItem.setQuantity(cartItem.getQuantity());
			// * 小计
			orderItem.setPrice(cartItem.getPrice());
			// * 书籍
			orderItem.setBook(cartItem.getBook());
			// * 订单
			orderItem.setOrder(order);
			
			// 6.3 将订单项添加到订单
			order.getOrderItemSet().add(orderItem);
		}
		
		// 保存订单
		orderDao.save(order);
		// 清空购物车
		cart.getData().clear();
		
		return order;
	}


	
	
	
	
	
	
	
	
	
	
}
