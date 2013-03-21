var i = 0, got = -1, len = document.getElementsByTagName('script').length;
while ( i <= len && got == -1){
	var js_url = document.getElementsByTagName('script')[i].src,
	got = js_url.indexOf('bg.js'); i++ ;
}
var wp_url = js_url.substr(0, js_url.indexOf('wp-content')),
	bg_src=new Array(),
	bg_img=new Image(),
	bg_new_img=new Image(),
	bg=$('<div id=bg/>'),
	bg1=$('<div id=bg1/>'),
	index;

function Ajax_bg_src(){
	jQuery.ajax({
		type:'GET',
		url: wp_url+"?action=Ajax_bg",
		success:function(data){
			bg_src=data.split('<!--bg_src-->');
			//alert(bg_src);
			background();//获取地址成功显示
		}
    });
}
function bg_image_pos(W,o,o_img,bg_new_img){
	var LH=bg_new_img.height/bg_new_img.width*W.width(),//对firefox，汗~~啊
		LW=bg_new_img.width/bg_new_img.height*W.height();
	/*if(OH>=W.height()){*/
		$(o_img).css({
			'width':W.width(),
			'height':LH
		});
		o.css({
			'margin-top':(W.height()-LH)/2//垂直居中
		});
		
	/*}else{
		$(o_img).css({
			'height':W.height(),
			'width':LW
		});
		o.css({
			'margin-left':(W.width()-LW)/2//水平居中
		});
	}*/
}
function background_slides(){
	if(index<bg_src.length-1){//下一张图片地址
		index+=1;
	}else{
		index=0;
	}
	bg_new_img.src="";//重要,没这句会出现空白
	bg_new_img.src=bg_src[index];
	bg_new_img.onload=function(){//图片载入成功事件
		$('#bg').animate({opacity:0},1200,function(){
			bg_img.src=bg_new_img.src;
			bg_image_pos($(window),$('#bg'),bg_img,bg_new_img);//定位
			$('#bg').animate({opacity:1},1200);
		});
		window.onresize = function(){//改变窗口调整大小
			bg_image_pos($(window),$('#bg'),bg_img,bg_new_img);
		};
	};
	setTimeout(function(){
		background_slides();
	},40000);
}
function background(){//背景
	index = Math.floor(Math.random() * bg_src.length);//伪随机获取图片地址
	bg_new_img.src="";//重要,没这句会出现空白
	bg_new_img.src=bg_src[index];
	bg_new_img.onload=function(){//图片载入成功事件
		$('#bg')[0]||($('body').append(bg).append(bg1),
		$('#bg').append(bg_img));
		$('#bg').animate({opacity:0},1200,function(){
			bg_img.src=bg_new_img.src;
			bg_image_pos($(window),$('#bg'),bg_img,bg_new_img);//定位
			$('#bg').animate({opacity:1},1200);
		});
		window.onresize = function(){//改变窗口调整大小
			bg_image_pos($(window),$('#bg'),bg_img,bg_new_img);
		};
	};
	if(bg_src.length>1){
		setTimeout(function(){
			background_slides();
		},40000);
	}
}
$(document).ready(function () {
	Ajax_bg_src();
})