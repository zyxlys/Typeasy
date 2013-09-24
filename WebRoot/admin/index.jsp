<%@page import="me.llss.utils.Gravatar"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="title" scope="request" value="管理中心首页"></c:set>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<section id="main" class="column">
	<script type="text/javascript">
		var mess = "";
		day = new Date();
		hr = day.getHours();
		if ((hr >= 0) && (hr <= 4))
			mess = "已经夜深了，请注意休息哦！";
		if ((hr >= 4) && (hr < 7))
			mess = "早上好，这么早就起来了？ ";
		if ((hr >= 7) && (hr < 12))
			mess = "上午好，祝您保持心情愉快！";
		if ((hr >= 12) && (hr <= 13))
			mess = "现在是午饭时间，你做什么好吃的了吗？";
		if ((hr >= 13) && (hr <= 17))
			mess = "该午休了，希望您能够劳逸结合！ ";
		if ((hr >= 17) && (hr <= 18))
			mess = "今晚时分，天空好美！";
		if ((hr >= 18) && (hr <= 19))
			mess = "晚上好！";
		if ((hr >= 19) && (hr <= 23))
			mess = "一天又快过去了，你今天收获很多吧？";
	</script>
	<h4 class="alert_info">
		欢迎归来！
		<script type="text/javascript">
			document.write(mess);
		</script>
	</h4>

	<article class="module width_full">
		<header>
			<h3>欢迎</h3>
		</header>
		<div class="module_content">
			<article class="stats_graph">
				<img src="images/admin-logo.png" width="520" height="140"
					alt="Typeasy" />
			</article>

			<article class="stats_overview">
				<div class="overview_today">
					<p class="overview_count">${fn:length(contents) }</p>
					<p class="overview_type">篇文章和页面</p>
					<p class="overview_count">${fn:length(metas) }</p>
					<p class="overview_type">个目录和标签</p>
				</div>
				<div class="overview_previous">
					<p class="overview_count">${fn:length(users) }</p>
					<p class="overview_type">位用户</p>
					<p class="overview_count">${fn:length(comments) }</p>
					<p class="overview_type">条评论</p>
				</div>
			</article>
			<div class="clear"></div>
		</div>
	</article>
	<!-- end of stats article -->

	<article class="module width_3_quarter">
		<form id="add-post"
			action="AddContents?type=post&authorId=${sessionScope.user.uid }"
			method="post">
			<header>
				<h3>快速发表</h3>
			</header>
			<div class="module_content">
				<fieldset>
					<label>文章标题</label> <input type="text" id="title" name="title"
						value="">
				</fieldset>
				<fieldset>
					<label>文章内容</label>
					<textarea rows="12" name="text" id="text"></textarea>
				</fieldset>
				<fieldset style="width:48%; float:left; margin-right: 3%;">
					<!-- to make two field float next to one another, adjust values accordingly -->
					<label>分类目录</label> <select style="width:92%;" name="category">
						<c:forEach var="meta" items="${metas }">
							<c:if test="${meta.type == 'category'}">
								<option value="${meta.mid }">${meta.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</fieldset>
				<fieldset style="width:48%; float:left;">
					<!-- to make two field float next to one another, adjust values accordingly -->
					<label>文章标签</label> <input name="name" id="name" type="text"
						style="width:92%;">
				</fieldset>
				<div class="clear"></div>
			</div>
			<footer>
				<div class="submit_link">
					<input type="submit" value="发表文章" class="alt_btn">
				</div>
			</footer>
		</form>

	</article>
	<!-- end of content manager article -->

	<article class="module width_quarter">
		<header>
			<h3>最新评论</h3>
		</header>
		<div class="message_list">
			<div class="module_content">
				<c:forEach items="${comments }" begin="0" end="20" var="comment">
					<div class="message">
						<p>${comment.text }</p>
						<p>
							<strong>${comment.author }</strong>
						</p>
					</div>
				</c:forEach>
			</div>
		</div>
	</article>
	<!-- end of messages article -->

	<div class="clear"></div>


	<article class="module width_full">
		<header>
			<h3>程序简介</h3>
		</header>
		<div class="module_content">
			<h1>欢迎使用Typeasy博客系统</h1>
			<p>Typeasy是一个使用Struts2框架编写的轻量级博客程序。</p>

			<p>
				Typeasy名字源于英语单词type和easy的组合，寓意在于让你打字变得很简单。这里的打字就是代表这写博客。你能用它来部署属于你自己的Blog。
				<br />
				Typeasy拥有免费、开源、轻量、安全、人性化和操作简单等一系列的优点。它能让你记录下生活中的每一刻。当前版本为2.0版本，由于时间和技术的问题，程序难免存在纰漏。如果您发现有任何的错误，或者有任何的意见和建议，欢迎您联系我们：
			</p>
			<ul>
				<li>E-Mail: shallowmo@outlook.com</li>
				<li>Q Q: 2235515581</li>
				<li>Github: http://github.com/Acris/</li>
			</ul>
		</div>
	</article>
	<!-- end of styles article -->
	<div class="spacer"></div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
