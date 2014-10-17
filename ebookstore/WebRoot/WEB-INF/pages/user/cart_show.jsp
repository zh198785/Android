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
	<%@ include file="/WEB-INF/pages/user/header.jsp" %>
	
	<c:if test="${empty cart || empty cart.data }">
		您还没有购买任何书籍
	</c:if>
	
	<c:if test="${not (empty cart || empty cart.data) }">
		<table border="1">
			<tr>
				<th>标题</th>
				<th>单价</th>
				<th>购买数量</th>
				<th>小计</th>
			</tr>
			<c:forEach items="${cart.data}" var="entry">
				<%--
					${entry.key}  bookid
					${entry.value}  CartItem
					${entry.value.price}  小计
					${entry.value.book}  书籍
					${entry.value.quantity}  购买数量
					
					${entry.value.book.title}  书籍的标题
					${entry.value.book.price}  书籍的单价
					
					${cart.price}	总价
				--%>
				<tr>
					<td>${entry.value.book.title}</td>
					<td>${entry.value.book.price} </td>
					<td>
						<a style="text-decoration: none;" href="${pageContext.request.contextPath}/user/BookServlet?method=removeBookToCart&bookId=${entry.value.book.id}">-</a>
						${entry.value.quantity}
						<a style="text-decoration: none;" href="${pageContext.request.contextPath}/user/BookServlet?method=addBookToCart&bookId=${entry.value.book.id}">+</a>
					</td>
					<td>${entry.value.price}</td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td>总价</td>
				<td>${cart.price}</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td>
					<a href="${pageContext.request.contextPath}/user/OrderServlet?method=addOrder">去结算(下订单)</a>
				</td>
			</tr>
		
		</table>
	</c:if>
	
	
</body>
</html>