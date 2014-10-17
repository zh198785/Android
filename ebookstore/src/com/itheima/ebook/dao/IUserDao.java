package com.itheima.ebook.dao;

import com.itheima.ebook.domain.User;

public interface IUserDao {
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void save(User user);
	
	/**
	 * 查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	public User find(String username ,String password);

}
