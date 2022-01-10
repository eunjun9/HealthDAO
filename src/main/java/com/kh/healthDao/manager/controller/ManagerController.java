package com.kh.healthDao.manager.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.healthDao.manager.model.service.ManagerService;
import com.kh.healthDao.manager.model.vo.Qna;

@Controller
@RequestMapping("/manager/*")
public class ManagerController {
	
	private ManagerService managerService;
	
	@Autowired
	public ManagerController(ManagerService managerService) {
		this.managerService = managerService;
	}

	// 정산내역
	@GetMapping("/calculateList")
	public String managerCalculateList() {
		
		return "manager/calculateList";
	}
	
	
	// 회원문의 페이징 된
	@GetMapping("/memberInquiry")
	public ModelAndView managerMemberInquiry(ModelAndView mv, @RequestParam int page) {
		
		Map<String, Object> map = managerService.InquiryPaging(page);
		
		mv.addObject("QnaList", map.get("QnaList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("manager/memberInquiry");
		
		return mv;
	}
	
	// 회원문의답변
	@GetMapping("memberInquiryAnswer")
	public ModelAndView managerMemberInquiryAnswer(ModelAndView mv, @RequestParam int qNo) {
		
		Qna QnaList = managerService.listQna(qNo);
		
		mv.addObject("QnaList", QnaList);
		mv.setViewName("manager/memberInquiryAnswer");
		
		return mv;
	}
	
	@PostMapping("/qanswer")
	public String managerQAnswer(Qna qna, RedirectAttributes rttr) {
		
		String result = managerService.managerQAnswer(qna) > 0 ? "문의 답변 수정 성공" : "문의 답변 수정 실패";
		rttr.addFlashAttribute("msg", result);
		
		return "redirect:/manager/memberInquiry?page=1";
	}
	
	// 환불내역
	@GetMapping("/refundList")
	public String managerRefundList() {
		
		return "manager/refundList";
	}
	

	
	
	
	
	
}















