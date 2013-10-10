<%@ page language="java" import="java.util.*,me.llss.vo.*,me.llss.dao.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<c:if test="${sessionScope.user.group != 'admin' }">
	<script type="text/javascript">
		alert('用户权限不够，非法操作！');
		history.back();
	</script>
</c:if>

<c:set var="title" scope="request" value="主题设置">
</c:set>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<section id="main" class="column">
	<h4 class="alert_info">
		<c:choose>
			<c:when test="${param.op=='suc' }">主题更换成功！马上去<a
					href="index.jsp" target="_blank">博客首页</a>看看吧！</c:when>
			<c:otherwise>在这里你可以选择博客的模板风格</c:otherwise>
		</c:choose>
	</h4>
	<article class="module width_full">
		<header>
			<h3>主题设置</h3>
		</header>
		<div class="module_content">

			<h1 style="text-align:center;">修改博客主题</h1>
			<h2>前台主题</h2>

			<table width="700" height="500" border="0" cellspacing="5"
				cellpadding="5" align="center">
				<tr>
					<td><div class="theme-display">
							<img alt="typeasy" src="${basePath }images/typeasy.png" /><br />
							<br /> 主题名称：<b>Typeasy</b><br /> <br />
							<c:choose>
								<c:when test="${applicationScope.themeType == 'typeasy' }">
									<b>✓ 已启用</b>
								</c:when>
								<c:otherwise>
									<a href="EditTheme?theme=typeasy"><b>启用该主题</b></a>
									<br />
									<br />
								</c:otherwise>
							</c:choose>
						</div></td>
					<td><div class="theme-display">
							<img alt="typeasy" src="${basePath }images/dispatch.png" /><br />
							<br /> 主题名称：<b>Dispatch</b><br /> <br />
							<c:choose>
								<c:when test="${applicationScope.themeType == 'dispatch' }">
									<b>✓ 已启用</b>
								</c:when>
								<c:otherwise>
									<a href="EditTheme?theme=dispatch"><b>启用该主题</b></a>
									<br />
									<br />
								</c:otherwise>
							</c:choose>
						</div></td>

				</tr>

				<tr>
					<td><div class="theme-display">
							<img alt="typeasy" src="${basePath }images/white.png" /><br />
							<br /> 主题名称：<b>White</b><br /> <br />
							<c:choose>
								<c:when test="${applicationScope.themeType == 'white' }">
									<b>✓ 已启用</b>
								</c:when>
								<c:otherwise>
									<a href="EditTheme?theme=white"><b>启用该主题</b></a>
									<br />
									<br />
								</c:otherwise>
							</c:choose>
						</div>
						</div></td>
					<td><div class="theme-display">
							<img alt="typeasy" src="${basePath }images/tiny-mod.png" /><br />
							<br /> 主题名称：<b>Tiny-Mod</b><br /> <br />
							<c:choose>
								<c:when test="${applicationScope.themeType == 'tiny-mod' }">
									<b>✓ 已启用</b>
								</c:when>
								<c:otherwise>
									<a href="EditTheme?theme=tiny-mod"><b>启用该主题</b></a>
									<br />
									<br />
								</c:otherwise>
							</c:choose>
						</div>
						</div></td>
				</tr>
			</table>

		</div>
	</article>
	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
