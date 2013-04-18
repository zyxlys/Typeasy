/*
 * jQuery Easing Scroll - http://moto-mono.net/easingScroll/
 *
 * jQuery required.
 * jQuery Easing Plugin extends this Plugin.
 *
 * Copyright 2009 (c) nori (norimania@gmail.com)
 * http://moto-mono.net
 * Licensed Under the MIT.
 *
 * $Date: 2009-05-11
*/

jQuery.fn.easingScroll = function(a){
	var e = $.extend({
			easing: "swing",
			duration: 500
		},a),
		t = $.support.boxModel ? navigator.appName.match(/Opera/) ? "html" : "html,body" : "body";
	if(isNaN(e.duration)==null){
		if(e.duration.match(/fast/)) e.duration = 210;
		else if(e.duration.match(/normal/)) e.duration = 410;
		else if(e.duration.match(/slow/)) e.duration = 610;
	}
	$(this).each(function(){
		if(this.hash
		&& $(this.hash).length>0
		&& this.href.match(new RegExp(location.href.split("#")[0]))){
			$(this).click(function(d){
				$(t).queue([]).stop();
				var h = this.hash;
				var c = $(h).offset();
				$(t).animate({
					scrollTop: c.top,
					scrollLeft: c.left
				},{
					duration:e.duration,
					easing:e.easing
				});
				d.preventDefault();
				d.stopPropagation();
			});
		}
	});
	$(document).click(function(d){
		$(t).queue([]).stop();
	});
}
