<?php
/*
  Template Name: Search
 */
?>
<?php get_header(); ?>
	<div id="content">
		<?php if(have_posts()):while(have_posts()):the_post(); ?>
			<div class="post">
				<div class="post_header">
					<div class="post_title_page"><a href="<?php the_permalink(); ?>" ><?php the_title(); ?></a></div>
				</div><div class="clear"></div>
				<div id="cse" style="width: 100%;">正在从Google 加载搜索结果......</div>
				<script src="http://www.google.com/jsapi" type="text/javascript"></script>
				<script type="text/javascript"> 
				  google.load('search', '1', {language : 'zh-CN', style : google.loader.themes.GREENSKY});
				  google.setOnLoadCallback(function() {
					var customSearchOptions = {};  var customSearchControl = new google.search.CustomSearchControl('006580503520945180876:-vp7qjdzaa8', customSearchOptions);
					customSearchControl.setResultSetSize(google.search.Search.FILTERED_CSE_RESULTSET);
					customSearchControl.draw('cse');
					var match = location.search.match(/g=([^&]*)(&|$)/);
					if(match && match[1]){
						var search = decodeURIComponent(match[1]);
						customSearchControl.execute(search);
					}
				  }, true);
				</script>
				<style type="text/css">
					div#adBlock{display:none;!important}
				</style>
				<div class="clear"></div>
			</div>
		<?php endwhile;endif; ?>
		<?php comments_template('',true); ?>
	</div>
<?php get_footer(); ?>