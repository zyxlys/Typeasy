<%@ page import="me.llss.utils.Gravatar"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="clearfix" id="sidebar-wrap">
	<div id="sidebar">

		<!-- grab the logo -->
		<h1>
			<a href="index.jsp"> <img
				alt="<c:forEach var="option" items="${options }">
					<c:if test="${option.name == 'description' }">${option.value }</c:if>
				</c:forEach>"
				src="${applicationScope.themePath }images/logo.jpg" class="logo"></a>
		</h1>

		<!-- otherwise show the site title and description -->

		<!-- grab sidebar widgets -->
		<div class="widget last-sidebar">
			<h2 class="widgettitle">搜索</h2>
			<form class="search-form clearfix" action="SearchContents" method="post">
				<fieldset>
					<input type="text" value="search the site"
						onblur="if (this.value == '') {this.value = 'search the site';}"
						onfocus="if (this.value == 'search the site') {this.value = '';}"
						name="keywords" class="search-form-input text"> <input
						type="submit" class="submit" value="Go">
				</fieldset>
			</form>
		</div>
		<div class="widget last-sidebar">
			<h2 class="widgettitle">最近回复</h2>
			<ul>
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
		</div>
		
		<div class="widget last-sidebar">
			<h2 class="widgettitle">文章分类</h2>
			<ul>
				<c:set scope="request" value="0" var="j"></c:set>
				<c:forEach items="${metas }" var="meta">
					<c:if test="${meta.type == 'category' && j < 5 }">
						<li><a href="ListContentsByCategory?mid=${meta.mid }"
							title="${meta.name }">${meta.name }</a></li>
						<c:set scope="request" value="${j+1 }" var="j"></c:set>
					</c:if>

				</c:forEach>
			</ul>
		</div>
		<div class="widget last-sidebar">
			<h2 class="widgettitle">友情链接</h2>
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
		<div class="widget last-sidebar">
			<h2 class="widgettitle">功能</h2>
			<ul>
				<c:choose>
					<c:when test="${user.uid != null }">
						<li class="last"><a href="admin">后台管理</a></li>
						<li class="last"><a href="Logout"
							onclick="return confirm('确认要退出吗？');">安全退出</a></li>
					</c:when>
					<c:otherwise>
						<li class="last"><a href="login.jsp">用户登录</a></li>
						<li class="last"><a href="login.jsp?action=register">免费注册</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="http://validator.w3.org/check/referer">Valid
						XHTML</a></li>
				<li><a href="http://llss.me/typeasy">Typeasy</a></li>
			</ul>
		</div>



	</div>
	<!--sidebar-->

	<div style="clear: both;"></div>

	<!-- grab the sticky sidebar on the main blog pages, hide it on inside pages -->

	<div class="clearfix" id="stickyStickyWrapper"
		style="height: 444px; width: 200px;">
		<div class="clearfix" id="sticky">
			<h2 class="sticky-title"></h2>

			<div class="widget last-sidebar">
				<h2 class="widgettitle">标签</h2>
				<div class="tagcloud">

					<c:forEach items="${metas }" var="meta">
						<c:if test="${meta.type == 'tag' }">
							<a class="tag-link" href="ListContentsByTag?mid=${meta.mid }"
								title="${meta.name }">${meta.name }</a>

						</c:if>

					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- sticky -->


</div>
