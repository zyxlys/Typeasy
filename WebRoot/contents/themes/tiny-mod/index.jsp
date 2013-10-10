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

	<div id="content">
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
				<c:set scope="request" value="${content.created }" var="createdTime"></c:set>
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
					<h2>
						<a href="post-${content.cid }.htm">${content.title }</a>
					</h2>
					<p class="postinfo">
						${requestScope.formatTime } |
						<c:forEach items="${relationships }" var="relationship">
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
						</c:forEach>
						| <a href="post-${content.cid }.htm#comments">${content.commentsNum
							}条评论</a>
					</p>
					<div class="entry">
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
													+ ".htm\" title=\"阅读全文\">阅读全文</a>】</p>")%>
							</c:when>
							<c:otherwise>${content.text }</c:otherwise>
						</c:choose>
					</div>

					


				</pg:item>
			</c:if>
		</c:forEach>
		<ol class="pages">
			<div class="pagination">
				
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
				

			</div>
		</ol>
	</div>
	<!-- end of #content -->
</pg:pager>
<jsp:include page="footer.jsp"></jsp:include>
