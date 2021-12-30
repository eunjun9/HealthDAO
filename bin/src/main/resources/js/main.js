"use strict";

/**
MAIN
**/
function mainStart(){
	if ($('.jsVisualRoll').length > 0)
	{
		//mainVisual(); //비주얼
	}
	if ($('.jsKeyWord').length > 0)
	{
		mainKeyWord(); //키워드
	}
	if ($('.jsExhibition').length > 0)
	{
		mainExhibition(); //기획전
	}
	if ($('.jsBrandNew').length > 0)
	{
		mainBrandNew(); //브랜드 / 신제품
	}
	if ($('.mainBest').length > 0)
	{
		mainBest(); //베스트
	}
	if ($('.mainNowHot').length > 0)
	{
		mainNowHot(); //실시간 인기 제품
	}
	if ($('.mainReco').length > 0)
	{
		mainReco(); //세트상품
	}
}

/* 메인 비주얼 */
var autoPlayTime = 5000;//오토플레이 시간
var mainVisualNow = '1';//현재 비주얼번호

function mainVisual(){
	var mainVisualLeng = $('.jsVisualRoll > div').length;//총 비주얼 갯수
	if (String(mainVisualLeng).length < 2)
	{
		mainVisualLeng = '0' + mainVisualLeng;
	}
	$('.visualRoll .indicator .maxNum').text(mainVisualLeng);

	$('.jsVisualRoll').slick({
		infinite:true,
		speed:300,
		autoplay:true,
		autoplaySpeed:autoPlayTime,
		slidesToShow:1,
		centerMode:true,
		variableWidth:true,
		pauseOnHover:false,
		asNavFor:'.jsVisualBtn'
	});

	if (parseFloat(mainVisualLeng) > 5)
	{
		$('.jsVisualBtn').slick({
			infinite:true,
			speed:300,
			slidesToShow:3,
			centerMode:true,
			variableWidth:true,
			asNavFor: '.jsVisualRoll',
			focusOnSelect: true,
			draggable:false
		});
	}
	else
	{
		$('.jsVisualBtn').slick({
			infinite:true,
			speed:300,
			slidesToShow:3,
			asNavFor: '.jsVisualRoll',
			focusOnSelect: true,
			draggable:false
		});
	}

	$('.jsVisualRoll').on('setPosition', function(event, slick){
		$('.visualRoll .indicator div em').stop().css({'width' : '0'});
		$('.visualRoll .indicator div em').stop().animate({'width' : '100%'},autoPlayTime);
	});
}

$('.jsVisualRoll').on('afterChange', function(event, slick, currentSlide){
	if (String(currentSlide+1).length < 2) mainVisualNow = '0' + (currentSlide+1);
	else mainVisualNow = (currentSlide+1);
	$('.visualRoll .indicator .nowNum').text(mainVisualNow);
});

$('.jsVisualRoll .slick-arrow').hide();

$(document).on('mouseenter', '.jsVisualRoll .slick-slide, .jsVisualRoll .slick-arrow', function(){
	if ($(this).hasClass('slick-current') || $(this).hasClass('slick-arrow'))
	{
		$('.visualRoll .slick-arrow').stop().fadeIn(200);
	}
});

$(document).on('mouseleave', '.jsVisualRoll', function(){
	$('.visualRoll .slick-arrow').stop().fadeOut(200);
});

$(document).on('click', '.mainVisual .indicator button', function(){
	$(this).toggleClass('play');
	if ($(this).hasClass('play'))
	{
		$('.jsVisualRoll').slick('slickPause');
		$('.visualRoll .indicator div em').stop().css({'width' : '0'});
	}
	else
	{
		$('.jsVisualRoll').slick('slickPlay');
		$('.visualRoll .indicator div em').stop().animate({'width' : '100%'},autoPlayTime);
	}
});

/* 키워드 */
var keyWordPo = 0
var keyWordRoll;
var keyWordRollView = false;

function mainKeyWord(){
	keyWordRoll = setInterval(keyWordMove,30);
	$('.jsKeyWord li').each(function(){
		if($(this).find('span').text() === "#케어베어"){//키워드입력
			$(this).addClass('keywordPick');
			var pick = $('.keywordPick')[1],
				pickBtn = $(pick).children('a').addClass('pickBtn');
			if($(this).find('a').hasClass('pickBtn')){
				$(pickBtn).click();
			}
		}
	})
}

