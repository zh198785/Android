<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/pages/user/header.jsp" %>
	<form action="${pageContext.request.contextPath}/payServlet" method="post">
		<table>
			<tr>
				<%--订单号Order.id --%>
				<td>商品编号：</td>
				<td colspan="3"><input type="text" name="orderId" /></td>
			</tr>
			<tr>
				<%--总价：order.price --%>
				<td>价格：</td>
				<td colspan="3"><input type="text" name="amount" />元</td>
			</tr>
			<tr>
				<td colspan="4">请选择在线支付银行：</td>
			</tr>
			<tr>
				<td><input type="radio" name="frpId" value="ICBC-NET"/>中国工商银行 </td>
				<td><input type="radio" name="frpId" value="ABC-NET"/>中国农业银行</td>
				<td><input type="radio" name="frpId" value="CMBCHINA-NET"/>招商银行</td>
				<td><input type="radio" name="frpId" value="BOC-NET"/> 中国银行 </td>
			</tr>
			<tr>
				<td><input type="radio" name="frpId" value="CCB-NET"/>建设银行</td>
				<td><input type="radio" name="frpId" value="BCCB-NET"/> 北京银行</td>
				<td><input type="radio" name="frpId" value="CMBC-NET"/>中国民生银行总</td>
				<td><input type="radio" name="frpId" value="HXB-NET"/>华夏银行 </td> 
			</tr>
			<tr>
				<td><input type="radio" name="frpId" value="POST-NET"/> 中国邮政 </td>
				<td><input type="radio" name="frpId" value="ECITIC-NET"/>中信银行</td>
				<td><input type="radio" name="frpId" value="BOCO-NET"/>交通银行</td>
				<td><input type="radio" name="frpId" value="CEB-NET"/>中国光大银行</td>
			</tr>
			<tr>
				<td><input type="radio" name="frpId" value="CITI-NET"/>美国花旗银行</td>
			</tr>
			<tr>
				<td colspan="4"><input type="submit" value="付款"/> </td>
			</tr>
		</table>
		 <br/>
		<br/>
		
		 

 











	
	</form>

</body>
</html>