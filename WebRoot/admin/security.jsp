<%@ page language="java" import="java.util.*,me.llss.vo.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" scope="request" value="安全设置"></c:set>



<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<section id="main" class="column">
	<h4 class="alert_info">建议定期修改您的密码，确保帐号安全。</h4>
	<article class="module width_full">
		<header>
			<h3>安全设置</h3>
		</header>
		<div class="module_content">
			<form id="security"
				action="ModifyPwd?uid=${user.uid }&adminId=${user.uid }"
				method="post">
				<h1 style="text-align:center;">修改安全信息</h1>

				<h2>安全信息</h2>
				<table class="form-table">
					<tr>
						<th><label for="user_pwd">原密码 <span
								class="description">(必填)</span></label></th>
						<td><input type="password" name="password" id="password"
							value="" class="regular-text" required /></td>
					</tr>
					<tr>
						<th><label for="user_pwd">新密码</label></th>
						<td><input type="password" name="new_password"
							id="new_password" value="" class="regular-text" /></td>
					</tr>
					<tr>
						<th><label for="user_pwd">确认新密码</label></th>
						<td><input type="password" name="new_password_again"
							id="new_password_again" value="" class="regular-text" /></td>
					</tr>
				</table>
				<br> <br>
				<p>
					<input name="submit" type="submit" value="更新安全信息" />
				</p>
			</form>
			<script>
				$("#security").validate();
			</script>
		</div>
	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
