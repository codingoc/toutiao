<%@page import="com.sun.mail.imap.protocol.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--静态包含-->
<%@include file="common.jsp"%>
<link href="<%=basePath%>static/css/docs.css" rel="stylesheet">
<title>股票</title>
</head>
<body>
	<!-- 导航条 -->
	<jsp:include page="header.jsp" flush="true" />
	<div class="container bs-docs-container">
		<ul class="list-group">
			<c:forEach var="item" items="${hotStocksList}" varStatus="index">
				<li class="list-group-item">
					<h4 class="list-group-item-heading">${item.stockName }</h4>
					<p class="list-group-item-text">${item.stockNo }</p>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- 底部footer -->
	<jsp:include page="footer.jsp" flush="true" />
</body>
</html>