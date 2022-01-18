/* 로그인 */
/*$(document).ready(function() {
	$('#subbtn').click(function() {
		let name = $.trim($("#ip_name").val());
		
		if(!name) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if($('#ip_name').val() == "") {
			alert("아이디를 입력해주세요.");
			return false;
		}
	})
});*/

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

function chk_signUp() {
	document.getElementById('memberJoinForm').submit();
}

