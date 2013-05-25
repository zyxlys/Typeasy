<%@page import="me.imomo.typeasy.vo.Contents"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${contents }" var="content">
	<c:if test="${content.cid == param.cid }">
		<c:set scope="request" value="${content }" var="c"></c:set>
	</c:if>
</c:forEach>

<c:set var="title" scope="request" value="${c.title }"></c:set>
<jsp:include page="header.jsp"></jsp:include>

<div id="content">
	<div id="content-header">
		<div id="breadcrumbs">
			<a href="index.jsp">Home</a> → 目录 → ${c.title }
		</div>
		<div id="post-entries">
			<div class="nav-prev fl">上一篇</div>
			<div class="nav-next fr">下一篇</div>
		</div>
		<div class="fix"></div>
	</div>
	<div id="inner">
		<div class="postEntry single">
			<h2 class="entry-title">${c.title }</h2>
			<div class="postMeta">
				By
				<c:forEach items="${users }" var="user">
					<c:if test="${user.uid == c.authorId }">${user.screenName }</c:if>
				</c:forEach>
				• ${c.created } • 音乐 • 3条评论
			</div>
			<div class="postContent">
				<div class="entry-content">${c.text }</div>
				<p class="tags">文章标签: 周杰伦</p>
			</div>

		</div>
		<jsp:include page="comments.jsp"></jsp:include>
	</div>
</div>
<!-- end #content-->

<jsp:include page="footer.jsp"></jsp:include>