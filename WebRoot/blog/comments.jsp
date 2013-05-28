<%@ page language="java" import="java.util.*,me.imomo.typeasy.commons.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1 class="comments-title">${c.commentsNum }条评论</h1>

<div id="comments">
	<c:forEach items="${comments }" var="comment">
		<c:if test="${c.cid == comment.cid }">
		<c:set scope="request" value="${comment.mail }" var="gravatarMail"></c:set>
			<div class="comment" id="comment-${c.cid }">
				<div class="comment-avatar">
					<c:forEach items="${users }" var="user">
						<c:choose>
						<c:when test="${user.uid == comment.authorId }"><img src="${user.avatar }" width="50" height="50" alt="用户头像" /></c:when>
						<c:otherwise><%=Gravatar.getAvatar(request.getAttribute("gravatarMail").toString(), 50) %></c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<div class="comment-content">
					<div class="comment-info">
						<span> ${comment.author } </span> ${comment.created }
					</div>

					${comment.text }
				</div>
			</div>
		</c:if>
	</c:forEach>
</div>

<c:if test="${c.allowComment == 0 }">
	<h1 class="comments-title">发表评论</h1>


	<form method="post" action="servlet/CommentsServlet?action=add"
		id="commentform">
		<input type="hidden" name="cid" value="${c.cid }" />
		<input type="hidden" name="ownerId" value="${c.authorId }" />
		<c:choose>
			<c:when test="${user.uid != null }">
			<input type="hidden" name="author" value="${user.screenName }" />
			<input type="hidden" name="authorId" value="${user.uid }" />
			<input type="hidden" name="mail" value="${user.mail }" />
			<input type="hidden" name="url" value="${user.url }" />
				<p>
					当前登录用户 <a href="admin/my-profiles.jsp"> ${user.screenName } </a>. <a
						href="servlet/LoginServlet?action=logout" title="Logout"> 安全退出 &raquo; </a>
				</p>
			</c:when>
			<c:otherwise>

				<p>
					<input type="text" name="author" class="text" size="35"
						value="${user.screenName }" /><label> 昵称 * </label>
				</p>
				<p>
					<input type="text" name="mail" class="text" size="35"
						value="${user.mail }" /><label> E-mail * </label>
				</p>
				<p>
					<input type="text" name="url" class="text" size="35"
						value="${user.url }" /><label> 个人主页 （选填） </label>
				</p>
			</c:otherwise>
		</c:choose>
		<p>
			<textarea rows="10" cols="50" name="text"></textarea>
		</p>
		<p>
			<input type="submit" value="提交评论" class="submit" id="submit" />
		</p>
	</form>
</c:if>

