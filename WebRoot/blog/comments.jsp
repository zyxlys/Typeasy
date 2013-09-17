<%@ page language="java" import="java.util.*,me.llss.utils.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>


<pg:pager items="${c.commentsNum }"
	export="currentPageNumber=pageNumber" maxPageItems="10"
	url="post-${c.cid }.htm">
	<div id="comments">
		<c:if test="${comments != null }">
			<h4 class="m4ctt">${c.commentsNum }条评论 &raquo;</h4>
			<ol class="pages clearfix">
				<pg:first>
					<a href="${pageUrl}">首页</a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl}"><<</a>
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${pageNumber eq currentPageNumber }">
							<b class="current">${pageNumber }</b>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl }">${pageNumber}</a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>
					<a href="${pageUrl}">>></a>
				</pg:next>
				<pg:last>
					<a href="${pageUrl}">尾页</a>
				</pg:last>
			</ol>

			<ol class="comment-list">
				<c:forEach items="${comments }" var="comment">
					<c:if test="${c.cid == comment.cid }">
						<pg:item>
							<c:set scope="request" value="${comment.mail }"
								var="gravatarMail"></c:set>
							<li id="comment-${comment.coid }"
								class="comment-body comment-parent comment-even">
								<div class="comment-author">

									<c:choose>
										<c:when test="${comment.authorId != 0 }">
											<c:forEach items="${users }" var="u">
												<c:if test="${comment.authorId == u.uid }">
													<img src="${u.avatar }" alt="${comment.author }" width="50"
														height="50" />
												</c:if>
											</c:forEach>
										</c:when>
										<c:otherwise><%=Gravatar.getAvatar(request
												.getAttribute("gravatarMail")
												.toString(), 50)%></c:otherwise>
									</c:choose>
									<cite class="fn"><c:choose>
											<c:when test="${comment.url != null && comment.url != ''}">
												<a href="${comment.url }" target="_blank"
													title="${comment.url }">${comment.author }</a>
											</c:when>
											<c:otherwise> ${comment.author }</c:otherwise>
										</c:choose> </cite>
								</div>
								<div class="comment-meta">
									<a href="${c.cid }.post#comment-${comment.coid }">${comment.created
										}</a>
								</div>
								<p>${comment.text }</p> <c:choose>
									<c:when test="${sessionScope.user.group == 'admin' }">
										<div class="comment-reply">
											<a
												href="servlet/CommentsServlet?action=del&coid=${comment.coid }&cid=${comment.cid }&authorId=${comment.authorId }&fromPage=postPage"
												title="删除该评论" onclick="return confirm('确认要删除该评论吗？');">删除该评论</a>
										</div>
									</c:when>
									<c:otherwise>
										<c:if test="${comment.authorId == sessionScope.user.uid }">
											<div class="comment-reply">
												<a
													href="servlet/CommentsServlet?action=del&coid=${comment.coid }&cid=${comment.cid }&authorId=${comment.authorId }&fromPage=postPage"
													title="删除该评论" onclick="return confirm('确认要删除该评论吗？');">删除该评论</a>
											</div>
										</c:if>
									</c:otherwise>
								</c:choose>
							</li>
						</pg:item>
					</c:if>
				</c:forEach>
			</ol>

		</c:if>

		<c:choose>
			<c:when test="${c.allowComment == 0 }">
				<div id="respond-post-${comment.coid }" class="respond">

					<div class="cancel-comment-reply">
						<a id="cancel-comment-reply-link" href="#" rel="nofollow"
							style="display:none">取消回复</a>
					</div>

					<h4 id="response">发表评论 &raquo;</h4>


					<form method="post" action="servlet/CommentsServlet?action=add"
						id="comment_form">
						<input type="hidden" name="cid" value="${c.cid }" /> <input
							type="hidden" name="ownerId" value="${c.authorId }" />
						<c:choose>
							<c:when test="${user.uid != null }">
								<input type="hidden" name="author" value="${user.screenName }" />
								<input type="hidden" name="authorId" value="${user.uid }" />
								<input type="hidden" name="mail" value="${user.mail }" />
								<input type="hidden" name="url" value="${user.url }" />
								<div class="m4rev">
									当前登录用户 <a href="admin/my-profiles.jsp">${user.screenName }</a>.
									<a href="servlet/LoginServlet?action=logout" title="Logout"
										onclick="return confirm('确认要退出吗？');">安全退出 &raquo;</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="m4rev">
									<input type="text" name="author" id="author" class="miauthor"
										size="15" value="${user.screenName }" required />
								</div>
								<div class="m4rev">
									<input name="mail" id="mail" class="mimail" size="15"
										value="${user.mail }" type="email" required />
								</div>
								<div class="m4rev">
									<input type="url" name="url" id="url" class="miurl" size="15"
										value="${user.url }" />
								</div>
							</c:otherwise>
						</c:choose>
						<div class="m4rev">
							<textarea rows="5" cols="50" name="text" class="mitextarea"
								required></textarea>
						</div>
						<div class="m4rev">
							<input name="submit" id="misubmit" type="image"
								src="images/send.png" value="提交评论" />
						</div>
					</form>
					<script>
						$("#comment_form").validate();
					</script>
				</div>
			</c:when>
			<c:otherwise>
				<h4>评论已关闭</h4>
			</c:otherwise>
		</c:choose>
	</div>
</pg:pager>