"use strict";

$(document).on('click', '.banilaCo .conBox .txt, .banilaCo .conBox .img,  .banilaCo .conBox .video', function(){
	$(this).closest('.conBox').toggleClass('open');
	return false;
});

var indicatorW = new Array;
var indicatorPdt = new Array;

$('.banilaCo .jsPdt').each(function(i){
	indicatorPdt[i] = Math.ceil($(this).find('.pdtList').length / 4) - 1;
	indicatorW[i] = 100 / (Math.ceil($(this).find('.pdtList').length / 4));
	$(this).siblings('.indicator').find('span').css({'width' : indicatorW[i] + '%'})
});

$('.banilaCo .jsPdt').slick({
	infinite:false,
	slidesToShow:4,
	slidesToScroll:4,
	draggable:false,
	prevArrow:'<button type="button" class="slick-prev hide"><span>Previous</span></button>',
	nextArrow:'<button type="button" class="slick-next show"><span>Next</span></button>'
});

$('.banilaCo .jsPdt').on('beforeChange', function(event, slick, currentSlide, nextSlide){
	$(this).siblings('.indicator').find('span').css({'left' : indicatorW[$(this).index('.banilaCo .jsPdt')] * Math.ceil(nextSlide / 4) + '%'});

	if (indicatorPdt[$(this).index('.banilaCo .jsPdt')] == nextSlide / 4)
	{
		$(this).find('.slick-prev').removeClass('hide').addClass('show');
		$(this).find('.slick-next').removeClass('show').one("webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend", function() {
			if (!$(this).hasClass('show'))
			{
				$(this).addClass('hide');
			}
		});
	}
	else if (0 == nextSlide)
	{
		
		$(this).find('.slick-next').removeClass('hide').addClass('show');
		$(this).find('.slick-prev').removeClass('show').one("webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend", function() {
			if (!$(this).hasClass('show'))
			{
				$(this).addClass('hide');
			}
		});
	}
	else
	{
		$(this).find('.slick-prev, .slick-next').removeClass('hide').addClass('show');
	}
});

var indicatorVisual = new Array;

$('.banilaCo .jsVisual').each(function(i){
	indicatorVisual[i] = $(this).find(' > div').length -1;
});

$('.banilaCo .jsVisual').slick({
	infinite:false,
	draggable:false,
	prevArrow:'<button type="button" class="slick-prev hide"><span>Previous</span></button>',
	nextArrow:'<button type="button" class="slick-next show"><span>Next</span></button>'
});

$('.banilaCo .jsVisual').on('beforeChange', function(event, slick, currentSlide, nextSlide){
	$(this).find('img').removeClass('hover');

	if (indicatorVisual[$(this).index('.banilaCo .jsVisual')] == nextSlide)
	{
		$(this).find('.slick-prev').removeClass('hide').addClass('show');
		$(this).find('.slick-next').removeClass('show').on("webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend", function() {
			$(this).addClass('hide')
		})
	}
	else if (0 == nextSlide)
	{
		$(this).find('.slick-next').removeClass('hide').addClass('show');
		$(this).find('.slick-prev').removeClass('show').on("webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend", function() {
			$(this).addClass('hide')
		})
	}
	else
	{
		$(this).find('.slick-prev, .slick-next').removeClass('hide').addClass('show');
	}
});

$(document).on('mouseenter', '.banilaCo .jsVisual .img', function(){
	$(this).find('img').addClass('hover');
});
$(document).on('mouseleave', '.banilaCo .visual', function(){
	$(this).find('img').removeClass('hover');
});

$(document).on('mouseenter', '.banilaCo .conBox .slick-arrow', function() {
	if ($(this).hasClass('slick-prev'))
	{
		$(this).find('span').stop().animate({'left' : '-20px'}, 150, function(){
			$(this).css({'left' : '60px'}).stop().animate({'left' : '20px'}, 150);
		});
	}
	else
	{
		$(this).find('span').stop().animate({'right' : '-20px'}, 150, function(){
			$(this).css({'right' : '60px'}).stop().animate({'right' : '20px'}, 150);
		});
	}
});