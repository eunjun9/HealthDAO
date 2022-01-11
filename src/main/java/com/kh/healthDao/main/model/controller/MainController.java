package com.kh.healthDao.main.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		mv.addObject("bannerList", bannerList);
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
	
	@Autowired
	public MainController(BannerService bannerService, ShoppingService shoppingService) {
		this.bannerService = bannerService;
		this.shoppingService = shoppingService;
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
	
}
