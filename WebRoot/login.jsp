<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<%
	String acceptLanguage = request.getHeader("Accept-Language");
	String currentLang = acceptLanguage.split(",")[0];
	if ("zh-CN".equals(currentLang)) {
%>
<script type="text/javascript" src="js/messages_zh.js"></script>
<%
	}
%>
<%
	if (("login").equals(action)) {
%>
<title><s:text name="root.login.login_submit"></s:text> - <c:forEach
		var="option" items="${options }">
		<c:if test="${option.name == 'title' }">${option.value }</c:if>
	</c:forEach></title>
<%
	} else {
%>
<title><s:text name="root.login.reg_submit"></s:text> - <c:forEach
		var="option" items="${options }">
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
				<label for="user_login"><s:text name="root.login.user_login"></s:text>
					<br /> <s:textfield name="user_login" id="user_login"
						cssClass="input"></s:textfield></label>
			</p>
			<p>
				<label for="user_pwd"><s:text name="root.login.user_pwd"></s:text>
					<br /> <s:password name="user_pwd" id="user_pwd" cssClass="input"></s:password></label>
			</p>
			<p class="forgetmenot">
				<label for="rememberme"> <s:checkbox name="rememberMe"
						id="rememberme" value="forever"></s:checkbox> <s:text
						name="root.login.rememberMe"></s:text>
				</label>
			</p>
			<p class="submit">
				<s:submit name="submit" cssClass="button-primary" id="submit"
					key="root.login.login_submit"></s:submit>
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
			<a href="login.jsp?action=register"><s:text
					name="root.login.reg_title"></s:text> </a>
		</p>
		<%
			} else {
		%>
		<s:actionerror cssClass="message register error" />
		<s:actionmessage cssClass="message register" />

		<s:form action="Register" id="registerForm" name="registerForm"
			theme="simple">
			<p>
				<label for="user_login"><s:text name="root.login.user_login"></s:text>
					<br /> <s:textfield name="user_login" id="user_login"
						cssClass="input"></s:textfield></label>
			</p>
			<p>
				<label for="user_pwd"><s:text name="root.login.user_pwd"></s:text>
					<br /> <s:password name="user_pwd" id="user_pwd" cssClass="input"></s:password>
				</label>
			</p>
			<p>
				<label for="user_pwd_agin"><s:text
						name="root.login.user_pwd_agin"></s:text> <br /> <s:password
						name="user_pwd_agin" id="user_pwd_agin" cssClass="input"></s:password></label>
			</p>
			<p>
				<label for="user_email"><s:text name="root.login.user_email"></s:text>
					<br /> <s:textfield name="user_email" id="user_email"
						cssClass="input"></s:textfield> </label>
			</p>
			<p>
				<label for="user_nickname"><s:text
						name="root.login.user_nickname"></s:text> <br /> <s:textfield
						name="user_nickname" id="user_nickname" cssClass="input"></s:textfield></label>

			</p>
			<p class="submit">
				<s:submit name="submit" cssClass="button-primary" id="submit"
					key="root.login.reg_submit"></s:submit>
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
			<a href="login.jsp"><s:text name="root.login.reg_login"></s:text>
			</a>
		</p>
		<%
			}
		%>
		<p id="backtoblog">
			<a href="index.jsp" title="Home Page">&larr; <s:text
					name="root.login.return"></s:text>
			</a>
		</p>
	</div>
	<div class="clear"></div>
</body>
</html>
