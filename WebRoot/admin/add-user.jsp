<%@ page language="java" import="java.util.*,me.imomo.typeasy.vo.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<form id="new-user" action="servlet/UsersServlet?action=add"
				method="post">
				<div class="padding-box">
					<h2>基本资料</h2>
					<table class="form-table">

						<tr>
							<th><label for="name">用户名 <span class="description">(必填项
										，用户名唯一，不可重复)</span></label></th>
							<td><input type="text" name="name" id="name" value=""
								class="regular-text" /></td>
						</tr>
						<br>
						<br>
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
								class="regular-text" /></td>
						</tr>
						<tr>
							<th><label for="url">个人主页</label></th>
							<td><input type="text" name="url" id="url" value=""
								class="regular-text" /></td>
						</tr>
					</table>
					<br> <br>
					<h2>安全</h2>
					<table class="form-table">
						<tr>
							<th><label for="group">用户组 <span class="description">(visitor为普通会员,admin为管理员)</span></label></th>
							<td><input type="text" name="group" id="group"
								value="visitor" class="regular-text" /></td>
						</tr>
						<tr>
							<th><label for="password">密码</label></th>
							<td><input type="password" name="password" id="password"
								value="" class="regular-text" /></td>
						</tr>
						<tr>
							<th><label for="password_agin">确认密码</label></th>
							<td><input type="password" name="password_again"
								id="password_again" value="" class="regular-text" /></td>
						</tr>
					</table>
					<br> <br>
					<p align="right">
						<input name="submit" type="submit" value="添加用户" />
					</p>
				</div>
			</form>
		</div>
	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
