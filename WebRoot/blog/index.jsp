<%@page import="me.llss.utils.SubStringHTML"%>
<%@page import="me.llss.utils.SwitchDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>

<c:if test="${contents == null }">
	<jsp:forward page="goIndex.jsp?cid=${cid }&type=post"></jsp:forward>
</c:if>

<c:set var="title" scope="request" value="首页"></c:set>


<jsp:include page="header.jsp"></jsp:include>
<c:forEach var="option" items="${options }">
	<c:if test="${option.name == 'number' }">
		<c:set scope="request" value="${option.value }" var="maxPageItems"></c:set>
	</c:if>
</c:forEach>
<pg:pager items="${countList }" export="currentPageNumber=pageNumber"
	maxPageItems="${maxPageItems }">
	<div id="m4out" class="container_16 clearfix">

		<div class="grid_10" id="content">
			<%
				int count = 0;
			%>
			<c:forEach var="content" items="${contents }">

				<c:if test="${content.type == 'post' }">
					<%
						count++;
					%>
				</c:if>

			</c:forEach>
			<%
				request.setAttribute("countList", count);
			%>
			<c:forEach items="${contents }" var="content">
				<c:if test="${content.type == 'post' }">
					<c:set scope="request" value="${content.created }"
						var="createdTime"></c:set>
					<c:set scope="request" value="${content.text }" var="contentText"></c:set>
					<c:set scope="request" value="${content.cid }" var="contentCid"></c:set>
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
								<a href="post-${content.cid }.htm" title="${content.title }">${content.title
									}</a>
							</h2>
							<p class="entry_data">
								<span>作者：<c:forEach items="${sessionScope.users }"
										var="user">
										<c:if test="${content.authorId == user.uid }">${user.screenName }</c:if>
									</c:forEach></span> <span>发布时间：${requestScope.formatTime }</span> <span>分类目录：<c:forEach
										items="${relationships }" var="relationship">
										<c:if test="${relationship.cid == content.cid }">
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

							<c:forEach var="option" items="${options }">
								<c:if test="${option.name == 'excerpt' }">
									<c:set scope="request" value="${option.value }" var="isExcerpt"></c:set>
								</c:if>
								<c:if test="${option.name == 'count' }">
									<c:set scope="request" value="${option.value }"
										var="contentCount"></c:set>
								</c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${requestScope.isExcerpt == 'excerpt' }"><%=SubStringHTML.subStringHTML(
											((String) request
													.getAttribute("contentText")),
											(Integer.valueOf((String) request
													.getAttribute("contentCount"))),
											"<p style=\"text-align: right\">【<a href=\"post-"
													+ request
															.getAttribute("contentCid")
													+ ".htm\" title=\"阅读更多\">阅读更多</a>】</p>")%>
								</c:when>
								<c:otherwise>${content.text }</c:otherwise>
							</c:choose>
						</div>
						<div class="m4gomore">
							<a href="post-${content.cid }.htm#comments"
								title="${content.commentsNum }条评论" class="m4pc">${content.commentsNum
								}</a> <a href="post-${content.cid }.htm" title="${content.title }"
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
