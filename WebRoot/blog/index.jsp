<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="首页"></c:set>

<jsp:include page="header.jsp"></jsp:include>
<div id="content">

	<c:if test="${contents != null }">
	
	<div class="post">
	<c:forEach items="${contents }" var="content">
	<c:if test="${content.type == 'post' }">
	
		<h1 class="post-title">
			<a href="${content.cid }.post" rel="bookmark"
				title="${content.title }">
				${content.title }
			</a>
		</h1>
		${content.text }

		<div class="post-info">
			${content.created } in
				分类目录
			| tags:
			文章标签
			| <strong>
				<a href="${content.cid }.post#comment-${content.cid }">${content.commentsNum }条评论</a>
			</strong>
		</div>
		</c:if>
		</c:forEach>
	</div>
	</c:if>
	<ul id="pages">
		<li>页数:</li>
		显示分页
	</ul>
</div>
<!-- end #content-->
<jsp:include page="sidebar.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
