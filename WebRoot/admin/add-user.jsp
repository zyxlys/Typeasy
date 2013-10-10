<%@ page language="java" import="java.util.*,me.llss.vo.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user.group != 'admin' }">
	<script type="text/javascript">
		alert('用户权限不够，非法操作！');
		history.back();
	</script>
</c:if>
<c:set var="title" scope="request" value="添加用户"></c:set>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<section id="main" class="column">
	<h4 class="alert_info">除了前台注册以外，管理员还可以在后台添加用户。</h4>
	<article class="module width_full">
		<header>
			<h3>添加用户</h3>
		</header>
		<div class="module_content">
			<form id="add-user" action="AddUsers" method="post">
				<h2>基本资料</h2>
				<table class="form-table">

					<tr>
						<th><label for="name">用户名 <span class="description">(必填项
									，用户名唯一，不可重复)</span></label></th>
						<td><input type="text" name="name" id="name" value=""
							class="regular-text" required /></td>
					</tr>
					<tr>
						<th><label for="screenName">用户昵称</label></th>
						<td><input type="text" name="screenName" id="screenName"
							value="" class="regular-text" /></td>
					</tr>
				</table>
				<br> <br>
				<h2>联系信息</h2>
				<table class="form-table">
					<tr>
						<th><label for="mail">电子邮箱 <span class="description">(必填项
									，邮箱唯一，不可重复)</span></label></th>
						<td><input type="text" name="mail" id="mail" value=""
							class="regular-text" required /></td>
					</tr>
					<tr>
						<th><label for="url">个人主页</label></th>
						<td><input type="text" name="url" id="url" value=""
							class="regular-text" /></td>
					</tr>
				</table>
				<br> <br>
				<h2>密码</h2>
				<table class="form-table">
					<tr>
						<th><label for="password">用户密码</label></th>
						<td><input type="password" name="password" id="password"
							value="" class="regular-text" required /></td>
					</tr>
				</table>
				<br> <br>
				<p>
					<input name="submit" type="submit" value="添加用户" />
				</p>
			</form>
			<script>
			$("#add-user").validate();
		</script>
		</div>
	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
