package com.kh.healthDao.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.healthDao.member.model.service.MemberService;
import com.kh.healthDao.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	private MemberService memberService;
	private JavaMailSender mailSender;
	
	@Autowired
	public MemberController(MemberService memberService, JavaMailSender mailSender) {
		this.memberService = memberService;
		this.mailSender = mailSender;
	}
	
	/*@GetMapping("/login")
	public String loginForm(@RequestParam(value = "error", required = false) String error,
            			    @RequestParam(value = "exception", required = false) String exception, Model model) {
		
		model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/member/login";
	}*/
	
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
		
		return "redirect:/";
	}
	
	@PostMapping("/idChk")
	@ResponseBody
	public int idChk(@RequestParam String userId) {
		int result = memberService.idChk(userId);
		return result;
	}
	
	@PostMapping("/nickChk")
	@ResponseBody
	public int nickChk(@RequestParam String userNickName) {
		int result = memberService.nickChk(userNickName);
		return result;
	}
	
	@GetMapping("/findId/{userName}/{userEmail}")
	@ResponseBody
	public Member findId(@PathVariable String userName, @PathVariable String userEmail) {
		
		log.info("?????? ?????? ?????? : {}", userName);
		log.info("?????? ?????? ????????? : {}", userEmail);
		
		return memberService.findId(userName, userEmail);
	}
	
	@GetMapping("/findPwd")
	@ResponseBody
	public Member findPwd(String userId, String userEmail, HttpSession session, RedirectAttributes rttr) {

		Member selectMember = memberService.selectMember(userId, userEmail);
		
		System.out.println(userId +", "+ userEmail);
		
		if(selectMember != null && selectMember.getUserId().equals(userId)) {
			Random r = new Random();
			int num = r.nextInt(999999); // ??????????????????
		
			session.setAttribute("email", selectMember.getUserEmail());
	
			String setfrom = "khhealthdao@google.com";
			String tomail = userEmail; // ????????????
			String title = "[HealthDao] ?????????????????? ?????? ????????? ?????????"; 
			String content = "??????????????? " + userId + "???! HealthDao ???????????? ??????(??????) ??????????????? " + num + " ?????????.";
	
			try {
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
			selectMember.setNum(num);
			
			return selectMember;
		} else {
			return selectMember;
		}
	}
	
	@PostMapping("/findPwdUpdate")
	public String findPwdUpdate(@RequestParam String ijnum, Member member, RedirectAttributes rttr) {
		int result = memberService.pwdUpdate(member);
		int ijnum2 = Integer.parseInt(ijnum);
		
		if(ijnum2 == member.getNum() && result == 1) {
			rttr.addFlashAttribute("msg", "??????????????? ?????????????????????.");
			return "member/login";
		} else {
			rttr.addFlashAttribute("msg", "??????????????? ?????? ????????????.");
			return "member/findIdPwd";
		}
	}
	
}
