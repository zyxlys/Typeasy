<?php
/*
  Template Name: Links
 */
?>
<?php get_header(); ?>
	<div id="content">
		<?php if(have_posts()):while(have_posts()):the_post(); ?>
			<div class="post">
				<div class="post_header">
					<div class="post_title_page"><a href="<?php the_permalink(); ?>" ><?php the_title(); ?></a></div>
				</div><div class="clear"></div>
				<ul class="page_links">
					<?php
						$bookmarks = get_bookmarks('title_li=&categorize=0&category=0&orderby=name&order=ASC');
						if ( !empty($bookmarks) ) {
						foreach ($bookmarks as $bookmark) {
							echo '<li><a href="' . $bookmark->link_url . '" title="'.$bookmark->link_description.'"target="_blank" class="link_name">' . $bookmark->link_name . '</a>' . $bookmark->link_description . '</li>';
							}
						}
					?>
				</ul>
				<div class="post_content">
					<?php the_content('阅读全文'); ?>
				</div>
				<div class="clear"></div>
			</div>
		<?php endwhile;endif; ?>
	</div>
<?php get_footer(); ?>