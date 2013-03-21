$(document).ready(function () {
	//nav
	$('#nav li:has(ul)').hover(function(){
		$('#content').stop(0,0).animate({
			marginTop:50
		},300);
		$(this).find('ul:first').css({'display':'block','margin-left':'-40px','opacity':'0'}).stop().animate({
			opacity:1,
			marginLeft:0
		},600);
		
	},function(){
		$(this).find('ul:first').stop().hide();
		$('#content').stop(0,0).animate({marginTop:30},300);
	});
	$('nav li ul .current-menu-item').parent().parent().css({'background':'rgba(0,0,0,0.1)'});
	
	//scrollTop
	$('#top').css({"opacity":0});
	$(window).scroll(function(){
		if ($(window).scrollTop()>500){
			$('#top').stop().animate({opacity:1,bottom:20},500);
		}
		else{
			$('#top').stop().animate({opacity:0,bottom:0},500);
		}
	});
	$('#top').bind("click",function(){
		$('html,body').animate({scrollTop:0},500);
	});
	
	//archives
	$('.archive_content').hide();
	$('.archive_content:first').show();
	$('.archive_date').click(function(){
		$(this).next().slideToggle(400);
		return false;
	});
	$('#archives .archive_date').each(function(){
		var num=$(this).next().children('li').size();
		var text=$(this).text();
		$(this).html(text+' <span class="num">( '+num+' 篇文章 )</span>');
	});
	$('.toggle_all').toggle(function(){
		$('.archive_content').show();
		$('.toggle_all').text("全部折叠");
	},function(){
		$('.archive_content').hide();
		$('.toggle_all').text("全部展开");
	});
	
	//share
	$('.share ul').css({"margin-right":"-252px"});
	$('.share_c').toggle(function(){$('.share ul').animate({marginRight:0},600);},function(){$('.share ul').animate({marginRight:-252},600);});
	$('.share ul li a').click(function(){window.showModalDialog($(this).attr("href"),'dialogHeight=100, dialogWidth=400');return false;});
	
	//评论表情
	respond();
	function respond(){
		$('#smilies').hide();
		$('#comment').focus(function() {
			$('#smilies').slideDown('slow');
		});
		//comment.click
		$('.reply').click(function() {
			var atid = '"#' + $(this).parent().parent().attr("id") + '"';
			var atname = $(this).parent().find('.name').text();
			$('#comment').attr("value","<a href=" + atid + ">@" + atname + "" + " </a>").focus();
		});
		$('.cancel_comment_reply a').click(function() {
			$('#comment').attr("value",'');
			$('#smilies').slideUp('slow');
		});
	}
	
	//笑脸和哭脸
	var comment_num=$('.commentlist li').length;
	if(comment_num > 1 || $('.commentnavi a')[0]){
		$('.face').removeClass('face1').addClass('face2');
	}
	
	//ajax评论翻页
	ajacpload();
	function ajacpload(){
		$('.commentnavi a').click(function(){
			var wpurl=$(this).attr("href").split(/(\?|&)action=AjaxCommentsPage.*$/)[0];
			var commentPage = 1;
			if (/comment-page-/i.test(wpurl)) {
				commentPage = wpurl.split(/comment-page-/i)[1].split(/(\/|#|&).*$/)[0];
			} else if (/cpage=/i.test(wpurl)) {
				commentPage = wpurl.split(/cpage=/)[1].split(/(\/|#|&).*$/)[0];
			};
			//alert(commentPage);//获取页数
			var postId =$('#cp_post_id').text();
			//alert(postId);//获取postid
			var url = wpurl.split(/#.*$/)[0];
			url += /\?/i.test(wpurl) ? '&' : '?';
			url += 'action=AjaxCommentsPage&post=' + postId + '&page=' + commentPage;
			//alert(url);//看看传入参数是否正确
			var loading='<div class="commnav_loding">正在努力读取中......</div>';
			$.ajax({
				url:url,
				type: 'GET',
				beforeSend: function() {
					jQuery('.commentlist').empty().html(loading);
				},
				error: function(request) {
						alert(request.responseText);
					},
				success:function(data){
					var responses=data.split('<!--winysky-AJAX-COMMENT-PAGE-->');
					$('.commentlist').empty().html(responses[0]).hide().fadeIn('slow');
					$('.commentnavi').empty().html(responses[1]);
					ajacpload();//自身重载一次
					//single_js();//需要重载的js，注意
					respond();
					$('.commentlist img').lazyload({effect : "fadeIn"});
					$body.animate( { scrollTop: $('#comments').offset().top - 200}, 1000);
				}//返回评论列表顶部
			});
			return false;
		});
	}
})