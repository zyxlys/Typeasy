<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String action = request.getParameter("action");
	if (action == null || !action.equals("register")) {
		action = "login";
	}
%>



<s:if test="#session.user.uid != null">
	<jsp:forward page="admin/index.jsp"></jsp:forward>
</s:if>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="shortcut icon" href="${basePath }favicon.ico"
	type="image/vnd.microsoft.icon">
<link rel="icon" href="${basePath }favicon.ico"
	type="image/vnd.microsoft.icon">
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/messages_zh.js"></script>

<%
	if (("login").equals(action)) {
%>
<title>登录 - <c:forEach var="option" items="${options }">
		<c:if test="${option.name == 'title' }">${option.value }</c:if>
	</c:forEach></title>
<%
	} else {
%>
<title>注册 - <c:forEach var="option" items="${options }">
		<c:if test="${option.name == 'title' }">${option.value }</c:if>
	</c:forEach></title>
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
			if (("login").equals(action)) {
		%>
		<s:actionerror cssClass="message login error" />
		<s:actionmessage cssClass="message login" />
		<s:form action="Login" id="loginForm" name="loginForm" theme="simple">
			<p>
				<label for="user_login">用户名<br /> <s:textfield
						name="user_login" id="user_login" cssClass="input"></s:textfield></label>
			</p>
			<p>
				<label for="user_pwd">密&nbsp;&nbsp;&nbsp;&nbsp;码<br /> <s:password
						name="user_pwd" id="user_pwd" cssClass="input"></s:password></label>
			</p>
			<p class="forgetmenot">
				<label for="rememberme"> <s:checkbox name="rememberMe"
						id="rememberme" value="forever"></s:checkbox> 十天内免登录
				</label>
			</p>
			<p class="submit">
				<s:submit name="submit" cssClass="button-primary" id="submit"
					value="登录"></s:submit>
			</p>
		</s:form>
		<script>
			$().ready(function() {

				// validate signup form on keyup and submit
				$("#loginForm").validate({
					rules : {
						user_login : {
							required : true,
							minlength : 2
						},
						user_pwd : {
							required : true,
						},
					},
				});

			});
		</script>

		<p id="nav">
			<a href="login.jsp?action=register" title="注册">注册一个新帐户</a>
		</p>
		<%
			} else {
		%>
		<s:actionerror cssClass="message register error" />
		<s:actionmessage cssClass="message register" />

		<s:form action="Register" id="registerForm" name="registerForm"
			theme="simple">
			<p>
				<label for="user_login">用&nbsp;户&nbsp;名 *<br /> <s:textfield
						name="user_login" id="user_login" cssClass="input"></s:textfield></label>
			</p>
			<p>
				<label for="user_pwd">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码
					*<br /> <s:password name="user_pwd" id="user_pwd" cssClass="input"></s:password>
				</label>
			</p>
			<p>
				<label for="user_pwd_agin">确认密码 *<br /> <s:password
						name="user_pwd_agin" id="user_pwd_agin" cssClass="input"></s:password></label>
			</p>
			<p>
				<label for="user_email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱
					*<br /> <s:textfield name="user_email" id="user_email"
						cssClass="input"></s:textfield>
				</label>
			</p>
			<p>
				<label for="user_nickname">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称<br />
					<s:textfield name="user_nickname" id="user_nickname"
						cssClass="input"></s:textfield></label>

			</p>
			<p class="submit">
				<s:submit name="submit" cssClass="button-primary" id="submit"
					value="注册"></s:submit>
			</p>
		</s:form>
		<script>
			$().ready(function() {

				// validate signup form on keyup and submit
				$("#registerForm").validate({
					rules : {
						user_login : {
							required : true,
							minlength : 5
						},
						user_pwd : {
							required : true,
						},
						user_pwd_agin : {
							required : true,
							equalTo : "#user_pwd"
						},
						user_email : {
							required : true,
							email : true
						},
						user_nickname : {
							minlength : 1
						},
					},
				});

			});
		</script>
		<p id="nav">
			<a href="login.jsp" title="登录">已经有了一个帐号?点击登录</a>
		</p>
		<%
			}
		%>
		<p id="backtoblog">
			<a href="index.jsp" title="Home Page">&larr; 回到Typeasy</a>
		</p>
	</div>
	<div class="clear"></div>
</body>
</html>
