<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!doctype html>
<html>

<head>
<base href="${basePath }">
<meta charset="utf-8" />

<title>${title} - <c:forEach var="option" items="${options }">
		<c:if test="${option.name == 'title' }">${option.value }</c:if>
	</c:forEach></title>
<link rel="stylesheet" href="css/admin-layout.css" type="text/css"
	media="screen" />
<!--[if lt IE 9]>
	<link rel="stylesheet" href="css/admin-ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="kindeditor/plugins/code/prettify.js"></script>
<script src="kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="kindeditor/lang/zh_CN.js" type="text/javascript"></script>

<script src="js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<%
	String acceptLanguage = request.getHeader("Accept-Language");
	String currentLang = acceptLanguage.split(",")[0];
	if ("zh-CN".equals(currentLang)) {
%>
<script type="text/javascript" src="js/messages_zh.js"></script>
<%
	}
%>
<script src="js/hideshow.js" type="text/javascript"></script>
<script src="js/jquery.tablesorter.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.equalHeight.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".tablesorter").tablesorter();
	});
	$(document).ready(function() {

		//When page loads...
		$(".tab_content").hide(); //Hide all content
		$("ul.tabs li:first").addClass("active").show(); //Activate first tab
		$(".tab_content:first").show(); //Show first tab content

		//On Click Event
		$("ul.tabs li").click(function() {

			$("ul.tabs li").removeClass("active"); //Remove any "active" class
			$(this).addClass("active"); //Add "active" class to selected tab
			$(".tab_content").hide(); //Hide all tab content

			var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
			$(activeTab).fadeIn(); //Fade in the active ID content
			return false;
		});

	});
</script>
<script type="text/javascript">
	$(function() {
		$('.column').equalHeight();
	});
</script>


<link rel="shortcut icon" href="${basePath }favicon.ico"
	type="image/vnd.microsoft.icon">
<link rel="icon" href="${basePath }favicon.ico"
	type="image/vnd.microsoft.icon">
</head>


<body>

	<header id="header">
		<hgroup>
			<h1 class="site_title">
				<a href="admin/index.jsp">控制面板</a>
			</h1>
			<h2 class="section_title">${title}</h2>
			<div class="btn_view_site">
				<a href="index.jsp" target="_blank">查看站点</a>
			</div>
		</hgroup>
	</header>