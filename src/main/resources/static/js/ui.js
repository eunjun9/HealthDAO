"use strict";

/**
CSS ANIMATION CHECK
**/
function whichAnimationEvent(){
  var t,
      el = document.createElement("fakeelement");

  var animations = {
    "animation"      : "animationend",
    "OAnimation"     : "oAnimationEnd",
    "MozAnimation"   : "animationend",
    "WebkitAnimation": "webkitAnimationEnd"
  }

  for (t in animations){
    if (el.style[t] !== undefined){
      return animations[t];
    }
  }
}

var animationEvent = whichAnimationEvent();


/**
LOADING
**/
$(window).on('load',function(){ //로딩 이미지 호출
	loadingClose(); // 윈도우 로딩이 끝난시점에 로딩 종료
});

function loadingOpen(){
	$('#loading').stop(true).hide().fadeIn(300);
}
function loadingClose(){
	$('#loading').fadeOut(300);
}

var mainLoadingHtml = '<div class="mainLoading"><span class="hide">로딩중</span></div>';
function mainLoadingOpen(mainTarget){
	$(mainTarget).append(mainLoadingHtml);
}
function mainLoadingClose(mainTarget){
	$(mainTarget).find('.mainLoading').fadeOut(300, function(){
		$(this).remove();
		$(this).css({'overflow' : ''});
	});
}


/**
스크롤 FUNCTION
**/
$(window).on('scroll', function(){
	orderFloating();//주문 플로팅
	gnbFixed();//GNB FIXED
	quickMenu();//퀵메뉴
});


/* 퀵메뉴 */
function recoQuick(){
	$('.recoQuick').slick({
		infinite:false,
		speed:300,
		slidesToShow:2,
		slidesToScroll:2,
		arrows:false,
		dots: true
	});
}

/**
scrollTop
**/
$(document).on('click', '.btnTop', function(){
	$('html, body').animate({'scrollTop' : 0},200);
});
$(document).on('click', '.btnBtm', function(){
	$('html, body').animate({'scrollTop' : $(document).height()},200);
});

$(window).scroll(function(){
	// 토크 활성화 시 계속 보이게
	if($("#talk").is(':visible')){
		$('.btnTalk').fadeIn('swing');
	}else{
		if($(".mainPt").length > 0){
			if ($(this).scrollTop() > 800){
				$('.btnTalk').fadeIn('swing');
			} else {
				$('.btnTalk').fadeOut('swing');
			}
		}else{
			if ($(this).scrollTop() > 200){
				$('.btnTalk').fadeIn('swing');
			} else {
				$('.btnTalk').fadeOut('swing');
			}
		}
	}
	return false;
});


/**
tabType
**/
$(document).on('click', '.tabType a', function(){
	/* [FIXED SCROLL] */
	if ($(this).closest('.tabType').hasClass('fixedScroll')){
		$('html, body').animate({'scrollTop' : $($(this).attr('href')).offset().top - $('.tabType.fixedScroll').outerHeight()},500);
		return false;
	}
	/* [SCROLL] */
	if ($(this).closest('.tabType').hasClass('scroll'))	{
		scrollSet($(this).attr('href'));
		$(this).closest('.tabType').find('a').removeClass('current');
		$(this).addClass('current');
		return false;
	}
	/* [SHOW HIDE] */
	else if ($(this).closest('.tabType').hasClass('switch')) {
		$('.'+$(this).closest('.tabType').attr('id')).hide();
		$($(this).attr('href')).show();
		$(this).closest('.tabType').find('a').removeClass('current');
		$(this).addClass('current');
		reviewMoreChk(true);
		return false;
	}
	/* [NOLINK] */
	else if ($(this).closest('.tabType').hasClass('no_link')) {
		$(this).closest('.tabType').find('a').removeClass('current');
		$(this).addClass('current');
		return false;
	}
});

$(window).on('load', function(){
	if ($('.tabType.fixedScroll').length > 0){
		var ftGnb = $('.tabType.fixedScroll'),
			ftGnbPo = ftGnb.offset().top;
		$(window).on('scroll', function(){
			var gnbMenu = $('.tabType.fixedScroll ul li'),
				tabH = $('.tabType.fixedScroll').outerHeight(),
				menuLeng = gnbMenu.length;
			for (var i=0; i<menuLeng; i++){
				if ($(gnbMenu.eq(menuLeng-1-i).find('a').attr('href')).offset().top - tabH - 1 < $(window).scrollTop()){
					gnbMenu.find('a').removeClass('current')
					gnbMenu.eq(menuLeng-1-i).find('a').addClass('current')
					break
				} else {
					gnbMenu.eq(0).find('a').addClass('current')
				}
			}
			if ($(this).scrollTop() > ftGnbPo){
				ftGnb.addClass('fixed');
			} else {
				ftGnb.removeClass('fixed');
			}
			if (!ftGnb.hasClass('fixed'))
			{
				ftGnbPo = ftGnb.offset().top;
			}
		});
	}
});


