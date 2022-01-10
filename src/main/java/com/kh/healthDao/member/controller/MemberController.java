package com.kh.healthDao.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.healthDao.member.model.service.MemberService;
import com.kh.healthDao.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
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
	public String signUp(HttpServletRequest hsr, Member member) {
		
		String b1 = hsr.getParameter("userBirth");
		String b2 = hsr.getParameter("userBirth2");
		String b3 = hsr.getParameter("userBirth3");
		String birth = b1+b2+b3;
		member.setUserBirth(birth);
		
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
			// 존재하지 않는다면 -> service
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
	
	@ResponseBody
	@GetMapping("/findIdPwd/{userName}/{userEmail}")
	public Member findId(ModelAndView mv, @PathVariable String userName, @PathVariable String userEmail) {
		
		log.info("조회 요청 이름 : {}", userName);
		log.info("조회 요청 이메일 : {}", userEmail);
		
		return memberService.findId(userName, userEmail);
	}
	
}
