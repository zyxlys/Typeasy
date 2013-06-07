<%@page import="java.io.*,sun.misc.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String pic = request.getParameter("pic");
	String pic1 = request.getParameter("pic1");
	String pic2 = request.getParameter("pic2");
	String pic3 = request.getParameter("pic3");
%>
<jsp:forward page="servlet/AvatarServlet?pic=<%=pic %>&pic1=<%=pic1 %>&pic2=<%=pic2 %>&pic3=<%=pic3 %>"></jsp:forward>