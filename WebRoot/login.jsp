<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String loginMessage = (String) request.getAttribute("loginMessage");
	String registerMessage = (String) request
			.getAttribute("registerMessage");
	if (loginMessage == null || loginMessage.equals(""))
		loginMessage = "登录后继续~";
	if (registerMessage == null || registerMessage.equals(""))
		registerMessage = "标记*号的为必填项";

	String action = request.getParameter("action");
	if (action == null || !action.equals("register")) {
		action = "login";
	}
%>



<s:if test="%{#session.user.uid != null }">
	<jsp:forward page="admin/index.jsp"></jsp:forward>
</s:if>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
<%
	if (action.equals("login")) {
%>
<title>登录 - Typeasy</title>
<%
	}
%>
<%
	if (action.equals("register")) {
%>
<title>注册 - Typeasy</title>


<%
	}
%>
</head>

<body class="login login-action-login core-ui">
	<div id="login">
		<h1>
			<a href="index.jsp" title="Typeasy">Typeasy</a>
		</h1>
		<%
			if (action.equals("login")) {
		%>
<!-- 		<p class="message login"><%=loginMessage%></p> -->
		<s:actionerror cssClass="message login"/>
		<s:actionmessage cssClass="message register"/>
		<s:form action="Login" id="loginForm" name="loginForm">
			<p>
				<s:textfield name="user_login" label="账号" id="user_login" cssClass="input"></s:textfield>
			</p>
			<p>
				<s:password name="user_pwd" label="密码" id="user_pwd" cssClass="input"></s:password>
			</p>
			<p class="forgetmenot">
				<s:checkbox label="十天内免登录" name="rememberMe" id="rememberme" value="forever"></s:checkbox>
			</p>
			<p class="submit">
				<s:submit name="submit" cssClass="button-primary" id="submit" value="登录"></s:submit>
			</p>
		</s:form>
		
		
		<p id="nav">
			<a href="login.jsp?action=register" title="注册">注册一个新帐户</a>
		</p>
		<p id="backtoblog">
			<a href="index.jsp" title="Home Page">&larr; 回到Typeasy</a>
		</p>
		<%
			}
		%>
		<%
			if (action.equals("register")) {
		%>
		<p class="message register"><%=registerMessage%></p>
		
		<s:form action="Register" id="registerForm" name="registerForm">
			<p>
				<s:textfield name="user_login" label="账号 *" id="user_login" cssClass="input"></s:textfield>
			</p>
			<p>
				<s:password name="user_pwd" label="密码 *" id="user_pwd" cssClass="input"></s:password>
			</p>
			<p>
				<s:password name="user_pwd_agin" label="确认密码 *" id="user_pwd_agin" cssClass="input"></s:password>
			</p>
			<p>
				<s:textfield name="user_email" label="邮箱 *" id="user_email" cssClass="input"></s:textfield>
			</p>
			<p>
				<s:textfield name="user_nickname" label="昵称" id="user_nickname" cssClass="input"></s:textfield>
				
			</p>
			<p class="submit">
				<s:submit name="submit" cssClass="button-primary" id="submit" value="注册"></s:submit>
			</p>
		</s:form>
		
		<p id="nav">
			<a href="login.jsp" title="登录">已经有了一个帐号?点击登录</a>
		</p>
		<p id="backtoblog">
			<a href="index.jsp" title="Home Page">&larr; 回到Typeasy</a>
		</p>
		<%
			}
		%>
	</div>
	<div class="clear"></div>
</body>
</html>
