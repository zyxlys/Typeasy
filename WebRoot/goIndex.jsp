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

<title>Please wait...</title>


</head>

<body>
	<script type="text/javascript">
		window.location.href = "Index?error=${param.error }&type=${param.type }&cid=${param.cid }";
	</script>
</body>
</html>
