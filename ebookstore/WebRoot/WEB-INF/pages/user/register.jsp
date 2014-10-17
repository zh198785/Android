<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
</head>
<body>
	<%@ include file="/WEB-INF/pages/user/header.jsp" %>
	
	${msg}
	<form action="${pageContext.request.contextPath}/user/UserServlet?method=register" method="post">
		<table border="1">
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>确认密码</td>
				<td><input type="password" name="repassword" /></td>
			</tr>
			<tr>
				<td>电子邮箱</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>电话</td>
				<td><input type="text" name="tel" /></td>
			</tr>
			<tr>
				<td>地址</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>验证码</td>
				<td>
					<img id="verifyCodeImageId" onclick="change()" src="${pageContext.request.contextPath}/user/VerifyCodeServlet"/>
					<input type="text" name="verifyCode" size="6" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="注册" /></td>
			</tr>
		</table>
	</form>
	
	<script type="text/javascript">
		function change(){
			document.getElementById("verifyCodeImageId").src="${pageContext.request.contextPath}/user/VerifyCodeServlet?d=" + new Date();
		}
	</script>
</body>
</html>