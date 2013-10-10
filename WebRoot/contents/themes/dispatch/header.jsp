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

<body class="home blog">
	<div id="wrapper" class="clearfix">
		<div class="top-bar">
			<div class="frame">
				<div class="frame-inside">
					<!-- header navigation menu -->
					<div class="menu-templatemenu-container">
						<ul id="menu-templatemenu" class="nav">
							<li
								<c:if test="${uri == 'index.jsp' }"> class="current menu-item menu-item-type-taxonomy menu-item-object-category" </c:if>><a
								href="index.jsp">首页</a></li>
							<c:forEach items="${contents }" var="content">

								<c:if test="${content.type == 'page' }">
									<li
										<c:if test="${c.cid == content.cid }">
						class="current menu-item menu-item-type-taxonomy menu-item-object-category" </c:if>><a
										href="page-${content.cid }.htm" title="${content.title }">${content.title
											}</a></li>

								</c:if>
							</c:forEach>
					</div>
					<!-- social icons -->

					<div id="social-strip-icons"></div>

					<!-- If it's the main page, category, or search page show the sorting drop menu. -->
					<ul class="nav filter-list filter">
						<li class="sort-posts all-projects"><a href="#wrapper">回到顶部</a>
						</li>
					</ul>
					<!-- filter list -->
				</div>
				<!-- frame inside -->
			</div>
			<!-- frame -->
		</div>
		<!-- top bar -->

		<div style="clear:both;"></div>