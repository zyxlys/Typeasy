<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${contents }" var="content">
	<c:if test="${content.cid == param.cid }">
		<c:set scope="request" value="${content }" var="c"></c:set>
	</c:if>
</c:forEach>
<c:set var="title" scope="request" value="${c.title }"></c:set>
<jsp:include page="header.jsp"></jsp:include>

	
    <div id="content">
    	<div class="post post-list">
        	<h1 class="post-title">${c.title }</h1>
            
            ${c.text }
            
            <div class="post-info">
				${c.created } in
				分类目录
				| 标签: 数据库读取
			</div>

			
		</div>

		

		<jsp:include page="comments.jsp"></jsp:include>

    </div><!-- end #content-->
<jsp:include page="sidebar.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
