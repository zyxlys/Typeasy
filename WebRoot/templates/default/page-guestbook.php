<?php
/*
  Template Name: GuestBook
 */
?>
<?php get_header(); ?>
	<div id="content">
		<?php if(have_posts()):while(have_posts()):the_post(); ?>
			<div class="post">
				<div class="post_header">
					<div class="post_title_page"><a href="<?php the_permalink(); ?>" ><?php the_title(); ?></a></div>
				</div><div class="clear"></div>
				<div id="readerwall">
					<h3>留言有墙</h3>
					<ul>
					<?php
						global $wpdb;
						$counts = $wpdb->get_results("SELECT COUNT(comment_author) AS cnt, comment_author, comment_author_url, comment_author_email
						FROM {$wpdb->prefix}comments
						WHERE MONTH(comment_date)=MONTH(NOW()) and year(comment_date)=year(now())
						AND comment_approved = '1'
						AND comment_author_url != ''
						AND comment_type = ''
						AND user_id = '0'
						GROUP BY comment_author_email
						ORDER BY cnt DESC
						LIMIT 9");
						if ( $counts ) {
							foreach ($counts as $count) {
								echo '<li>' . '<a href="'. $count->comment_author_url . '" target="_blank">' . get_avatar($count->comment_author_email, 30).'<span class="wall_count">+'. $count->cnt . '</span><span class="wall_name">'.$count->comment_author.'</span><span class="wall_url">'.$count->comment_author_url.'</span></a></li>';
							}
						}
					?>
					</ul>
				</div>
				<?php the_content(); ?>
				<div class="clear"></div>
			</div>
		<?php endwhile;endif; ?>
		<?php comments_template('',true); ?>
	</div>
<?php get_footer(); ?>