<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="sidebar">
	<div class="block">
		<h3>最新文章</h3>
		<ul>
			<c:set scope="request" value="0" var="i"></c:set>
			<c:forEach items="${contents }" var="content">
				<c:if test="${content.type == 'post' && i < 5 }">
					<li class="cat-item cat-item-6"><a href="${content.cid }.post">${content.title
							}</a></li>
					<c:set scope="request" value="${i+1 }" var="i"></c:set>
				</c:if>
			</c:forEach>

		</ul>
	</div>

	<div class="block">
		<h3>最新评论</h3>
		<ul>
			<c:forEach items="${comments }" begin="0" end="4" var="comment">
				<li>${comment.author }: ${comment.text }  </li>
			</c:forEach>
		</ul>
	</div>

	<div class="block">
		<h3>文章分类</h3>
		<ul>
			<c:set scope="request" value="0" var="j"></c:set>
			<c:forEach items="${metas }" var="meta">
				<c:if test="${meta.type == 'category' && j < 5 }">
					<li>${meta.name }</li>
				</c:if>
				<c:set scope="request" value="${j+1 }" var="j"></c:set>
			</c:forEach>
		</ul>
	</div>

	<div class="block">
		<h3>友情链接</h3>
		<ul>
			<li><a href="http://acora.cc" target="_blank">Acora</a> -
				你認識的任何人</li>
			<li><a href="http://mufeng.me/" target="_blank">牧风</a> - 呼吸风的气息</li>
			<li><a href="http://www.imobei.com/" target="_blank">漠北之南</a> -
				你好！旧时光.</li>
			<li><a href="http://zuidongting.com/" target="_blank">最动听</a> -
				精品资源分享</li>
			<li><a href="http://www.dearzd.com/" target="_blank">咚门</a> -
				他微笑着关了窗</li>
		</ul>
	</div>

	<div class="block">
		<h3>用户服务</h3>
		<ul>
			<c:choose>
				<c:when test="${user.uid != null }">
					<li class="last"><a href="admin">后台管理</a></li>
					<li><a href="servlet/LoginServlet?action=logout" onclick="return confirm('确认要退出吗？');">安全退出</a></li>
				</c:when>
				<c:otherwise>
					<li class="last"><a href="login.jsp">用户登录</a></li>
					<li class="last"><a href="login.jsp?action=register">免费注册</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>
<!-- end #sidebar -->
