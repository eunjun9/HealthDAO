package com.kh.healthDao.mypage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.healthDao.admin.model.service.NoticeService;
import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.admin.model.vo.Notice;
import com.kh.healthDao.member.model.vo.UserImpl;
import com.kh.healthDao.mypage.model.service.MyCouponService;
import com.kh.healthDao.mypage.model.service.MyReviewService;
import com.kh.healthDao.mypage.model.service.QnaService;
import com.kh.healthDao.mypage.model.vo.AttCheck;
import com.kh.healthDao.mypage.model.vo.Point;
import com.kh.healthDao.mypage.model.vo.Qna;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	private QnaService qnaService;
	private MyCouponService couponService;
	private MessageSource messageSource;
	private NoticeService noticeService;
	private MyReviewService myReviewService;
	
	@Autowired
	public MypageController(QnaService qnaService, MyCouponService couponService, MessageSource messageSource, NoticeService noticeService, MyReviewService myReviewService) {
		this.qnaService = qnaService;
		this.couponService = couponService;
		this.messageSource = messageSource;
		this.noticeService = noticeService;
		this.myReviewService = myReviewService;
	}
	
	@GetMapping(value= {"/", "/myOrder"})
	public String mypage() {
		return "mypage/myOrder";
	}
	
	/* 1:1 문의 */
	@GetMapping("/qna")
	public ModelAndView qna(ModelAndView mv, @RequestParam int page, @AuthenticationPrincipal UserImpl userImpl) {
		
		int userNo = userImpl.getUserNo();
		Map<String, Object> map = qnaService.findQnaList(page, userNo);
		
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
	public String qnaInsert() {
		return "mypage/oneQuestionInsert";
	}
	
	@PostMapping("/qnaInsert")
	public String qnaInsert(Qna newQna, RedirectAttributes rttr) {
		System.out.println(newQna.getQDeptCode());
		
		String msg = qnaService.qnaInsert(newQna) > 0 ? "문의 등록 성공" : "문의 등록 실패";		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/mypage/qna?page=1";
	}
	
	/* 1:1문의하기 수정(사용자) */
	@PostMapping("/qnaModify")
	public String qnaModify(Qna modifyQna, RedirectAttributes rttr) {
		String msg = qnaService.qnaModify(modifyQna) > 0 ? "문의 수정 성공" : "문의 수정 실패";		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/mypage/qna?page=1";
	}
	
	/* 쿠폰받기 이벤트 */
	@GetMapping("/event/couponEvent")
	public ModelAndView couponEvent(ModelAndView mv) {
		List<Coupon> couponList = couponService.couponEventList();
		mv.addObject("couponList", couponList);
		mv.setViewName("mypage/couponEvent");
		
		return mv;
	}
	
	@PostMapping("/event/couponEvent")
	@ResponseBody
	public String myCouponInsert(int pNo, @AuthenticationPrincipal UserImpl userImpl, RedirectAttributes rttr) {
		int userNo = userImpl.getUserNo();
		
		String msg = couponService.myCouponInsert(pNo, userNo) > 0 ? "success" : "fail";
		
		return msg;
	}
	
	
	/* 내 쿠폰 */
	@GetMapping("/myCoupon")
	public ModelAndView myCoupon(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		List<Coupon> myCouponList = couponService.myCouponList(userNo);
		mv.addObject("couponList", myCouponList);
		mv.setViewName("mypage/couponList");
		
		return mv;
	}
	
	/* 내가 쓴 리뷰 */
	@GetMapping("/review")
	public ModelAndView review(ModelAndView mv, @RequestParam int page, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		Map<String, Object> map = myReviewService.userReviewList(page, userNo);
		
		mv.addObject("reviewList", map.get("reviewList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("mypage/reivewList");
		
		return mv;
	}
	
	/* 고객센터 */
	@GetMapping("/customCenter")
	public ModelAndView customCenter(ModelAndView mv) {
		
		List<Notice> noticeList = noticeService.newfiveNoticeList();
		
		mv.addObject("noticeList", noticeList);
		mv.setViewName("mypage/customCenter");
		
		return mv;
	}
	
	/* 자주묻는질문 */
	@GetMapping("/jajuQna")
	public String jajuQna() {
		return "mypage/jajuQna";
	}
	
	/* 공지사항 */
	@GetMapping("/notice")
	public ModelAndView notice(ModelAndView mv, @RequestParam int page) {
		Map<String, Object> map = noticeService.allNoticeList(page);
		
		mv.addObject("noticeList", map.get("noticeList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("mypage/noticeList");
		
		return mv;
	}
	
	/* 공지사항 상세 */
	@GetMapping("/noticeDetail")
	public ModelAndView noticeDetail(ModelAndView mv, @RequestParam int nNo) {
		
		int viewUpdate = noticeService.viewUpdate(nNo);
		Notice notice = noticeService.noticeDetail(nNo);
		
		mv.addObject("notice", notice);
		mv.setViewName("mypage/noticeDetail");
		
		return mv;
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
	public ModelAndView point(ModelAndView mv) {
		
		List<Point> PointList = qnaService.pointList();
		
		mv.addObject("pointList", PointList);
		mv.setViewName("mypage/point");
		
		return mv;
	}
	
	/* 출석체크 화면이동 */
	@GetMapping("/attendanceCheck")
	public String attendanceCheck() {
		return "mypage/attendanceCheck";
	}
	
	/* 출석 체크 */
	@PostMapping("/attendance")
	@ResponseBody
	public ModelAndView attendanceCheck(ModelAndView mv, AttCheck att) {
		
		int result = qnaService.attendanceCheck(att);

		if(result > 0) {
			mv.setViewName("redirect:/mypage/attendanceCheck");
			return mv;
		}else {
			mv.setViewName("redirect:/mypage/attendanceCheck");
			return mv;
		}
		
	}
}
