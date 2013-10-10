<%@ page language="java" import="java.util.*,me.llss.vo.*,me.llss.dao.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${sessionScope.user.group != 'admin' }">
	<script type="text/javascript">
		alert('用户权限不够，非法操作！');
		history.back();
	</script>
</c:if>
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
			<form id="options" action="EditOptions" method="post">

				<h1 style="text-align:center;">修改基本信息</h1>
				<h2>博客信息</h2>

				<table class="form-table">

					<tr>
						<th><label for="blog_title">博客标题 <span
								class="description">(必填)</span>
						</label></th>
						<td><input type="text" name="title" id="blog_title"
							value="<c:forEach var="option" items="${options }"><c:if test="${option.name == 'title' }">${option.value }</c:if></c:forEach>"
							class="regular-text" size=50 required /></td>
					</tr>
					<tr>
						<th><label for="blog_description">博客描述<span
								class="description">(必填)</span>
						</label></th>
						<td><input type="text" name="description"
							id="blog_description"
							value="<c:forEach var="option" items="${options }"><c:if test="${option.name == 'description' }">${option.value }</c:if></c:forEach>"
							class="regular-text" size=50 required /></td>
					</tr>
				</table>
				<br> <br>

				<h2>日期格式</h2>

				<table class="form-table">

					<tr>
						<td style="width:250px;"></td>
						<td style="text-align:left;"><label> <input
								type="radio" name="date"
								<c:forEach var="option" items="${options }"><c:if test="${option.value == 'yyyy年MM月dd日' }">checked="checked"</c:if></c:forEach>
								value="yyyy年MM月dd日"> 2013年5月20日
						</label></td>
					</tr>
					<tr>
						<td style="width:250px;"></td>
						<td style="text-align:left;"><label> <input
								type="radio" name="date"
								<c:forEach var="option" items="${options }"><c:if test="${option.value == 'yyyy-MM-dd' }">checked="checked"</c:if>  </c:forEach>
								value="yyyy-MM-dd"> 2013-05-20
						</label></td>
					</tr>
					<tr>
						<td style="width:250px;"></td>
						<td style="text-align:left;"><label> <input
								type="radio" name="date"
								<c:forEach var="option" items="${options }"><c:if test="${option.value == 'yyyy/MM/dd' }">checked="checked"</c:if>  </c:forEach>
								value="yyyy/MM/dd"> 2013/05/20
						</label></td>
					</tr>
					<tr>
						<td style="width:250px;"></td>
						<td style="text-align:left;"><label> <input
								type="radio" name="date"
								<c:forEach var="option" items="${options }"><c:if test="${option.value == 'MM/dd/yyyy' }">checked="checked"</c:if>  </c:forEach>
								value="MM/dd/yyyy"> 05/20/2013
						</label></td>
					</tr>
				</table>
				<br> <br>

				<h2>时间格式</h2>

				<table class="form-table">

					<tr>
						<td style="width:250px;"></td>
						<td style="text-align:left;"><label> <input
								type="radio" name="time"
								<c:forEach var="option" items="${options }"><c:if test="${option.value == 'HH时mm分' }">checked="checked"</c:if> </c:forEach>
								value="HH时mm分"> 13时14分
						</label></td>
					</tr>
					<tr>
						<td style="width:250px;"></td>
						<td style="text-align:left;"><label> <input
								type="radio" name="time"
								<c:forEach var="option" items="${options }"><c:if test="${option.value == 'HH:mm' }">checked="checked"</c:if>  </c:forEach>
								value="HH:mm"> 13:14
						</label></td>
					</tr>
					<tr>
						<td style="width:250px;"></td>
						<td style="text-align:left;"><label> <input
								type="radio" name="time"
								<c:forEach var="option" items="${options }"><c:if test="${option.value == 'a hh:mm' }">checked="checked"</c:if> </c:forEach>
								value="a hh:mm"> 下午 01:14
						</label></td>
					</tr>
					<tr>
						<td style="width:250px;"></td>
						<td style="text-align:left;"><label> <input
								type="radio" name="time"
								<c:forEach var="option" items="${options }"><c:if test="${option.value == 'hh:mm a' }">checked="checked"</c:if> </c:forEach>
								value="hh:mm a"> 01:14 下午
						</label></td>
					</tr>
				</table>
				<br> <br>

				<h2>文章显示设置</h2>

				<table class="form-table">
					<tr>
						<th style="width:280px;text-align:left;"><label
							for="blog_title">文章显示方式<span class="description"></span></label></th>
						<td style="text-align:left"><input type="radio"
							name="excerpt"
							<c:forEach var="option" items="${options }"><c:if test="${option.value == 'excerpt' }">checked="checked"</c:if> </c:forEach>
							value="excerpt"> 摘要 <input type="radio" name="excerpt"
							<c:forEach var="option" items="${options }"><c:if test="${option.value == 'content' }">checked="checked"</c:if> </c:forEach>
							value="content"> 全文</td>
					</tr>
					<tr>
						<th style="width:280px;text-align:left;"><label
							for="blog_title">每页显示文章数<span class="description">(必填，默认10条)</span></label></th>
						<td style="text-align:left"><input type="text" name="number"
							id="blog_title"
							value="<c:forEach var="option" items="${options }"><c:if test="${option.name == 'number' }">${option.value }</c:if></c:forEach>"
							class="regular-text" size=20 required /></td>
					</tr>
					<tr>
						<th style="width:280px;text-align:left;"><label
							for="blog_title">文章摘要字数<span class="description">(文章显示方式为全文时无效)</span></label></th>
						<td style="text-align:left"><input
							<c:forEach var="option" items="${options }"></c:forEach>
							type="text" name="count" id="blog_title"
							value="<c:forEach var="option" items="${options }"><c:if test="${option.name == 'count' }"><fmt:formatNumber type="number" value="${option.value/2 }" maxFractionDigits="0"/></c:if></c:forEach>"
							class="regular-text" size=20 required /></td>
					</tr>
				</table>
				<br> <br>

				<p>
					<input name="submit" type="submit" value="更新基本信息" />
				</p>
			</form>
		</div>
	</article>

	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
