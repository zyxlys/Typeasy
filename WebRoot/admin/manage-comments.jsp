<%@page import="me.imomo.typeasy.vo.CommentsVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="title" scope="request" value="管理评论"></c:set>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<h4 class="alert_info">小提示：单击列名可以将评论按照列名项目排序。</h4>
	<script type="text/javascript">
		var selectAll = function() {
			var all = document.getElementsByName("all")[0];
			var ids = document.getElementsByName("coid");

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

	<form action="servlet/CommentsServlet?action=multiDel" method="post">
		<fieldset style="width: 95%;margin: 20px 3% 0 3%;">

			<input type="checkbox" name="all" onclick="selectAll();" /><select
				style="width: 70%;" name="multiOption">
				<option value="default">批量操作</option>
				<option value="multiDel">删除选中的评论</option>
			</select> <input type="submit" value="应用">

		</fieldset>

		<article class="module width_full">


			<header>
				<h3 class="tabs_involved">评论管理</h3>

			</header>

			<table class="tablesorter" cellspacing="0">
				<thead>
					<tr>
						<th>作者</th>
						<th>评论</th>
						<th>发表于</th>
						<th>时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="comment" items="${comments }">


						<tr>
							<td><input type="checkbox" name="coid"
								value="${comment.coid }">${comment.author }</td>
							<td>${comment.text }</td>
							<td><c:forEach var="content" items="${contents }">
									<c:if test="${content.cid == comment.cid }">${content.title }</c:if>
								</c:forEach></td>
							<td>${comment.created }</td>
							<td><a
								href="./servlet/CommentsServlet?action=find&coid=${comment.coid }"><img
									src="images/icn_edit.png" alt="编辑" style="border:0px;"> </a>
								<a
								href="./servlet/CommentsServlet?action=del&coid=${comment.coid }&cid=${comment.cid }"
								onclick="return confirm('确认删除吗？')"><img
									src="images/icn_trash.png" alt="删除" style="border:0px;">
							</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>

		</article>
	</form>
	<!-- end of content manager article -->


	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
