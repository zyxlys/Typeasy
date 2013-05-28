<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="文章归档"></c:set>

<jsp:include page="header.jsp"></jsp:include>

<div id="content">
	<c:if test="${contents != null }">
		<div class="post">
			<c:forEach items="${contents }" var="content">
				<c:if test="${content.type == 'post' }">

					<h1 class="post-title">
						<a href="${content。cid }.post" rel="bookmark"
							title="${content。title }">${content。title }</a>
					</h1>
            	${content。text }
            
            	<div class="post-info">
						${content。created } in 分类目录 | tags: 标签 | <strong>${content.commentsNum
							}条评论</strong>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</c:if>
	<ul id="pages">
		<li>页数:</li> 分页
	</ul>
</div>
<!-- end #content-->
<jsp:include page="sidebar.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
