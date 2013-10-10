<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String cid = request.getParameter("cid");
	request.setAttribute("cid", cid);
%>
<c:if test="${contents == null }">
	<jsp:forward page="goIndex.jsp?cid=${param.cid }&type=page"></jsp:forward>
</c:if>
<c:forEach items="${contents }" var="content">
	<c:if test="${content.cid == param.cid }">
		<c:set scope="request" value="${content }" var="c"></c:set>
	</c:if>
</c:forEach>
<c:set var="title" scope="request" value="${c.title }"></c:set>
<jsp:include page="header.jsp"></jsp:include>

<div id="content">
	<div class="entry">${c.text }</div>
	<c:choose>
		<c:when test="${sessionScope.user.group == 'admin' }">
			<p style="text-align: right">
				操作：<a
					href="DelContents?cid=${c.cid}&type=page&authorId=${c.authorId }&fromPage=postPage"
					title="删除" onclick="return confirm('确认要删除吗？');">删除</a> | <a
					href="FindContents?cid=${c.cid}&type=page&authorId=${c.authorId }"
					title="修改">修改 </a>
			</p>
		</c:when>
		<c:otherwise>
			<c:if test="${c.authorId == sessionScope.user.uid }">
				<p style="text-align: right">
					操作：<a
						href="DelContents?cid=${c.cid}&type=post&authorId=${c.authorId }&fromPage=postPage"
						title="删除" onclick="return confirm('确认要删除吗？');">删除</a> | <a
						href="FindContents?cid=${c.cid}&type=post&authorId=${c.authorId }"
						title="修改">修改 </a>
				</p>
			</c:if>
		</c:otherwise>
	</c:choose>
</div>
<!-- end of #content -->
<jsp:include page="footer.jsp"></jsp:include>
