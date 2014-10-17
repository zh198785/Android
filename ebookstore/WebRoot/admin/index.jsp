<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台</title>
</head>
	<%-- 注意：必须删除<body> --%>
	<frameset rows="120px,*">
		<frame src="${pageContext.request.contextPath}/admin/UIServlet?n=top" noresize="noresize"/>
		<frameset cols="200px,*" >
			<frame src="${pageContext.request.contextPath}/admin/UIServlet?n=left" noresize="noresize"/>
			<frame src="${pageContext.request.contextPath}/admin/UIServlet?n=welcome" name="mainFrame" />
		</frameset>
	</frameset>
	
	
	<%-- 原始版
		<frameset rows="120px,*">
			<frame src="${pageContext.request.contextPath}/WEB-INF/pages/admin/top.jsp" noresize="noresize"/>
			<frameset cols="200px,*" >
				<frame src="${pageContext.request.contextPath}/WEB-INF/pages/user/left.jsp" noresize="noresize"/>
				<frame src="#" />
			</frameset>
		</frameset>
	--%>
</html>