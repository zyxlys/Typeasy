<?php $options=get_option('demo_options'); ?>
	<div id="footer">
		<?php 
		if($options['footer']) : 
			echo $options['footer']; 
		else: 
			echo 'no fuck footer';
		endif; ?>
	</div>
</div>
	<div id="top"><img src="<?php bloginfo('template_url'); ?>/images/top.jpg" /></div>
<?php wp_footer(); ?>
	<?php if($options['lazyload']) : ?><script src="<?php bloginfo('template_url'); ?>/script/jquery.lazyload 1.5.0.js"></script>
		<script>
			$(document).ready(function () {
				$('section img,.commentlist img').lazyload({effect : "fadeIn"});//lazyload
			})
		</script>
	<?php endif ;?>
	
	<?php if($options['phzoom']) : ?><script src="<?php bloginfo('template_url'); ?>/script/phzoom.js"></script>
		<script>
			$(document).ready(function () {
				$('.post_content a').phzoom({});//phzoom
			})
		</script>
	<?php endif ;?>
	
	<?php if($options['bg']) : ?><script src="<?php bloginfo('template_url'); ?>/script/bg.js"></script>
	<?php endif ;?>
</body>
</html>