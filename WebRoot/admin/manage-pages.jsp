<%@ page language="java" import="java.util.*,me.imomo.typeasy.vo.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" scope="request" value="管理页面"></c:set>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<section id="main" class="column">
	<h4 class="alert_info">小提示：单击列名可以将页面按照列名项目排序。</h4>
	<script type="text/javascript">
		var selectAll = function() {
			var all = document.getElementsByName("all")[0];
			var ids = document.getElementsByName("id");

			if (all.checked) {
				for ( var i = 0; i < ids.length; i++) {
					ids[i].checked = true;
				}
			} else {
				for ( var i = 0; i < ids.length; i++) {
					ids[i].checked = false;
				}
			}
		};
	</script>

	<fieldset style="width: 95%;margin: 20px 3% 0 3%;">
		<input type="checkbox" name="all" onclick="selectAll();" /> <select
			style="width: 70%;">
			<option>批量操作</option>
			<option>删除选中的页面</option>
		</select> <input type="submit" value="应用"> <input type="submit"
			value="发表新页面" class="alt_btn"
			onclick="window.location.href='admin/add-post.jsp';">
	</fieldset>

	<article class="module width_full">
		<header>
			<h3 class="tabs_involved">页面管理</h3>
		</header>
		<table class="tablesorter" cellspacing="0">
			<thead>
				<tr>
					<th>标题</th>
					<th>作者</th>
					<th>发布时间</th>
					<th>评论数</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="content" items="${contents }">
					<tr>
						<td><input type="checkbox" name="id">${content.title}</td>
						<td>${content.authorId }</td>
						<td>$content.created }</td>
						<td>${content.commentsNum }</td>
						<td><a
							href="servlet/ContentsServlet?action=find&type=page&cid=${content.cid}"><img
								src="images/icn_edit.png" title="编辑" style="border:0px;"></a>
							<a
							href="servlet/ContentsServlet?action=del&type=page&cid=${content.cid}"><img
								src="images/icn_trash.png" alt="删除" style="border:0px;" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</article>


	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
