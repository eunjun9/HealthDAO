"use strict";

var s = skrollr.init({
	render: function(data){
		//0~1000 영상영역
		//750~2500 HOME영역 TOP-1500
		//2250~4000 AGA GLOW LINE영역 TOP-3000
		//6750~10500 BRAND영역 TOP-10400
		//14750~ 제품영역 TOP-15500
		if(data.curTop < 1500){
			$('.brandArea').hide();
		}
		if(data.curTop >= 1500){
			$('.brandArea').show();
		}
		if(data.curTop >= 1500 && data.curTop < 3000){
			$('.brandArea li a').removeClass('current');
			$('.nav01').addClass('current');
			videoPause('.pauseBtn');
		}
		if(data.curTop >= 3000 && data.curTop < 7000 ){
			$('.brandArea li a').removeClass('current');
			$('.nav02').addClass('current');
			if($('.mouseArea').hasClass('off')){
				setTimeout(function(){
					$('.mouseArea').removeClass('off');
				}, 4500);
			}
		}else{
			$('.mouseArea').addClass('off');
		}
		if(data.curTop >= 7000 && data.curTop < 15500 ){
			$('.brandArea li a').removeClass('current');
			$('.nav03').addClass('current');
		}
		if(data.curTop >= 15500){
			$('.brandArea li a').removeClass('current');
			$('.nav04').addClass('current');
		}
		// 네비 위치 & 색상
		if(data.curTop >= 1500 && data.curTop < 3000){
			$('.brandArea li a').removeClass('change');
			$('.brandArea').addClass('top');
		}
		else if(data.curTop >= 3000 && data.curTop < 4500 ){
			$('.brandArea li a').addClass('change');
			$('.brandArea').removeClass('top');
		}
		else if(data.curTop >= 4500 && data.curTop < 12500){
			$('.brand li a').removeClass('change');
			$('.brandArea').removeClass('top');
		}
		else if(data.curTop >= 12500 && data.curTop < 15500){
			$('.brand li a').addClass('change');
			$('.brandArea').removeClass('top');
		}
		else if(data.curTop >= 15500){
			$('.brand li a').removeClass('change');
			$('.brandArea').addClass('top');
		}
	}
});
$(".brandArea li a").each(function (index){
	$(this).click(function(){
		$('.brandArea li a').removeClass('current');
		if(index === 0){
			$("html,body").stop().animate({ "scrollTop": 1500 }, 1000);
		}
		if(index === 1){
			$("html,body").stop().animate({ "scrollTop": 3000 }, 1000);
		}
		if(index === 2){
			$("html,body").stop().animate({ "scrollTop": 7500 }, 1000);
		}
		if(index === 3){
			$("html,body").stop().animate({ "scrollTop": 15500 }, 1000);
		}
		$(this).addClass('current');
		return false;
	})
});

$(".mouseArea").mouseover(function () {
	$(this).parent().parent('.p2_img').addClass('bye');
});
$(".mouseArea").mouseout(function () {
	$(this).parent().parent('.p2_img').removeClass('bye');
});

// ie fixed 떨림현상
if(navigator.userAgent.match(/Trident\/7\./)) {
	// $('html body').on("mousewheel", function (e) {
	// 	e.preventDefault();

	// 	var wheelDelta = e.originalEvent.deltaY; //휠체크

	// 	// var currentScrollPosition = window.pageYOffset; //아무거나써도 무방
	// 	var currentScrollPosition = $(window).scrollTop(); //아무거나써도 무방

	// 	console.log(wheelDelta, currentScrollPosition);
		
	// 	if (wheelDelta > 0) {
	// 		window.scrollTo(0, currentScrollPosition + 100);
	// 	} else {
	// 		window.scrollTo(0, currentScrollPosition - 100);
	// 	}
	// });
	
	$('body').keydown(function (e) {
		e.preventDefault(); // prevent the default action (scroll / move caret)
		var currentScrollPosition = window.pageYOffset;

		switch (e.which) {

			case 38: // up
				window.scrollTo(0, currentScrollPosition - 100);
				break;

			case 40: // down
				window.scrollTo(0, currentScrollPosition + 100);
				break;

			default: return; // exit this handler for other keys
		} 
	});
}