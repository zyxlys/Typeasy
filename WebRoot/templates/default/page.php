<?php get_header(); ?>
	<div id="content">
		<?php if(have_posts()):while(have_posts()):the_post(); ?>
			<div class="post">
				<div class="post_header">
					<div class="post_title_page"><a href="<?php the_permalink(); ?>" ><?php the_title(); ?></a></div>
				</div><div class="clear"></div>
				<div class="post_content">
					<?php the_content(); ?>
				</div>
				<div class="clear"></div>
			</div>
		<?php endwhile;endif; ?>
		<?php comments_template('',true); ?>
	</div>
<?php get_footer(); ?>