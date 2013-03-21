<?php
add_theme_support('menus');

//mp3 player
function mp3link($atts, $content=null){
	extract(shortcode_atts(array("auto"=>'0',"replay"=>'0',),$atts));	
	return '
	<object type="application/x-shockwave-flash" data="'.get_bloginfo("template_url"). '/images/mp3_player.swf" width="240px" height="20px">
		<param name="wmode" value="transparent" />
		<param name="movie" value="'.get_bloginfo("template_url"). '/images/mp3_player.swf" />
		<param name="flashvars" value="mp3='.$content.'&amp;autostart='.$auto.'&amp;autoreplay='.$replay.'" />
	</object>';
}
add_shortcode('mp3','mp3link');

//lrc
function lrc($atts,$content=null,$code=""){
	$return='<div class="lrc">';
	$return.=$content;
	$return.='</div>';
	return $return;
}
add_shortcode('lrc','lrc');

//阅读全文打开是顶部
function remove_more_jump_link($link) {
	$offset = strpos($link, '#more-');
	if ($offset) {
		$end = strpos($link, '"',$offset);
	}
	if ($end) {
		$link = substr_replace($link, '', $offset, $end-$offset);
	}
	return $link;
}
add_filter('the_content_more_link', 'remove_more_jump_link');

//截断文字from良心
function cut_str($string, $sublen, $start = 0, $code = 'UTF-8')
{
	if($code == 'UTF-8'){
		$pa = "/[\x01-\x7f]|[\xc2-\xdf][\x80-\xbf]|\xe0[\xa0-\xbf][\x80-\xbf]|[\xe1-\xef][\x80-\xbf][\x80-\xbf]|\xf0[\x90-\xbf][\x80-\xbf][\x80-\xbf]|[\xf1-\xf7][\x80-\xbf][\x80-\xbf][\x80-\xbf]/";
		preg_match_all($pa, $string, $t_string);
		if(count($t_string[0]) - $start > $sublen) return join('', array_slice($t_string[0], $start, $sublen))."...";
		return join('', array_slice($t_string[0], $start, $sublen));
	}else{
		$start = $start*2;
		$sublen = $sublen*2;
		$strlen = strlen($string);
		$tmpstr = '';
		for($i=0; $i<$strlen; $i++){
			if($i>=$start && $i<($start+$sublen)){
				if(ord(substr($string, $i, 1))>129) $tmpstr.= substr($string, $i, 2);
				else $tmpstr.= substr($string, $i, 1);
			}
			if(ord(substr($string, $i, 1))>129) $i++;
		}
		if(strlen($tmpstr)<$strlen ) $tmpstr.= "...";
		return $tmpstr;
	}
}
//文章分页
 function pagenavi($range = 8){
	global $paged, $wp_query;
	if ( !$max_page ) {$max_page = $wp_query->max_num_pages;}
	if($max_page > 1){
		if(!$paged){$paged = 1;}
		echo "<a href='" . get_pagenum_link(1) . "'title='跳转到首页' class='first'>第一页</a>";
		if($max_page > $range){
			if($paged <= $range){
				for($i = 1; $i <= ($range + 1); $i++){
					if($i==$paged) echo "<span class='current'>$i</span>";
					else echo"<a href='" . get_pagenum_link($i) ."'>$i</a>";
				}
			}
			elseif($paged > $range && $paged < ($max_page - $range)){
				for($i = ($paged - ceil($range/2)); $i <= ($paged + ceil(($range/2))); $i++){
					if($i==$paged) echo "<span class='current'>$i</span>";
					else echo"<a href='" . get_pagenum_link($i) ."'>$i</a>";
				}
			}
			elseif($paged >= ($max_page - $range)){
				for($i = $max_page - $range; $i <= $max_page; $i++){
					if($i==$paged) echo "<span class='current'>$i</span>";
					else echo"<a href='" . get_pagenum_link($i) ."'>$i</a>";
				}
			}
		}
		else{
			for($i = 1; $i <= $max_page; $i++){
				if($i==$paged) echo "<span class='current'>$i</span>";
				else echo"<a href='" . get_pagenum_link($i) ."'>$i</a>";
			}
		}
		echo "<a href='" . get_pagenum_link($max_page) . "'title='跳转到最后一页' class='last'>最后一页</a>";
	}
}

