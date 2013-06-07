<%@ page import="me.imomo.typeasy.commons.Gravatar"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="grid_4" id="sidebar">

	<div class="siderss">
		<a href="#" class="rrss" target="_blank" title="订阅">rss</a> <span
			class="addthis_org_cn"><a href="#" class="radd" title="分享">share</a></span>
		<a href="mailto:shallowmo@outlook.com" title="联系我们" class="radd">mail</a>
	</div>

	<div class="widget">
		<h3>用户服务</h3>
		<ul>
			<c:choose>
				<c:when test="${user.uid != null }">
					<li class="last"><a href="admin">后台管理</a></li>
					<li><a href="servlet/LoginServlet?action=logout"
						onclick="return confirm('确认要退出吗？');">安全退出</a></li>
				</c:when>
				<c:otherwise>
					<li class="last"><a href="login.jsp">用户登录</a></li>
					<li class="last"><a href="login.jsp?action=register">免费注册</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>


	<div class="widget">
		<h3>文章分类</h3>
		<ul>
			<c:set scope="request" value="0" var="j"></c:set>
			<c:forEach items="${metas }" var="meta">
				<c:if test="${meta.type == 'category' && j < 5 }">
					<li><a
						href="servlet/ContentsServlet?mid=${meta.mid }&action=listByCategory"
						title="${meta.name }">${meta.name }</a></li>
					<c:set scope="request" value="${j+1 }" var="j"></c:set>
				</c:if>

			</c:forEach>
		</ul>
	</div>

	<div class="widget">
		<h3>最新评论</h3>
		<ul>
			<c:forEach items="${comments }" begin="0" end="4" var="comment">
				<c:set scope="request" value="${comment.mail }" var="gravatarMail"></c:set>
				<div class="m4sidco">
					<a href="post-${comment.cid }.htm#comments"><c:choose>
							<c:when test="${comment.authorId != 0 }">
								<c:forEach items="${users }" var="u">
									<c:if test="${comment.authorId == u.uid }">
										<img src="${u.avatar }" width="40" height="40" />
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise><%=Gravatar
								.getAvatar(request.getAttribute("gravatarMail")
										.toString(), 40)%></c:otherwise>
						</c:choose></a><span><a href="post-${comment.cid }.htm#comments"><c:choose>
								<c:when test="${fn:length(comment.text) > 10 }"> ${fn:substring(comment.text,0,10) }...</c:when>
								<c:otherwise>${comment.text }</c:otherwise>
							</c:choose></a><br>On ${comment.created }</span>
				</div>
			</c:forEach>
		</ul>
	</div>

	<div class="widget">
		<h3>最新文章</h3>
		<ul>
			<c:set scope="request" value="0" var="i"></c:set>
			<c:forEach items="${contents }" var="content">
				<c:if test="${content.type == 'post' && i < 5 }">
					<li><a href="post-${content.cid }.htm"><c:choose>
								<c:when test="${fn:length(content.title) > 15 }"> ${fn:substring(content.title,0,15) }...</c:when>
								<c:otherwise>${content.title }</c:otherwise>
							</c:choose></a></li>
					<c:set scope="request" value="${i+1 }" var="i"></c:set>
				</c:if>
			</c:forEach>

		</ul>
	</div>

	<div class="widget">
		<h3>友情链接</h3>
		<ul>
			<li><a href="http://acora.cc" target="_blank">Acora</a> -
				你認識的任何人</li>
			<li><a href="http://mufeng.me/" target="_blank">牧风</a> - 呼吸风的气息</li>
			<li><a href="http://www.imobei.com/" target="_blank">漠北之南</a> -
				你好！旧时光.</li>
			<li><a href="http://zuidongting.com/" target="_blank">最动听</a> -
				精品资源分享</li>
			<li><a href="http://www.dearzd.com/" target="_blank">咚门</a> -
				他微笑着关了窗</li>
		</ul>
	</div>

	<script type="text/javascript">
		var $ = function(id) {
			return "string" == typeof id ? document.getElementById(id) : id;
		};

		var Class = {
			create : function() {
				return function() {
					this.initialize.apply(this, arguments);
				}
			}
		}

		var Extend = function(destination, source) {
			for ( var property in source) {
				destination[property] = source[property];
			}
			return destination;
		}

		var Calendar = Class.create();
		Calendar.prototype = {
			initialize : function(container, options) {
				this.Container = $(container);//容器(table结构)
				this.Days = [];//日期对象列表

				this.SetOptions(options);

				this.Year = this.options.Year || new Date().getFullYear();
				this.Month = this.options.Month || new Date().getMonth() + 1;
				this.SelectDay = this.options.SelectDay ? new Date(
						this.options.SelectDay) : null;
				this.onSelectDay = this.options.onSelectDay;
				this.onToday = this.options.onToday;
				this.onFinish = this.options.onFinish;

				this.Draw();
			},
			//设置默认属性
			SetOptions : function(options) {
				this.options = {//默认值
					Year : 0,//显示年
					Month : 0,//显示月
					SelectDay : null,//选择日期
					onSelectDay : function() {
					},//在选择日期触发
					onToday : function() {
					},//在当天日期触发
					onFinish : function() {
					}//日历画完后触发
				};
				Extend(this.options, options || {});
			},
			//当前月
			NowMonth : function() {
				this.PreDraw(new Date());
			},
			//上一月
			PreMonth : function() {
				this.PreDraw(new Date(this.Year, this.Month - 2, 1));
			},
			//下一月
			NextMonth : function() {
				this.PreDraw(new Date(this.Year, this.Month, 1));
			},
			//上一年
			PreYear : function() {
				this.PreDraw(new Date(this.Year - 1, this.Month - 1, 1));
			},
			//下一年
			NextYear : function() {
				this.PreDraw(new Date(this.Year + 1, this.Month - 1, 1));
			},
			//根据日期画日历
			PreDraw : function(date) {
				//再设置属性
				this.Year = date.getFullYear();
				this.Month = date.getMonth() + 1;
				//重新画日历
				this.Draw();
			},
			//画日历
			Draw : function() {
				//用来保存日期列表
				var arr = [];
				//用当月第一天在一周中的日期值作为当月离第一天的天数
				for ( var i = 1, firstDay = new Date(this.Year, this.Month - 1,
						1).getDay(); i <= firstDay; i++) {
					arr.push(0);
				}
				//用当月最后一天在一个月中的日期值作为当月的天数
				for ( var i = 1, monthDay = new Date(this.Year, this.Month, 0)
						.getDate(); i <= monthDay; i++) {
					arr.push(i);
				}
				//清空原来的日期对象列表
				this.Days = [];
				//插入日期
				var frag = document.createDocumentFragment();
				while (arr.length) {
					//每个星期插入一个tr
					var row = document.createElement("tr");
					//每个星期有7天
					for ( var i = 1; i <= 7; i++) {
						var cell = document.createElement("td");
						cell.innerHTML = "&nbsp;";
						if (arr.length) {
							var d = arr.shift();
							if (d) {
								cell.innerHTML = d;
								this.Days[d] = cell;
								var on = new Date(this.Year, this.Month - 1, d);
								//判断是否今日
								this.IsSame(on, new Date())
										&& this.onToday(cell);
								//判断是否选择日期
								this.SelectDay
										&& this.IsSame(on, this.SelectDay)
										&& this.onSelectDay(cell);
							}
						}
						row.appendChild(cell);
					}
					frag.appendChild(row);
				}
				//先清空内容再插入(ie的table不能用innerHTML)
				while (this.Container.hasChildNodes()) {
					this.Container.removeChild(this.Container.firstChild);
				}
				this.Container.appendChild(frag);
				//附加程序
				this.onFinish();
			},
			//判断是否同一日
			IsSame : function(d1, d2) {
				return (d1.getFullYear() == d2.getFullYear()
						&& d1.getMonth() == d2.getMonth() && d1.getDate() == d2
						.getDate());
			}
		}
	</script>
	<style type="text/css">
