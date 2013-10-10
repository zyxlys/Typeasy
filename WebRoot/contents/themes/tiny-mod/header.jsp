<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<%
	String requesUri = request.getRequestURI();
	String uri = requesUri.substring(requesUri.lastIndexOf("/") + 1);
	request.setAttribute("uri", uri);
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">
<base href="<%=basePath%>">
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<title>${requestScope.title} - <c:forEach var="option"
		items="${options }">
		<c:if test="${option.name == 'title' }">${option.value }</c:if>
	</c:forEach></title>
<link rel="stylesheet" type="text/css" media="all"
	href="${applicationScope.themePath }style.css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.min.js"></script>

<%
	String acceptLanguage = request.getHeader("Accept-Language");
	String currentLang = acceptLanguage.split(",")[0];
	if ("zh-CN".equals(currentLang)) {
%>
<script type="text/javascript" src="<%=basePath%>js/messages_zh.js"></script>
<%
	}
%>
<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
	type="image/vnd.microsoft.icon">
<link rel="icon" href="<%=basePath%>favicon.ico"
	type="image/vnd.microsoft.icon">
</head>


<body>
	<div id="wrap">
		<div id="header">
			<h1>
				<a href="index.jsp"><c:forEach var="option" items="${options }">
						<c:if test="${option.name == 'title' }">${option.value }</c:if>
					</c:forEach></a>
			</h1>
		</div>
		<div id="menu">
			<ul class="pagelinks">
				<li><a href="index.jsp">首页</a></li>
				<c:forEach items="${contents }" var="content">

					<c:if test="${content.type == 'page' }">
						<li><a href="page-${content.cid }.htm"
							title="${content.title }">${content.title }</a></li>

					</c:if>
				</c:forEach>

			</ul>
			<form id="searchform" method="post" action="SearchContents">
				<p>
					Search: <input type="text" name="keywords" size="15" />
				</p>
			</form>
		</div>