<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
   <p class="date">这里显示时间</p>
    <h3 class="post_title"><a href="#">title</a></h3>
<div class="post_content cf">
<a id="expand_collapse" href="#">全部展开/全部折叠</a>
<div id="archives">这里显示分类</div>
<script>
 $('#expand_collapse,.archives-yearmonth').css({cursor:"s-resize"});
 $('#archives ul li ul.archives-monthlisting').hide();
 $('#archives ul li ul.archives-monthlisting:first').show();
 $('#archives ul li span.archives-yearmonth').click(function(){$(this).next().slideToggle('fast');return false;});
 $('#expand_collapse').toggle(
 function(){
 $('#archives ul li ul.archives-monthlisting').slideDown('fast');
 },
 function(){
 $('#archives ul li ul.archives-monthlisting').slideUp('fast');
 });
</script>
</div>