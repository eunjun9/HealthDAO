package com.example.demo.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
public class mypageController {
	
	@GetMapping(value= {"/", "/myOrder"})
	public String mypage() {
		return "mypage/myOrder";
	}
	
	@GetMapping("/qna")
	public String qna() {
		return "mypage/oneQuestion";
	}
	
	@GetMapping("/qnaDetail")
	public String qnaDetail() {
		return "mypage/oneQuestionDetail";
	}
	
	@GetMapping("/qnaInsert")
	public String qnaInsert() {
		return "mypage/oneQuestionInsert";
	}
}
