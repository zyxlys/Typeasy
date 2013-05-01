<%@page import="me.imomo.typeasy.vo.User"%>
<%@page import="me.imomo.typeasy.commons.CookieUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	Cookie cookie = CookieUtil.isCookieExist("UserCookie", request);
	if (cookie != null) {
		User user = CookieUtil.validateCookieLoginIdAndPassword(cookie);
		if (user != null) {
			session.setAttribute("user", user);
		}
	}
%>
<jsp:forward page="content/themes/moon/index.jsp" />