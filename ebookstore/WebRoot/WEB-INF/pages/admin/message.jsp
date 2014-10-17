<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	${requestScope.msg}  <%--声明式从request作用域获得内容 --%> <br/>
	${msg} <%-- 依次从page/request/session/application 获得数据 --%> <br/>
</body>
</html>