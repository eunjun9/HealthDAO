package com.kh.healthDao.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.healthDao.mypage.model.service.QnaService;
import com.kh.healthDao.mypage.model.vo.Qna;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	private QnaService qnaService;
	private MessageSource messageSource;
	
	@Autowired
	public MypageController(QnaService qnaService, MessageSource messageSource) {
		this.qnaService = qnaService;
		this.messageSource = messageSource;
	}
	
	@GetMapping(value= {"/", "/myOrder"})
	public String mypage() {
		return "mypage/myOrder";
	}
	
	/* 1:1 문의 */
	@GetMapping("/qna")
	public ModelAndView qna(ModelAndView mv) {
		List<Qna> qnaList = qnaService.findQnaList();
		
		mv.addObject("qnaList", qnaList);
		mv.setViewName("mypage/oneQuestion");
		
		return mv;
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
	public String qnaInsert(Qna newQna, RedirectAttributes rttr) {
		String msg = qnaService.qnaInsert(newQna) > 0 ? "문의 등록 성공" : "문의 등록 실패";		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/mypage/qna";
	}
	
	/* 쿠폰받기 이벤트 */
	@GetMapping("/event/couponEvent")
	public String couponEvent() {
		return "mypage/couponEvent";
	}
	
	/* 내 쿠폰 */
	@GetMapping("/myCoupon")
	public String myCoupon() {
		return "mypage/couponList";
	}
	
	/* 내가 쓴 리뷰 */
	@GetMapping("/review")
	public String review() {
		return "mypage/reivewList";
	}
	
	/* 고객센터 */
	@GetMapping("/customCenter")
	public String customCenter() {
		return "mypage/customCenter";
	}
	
	/* 자주묻는질문 */
	@GetMapping("/jajuQna")
	public String jajuQna() {
		return "mypage/jajuQna";
	}
	
	/* 공지사항 */
	@GetMapping("/notice")
	public String notice() {
		return "mypage/noticeList";
	}
	
	/* 공지사항 상세 */
	@GetMapping("/noticeDetail")
	public String noticeDetail() {
		return "mypage/noticeDetail";
	}
	
	/* 고객의 소리 */
	@GetMapping("/memberSound")
	public String memberSound() {
		return "mypage/memberSoundInput";
	}
}
