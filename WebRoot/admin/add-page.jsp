<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" scope="request" value="发布页面"></c:set>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<h4 class="alert_info">页面比文字更适合展示独立的信息。</h4>
	<article class="module width_full">
		<form id="edit-page"
			action="servlet/ContentsServlet?action=add&type=page" method="post">
			<header>
				<h3>发表页面</h3>
			</header>
			<div class="module_content">
				<fieldset>
					<label>页面标题</label> <input type="text" is="title" name="title">
				</fieldset>
				<fieldset>
					<label>页面内容</label>
					<textarea name="text" id="text"></textarea>
					<script>
						KindEditor
								.ready(function(K) {
									var editor = K
											.create(
													'textarea[name="text"]',
													{
														width : '99.9%',
														height : '450px',
														cssPath : 'kindeditor/plugins/code/prettify.css',
														uploadJson : 'kindeditor/jsp/upload_json.jsp',
														fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
														allowFileManager : true,
														resizeType : '0',
													});
								});
					</script>
				</fieldset>
			</div>
			<footer>
				<div class="submit_link">
					<input type="submit" value="发表页面" class="alt_btn">
				</div>
			</footer>
		</form>
	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
