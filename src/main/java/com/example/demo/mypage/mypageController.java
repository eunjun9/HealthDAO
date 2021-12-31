package com.example.demo.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class mypageController {
	
	@GetMapping("/")
	public String mypage() {
		return "mypage/payment";
	}
}
