package com.itheima.ebook.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.ebook.dao.IUserDao;
import com.itheima.ebook.domain.User;
import com.itheima.ebook.utils.JdbcUtils;

public class UserDaoImpl implements IUserDao {

	//1 核心类
	private QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
	
	
	@Override
	public void save(User user) {
		try {
			String sql = "insert into t_user(id,username,password, email,tel,address, role_id) values(?,?,?, ?,?,?, ?)";
			Object[] params = {user.getId(),user.getUsername(),user.getPassword(),
							user.getEmail(),user.getTel(),user.getAddress(),
							user.getRole_id()};
			runner.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User find(String username, String password) {
		try {
			String sql = "select * from t_user where username =? and password=?";
			Object[] params = { username, password };
			return runner.query(sql, new BeanHandler<User>(User.class), params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
