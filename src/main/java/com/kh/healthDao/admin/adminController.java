package com.kh.healthDao.admin;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.healthDao.admin.model.service.AdminService;
import com.kh.healthDao.admin.model.vo.Product;


@Controller
@RequestMapping("/admin/*")
public class adminController {
	
	private AdminService adminService;
	
	@Autowired
	public adminController(AdminService adminService) {
		this.adminService = adminService;
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
	
	// 페이징 된 리스트
	@GetMapping("inventoryList")
	public ModelAndView managerInventoryList(ModelAndView mv, @RequestParam int page) {
		
		Map<String, Object> map = adminService.inventoryPaging(page);
		
		mv.addObject("ProductList", map.get("ProductList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("admin/inventoryList");

		return mv;
	}
	
	// 팝업 수량 입력
	@PostMapping("/pLPopupSu")
	@ResponseBody
	public ModelAndView pLPopupSu(Product product, ModelAndView mv) {
		System.out.println(product.getProductStock());
		System.out.println(product.getProductNo());
		
		int result = adminService.stockPlus(product);
		
		mv.setViewName("redirect:/");
		return mv;
	}
	
	
}
