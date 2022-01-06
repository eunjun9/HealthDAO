package com.kh.healthDao.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/login")
	public void loginForm() {}
	
	@GetMapping("/signUp")
	public void signupForm() {}
	
	@GetMapping("/findIdPwd")
	public void findIdPwd() {}
	
	@PostMapping("/signUp")
	public String signUp(Member member) {
		
		memberService.signUp(member);
		/*
		int result = memberService.idChk(member);
		try {
			if(result == 1) {
				return "/member/signUp";
			}else if(result == 0) {
				memberService.signUp(member);
			}
			// 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
			// 존재하지 않는다면 -> signUp
		} catch (Exception e) {
			throw new RuntimeException();
		}*/
		
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping("/idChk")
	public int idChk(Member member) throws Exception {
		int result = memberService.idChk(member);
		return result;
	}

}
