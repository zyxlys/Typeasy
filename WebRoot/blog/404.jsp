<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="404 Error"></c:set>

<jsp:include page="header.jsp"></jsp:include>
<div id="content">
	<div class="post">
		<h1 class="post-title">404 - 页面不存在</h1>


		<div class="post-text">您要访问的页面不存在，请检查您输入的网址，然后重新再试。</div>


	</div>


</div>
<!-- end #content-->
<jsp:include page="sidebar.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
