<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
	<%@ include file="/WEB-INF/pages/user/header.jsp" %>
	${msg}
	
	<form action="${pageContext.request.contextPath}/user/UserServlet?method=login" method="post">
		<table border="1">
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username"/></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<%--自己完成功能：记住账号，自动登录 --%>
			<tr>
				<td></td>
				<td><input type="submit" value="登录"/></td>
			</tr>
		</table>
	</form>
	
	
	
</body>
</html>