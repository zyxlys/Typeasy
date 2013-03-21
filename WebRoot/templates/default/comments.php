<div id="comments">
<?php if ( post_password_required() ) : ?>
	<p class="nopassword"><?php _e( 'This post is password protected. Enter the password to view any comments.'); ?></p>
<!-- #comments -->
<?php
		/* Stop the rest of comments.php from being processed,
		 * but don't kill the script entirely -- we still have
		 * to fully load the template.
		 */
		return;
	endif;
?>
	
	<?php if(have_comments()):?>
		
		<ul class="commentlist">
			<?php wp_list_comments("type=comment&callback=commentlist"); ?>
		</ul>
		<div class="commentnavi">
			<span id="cp_post_id" style="display:none;">
				<?php echo $post->ID; ?>
			</span>
			<?php paginate_comments_links('');?>
		</div>
	<?php endif; ?>
	<?php if (comments_open()): ?>
		<div id="respond">
			<div class="cancel_comment_reply">
				<?php cancel_comment_reply_link('取消回复'); ?>
			</div>
			<form action="<?php echo get_option('siteurl'); ?>/wp-comments-post.php" method="post" id="commentform">
				<?php if($user_ID): ?>
					<div>已登录<a href="<?php echo get_option('siteurl'); ?>/wp-admin/profile.php"><?php echo $user_identity; ?></a>&nbsp;，&nbsp;<a href="<?php echo wp_logout_url(get_permalink()); ?>" title="Log out of this account">注销？</a></div>
				<?php else: ?>
					<div class="welcome">
						<?php if($comment_author): ?>
							欢迎回来，<?php echo $comment_author; ?>！
						<?php else: ?>
							填写名称和邮箱即可留言！
						<?php endif; ?>
					</div>
					<div>
						<label>名称：</label>
						<input type="text" name="author" id="author" value="<?php echo $comment_author; ?>" tabindex="1" <?php if($req) echo "aria-required='true'"; ?> />
					</div>
					<div>
						<label>邮箱：</label>
						<input type="text" name="email" id="email" value="<?php echo $comment_author_email; ?>" tabindex="2" <?php if($req) echo "aria-required='true'"; ?> />
					</div>
					<div>
						<label>网站：</label>
						<input type="text" name="url" id="url" value="<?php echo $comment_author_url; ?>" tabindex="3" />
					</div>
				<?php endif; ?>
				<div>
					<textarea name="comment" id="comment" tabindex="4" onkeydown="if(event.ctrlKey&&event.keyCode==13){document.getElementById('submit').click();return false};"></textarea>
				</div>
				<?php include('include/smilies.php'); ?>
				<div>
					<input name="submit" type="submit" id="submit" tabindex="5" value="发布" />
					<?php comment_id_fields(); ?>
				</div>
				<?php do_action('comment_form',$post->ID); ?>
			</form>
		</div>
	<?php endif; ?>
</div>