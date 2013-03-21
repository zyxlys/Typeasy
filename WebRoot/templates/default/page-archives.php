<?php
/*
  Template Name: Archives
 */
?>
<?php get_header(); ?>
	<div id="content">
		<?php if(have_posts()):while(have_posts()):the_post(); ?>
			<div class="post">
				<div class="post_header">
					<div class="post_title_page"><a href="<?php the_permalink(); ?>" ><?php the_title(); ?></a></div>
				</div><div class="clear"></div>
				<div id="archives">
					<span class="toggle_all">全部展开</span>
					<?php
						$previous_year = $year = 0;
						$previous_month = $month = 0;
						$ul_open = false;
						$myposts = get_posts('numberposts=-1&orderby=post_date&order=DESC');
						foreach($myposts as $post) : 
								setup_postdata($post);
								$year = mysql2date('Y', $post->post_date);
								$month = mysql2date('n', $post->post_date);
								$day = mysql2date('j', $post->post_date);
							if($year != $previous_year || $month != $previous_month) : 
								if($ul_open == true) : 
									echo '</ul>
									</div>';
								endif; 
								echo '<div style="clear:right;">
								<span class="archive_date">'.get_the_time('Y年 m月'). '</span>
								<ul class="archive_content">';
								$ul_open = true; 
							endif; 
							$previous_year = $year; $previous_month = $month; 
							echo '<li>
								<span>'.get_the_time("d日").'</span><a href="'.get_permalink().'" target="_blank">'.get_the_title().'</a>
								<span class="msg">&#40;';if(function_exists('the_views')) { the_views(); } echo '&#41;</span>
							</li>';
						endforeach; 
					echo '</ul>
					</div>'
					?>
				</div>
				<div class="clear"></div>
			</div>
		<?php endwhile;endif; ?>
	</div>
<?php get_footer(); ?>