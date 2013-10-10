<%@ page import="me.llss.utils.Gravatar"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="right">
	<form id="search" method="post" action="SearchContents">
		<input type="text" name="keywords" class="text" size="20"
			onfocus="this.value=''" value="To search type and hit enter" />
	</form>


	<h5>用户服务</h5>
	<ul>
		<c:choose>
			<c:when test="${user.uid != null }">
				<li><a href="admin">后台管理</a></li>
				<li><a href="Logout" onclick="return confirm('确认要退出吗？');">安全退出</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="login.jsp">用户登录</a></li>
				<li><a href="login.jsp?action=register">免费注册</a></li>
			</c:otherwise>
		</c:choose>
	</ul>

	<h5>文章分类</h5>
	<ul class="shit">
		<c:set scope="request" value="0" var="j"></c:set>
		<c:forEach items="${metas }" var="meta">
			<c:if test="${meta.type == 'category' && j < 5 }">
				<li><a href="ListContentsByCategory?mid=${meta.mid }"
					title="${meta.name }">${meta.name }</a></li>
				<c:set scope="request" value="${j+1 }" var="j"></c:set>
			</c:if>

		</c:forEach>
	</ul>

	<h5>最新文章</h5>
	<ul class="shit">
		<c:set scope="request" value="0" var="i"></c:set>
		<c:forEach items="${contents }" var="content">
			<c:if test="${content.type == 'post' && i < 5 }">
				<li><a href="post-${content.cid }.htm"><c:choose>
							<c:when test="${fn:length(content.title) > 15 }"> ${fn:substring(content.title,0,15) }...</c:when>
							<c:otherwise>${content.title }</c:otherwise>
						</c:choose></a></li>
				<c:set scope="request" value="${i+1 }" var="i"></c:set>
			</c:if>
		</c:forEach>
	</ul>



	<h5>最近回复</h5>
	<ul class="shit">
		<c:forEach items="${comments }" begin="0" end="4" var="comment">
			<c:set scope="request" value="${comment.mail }" var="gravatarMail"></c:set>
			<div class="m4sidco">
				<a href="post-${comment.cid }.htm#comments"><c:choose>
						<c:when test="${comment.authorId != 0 }">
							<c:forEach items="${users }" var="u">
								<c:if test="${comment.authorId == u.uid }">
									<img src="${u.avatar }" width="40" height="40" />
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise><%=Gravatar
								.getAvatar(request.getAttribute("gravatarMail")
										.toString(), 40)%></c:otherwise>
					</c:choose></a><span><a href="post-${comment.cid }.htm#comments"><c:choose>
							<c:when test="${fn:length(comment.text) > 10 }"> ${fn:substring(comment.text,0,10) }...</c:when>
							<c:otherwise>${comment.text }</c:otherwise>
						</c:choose></a><br>On ${comment.created }</span>
			</div>
		</c:forEach>
	</ul>


	<h5>友情链接</h5>
	<ul class="shit">
		<li><a href="http://acora.cc" target="_blank">Acora</a> - 你認識的任何人</li>
		<li><a href="http://mufeng.me/" target="_blank">牧风</a> - 呼吸风的气息</li>
		<li><a href="http://www.imobei.com/" target="_blank">漠北之南</a> -
			你好！旧时光.</li>
		<li><a href="http://zuidongting.com/" target="_blank">最动听</a> -
			精品资源分享</li>
		<li><a href="http://www.dearzd.com/" target="_blank">咚门</a> -
			他微笑着关了窗</li>
	</ul>
</div>