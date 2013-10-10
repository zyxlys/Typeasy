<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String cid = request.getParameter("cid");
	request.setAttribute("cid", cid);
%>
<c:if test="${contents == null }">
	<jsp:forward page="goIndex.jsp?cid=${cid }&type=post"></jsp:forward>
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
				<a href="post-${c.cid }.htm">${c.title }</a>
			</h2>
			<p class="entry_data">
				<span>作者：<c:forEach items="${sessionScope.users }" var="user">
						<c:if test="${c.authorId == user.uid }">${user.screenName }</c:if>
					</c:forEach></span> <span>发布时间：${c.created }</span> <span>分类目录：<c:forEach
						items="${relationships }" var="relationship">
						<c:if test="${relationship.cid == c.cid }">
							<c:forEach items="${metas }" var="meta">
								<c:if test="${meta.mid == relationship.mid }">
									<c:if test="${meta.type == 'category' }">
										<a href="ListContentsByCategory?mid=${meta.mid }"
											title="${meta.name }">${meta.name }</a>
									</c:if>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach></span>
			</p>
			${c.text } <br />
			<p class="tags">
				标签:
				<c:forEach items="${relationships }" var="relationship">
					<c:if test="${relationship.cid == c.cid }">
						<c:forEach items="${metas }" var="meta">
							<c:if test="${meta.mid == relationship.mid }">
								<c:if test="${meta.type == 'tag' }">
									<a href="ListContentsByTag?mid=${meta.mid }"
										title="${meta.name }">${meta.name }</a>
								</c:if>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
				<c:choose>
					<c:when test="${sessionScope.user.group == 'admin' }">
						<p style="text-align: right">
							操作：<a
								href="DelContents?cid=${c.cid}&type=post&authorId=${c.authorId }&fromPage=postPage"
								title="删除" onclick="return confirm('确认要删除吗？');">删除</a> | <a
								href="FindContents?cid=${c.cid}&type=post&authorId=${c.authorId }"
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
			</p>
		</div>
		<div class="m4gomore"></div>
		<jsp:include page="comments.jsp"></jsp:include>
	</div>
	<!-- end #content-->
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div class="clearfix m4pof"></div>
</div>
<!-- end m4out -->
<jsp:include page="footer.jsp"></jsp:include>