//评论表情
add_filter('smilies_src','custom_smilies_src',1,10);
function custom_smilies_src ($img_src, $img, $siteurl){
    return get_bloginfo('template_directory').'/images/smilies/'.$img;
}
//评论时间
function time_ago( $type = 'commennt', $day = 30 ) {
	$d = $type == 'post' ? 'get_post_time' : 'get_comment_time';
	$timediff = time() - $d('U');
	if ($timediff <= 60*60*24*$day){
	echo  human_time_diff($d('U'), strtotime(current_time('mysql', 0))), '前';
	}
	if ($timediff > 60*60*24*$day){
	echo  date('Y/m/d',get_comment_date('U')), ' ', get_comment_time('H:i');
	};
}
//评论列表
function commentlist($comment,$args,$depth){
	$GLOBALS['comment']=$comment; ?>
	<li <?php comment_class(); ?> id="li-comment-<?php comment_ID ?>">
		<div id="comment-<?php comment_ID(); ?>" class="comment_body">
			<div class="author"><?php echo get_avatar($comment,'30'); ?></div>
			<div class="comment_data">
				<span class="name"><?php printf(__('%s'), get_comment_author_link()) ?></span>
				<div class="time"><?php echo time_ago(); ?></div>
				<div class="reply">
					<?php comment_reply_link(array_merge($args,array('reply_text' =>'回复','depth' =>$depth,'max_depth'=>$args['max_depth']))) ?>
				</div>
				<?php if($comment->comment_approved=='0'): ?>
					<em><span class="moderation"><?php _e('Your comment is awaiting moderation.') ?></span></em>
				<?php endif; ?>
				<div class="text">
					<?php comment_text() ?>
				</div>
				
			</div>
		</div>
<?php 
}

//评论作者新标签打开
function hu_popuplinks($text) {
	$text = preg_replace('/<a (.+?)>/i', "<a $1 target='_blank'>", $text);
	return $text;
}
add_filter('get_comment_author_link', 'hu_popuplinks', 6);

//冒充评论检验
function CheckEmailAndName(){
	global $wpdb;
	$comment_author       = ( isset($_POST['author']) )  ? trim(strip_tags($_POST['author'])) : null;
	$comment_author_email = ( isset($_POST['email']) )   ? trim($_POST['email']) : null;
	if(!$comment_author || !$comment_author_email){
		return;
	}
	$result_set = $wpdb->get_results("SELECT display_name, user_email FROM $wpdb->users WHERE display_name = '" . $comment_author . "' OR user_email = '" . $comment_author_email . "'");
	if ($result_set) {
		if ($result_set[0]->display_name == $comment_author){
			err(__('警告: 您不能使用博主的昵称！'));
		}else{
			err(__('警告: 您不能使用博主的邮箱！'));
		}
		fail($errorMessage);
	}
}

//邮件回复
function comment_mail_notify($comment_id) {
	$admin_notify = '1';
	$admin_email = get_bloginfo ('admin_email');
	$comment = get_comment($comment_id);
	$comment_author_email = trim($comment->comment_author_email);
	$parent_id = $comment->comment_parent ? $comment->comment_parent : '';
	global $wpdb;
	if ($wpdb->query("Describe {$wpdb->comments} comment_mail_notify") == '')
		$wpdb->query("ALTER TABLE {$wpdb->comments} ADD COLUMN comment_mail_notify TINYINT NOT NULL DEFAULT 0;");
	if (($comment_author_email != $admin_email && isset($_POST['comment_mail_notify'])) || ($comment_author_email == $admin_email && $admin_notify == '1'))
		$wpdb->query("UPDATE {$wpdb->comments} SET comment_mail_notify='1' WHERE comment_ID='$comment_id'");
	$notify = $parent_id ? '1' : '0';
	$spam_confirmed = $comment->comment_approved;
	if ($parent_id != '' && $spam_confirmed != 'spam' && $notify == '1') {
		$wp_email = 'no-reply@' . preg_replace('#^www\.#', '', strtolower($_SERVER['SERVER_NAME']));
		$to = trim(get_comment($parent_id)->comment_author_email);
		$subject = '你在' . get_option("blogname") . '被关注啦';
		$message = '
		<div id="mailtou">
			<style type="text/css">
				#mailtou{width:502px;height:auto;margin-bottom:50px;margin-left:auto;margin-right:auto;font-size:13px;line-height:14px;}
				#mailtou a{color:#8dc666;text-decoration:none;outline:none;}
				#mailtou .mail_head{width:502px;margin-top:10px;}
					#mailtou .mail_logo{font-size:16px;color:#373737;text-align:center;}
					#mailtou .mail_title{font-size:15px;color:#f0f7eb;padding:9px;margin-top:20px;overflow:hidden;background:#8dc666;padding-left:30px;padding-right:40px;}
				#mailtou .mail_main{width:420px;margin-top:30px;padding:0 40px 20px;border-left:1px dashed #8dc666;border-right:1px dashed #8dc666;color:rgba(0,0,0,0.7);background:#f9f9f9;overflow:hidden;}
					.one a{display:none}
			</style>
			<div class="mail_head">
				<div class="mail_logo">'.get_bloginfo("name").'</div>
				<div class="mail_title">你在&#8968; '. get_the_title($comment->comment_post_ID) .' &#8971;的评论有了回复：</div>
			</div>
			<div class="mail_main">
				<div class="one origin" style="border:1px solid #EEE;overflow:auto;padding:10px;margin:1em 0;"><span style="color:#8dc666;">'. trim(get_comment($parent_id)->comment_author) .'</span>:'. trim(get_comment($parent_id)->comment_content) .'</div>
				<div class="one reply" style="border:1px solid #EEE;overflow:auto;padding:10px;margin:1em 0 1em 60px;"><span style="color:#8dc666;">'. trim($comment->comment_author) .'</span>:'. trim($comment->comment_content) .'</div>
				<p style="margin-bottom:10px">点击<a href="' . htmlspecialchars(get_comment_link($parent_id)) . '">查看完整内容</a></p>
				<p style="margin-bottom:10px">(此邮件由系统发出,无需回复.)</p>
			</div>
		</div>';
		$from = "From: \"" . get_option('blogname') . "\" <$wp_email>";
		$headers = "$from\nContent-Type: text/html; charset=" . get_option('blog_charset') . "\n";
		wp_mail( $to, $subject, $message, $headers );
	}
}
add_action('comment_post', 'comment_mail_notify');

