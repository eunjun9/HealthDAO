package com.kh.healthDao.main.model.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.main.model.service.BannerService;
import com.kh.healthDao.main.model.vo.Banner;
import com.kh.healthDao.member.model.vo.UserImpl;
import com.kh.healthDao.mypage.model.service.CartService;
import com.kh.healthDao.mypage.model.vo.Cart;
import com.kh.healthDao.shopping.model.service.ShoppingService;
import com.kh.healthDao.trainer.model.service.TrainerService;
import com.kh.healthDao.trainer.model.vo.Trainer;

@Controller
public class MainController {

	private BannerService bannerService;
	private ShoppingService shoppingService;
	private CartService cartService;
	private TrainerService trainerService;
	
	@Autowired
	public MainController(BannerService bannerService, ShoppingService shoppingService, CartService cartService, TrainerService trainerService) {
		this.bannerService = bannerService;
		this.shoppingService = shoppingService;
		this.cartService = cartService;
		this.trainerService = trainerService;
	}
	
	
	@GetMapping(value= {"/", "/main"})
	public ModelAndView findBannerRankList(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {
		List<Banner> bannerList = bannerService.bannerRankList();
		List<Trainer> trainList = trainerService.trainerRankList();
		Map<String, Object> map = shoppingService.pdtList();

		mv.addObject("bannerList", bannerList);
		mv.addObject("bannerSize", bannerList.size());
		mv.addObject("trainList", trainList);
		mv.addObject("recoList", map.get("recoList"));
		mv.addObject("pdtList", map.get("pdtList"));
		mv.addObject("recoCount", map.get("recoCount"));

		int userNo = 0;		
		if(userImpl != null) {
			// 찜한 상품 확인
			userNo = userImpl.getUserNo();
			List like = shoppingService.likeList(userNo);
			mv.addObject("likeList", like);
			
			// 장바구니 확인
			List<Cart> cartList = cartService.cartList(userNo);
			mv.addObject("cartCount", cartList.size());
		}else {
			mv.addObject("cartCount", 0);
		}

		mv.setViewName("main/main");
		return mv;
	}
	
	@PostMapping(value= {"/", "/main"})
	public String redirectMain() {
		return "redirect:/";
	}
	
	@GetMapping("/time")
	public String time() {
		return "admin/time";
	}

	@GetMapping("/error500")
	public String error500() {
		return "error/error500";
	}
	
	@GetMapping("/banner")
	public ModelAndView findBannerList(ModelAndView mv, @RequestParam int page) {
		
		Map<String, Object> map = bannerService.findBannerList(page);
		
		mv.addObject("bannerList", map.get("bannerList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("admin/banner");
		
		return mv;
	}

	@PostMapping("/banner/select")
	@ResponseBody
	public Banner modifyBanner(int main_no) {
		Banner banner = bannerService.bannerSelect(main_no);
		return banner;
	}


	// 추천상품 검색
	@GetMapping("/reco")
	public ModelAndView shoppingNewProduct(ModelAndView mv) {
		Map<String, Object> map = shoppingService.pdtList();
		
		mv.addObject("pdtList", map.get("pdtList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("recoList", map.get("recoList"));
		mv.addObject("recoCount", map.get("recoCount"));
		mv.setViewName("admin/reco");
		return mv;
	}

	// 상품 선택
	@ResponseBody
	@PostMapping("/reco/detail")
	public Product detailPdt(int productNo) {
		Product pdt = shoppingService.detailPdt(productNo);
		return pdt;
	}
	
	// 추천상품 추가
	@ResponseBody
	@PostMapping("/reco/insert")
	public int insertPdt(int productNo, int productRank) {
		int result = shoppingService.insertReco(productNo, productRank);
		return result;
	}

	// 추천 상품 선택
	@ResponseBody
	@PostMapping("/reco/select")
	public Product selectReco(int productNo) {
		Product pdt = shoppingService.selectReco(productNo);
		return pdt;
	}

	// 추천 상품 수정
	@ResponseBody
	@PostMapping("/reco/modify")
	public int modifyReco(int productNo, int productRank) {
		int result = shoppingService.modifyReco(productNo, productRank);
		return result;
	}

	// 추천 상품 삭제
	@ResponseBody
	@PostMapping("/reco/delete")
	public int deleteBanner(int[] addList) {
		int result = 0;
		for(int i = 0; i < addList.length; i++) {
			result += shoppingService.deleteReco(addList[i]);			
		}
		System.out.println(result);
		
		return result;
	}

	// 찜한 상품 추가
	@ResponseBody
	@PostMapping("insertWish")
	public int insertWishPdt(int productNo, @AuthenticationPrincipal UserImpl userImpl) {
		if(userImpl == null) {
			return 0;
		}else {
			int userNo = userImpl.getUserNo();
			int result = 1;
			Product wishChk = shoppingService.wishChk(productNo, userNo);
			if(wishChk == null) {
				result = shoppingService.insertWish(productNo, userNo);
			}else {
				result = shoppingService.deleteWish(productNo, userNo)+1;
			}
			return result;
		}
	}

	// 찜한 상품 삭제
	@ResponseBody
	@PostMapping("deleteWish")
	public int deleteWishPdt(int[] addList, @AuthenticationPrincipal UserImpl userImpl) {
		int result = 0;

		int userNo = userImpl.getUserNo();
		for(int i = 0; i < addList.length; i++) {
			result += shoppingService.deleteWishPdt(addList[i], userNo);			
		}
		System.out.println(result);
		
		return result;
	}
	
	// 최근 본 상품
	@ResponseBody
	@PostMapping("/recentPdt")
	public List<Product> recentPdt(int[] addList) {
		List<Product> recentList = shoppingService.recentList(addList);
		return recentList;
	}
	
	// 찜한 상품
	@ResponseBody
	@GetMapping("/mypage/wish")
	public ModelAndView wishProduct(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		System.out.println(userNo);
		
		Map<String, Object> map = shoppingService.wishList(userNo);
		
		mv.addObject("wishList", map.get("wishList"));
		mv.addObject("wishListCount", map.get("wishListCount"));
		mv.setViewName("mypage/mywish");

		// 찜한 상품 확인
		// int userNo = 0;		
		if(userImpl != null) {
			//userNo = userImpl.getUserNo();
			List like = shoppingService.likeList(userNo);
			mv.addObject("likeList", like);
		}
		return mv;
	}
	
	// 장바구니 카운트
	@GetMapping("/cartCountSelect")
	@ResponseBody
	public int cartCount(@AuthenticationPrincipal UserImpl userImpl) {
		if(userImpl != null) {
			int userNo = userImpl.getUserNo();
			List<Cart> cartList = cartService.cartList(userNo);
			return cartList.size();
		}else {
			return 0;
		}
	}
}
