<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
<!--<?php if(have_posts()) : while (have_posts()) : the_post(); ?>-->
   <p class="date">2013/04/15</p>
    <h3 class="post_title"><a href="#" title="标题">这里显示文章标题</a></h3>
    <div class="post_content cf">
   <!--<?php the_content('<img src="'.get_bloginfo('template_url').'/images/read_more.gif" width="74" height="15" class="roll" />'); ?>-->
   这里是文章内容。<br />
   那又怎样 只要最后我在场 你经过谁 我都敢当
<br />
	去吧 这无妨 可以受伤 但不许对男人绝望 更不必用刺伪装
<br />
	不得已地坚强 那又怎样 只要是最后你在场 就算我曾多荒唐 也会因为对你不改而高尚
<br />

   

<!--<?php if ( is_single() ) : ?> -->  
<div id="similar_post">
<h3>这里显示相关文章</h3>
<ul>
<!--<?php $posts = query_posts("posts_per_page=5&orderby=rand"); if(have_posts()) : while (have_posts()) : the_post(); ?>-->
<li><a href="#" title="标题">相关文章标题</a></li>
<!--<?php endwhile; endif; wp_reset_query(); ?>-->
</ul>
</div>
<!--<?php endif; if ( is_single() ) { ?>-->
<a href="javascript:history.back();" id="return"><img src="./images/return.gif" alt="返回" title="返回" width="61" height="15" class="roll"></a> 
<!--<? } if ( !is_page() ) { ?>-->
     <ul class="post_meta">
      <li class="post_comment">显示评论条数</li>
      <li class="post_category">显示文章分类</li>
     </ul>
<!--<?php } ?>-->
    </div>

<!--<?php endwhile;else : get_template_part("includes/nomatch"); endif; ?>-->

<div id="comments_wrapper">显示评论</div>