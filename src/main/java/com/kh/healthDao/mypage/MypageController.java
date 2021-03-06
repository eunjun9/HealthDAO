package com.kh.healthDao.mypage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.healthDao.admin.model.service.MemberSoundService;
import com.kh.healthDao.admin.model.service.NoticeService;
import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.admin.model.vo.Notice;
import com.kh.healthDao.manager.model.vo.Payment;
import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.member.model.vo.UserImpl;
import com.kh.healthDao.mypage.model.service.CartService;
import com.kh.healthDao.mypage.model.service.MyCouponService;
import com.kh.healthDao.mypage.model.service.MyInfoService;
import com.kh.healthDao.mypage.model.service.MyReviewService;
import com.kh.healthDao.mypage.model.service.PaymentService;
import com.kh.healthDao.mypage.model.service.QnaService;
import com.kh.healthDao.mypage.model.vo.Address;
import com.kh.healthDao.mypage.model.vo.AttCheck;
import com.kh.healthDao.mypage.model.vo.Cart;
import com.kh.healthDao.mypage.model.vo.MemberSound;
import com.kh.healthDao.mypage.model.vo.Qna;
import com.kh.healthDao.mypage.model.vo.Roulette;
import com.kh.healthDao.review.model.vo.Review;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	private QnaService qnaService;
	private MyCouponService couponService;
	private MessageSource messageSource;
	private NoticeService noticeService;
	private MyReviewService myReviewService;
	private MemberSoundService memberSoundService;
	private MyInfoService myInfoService;
	private CartService cartService;
	private PaymentService paymentService;

	
	@Autowired
	public MypageController(QnaService qnaService, MyCouponService couponService, MessageSource messageSource, NoticeService noticeService, 
			MyReviewService myReviewService, MemberSoundService memberSoundService, MyInfoService myInfoService, CartService cartService,
			PaymentService paymentService) {
		this.qnaService = qnaService;
		this.couponService = couponService;
		this.messageSource = messageSource;
		this.noticeService = noticeService;
		this.myReviewService = myReviewService;
		this.memberSoundService = memberSoundService;
		this.myInfoService = myInfoService;
		this.cartService = cartService;
		this.paymentService = paymentService;
	}
	
	@GetMapping(value= {"/", "/myOrder"})
	public ModelAndView order(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		List<Payment> paymentList = paymentService.mypaymentList(userNo);

		mv.addObject("userImpl", userImpl);
		mv.addObject("paymentList", paymentList);
		mv.setViewName("mypage/myOrder");
		
		return mv;
	}
	
	@PostMapping("/reviewInsert")
	public String reviewInsert(Review review, @AuthenticationPrincipal UserImpl userImpl, RedirectAttributes rttr) {
		
		String msg = myReviewService.reviewInsert(review) > 0 ? "?????? ?????? ??????" : "?????? ?????? ??????";					
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/mypage/myOrder";
	}
	
	@PostMapping("/refundInsert")
	@ResponseBody
	public String refundInsert(int payNo) {
		
		String msg = paymentService.refundInsert(payNo) > 0 ? "success" : "fail";	
		
		return msg;
	}
	
	
	/* 1:1 ?????? */
	@GetMapping("/qna")
	public ModelAndView qna(ModelAndView mv, @RequestParam int page, @AuthenticationPrincipal UserImpl userImpl) {
		
		int userNo = userImpl.getUserNo();
		Map<String, Object> map = qnaService.findQnaList(page, userNo);
		
		mv.addObject("qnaList", map.get("qnaList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.addObject("userImpl", userImpl);
		mv.setViewName("mypage/oneQuestion");
		
		return mv;
	}
	
	
	
	/* 1:1 ?????? ?????? */
	@GetMapping("qnaDetail")
	public ModelAndView qnaDetail(ModelAndView mv, @RequestParam int qNo, @AuthenticationPrincipal UserImpl userImpl) {
		Qna qna = qnaService.qnaDetail(qNo);
		
		mv.addObject("qna", qna);
		mv.addObject("userImpl", userImpl);
		mv.setViewName("mypage/oneQuestionDetail");
		
		return mv;
	}
	
	/* 1:1 ???????????? */
	@GetMapping("/qnaInsert")
	public String qnaInsert() {
		return "mypage/oneQuestionInsert";
	}
	
	@PostMapping("/qnaInsert")
	public String qnaInsert(Qna newQna, RedirectAttributes rttr) {
		System.out.println(newQna.getQDeptCode());
		
		String msg = qnaService.qnaInsert(newQna) > 0 ? "?????? ?????? ??????" : "?????? ?????? ??????";		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/mypage/qna?page=1";
	}
	
	/* 1:1???????????? ??????(?????????) */
	@PostMapping("/qnaModify")
	public String qnaModify(Qna modifyQna, RedirectAttributes rttr) {
		String msg = qnaService.qnaModify(modifyQna) > 0 ? "?????? ?????? ??????" : "?????? ?????? ??????";		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/mypage/qna?page=1";
	}
	
	/* ???????????? ????????? */
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
	
	
	/* ??? ?????? */
	@GetMapping("/myCoupon")
	public ModelAndView myCoupon(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		List<Coupon> myCouponList = couponService.myCouponList(userNo);
		mv.addObject("couponList", myCouponList);
		mv.setViewName("mypage/couponList");
		
		return mv;
	}
	
	/* ?????? ??? ?????? */
	@GetMapping("/review")
	public ModelAndView review(ModelAndView mv, @RequestParam int page, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		Map<String, Object> map = myReviewService.userReviewList(page, userNo);
		
		System.out.println(map.get("reviewList"));
		
		mv.addObject("reviewList", map.get("reviewList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("mypage/reivewList");
		
		return mv;
	}
	
	@PostMapping("/reviewDetail")
	@ResponseBody
	public Review reviewDetail(int reviewNo) {
		Review review = myReviewService.reviewDetail(reviewNo);
		
		return review;
	}
	
	@PostMapping("/reviewModify")
	public String reviewModify(Review review, RedirectAttributes rttr) {
		String msg = myReviewService.reviewModify(review) > 0 ? "?????? ?????? ??????" : "?????? ?????? ??????";		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/mypage/review?page=1";
	}
	
	/* ???????????? */
	@GetMapping("/customCenter")
	public ModelAndView customCenter(ModelAndView mv) {
		
		List<Notice> noticeList = noticeService.newfiveNoticeList();
		
		mv.addObject("noticeList", noticeList);
		mv.setViewName("mypage/customCenter");
		
		return mv;
	}
	
	/* ?????????????????? */
	@GetMapping("/jajuQna")
	public String jajuQna() {
		return "mypage/jajuQna";
	}
	
	/* ???????????? */
	@GetMapping("/notice")
	public ModelAndView notice(ModelAndView mv, @RequestParam int page) {
		Map<String, Object> map = noticeService.allNoticeList(page);
		
		mv.addObject("noticeList", map.get("noticeList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("mypage/noticeList");
		
		return mv;
	}
	
	/* ???????????? ?????? */
	@GetMapping("/noticeDetail")
	public ModelAndView noticeDetail(ModelAndView mv, @RequestParam int nNo) {
		
		int viewUpdate = noticeService.viewUpdate(nNo);
		Notice notice = noticeService.noticeDetail(nNo);
		
		mv.addObject("notice", notice);
		mv.setViewName("mypage/noticeDetail");
		
		return mv;
	}
	
	/* ????????? ?????? */
	@GetMapping("/memberSound")
	public String memberSound() {
		return "mypage/memberSoundInput";
	}
	
	@PostMapping("/memberSoundInsert")
	public String memberSoundInsert(MemberSound ms, RedirectAttributes rttr, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		ms.setUserNo(userNo);
		
		String msg = memberSoundService.memberSoundInsert(ms) > 0 ? "??????????????? ?????? ??????" : "??????????????? ?????? ??????";		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/mypage/memberSound";
	}
	
	/* ????????? ?????? */
	@GetMapping("/deli")
	public ModelAndView deliView(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		List<Address> addressList = myInfoService.deliView(userNo);
		
		mv.addObject("addressList", addressList);
		mv.setViewName("mypage/deliModify");
		
		return mv;
	}
	
	@PostMapping("deli/insert")
	@ResponseBody
	public Map<String, String> insertDeli(@RequestBody Address address, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		address.setUserNo(userNo);
		
		String msg = myInfoService.insertDeli(address) > 0 ? "???????????? ?????????????????????." : "????????? ????????? ?????????????????????.";
		
		Map<String, String> map = new HashMap<>();
		map.put("msg", msg);
		
		return map;
	}
	
	@GetMapping("deli/detail")
	@ResponseBody
	public Address selectDeli(@RequestBody Address address) {
		address = myInfoService.selectDeli(address.getAddressNo());
		
		return address;
	}
	
	@PutMapping("deli/update")
	@ResponseBody
	public Map<String, String> updateDeli(@RequestBody Address address) {
		
		String msg = myInfoService.updateDeil(address.getAddressNo()) > 0 ? "?????? ?????? ??????" : "?????? ?????? ??????";
		
		Map<String, String> map = new HashMap<>();
	    map.put("msg", msg);
	    
	    return map;
	}
	
	@DeleteMapping("deli/delete")
	@ResponseBody
	public Map<String, String> deleteDeli(@RequestBody Address address) {
		
		String msg = myInfoService.deleteDeil(address.getAddressNo()) > 0 ? "???????????? ?????????????????????." : "????????? ????????? ??????????????????.";
		
		Map<String, String> map = new HashMap<>();
	    map.put("msg", msg);
	    
	    return map;
	}
	
	@PatchMapping("deli/defAddress")
	@ResponseBody
	public Map<String, String> defAddDeli(@RequestBody Address address, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		/* ?????? ?????????????????? ????????? status N?????? ?????? */
		myInfoService.defAddRemove(userNo);
		/* ?????? ?????? ?????????????????? ?????? */
		String msg = myInfoService.defAddDeli(address.getAddressNo()) > 0 ? "?????????????????? ?????????????????????." : "??????????????? ????????? ??????????????????.";
		
		Map<String, String> map = new HashMap<>();
	    map.put("msg", msg);
	    
	    return map;
	}
	
	/* ??? ?????? ?????? */
	@GetMapping("myInfo")
	public ModelAndView myInfo(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		Member member = myInfoService.myInfoView(userNo);
		
		mv.addObject("member", member);
		mv.setViewName("mypage/myInfoModify");
		
		return mv;
	}
	
	@PutMapping("myInfo/modify")
	@ResponseBody
	public Map<String, String> myInfoModify(@RequestBody Member member) {
		System.out.println(member);
		String msg = myInfoService.myInfoModify(member) > 0 ? "?????? ????????? ??????????????????." : "?????? ????????? ??????????????????.";
		
		Map<String, String> map = new HashMap<>();
	    map.put("msg", msg);
	    
	    return map;
	}
	
	@PatchMapping("myInfo/delete")
	@ResponseBody
	public Map<String, String> myInfoDelete(@RequestBody Member member, HttpSession session) {
		
		String msg = myInfoService.myInfoDelete(member.getUserNo(), session) > 0 ? "?????? ?????????????????????." : "?????? ????????? ??????????????????.";
		
		Map<String, String> map = new HashMap<>();
	    map.put("msg", msg);
	    
	    return map;
	}
	
	/* ?????? ?????? */
	@GetMapping("unregister")
	public String unregister() {
		return "member/unregister";
	}
	
	@PostMapping("unregister/proc")
	public String unregisterProc(Member member, HttpSession session) {
		
		myInfoService.unregister(member, session);
		
		return "redirect:/main";
	}
	
	@GetMapping("unregister/passCheck")
	@ResponseBody
	public String passCheck(String userPwd, @AuthenticationPrincipal UserImpl userImpl) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String loginUserPwd = userImpl.getUserPwd();
		
		int result = 0;
		if(passwordEncoder.matches(userPwd, loginUserPwd)) result = 1;
		
		return Integer.toString(result);
	}
	
	/* ?????? */
	@GetMapping("/roulette")
	public ModelAndView roulette(@AuthenticationPrincipal UserImpl user, ModelAndView mv) {
		int userNo = user.getUserNo();
	
		List<Roulette> rouletteButtonList = qnaService.rouletteButton(userNo);
		
		mv.setViewName("mypage/roulette");
		mv.addObject("rouletteButtonList", rouletteButtonList);
		return mv;
	}
	
	/* ?????? ??? ?????? */
	@PostMapping("rouletteInsert")
	@ResponseBody
	public Map<String, String> rouletteInsert(@AuthenticationPrincipal UserImpl user, int pointAmount) {
		int result = qnaService.rouletteInsert(user.getUserNo(), pointAmount);
		
		Map<String, String> map = new HashMap<>();
	    map.put("msg", result > 0 ? "?????? ??????" : "?????? ??????");
	    
	    return map;
	}
		
	/* ?????? ????????? ?????? */
	@GetMapping("/point")
	public ModelAndView point(ModelAndView mv,  @RequestParam int page, @AuthenticationPrincipal UserImpl userImpl) {
		
		int userNo = userImpl.getUserNo();
		Map<String, Object> map = qnaService.pointList(page, userNo);
		
		mv.addObject("PointList", map.get("PointList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pointCount", map.get("pointCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("mypage/point");
		
		return mv;
	}
	
	/* ???????????? ???????????? */
	@GetMapping("/attendanceCheck")
	public ModelAndView attendUser(@AuthenticationPrincipal UserImpl userImpl, ModelAndView mv) {
		int userNo = userImpl.getUserNo();
		
		List<AttCheck> attendUserList = qnaService.attendUserList(userNo);
		
		List dateArr = new ArrayList();
		for(int i = 0; i < attendUserList.size(); i++) {
			dateArr.add(attendUserList.get(i).getStringAttendanceDate());
		}
		
		int attendCount = qnaService.attendCount(userNo);
		
		mv.addObject("attendUserList", attendUserList);
		mv.addObject("attendCount", attendCount);
		mv.addObject("dateArr", dateArr);
		mv.setViewName("mypage/attendanceCheck");

		return mv;
	}
	
	/* ?????? ?????? */
	@PostMapping("/attCheck")
	@ResponseBody
	public String attendanceCheck(Date attendanceDate, @AuthenticationPrincipal UserImpl userImpl) {
		
		int userNo = userImpl.getUserNo();
		AttCheck attcheck = new AttCheck();
		attcheck.setAttendanceDate(attendanceDate);
		attcheck.setUserNo(userNo);
		int result = qnaService.attendCheck(attcheck);	
		
		if(result > 0) {
			return "???????????? ??????";
		}else {
			return "???????????? ??????";
		}
		
	}
	
	// ????????????
	@GetMapping("/cart")
	public ModelAndView cartList(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		List<Cart> cartList = cartService.cartList(userNo);
		
		int allTotalPrice = 0;
		for(int i = 0; i < cartList.size(); i++) {
			allTotalPrice += (Integer.parseInt(cartList.get(i).getProduct().getProductPrice()) * cartList.get(i).getCartStock());
		}
		
		mv.addObject("cartList", cartList);
		mv.addObject("allTotalPrice", allTotalPrice);
		mv.setViewName("mypage/cart"); 
		
		return mv;
	}
	
	@PostMapping("cartInsert")
	@ResponseBody
	public String cartInsert(@AuthenticationPrincipal UserImpl userImpl, int productNo, int cartStock) {
		Cart cartinfo = new Cart();
		cartinfo.setUserNo(userImpl.getUserNo());
		cartinfo.setProductNo(productNo);
		cartinfo.setCartStock(cartStock);
		
		String msg = cartService.cartInsert(cartinfo) > 0 ? "success" : "fail";
		
		return msg;
	}
	
	@PostMapping("cartStock")
	@ResponseBody
	public String cartStock(int cartNo, String upDown) {
		
		String msg = cartService.cartStock(cartNo, upDown) > 0 ? "success" : "fail";
		
		return msg;
	}
	
	@PostMapping("cartDelete")
	@ResponseBody
	public String cartDelete(int cartNo) {
		
		String msg = cartService.cartDelete(cartNo) > 0 ? "success" : "fail";
		
		return msg;
	}
	
	@PostMapping("cartAllDelete")
	@ResponseBody
	public String cartAllDelete(@AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		String msg = cartService.cartAllDelete(userNo) > 0 ? "success" : "fail";
		
		return msg;
	}
	
	// ????????????
	@GetMapping("/memberGrade")
	public ModelAndView memberGrade(@AuthenticationPrincipal UserImpl userImpl, ModelAndView mv) {		
		int userNo = userImpl.getUserNo();

		//System.out.println(userNo);
		List<Payment> memberGrade = qnaService.memberGrade(userNo);
		//System.out.println(memberGrade);
		
		
		List payArr = new ArrayList();
		for(int i = 0; i < memberGrade.size(); i++) {

			payArr.add(memberGrade.get(i).getTotalPrice());

		}
		
		// System.out.println(payArr);
		
		int sum = 0;
		for(int i = 0; i < payArr.size(); i++) {
			sum += (int)payArr.get(i);
		}
		// System.out.println(sum);

		mv.addObject("sum", sum);
		mv.setViewName("mypage/memberGrade"); 
		
		return mv;
	}
	

}

