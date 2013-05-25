<%@page import="me.imomo.typeasy.vo.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<section id="secondary_bar">
	<div class="user">
		<p>
			<a href="admin/my-profiles.jsp">
				<c:if test="${user.screenName == null }">新用户${user.uid }</c:if><c:if test="${user.screenName != null }">新用户${user.screenName }</c:if>
			</a>
		</p>
		<a class="logout_user" href="servlet/LoginServlet?action=logout" onclick="return confirm('确认要安全退出吗?')">安全退出</a>
	</div>
	<div class="breadcrumbs_container">
		<article class="breadcrumbs">
			<a href="admin/index.jsp">控制面板</a>
			<div class="breadcrumb_divider"></div>
			<a class="current">${requestScope.title}</a>
		</article>
	</div>
</section>
<!-- end of secondary bar -->

<aside id="sidebar" class="column">
	<form class="quick_search">
		<input type="text" value=""
			onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
	</form>
	<hr />
	<h3>文章</h3>
	<ul class="toggle">
		<li class="icn_new_post"><a href="admin/add-post.jsp">发表文章</a></li>
		<li class="icn_manage_posts"><a href="admin/manage-posts.jsp">管理文章</a></li>
		<li class="icn_manage_categories"><a href="admin/manage-categories.jsp">分类目录</a></li>
		<li class="icn_manage_tags"><a href="admin/manage-tags.jsp">文章标签</a></li>
	</ul>
	<h3>页面</h3>
	<ul class="toggle">
		<li class="icn_new_page"><a href="admin/add-page.jsp">发表页面</a></li>
		<li class="icn_manage_pages"><a href="admin/manage-pages.jsp">管理页面</a></li>
	</ul>
	<h3>评论</h3>
	<ul class="toggle">
		<li class="icn_manage_comments"><a href="admin/manage-comments.jsp">管理评论</a></li>
	</ul>
	<h3>用户</h3>
	<ul class="toggle">
		<li class="icn_new_user"><a href="admin/add-user.jsp">添加用户</a></li>
		<li class="icn_manage_users"><a href="admin/manage-users.jsp">管理用户</a></li>
		<li class="icn_my_profile"><a href="admin/my-profiles.jsp">我的资料</a></li>
	</ul>
	<h3>选项</h3>
	<ul class="toggle">
		<li class="icn_options"><a href="admin/options.jsp">基本设置</a></li>
		<li class="icn_security"><a href="admin/security.jsp">安全设置</a></li>
		<li class="icn_jump_back"><a class="logout_user" href="servlet/LoginServlet?action=logout"
			onclick="return confirm('确认要安全退出吗?')">安全退出</a></li>
	</ul>

	<footer>
		<hr />
		<p>
			<strong>Copyright &copy; 2013 <a
				href="http://github.com/Acris/Typeasy" target="_blank">Typeasy</a></strong>
		</p>
		<p>
			Powered by <a href="http://imomo.me" target="_blank">Acris</a> |
			version 1.0
		</p>
	</footer>
</aside>
<!-- end of sidebar -->