function keyWordMove(){
	keyWordPo --;

	if (-$('.mainKeyWord .jsKeyWord li').eq(0).outerWidth() - 3 > keyWordPo)
	{
		$('.mainKeyWord .jsKeyWord').append($('.mainKeyWord .jsKeyWord li').eq(0)).css({'margin-left' : 0});
		keyWordPo = 0;
	}
	else
	{
		$('.mainKeyWord .jsKeyWord').css({'margin-left' : keyWordPo});
	}
}

function keyWordSlick(keyWordLeng){
	var latestIndi = Math.ceil(keyWordLeng / 5);
	var latestIndiW = 100 / Math.ceil(keyWordLeng / 5);

	if (latestIndi > 1)
	{
		$('.mainKeyWord .indicator, .mainKeyWord .slick-arrow').show();
		$('.mainKeyWord .indicator span').css({'width' : latestIndiW + '%', 'left' : 0});
	}
	else
	{
		$('.mainKeyWord .indicator, .mainKeyWord .slick-arrow').hide();
	}

	$('.mainKeyWord .list_box').slick({
		infinite:true,
		speed:300,
		slidesToShow:5,
		slidesToScroll:5,
		arrows:false
	});

	$('.mainKeyWord .list_box').on('beforeChange', function(event, slick, currentSlide, nextSlide){
		$(this).siblings('.indicator').find('span').css({'left' : latestIndiW * Math.ceil(nextSlide / 5) + '%'});
	});

	$(document).on('click', '.mainKeyWord .slick-prev', function(){
		$('.mainKeyWord .list_box').slick('slickPrev');
	});
	$(document).on('click', '.mainKeyWord .slick-next', function(){
		$('.mainKeyWord .list_box').slick('slickNext');
	});
}

$(document).on('mouseenter', '.mainKeyWord .jsKeyWord a', function(){
	clearInterval(keyWordRoll);
});

$(document).on('mouseleave', '.mainKeyWord .jsKeyWord a', function(){
	if (!keyWordRollView)
	{
		keyWordRoll = setInterval(keyWordMove,30);
	}
});

$(document).on('click', '.mainKeyWord .jsKeyWord a', function(){
	if ($(this).hasClass('active'))
	{
		$('.mainKeyWord .jsKeyWord a').removeClass('active');
		$('.mainKeyWord .keyWordView').slideUp(500);
		keyWordRollView = false;
	}
	else
	{
		$('.mainKeyWord .jsKeyWord a').removeClass('active');
		$(this).addClass('active');
		clearInterval(keyWordRoll);
		keyWordRollView = true;
		$('.mainKeyWord .keyWordView .tit span').text($(this).find('span').text());
		$('.mainKeyWord .keyWordView').slideDown(500);
	}
	return false;
});

$(document).on('click', '.mainKeyWord .keyWordClose', function(){
	$('.mainKeyWord .keyWordView').slideUp(500);
	$('.mainKeyWord .jsKeyWord a').removeClass('active');
	keyWordRollView = false;
	keyWordRoll = setInterval(keyWordMove,30);
});

/* 기획전 */
function mainExhibition(){
	var idx = 0;
	var timer = setInterval(function(){
		idx++;
		if($('.jsExhibition').not('.slick-initialized').length > 0){
			$('.jsExhibition').not('.slick-initialized').slick({
				infinite:true,
				dots:true
			});
			clearInterval(timer);
		}else if(idx > 5){
			clearInterval(timer);
		}
	},500)
}

/* 브랜드 / 신제품 */
function mainBrandNew(){
	var idx = 0;
	var timer = setInterval(function(){
		idx++;
		if($('.jsBrandNew .list_box').not('.slick-initialized').length > 0){
			$('.jsBrandNew .list_box').not('.slick-initialized').slick({
				infinite:true,
				speed:300,
				slidesToShow:3,
				slidesToScroll:3
			});
			clearInterval(timer);
		}else if(idx > 5){
			clearInterval(timer);
		}
	},500)
}

/* 메인 베스트 */
$(document).on('click', '.mainBest .tabType a', function(){
	$('.mainBest .list_box').slick('setPosition');
});

