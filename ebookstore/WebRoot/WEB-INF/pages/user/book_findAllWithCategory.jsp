<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
			function changeUrl(num){
				document.getElementById("pageNum").value = num;
				document.getElementById("conditionFormId").submit();
			}
	</script>
</head>
<body>
	<%--将请求抽取 --%>
	<c:url value="/user/BookServlet" var="baseUrl">
		<c:param name="method" value="findAllWithCategory"></c:param>
	</c:url>
	

	<%@ include file="/WEB-INF/pages/user/header.jsp" %>
	<%--分类列表 --%>
	<div style="border:1px solid #ABABAB;height:400px;width:200px;float:left;">
		<h3>分类列表</h3>
		<c:forEach items="${allCategory}" var="category">
			<a href="${baseUrl}&categoryId=${category.id}">${category.categoryName}</a> <br/>
		</c:forEach>
	</div>
	
	<%--指定分类书籍数据 start --%>
	<div style="border:1px solid #ABABAB;min-height:400px; width:500px;float:left;margin-left:10px;">
		<%--条件查询 --%>
		<fieldset>
			<legend>条件查询</legend>
			<form id="conditionFormId" action="${baseUrl}" method="post">
				<!-- 当前分类的id -->
				<input type="hidden" name="categoryId" value="${categoryId}" />
				<!-- 设置的当前页 -->
				<input type="hidden" id="pageNum" name="pageNum" value="1"/>
				<table>
					<tr>
						<td>标题</td>
						<td><input type="text" name="title" value="${conditionBook.title}"/></td>
						<td>作者</td>
						<td><input type="text" name="author" value="${conditionBook.author}"/></td>
					</tr>
					<tr>
						<td>价格</td>
						<td colspan="3">
							<input type="text" name="startPrice" value="${conditionBook.startPrice}" size="6"/> - 
							<input type="text" name="endPrice" value="${conditionBook.endPrice}" size="6"/>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td><input type="submit" value="查询"/></td>
					</tr>
				</table>
			</form>
		</fieldset>
	
		<table width="100%">
			<c:forEach items="${page.data}" var="book">
				<tr>
					<td>
						<table border="1" width="100%">
							<tr>
								<td rowspan="5" width="150px">
									<img width="150px" height="200px" src="${pageContext.request.contextPath}${book.imgUrl}"/>
								</td>
								<td width="80px">标题</td>
								<td>${book.title}</td>
							</tr>
							<tr>
								<td>作者</td>
								<td>${book.author}</td>
							</tr>
							<tr>
								<td>价格</td>
								<td>${book.price}</td>
							</tr>
							<tr>
								<td>数量</td>
								<td>${book.quantity}</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<a href="${pageContext.request.contextPath}/user/BookServlet?method=addBookToCart&bookId=${book.id}">添加到购物车</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%--分页导航条 --%>
		当前${page.pageNum}页/共${page.totalPage}页，总条数${page.totalRecord}条<br/>
		
		<c:if test="${page.pageNum > 1}">
			<a href="javascript:void(0);" onclick="changeUrl(1)">首页</a>
			<a href="javascript:void(0);" onclick="changeUrl(${page.pageNum-1})">上一页</a>
		</c:if>
		
		<c:forEach begin="${page.start}" end="${page.end}" var="num">
			<a href="javascript:void(0);" onclick="changeUrl(${num})">${num}</a>
		</c:forEach>
		
		<c:if test="${page.pageNum < page.totalPage}">
			<a href="javascript:void(0);" onclick="changeUrl(${page.pageNum+1})">下一页</a>
			<a href="javascript:void(0);" onclick="changeUrl(${page.totalPage})">尾页</a>
		</c:if>
		
	</div>
	<%--指定分类书籍数据 end --%>
</body>
</html>