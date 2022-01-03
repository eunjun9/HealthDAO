var s = skrollr.init({
	edgeStrategy: 'set',
	easing: { // 움직임?
		WTF: Math.random,
		inverted: function(p) {
			return 1-p;
		}
	}
});

var curly_viewImgTarget = 0;
var curly_viewImgLg = setInterval(curly_viewImg, 1000)
function curly_viewImg(){
	if (curly_viewImgTarget < $('#curly_content01 .curly_viewImg').length-1){
		$('#curly_content01 .curly_viewImg').removeClass('on')
		$('#curly_content01 .curly_viewImg').eq(curly_viewImgTarget).addClass('on')
		curly_viewImgTarget++;
	} else {
		$('#curly_content01 .curly_viewImg').removeClass('on')
		$('#curly_content01 .curly_viewImg').eq(curly_viewImgTarget).addClass('on')		
		curly_viewImgTarget=0;
	}
}

$('#curly_progress_circle').circleProgress({
	startAngle: +Math.PI / -2,
	size:60,
	emptyFill: '#2f2725', // 바탕 색
	thickness: 5, // 굵기
	fill: 'rgba(0,0,0,0)', // 채워지는 색
	animation:{ duration: 100 }, // 움직이는 시간
	lineCap: 'round' // 테두리
});

$('.curly_visualBox > div').each(function(){
	$(this).css('top', $(this).index() * 100 + '%')
})

$('.curly_infoBox > div').each(function(){
	$(this).css('bottom', $(this).index() * 100 + '%')
})

$(function(){
	$('.curly_videoCtt').on('pause', function() {
		if ($(this).get(0).currentTime >= $(this).get(0).duration){
			$(this).closest('.curly_videoBox').removeClass('play');
			$(this).closest('.curly_videoBox').removeClass('pause');
			$(this).closest('.curly_videoBox').addClass('end');
		} else {
			$(this).closest('.curly_videoBox').removeClass('play');
			$(this).closest('.curly_videoBox').addClass('pause');
			$(this).closest('.curly_videoBox').removeClass('end');	
		}
	});
});

function curly_videoPlay (e){
	$(e)[0].play()
	$(e).closest('.curly_videoBox').addClass('play');
	$(e).closest('.curly_videoBox').removeClass('pause');
	$(e).closest('.curly_videoBox').removeClass('end');
}

function curly_videoPause (e){
	$(e)[0].pause()
	$(e).closest('.curly_videoBox').removeClass('play');
	$(e).closest('.curly_videoBox').addClass('pause');
	$(e).closest('.curly_videoBox').removeClass('end');
}

var videoOnoff = true
$(window).on('scroll', function(){
	// 이미지 스와핑
	if ($(this).scrollTop() > 0){
		clearInterval(curly_viewImgLg);
	} else {
		clearInterval(curly_viewImgLg);
		curly_viewImgLg = setInterval(curly_viewImg, 500)
	}
	// 프로그래스 움직임
	$('#curly_progress_circle').circleProgress('value', $(window).scrollTop() / 8711);
	// 프로그래스 색변경
	if ( 4400<=$(this).scrollTop() && $(this).scrollTop()<=(5600 - $(window).height()) ){ 
		$('#curly_progress_circle').addClass('on').circleProgress({
			emptyFill: '#fff' // 바탕 색
		});
	} else {
		$('#curly_progress_circle').removeClass('on').circleProgress({
			emptyFill: '#2f2725' // 바탕 색
		});
	}
	// 영상재생
	if ( 4400<=$(this).scrollTop() && $(this).scrollTop()<=5200 ){
		//console.log('비디오실행')
		curly_videoPlay('.curly_videoCtt')
	} else {
		//console.log('비디오중지')
		curly_videoPause('.curly_videoCtt')
	}
});