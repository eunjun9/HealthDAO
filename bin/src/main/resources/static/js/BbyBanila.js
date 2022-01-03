
$(document).on('click', '.BbyTab a', function(){
	$('html, body').animate({'scrollTop' : $($(this).attr('href')).offset().top - 48},500);
	return false;
});

$(function(){
	var Bby_ftGnb = $('.BbyTab'),
		Bby_ftGnbPo = Bby_ftGnb.offset().top;
	$(window).on('scroll', function(){
		var Bby_gnbMenu = $('.BbyTab ul li'),
			Bby_tabH = $('.BbyTab').outerHeight(),
			Bby_menuLeng = Bby_gnbMenu.length;
		for (var i=0; i<Bby_menuLeng; i++){
			if (Math.floor($('#wrap').outerHeight(true)-$(window).height()) <= $(window).scrollTop()){
				Bby_gnbMenu.find('a').removeClass('current')
				Bby_gnbMenu.eq(Bby_menuLeng-1).find('a').addClass('current')
			} else if ($(Bby_gnbMenu.eq(Bby_menuLeng-1-i).find('a').attr('href')).offset().top - Bby_tabH - 1 < $(window).scrollTop()){
				Bby_gnbMenu.find('a').removeClass('current')
				Bby_gnbMenu.eq(Bby_menuLeng-1-i).find('a').addClass('current')
				break
			} else {
				Bby_gnbMenu.eq(0).find('a').addClass('current')			
			}
		}
	});
});