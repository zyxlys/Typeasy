<%@ page language="java" import="java.util.*,me.llss.vo.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user.group != 'admin' }">
	<script type="text/javascript">
		alert('用户权限不够，非法操作！');
		history.back();
	</script>
</c:if>
<c:set var="title" scope="request" value="添加标签"></c:set>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<h4 class="alert_info">你可以在此添加一个标签。</h4>
	<article class="module width_full">
		<header>
			<h3>添加标签</h3>
		</header>
		<div class="module_content">
			<form id="edit-tag" action="AddMetas?type=tag" method="post">
				<table class="form-table">
					<tr>
						<th><label for="name">标签名 <span class="description">(必填)</span></label></th>
						<td><input type="text" name="name" id="name" value=""
							class="regular-text" required/></td>
					</tr>
					<tr>
						<th><label for="slug">缩略名 <span class="description">(缩略名可以为标签名的英文名或缩写)</span></label></th>
						<td><input type="text" name="slug" id="slug" value=""
							class="regular-text" /></td>
					</tr>
					<tr>
						<th><label for="description">标签描述 </label></th>
						<td><textarea name="description" id="description" cols="20"
								rows="5"></textarea></td>
					</tr>
				</table>
				<p>
					<input name="submit" type="submit" value="添加标签" />
				</p>
			</form>
			<script>
			$("#add-tag").validate();
		</script>
		</div>
	</article>

	<div class="spacer"></div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
