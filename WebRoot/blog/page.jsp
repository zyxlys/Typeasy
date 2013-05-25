<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${contents }" var="content">
	<c:if test="${content.cid == cid }">
		<c:set scope="request" value="${content }" var="c"></c:set>
	</c:if>
</c:forEach>
<c:set var="title" scope="request" value="${c.title }"></c:set>

<jsp:include page="header.jsp"></jsp:include>

<div id="content">
<div id="carousel-inner"></div>
<div id="inner">
	<div class="postEntry single">
		<h2 class="entry-title">${c.title }</h2>
		<div class="postContent">
			<div class="entry-content">${c.text }</div>
		</div>
	</div>

	<jsp:include page="comments.jsp"></jsp:include>
</div>
</div><!-- end #content-->

<jsp:include page="footer.jsp"></jsp:include>