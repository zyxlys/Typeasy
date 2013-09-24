<%@ page language="java" import="java.util.*,me.llss.vo.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" scope="request" value="编辑用户信息"></c:set>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<h4 class="alert_info">管理员拥有在后台编辑用户信息的权限。</h4>
	<article class="module width_full">
		<header>
			<h3>编辑用户信息</h3>
		</header>
		<div class="module_content">
			<form id="edit-user"
				action="EditUsers?uid=${u.uid }&adminId=${user.uid }" method="post">
				<h2>基本资料</h2>
				<table class="form-table">

					<tr>
						<th><label for="name">用户头像</label></th>
						<td>
							<%
								String newAvatar = (String) session.getAttribute("newAvatar");
								if (newAvatar == null || ("").equals(newAvatar)) {
									newAvatar = ((UsersVO) request.getAttribute("u")).getAvatar();
								}
								session.removeAttribute("newAvatar");
							%> <img src="<%=newAvatar%>" alt="用户头像" width="100" height="100" />
							<br /> <a href="javascript:void(0);"
							onclick="PopUpWindow('${basePath }avatar.jsp?avatar=${u.avatar }',200,200,650,500)">点击修改</a>
							<input type="hidden" name="avatar" value="<%=newAvatar%>" />
						</td>
					</tr>
					<tr>
						<th><label for="name">用户名 <span class="description">(用户名不可更改)</span></label></th>
						<td><input type="text" name="name" id="name"
							value="${u.name }" class="regular-text" readonly="readonly" /></td>
					</tr>
					<tr>
						<th><label for="screenName">用户昵称</label></th>
						<td><input type="text" name="screenName" id="screenName"
							value="${u.screenName }" class="regular-text" /></td>
					</tr>
				</table>
				<br> <br>
				<h2>联系信息</h2>
				<table class="form-table">
					<tr>
						<th><label for="mail">电子邮箱 <span class="description">(必填项
									，邮箱唯一，不可重复)</span></label></th>
						<td><input type="text" name="mail" id="mail"
							value="${u.mail }" class="regular-text" required /></td>
					</tr>
					<tr>
						<th><label for="url">个人主页</label></th>
						<td><input type="text" name="url" id="url" value="${u.url }"
							class="regular-text" /></td>
					</tr>
				</table>

				<br> <br>
				<c:if test="${user.group == 'admin' }">
					<h2>高级设置</h2>
					<table class="form-table">
						<tr>
							<th><label for="group">用户组 <span class="description">(仅管理员可见)</span></label></th>
							<td><input type="radio"
								<c:if test="${u.group == 'visitor' }">checked="checked"</c:if>
								name="group" value="visitor" />普通会员 <input type="radio"
								name="group"
								<c:if test="${u.group == 'admin' }">checked="checked"</c:if>
								value="admin" />管理员
								<p>普通会员只能管理自己发表的文章及评论,管理员具有最高权限.</p></td>
						</tr>
					</table>
				</c:if>
				<br> <br>
				<p>
					<input name="submit" type="submit" value="修改用户信息" />
				</p>
			</form>
			<script>
				$("#edit-user").validate();
			</script>
		</div>
	</article>

	<div class="spacer"></div>
</section>
<script type="text/javascript">
	var popUpWin = 0;
	function PopUpWindow(URLStr, left, top, width, height, newWin, scrollbars) {
		if (typeof (newWin) == "undefined")
			newWin = false;

		if (typeof (left) == "undefined")
			left = 100;

		if (typeof (top) == "undefined")
			top = 0;

		if (typeof (width) == "undefined")
			width = 800;

		if (typeof (height) == "undefined")
			height = 760;

		if (newWin) {
			open(URLStr, '',
					'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars='
							+ scrollbars
							+ ',resizable=yes,copyhistory=yes,width=' + width
							+ ',height=' + height + ',left=' + left + ', top='
							+ top + ',screenX=' + left + ',screenY=' + top + '');
			return;
		}

		if (typeof (scrollbars) == "undefined") {
			scrollbars = 0;
		}

		if (popUpWin) {
			if (!popUpWin.closed)
				popUpWin.close();
		}
		popUpWin = open(URLStr, 'popUpWin',
				'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars='
						+ scrollbars + ',resizable=yes,copyhistory=yes,width='
						+ width + ',height=' + height + ',left=' + left
						+ ', top=' + top + ',screenX=' + left + ',screenY='
						+ top + '');
		popUpWin.focus();
	}
</script>

<jsp:include page="footer.jsp"></jsp:include>
