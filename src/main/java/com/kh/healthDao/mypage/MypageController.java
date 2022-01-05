package com.kh.healthDao.mypage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/qna/{page}")
	public ModelAndView qna(ModelAndView mv, @PathVariable int page) {
		Map<String, Object> map = qnaService.pagingQnaList(page);
			
		
		mv.addObject("qnaList", map.get("qnaList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("mypage/oneQuestion");
		
		return mv;
	}
	
	
	/* 1:1 문의 상세 */
	@GetMapping("qnaDetail")
	public ModelAndView qnaDetail(ModelAndView mv, @RequestParam int qNo) {
		Qna qna = qnaService.qnaDetail(qNo);
		
		mv.addObject("qna", qna);
		mv.setViewName("mypage/oneQuestionDetail");
		
		return mv;
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
	
	/* 배송지 관리 */
	@GetMapping("/deli")
	public String deliModify() {
		return "mypage/deliModify";
	}
	
	/* 내 정보 수정 */
	@GetMapping("/myInfo")
	public String myInfoModify() {
		return "mypage/myInfoModify";
	}
	
	/* 룰렛 */
	@GetMapping("/roulette")
	public String roulette() {
		return "mypage/roulette";
	}
		
	/* 보유 포인트 내역 */
	@GetMapping("/point")
	public String point() {
		return "mypage/point";
	}
	
	/* 출석 체크 */
	@GetMapping("/attendanceCheck")
	public String attendanceCheck() {
		return "mypage/attendanceCheck";
	}
}
