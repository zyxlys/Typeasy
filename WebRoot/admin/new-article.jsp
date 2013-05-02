<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setAttribute("title", "发表文章");
%>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<section id="main" class="column">
	<h4 class="alert_info">您可以在这里记录下生活中的点点滴滴。</h4>
	<article class="module width_full">
		<header>
			<h3>发表文章</h3>
		</header>
		<div class="module_content">
			<fieldset>
				<label>文章标题</label> <input type="text" name="post_title">
			</fieldset>
			<fieldset>
				<label>文章内容</label>
				<textarea name="post_content" id="content"></textarea>
				<script>
					KindEditor
							.ready(function(K) {
								var editor = K
										.create(
												'textarea[name="post_content"]',
												{
													width : '99.9%',
													height : '450px',
													cssPath : '../includes/kindeditor/plugins/code/prettify.css',
													uploadJson : '../includes/kindeditor/jsp/upload_json.jsp',
													fileManagerJson : '../includes/kindeditor/jsp/file_manager_json.jsp',
													allowFileManager : true,
													resizeType: '0',
												});
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
				</select> <input type="submit" value="发布" class="alt_btn"> <input
					type="submit" value="清空">
			</div>
		</footer>
	</article>

	<div class="clear"></div>
	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
