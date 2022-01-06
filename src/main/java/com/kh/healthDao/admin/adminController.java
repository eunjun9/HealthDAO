package com.kh.healthDao.admin;


import java.util.List;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.healthDao.admin.model.service.AdminService;
import com.kh.healthDao.admin.model.service.CouponService;
import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.manager.model.vo.Qna;


@Controller
@RequestMapping("/admin/*")
public class adminController {
	
	private AdminService adminService;
	private CouponService couponService;
	
	@Autowired
	public adminController(AdminService adminService, CouponService couponService) {
		this.adminService = adminService;
		this.couponService = couponService;
	}
	
	@GetMapping("/productRegist")
	public String registMain() {
		return "admin/adminProductRegist";
	}
	
	/* 상품 등록 */
	@PostMapping("productRegist")
	@ResponseBody
	public ModelAndView registProduct(Product product, ModelAndView mv) {
		int result = adminService.RegistProduct(product);
		if(result > 0) {
			mv.setViewName("redirect:/admin/productRegist");
			return mv;
			
		}else {
			mv.setViewName("redirect:/ProductRegist");
			return mv;
		}
	}
	
	@GetMapping("inventoryList")
	public ModelAndView managerInventoryList(ModelAndView mv) {
		
		List<Product> ProductList = adminService.listProductInventory();

		
		mv.addObject("ProductList", ProductList);
		mv.setViewName("admin/inventoryList");
		

		return mv;
	}
	
	// 팝업
	@GetMapping("/pLPopup")
	public String managerpLPopup() {
		
		return "admin/pLPopup";
	}
	
	// 팝업 수량 입력
	@PostMapping("/pLPopupSu")
	@ResponseBody
	public ModelAndView pLPopupSu(ModelAndView mv, Product product) {
		
		int result = adminService.pLPopupSu(product);
		
		if(result > 0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}

		return mv;
	}
	
	// 쿠폰등록
	@PostMapping("/couponInput")
	public String couponInput(Coupon coupon, RedirectAttributes rttr) {
		String msg = couponService.couponInput(coupon) > 0 ? "쿠폰 등록 성공" : "쿠폰 등록 실패";
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/admin/couponList";
	}
	
	// 쿠폰리스트
	@GetMapping("/couponList")
	public ModelAndView couponList(ModelAndView mv, @RequestParam int page) {
		Map<String, Object> map = couponService.allCouponList(page);
		
		mv.addObject("couponList", map.get("couponList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("admin/couponList");
		
		return mv;
	}

}
