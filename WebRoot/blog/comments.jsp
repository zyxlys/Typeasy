<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="comments">
	<c:if test="${content.commentsNum == 0 }">暂无评论</c:if>
	<c:if test="${content.commentsNum != 0 }">
		<h4>
			<a href="#comment_form">${content.commentsNum }条评论</a>
		</h4>
		<c:forEach items="${comments }" var="comment">
			<c:if test="${comment.cid == content.cid }">
		${comment.author }
		<br />
		${comment.text }
		</c:if>
		</c:forEach>
	</c:if>
	<c:if test="${content.allowComment == '0' }">
		<div id="respond" class="respond">
			<form method="post" action="servlet/CommentsServlet?action=add"
				id="comment_form">
				<fieldset>
					<c:if test="${user.name != null }">
						<p>
							已登录：<c:if test="${user.screenName == null }">新用户${user.uid }</c:if><c:if test="${user.screenName != null }">新用户${user.screenName }</c:if>. <a href="servlet/LoginServlet?action=logout" title="Logout">退出 &raquo;</a>
						</p>
					</c:if>
					<c:if test="${user.name == null }">
						<div class="commentAuthor">
							<label for="author">昵称<span class="required">*</span><input
								type="text" name="author" id="author" class="text" size="15"
								value="" /></label> <label for="mail">电子邮件<span
								class="required">*</span><input type="text" name="mail"
								id="mail" class="text" size="15" value="" /></label> <label for="url">个人主页<input
								type="text" name="url" id="url" class="text" size="15" value="" /></label>
						</div>
					</c:if>
					<label>评论内容<textarea rows="5" cols="50" name="text"
							id="comment" class="textarea"
							onkeydown="if(event.ctrlKey&&event.keyCode==13){document.getElementById('submit').click();return false};"></textarea></label>
					<input type="submit" value="确 认 提 交 / Ctrl + Enter" class="submit"
						id="submit" />
				</fieldset>
			</form>
		</div>
	</c:if>
	<c:if test="${content.allowComment != '0' }">
		<h4>评论已关闭</h4>
	</c:if>
</div>