function Ajax_bg(){
	$options=get_option('demo_options');
	if( isset($_GET['action'])&& $_GET['action'] == 'Ajax_bg'  ){
		if($options['bg_img_1']){
			$bg_img_src=$options['bg_img_1'];
		}
		if($options['bg_img_2']){
			$bg_img_src.='<!--bg_src-->'.$options['bg_img_2'];
		}
		if($options['bg_img_3']){
			$bg_img_src.='<!--bg_src-->'.$options['bg_img_3'];
		}
		if($options['bg_img_4']){
			$bg_img_src.='<!--bg_src-->'.$options['bg_img_4'];
		}
		if($options['bg_img_5']){
			$bg_img_src.='<!--bg_src-->'.$options['bg_img_5'];
		}
		if($options['bg_img_6']){
			$bg_img_src.='<!--bg_src-->'.$options['bg_img_6'];
		}
		if($options['bg_img_7']){
			$bg_img_src.='<!--bg_src-->'.$options['bg_img_7'];
		}
		if($options['bg_img_8']){
			$bg_img_src.='<!--bg_src-->'.$options['bg_img_8'];
		}
		if($options['bg_img_9']){
			$bg_img_src.='<!--bg_src-->'.$options['bg_img_9'];
		}
		echo $bg_img_src;
		die;
	}
}
add_action('init', 'Ajax_bg');

function AjaxCommentsPage(){
	if( isset($_GET['action'])&& $_GET['action'] == 'AjaxCommentsPage'  ){
		global $post,$wp_query, $wp_rewrite;
		$postid = isset($_GET['post']) ? $_GET['post'] : null;
		$pageid = isset($_GET['page']) ? $_GET['page'] : null;
		if(!$postid || !$pageid){
			fail(__('Error post id or comment page id.'));
		}
		// get comments
		$comments = get_comments('post_id='.$postid);
		$post = get_post($postid);
		if(!$comments){
			fail(__('Error! can\'t find the comments'));
		}
		//if( 'desc' != get_option('comment_order') ){
		//	$comments = array_reverse($comments);
		//}
		$comments = array_reverse($comments);//?有点不明白
		// set as singular (is_single || is_page || is_attachment)
		$wp_query->is_singular = true;
		// base url of page links
		$baseLink = '';
		if ($wp_rewrite->using_permalinks()) {
			$baseLink = '&base=' . user_trailingslashit(get_permalink($postid) . 'comment-page-%#%', 'commentpaged');
		}
		// response 注意修改callback为你自己的，没有就去掉callback
		wp_list_comments('callback=commentlist&type=comment&page=' . $pageid . '&per_page=' . get_option('comments_per_page'), $comments);
		echo '<!--winysky-AJAX-COMMENT-PAGE-->';
		echo '<span id="cp_post_id" style="display:none;">
			'.$post->ID.'
		</span>';
		paginate_comments_links('current=' . $pageid . $baseLink);
		die;
	}
}
add_action('init', 'AjaxCommentsPage');

include('include/setting.php');
?>