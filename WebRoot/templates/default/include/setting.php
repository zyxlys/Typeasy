<?php

add_action('admin_menu','theme_setting');

function theme_setting(){
	add_theme_page(__('主题设置'),__('主题设置'),'edit_themes',basename(__FILE__),'setting');
	add_action('admin_init', 'register_theme_setting');
}

function register_theme_setting(){
	register_setting('settings_group','demo_options');
}

function setting(){

?>
<style type="text/css">
	.wrap{width:600px;margin:60px auto;padding:30px;border:1px dashed #8dc666;}
	.choice{border-bottom:1px dashed #8dc666;padding:10px 0;overflow:hidden;}
	.choice .span1{float:left;}
	.choice .span2{float:left;margin-top:10px;}
	.choice .span3{float:right;width:333px;color:rgba(0,0,0,0.4);}
	.choice input[type=text]{width:400px;margin-left:70px;padding:8px 4px;border:1px solid #8dc666;}
	.choice_radio{margin-left:150px;}
	.submit{margin-top:20px;margin-right:10px;padding:0;float:left;}
	.submit input,.submit input:hover,.submit input:active{padding:6px 10px;border:none;border-radius:0;background:#8dc666;color:#fff;text-shadow:none;}
</style>
<script>
jQuery(document).ready(function () {
	//$('.bg_radio').click(function(){$(this).parent().parent().find('input[type=text]').slideDown(400);});//
	jQuery('.bg_radio_no:checked').each(function(){jQuery(this).parent().parent().find('.bg_text').hide();});
	jQuery('.bg_radio').click(function(){jQuery(this).parent().parent().find('.bg_text').slideDown(300);});
	jQuery('.bg_radio_no').click(function(){jQuery(this).parent().parent().find('.bg_text').slideUp(300);});
});
</script>
	<div class="wrap">
		<h2 style="color:#8dc666;border-bottom:1px dashed #8dc666;">Shed主题设置</h2>
		<?php
			if(isset($_REQUEST['save'])){
				echo '<div id="message" class="updated fade"><p><strong> settings saved.</strong></p></div>';
			}
			if( 'reset' == isset($_REQUEST['reset']) ) {
				delete_option('demo_options');
				echo '<div id="message" class="updated fade"><p><strong> settings reset.</strong></p></div>';
			}
		?>
		<form method="post" action="options.php">
			<?php settings_fields('settings_group'); ?>
			<?php $options=get_option('demo_options'); ?>
			<div class="choice">
				<input type="radio" name="demo_options[logo]" value="1" <?php checked('1',$options['logo']); ?> />使用文字logo
				<input type="radio" name="demo_options[logo]" value="2" <?php checked('2',$options['logo']); ?> />使用图片logo
				<span class="span3">（为空显示博客名）</span>
				<div><span class="span2">文字logo：</span><input type="text" name="demo_options[logo1]" value="<?php echo $options['logo1']; ?>" /></div>
				<div><span class="span2">logo地址：</span><input type="text" name="demo_options[logo2]" value="<?php echo $options['logo2']; ?>" /></div>
			</div>
			<div class="choice">
				<span class="span1">背景图片：</span>
				<div class="choice_radio">
					<input type="radio" name="demo_options[bg]" class="bg_radio" value="1" <?php checked('1',$options['bg']); ?> />使用
					<input type="radio" name="demo_options[bg]" class="bg_radio_no" value="" <?php checked('',$options['bg']); ?> />不使用
					<span class="span3">（支持最多9张背景图片。地址不填则不显示图片，跳跃填写如填写了一、三没填二，相当于只填了一、二，背景切换显示2张图片）</span>
				</div>
				<div class="bg_text"  style="clear:both;">
					<div><span class="span2">图片一：</span><input type="text" name="demo_options[bg_img_1]" value="<?php echo $options['bg_img_1']; ?>" /></div>
					<div><span class="span2">图片二：</span><input type="text" name="demo_options[bg_img_2]" value="<?php echo $options['bg_img_2']; ?>" /></div>
					<div><span class="span2">图片三：</span><input type="text" name="demo_options[bg_img_3]" value="<?php echo $options['bg_img_3']; ?>" /></div>
					<div><span class="span2">图片四：</span><input type="text" name="demo_options[bg_img_4]" value="<?php echo $options['bg_img_4']; ?>" /></div>
					<div><span class="span2">图片五：</span><input type="text" name="demo_options[bg_img_5]" value="<?php echo $options['bg_img_5']; ?>" /></div>
					<div><span class="span2">图片六：</span><input type="text" name="demo_options[bg_img_6]" value="<?php echo $options['bg_img_6']; ?>" /></div>
					<div><span class="span2">图片七：</span><input type="text" name="demo_options[bg_img_7]" value="<?php echo $options['bg_img_7']; ?>" /></div>
					<div><span class="span2">图片八：</span><input type="text" name="demo_options[bg_img_8]" value="<?php echo $options['bg_img_8']; ?>" /></div>
					<div><span class="span2">图片九：</span><input type="text" name="demo_options[bg_img_9]" value="<?php echo $options['bg_img_9']; ?>" /></div>
				</div>
			</div>
			<div class="choice">
				<span class="span1">lazyload图片延迟加载：</span>
				<div class="choice_radio">
					<input type="radio" name="demo_options[lazyload]" value="1" <?php checked('1',$options['lazyload']); ?> />使用
					<input type="radio" name="demo_options[lazyload]" value="" <?php checked('',$options['lazyload']); ?> />不使用
				</div>
			</div>
			<div class="choice">
				<span class="span1">phzoom图片放大：</span>
				<div class="choice_radio">
					<input type="radio" name="demo_options[phzoom]" value="1" <?php checked('1',$options['phzoom']); ?> />使用
					<input type="radio" name="demo_options[phzoom]" value="" <?php checked('',$options['phzoom']); ?> />不使用
				</div>
			</div>
			<div class="choice">
				<span class="span1">底部网站链接：</span>
				<div class="choice_radio">
					<input type="radio" name="demo_options[bottom_link]" value="1" <?php checked('1',$options['bottom_link']); ?> />使用
					<input type="radio" name="demo_options[bottom_link]" value="" <?php checked('',$options['bottom_link']); ?> />不使用
					<span class="span3">(我个人网站特殊需要，用不着的k掉就行。图片和地址在index.php和single.php里修改)</span>
				</div>
			</div>
			<div class="choice">
				<div><span class="span2">页脚显示文字：</span><input type="text" name="demo_options[footer]" value="<?php echo $options['footer']; ?>" /></div>
			</div>
			<div class="submit">
				<input type="submit" name="Submit" value="保存设置"/>
			</div>
		</form>
		<form method="post">
			<div class="submit">
				<input type="submit" name="reset" value="重置"/>
				<input type="hidden" name="reset" value="reset" />
			</div>
		</form>
		<div style="padding:10px 0;color:rgba(0,0,0,0.4);">
			<p>感谢使用，有问题可以到www.dearzd.com留言或给我邮件seewest@yahoo.cn</p>
		</div>
		<div class="clear"></div>
	</div>
<?php } ?>