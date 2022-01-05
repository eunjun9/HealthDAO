package com.kh.healthDao.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	
	
}
