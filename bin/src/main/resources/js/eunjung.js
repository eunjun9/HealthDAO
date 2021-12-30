/* 회원가입 */
function selectAll(selectAll)  {
    const checkboxes = document.querySelectorAll('.js_tblList input[type="checkbox"]');
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = selectAll.checked
    })
}

$(document).ready(function() {   
    $('.js_tblList input[type="checkbox"]').click(function() {
        let total = $('.js_tblList input[type="checkbox"]').length;
        let checked = $('.js_tblList input[type="checkbox"]:checked').length;
        
        if(total != checked) $("#inp_check1_1").prop("checked", false);
        else $("#inp_check1_1").prop("checked", true); 
    });

    $('.inp_gender .btn').click(function(){
        $('.inp_gender .btn').removeClass("active");
        $(this).addClass("active");

        if($(this).hasClass("btnMale")) {
            $("#male").prop("checked", true);
        } else {
            $("#female").prop("checked", true);
        }
    });
});

/* 아이디/비밀번호 찾기 */
document.getElementById('ht-custom2').addEventListener('click', function(e) {
    if ( ht_init ) {
        setIframe(frm);
        ht_init = false;
    }
    if ( document.getElementById('ht-wrapper') ) {
        document.getElementById('ht-wrapper').classList.add('ht-open');
    }
    document.getElementById('ht-custom').style.display = 'none';
    if ( window.innerWidth <= 767 ) {
        function calcVH() {
            var vH = Math.min(document.documentElement.clientHeight, window.innerHeight || 0);
            document.getElementById("ht-wrapper").setAttribute("style", "height:" + vH + "px;");
        }

        calcVH();
        window.addEventListener('onorientationchange', calcVH, true);
        window.addEventListener('resize', calcVH, true);
        document.documentElement.style.cssText = "position: fixed; overflow:hidden;";
        document.body.style.cssText = "position: fixed; overflow:hidden";
    }
});

$(document).ready(function() {
	setFindIDPW($(':checked[name=find_id_type]')[0]);
	setFindIDPW($(':checked[name=find_pw_type]')[0]);
});