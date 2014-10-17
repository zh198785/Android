package com.itheima.ebook.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.itheima.ebook.dao.IBookDao;
import com.itheima.ebook.domain.Book;
import com.itheima.ebook.utils.JdbcUtils;

public class BookDaoImpl implements IBookDao {

	//1 核心类
	private QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
	
	@Override
	public void save(Book book) {
		try {
			//2 sql语句
			String sql = "insert into t_book(id,title,price, author,quantity,category_id, imgUrl,description) values(?,?,?, ?,?,?, ?,?)";
			//3 实际参数
			Object[] params = {book.getId(),book.getTitle(),book.getPrice(),
							book.getAuthor(),book.getQuantity(),book.getCategory_id(),
							book.getImgUrl(),book.getDescription()};
			//4 执行
			runner.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Book> findAllWithCategory(String categoryId) {
		try {
			String sql = "select * from t_book where category_id = ?";
			Object[] params = {categoryId};
			return runner.query(sql, new BeanListHandler<Book>(Book.class), params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Book> findAllWithCategory(String categoryId, Book conditionBook) {
		try {
			/**条件查询 start*/
			StringBuilder sqlBuilder = new StringBuilder("select * from t_book where category_id = ? ");
			List<Object> paramsList = new ArrayList<Object>();
			// * 添加分类的id
			paramsList.add(categoryId);
			
			// 条件
			// * 标题
			if(StringUtils.isNotBlank(conditionBook.getTitle())){
				sqlBuilder.append(" and title like ?");
				paramsList.add("%"+conditionBook.getTitle()+"%");
			}
			// * 作者
			if(StringUtils.isNotBlank(conditionBook.getAuthor())){
				sqlBuilder.append(" and author like ?");
				paramsList.add("%"+conditionBook.getAuthor()+"%");
			}
			// * 价格
			if(StringUtils.isNotBlank(conditionBook.getStartPrice())){
				sqlBuilder.append(" and price >= ?");
				paramsList.add(conditionBook.getStartPrice());
			}
			if(StringUtils.isNotBlank(conditionBook.getEndPrice())){
				sqlBuilder.append(" and price <= ?");
				paramsList.add(conditionBook.getEndPrice());
			}
			
			/**条件查询 end*/
			
			
			
			
			String sql = sqlBuilder.toString();
			Object[] params = paramsList.toArray();
			return runner.query(sql, new BeanListHandler<Book>(Book.class), params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getTotalRecord(String categoryId, Book conditionBook) {
		try {
			/**条件查询 start*/
			StringBuilder sqlBuilder = new StringBuilder("select count(*) from t_book where category_id = ? ");
			List<Object> paramsList = new ArrayList<Object>();
			// * 添加分类的id
			paramsList.add(categoryId);
			
			// 条件
			// * 标题
			if(StringUtils.isNotBlank(conditionBook.getTitle())){
				sqlBuilder.append(" and title like ?");
				paramsList.add("%"+conditionBook.getTitle()+"%");
			}
			// * 作者
			if(StringUtils.isNotBlank(conditionBook.getAuthor())){
				sqlBuilder.append(" and author like ?");
				paramsList.add("%"+conditionBook.getAuthor()+"%");
			}
			// * 价格
			if(StringUtils.isNotBlank(conditionBook.getStartPrice())){
				sqlBuilder.append(" and price >= ?");
				paramsList.add(conditionBook.getStartPrice());
			}
			if(StringUtils.isNotBlank(conditionBook.getEndPrice())){
				sqlBuilder.append(" and price <= ?");
				paramsList.add(conditionBook.getEndPrice());
			}
			
			/**条件查询 end*/
			
			String sql = sqlBuilder.toString();
			Object[] params = paramsList.toArray();
			Long numLong = (Long)runner.query(sql, new ScalarHandler(), params);
			return numLong.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Book> findAllWithCategory(String categoryId,
			Book conditionBook, int startIndex, int pageSize ) {
		try {
			/**条件查询 start*/
			StringBuilder sqlBuilder = new StringBuilder("select * from t_book where category_id = ? ");
			List<Object> paramsList = new ArrayList<Object>();
			// * 添加分类的id
			paramsList.add(categoryId);
			
			// 条件
			// * 标题
			if(StringUtils.isNotBlank(conditionBook.getTitle())){
				sqlBuilder.append(" and title like ?");
				paramsList.add("%"+conditionBook.getTitle()+"%");
			}
			// * 作者
			if(StringUtils.isNotBlank(conditionBook.getAuthor())){
				sqlBuilder.append(" and author like ?");
				paramsList.add("%"+conditionBook.getAuthor()+"%");
			}
			// * 价格
			if(StringUtils.isNotBlank(conditionBook.getStartPrice())){
				sqlBuilder.append(" and price >= ?");
				paramsList.add(conditionBook.getStartPrice());
			}
			if(StringUtils.isNotBlank(conditionBook.getEndPrice())){
				sqlBuilder.append(" and price <= ?");
				paramsList.add(conditionBook.getEndPrice());
			}
			
			/**条件查询 end*/
			
			/**分页start*/
			sqlBuilder.append(" limit ?,?");
			paramsList.add(startIndex);
			paramsList.add(pageSize);
			/**分页end*/
			
			
			String sql = sqlBuilder.toString();
			Object[] params = paramsList.toArray();
			return runner.query(sql, new BeanListHandler<Book>(Book.class), params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Book findById(String bookId) {
		try {
			String sql = "select * from t_book where id = ?";
			Object[] params = {bookId};
			return runner.query(sql, new BeanHandler<Book>(Book.class), params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
