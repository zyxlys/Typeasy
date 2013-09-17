<%@page import="me.llss.vo.UsersVO"%>
<%@page import="me.llss.utils.CookieUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	Cookie cookie = CookieUtil.isCookieExist("UserCookie", request);
	if (cookie != null) {
		UsersVO user = CookieUtil
				.validateCookieLoginIdAndPassword(cookie);
		if (user != null) {
			session.setAttribute("user", user);
		}
	}
%>
<c:if test="${param.op != null && param.op == 'regSuc' }">
	<script type="text/javascript">
		alert("注册成功，点击进入首页");
	</script>
</c:if>


<jsp:forward page="servlet/IndexServlet?error=${param.error }"></jsp:forward>