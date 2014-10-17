package com.itheima.spring;

public class Property {
	
	private String propName;
	private String propRef;
	private String propValue; //无用
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public String getPropRef() {
		return propRef;
	}
	public void setPropRef(String propRef) {
		this.propRef = propRef;
	}
	public String getPropValue() {
		return propValue;
	}
	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}
	public Property() {
		super();
	}
	public Property(String propName, String propRef, String propValue) {
		super();
		this.propName = propName;
		this.propRef = propRef;
		this.propValue = propValue;
	}
	
	
	

}
