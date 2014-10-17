package com.itheima.ebook.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.ebook.dao.ICategoryDao;
import com.itheima.ebook.domain.Category;
import com.itheima.ebook.utils.JdbcUtils;

public class CategoryDaoImpl implements ICategoryDao {

	//1 核心类
	private QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource()); 
	@Override
	public void save(Category category) {
		try {
			//2 sql语句
			String sql = "insert into t_category(id,categoryName,description) values(?,?,?)";
			//3 实际参数
			Object[] params = {category.getId(),category.getCategoryName(),category.getDescription()};
			//4 执行
			runner.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Category> findAll() {
		try {
			//2 sql语句
			String sql = "select * from t_category";
			//3 实际参数
			Object[] params = {};
			//4 执行
			return runner.query(sql, new BeanListHandler<Category>(Category.class), params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Category category) {
		
	}

	@Override
	public void delete(String id) {
		
	}

}
