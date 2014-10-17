package com.itheima.spring;

import java.util.ArrayList;
import java.util.List;

/**
 * 是配置<bean>元素一个封装对象
 * @author lt
 *
 */
public class Bean {
	
	private String beanId;		//id attr
	private String beanClass;	//class attr
	private String beanScope;	//scope attr
	
	//1对多：提供 <property> 封装
	private List<Property> allProp = new ArrayList<Property>(); //建议：将集合进行实例化，方便操作
	
	public String getBeanId() {
		return beanId;
	}
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}
	public String getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	public String getBeanScope() {
		return beanScope;
	}
	public void setBeanScope(String beanScope) {
		this.beanScope = beanScope;
	}
	public Bean() {
		super();
	}
	public Bean(String beanId, String beanClass, String beanScope) {
		super();
		this.beanId = beanId;
		this.beanClass = beanClass;
		this.beanScope = beanScope;
	}
	public List<Property> getAllProp() {
		return allProp;
	}
	public void setAllProp(List<Property> allProp) {
		this.allProp = allProp;
	}

	
}
