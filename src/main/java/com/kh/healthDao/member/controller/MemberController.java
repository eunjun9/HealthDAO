package com.kh.healthDao.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.healthDao.member.model.service.MemberService;
import com.kh.healthDao.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/signUp")
	public void signupForm() {}
	
	@GetMapping("/findIdPwd")
	public void findIdPwd() {}
	
	@PostMapping("/signUp")
	public String signUp(Member member) {
		
		memberService.signUp(member);
		
		return "redirect:/";
	}

}
