<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="404 Error"></c:set>

<jsp:include page="header.jsp"></jsp:include>

<div id="content">
<div id="carousel-inner"></div>
<div id="inner">
	<div class="postEntry single">
		<h2 class="entry-title">404 - 页面不存在</h2>
	</div>
</div>
</div><!-- end #content-->

<jsp:include page="footer.jsp"></jsp:include>