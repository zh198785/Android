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
	<form action="${pageContext.request.contextPath}/admin/BookServlet?method=add" method="post" enctype="multipart/form-data" >
		<table border="1">
			<tr>
				<td>标题</td>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td>价格</td>
				<td><input type="text" name="price" /></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input type="text" name="author" /></td>
			</tr>
			<tr>
				<td>数量</td>
				<td><input type="text" name="quantity" /></td>
			</tr>
			<tr>
				<td>分类</td>
				<td>
					<select name="category_id">
						<c:forEach items="${allCategory}" var="category">
							<option value="${category.id}">
								<c:out value="${category.categoryName}"></c:out>
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>选择图片</td>
				<td><input type="file" name="bookImage" /></td>
			</tr>
			<tr>
				<td>介绍</td>
				<td>
					<textarea rows="5" cols="20" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="添加书籍" /></td>
			</tr>
		</table>
	
	</form>
</body>
</html>