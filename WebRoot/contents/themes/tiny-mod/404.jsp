<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="404 Error"></c:set>

<jsp:include page="header.jsp"></jsp:include>

<div id="content">
	<h2>404 - 页面不存在</h2>
	<p>您要访问的页面不存在，请检查您输入的网址，然后重新再试。</p>
	<h3>详细信息</h3>
	<h4>
		请求的URL:
		<%=request.getHeader("Referer")%></h4>

	<br>




</div>

<jsp:include page="footer.jsp"></jsp:include>
