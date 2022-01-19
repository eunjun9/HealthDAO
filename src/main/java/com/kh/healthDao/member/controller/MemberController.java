package com.kh.healthDao.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.healthDao.member.model.service.MemberService;
import com.kh.healthDao.member.model.vo.MailTO;
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
	public String signUp(Member member, @RequestParam String userBirth, @RequestParam String userBirth2, @RequestParam String userBirth3) {
		
		String birth = userBirth + userBirth2 + userBirth3;
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
	
	@PostMapping("/idChk")
	@ResponseBody
	public int idChk(Member member) {
		int result = memberService.idChk(member);
		return result;
	}
	
	@GetMapping("/findId/{userName}/{userEmail}")
	@ResponseBody
	public Member findId(@PathVariable String userName, @PathVariable String userEmail) {
		
		log.info("조회 요청 이름 : {}", userName);
		log.info("조회 요청 이메일 : {}", userEmail);
		
		return memberService.findId(userName, userEmail);
	}
	
	@PostMapping("/findPwd")
	public ModelAndView findPwd(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String userEmail = (String)request.getParameter("userEmail");
		String userId = (String)request.getParameter("userId");
		ModelAndView mv = new ModelAndView();

		Member member = memberService.selectMember(userEmail);
		JavaMailSender mailSender = null;
			
		if(member != null) {
		Random r = new Random();
		int num = r.nextInt(999999); // 랜덤난수설정
		
			if(member.getUserId().equals(userId)) {
				session.setAttribute("email", member.getUserEmail());
	
				String setfrom = "khhealthdao@google.com";
				String tomail = userEmail; // 받는사람
				String title = "[HealthDao] 비밀번호변경 인증 이메일 입니다"; 
				String content = System.getProperty("line.separator") + "안녕하세요 회원님" + System.getProperty("line.separator")
						+ "HealthDao 비밀번호 찾기(변경) 인증번호는 " + num + " 입니다." + System.getProperty("line.separator");
	
				try {
					@SuppressWarnings("null")
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
	
					messageHelper.setFrom(setfrom); 
					messageHelper.setTo(tomail); 
					messageHelper.setSubject(title);
					messageHelper.setText(content); 
	
					mailSender.send(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
	
				mv.setViewName("member/findIdPwd");
				mv.addObject("num", num);
				mv.addObject("findUserEmail", userEmail);
				return mv;
			} else {
				mv.setViewName("member/findIdPwd");
				return mv;
			}
		}
		return mv;
	}
	
	@PostMapping("/findPwdUpdate")
	public String findPwdUpdate(@RequestParam String num, @RequestParam String ijnum, Member member, HttpSession session, RedirectAttributes rttr) {
		int result = memberService.pwdUpdate(member);
		
		if(ijnum.equals(num) && result == 1) {
			rttr.addFlashAttribute("msg", "비밀번호가 변경되었습니다.");
			return "member/login";
		} else {
			rttr.addFlashAttribute("msg", "인증번호가 맞지 않습니다.");
			return "member/findIdPwd";
		}
	}
	
}
