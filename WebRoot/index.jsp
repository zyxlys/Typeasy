<%@page import="me.imomo.typeasy.vo.UsersVO"%>
<%@page import="me.imomo.typeasy.commons.CookieUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	Cookie cookie = CookieUtil.isCookieExist("UserCookie", request);
	if (cookie != null) {
		UsersVO user = CookieUtil.validateCookieLoginIdAndPassword(cookie);
		if (user != null) {
	session.setAttribute("user", user);
		}
	}
%>



<jsp:forward page="servlet/IndexServlet?error=${param.error }"></jsp:forward>