function mainBest(){
	var bestidx = 0;
	var besttimer = setInterval(function(){
		bestidx++;
		if($('#mainBestTab1 .list_box').length > 0){
			$('#mainBestTab1 .list_box').slick({
				infinite:true,
				speed:300,
				slidesToShow:4,
				slidesToScroll:4,
				arrows:false
			});
			clearInterval(besttimer);
		}else if(bestidx > 5){
			clearInterval(besttimer);
		}
	},500);

	var bestcateidx = 0;
	var bestcatetimer = setInterval(function(){
		bestcateidx++;
		if($('#mainBestTab2 .list_box').length > 0){
			$('#mainBestTab2 .list_box').slick({
				infinite:true,
				speed:300,
				slidesToShow:4,
				slidesToScroll:4,
				arrows:false
			});
			clearInterval(bestcatetimer);
		}else if(bestcateidx > 5){
			clearInterval(bestcatetimer);
		}
	},500);

	var reviewidx = 0;
	var reviewtimer = setInterval(function(){
		reviewidx++;
		if($('#mainBestTab3 .list_box').length > 0){
			$('#mainBestTab3 .list_box').slick({
				infinite:true,
				speed:300,
				slidesToShow:4,
				slidesToScroll:4,
				arrows:false
			});
			clearInterval(reviewtimer);
		}else if(reviewidx > 5){
			clearInterval(reviewtimer);
		}
	},500);

}

$(document).on('click', '#mainBestTab1 .best-prev', function(){
	$('#mainBestTab1 .list_box').slick('slickPrev');
});
$(document).on('click', '#mainBestTab1 .best-next', function(){
	$('#mainBestTab1 .list_box').slick('slickNext');
});

$(document).on('click', '#mainBestTab2 .best-prev', function(){
	$('#mainBestTab2 .list_box').slick('slickPrev');
});
$(document).on('click', '#mainBestTab2 .best-next', function(){
	$('#mainBestTab2 .list_box').slick('slickNext');
});

$(document).on('click', '#mainBestTab3 .best-prev', function(){
	$('#mainBestTab3 .list_box').slick('slickPrev');
});
$(document).on('click', '#mainBestTab3 .best-next', function(){
	$('#mainBestTab3 .list_box').slick('slickNext');
});


/**
실시간 인기 제품
**/
var mainNowHot1 = 0, mainNowHot2 = 0, mainNowHotTab = 1, mainNowHotInter;

function mainNowHot(){
	mainNowHotInter = setInterval(nowHotInter,3000);
}

function nowHotInter(){
	if (mainNowHotTab == 1)
	{
		mainNowHot1++;
		if (mainNowHot1 > 4)
		{
			mainNowHot1 = 0;
		}
		mainNowHotFocus(mainNowHot1);
	}
	else
	{
		mainNowHot2++;
		if (mainNowHot2 > 4)
		{
			mainNowHot2 = 0;
		}
		mainNowHotFocus(mainNowHot2);
	}
}

$(document).on('mouseenter', '.mainNowHot .mainNowHotTab', function(){
	clearInterval(mainNowHotInter);
});
$(document).on('mouseleave', '.mainNowHot .mainNowHotTab', function(){
	mainNowHotInter = setInterval(nowHotInter,3000);
});

$(document).on('mouseenter','.mainNowHot dt', function(){
	var InterNum = $(this).closest('li').index();
	if (mainNowHotTab == 1)
	{
		mainNowHot1 = InterNum;
	}
	else
	{
		mainNowHot2 = InterNum;
	}
	mainNowHotFocus(InterNum);
});

function mainNowHotFocus(target){
	$('#mainNowHotTab' + mainNowHotTab + ' ol > li').removeClass('active');
	$('#mainNowHotTab' + mainNowHotTab + ' ol > li').eq(target).addClass('active');
}

$(document).on('click', '#mainNowHotTab a', function(){
	clearInterval(mainNowHotInter);
	mainNowHotTab = $('#mainNowHotTab .current').closest('li').index() + 1;
	mainNowHotInter = setInterval(nowHotInter,3000);
});

/* 세트 상품 */
function mainReco(){
	var idx = 0;
	var timer = setInterval(function(){
		idx++;
		if($('.mainReco .list_box').not('.slick-initialized').length > 0){
			$('.mainReco .list_box').not('.slick-initialized').slick({
				infinite:true,
				speed:300,
				slidesToShow:4,
				slidesToScroll:4,
				arrows:false
			});
			clearInterval(timer);
		}else if(idx > 5){
			clearInterval(timer);
		}
	},500)
}

$(document).on('click', '.mainReco .set-prev', function(){
	$('.mainReco .list_box').slick('slickPrev');
});
$(document).on('click', '.mainReco .set-next', function(){
	$('.mainReco .list_box').slick('slickNext');
});