<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">
<base href="<%=basePath%>">
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>${requestScope.title} - Typeasy</title>


<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />

<!--[if IE]><link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" /><![endif]-->

<link rel="icon" href="favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

</head>

<body>
	<div id="container">
		<div id="header">
			<h1>
				<a href="index.jsp"> Typeasy </a><span> 这里显示博客描述,从数据库读取. </span>
			</h1>
			<div id="search">
				<form method="post" id="searchform" action="">
					<input type="text" value="Search" name="s" id="s"
						onfocus="if(this.value=='Search')this.value=''"
						onblur="if(this.value=='')this.value='Search'" />
				</form>
			</div>
		</div>

		<div id="nav">
			<ul>
				<li class="current_page_item"><a href="index.jsp"> 首页 </a></li>
				<c:forEach items="${contents }" var="content">
					<c:if test="${content.type == 'page' }">
						<li class="page_item page-item-625900"><a
							href="${content.cid }.page">${content.title }</a></li>
					</c:if>
				</c:forEach>
			</ul>
			<a href="#" id="feed">Subscribe</a>
		</div>

		<div id="wrapper">

			<!-- end #header -->