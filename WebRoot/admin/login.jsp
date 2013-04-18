<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% String action = request.getParameter("action"); %>
<% if(action==null) action="login"; %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <% if (action.equals("login")) { %>
    <title>Log In - Typeasy</title>
    <% } %>
    <% if (action.equals("register")) { %>
    <title>Register - Typeasy</title>
    <% } %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/login.css">
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.backstretch.min.js"></script>
<script type="text/javascript"> 
	$.backstretch("images/login-bg.jpg", {speed: 250});
</script>

  </head>
  
  <body class="login login-action-login wp-core-ui">
  <div class="clear_bg"></div>
	<div id="login">
		<h1><a href="/" title="Typeasy">Typeasy</a></h1>
        
<!-- login -->
<% if (action.equals("login")) { %>
<form name="loginform" id="loginform" action="/" method="post">
	<p>
		<label for="user_login">Username<br />
		<input type="text" name="username" id="user_login" class="input" value="" size="20" /></label>
	</p>
	<p>
		<label for="user_pass">Password<br />
		<input type="password" name="password" id="user_pass" class="input" value="" size="20" /></label>
	</p>
	<p class="forgetmenot"><label for="rememberme"><input name="rememberme" type="checkbox" id="rememberme" value="forever"  /> Remember Me</label></p>
	<p class="submit">
		<input type="submit" name="submit" id="submit" class="button-primary" value="Log In" />
		<input type="hidden" name="redirect_to" value="admin/index.jsp" />
		<input type="hidden" name="testcookie" value="1" />
	</p>
</form>

<p id="nav">
<a href="login.jsp?action=register" title="New Account">Create a new account</a>
</p>


	<p id="backtoblog"><a href="/" title="Home Page">&larr; Back to Typeasy Home Page</a></p>
<% } %>

<!-- register -->
<% if (action.equals("register")) { %>
<p class="message register">With a * are required.</p>
<form name=""registerform" id=""registerform" action="/" method="post">
	<p>
		<label for="user_login">Username *<br />
		<input type="text" name="username" id="user_login" class="input" value="" size="20" /></label>
	</p>
	<p>
		<label for="user_pass">Password *<br />
		<input type="password" name="password" id="user_pass" class="input" value="" size="20" /></label>
	</p>
    <p>
		<label for="user_pass_agin">Repeat Password *<br />
		<input type="password" name="password_agin" id="user_pass_agin" class="input" value="" size="20" /></label>
	</p>
    <p>
		<label for="user_email">E-Mail<br />
		<input type="text" name="age" id="user_age" class="input" value="" size="20" /></label>
	</p>
    <p>
		<label for="user_nickname">Nickname<br />
		<input type="text" name="nickname" id="user_nickname" class="input" value="" size="20" /></label>
	</p>
	<p class="submit">
		<input type="submit" name="submit" id="submit" class="button-primary" value="Register" />
		<input type="hidden" name="redirect_to" value="admin/index.jsp" />
		<input type="hidden" name="testcookie" value="1" />
	</p>
</form>

<p id="nav">
<a href="login.jsp" title="Log In">Already have an account?</a>
</p>


	<p id="backtoblog"><a href="/" title="Home Page">&larr; Back to Typeasy Home Page</a></p>
<% } %>	
	</div>

	
		<div class="clear"></div>
	</body>
	</html>
