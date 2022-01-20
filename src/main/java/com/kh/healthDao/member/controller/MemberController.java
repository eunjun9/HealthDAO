package com.kh.healthDao.member.controller;

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
import org.springframework.web.servlet.ModelAndView;
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
	
	@GetMapping("/login")
	public String loginForm(@RequestParam(value = "error", required = false) String error,
            			    @RequestParam(value = "exception", required = false) String exception, Model model) {
		
		model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/member/login";
	}
	
	/*@GetMapping("/login")
	public void loginForm() {}*/
	
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
		
		log.info("조회 요청 이름 : {}", userName);
		log.info("조회 요청 이메일 : {}", userEmail);
		
		return memberService.findId(userName, userEmail);
	}
	
	@PostMapping("/findPwd")
	public ModelAndView findPwd(HttpSession session, @RequestParam String userId, @RequestParam String userEmail,
			RedirectAttributes rttr) {
		ModelAndView mv = new ModelAndView();

		Member selectMember = memberService.selectMember(userEmail);
		
		if(selectMember != null && selectMember.getUserId().equals(userId)) {
			Random r = new Random();
			int num = r.nextInt(999999); // 랜덤난수설정
		
				session.setAttribute("email", selectMember.getUserEmail());
	
				String setfrom = "khhealthdao@google.com";
				String tomail = userEmail; // 받는사람
				String title = "[HealthDao] 비밀번호변경 인증 이메일 입니다"; 
				String content = "안녕하세요 " + userId + "님! HealthDao 비밀번호 찾기(변경) 인증번호는 " + num + " 입니다.";
	
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
	
				mv.addObject("num", num);
				mv.addObject("findUserEmail", userEmail);
				mv.setViewName("member/findIdPwd");
				return mv;
		} else {
				rttr.addFlashAttribute("msg", "회원정보를 찾을 수 없습니다.");
				mv.setViewName("redirect:/member/findIdPwd");
				return mv;
		}
	}
	
	/* @GetMapping("/findPwd")
	@ResponseBody
	public Map<String, Member> findPwd(String userId, String userEmail, HttpSession session, RedirectAttributes rttr) {

		Map<String, Member> json = new HashMap<>();
		Member selectMember = memberService.selectMember(userEmail);
		
		System.out.println(userId +", "+ userEmail);
		
		if(selectMember != null && selectMember.getUserId().equals(userId)) {
			Random r = new Random();
			int num = r.nextInt(999999); // 랜덤난수설정
		
			session.setAttribute("email", selectMember.getUserEmail());
	
			String setfrom = "khhealthdao@google.com";
			String tomail = userEmail; // 받는사람
			String title = "[HealthDao] 비밀번호변경 인증 이메일 입니다"; 
			String content = "안녕하세요 " + userId + "님! HealthDao 비밀번호 찾기(변경) 인증번호는 " + num + " 입니다.";
	
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
			json.put("member", selectMember);
			return json;
		} else {
			return null;
		}
	} */
	
	/* @PostMapping("/findPwdUpdate")
	public String findPwdUpdate(@RequestParam String num, @RequestParam String ijnum, Member member, HttpSession session, RedirectAttributes rttr) {
		int result = memberService.pwdUpdate(member);
		
		if(ijnum.equals(num) && result == 1) {
			rttr.addFlashAttribute("msg", "비밀번호가 변경되었습니다.");
			return "member/login";
		} else {
			rttr.addFlashAttribute("msg", "인증번호가 맞지 않습니다.");
			return "member/findIdPwd";
		}
	} */
	
	@PostMapping("/findPwdUpdate")
	public String findPwdUpdate(@RequestParam String ijnum, Member member, RedirectAttributes rttr) {
		int result = memberService.pwdUpdate(member);
		int ijnum2 = Integer.parseInt(ijnum);
		
		if(ijnum2 == member.getNum() && result == 1) {
			rttr.addFlashAttribute("msg", "비밀번호가 변경되었습니다.");
			return "member/login";
		} else {
			rttr.addFlashAttribute("msg", "인증번호가 맞지 않습니다.");
			return "member/findIdPwd";
		}
	}
	
}
