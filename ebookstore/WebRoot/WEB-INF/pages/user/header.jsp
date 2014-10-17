<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<a href="${pageContext.request.contextPath}/index.jsp">首页</a>
<%--没有登录 --%>
<c:if test="${empty sessionScope.loginUser}">
	<a href="${pageContext.request.contextPath}/user/UIServlet?n=register">注册</a>
	<a href="${pageContext.request.contextPath}/user/UIServlet?n=login">登录</a>
</c:if>
<%--登录 --%>
<c:if test="${not empty sessionScope.loginUser}">
	<a href="${pageContext.request.contextPath}/user/UserServlet?method=logout">注销</a>
</c:if>
<a href="${pageContext.request.contextPath}/user/UIServlet?n=cart_show">显示购物车</a>

<a href="${pageContext.request.contextPath}/clock.jsp">图片表</a>
<a href="${pageContext.request.contextPath}/user/UIServlet?n=pay">在线支付</a>

<hr/>
    
    