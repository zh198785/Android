<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>名称</td>
			<td>介绍</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${allCategory}" var="category">
			<tr>
				<td><c:out value="${category.categoryName}"/></td>
				<td><c:out value="${category.description}"/></td>
				<td>
					<%-- 先查询，在更新 --%>
					<a href="${pageContext.request.contextPath}/admin/CategoryServlet?method=editUI&id=${category.id}">修改分类</a>
					<%--注意：删除书分类时，如果此分类已经被书籍使用了，将不能删除 --%>
					<a href="${pageContext.request.contextPath}/admin/CategoryServlet?method=delete&id=${category.id}">删除分类</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>