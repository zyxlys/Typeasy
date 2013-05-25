<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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



<c:if test="${user!=null }">
	<jsp:forward page="admin/index.jsp"></jsp:forward>
</c:if>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/login.css">
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
<script type="text/javascript"
	src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/messages_zh.js"></script>
<!-- 表单验证 -->
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
					minlength : 6
				},
				user_pwd_agin : {
					required : true,
					minlength : 6,
					equalTo : "#user_pwd"
				},
				user_email : {
					required : true,
					email : true
				},
				user_nickname : {
					minlength : 2
				},
			},
		});

	});
</script>
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
		<p class="message login"><%=loginMessage%></p>
		<form name="loginForm" id="loginForm"
			action="servlet/LoginServlet?action=login" method="post">
			<p>
				<label for="user_login">用户名<br /> <input type="text"
					name="user_login" id="user_login" class="input" value="" size="20" />
				</label>
			</p>
			<p>
				<label for="user_pwd">密码<br /> <input type="password"
					name="user_pwd" id="user_pwd" class="input" value="" size="20" />
				</label>
			</p>
			<p class="forgetmenot">
				<label for="rememberme"> <input name="rememberMe"
					type="checkbox" id="rememberme" value="forever" /> 十天内免登录
				</label>
			</p>
			<p class="submit">
				<input type="submit" name="submit" id="submit"
					class="button-primary" value="登录" />
			</p>
		</form>
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
		<form name="registerForm" id="registerForm"
			action="servlet/LoginServlet?action=register" method="post">
			<p>
				<label for="user_login">用户名 *<br /> <input type="text"
					name="user_login" id="user_login" class="input" value="" size="20" />
				</label>
			</p>
			<p>
				<label for="user_pwd">密码 *<br /> <input type="password"
					name="user_pwd" id="user_pwd" class="input" value="" size="20" />
				</label>
			</p>
			<p>
				<label for="user_pwd_agin">确认密码 *<br /> <input
					type="password" name="user_pwd_agin" id="user_pwd_agin"
					class="input" value="" size="20" />
				</label>
			</p>
			<p>
				<label for="user_email">邮箱 *<br /> <input type="text"
					name="user_email" id="user_email" class="input" value="" size="20" />
				</label>
			</p>
			<p>
				<label for="user_nickname">昵称<br /> <input type="text"
					name="user_nickname" id="user_nickname" class="input" value=""
					size="20" />
				</label>
			</p>
			<p class="submit">
				<input type="submit" name="submit" id="submit"
					class="button-primary" value="注册" />
			</p>
		</form>
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
