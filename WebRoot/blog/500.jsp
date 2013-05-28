<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="500 Error"></c:set>

<jsp:include page="header.jsp"></jsp:include>
<div id="content">
	<div class="post">
		<h1 class="post-title">500 - 服务器内部错误</h1>


		<div class="post-text">该文件的数据在配置数据库中配置不正确，或者该文件存在语法错误。</div>


	</div>


</div>
<!-- end #content-->
<jsp:include page="sidebar.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
