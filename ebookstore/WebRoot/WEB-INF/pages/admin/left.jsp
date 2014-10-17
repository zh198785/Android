<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>分类管理</h3>
	<a target="mainFrame" href="${pageContext.request.contextPath}/admin/UIServlet?n=category_add">添加分类</a> <br/>
	<a target="mainFrame" href="${pageContext.request.contextPath}/admin/CategoryServlet?method=findAll">查询所有分类</a> <br/>
	<h3>图书管理</h3>
	<%--显示添加页面，但需要在显示之前，查询所有分类并展示 --%>
	<a target="mainFrame" href="${pageContext.request.contextPath}/admin/BookServlet?method=addUI">添加图书</a> <br/>
</body>
</html>