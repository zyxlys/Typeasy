<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="404 Error"></c:set>

<jsp:include page="header.jsp"></jsp:include>
<div class="main">
<div class="err">
	<h4>Error 404 - 找不到网页</h4>
	<p>你要查看的网页可能已被删除、名称已被更改，或者暂时不可用。</p>
	<p>
	请尝试以下操作：<br />

	1. 如果您已经在地址栏中输入该网页的地址，请确认其拼写正确；<br />

	2. 点击进入 <a href="index.jsp">首页</a><br />

	3. 单击<span class="light">后退</span>按钮，尝试其他链接；
	</p>
</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>