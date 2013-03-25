$(function(){
  $("a").bind("focus",function(){if(this.blur)this.blur();});
  $('.roll, .highslide-close img,.download_button img,.download_image img,.download_download img,#global_menu a img,#header_menu a img').rollover();
  $("a.external").attr("target","_blank");
  $("#search_tag,#open_category")
    .click(
    function () {
      $("#footer").animate({bottom:"0"},{ duration:700 ,easing:"easeOutExpo"});
      $("#open_category").hide();
      return false;
    }
  );
  $(".close")
    .click(
    function () {
      $("#footer").animate({bottom:"-200px"},{ duration:700 ,easing:"easeOutExpo"});
      $("#open_category").show();
      return false;
    }
  );
  $("#trackback_switch").click(function(){
    $("#comment_switch").removeClass("comment_switch_active");
    $(this).addClass("comment_switch_active");
    $("#comment_area").animate({opacity: 'hide'}, 0);
    $("#trackback_area").animate({opacity: 'show'}, 1000);
    return false;
  });

  $("#comment_switch").click(function(){
    $("#trackback_switch").removeClass("comment_switch_active");
    $(this).addClass("comment_switch_active");
    $("#trackback_area").animate({opacity: 'hide'}, 0);
    $("#comment_area").animate({opacity: 'show'}, 1000);
    return false;
  });
  $("#comment_area ol > li:even").addClass("even_comment");
  $("#comment_area ol > li:odd").addClass("odd_comment");
  $(".even_comment > .children > li").addClass("even_comment_children");
  $(".odd_comment > .children > li").addClass("odd_comment_children");
  $(".even_comment_children > .children > li").addClass("odd_comment_children");
  $(".odd_comment_children > .children > li").addClass("even_comment_children");
  $(".even_comment_children > .children > li").addClass("odd_comment_children");
  $(".odd_comment_children > .children > li").addClass("even_comment_children");

  $("#guest_info input,#comment_textarea textarea")
    .focus( function () { $(this).css({borderColor:"#33a8e5"}) } )
    .blur( function () { $(this).css({borderColor:"#ccc"}) } );

  $("blockquote").append('<div class="quote_bottom"></div>');

  $(".post_content:last").css({margin:"0 0 70px 0"});

  $("a[href*='#wrapper'],a[href*='#respond'],a[href*='#monochrome'],a[href*='#pianoblack']").easingScroll({ easing: "easeOutExpo", duration:1500 });

  $('.tooltip').poshytip({
	className: 'tip-twitter',
	showTimeout: 1,
	alignTo: 'target',
	alignX: 'center',
	offsetY: 8,
	allowTipHover: false,
	fade: true,
	slide: false
  });

});
