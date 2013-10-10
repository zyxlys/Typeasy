<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="clearfix" id="footer">
	<p align="center">
		Copyright &copy; 2013
		<c:forEach var="option" items="${options }">
			<c:if test="${option.name == 'title' }">${option.value }</c:if>
		</c:forEach>
		, All right reserved. | Follow me on <a
			href="https://twitter.com/acrisliu" target="_blank">Twitter</a> • <a
			href="https://www.facebook.com/acrisliu" target="_blank">Facebook</a>
		• <a href="http://weibo.com/acris" target="_blank">Sina Weibo</a> • <a
			href="http://github.com/Acris" target="_blank">Github</a>
	</p>
</div>

<!-- end #footer -->
</body>
</html>
