$(function(){
  var $menu = $("#global_menu");
  var $selected = $menu.find('li:first');

  var $moving = $('<li />',{
   className : 'move',
   top : $selected[0].offsetTop + 'px',
   width : $selected[0].offsetWidth + 'px'
  });

  $('#menu_desc > div').each(function(i){
   var $this = $(this);
   $this.css('top',$menu.find('li:nth-child('+parseInt(i+2)+')')[0].offsetTop + 'px');
  });

  $menu.bind('mouseleave',function(){
   moveTo($selected,400);
  })
  .append($moving)
  .find('li')
  .not('#active_menu')
  .bind('mouseenter',function(){
   var $this = $(this);
   var offsetLeft = $this.offset().left - 30;
   $('#menu_desc > div:nth-child('+ parseInt($this.index()) +')').stop(true).animate({'width':offsetLeft+'px'},400, 'easeOutExpo');
   moveTo($this,400);
  })

 .bind('mouseleave',function(){
  var $this = $(this);
  var offsetLeft = $this.offset().left - 30;
  $('#menu_desc > div:nth-child('+ parseInt($this.index()) +')').stop(true).animate({'width':'0px'},400, 'easeOutExpo');
 });;

 $('li#active_menu').each(function(){
  var $this = $(this);
  var offsetLeft = $this.offset().left - 30;
  $('#menu_desc > div:nth-child('+ parseInt($this.index()) +')').stop(true).animate({'width':offsetLeft+'px'},0, 'easeOutExpo');
  moveTo($this,0);
 });

 function moveTo($elem,speed){
  $moving.stop(true).animate({
   top : $elem[0].offsetTop + 'px',
   width : $elem[0].offsetWidth + 'px'
  }, speed, 'easeOutExpo');
 }

});