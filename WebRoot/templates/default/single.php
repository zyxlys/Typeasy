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
				</div>
				<div class="clear"></div>
				<div class="share">
					<ul>
						<li><a href="http://twitter.com/share?url=<?php the_permalink(); ?>&text=<?php the_title(); ?>" target="_blank" rel="nofollow" class="twitter-share" title="Twitter"></a></li>
						<li><a href="http://facebook.com/share.php?u=<?php the_permalink(); ?>&t=<?php the_title(); ?>" target="_blank" rel="nofollow" class="facebook-share" title="facebook"></a></li>
						<li><a href="http://v.t.sina.com.cn/share/share.php?url=<?php the_permalink(); ?>&title=<?php the_title(); ?>" target="_blank" rel="nofollow" class="sina-share" title="新浪微博"></a></li>
						<li><a href="http://v.t.qq.com/share/share.php?title=<?php the_title(); ?>&url=<?php the_permalink(); ?>&site=<?php bloginfo('url');?>" target="_blank" rel="nofollow" class="tencent-share" title="腾讯微博"></a></li>
						<li><a href="http://www.douban.com/recommend/?url=<?php the_permalink(); ?>&title=<?php the_title(); ?>" target="_blank" rel="nofollow" class="douban-share" title="豆瓣网"></a></li>
						<li><a href="http://fanfou.com/sharer?u=<?php the_permalink(); ?>&t=<?php the_title(); ?>" target="_blank" rel="nofollow" class="fanfou-share" title="饭否网"></a></li>
						<li><a href="http://share.renren.com/share/buttonshare?link=<?php the_permalink(); ?>&title=<?php the_title(); ?>" target="_blank" rel="nofollow" class="renren-share" title="人人网"></a></li>
					</ul>
					<span class="share_c">分享到</span>
				</div>
			</div>
		<?php endwhile;endif; ?>
		<?php if (comments_open()): ?>
			<div class="comment_amount">
				<div class="face face1"></div>
				<?php comments_popup_link( '1条留言都还没有，呜呜~~~',' 只有1条留言，呜呜~~~', '一哈有% 条留言了哦','comment_num'); ?>
			</div>
		<?php endif; ?>
		<?php comments_template('',true); ?>
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