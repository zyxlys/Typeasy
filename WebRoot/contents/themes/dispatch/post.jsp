<%@page import="me.llss.utils.SwitchDateFormat"%>
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

<c:set scope="request" value="${c.created }" var="createdTime"></c:set>
<div class="clearfix" id="main">
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div class="filter-posts" id="content">
		<!-- grab the posts -->
		<div class="post-${c.cid } post clearfix project"
			data-id="post-${c.cid }">
			<div class="box">
				<div class="shadow clearfix">
					<div class="frame">
						<h2 class="entry-title">
							<a href="post-${c.cid }.htm">${c.title }</a>
						</h2>
						${c.text }
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
						</p>

					</div>
					<!-- frame -->
				</div>
				<!-- shadow -->

				<!-- meta info bar -->
				<div class="bar">
					<div class="bar-frame clearfix">
						<div class="date">
							<strong class="day"><%=SwitchDateFormat.SplitDate(
					(String) (request.getAttribute("createdTime")), "dd")%></strong>
							<div class="holder">
								<span class="month"><%=SwitchDateFormat.SplitDate(
					(String) (request.getAttribute("createdTime")), "MM")%></span> <span class="year"><%=SwitchDateFormat.SplitDate(
					(String) (request.getAttribute("createdTime")), "yyyy")%></span>
							</div>
						</div>
						<div class="author">
							<strong class="title">作者</strong>
							<c:forEach items="${sessionScope.users }" var="user">
								<c:if test="${c.authorId == user.uid }">${user.screenName }</c:if>
							</c:forEach>
						</div>
						<div class="categories">
							<strong class="title">分类</strong>
							<c:forEach items="${relationships }" var="relationship">
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
							</c:forEach>
						</div>
						<div class="comments">
							<strong class="title">评论</strong> <a
								href="post-${c.cid }.htm#comments" title="${c.commentsNum }条评论">${c.commentsNum
								}条评论</a>
						</div>


					</div>
					<!-- bar frame -->
				</div>
				<!-- bar -->
			</div>
			<!-- box -->
			<div style="clear: both;"></div>
			<jsp:include page="comments.jsp"></jsp:include>
		</div>

		<div class="push"></div>
		<!-- end #content-->

	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>

