<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("post_content") != null
			? request.getParameter("post_content")
			: "";
%>
<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>发表文章 - Typeasy</title>
<link rel="stylesheet" href="admin/css/layout.css" type="text/css"
	media="screen" />
<!--[if lt IE 9]>
	<link rel="stylesheet" href="admin/css/ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<script src="kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script src="admin/scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="admin/scripts/hideshow.js" type="text/javascript"></script>
<script src="admin/scripts/jquery.tablesorter.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="admin/scripts/jquery.equalHeight.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".tablesorter").tablesorter();
	});
	$(document).ready(function() {

		//When page loads...
		$(".tab_content").hide(); //Hide all content
		$("ul.tabs li:first").addClass("active").show(); //Activate first tab
		$(".tab_content:first").show(); //Show first tab content

		//On Click Event
		$("ul.tabs li").click(function() {

			$("ul.tabs li").removeClass("active"); //Remove any "active" class
			$(this).addClass("active"); //Add "active" class to selected tab
			$(".tab_content").hide(); //Hide all tab content

			var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
			$(activeTab).fadeIn(); //Fade in the active ID content
			return false;
		});

	});
</script>
<script type="text/javascript">
	$(function() {
		$('.column').equalHeight();
	});
</script>
</head>

<body>
	<header id="header">
		<hgroup>
			<h1 class="site_title">
				<a href="admin/index.jsp">管理中心</a>
			</h1>
			<h2 class="section_title">Typeasy Control Panel</h2>
			<div class="btn_view_site">
				<a href="index.jsp">查看站点</a>
			</div>
		</hgroup>
	</header>
	<!-- end of header bar -->

	<section id="secondary_bar">
		<div class="user">
			<p>
				Acris (<a href="#">3 条消息</a>)
			</p>
			<a class="logout_user" href="#" title="Logout">退出登录</a>
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs">
				<a href="admin/index.jsp">管理中心</a>
				<div class="breadcrumb_divider"></div>
				<a class="current">发表文章</a>
			</article>
		</div>
	</section>
	<!-- end of secondary bar -->

	<aside id="sidebar" class="column">
		<form class="quick_search">
			<input type="text" value="">
		</form>
		<hr />
		<h3>文章管理</h3>
		<ul class="toggle">
			<li><a href="admin/write-post.jsp" class="current_menu">发表文章</a></li>
			<li><a href="#">所有文章</a></li>
			<li><a href="#">分类目录</a></li>
			<li><a href="#">文章标签</a></li>
		</ul>
		<h3>用户管理</h3>
		<ul class="toggle">
			<li><a href="#">添加用户</a></li>
			<li><a href="#">所有用户</a></li>
			<li><a href="#">个人资料</a></li>
		</ul>
		<h3>评论管理</h3>
		<ul class="toggle">
			<li><a href="#">File Manager</a></li>
			<li><a href="#">Gallery</a></li>
			<li><a href="#">Audio</a></li>
			<li><a href="#">Video</a></li>
		</ul>
		<h3>站点设置</h3>
		<ul class="toggle">
			<li><a href="#">基本信息</a></li>
			<li><a href="#">Security</a></li>
			<li><a href="#">安全退出</a></li>
		</ul>
		<footer>
			<hr />
			<p>
				<strong>Copyright &copy; 2013 Typeasy</strong>
			</p>
			<p>
				Powered by <a href="http://imomo.me" target="_blank">Acris Liu</a>
			</p>
		</footer>
	</aside>
	<!-- end of sidebar -->

	<section id="main" class="column">
		<h4 class="alert_info">在这里，你可以发布一篇新的文章。</h4>
		<article class="module width_full">
			<header>
				<h3>发表文章</h3>
			</header>
			<div class="module_content">
				<fieldset>
					<label>文章标题</label> <input type="text">
				</fieldset>
				<fieldset>
					<label>文章内容</label>
					<textarea id="post_editor" name="post_content"></textarea>
					<script>
						KindEditor
								.ready(function(K) {
									var editor = K
											.create(
													'textarea[name="post_content"]',
													{
														width : '99.9%',
														height : '350px',
														cssPath : 'kindeditor/plugins/code/prettify.css',
														uploadJson : 'kindeditor/jsp/upload_json.jsp',
														fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
														allowFileManager : true,
														afterCreate : function() {
															var self = this;
															K
																	.ctrl(
																			document,
																			13,
																			function() {
																				self
																						.sync();
																				document.forms['example']
																						.submit();
																			});
															K
																	.ctrl(
																			self.edit.doc,
																			13,
																			function() {
																				self
																						.sync();
																				document.forms['example']
																						.submit();
																			});
														}
													});
									prettyPrint();
								});
					</script>
				</fieldset>
				<fieldset style="width:48%; float:left; margin-right: 3%;">
					<!-- to make two field float next to one another, adjust values accordingly -->
					<label>分类目录</label> <select style="width:92%;">
						<option>Articles</option>
						<option>Tutorials</option>
						<option>Freebies</option>
					</select>
				</fieldset>
				<fieldset style="width:48%; float:left;">
					<!-- to make two field float next to one another, adjust values accordingly -->
					<label>文章标签</label> <input type="text" style="width:92%;">
				</fieldset>
				<div class="clear"></div>
			</div>
			<footer>
				<div class="submit_link">
					<select>
						<option>Draft</option>
						<option>Published</option>
					</select> <input type="submit" value="发布" class="alt_btn">
				</div>
			</footer>
		</article>
		<!-- end of post new article -->

		<div class="spacer"></div>
	</section>
</body>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>