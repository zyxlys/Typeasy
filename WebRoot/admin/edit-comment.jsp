<%@ page language="java" import="java.util.*,me.llss.vo.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" scope="request" value="编辑评论"></c:set>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<h4 class="alert_info">编辑评论。</h4>
	<article class="module width_full">
		<header>
			<h3>编辑评论</h3>
		</header>
		<div class="module_content">
			<form id="edit-comment"
				action="./servlet/CommentsServlet?action=edit&coid=${comment.coid }&authorId=${comment.authorId }"
				method="post">
					<table class="form-table">
						<tr>
							<th><label for="author">作者<span class="description">(必填)</span></label></th>
							<td><input type="text" name="author" id="author"
								value="${comment.author }" class="regular-text" /></td>
						</tr>
						<tr>
							<th><label for="mail">邮箱 <span class="description">(必填)</span></label></th>
							<td><input type="text" name="mail" id="mail"
								value="${comment.mail }" class="regular-text" /></td>
						</tr>
						<tr>
							<th><label for="url">主页</label></th>
							<td><input type="text" name="url" id="url"
								value="${comment.url }" class="regular-text" /></td>
						</tr>
						<tr>
							<th><label for="text">评论</label></th>
							<td><textarea name="text" id="text" cols="20" rows="5">${comment.text }</textarea></td>
						</tr>
					</table>

					<p align="right">
						<input name="submit" type="submit" value="修改评论" />
					</p>
			</form>
		</div>
	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
