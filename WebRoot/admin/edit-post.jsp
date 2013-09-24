<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" scope="request" value="编辑文章"></c:set>

<c:forEach items="${relationships }" var="relationship">
	<c:if test="${relationship.cid == content.cid }">
		<c:set scope="page" value="${relationship }" var="re"></c:set>
	</c:if>
</c:forEach>
<c:forEach items="${metas }" var="meta">
	<c:if test="${meta.type == 'category' }">

		<c:if test="${meta.mid == re.mid }">
			<c:set scope="page" value="${meta.mid }" var="cMid"></c:set>
		</c:if>
	</c:if>
</c:forEach>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<h4 class="alert_info">在此处修改文章的内容.</h4>

	<article class="module width_full">
		<form id="edit-post"
			action="EditContents?type=post&cid=${content.cid }&oldCMid=${cMid }&authorId=${content.authorId }"
			method="post">
			<header>
				<h3>修改文章</h3>
			</header>
			<div class="module_content">
				<fieldset>
					<label>文章标题</label> <input type="text" id="title" name="title"
						value="${content.title }">
				</fieldset>
				<fieldset>
					<label>文章内容</label>
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
				<fieldset style="width:48%; float:left; margin-right: 3%;">
					<label>分类目录</label> <select style="width:92%;" name="category">
						<c:forEach items="${relationships }" var="relationship">
							<c:if test="${relationship.cid == content.cid }">
								<c:set scope="request" value="${relationship }" var="re"></c:set>
							</c:if>
						</c:forEach>
						<c:forEach items="${metas }" var="meta">
							<c:if test="${meta.type == 'category' }">
								<option value="${meta.mid }"
									<c:if test="${meta.mid == re.mid }">selected="selected"</c:if>>${meta.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</fieldset>
				<fieldset style="width:48%; float:left;">
					<label>文章标签</label> <i>当前标签:</i>
					<c:forEach items="${relationships }" var="relationship">
						<c:if test="${relationship.cid == content.cid }">
							<c:forEach items="${metas }" var="meta">
								<c:if test="${meta.mid == relationship.mid }">
									<c:if test="${meta.type == 'tag' }">${meta.name },</c:if>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
					<input name="name" id="name" type="text" value=""
						style="width:92%;">
				</fieldset>
				<div class="clear"></div>
			</div>
			<footer>
				<div class="submit_link">
					<input type="submit" value="确定修改" class="alt_btn">
				</div>
			</footer>
		</form>
	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
