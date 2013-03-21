<!DOCTYPE html>
<html>
<head>
	<title><?php
		if(is_home()){
			bloginfo('name');if($paged>1) printf(' - 第%s页',$paged);echo ' | ';bloginfo('description');
		}elseif(is_archive()){
			echo wp_title('');if($paged>1) printf(' - 第%s页',$paged);echo ' | ';bloginfo('name');
		}elseif(is_search()){
			echo 'Search:'.wp_specialchars($s).' | ';bloginfo('name');
		}elseif(is_404()){
			echo '页面不存在！ | ';bloginfo('name');
		}else{
			echo wp_title(' | ',false,right);bloginfo('name');
		}
	?></title>
	<?php if(is_single()){
		if($post->post_expercpt){
			$description=$post->post_excerpt;
		}else{
			$description=cut_str(strip_tags(
				apply_filters('the_content',$post->post_content)
			),200);
		}
		$keywords=get_bloginfo('name').",";
		$tags=wp_get_post_tags($post->ID);
		foreach($tags as $tag){
			$keywords=$keywords.$tag->name.",";
		}
	}?>
	<?php $options=get_option('demo_options'); ?>
	
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="description" content="<?php if(is_single()){echo $description;}else echo bloginfo('description'); ?>" />
	<meta name="keywords" content="<?php if(is_single()){echo $keywords;}else bloginfo('name');?>" />
	<link rel="stylesheet" type="text/css" href="<?php bloginfo("template_url"); ?>/style.css" />
	<link rel="stylesheet" type="text/css" href="<?php bloginfo("template_url"); ?>/include/phzoom.css" />
	<link rel="shortcut icon" type="images/x-icon" href="<?php bloginfo('template_url'); ?>/images/favicon.ico" />
	<script src="<?php bloginfo('template_url'); ?>/script/jquery-1.7.1.min.js"></script>
	<script src="<?php bloginfo('template_url'); ?>/script/index.js"></script>
	<?php if(is_singular()): ?>
		<script src="<?php bloginfo('template_directory'); ?>/include/comments-ajax.js"></script>
	<?php endif; ?>
	<!--[if IE]>
		<style type="text/css">
			.post_title_page{font-size:13px;}
		</style>
	<![endif]-->
	<!--[if IE 6]>
		<style type="text/css">
			#nav{width:582px;height:40px;background:#8dc666;}
			#top{display:none;}
			#archives li{width:500px;height:26px;}
		</style>
	<![endif]-->
	<?php wp_head(); ?>
</head>
<body>
<div id="wrap">
	<div id="header">
		<div id="logo">
			<?php
				if($options['logo']==1){
					if($options['logo1']){//文字logo
						echo $options['logo1'];
					}else{
						bloginfo('name');
					}
				}elseif($options['logo']==2){
					if($options['logo2']){//图片logo
						echo '<a href="'.get_bloginfo("url") .'" ><img src="'.$options["logo2"].'" /></a>';
					}else{
						bloginfo('name');
					}
				}
				else{
					bloginfo('name');
				}
			?>
		</div>
		<div id="nav">
			<?php wp_nav_menu( array('menu' => 'Header Menu','menu_id'=>'main_menu','container'=>'ul' )); ?>
			<div id="search">
				<form action="<?php bloginfo('url'); ?>/search" method="get" id="search_form">
					<input type="text" name="g" class="text" id="s" placeholder="Google" />
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>