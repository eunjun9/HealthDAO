package com.kh.healthDao.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
public class mypageController {
	
	@GetMapping(value= {"/", "/myOrder"})
	public String mypage() {
		return "mypage/myOrder";
	}
	
	/* 1:1 문의 */
	@GetMapping("/qna")
	public String qna() {
		return "mypage/oneQuestion";
	}
	
	/* 1:1 문의 상세 */
	@GetMapping("/qnaDetail")
	public String qnaDetail() {
		return "mypage/oneQuestionDetail";
	}
	
	/* 1:1 문의하기 */
	@GetMapping("/qnaInsert")
	public String qnaInsertView() {
		return "mypage/oneQuestionInsert";
	}
	
	@PostMapping("/qnaInsert")
	public String qnaInsert() {
		return "redirect:/mypage/qna";
	}
	
	/* 쿠폰받기 이벤트 */
	@GetMapping("/event/couponEvent")
	public String couponEvent() {
		return "mypage/couponEvent";
	}
	
	/* 내 쿠폰 */
	@GetMapping("/myCoupon")
	public String myCoupon() {
		return "mypage/couponList";
	}
	
	/* 내가 쓴 리뷰 */
	@GetMapping("/review")
	public String review() {
		return "mypage/reivewList";
	}
	
	/* 고객센터 */
	@GetMapping("/customCenter")
	public String customCenter() {
		return "mypage/customCenter";
	}
	
	/* 자주묻는질문 */
	@GetMapping("/jajuQna")
	public String jajuQna() {
		return "mypage/jajuQna";
	}
	
	/* 공지사항 */
	@GetMapping("/notice")
	public String notice() {
		return "mypage/noticeList";
	}
	
	/* 공지사항 상세 */
	@GetMapping("/noticeDetail")
	public String noticeDetail() {
		return "mypage/noticeDetail";
	}
	
	/* 고객의 소리 */
	@GetMapping("/memberSound")
	public String memberSound() {
		return "mypage/memberSoundInput";
	}
	
	/* 배송지 관리 */
	@GetMapping("/deli")
	public String deliModify() {
		return "mypage/deliModify";
	}
	
	/* 내 정보 수정 */
	@GetMapping("/myInfo")
	public String myInfoModify() {
		return "mypage/deliModify";
	}
	
}
