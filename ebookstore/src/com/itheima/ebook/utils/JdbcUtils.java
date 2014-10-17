package com.itheima.ebook.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	//1 提供连接池
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource("ebookstoreXml");
	
	/**
	 * 获得数据源，及获得连接池
	 * @return
	 */
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	/**
	 * 获得新的连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}

}
