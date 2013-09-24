<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user.group != 'admin' }">
	<script type="text/javascript">
		alert('用户权限不够，非法操作！');
		history.back();
	</script>
</c:if>
<c:set var="title" scope="request" value="编辑页面"></c:set>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<h4 class="alert_info">在此处修改页面的内容。该页面最后修改于：${content.modified }。</h4>
	<article class="module width_full">
		<form id="edit-page"
			action="EditContents?type=page&cid=${content.cid }" method="post">
			<header>
				<h3>修改页面</h3>
			</header>
			<div class="module_content">
				<fieldset>
					<label>页面标题</label> <input type="text" value="${content.title }"
						id="title" name="title">
				</fieldset>
				<fieldset>
					<label>页面内容</label>
					<textarea name="text" id="text">${content.text }</textarea>
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
					<input type="submit" value="修改页面" class="alt_btn">
				</div>
			</footer>
		</form>
	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
