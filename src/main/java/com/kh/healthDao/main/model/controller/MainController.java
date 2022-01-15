package com.kh.healthDao.main.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.main.model.service.BannerService;
import com.kh.healthDao.main.model.vo.Banner;
import com.kh.healthDao.member.model.vo.UserImpl;
import com.kh.healthDao.mypage.model.service.CartService;
import com.kh.healthDao.mypage.model.vo.Cart;
import com.kh.healthDao.shopping.model.service.ShoppingService;
import com.kh.healthDao.shopping.model.vo.Shopping;


@Controller
public class MainController {
	/*
	 * @GetMapping(value= {"/", "/main"}) public String main() { return "main/main";
	 * }
	 */

	@GetMapping(value= {"/", "/main"})
	public ModelAndView findBannerRankList(ModelAndView mv) {
		
		List<Banner> bannerList = bannerService.bannerRankList();
		Map<String, Object> map = shoppingService.pdtList();

		mv.addObject("bannerList", bannerList);
		mv.addObject("recoList", map.get("recoList"));
		mv.addObject("recoCount", map.get("recoCount"));
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

	private BannerService bannerService;
	private ShoppingService shoppingService;
	private CartService cartService;
	
	@Autowired
	public MainController(BannerService bannerService, ShoppingService shoppingService, CartService cartService) {
		this.bannerService = bannerService;
		this.shoppingService = shoppingService;
		this.cartService = cartService;
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
	
	@ResponseBody
	@PostMapping("/banner/delete")
	public int deleteBanner(int[] addList, int[] addList2) {
		int result = 0;
		for(int i = 0; i < addList.length; i++) {
			result += bannerService.deleteBanner(addList[i], addList2[i]);			
		}
		System.out.println(result);
		
		return result;
	}

	@PostMapping("/banner/select")
	@ResponseBody
	public Banner modifyBanner(int main_no) {
		Banner banner = bannerService.bannerSelect(main_no);
		return banner;
	}

	@PostMapping("/banner/update")
	@ResponseBody
	public int updateBanner(int main_no, String main_name, String main_url, String imgUpload, String main_status, int main_rank) {
		System.out.println(imgUpload);
		
		Map<String, Object> map = new HashMap<>();
		map.put("main_no", main_no);
		map.put("main_name", main_name);
		map.put("main_url", main_url);
		map.put("imgUpload", imgUpload);
		map.put("main_status", main_status);
		map.put("main_rank", main_rank);
		
		int result = bannerService.bannerUpdate(map);
		return result;
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
		
		mv.addObject("pdtList", map.get("pdtList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("recoList", map.get("recoList"));
		mv.addObject("recoCount", map.get("recoCount"));
		mv.setViewName("mypage/mywish");
		return mv;
	}
	
	//헤더 장바구니 카운트
	@PostMapping("/main/cartCount")
	@ResponseBody
	public int cartCount(@AuthenticationPrincipal UserImpl userImpl) {
		if(userImpl == null) {
			return -1;
		}else {
			int userNo = userImpl.getUserNo();
			
			List<Cart> cartList = cartService.cartList(userNo);
			
			int count = cartList.size() + 1;
			System.out.print(count);
			
			return count;
		}
	}
	
}
