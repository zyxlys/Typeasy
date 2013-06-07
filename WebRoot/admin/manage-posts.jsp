<%@ page language="java" import="java.util.*,me.imomo.typeasy.vo.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<c:if test="${sessionScope.user.group != 'admin' }">
	<script type="text/javascript">
		alert('用户权限不够，非法操作！');
		history.back();
	</script>
</c:if>
<c:set var="title" scope="request" value="管理文章"></c:set>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<pg:pager items="${countList }" export="currentPageNumber=pageNumber"
	maxPageItems="10">

	<section id="main" class="column">
		<h4 class="alert_info">小提示：单击列名可以将文章按照列名项目排序。</h4>
		<script type="text/javascript">
			var selectAll = function() {
				var all = document.getElementsByName("all")[0];
				var ids = document.getElementsByName("cid");
				if (all.checked) {
					for ( var i = 0; i < ids.length; i++) {
						ids[i].checked = true;
					}
				} else {
					for ( var i = 0; i < ids.length; i++) {
						ids[i].checked = false;
					}
				}
			};
		</script>
		<form action="servlet/ContentsServlet?action=multiDel&type=post"
			method="post">
			<fieldset style="width: 95%;margin: 20px 3% 0 3%;">
				<input type="checkbox" name="all" onclick="selectAll();" /> <select
					style="width: 70%;" name="multiOption">
					<option value="default">批量操作</option>
					<option value="multiDel">删除选中的文章</option>
				</select> <input type="submit" value="应用"> <input type="button"
					value="发表新文章"
					onclick="window.location.href='${basePath }admin/add-post.jsp';">
			</fieldset>

			<article class="module width_full">
				<header>
					<h3 class="tabs_involved">文章管理</h3>
				</header>

				<table class="tablesorter" cellspacing="0">

					<thead>
						<tr>
							<th>标题</th>
							<th>作者</th>
							<th>分类</th>
							<th>发布时间</th>
							<th>评论数</th>
							<th>操作</th>
						</tr>
					</thead>

					<tbody>
						<%
							int count = 0;
						%>
						<c:forEach var="content" items="${contents }">

							<c:if test="${content.type == 'post' }">
								<%
									count++;
								%>
							</c:if>

						</c:forEach>
						<%
							request.setAttribute("countList", count);
						%>
						<c:choose>


							<c:when test="${countList == 0 }">
								<tr>
									<td>文章列表为空</td>
								</tr>
							</c:when>


							<c:otherwise>
								<c:forEach var="content" items="${contents }">
									<c:if test="${content.type == 'post' }">
										<pg:item>
											<tr>
												<td><input type="checkbox" name="cid"
													value="${content.cid }"> <c:choose>
														<c:when test="${fn:length(content.title) > 15 }">${fn:substring(content.title,0,15) } ...</c:when>
														<c:otherwise>${content.title }</c:otherwise>
													</c:choose></td>
												<td><c:forEach items="${sessionScope.users }"
														var="user">
														<c:if test="${content.authorId == user.uid }">${user.screenName }</c:if>
													</c:forEach></td>
												<td><c:forEach items="${relationships }"
														var="relationship">
														<c:if test="${relationship.cid == content.cid }">
															<c:forEach items="${metas }" var="meta">
																<c:if test="${meta.mid == relationship.mid }">
																	<c:if test="${meta.type == 'category' }">${meta.name }</c:if>
																</c:if>
															</c:forEach>
														</c:if>
													</c:forEach></td>
												<td>${content.created }</td>
												<td>${content.commentsNum }</td>
												<td><a
													href="servlet/ContentsServlet?action=find&cid=${content.cid}&type=post&authorId=${content.authorId }"><img
														src="images/icn_edit.png" alt="编辑" style="border:0px;" />
												</a> <a
													href="servlet/ContentsServlet?action=del&cid=${content.cid}&type=post&authorId=${content.authorId }"
													onclick="return confirm('确认删除？')"><img
														src="images/icn_trash.png" alt="删除" style="border:0px;" />
												</a></td>
											</tr>
										</pg:item>
									</c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<div style="text-align: center;padding:10px;">
					<pg:first>
						<a href="${pageUrl}">首页</a>
					</pg:first>
					<pg:prev>
						<a href="${pageUrl}">上页</a>
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
						<a href="${pageUrl}">下页</a>
					</pg:next>
					<pg:last>
						<a href="${pageUrl}">尾页</a>
					</pg:last>
				</div>
			</article>
			<!-- end of content manager article -->
		</form>

		<div class="clear"></div>
		<div class="spacer"></div>
	</section>
</pg:pager>
<jsp:include page="footer.jsp"></jsp:include>
