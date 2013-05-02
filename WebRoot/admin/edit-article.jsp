<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setAttribute("title", "编辑文章");
%>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<section id="main" class="column">
	<h4 class="alert_info">小提示：单击列名可以将文章按照列名项目排序。</h4>


	<fieldset style="width: 95%;margin: 20px 3% 0 3%;">
		<select style="width: 80%;">
			<option>批量操作</option>
			<option>删除选中的文章</option>
		</select> <input type="submit" value="应用"> <input type="submit"
			value="发表新文章" class="alt_btn"
			onclick="window.location.href='new-article.jsp';">
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
						<tr>
							<td><input type="checkbox"> 标题1</td>
							<td>Acris</td>
							<td>随笔</td>
							<td>2013年5月1日</td>
							<td>3</td>
							<td><input type="image" src="images/icn_edit.png" title="编辑"><input
								type="image" src="images/icn_trash.png" title="删除"></td>
						</tr>
						<tr>
							<td><input type="checkbox"> 标题2</td>
							<td>Acris</td>
							<td>随笔</td>
							<td>2013年5月1日</td>
							<td>8</td>
							<td><input type="image" src="images/icn_edit.png" title="编辑"><input
								type="image" src="images/icn_trash.png" title="删除"></td>
						</tr>
						<tr>
							<td><input type="checkbox"> 标题3</td>
							<td>Acris</td>
							<td>随笔</td>
							<td>2013年5月1日</td>
							<td>5</td>
							<td><input type="image" src="images/icn_edit.png" title="编辑"><input
								type="image" src="images/icn_trash.png" title="删除"></td>
						</tr>
						<tr>
							<td><input type="checkbox"> 标题4</td>
							<td>Acris</td>
							<td>随笔</td>
							<td>2013年5月1日</td>
							<td>1</td>
							<td><input type="image" src="images/icn_edit.png" title="编辑"><input
								type="image" src="images/icn_trash.png" title="删除"></td>
						</tr>
						<tr>
							<td><input type="checkbox"> 标题5</td>
							<td>Acris</td>
							<td>随笔</td>
							<td>2013年5月1日</td>
							<td>3</td>
							<td><input type="image" src="images/icn_edit.png" title="编辑"><input
								type="image" src="images/icn_trash.png" title="删除"></td>
						</tr>
						<tr>
							<td><input type="checkbox"> 标题6</td>
							<td>Acris</td>
							<td>随笔</td>
							<td>2013年5月1日</td>
							<td>4</td>
							<td><input type="image" src="images/icn_edit.png" title="编辑"><input
								type="image" src="images/icn_trash.png" title="删除"></td>
						</tr>
						<tr>
							<td><input type="checkbox"> 标题7</td>
							<td>Acris</td>
							<td>随笔</td>
							<td>2013年5月1日</td>
							<td>8</td>
							<td><input type="image" src="images/icn_edit.png" title="编辑"><input
								type="image" src="images/icn_trash.png" title="删除"></td>
						</tr>
					</tbody>
				</table>

	</article>
	<!-- end of content manager article -->


	<div class="clear"></div>
	<div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
