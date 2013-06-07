<%@page import="me.imomo.typeasy.commons.SubStringHTML"%>
<%@page import="me.imomo.typeasy.commons.SwitchDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>

<c:choose>
	<c:when test="${param.action == 'search' }">
		<c:set scope="request" value="${sessionScope.sContents }"
			var="archives"></c:set>
		<c:set var="title" scope="request" value="搜索结果"></c:set>
	</c:when>
	<c:when test="${param.action == 'tag' }">
		<c:set scope="request" value="${sessionScope.tmContents }"
			var="archives"></c:set>
		<c:set var="title" scope="request" value="按标签查看"></c:set>
	</c:when>
	<c:when test="${param.action == 'category' }">
		<c:set scope="request" value="${sessionScope.cmContents }"
			var="archives"></c:set>
		<c:set var="title" scope="request" value="按目录查看"></c:set>
	</c:when>
</c:choose>

<c:if test="${archives == null || fn:length(archives) == 0 }">
	<script type="text/javascript">
		alert("未找到内容");
		history.back();
	</script>
</c:if>




<jsp:include page="header.jsp"></jsp:include>
<c:forEach var="option" items="${options }">
	<c:if test="${option.name == 'number' }">
		<c:set scope="request" value="${option.value }" var="maxPageItems"></c:set>
	</c:if>
</c:forEach>
<pg:pager items="${fn:length(archives) }"
	export="currentPageNumber=pageNumber" maxPageItems="${maxPageItems }">
	<div id="m4out" class="container_16 clearfix">

		<div class="grid_10" id="content">
			<c:forEach items="${archives }" var="archive">
				<c:if test="${archive.type == 'post' }">
					<c:set scope="request" value="${archive.created }"
						var="createdTime"></c:set>
					<c:set scope="request" value="${archive.text }" var="archiveText"></c:set>
					<c:set scope="request" value="${archive.cid }" var="archiveCid"></c:set>
					<%
						String createdTime = (String) request
														.getAttribute("createdTime");
												String formatTime = SwitchDateFormat
														.SwitchFormat(createdTime);
												request.setAttribute("formatTime", formatTime);
					%>
					<pg:item>
						<div class="post clearfix">
							<h2 class="entry_title">
								<a href="post-${archive.cid }.htm" title="${archive.title }">${archive.title
									}</a>
							</h2>
							<p class="entry_data">
								<span>作者：<c:forEach items="${sessionScope.users }"
										var="user">
										<c:if test="${archive.authorId == user.uid }">${user.screenName }</c:if>
									</c:forEach></span> <span>发布时间：${requestScope.formatTime }</span> <span>分类目录：<c:forEach
										items="${relationships }" var="relationship">
										<c:if test="${relationship.cid == archive.cid }">
											<c:forEach items="${metas }" var="meta">
												<c:if test="${meta.mid == relationship.mid }">
													<c:if test="${meta.type == 'category' }">${meta.name }</c:if>
												</c:if>
											</c:forEach>
										</c:if>
									</c:forEach></span>
							</p>
							<c:forEach var="option" items="${options }">
								<c:if test="${option.name == 'excerpt' }">
									<c:set scope="request" value="${option.value }" var="isExcerpt"></c:set>
								</c:if>
								<c:if test="${option.name == 'count' }">
									<c:set scope="request" value="${option.value }"
										var="archiveCount"></c:set>
								</c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${requestScope.isExcerpt == 'excerpt' }"><%=SubStringHTML.subStringHTML(
											((String) request
													.getAttribute("archiveText")),
											(Integer.valueOf((String) request
													.getAttribute("archiveCount"))),
											"<p style=\"text-align: right\">【<a href=\"post-"
													+ request
															.getAttribute("archiveCid")
													+ ".htm\" title=\"阅读更多\">阅读更多</a>】</p>")%>
								</c:when>
								<c:otherwise>${content.text }</c:otherwise>
							</c:choose>
						</div>
						<div class="m4gomore">
							<a href="post-${archive.cid }.htm#comments"
								title="${archive.commentsNum }条评论" class="m4pc">${archive.commentsNum
								}</a> <a href="post-${archive.cid }.htm" title="${archive.title }"
								class="m4pr">阅读更多</a>
						</div>
					</pg:item>
				</c:if>
			</c:forEach>
		</div>
		<!-- end #content-->
		<jsp:include page="sidebar.jsp"></jsp:include>

		<div class="clearfix m4ip">
			<ol class="pages clearfix m4ipol">

				<div class="pagination">
					<pg:first>
						<a href="${pageUrl}">首页</a>
					</pg:first>
					<pg:prev>
						<a href="${pageUrl}">上页</a>
					</pg:prev>
					<pg:pages>
						<c:choose>
							<c:when test="${pageNumber eq currentPageNumber }">
								<b class="current">${pageNumber }</b>
							</c:when>
							<c:otherwise>
								<a href="${pageUrl }">${pageNumber}</a>
							</c:otherwise>
						</c:choose>
					</pg:pages>
					<pg:next>
						<a href="${pageUrl}">下页</a>
					</pg:next>
					<pg:last>
						<a href="${pageUrl}">尾页</a>
					</pg:last>

				</div>
			</ol>
		</div>
	</div>
</pg:pager>
<!-- end m4out -->
<jsp:include page="footer.jsp"></jsp:include>
