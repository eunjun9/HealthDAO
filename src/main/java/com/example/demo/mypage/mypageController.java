package com.example.demo.mypage;

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
}
