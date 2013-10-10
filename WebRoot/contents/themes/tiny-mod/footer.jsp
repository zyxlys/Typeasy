<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="footer">
	<p>
		&copy; 2013
		<c:forEach var="option" items="${options }">
			<c:if test="${option.name == 'title' }">${option.value }</c:if>
		</c:forEach>
		, All rights reserved. <br /> Tiny Mod and <a
			href="http://llss.me/typeasy">Typeasy</a>, Created by <a
			href="http://llss.me">Acris</a>.<br /> <a
			href="http://validator.w3.org/check/referer"
			title="This page should validate as XHTML 1.0 Strict">Valid XHTML
			Strict</a>.
		<c:choose>
			<c:when test="${user.uid != null }">
				<br />
				<a href="admin">Management (<c:choose>
						<c:when test="${user.screenName == '' }">新用户${user.uid }</c:when>
						<c:otherwise>${user.screenName }</c:otherwise>
					</c:choose>)
				</a> | <a href="Logout">Log out</a>.
        </c:when>
			<c:otherwise>
				<a href="login.jsp">Login</a>.
        </c:otherwise>
		</c:choose>
</div>
</div>
<!-- end of #wrap -->


</body>
</html>
