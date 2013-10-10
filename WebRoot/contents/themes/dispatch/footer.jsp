<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="clearfix" id="footer">
	<div class="frame">
		<div class="bar">
			<p class="copyright">
				Copyrigght &copy; 2013 <a href="index.jsp"><c:forEach
						var="option" items="${options }">
						<c:if test="${option.name == 'title' }">${option.value }</c:if>
					</c:forEach></a> , All rights reserved.
			</p>
			<div class="menu-topmenu-container">
				<ul class="footernav" id="menu-footer">
					<li
						<c:if test="${uri == 'index.jsp' }"> class="current menu-item menu-item-type-taxonomy menu-item-object-category" </c:if>><a
						href="index.jsp">首页</a></li>
					<c:forEach items="${contents }" var="content">

						<c:if test="${content.type == 'page' }">
							<li
								<c:if test="${c.cid == content.cid }">
						class="current menu-item menu-item-type-taxonomy menu-item-object-category" </c:if>><a
								href="page-${content.cid }.htm" title="${content.title }">${content.title
									}</a></li>

						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!--bar-->
	</div>
	<!--frame-->
</div>

</div>

<link rel="stylesheet" type="text/css" media="all"
	href="${applicationScope.themePath }style.css" />
<script type='text/javascript'
	src='${applicationScope.themePath }includes/js/jquery.sticky.js?ver=3.1.1'></script>
<script type='text/javascript'
	src='${applicationScope.themePath }includes/js/quicksand/jquery-css-transform.js?ver=3.1.1'></script>
<script type='text/javascript'
	src='${applicationScope.themePath }includes/js/quicksand/jquery.easing.1.3.js?ver=3.1.1'></script>
<script type='text/javascript'
	src='${applicationScope.themePath }includes/js/quicksand/quicksand.js?ver=3.1.1'></script>
<script type="text/javascript">
	/* <![CDATA[  */

	var J = jQuery.noConflict();

	J(document)
			.ready(
					function() {

						// Drop Menu
						function mainmenu() {
							J(".nav ul ").css({
								display : "none"
							}); // Opera Fix
							J(".nav li").hover(function() {
								J(this).find('ul:first').css({
									visibility : "visible",
									display : "none"
								}).slideDown(200);
							}, function() {
								J(this).find('ul:first').css({
									visibility : "hidden"
								});
							});
						}

						mainmenu();

						// Fade Icons
						J("img.a").hover(function() {
							J(this).stop().animate({
								"opacity" : "0"
							}, "fast");
						}, function() {
							J(this).stop().animate({
								"opacity" : "1"
							}, "fast");
						});

						// Fade Hover Links
						J(".entry-title a").hover(function() {
							J(this).animate({
								"opacity" : ".7"
							}, "fast");
						}, function() {
							J(this).animate({
								"opacity" : "1"
							}, "fast");
						});

						// Remove Margins
						J(".flickrPhotos > li:nth-child(2n)").addClass(
								'remove-margin');
						J('#sidebar > div').last().addClass('last-sidebar');

						// Sticky Sidebar
						J('#sticky > div').last().addClass('last-sidebar');
						J("#sticky").sticky({
							topSpacing : 90,
							className : 'sticky'
						});

						J(".lightbox").fancybox({
							'titlePosition' : 'outside',
							'overlayColor' : '#ddd',
							'overlayOpacity' : 0.9,
							'titleShow' : 'false',
							'transitionIn' : 'elastic',
							'transitionOut' : 'elastic',
							'speedIn' : '1400',
							'speedOut' : '1400',
							'easingIn' : 'easeOutBounce',
							'easingOut' : 'easeOutBounce'
						});

						// Quicksand Filtering
						var $data = J(".filter-posts").clone();

						J('.filter-list li').click(
								function(e) {
									J(".filter li").removeClass("active");
									// Use the last category class as the category to filter by.
									var filterClass = J(this).attr('class')
											.split(' ').slice(-1)[0];

									if (filterClass == 'all-projects') {
										var $filteredData = $data
												.find('.project');
									} else {
										var $filteredData = $data
												.find('.project[data-type='
														+ filterClass + ']');
									}
									J(".filter-posts").quicksand($filteredData,
											{
												duration : 700,
												easing : 'jswing',
												adjustHeight : 'dynamic'
											});
									J(this).addClass("active");
									return false;
								});
					});
	/* ]]> */
</script>

</body>
</html>