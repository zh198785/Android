package com.itheima.ebook.domain;

public class User {
	/*
	 表名	t_user	
	字段	类型	描述
	id	varchar(32)	主键
	username	varchar(50)	用户名
	password	varchar(32)	密码，将使用MD5加密
	email	varchar(50)	电子邮箱
	tel	varchar(11)	电话
	address	varchar(200)	地址
	role_id	varchar(32)	角色表对应的外键 --用于权限管理扩展

	 */
	private String id;
	private String username;
	private String password;
	
	private String email;
	private String tel;
	private String address;
	
	private String role_id;
	private String repassword;		//确认密码，只在表单校验时使用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	

}
