<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="500 Error"></c:set>

<jsp:include page="header.jsp"></jsp:include>
<div class="err">
	<h2 class="entry_title">500 - 服务器内部错误</h2>
	<p>该文件的数据在配置数据库中配置不正确，或者该文件存在语法错误。</p>
</div>
<div class="push"></div>
<!-- end #content-->
<jsp:include page="footer.jsp"></jsp:include>