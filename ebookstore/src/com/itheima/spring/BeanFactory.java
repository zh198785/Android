package com.itheima.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanFactory {
	
	//key ： 唯一标识 ; value : 自定义对象(封装所有的数据)
	private static Map<String, Bean> dataMap = new HashMap<String, Bean>();
	public static Map<String, Bean> getDataMap() {
		return dataMap;
	}
	
	static{
		//初始化：相当于读取配置文件
		// * 内部赋值
		//dataMap.put("categoryDaoId", new Bean("categoryDaoId", "com.itheima.ebook.dao.impl.CategoryDaoImpl", null));
		// * 外部赋值
		//BeanFactory.getDataMap().put("", null);
	}
	
	
	
	/**
	 * 从工厂获得指定对象的实例
	 * @param beanId 自定义的字符串(别名) --唯一
	 * @return
	 */
	public static Object getBean(String beanId){
		try {
			//1 通过id获得 bean对象,获得配置文件所有内容
			Bean beanObj = dataMap.get(beanId);
			if(beanObj == null){
				throw new RuntimeException("创建的实例的标示符["+beanId+"]不存在,请检查applicationContext.xml文件");
			}
			//2 获得实现类
			String beanClass = beanObj.getBeanClass();
			//3 获得Class对象
			Class clazz = Class.forName(beanClass);
			//4获得实例
			Object obj = clazz.newInstance();  //new BusinessServiceImpl()
			/**5自动的注入属性*/
			// * 获得所有<property> ，执行setter方法
			List<Property> allProp = beanObj.getAllProp();
			for(Property prop : allProp){
				// * 获得名称
				String propName = prop.getPropName();
				// * 获得引用名称
				String propRef = prop.getPropRef();
				// * 封装即可
				BeanUtils.setProperty(obj, propName, getBean(propRef));
			}
			/** 5.end*/
			
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T getBean(String beanId,Class<T> clazz){
		return (T)getBean(beanId);
	}

}
