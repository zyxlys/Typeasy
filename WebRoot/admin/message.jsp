<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" scope="request" value="${message }"></c:set>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<section id="main" class="column">
	<div style="height:250px; text-align:center;">
		<div
			style="width:350px;height:150px;background-color: #A8A8A8;margin: 150px auto auto auto;padding:auto;border: 1px solid #000000;font-size: 14px;">
			<div
				style="margin-top: 50px;line-height: 50px;background-color: #ffffff; margin:0 auto;">
				${message } <br /> <br />
			</div>
			<input type="button" value="确定"
				onclick="javascript:location.href='${returnURL}'">
		</div>
	</div>
	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
