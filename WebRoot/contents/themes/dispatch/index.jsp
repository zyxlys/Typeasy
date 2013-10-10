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


	<div class="clearfix" id="main">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="filter-posts" id="content">
			<!-- grab the posts -->
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

						<div class="post-${content.cid } post clearfix project"
							data-id="post-${content.cid }">
							<div class="box">
								<div class="shadow clearfix">
									<div class="frame">
										<h2 class="entry-title">
											<a href="post-${content.cid }.htm" title="${content.title }">${content.title
												}</a>
										</h2>
										<c:forEach var="option" items="${options }">
											<c:if test="${option.name == 'excerpt' }">
												<c:set scope="request" value="${option.value }"
													var="isExcerpt"></c:set>
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
											"<p class=\"more\"><a href=\"post-"
													+ request
															.getAttribute("contentCid")
													+ ".htm\" title=\"阅读全文\">阅读全文</a></p>")%>
											</c:when>
											<c:otherwise>${content.text }</c:otherwise>
										</c:choose>
										<div class="main_vist">

											<ul>
												
												<li style="float: right;"><a title="快速评论"
													href="post-${content.cid }.htm#comments">快速评论</a></li>
											</ul>
										</div>
									</div>
									<!-- frame -->
								</div>
								<!-- shadow -->

								<!-- meta info bar -->
								<div class="bar">
									<div class="bar-frame clearfix">
										<div class="date">
											<strong class="day"><%=SwitchDateFormat.SplitDate(
									(String) (request
											.getAttribute("createdTime")), "dd")%></strong>
											<div class="holder">
												<span class="month"><%=SwitchDateFormat.SplitDate(
									(String) (request
											.getAttribute("createdTime")), "MM")%></span> <span class="year"><%=SwitchDateFormat.SplitDate(
									(String) (request
											.getAttribute("createdTime")),
									"yyyy")%></span>
											</div>
										</div>
										<div class="author">
											<strong class="title">作者</strong>
											<c:forEach items="${sessionScope.users }" var="user">
												<c:if test="${content.authorId == user.uid }">${user.screenName }</c:if>
											</c:forEach>
										</div>
										<div class="categories">
											<strong class="title">分类</strong>
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
										</div>
										<div class="comments">
											<strong class="title">评论</strong> <a
												href="post-${content.cid }.htm#comments"
												title="${content.commentsNum }条评论">${content.commentsNum
												}条评论</a>
										</div>


									</div>
									<!-- bar frame -->
								</div>
								<!-- bar -->
							</div>
							<!-- box -->
						</div>
					</pg:item>
				</c:if>
			</c:forEach>
			<div class="push"></div>
			<div class="post-nav">
				<ol class="page-navigator">


					<pg:prev>
						<li><a href="${pageUrl}">上页</a></li>
					</pg:prev>
					<pg:pages>
						<c:choose>
							<c:when test="${pageNumber eq currentPageNumber }">
								<li><b class="current">${pageNumber }</b></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageUrl }">${pageNumber}</a></li>
							</c:otherwise>
						</c:choose>
					</pg:pages>
					<pg:next>
						<li><a href="${pageUrl}">下页</a></li>
					</pg:next>


				</ol>
			</div>
		</div>
		<!-- end #content-->
</pg:pager>
<jsp:include page="footer.jsp"></jsp:include>
