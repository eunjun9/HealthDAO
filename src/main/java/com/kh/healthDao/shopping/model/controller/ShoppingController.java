package com.kh.healthDao.shopping.model.controller;

import java.util.ArrayList;
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
import com.kh.healthDao.manager.model.service.ManagerService;
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
	private ManagerService managerService;
	
	
	@Autowired
	public ShoppingController(ShoppingService shoppingService, MyInfoService myInfoService, ManagerService managerService) {
		this.shoppingService = shoppingService;
		this.myInfoService = myInfoService;
		this.managerService = managerService;
	}
	
	// 쇼핑 랭킹페이지
	@GetMapping("/ranking")
	public ModelAndView shoppingRanking(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {

		Map<String, Object> map = shoppingService.rankList();

		mv.addObject("rankAll", map.get("rankAll"));
		mv.addObject("rankFood", map.get("rankFood"));
		mv.addObject("rankBeverage", map.get("rankBeverage"));
		mv.addObject("rankGoods", map.get("rankGoods"));
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
		List<Product> shoppingReview = shoppingService.shoppingReview(productNo);
		int sumReview = shoppingService.sumReview(productNo);
		int avgStar = shoppingService.avgStar(productNo);
		mv.addObject("sumReview", sumReview);
		mv.addObject("avgStar", avgStar);
		mv.addObject("shoppingDetail", shoppingDetail);
		mv.addObject("shoppingReview", shoppingReview);
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
	@PostMapping("payment") 
	public ModelAndView shoppingPaymentInfo(String[] select1, int[] amount, int sum, 
											ModelAndView mv, int[] productNo, int userNo) {
		Product shoppingPayment = shoppingService.shoppingPayment(productNo[0]);
		/*
		 * List<Product> shoppingList = new ArrayList<>();
		 * 
		 * for(int i = 0; i < productNo.length; i++) { Product shoppingPayment =
		 * shoppingService.shoppingPayment(productNo[i]);
		 * shoppingPayment.setQuantity(amount[i]);
		 * shoppingPayment.setProductOption(select1[i]);
		 * shoppingList.add(shoppingPayment); } System.out.println(shoppingList);
		 */
		
		List<Address> addressList = myInfoService.deliView(userNo);
		Member member = myInfoService.myInfoView(userNo);
			
		mv.addObject("shoppingPayment", shoppingPayment);
		mv.addObject("sum", sum);
		mv.addObject("addressList", addressList);
		mv.addObject("member", member);
		
		mv.setViewName("shopping/shoppingPayment");
		
		return mv; 
		
	}
	
	// 결제완료 정보
	@PostMapping("mypage/myOrder")
	@ResponseBody
	public String paymentInfoInsert(Payment payment) {
		
		System.out.println(payment);
		
		int result = shoppingService.paymentInfoInsert(payment);
		
		if(result > 0) {
			return "결제 성공";
		} else {
			return "결제 실패";
		}
	}
	
	
	
}
