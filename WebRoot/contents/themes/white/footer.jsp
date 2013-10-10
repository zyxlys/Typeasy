<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  <div class="foot">
    <div class="copy">
      <ul>
        <li><a href="index.jsp">Copyright &copy; 2013 <c:forEach var="option" items="${options }">
			<c:if test="${option.name == 'title' }">${option.value }</c:if>
		</c:forEach></a></li>
        <li>/</li>
        <li><a href="http://llss.me/typeasy">Powered by Typeasy</a></li>
        
        <li>/</li>
        <li><a href="http://llss.me">Theme by Acris</a></li>
      </ul>
    </div>
    <div class="weibo"><a href="http://weibo.com/acris" class="sina" target="_blank"></a> <a href="http://t.qq.com/acrisliu" class="qq" target="_blank"></a></div>
  </div>
</div>

</body>
</html>