<%@page import="me.llss.vo.OptionsVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>Just one moment...</title>


</head>

<body>
	<jsp:forward
		page="/${applicationScope.themePath }post.jsp?cid=${param.cid }"></jsp:forward>
</body>
</html>
