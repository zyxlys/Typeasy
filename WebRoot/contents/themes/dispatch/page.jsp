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
<div class="clearfix" id="main">
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div class="filter-posts" id="content">
		<!-- grab the posts -->
		<div class="page-${c.cid } page clearfix project"
			data-id="page-${c.cid }">
			<div class="box">
				<div class="shadow clearfix">
					<div class="frame">
						<h2 class="entry-title">
							<a href="page-${c.cid }.htm">${c.title }</a>
						</h2>

						${c.text }


					</div>
					<!-- frame -->
				</div>
				<!-- shadow -->

				<!-- meta info bar -->

				<!-- bar -->
			</div>
			<!-- box -->
			<div style="clear: both;"></div>

		</div>

		<div class="push"></div>
		<!-- end #content-->

	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>