.Calendar {
	font-family: Verdana;
	font-size: 12px;
	background-color: #e8ecf0;
	text-align: center;
	width: 200px;
	height: 160px;
	padding: 10px;
	line-height: 1.5em;
	margin: 0 auto;
}

.Calendar a {
	color: #1e5494;
}

.Calendar table {
	width: 100%;
	border: 0;
}

.Calendar table thead {
	color: #acacac;
}

.Calendar table td {
	font-size: 11px;
	padding: 1px;
}

#idCalendarPre {
	cursor: pointer;
	float: left;
	padding-right: 5px;
}

#idCalendarNext {
	cursor: pointer;
	float: right;
	padding-right: 5px;
}

#idCalendar td.onToday {
	font-weight: bold;
	color: #C60;
}

#idCalendar td.onSelect {
	font-weight: bold;
}
</style>

	<div class="widget" style="height:160px;">
		<h3>日期归档</h3>
		<ul>
			<div class="Calendar">
				<div id="idCalendarPre">&lt;&lt;</div>
				<div id="idCalendarNext">&gt;&gt;</div>
				<span id="idCalendarYear"></span>年 <span id="idCalendarMonth"></span>月
				<table cellspacing="0">
					<thead>
						<tr>
							<td>日</td>
							<td>一</td>
							<td>二</td>
							<td>三</td>
							<td>四</td>
							<td>五</td>
							<td>六</td>
						</tr>
					</thead>
					<tbody id="idCalendar">
					</tbody>
				</table>
			</div>


		</ul>
	</div>

	<script language="JavaScript">
		var cale = new Calendar(
				"idCalendar",
				{
					SelectDay : new Date().setDate(10),
					onSelectDay : function(o) {
						o.className = "onSelect";
					},
					onToday : function(o) {
						o.className = "onToday";
					},
					onFinish : function() {
						$("idCalendarYear").innerHTML = this.Year;
						$("idCalendarMonth").innerHTML = this.Month;
						var flag = [ 10, 15, 20 ];
						for ( var i = 0, len = flag.length; i < len; i++) {
							this.Days[flag[i]].innerHTML = "<a href='javascript:void(0);' onclick=\"alert('日期是:"
									+ this.Year
									+ "/"
									+ this.Month
									+ "/"
									+ flag[i]
									+ "');return false;\">"
									+ flag[i]
									+ "</a>";
						}
					}
				});

		$("idCalendarPre").onclick = function() {
			cale.PreMonth();
		}
		$("idCalendarNext").onclick = function() {
			cale.NextMonth();
		}
	</script>


</div>
<!-- end #sidebar -->
