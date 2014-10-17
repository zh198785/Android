package com.itheima.ebook.dao;

import com.itheima.ebook.domain.Order;

public interface IOrderDao {

	/**
	 * 保存
	 * @param order
	 */
	void save(Order order);

}
