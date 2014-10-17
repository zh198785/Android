package com.itheima.ebook.dao.impl;

import java.sql.Connection;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.itheima.ebook.dao.IOrderDao;
import com.itheima.ebook.domain.Order;
import com.itheima.ebook.domain.OrderItem;
import com.itheima.ebook.utils.JdbcUtils;

public class OrderDaoImpl implements IOrderDao {

	//1 核心类：需要使用事务，注意：不能提供数据源
	private QueryRunner runner =  new QueryRunner();
	
	/**
	 * 订单 关联 订单项
	 * * 思考：在保存订单时，是否需要保存订单项？
	 * * 需要在保存订单时，来保存订单项 -- 级联保存
	 */
	@Override
	public void save(Order order) {
		Connection conn = null;
		try {
			//#1 获得连接
			conn = JdbcUtils.getConnection();
			//#2 开始事务
			conn.setAutoCommit(false);
			
			// 1 保存订单
			String sql = "insert into t_order(id,price,user_id,status) values(?,?,?,?)";
			Object[] params = {order.getId(),order.getPrice(),
							order.getUser().getId(),order.getStatus()};
			runner.update(conn, sql, params);
			
			// 2 保存订单项
			sql = "insert into t_order_item(id,book_id,order_id, quantity,price) values(?,?,?, ?,?)";
			Object[][] paramsArr = new Object[order.getOrderItemSet().size()][];
			int i = 0;
			for(OrderItem orderItem : order.getOrderItemSet()){
				paramsArr[i] = new Object[]{orderItem.getId(),orderItem.getBook().getId(),orderItem.getOrder().getId(),
						orderItem.getQuantity(),orderItem.getPrice()};
				i++;
			}
			runner.batch(conn, sql, paramsArr);
			
			//#3 提交并关闭
			DbUtils.commitAndCloseQuietly(conn);
		} catch (Exception e) {
			//#4 回滚并关闭
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new RuntimeException(e);
		}
	}

}
