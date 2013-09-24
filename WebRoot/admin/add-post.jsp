<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" scope="request" value="发表文章"></c:set>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<h4 class="alert_info">记录下生活中的点点滴滴。</h4>

	<article class="module width_full">
		<form id="add-post"
			action="AddContents?type=post&authorId=${sessionScope.user.uid }"
			method="post">
			<header>
				<h3>发表文章</h3>
			</header>
			<div class="module_content">
				<fieldset>
					<label>文章标题</label> <input type="text" id="title" name="title"
						value="">
				</fieldset>
				<fieldset>
					<label>文章内容</label>
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
				<fieldset style="width:48%; float:left; margin-right: 3%;">
					<label>分类目录</label> <select style="width:92%;" name="category">
						<c:forEach var="meta" items="${metas }">
							<c:if test="${meta.type == 'category'}">
								<option value="${meta.mid }">${meta.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</fieldset>
				<fieldset style="width:48%; float:left;">
					<label>文章标签</label> <input name="name" id="name" type="text"
						style="width:92%;">
				</fieldset>
				<div class="clear"></div>
			</div>
			<footer>
				<div class="submit_link">
					<input type="submit" value="发表文章" class="alt_btn">
				</div>
			</footer>
		</form>
	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
