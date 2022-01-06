package com.kh.healthDao.main.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.healthDao.main.model.service.BannerService;
import com.kh.healthDao.main.model.vo.Banner;


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
	
	@GetMapping("/reco")
	public String reco() {
		return "admin/reco";
	}
	@GetMapping("/time")
	public String time() {
		return "admin/time";
	}
	@GetMapping("/file")
	public String file() {
		return "admin/fileuploadTest";
	}


	private BannerService bannerService;
	
	@Autowired
	public MainController(BannerService bannerService) {
		this.bannerService = bannerService;
	}
	
//	왜 안돼
//	@GetMapping("/banner")
//	public ModelAndView findBannerList(ModelAndView mv, @RequestParam int page){
//		page=1;
//		Map<String, Object> map = bannerService.bannerAllList(page);
//
//		mv.addObject("pi", map.get("pi"));
//		mv.addObject("bannerList", map.get("bannerList"));
//		mv.setViewName("admin/banner");
//		return mv;
//	}
	
	@GetMapping("/banner")
	public ModelAndView findBannerList(ModelAndView mv) {
		
		List<Banner> bannerList = bannerService.bannerAllList();
		
		mv.addObject("bannerList", bannerList);
		mv.setViewName("admin/banner");
		
		return mv;
	}
	
	@ResponseBody
	@PostMapping("/banner/delete")
	public ModelAndView deleteBanner(ModelAndView mv, @RequestBody String[] arr) {
		
		System.out.println(arr);
		int result = bannerService.deleteBanner(arr);
		
		mv.addObject("result", result);
		mv.setViewName("banner/bannerDetail");
		
		return mv;
	}
}
