<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${requestScope.title} - Typeasy</title>
<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
<link rel="shortcut icon" href="favicon.ico" type="image/vnd.microsoft.icon">
<link rel="icon" href="favicon.ico" type="image/vnd.microsoft.icon">
</head>

<body>
<div id="wrap">
<div id="header-container">
  <div id="header">
    <div id="logo">
      <h1><a href="index.jsp">Typeasy</a></h1>
    </div>
    <div id="description">这里显示博客描述,从数据库读取.</div>
    <div id="nav">
      <ul class="nav fl">
        <li class="current"><a href="index.jsp">首页</a></li>
        <li><a href="#" title="关于">关于</a></li>
      </ul>
      <ul class="rss fr">
        <li class="sub-rss"><a href="#"><img alt="Feed" src="images/ico-rss.png"></a></li>
      </ul>
    </div>
    <div class="fix"></div>
  </div>
  <!-- end #header --> 
</div>
<div id="shadow"></div>
