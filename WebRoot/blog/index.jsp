<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="首页"></c:set>


<jsp:include page="header.jsp"></jsp:include>


<div id="content">
	<div id="carousel-inner"></div>
	<div id="inner">
		<!--判断是否有文章 有则通过循环输出-->

		<div class="postEntry">
			<c:forEach var="content" items="${contents }">
				<h2 class="entry-title">
					<a href="${content.cid }.post">${content.title }</a>
				</h2>
				<div class="postMeta">
					By
					<c:forEach items="${users }" var="user">
						<c:if test="${user.uid == c.authorId }">${user.screenName }</c:if>
					</c:forEach>
					• ${content.created } • 随笔 • <a href="#comments">1条评论</a>
				</div>
				<div class="postContent">
					<div class="entry-content">${content.text }</div>
				</div>
			</c:forEach>
		</div>
		<!--结束判断-->

		<div id="pageNav">翻页</div>
	</div>
</div>
<!-- end #content-->

<jsp:include page="footer.jsp"></jsp:include>
