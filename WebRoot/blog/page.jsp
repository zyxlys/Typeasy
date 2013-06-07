<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String cid = request.getParameter("cid");
	request.setAttribute("cid", cid);
%>
<c:if test="${contents == null }">
	<jsp:forward page="/servlet/IndexServlet?cid=${param.cid }&type=page"></jsp:forward>
</c:if>
<c:forEach items="${contents }" var="content">
	<c:if test="${content.cid == param.cid }">
		<c:set scope="request" value="${content }" var="c"></c:set>
	</c:if>
</c:forEach>
<c:set var="title" scope="request" value="${c.title }"></c:set>
<jsp:include page="header.jsp"></jsp:include>

<div id="m4out" class="container_16 clearfix">
	<div class="grid_10" id="content">
		<div class="post">
			<h2 class="entry_title">
				<a href="page-${c.cid }.htm">${c.title }</a>
			</h2>
			<p class="entry_data">
				<span>作者：<c:forEach items="${sessionScope.users }" var="user">
						<c:if test="${c.authorId == user.uid }">${user.screenName }</c:if>
					</c:forEach></span> <span>发布时间：${c.created }</span>
			</p>
			${c.text }
			<c:choose>
				<c:when test="${sessionScope.user.group == 'admin' }">
					<p style="text-align: right">
						操作：<a
							href="servlet/ContentsServlet?action=del&cid=${c.cid}&type=page&authorId=${c.authorId }&fromPage=postPage"
							title="删除" onclick="return confirm('确认要删除吗？');">删除</a> | <a
							href="servlet/ContentsServlet?action=find&cid=${c.cid}&type=page&authorId=${c.authorId }"
							title="修改">修改 </a>
					</p>
				</c:when>
				<c:otherwise>
					<c:if test="${c.authorId == sessionScope.user.uid }">
						<p style="text-align: right">
							操作：<a
								href="servlet/ContentsServlet?action=del&cid=${c.cid}&type=post&authorId=${c.authorId }&fromPage=postPage"
								title="删除" onclick="return confirm('确认要删除吗？');">删除</a> | <a
								href="servlet/ContentsServlet?action=find&cid=${c.cid}&type=post&authorId=${c.authorId }"
								title="修改">修改 </a>
						</p>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- end #content-->
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div class="clearfix m4pof"></div>
</div>
<!-- end m4out -->
<jsp:include page="footer.jsp"></jsp:include>
