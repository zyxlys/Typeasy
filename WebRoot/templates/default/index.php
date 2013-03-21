<?php get_header(); ?>
	<div id="content">
		<?php if(have_posts()):while(have_posts()):the_post(); ?>
			<div class="post">
				<div class="post_header">
					<div class="post_author">
						<a href="<?php echo get_author_posts_url(get_the_author_meta( 'ID' )); ?>" title="<?php echo get_the_author_meta( 'display_name' ); ?>"><?php echo get_avatar(get_the_author_meta('user_email'),'40'); ?></a>	
					</div>
					<a href="<?php the_permalink(); ?>" class="post_title"><?php the_title(); ?></a>
					<ul class="post_meta">
						<li><?php the_time('Y/m/d'); ?></li>
						<li><?php the_tags('','&nbsp;,&nbsp;',''); ?></li>
						<li>
							<?php if(comments_open()): ?>
								<?php comments_popup_link( '0 Comments',' 1 Comments', '% Comments'); ?>
							<?php else: ?>
								评论关闭
							<?php endif; ?>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="post_content">
					<?php the_content('阅读全文'); ?>
					<div class="clear"></div>
				</div>
			</div>
		<?php endwhile;endif; ?>
		<div id="pagenavi">
			<?php pagenavi() ?>
		</div>
	</div>
	<?php $options=get_option('demo_options'); ?>
	<?php if($options['bottom_link']): ?>
		<div id="tui">
			<div class="tui1">
				<img src="<?php bloginfo('template_url'); ?>/images/tui1.jpg" />
				<div class="right">
					<a href="http://www.dearzd.com/ludenghun/" target="_blank">路灯魂的音乐空间</a>
				</div>
			</div>
			<div class="tui2">
				<img src="<?php bloginfo('template_url'); ?>/images/tui2.jpg" />
				<div class="right">
					<a href="http://yufan.me/" target="_blank">且听书吟</a>
				</div>
			</div>
			<div class="tui3">
				<img src="<?php bloginfo('template_url'); ?>/images/tui3.jpg" />
				<div class="right">
					<a href="http://www.ishare4you.com/" target="_blank">美剧百科</a>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	<?php endif; ?>
<?php get_footer(); ?>