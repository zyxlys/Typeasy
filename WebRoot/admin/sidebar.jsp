<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



<script>
	function logoutConfirm() {
		question = confirm("确认要安全退出吗?")
		if (question != "0") {
			window.location.href="../servlet/LoginServlet?action=logout"
		}
	}
</script>

<section id="secondary_bar">
	<div class="user">
		<p>
			欢迎您：<font color="black">${sessionScope.user.screenName}</font>
		</p>
		<a class="logout_user" href="#" onclick="logoutConfirm();">安全退出</a>
	</div>
	<div class="breadcrumbs_container">
		<article class="breadcrumbs">
			<a href="index.jsp">控制面板</a>
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
		<li class="icn_new_article"><a href="new-article.jsp">发表文章</a></li>
		<li class="icn_edit_article"><a href="edit-article.jsp">编辑文章</a></li>
		<li class="icn_categories"><a href="#">分类目录</a></li>
		<li class="icn_tags"><a href="#">文章标签</a></li>
	</ul>
	<h3>评论</h3>
	<ul class="toggle">
		<li class="icn_edit_comments"><a href="#">管理评论</a></li>
	</ul>
	<h3>用户</h3>
	<ul class="toggle">
		<li class="icn_add_user"><a href="#">添加用户</a></li>
		<li class="icn_view_users"><a href="#">管理用户</a></li>
		<li class="icn_profile"><a href="my-profiles.jsp">我的资料</a></li>
	</ul>
	<h3>媒体</h3>
	<ul class="toggle">
		<li class="icn_folder"><a href="#">文件管理</a></li>
		<li class="icn_photo"><a href="#">相册管理</a></li>
		<li class="icn_audio"><a href="#">音乐管理</a></li>
		<li class="icn_video"><a href="#">视频管理</a></li>
	</ul>
	<h3>选项</h3>
	<ul class="toggle">
		<li class="icn_settings"><a href="#">基本设置</a></li>
		<li class="icn_security"><a href="#">安全设置</a></li>
		<li class="icn_jump_back"><a class="logout_user" href="#" onclick="logoutConfirm();">安全退出</a></li>
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