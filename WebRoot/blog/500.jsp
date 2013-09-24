<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="500 Error"></c:set>

<jsp:include page="header.jsp"></jsp:include>

<div id="m4out" class="container_16 clearfix">
	<div class="grid_10" id="content">
		<div class="post">
			<h2 class="entry_title">500 - 服务器内部错误</h2>
			<p>该文件的数据在配置数据库中配置不正确，或者该文件存在语法错误。</p>
			
		</div>

	</div>
	<!-- end #content-->
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div class="clearfix m4pof"></div>
</div>
<!-- end m4out -->
<jsp:include page="footer.jsp"></jsp:include>
