package com.itheima.ebook.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class MyBeanUtils {
	
	/**
	 * 封装数据
	 * @param beanClass
	 * @param properties
	 * @return
	 */
	public static <T> T populate(Class<T> beanClass , Map<String,String[]> properties){
		try {
			//1 使用反射创建实例
			T bean = beanClass.newInstance();
			
			//2 自定义时间转换器
			DateConverter dateConverter = new DateConverter();
			dateConverter.setPattern("yyyy-MM-dd");
			ConvertUtils.register(dateConverter, java.util.Date.class);
			
			//3 使用工具封装
			BeanUtils.populate(bean, properties);
			
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
