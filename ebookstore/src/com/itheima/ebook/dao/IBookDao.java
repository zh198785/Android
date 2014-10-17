package com.itheima.ebook.dao;

import java.util.List;

import com.itheima.ebook.domain.Book;

public interface IBookDao {

	/**
	 * 添加书籍
	 * @param book
	 */
	void save(Book book);

	/**
	 * 查询指定分类的所有书籍
	 * @param categoryId
	 * @return
	 */
	List<Book> findAllWithCategory(String categoryId);
	/**
	 * 查询指定分类，带有条件的查询所有
	 * @param categoryId
	 * @param conditionBook
	 * @return
	 */
	List<Book> findAllWithCategory(String categoryId,Book conditionBook);

	/**
	 * 查询指定分类，带有条件的总记录数
	 * @param categoryId
	 * @param conditionBook
	 * @return
	 */
	int getTotalRecord(String categoryId, Book conditionBook);

	/**
	 * 查询指定分类，带有条件的分页数
	 * @param categoryId
	 * @param conditionBook
	 * @param pageNum
	 * @param startIndex
	 * @return
	 */
	List<Book> findAllWithCategory(String categoryId, Book conditionBook,
			int startIndex, int pageSize);

	/**
	 * 通过id查询
	 * @param bookId
	 * @return
	 */
	Book findById(String bookId);

}
