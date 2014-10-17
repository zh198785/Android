<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/admin/CategoryServlet?method=add" method="post">
		<%-- <input type="hidden" name="method" value="add" />--%>
		<table border="1">
			<tr>
				<td>分类名称</td>
				<td><input type="text" name="categoryName" style="width: 500px" /></td>
			</tr>
			<tr>
				<td>分类描述</td>
				<td>
					<textarea rows="5" cols="60" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="添加分类"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>