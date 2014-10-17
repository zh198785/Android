package com.itheima.ebook.dao;

import java.util.List;

import com.itheima.ebook.domain.Category;

public interface ICategoryDao {
	
	/**
	 * 保存分类
	 * @param category
	 */
	public void save(Category category);
	
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAll();
	
	/**
	 * 通过id更新分类
	 * @param category
	 */
	public void update(Category category);
	
	/**
	 * 通过id查询
	 * @param id
	 */
	public void delete(String id);

}
