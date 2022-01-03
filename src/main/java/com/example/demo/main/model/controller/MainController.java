package com.example.demo.main.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.main.model.service.BannerService;
import com.example.demo.main.model.vo.Banner;

@Controller
public class MainController {

	@GetMapping(value= {"/", "/main"})
	public String main() {
		return "main/main";
	}
	
	@PostMapping(value="/")
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


	private BannerService bannerService;
	
	@Autowired
	public MainController(BannerService bannerService) {
		this.bannerService = bannerService;
	}
	
	@GetMapping("/banner")
	public ModelAndView findBannerList(ModelAndView mv){
		
		List<Banner> bannerList = bannerService.bannerAllList();
		
		mv.addObject("bannerList", bannerList);
		mv.setViewName("admin/banner");
		return mv;
	}
	
}