/**
DATE PICKER
**/
$(window).on('load', function(){
	$('.js_datepicker').datepicker({
		dateFormat: 'yy-mm-dd',
		monthNames: ['01','02','03','04','05','06','07','08','09','10','11','12'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		dayNames: ['일','월','화','수','목','금','토'],
		showMonthAfterYear:true,
		buttonImageOnly: true
	});
});


/**
inputType
**/
$(document).on('keyup', '.inp_text > input', function(){
	var _Value = $(this).val();
	var _parent = $(this).closest('.inp_text');

	if (_Value.length > 0) {
		_parent.find('.btn_clear').addClass('on');
	} else {
		_parent.find('.btn_clear').removeClass('on');
	}
});
$(document).on('click', '.inp_wrap .btn_clear', function(){
	var _parent = $(this).closest('.inp_text');

	_parent.find('input').val('');
	$(this).removeClass('on');
});


/**
selectType
**/
$(document).on('click', '.inp_select .title', function(){
	if ($(this).closest('.inp_select').hasClass('disabled')) {return false;}
	var target = $(this).closest('.inp_select');

	if (target.hasClass('open')) {
		target.find('.selList').hide().mCustomScrollbar("destroy");
		target.removeClass('open');
	} else {
		//tel407 20200419[수정] 다른 셀렉트 박스 닫기
		$('.selList').hide().mCustomScrollbar("destroy");
		$('.inp_select').removeClass('open');
		$(this).closest('.inp_select').addClass('open');
		$(this).siblings('.selList').show().mCustomScrollbar({
			axis:'y',
			scrollbarPosition:'outside',
			advanced:{autoExpandHorizontalScroll:true}
		});
	};
});

$(document).on('click', '.inp_select .selList label', function (){
	var disabled = $(this).prev('input').prop('disabled');
	if (disabled) {return false}

	var txt_value = $(this).html();
	var target = $(this).closest('.inp_select');
	target.find('.title').html(txt_value);
	target.find('input').prop('checked',false);
	$(this).prev('input').prop('checked',true);
	target.removeClass('open');
	target.find('.selList').hide();

	//tel407 20200419[수정] callback 함수 추가
	if(typeof inp_select_click_callback_prdDetail  === 'function'){
		inp_select_click_callback_prdDetail(this);
	}
});

$(window).on('load', function(){
	if ($('.inp_select').length > 0)
	{
		$('.inp_select input[type=radio]:checked').each(function(){
			var select_title = $(this).closest('li').find('label').html();
			$(this).closest('.selectBox').find('.title').text(select_title);
		});
	}
});

/**
textareaType
**/
$(window).on('load', function(){
	if ($('.inp_textArea').length > 0)
	{
		$(document).on('focus', '.inp_textArea textarea', function(){
			$(this).closest('.inp_textArea').addClass('focus');
		});
		$(document).on('blur', '.inp_textArea textarea', function(){
			$(this).closest('.inp_textArea').removeClass('focus');
		});

		for (var i = 0; i < $('.inp_textArea').length; i++)
		{
			$('.inp_textArea').eq(i).height($('.inp_textArea').eq(i).find('textarea').innerHeight());
			$('.inp_textArea').eq(i).find(".textarea-clone").css({'min-height' : $('.inp_textArea').eq(i).find('textarea').innerHeight()});
			var textareaLineHeight=parseInt($('.inp_textArea').eq(i).find('textarea').css("line-height"));

			$(".inp_textArea").eq(i).mCustomScrollbar({
				scrollInertia:0,
				theme:"dark-3",
				advanced:{autoScrollOnFocus:false},
				mouseWheel:{disableOver:["select","option","keygen","datalist",""]},
				keyboard:{enable:false},
				snapAmount:textareaLineHeight
			});
		}

		$(".inp_textArea textarea").on("keyup keydown",function(e){
			var textarea=$(this),textareaWrapper=$(this).closest(".inp_textArea"),textareaClone=$(this).closest(".inp_textArea").find(".textarea-clone");
			var $this=$(this),textareaContent=$this.val(),clength=textareaContent.length,cursorPosition=textarea.getCursorPosition();
			//console.log(textareaContent.length);
			textareaContent="<span>"+textareaContent.substr(0,cursorPosition)+"</span>"+textareaContent.substr(cursorPosition,textareaContent.length);
			textareaContent=textareaContent.replace(/\n/g,"<br />");
			textareaClone.html(textareaContent+"<br />");
			$this.css("height",textareaClone.innerHeight());
			//console.log(textareaClone.innerHeight());
			var textareaCloneSpan=textareaClone.children("span"),textareaCloneSpanOffset=0,
				viewLimitBottom=(parseInt(textareaClone.css("min-height")))-textareaCloneSpanOffset,viewLimitTop=textareaCloneSpanOffset,
				viewRatio=Math.round(textareaCloneSpan.innerHeight()+textareaWrapper.find(".mCSB_container").position().top);
			if(viewRatio>viewLimitBottom || viewRatio<viewLimitTop){
				if((textareaCloneSpan.innerHeight()-textareaCloneSpanOffset)>0){
					textareaWrapper.mCustomScrollbar("scrollTo",textareaCloneSpan.innerHeight()-textareaCloneSpanOffset-textareaLineHeight);
				}else{
					textareaWrapper.mCustomScrollbar("scrollTo","top");
				}
			}
		});

		$.fn.getCursorPosition=function(){
			var el=$(this).get(0),pos=0;
			if("selectionStart" in el){
				pos=el.selectionStart;
			}else if("selection" in document){
				el.focus();
				var sel=document.selection.createRange(),selLength=document.selection.createRange().text.length;
				sel.moveStart("character",-el.value.length);
				pos=sel.text.length-selLength;
			}
			return pos;
		}
	}
});

/* 이메일 직접입력 */
$(document).on('click', '.inp_email .inp_select input', function() {
	if ($(this).closest('ul').find('li').length == ($(this).closest('li').index()+1))
	{
		$(this).closest('.inp_email').addClass('active');
		$(this).closest('.inp_email').find('.email_input input').focus();
	}
});

$(document).on('click', '.inp_email2 .inp_select input', function() {
	if ($(this).closest('ul').find('li').length == ($(this).closest('li').index()+1))
	{
		$(this).closest('.inp_email2').find('.email_input input').prop('readonly', false).val('').focus();
	}
	else
	{
		$(this).closest('.inp_email2').find('.email_input input').prop('readonly', true).val($(this).closest('.inp_email2').find('button').text());
	}
});

/* 배송 메세지 */
$(document).on('click', '.inp_mesg .inp_select input', function() {
	if (1 == ($(this).closest('li').index()+1))
	{
		$(this).closest('.inp_mesg').find('.mesg_input').show();
	}
	else
	{
		$(this).closest('.inp_mesg').find('.mesg_input').hide();
	}
});


/**
POPUP
**/
var pop = new Array(), popObj, popReady = true;
var pop_idx = -1;

function showLayer(e,t) {
	if (popReady){
		popReady = false;
		pop.push(e);
		pop_idx++;
		popObj = $('#' + pop[pop_idx]);
		if (pop_idx > 0) {
			console.log(pop_idx);
			popObj.css('z-index', + (7000 + pop_idx));
		}
		// 토크 예외 처리
		if(pop[pop_idx] != "talk"){
			$('html').css({'overflow-y' : 'hidden', 'width' : 'auto'});
		}
		popObj.fadeIn(300, function(){
			popObj.addClass('open');
			popReady = true;
		});
		if (popObj.find('.reviewPopSlide').length > 0){
			/*slick destroy 추가*/
            if(popObj.find('.reviewPopSlide').is(".slick-slider")){
                popObj.find('.reviewPopSlide').slick('unslick');
            }
			popObj.find('.reviewPopSlide').slick({
				slidesToShow: 1,
				slidesToScroll: 1,
				arrows: true,
				dots: true,
				infinite: false
			});
		}
		imgCrop();
	}
	if (t)
	{
		popObj.find('.tabType li:nth-child('+t.charAt(t.length-1)+') a').click();
	}
}

function layerPopClose(callback){
	if (popReady){
		popReady = false;
		popObj = $('#' + pop[pop_idx]);
		pop.pop();
		pop_idx = pop_idx - 1;
		popReady = true;
		popObj.fadeOut(300, function(){
			if (pop_idx < 0) {
				$('html').css({'overflow-y' : '', 'margin-right' : '0', 'width' : '100%'});
			}
			popObj.removeClass('open');
			/*close 후 callback 추가*/
			if(typeof callback === 'function'){
				callback();
			}
		})
	}
};

$(document).on('click', '.lyPopArea', function(e){
	if (!$(this).hasClass('noClose'))
	{
		layerPopClose();
	}
});

$(document).on('click', '.lyPopArea .lyPop', function(e){
	e.stopPropagation();
});

/* 원본 이미지 보기 */
function imgViewPop(imgSrc){
	$('#imgViewPop .viewImg').attr('src',imgSrc);
	showLayer('imgViewPop');
}

/* 제품 플로팅 배너 */
function jsShopFloating(showTarget, myTarget){
	if(!stringjs.isnull(myTarget)){
		if (myTarget && $(myTarget).hasClass('on'))
		{
			showTarget = showTarget + '2';
			$(myTarget).removeClass('on');
		}
		else
		{
			$(myTarget).addClass('on');
		}
	}
	$('.'+showTarget).fadeIn(500, function(){
		$(this).delay(500).fadeOut(500);
	});
}


/**
GRADE
**/
$(window).on('load', function(){
	if ($('.js_rating').length > 0)
	{
		for (var i = 0; i < $('.js_rating').length; i++)
		{
			var rating_W = Math.round($('.js_rating').eq(i).width()) * ($('.js_rating').eq(i).find('span').text() * 0.2);
			$('.js_rating').eq(i).find('span').width(rating_W);
		}
	}
});
$(document).on('click', '.js_userRating .userRatingBox button', function(e){
	var resultRtg = Number($(this).text()),
		rating_W = Math.round($(this).parents('.js_rating').width()) * (resultRtg * 0.2);
	$(this).closest('.userRatingBox').siblings('span').width(rating_W).text(resultRtg)
	$('.starResult').val(resultRtg);
})


/**
SLIDE
**/
$(window).on('load', function(){
	if ($('.js_pdtSlide').length > 0){
		$('.js_pdtSlide .js_pdtSlideImg').slick({
			slidesToShow: 1,
			slidesToScroll: 1,
			arrows: false,
			infinite: false,
			swipe : false,
			asNavFor: '.js_pdtSlide .js_pdtSlideImgThumb'
		});
		$('.js_pdtSlide .js_pdtSlideImgThumb').slick({
			slidesToShow: 4,
			slidesToScroll: 1,
			asNavFor: '.js_pdtSlide .js_pdtSlideImg',
			dots: false,
			infinite: false,
			swipe : false,
			focusOnSelect: true
		});
	}
});


/**
SCROLL
**/
$(window).on('load', function(){
	if ($('.hScroll').length > 0){
		hScroll();
	}
	if ($('.vScroll').length > 0){
		vScroll();
	}
});

function hScroll(){
	$('.hScroll').mCustomScrollbar("destroy");
	$('.hScroll').mCustomScrollbar({
		axis:'x',
		//autoHideScrollbar:true,
		scrollbarPosition:'outside',
		advanced:{autoExpandHorizontalScroll:true}

	});
}

function vScroll(){
	$('.vScroll').mCustomScrollbar("destroy");
	$('.vScroll').mCustomScrollbar({
		axis:'y',
		//autoHideScrollbar:true,
		scrollbarPosition:'outside',
		advanced:{autoExpandHorizontalScroll:true}
	});
}


/**
IMAGE CROP
**/
$(window).on('load', function(){
	if ($('.imgBox').length > 0){
		imgCrop();
	}
});

function imgCrop(){
	$('.imgBox').each(function() {
		//set size
		var th = $(this).height(),//box height
			tw = $(this).width(),//box width
			im = $(this).children('img'),//image
			ih = im.height(),//inital image height
			iw = im.width();//initial image width
		if (ih>iw) {//if portrait
			im.addClass('ww').removeClass('wh');//set width 100%
		} else {//if landscape
			im.addClass('wh').removeClass('ww');//set height 100%
		}
	});
}

/**
ACORDION
**/
/* 테이블 */
function tblAco(e){
	if ($(e).closest('tr').next().css('display') == 'none'){
		$(e).addClass('on')
		$(e).closest('tr').find('.js_arrow, .js_ListTtl').addClass('on')
		$(e).closest('tr').next().css('display', 'table-row')
		$(e).closest('tr').next().find('.js_replyCtt').stop().slideDown(500, function(){
			/* [클릭시 스크롤이동] */
			if ($(e).closest('tr').hasClass('noScrolling')){
				return false;
			} else {
				/* [제품상세일경우] */
				if ($('.product_detail').length > 0){
					$('body,html').animate({
						scrollTop: $(e).closest('tr').offset().top - 48
					}, '500');
				} else {
					$('body,html').animate({
						scrollTop: $(e).closest('tr').offset().top - 50
					}, '500');
				}			
			}
		})
	} else {
		$(e).removeClass('on')
		$(e).closest('tr').find('.js_arrow, .js_ListTtl').removeClass('on')
		$(e).closest('tr').next().find('.js_replyCtt').stop().slideUp(500, function(){
			$(this).parents('tr').css('display', 'none')
		})
	}
	imgCrop();
}
$(document).on('click', '.js_ListTtl', function(){
	/* [비밀글이 있을 경우] */
	if ($(this).closest('tr').hasClass('lock')){
		if ($(this).closest('tr').hasClass('userOn')){
			$(this).closest('.js_tblList').find('.js_ListTtl').removeClass('on')
			$(this).closest('.js_tblList').find('.js_arrow').removeClass('on')
			$(this).closest('.js_tblList').find('.js_replyCtt').stop().slideUp(500, function(){
				$(this).closest('tr').css('display', 'none')
			})
			tblAco(this)
		} else {
			alert('비밀글입니다.')
		}
	} else {
		$(this).closest('.js_tblList').find('.js_ListTtl').removeClass('on')
		$(this).closest('.js_tblList').find('.js_arrow').removeClass('on')
		$(this).closest('.js_tblList').find('.js_replyCtt').stop().slideUp(500, function(){
			$(this).closest('tr').css('display', 'none')
		})
		tblAco(this)
	}
});
$(document).on('click', '.js_acoLink', function(){
	/* [오픈시 다른 항목 닫힘] */
	if ($(this).closest('.js_acoBox').hasClass('type02')){
		$(this).closest('.js_acoBox').find('.js_acoList').removeClass('on');
		$(this).closest('.js_acoBox').find('.js_acoCon').stop().slideUp(300);
	}
	/* [해당 항목만 열고 닫기] */
	if ($(this).closest('.js_acoList').find('.js_acoCon').css('display') == 'block'){
		$(this).closest('.js_acoList').removeClass('on');
		$(this).closest('.js_acoList').find('.js_acoCon').stop().slideUp(300)
	} else {
		$(this).closest('.js_acoList').addClass('on');
		$(this).closest('.js_acoList').find('.js_acoCon').stop().slideDown(300)
	}
});


/**
리뷰 더보기
**/
$(window).on('load', function(){
	if ($('.reviewMore').length > 0 && $('.product_detail').length < 1){
		//alert($('.product_detail').length);
		reviewMoreChk();
	}
});

var tabChk = false;

function reviewMoreChk(tabType){
	if (!tabChk)
	{
		$('.reviewMore').each(function(){
			if (!$(this).siblings('.reviewCtt').hasClass('heightOn'))
			{
				if ($(this).siblings('.reviewCtt').height() > 60){
					console.log($(this).siblings('.reviewCtt').hasClass('heightOn'))
					$(this).show();
					$(this).siblings('.reviewCtt').css({
						height: $(this).siblings('.reviewCtt').height()
					}).addClass('heightOn');
				} else {
					$(this).hide();
				}
			}
		});
	}
	if (tabType)
	{
		tabChk = true;
	}
}

$(document).on('click', '.reviewMore', function(e){
	if ($(this).siblings('.reviewCtt').hasClass('heightOn')){
		$(this).addClass('on').html('닫기')
		$(this).siblings('.reviewCtt').removeClass('heightOn')
	} else {
		$(this).removeClass('on').html('더보기')
		$(this).siblings('.reviewCtt').addClass('heightOn')
	}
})


/**
PRODUCT COUNT
**/
function productCountMinus(e,_callback){
	var buyInput = $(e).siblings('input[type="text"]'),
		buyCount = buyInput.attr('value');

	if (buyCount > 2){
		buyCount--;
		buyInput.attr('value', buyCount);
		if (typeof _callback == "function") {
			_callback();
		}
	} else {
		buyCount--;
		buyInput.attr('value', buyCount);
		$(e).prop('disabled', true);
		if (typeof _callback == "function") {
			_callback();
		}
	}
}
function productCountPlus(e,_callback){
	var buyInput = $(e).siblings('input[type="text"]'),
		buyCount = buyInput.attr('value'),
		buyDataMax = buyInput.attr('data-max');

	if (!buyDataMax){
		buyCount++;
		buyInput.attr('value', buyCount);
		if (typeof _callback == "function") {
			_callback();
		}
	} else {
		if (buyCount < Number(buyDataMax)){
			buyCount++;
			buyInput.attr('value', buyCount);
			if (typeof _callback == "function") {
				_callback();
			}
		} else {
			alert('일일 최대 구매 수량을 초과하였습니다.');
		}
	}
	if (buyCount >= 2){
		$(e).siblings('.js_pdtCountMinus').prop('disabled', false);
	}
}
$(window).on('load', function(){
	if ($('.js_pdtCount').length > 0)
	{
		for (var i = 0; i < $('.js_pdtCount').length; i++)
		{
			if ($('.js_pdtCount').eq(i).find('input[type="text"]').attr('value') == 1)
			{
				$('.js_pdtCount').eq(i).find('.js_pdtCountMinus').prop('disabled', true);
			}
		}
	}
});



/**
리뷰 작성
**/
function coachMarkClose(e){
	$(e).closest('.coachMarkBox').stop().fadeOut(300)
}
$(window).on('load', function(){
	if ($('.coachMarkBox').length > 0){
		$('.coachMarkBox').closest('.lyPopArea').scroll(function(){
			if ($(this).scrollTop() > $('.coachMarkArea').position().top - $(this).outerHeight() + $('.coachMarkArea').outerHeight()){
				$(this).find('.coachMarkBox').delay('2000').fadeOut(300)
			}
		})
	}
});


/**
상품 상세
**/
$(window).on('load', function(){
	/* 상단 플로팅 배너 */
	$('.flBannerClose').click(function(){
		//24시간
		localstoragejs.sessionSet("loginFlBanner","Y",1440);
		$('.flBanner').hide()
	})
	/* 바로구매 툴바 */
	if ($('.pdtToolbar').length > 0){
		var TbOffTop = $('.pdtDetailBody').offset().top;
		$(window).scroll(function(){
			if (!$('.pdtToolbar').hasClass('active')){				
				if ($(this).scrollTop() > (TbOffTop-$(window).height())){
					$('.pdtToolbar').stop().animate({'bottom' : '-1px'},200);
				} else {
					$('.pdtToolbar').stop().animate({'bottom' : '-41px'},200);
				}
			}
		});
	}
});
$(document).on('click', '.tbPdtBtn', function(e){
	if(!$(this).closest('.pdtToolbar').hasClass('active')){
		$(this).next().stop().slideDown(300)
		$(this).closest('.pdtToolbar').addClass('active')
		$(this).addClass('on').find('.arrow').addClass('on')
	}else{
		$(this).next().stop().slideUp(300)
		$(this).closest('.pdtToolbar').removeClass('active')
		$(this).removeClass('on').find('.arrow').removeClass('on')
	}
})
$(window).on('load', function(){
	if ($('.reviewGrade').length > 0){
		$(window).scroll(function(){
			if ($(this).scrollTop() > $('.reviewGrade').offset().top - $('body, html').outerHeight() + $('.reviewGrade').outerHeight()){
				$('.statBar').each(function(){
					$(this).find('span').css('width', $(this).next('.statData').text())
				})
			}
		})
	}
});


/**
주문 플로팅
**/
function orderFloating(){
	var fixedTop = 10;
	if ($('#wrap').hasClass('cart'))
	{
		fixedTop = 60;
	}
	if ($('.order_floating').length > 0)
	{
		if ($(window).scrollTop() > $('.order_wrap .js_orderFloating').offset().top - fixedTop)
		{
			$('.order_wrap .order_floating').removeClass('bFixed').addClass('fixed');
			if ($(window).scrollTop() > $('.order_wrap .js_orderFloating').offset().top + $('.order_wrap .js_orderFloating').height() - $('.order_wrap .order_floating').height()  - fixedTop)
			{
				$('.order_wrap .order_floating').removeClass('fixed').addClass('bFixed');
			}
		}
		else
		{
			$('.order_wrap .order_floating').removeClass('fixed bFixed');
		}
	}
}


/**
ORDER
**/
/* 결제방법 */
$(document).on('click', '.order_wrap input[name="sttlmethod"]', function(){
	var target = $(this).attr('id').replace('payment_list','payment_info');
	order_payment(target);
});

$(window).on('load', function(){
	if ($('.order_wrap input[name="sttlmethod"]:checked').attr('id'))
	{
		var target = $('.order_wrap input[name="sttlmethod"]:checked').attr('id').replace('payment_list','payment_info');
		order_payment(target);
	}
});

function order_payment(target){
	$('.order_wrap .info_wrap').hide();
	$('.order_wrap .' + target).show();
}

/* 쿠폰 */
function order_couponPop(){
	$('.order_coupon .order_cpBtn').toggleClass('on');
	$('#coupon_layer').stop().fadeToggle(200);
	if ($('#coupon_layer2').is(":visible"))
	{
		order_couponPop2();
	}
};
function order_couponPop2(){
	$('.order_coupon .order_cpBtn2').toggleClass('on');
	$('#coupon_layer2').stop().fadeToggle(200);
};

/* 배송지 선택 */
$(document).on('click', '.deliveryMore a', function(){
	var targetList = $(this).closest('.delivery_list');
	var targetBtn = $(this).closest('.deliveryMore');
	targetList.addClass('all');
	targetBtn.hide();
	return false;
});


/**
마이페이지
**/
/* 휴대폰번호 변경 */
$(document).on('click', '.myCon .btnPhoneChange', function(){
	var toggleTxt = $(this).find('span').text();
	$(this).find('span').text(toggleTxt == "변경" ? "취소" : "변경");
	$('.myCon .phoneCert').stop().slideToggle(500);
});

/* 주문내역 PROGRESS */
$(window).on('load', function(){
	if ($('.myCon .myOrderProgress').length > 0)
	{
		if ($('.myOrderProgress .on').index() > 0)
		{
			$('.myOrderProgress .progressWrap').addClass('step'+($('.myOrderProgress .on').index()+1));
			$('.myOrderProgress span').one("webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend", function() {
				progressActive();
			});
		}
		else
		{
			progressActive();
		}
	}
});

function progressActive(){
	$('.myOrderProgress .on').addClass('active');
}

/**
HEADER
**/
/* 검색 */
var jsSearchNum = 0;

function jsSearchRoll(){
	$('.jsSearchTxt .rollCon').slick({
		pauseOnHover:false,
		autoplay:true,
		autoplaySpeed:2000,
		infinite:true,
		speed:300,
		vertical:true,
		arrows:false,
		initialSlide:jsSearchNum
	});
}
jsSearchRoll();

$(document).on('click', '.jsSearchTxt', function(){
	$('#headerSearch').focus();
});

$(document).on('focus', '#headerSearch', function(){
	$('#loginLayer').hide();
	$('.latestPdtH').stop().slideUp(300);
	$('.jsSearchTxt').hide();
	if ($('.jsSearchTxt .rollCon .txt').length > 0)
	{
		jsSearchNum = $('.jsSearchTxt .rollCon').slick('slickCurrentSlide');
		$('.jsSearchTxt .rollCon').slick('slickPause');
		$('.jsSearchTxt .rollCon').find('.slick-current').removeClass('slick-active');
	}

	if ($('#headerSearch').val() == '')
	{
		$('#header .searchInfo').show();
		$('#header .searchH .autoWord').hide();
	}
	else
	{
		$('#header .searchH .autoWord').show();
		$('#header .searchInfo').hide();
	}
});

$(document).on('keyup', '.searchWrap', function(){
	if ($('#headerSearch').val() == '')
	{
		$('#header .searchInfo').show();
		$('#header .searchH .autoWord').hide();
	}
	else
	{
		$('#header .searchH .autoWord').show();
		$('#header .searchInfo').hide();
	}
});

$(document).on('blur', '.searchWrap', function(){
	if ($('#headerSearch').val() == '' && $('.jsSearchTxt .rollCon .txt').length > 0)
	{
		$('.jsSearchTxt .rollCon').find('.slick-current').addClass('slick-active');
		$('.jsSearchTxt .rollCon').slick('slickPlay');
		$('.jsSearchTxt').show();
	}
	$('#header .searchInfo, #header .searchH .autoWord').hide();
});

$(document).on('mousedown', '#header .searchInfo, #header .searchH .autoWord, #header .searchWrap button', function(e){
	e.preventDefault();
});

$(document).on('focus', '.gnbFloating .searchBox', function(){
	if ($('#floatingSearch').val() == '')
	{
		$('.gnbFloating .autoWord').hide();
	}
	else
	{
		$('.gnbFloating .autoWord').show();
	}
});

$(document).on('keyup', '.gnbFloating .searchBox', function(){
	if ($('#floatingSearch').val() == '')
	{
		$('.gnbFloating .autoWord').hide();
	}
	else
	{
		$('.gnbFloating .autoWord').show();
	}
});

$(document).on('blur', '.gnbFloating .searchBox', function(){
	$('#header .searchInfo, .gnbFloating .autoWord').hide();
});

$(document).on('mousedown', '.gnbFloating .autoWord', function(e){
	e.preventDefault();
});

/* GNB 카테고리 */
$(document).on('mouseenter', '#header .gnbCate > dt, #header .gnbCate > dd', function(){
	if ($('#header .gnbCate > dd').find('dl').length > 0)
	{
		$('#header .gnbCate').addClass('open');
		$('#header .gnbCate > dd').stop().slideDown(300);
		$('.jsCateBanner').slick('setPosition');
	}
});

$(document).on('mouseleave', '#header .gnbCate > dt, #header .gnbCate > dd', function(){
	if ($('#header .gnbCate > dd').find('dl').length > 0)
	{
		$('#header .gnbCate').removeClass('open');
		$('#header .gnbCate > dd').stop().slideUp(300);
	}
});

$('.jsCateBanner .slick-arrow').hide();

$(document).on('mouseenter', '.jsCateBanner', function(){
	$('.jsCateBanner .slick-arrow').stop().fadeIn(200);
});

$(document).on('mouseleave', '.jsCateBanner', function(){
	$('.jsCateBanner .slick-arrow').stop().fadeOut(200);
});

/* 로그인 팝업 */
function loginLayer(){
	$('#loginLayer').toggle();
	$('.latestPdtH').stop().slideUp(300);
}

/* 최근본상품 팝업 */
function latestLayer(){
	$('.latestPdtH').stop().slideToggle(300);
	if ($('.latestPdtH .pdtCon').length > 0)
	{
		$('.latestPdtH .list_box').slick('setPosition');
	}
}



/* GNB FIXED */
function gnbFixed(){
	if ($('#header .gnbWrap').length > 0)
	{
		if ($('#header .gnbWrap').offset().top < $(window).scrollTop())
		{
			$('#header .gnbWrap .gnb').addClass('fixedType');
		}
		else
		{
			$('#header .gnbWrap .gnb').removeClass('fixedType');
		}
	}
}

function quickMenu(){
	var winS = $(window).scrollTop();
	var gnb = $(".gnb").height();
	var con = $("#container").offset().top;
	var h;
	if($(".mainPt").length > 0){
		var main = $(".mainPt").offset().top;
		con = main;
	}else{
		con += 90;
	}
	h = con;

	if(winS >= h - gnb -10) { //downscroll
		$('#quick .quickArea').css({'position':'absolute', 'top':'60px'});
	} else { //upscroll
		$('#quick .quickArea').css({'position':'absolute', 'top':h});
	}
	
	if(winS >= h - gnb -10) { //downscroll
		if( winS >= h - gnb -10){
			$('#quick').css({'position':'fixed'});
		} else {
			$('#quick').css({'position':'absolute'});
		}
	} else { //upscroll
		if( winS < h - gnb -10){
			$('#quick').css({'position':'absolute'});
		} else {
			$('#quick').css({'position':'fixed'});
		}
	}
}


// 쿠키 설정
function setCookie(cookie_name, value, days) {
	const exdate = new Date();
	exdate.setDate(exdate.getDate() + days); // 설정 일수만큼 현재시간에 만료값으로 지정
	const cookie_value = escape(value) + ((days == null) ? '' : ';path=/;expires=' + exdate.toUTCString());
	document.cookie = cookie_name + '=' + cookie_value;
}
// 쿠키 가져오기
function getCookie(cookie_name) {
	var x, y; var val = document.cookie.split(';');
	for (var i = 0; i < val.length; i++) {
		x = val[i].substr(0, val[i].indexOf('='));
		y = val[i].substr(val[i].indexOf('=') + 1);
		x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
		if (x == cookie_name) {
			return unescape(y); // unescape로 디코딩 후 값 리턴
		}
	}
}
// 쿠키 추가
function addCookie(id) {
	var items = getCookie('productItems'); // 이미 저장된 값을 쿠키에서 가져오기
	var maxItemNum = 5; // 최대 저장 가능한 아이템개수
	var expire = 1; // 쿠키값을 저장할 기간
	if (items) {
		var itemArray = items.split(',');
		if (itemArray.indexOf(id) != -1) { // 이미 존재하는 경우 순서 변경
			itemArray = itemArray.filter(function(item) {return item !== id});	// 존재하는 애 삭제
			itemArray.unshift(id);	// 다시 배열 맨 앞 추가
			items = itemArray.join(',');
			setCookie('productItems', items, expire);
		} else { // 새로운 값 저장 및 최대 개수 유지하기
			itemArray.unshift(id);
			if (itemArray.length > maxItemNum )
			itemArray.length = 5; items = itemArray.join(',');
			setCookie('productItems', items, expire);
		}
	} else { // 신규 id값 저장하기
		setCookie('productItems', id, expire);
	}
}
