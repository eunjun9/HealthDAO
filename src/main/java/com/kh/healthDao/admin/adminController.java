package com.kh.healthDao.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("inventoryList")
	public ModelAndView managerInventoryList(ModelAndView mv) {
		
		List<Product> ProductList = adminService.listProductInventory();
		
		mv.addObject("ProductList", ProductList);
		mv.setViewName("admin/inventoryList");
		
		return mv;
	}
	
	// 팝업
	@GetMapping("pLPopup")
	public String managerpLPopup() {
		
		return "admin/pLPopup";
	}
	
	// 팝업 수량 입력
	@PostMapping("pLPopupSu")
	public Map<String, String> pLPopupSu(@RequestBody Product product) {
		
		String msg = adminService.insertproductStock(product) > 0 ? "재고 등록 성공" : "재고 등록 실패";
		
		Map<String, String> map = new HashMap<>();
		
		map.put("msg", msg);
		
		return map;
	}
	
	
}
