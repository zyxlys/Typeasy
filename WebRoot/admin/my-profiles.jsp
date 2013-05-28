<%@page import="me.imomo.typeasy.vo.UsersVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" scope="request" value="我的资料"></c:set>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<h4 class="alert_info">在这里你可以查看和修改你的个人信息。</h4>
	<article class="module width_full">
		<header>
			<h3 class="tabs_involved">我的资料</h3>
		</header>
		<div class="module_content">
			<div class="padding-box">
				<h1 style="text-align:center;">查看个人信息</h1>
				<hr>
				<h4>用户头像</h4>
				<p><img src="${user.avatar }" alt="用户头像" width="100" height="100" /></p>
				<h4>用户UID</h4>
				<p>${user.uid }</p>
				<h4>用户名</h4>
				<p>${user.name }</p>
				<h4>昵称</h4>
				<p>${user.screenName }</p>
				<h4>电子邮箱</h4>
				<p>${user.mail }</p>
				<h4>个人主页</h4>
				<p>${user.url }</p>
				<h4>注册时间</h4>
				<p>${user.created }</p>
				<h4>用户权限</h4>
				<p>${user.group }</p>
			</div>
			<input type="submit" value="点击修改我的资料" class="alt_btn"
				onclick="window.location.href='../servlet/UsersServlet?action=edit&uid=${user.uid}';">

		</div>

	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
