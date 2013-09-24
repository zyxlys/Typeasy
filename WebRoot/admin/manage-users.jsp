<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${sessionScope.user.group != 'admin' }">
	<script type="text/javascript">
		alert('用户权限不够，非法操作！');
		history.back();
	</script>
</c:if>
<c:set var="title" scope="request" value="管理用户"></c:set>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<h4 class="alert_info">提示：不能删除当前登录的用户。</h4>
	<script type="text/javascript">
		var selectAll = function() {
			var all = document.getElementsByName("all")[0];
			var ids = document.getElementsByName("uid");

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

	<form action="MultiDelUsers" method="post">
		<fieldset style="width: 95%;margin: 20px 3% 0 3%;">
			<input type="checkbox" name="all" onclick="selectAll();" /> <select
				style="width: 70%;" name="multiOption">
				<option value="default">批量操作</option>
				<option value="multiDel">删除选中的用户</option>
			</select> <input type="submit" value="应用"> <input type="button"
				value="添加用户"
				onclick="window.location.href='${basePath }admin/add-user.jsp';">
		</fieldset>

		<article class="module width_full">


			<header>
				<h3 class="tabs_involved">用户管理</h3>

			</header>

			<table class="tablesorter">
				<thead>
					<tr>
						<th>用户名</th>
						<th>昵称</th>
						<th>邮箱</th>
						<th>注册时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${users == null || fn:length(users)== 0 }">
							<tr>
								<td>用户列表为空</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="user" items="${users }">
								<tr>
									<td><input type="checkbox" name="userIdArray"
										value="${user.uid }"
										<c:if test="${sessionScope.user.uid == user.uid }">disabled</c:if>>${user.name
										}</td>
									<td>${user.screenName }</td>
									<td>${user.mail }</td>
									<td>${user.created }</td>
									<td><a
										href="servlet/UsersServlet?action=find&uid=${user.uid}"><img
											src="images/icn_edit.png" title="编辑" style="border:0px;"></a>
										<a href="servlet/UsersServlet?action=del&uid=${user.uid}"
										onclick="return confirm('确认删除吗？')"><img
											src="images/icn_trash.png" alt="删除" style="border:0px;" /></a></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>

		</article>
	</form>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
