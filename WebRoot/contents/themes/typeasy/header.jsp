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
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery.validate.min.js"></script>

<%
	String acceptLanguage = request.getHeader("Accept-Language");
	String currentLang = acceptLanguage.split(",")[0];
	if ("zh-CN".equals(currentLang)) {
%>
<script type="text/javascript" src="<%=basePath%>js/messages_zh.js"></script>
<%
	}
%>

<link rel="stylesheet" type="text/css" media="all"
	href="${applicationScope.themePath }style.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${bathPath }css/pager.css" />
<link rel="shortcut icon" href="${basePath }favicon.ico"
	type="image/vnd.microsoft.icon">
<link rel="icon" href="${basePath }favicon.ico"
	type="image/vnd.microsoft.icon">




</head>

<body>


	<div id="header" class="container_16 clearfix">
		<form id="search" method="post" action="SearchContents">
			<div>
				<input type="text" name="keywords" class="text" size="20"
					value="输入关键词后回车..." onfocus="this.value=''" />
			</div>
		</form>
		<div id="logo">
			<h1>
				<a href="index.jsp"><c:forEach var="option" items="${options }">
						<c:if test="${option.name == 'title' }">${option.value }</c:if>
					</c:forEach></a>
			</h1>
			<p class="description">
				<c:forEach var="option" items="${options }">
					<c:if test="${option.name == 'description' }">${option.value }</c:if>
				</c:forEach>
			</p>
		</div>
	</div>
	<!-- end #header -->

	<div id="nav_box" class="clearfix">
		<ul class="container_16 clearfix" id="nav_menu">
			<li <c:if test="${uri == 'index.jsp' }"> class="current" </c:if>><a
				href="index.jsp">博客首页</a></li>
			<c:if test="${uri == 'index.jsp' }">
				<li id="menuli">nav</li>
			</c:if>
			<c:forEach items="${contents }" var="content">

				<c:if test="${content.type == 'page' }">
					<li
						<c:if test="${c.cid == content.cid }">
						class="current" </c:if>><a
						href="page-${content.cid }.htm" title="${content.title }">${content.title
							}</a></li>
					<c:if test="${c.cid == content.cid }">
						<li id="menuli">nav</li>
					</c:if>
				</c:if>
			</c:forEach>
			<li id="tqq"><a href="http://t.qq.com/acrisliu" title="收听我的腾讯微博">腾讯微博
					@acrisliu</a></li>
		</ul>
	</div>
	<!--end navbox-->

	<div id="topper" class="container_16 clearfix"></div>
	<!--end topper-->