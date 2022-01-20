package com.kh.healthDao.shopping.model.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.manager.model.vo.Payment;
import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.member.model.vo.UserImpl;
import com.kh.healthDao.mypage.model.service.MyInfoService;
import com.kh.healthDao.mypage.model.vo.Address;
import com.kh.healthDao.shopping.model.service.ShoppingService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/shopping/*")
public class ShoppingController {

	private ShoppingService shoppingService;
	private MyInfoService myInfoService;
	
	@Autowired
	public ShoppingController(ShoppingService shoppingService, MyInfoService myInfoService) {
		this.shoppingService = shoppingService;
		this.myInfoService = myInfoService;
	}
	
	// 쇼핑 랭킹페이지
	@GetMapping("/ranking")
	public ModelAndView shoppingRanking(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {
		
		//List<Shopping> shoppingList = shoppingService.ShoppingList();
		
		// mv.addObject("shoppingList", shoppingList);
		mv.setViewName("shopping/shoppingRanking");

		// 찜한 상품 확인
		int userNo = 0;		
		if(userImpl != null) {
			userNo = userImpl.getUserNo();
			List like = shoppingService.likeList(userNo);
			mv.addObject("likeList", like);
		}

		return mv;
	}

	// 쇼핑 검색
	@PostMapping("/search")
	public ModelAndView shoppingSearch(ModelAndView mv, String searchPdt, @AuthenticationPrincipal UserImpl userImpl) {
		if(searchPdt == null) {
			searchPdt = "";
		}
		List<Product> shoppingList = shoppingService.searchList(searchPdt);

		if(searchPdt == "") {
			searchPdt = "전체상품";
		}
		mv.addObject("searchPdt", searchPdt);
		mv.addObject("shoppingList", shoppingList);
		mv.setViewName("shopping/shoppingSearch");

		// 찜한 상품 확인
		int userNo = 0;		
		if(userImpl != null) {
			userNo = userImpl.getUserNo();
			List like = shoppingService.likeList(userNo);
			mv.addObject("likeList", like);
		}
		return mv;
	}
	
	// 쇼핑 신상품
	@GetMapping("/newProduct")
	public ModelAndView shoppingNewProduct(ModelAndView mv, @RequestParam int page, @AuthenticationPrincipal UserImpl userImpl) {
		Map<String, Object> map = shoppingService.ShoppingList(page);

		mv.addObject("shoppingList", map.get("shoppingList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		
		mv.setViewName("shopping/shoppingNewProduct");

		// 찜한 상품 확인
		int userNo = 0;		
		if(userImpl != null) {
			userNo = userImpl.getUserNo();
			List like = shoppingService.likeList(userNo);
			mv.addObject("likeList", like);
		}

		return mv;
	}
	
	// 쇼핑 식품
	@GetMapping("/foodProduct")
	public ModelAndView shoppingFoodProduct(ModelAndView mv, @RequestParam int page, @AuthenticationPrincipal UserImpl userImpl) {
		Map<String, Object> map = shoppingService.foodShoppingList(page);
		
		mv.addObject("shoppingList", map.get("shoppingList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		
		mv.setViewName("shopping/shoppingFoodProduct");
		
		// 찜한 상품 확인
		int userNo = 0;		
		if(userImpl != null) {
			userNo = userImpl.getUserNo();
			List like = shoppingService.likeList(userNo);
			mv.addObject("likeList", like);
		}
		
		return mv;
	}
	
	// 쇼핑 음료
	@GetMapping("/beverageProduct")
	public ModelAndView shoppingBeverageProduct(ModelAndView mv, @RequestParam int page, @AuthenticationPrincipal UserImpl userImpl) {
		Map<String, Object> map = shoppingService.beverageShoppingList(page);
		
		mv.addObject("shoppingList", map.get("shoppingList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		
		mv.setViewName("shopping/shoppingBeverageProduct");

		// 찜한 상품 확인
		int userNo = 0;		
		if(userImpl != null) {
			userNo = userImpl.getUserNo();
			List like = shoppingService.likeList(userNo);
			mv.addObject("likeList", like);
		}
		return mv;
	}
	
	// 쇼핑 운동용품
	@GetMapping("/goodsProduct")
	public ModelAndView shoppingGoodsProduct(ModelAndView mv, @RequestParam int page, @AuthenticationPrincipal UserImpl userImpl) {
		Map<String, Object> map = shoppingService.goodsShoppingList(page);
		
		mv.addObject("shoppingList", map.get("shoppingList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		
		mv.setViewName("shopping/shoppingGoodsProduct");

		// 찜한 상품 확인
		int userNo = 0;		
		if(userImpl != null) {
			userNo = userImpl.getUserNo();
			List like = shoppingService.likeList(userNo);
			mv.addObject("likeList", like);
		}
		return mv;
	}
	
	// 쇼핑 상세페이지
	@GetMapping("/detail")
	public ModelAndView shoppingDetail(ModelAndView mv, @RequestParam int productNo, @AuthenticationPrincipal UserImpl userImpl) {
		
		Product shoppingDetail = shoppingService.shoppingDetail(productNo);
		
		mv.addObject("shoppingDetail", shoppingDetail);
		mv.setViewName("shopping/shoppingProductDetail");

		// 찜한 상품 확인
		int userNo = 0;		
		if(userImpl != null) {
			userNo = userImpl.getUserNo();
			List like = shoppingService.likeList(userNo);
			mv.addObject("likeList", like);
		}
		return mv;
	}
	
	// 쇼핑 주문
	@GetMapping("/payment")
	public ModelAndView shoppingPayment(ModelAndView mv, @RequestParam int productNo) {
		
		Product shoppingPayment = shoppingService.shoppingPayment(productNo);
		
		mv.addObject("shoppingPayment", shoppingPayment);
		mv.setViewName("shopping/shoppingPayment");
		
		return mv;
	}

	// 쇼핑 주문
	@PostMapping("/payment") 
	public ModelAndView shoppingPaymentInfo(@RequestParam("select1") String select1, @RequestParam("amount") int amount, @RequestParam("sum") int sum, 
											ModelAndView mv, @RequestParam int productNo, @RequestParam int userNo) {
		
		log.info(select1 + "select1" + amount + "amount" + sum + "sum");
		
		
		Product shoppingPayment = shoppingService.shoppingPayment(productNo);
		List<Address> addressList = myInfoService.deliView(userNo);
		Member member = myInfoService.myInfoView(userNo);
		
		mv.addObject("shoppingPayment", shoppingPayment);
		mv.addObject("select1", select1);
		mv.addObject("amount", amount);
		mv.addObject("sum", sum);
		mv.addObject("addressList", addressList);
		mv.addObject("member", member);
		
		mv.setViewName("shopping/shoppingPayment");
		
		return mv; 
		
	}
	
	// 주문 내역
	@PostMapping("/mypage/myOrder")
	@ResponseBody
	public String myOrderInfo(Payment payment) {
		
		System.out.println(payment);
		
		return "";
	}
	
	
	
}
