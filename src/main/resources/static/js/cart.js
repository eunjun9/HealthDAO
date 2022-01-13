/**
 * 장바구니 추가 ajax
 */
function cartInsert(pNo){
	let productNo = pNo;
	
	var sendData = {"productNo":productNo, "cartStock":1};
	$.ajax({
		url : "/mypage/cartInsert",
		data : sendData,
		type : "post",
		success : function(data){
			if(data == "success"){
				alert("장바구니에 추가되었습니다.");							
			}else{
				alert("장바구니에 추가 실패");
			}
		},
		error : function(e){
			console.log(e)
		}
	});
}