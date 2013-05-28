<%@ page language="java" import="java.util.*,me.imomo.typeasy.vo.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" scope="request" value="基本设置"></c:set>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<section id="main" class="column">
	<h4 class="alert_info">在这里你可以自定义您的站点信息</h4>	
	<article class="module width_full">
		<header>
			<h3>基本设置</h3>
		</header>
		<div class="module_content">
			<form id="options" action="servlet/OptionsServlet?action=edit" method="post">
			<div class="padding-box">
				<h1 style="text-align:center;">修改基本信息</h1>
				<h2>博客信息</h2>
				<table class="form-table">
					<tr>
						<th><label for="blog_title">博客标题 <span
								class="description">(必填)</span></label></th>
						<td><input type="text" name="name" id="blog_title"
							value="${option.name }" class="regular-text" size=50 /></td>
					</tr>
					<tr>
						<th><label for="blog_description">博客描述<span
								class="description">(必填)</span></label></th>
						<td><input type="text" name="value"
							id="blog_description" value="${option.value }" class="regular-text"
							size=50 /></td>
					</tr>
				</table>
				<br> <br>
				<p align="right">
					<input name="submit" type="submit" value="更新基本信息" />
				</p>
				</div>
			</form>
		</div>
	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
