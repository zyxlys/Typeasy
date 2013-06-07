<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="404 Error"></c:set>

<jsp:include page="header.jsp"></jsp:include>

<div id="m4out" class="container_16 clearfix">
	<div class="grid_10" id="content">
		<div class="post">
			<h2 class="entry_title">404 - 页面不存在</h2>
			<p>
			您要访问的页面不存在，请检查您输入的网址，然后重新再试。
			</p>
		</div>

	</div>
	<!-- end #content-->
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div class="clearfix m4pof"></div>
</div>
<!-- end m4out -->
<jsp:include page="footer.jsp"></jsp:include>
