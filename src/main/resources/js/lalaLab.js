"use strict";

$(function(){
	$('.conBox1').addClass('show');
	lalaScroll();
});

$(window).on('scroll', function(){
	lalaScroll();
});

function lalaScroll(){
	if ($('.conBox3').offset().top - $('.lalaLab .brandLogo').position().top - 14 < $(window).scrollTop())
	{
		$('.lalaLab .brandLogo').addClass('colorB');
	}
	else
	{
		$('.lalaLab .brandLogo').removeClass('colorB');
	}

	if ($('.conBox3').offset().top - $(window).height() + 30 < $(window).scrollTop())
	{
		$('.downArr').addClass('colorB');
	}
	else
	{
		$('.downArr').removeClass('colorB');;
	}

	if ($('.conBox3').offset().top - $('.conBox1 .tit').position().top - 91 < $(window).scrollTop())
	{
		$('.conBox1 .tit p').eq(0).addClass('colorB');
		$('.conBox3').addClass('show');
	}
	else
	{
		$('.conBox1 .tit p').eq(0).removeClass('colorB');
		$('.conBox3').removeClass('show');
	}

	if ($('.conBox3').offset().top - $('.conBox1 .tit').position().top - 211 < $(window).scrollTop())
	{
		$('.conBox1 .tit p').eq(1).addClass('colorB');
	}
	else
	{
		$('.conBox1 .tit p').eq(1).removeClass('colorB');
	}

	if ($('.conBox3').offset().top - $('.conBox1 .tit').position().top - 331 < $(window).scrollTop())
	{
		$('.conBox1 .tit p').eq(2).addClass('colorB');
	}
	else
	{
		$('.conBox1 .tit p').eq(2).removeClass('colorB');
	}

	if ($('.conBox4 .conM').offset().top - ($(window).height() * 0.6) < $(window).scrollTop())
	{
		$('.conBox4 .conM').addClass('active');
	}
	else
	{
		$('.conBox4 .conM').removeClass('active');
	}

	if ($('.conBox4 .conM').offset().top - ($(window).height() * 0.2) < $(window).scrollTop())
	{
		$('.conBox4 .conM').addClass('active2');
	}
	else
	{
		$('.conBox4 .conM').removeClass('active2');
	}

	if ($('.conBox4').offset().top + 400 < $(window).scrollTop() && $('.conBox5').offset().top > $(window).scrollTop())
	{
		var textMotion = ($(window).scrollTop() - ($('.conBox4').offset().top + 400)) / (($('.conBox5').offset().top - ($('.conBox4').offset().top + 400)) / 100);
		$('.conBox4 .txtMotion img').css({'left' : (80 - textMotion) + '%', 'opacity' : textMotion / 60});
	}

	if ($('.conBox5').offset().top - 48 < $(window).scrollTop())
	{
		$('.conBox5').addClass('fixed');
	}
	else
	{
		$('.conBox5').removeClass('fixed');
	}

	if ($('.infoTit').offset().top < $('.conBox5 .bg2').offset().top)
	{
		$('.conBox5 .indicator em').text('1');
		$('.conBox5 .indicator .btn_up').hide();
		$('.conBox5 .indicator .btn_down').show();
		$('.conBox5 .pdt1').show();
		$('.conBox5 .pdt2').hide();
		$('.conBox5 .pdt3').hide();
		$('.conBox5 .pdt4').hide();
	}
	if ($('.infoTit').offset().top > $('.conBox5 .bg2').offset().top && $('.infoTit').offset().top <= $('.conBox5 .bg3').offset().top)
	{
		$('.conBox5 .indicator em').text('2');
		$('.conBox5 .indicator .btn_up').show();
		$('.conBox5 .indicator .btn_down').show();
		$('.conBox5 .pdt1').hide();
		$('.conBox5 .pdt2').show();
		$('.conBox5 .pdt3').hide();
		$('.conBox5 .pdt4').hide();
	}
	if ($('.infoTit').offset().top > $('.conBox5 .bg3').offset().top && $('.infoTit').offset().top <= $('.conBox5 .bg4').offset().top)
	{
		$('.conBox5 .indicator em').text('3');
		$('.conBox5 .indicator .btn_up').show();
		$('.conBox5 .indicator .btn_down').show();
		$('.conBox5 .pdt1').hide();
		$('.conBox5 .pdt2').hide();
		$('.conBox5 .pdt3').show();
		$('.conBox5 .pdt4').hide();
	}
	if ($('.infoTit').offset().top > $('.conBox5 .bg4').offset().top)
	{
		$('.conBox5 .indicator em').text('4');
		$('.conBox5 .indicator .btn_up').show();
		$('.conBox5 .indicator .btn_down').hide();
		$('.conBox5 .pdt1').hide();
		$('.conBox5 .pdt2').hide();
		$('.conBox5 .pdt3').hide();
		$('.conBox5 .pdt4').show();
	}
}

$(document).on('click', '.conBox5 .btn_up', function(){
	var pageNum = parseInt($('.conBox5 .indicator em').text()) - 1;
	$('html, body').animate({'scrollTop' : $('.conBox5 .bg' + pageNum).offset().top - 48});
});

$(document).on('click', '.conBox5 .btn_down', function(){
	var pageNum = parseInt($('.conBox5 .indicator em').text()) + 1;
	$('html, body').animate({'scrollTop' : $('.conBox5 .bg' + pageNum).offset().top - 48});
});