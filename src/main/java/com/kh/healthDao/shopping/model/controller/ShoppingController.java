package com.kh.healthDao.shopping.model.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.shopping.model.service.ShoppingService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/shopping/*")
public class ShoppingController {

	private ShoppingService shoppingService;
	
	@Autowired
	public ShoppingController(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}
	
	
	// 쇼핑 랭킹페이지
	@GetMapping("/ranking")
	public ModelAndView shoppingRanking(ModelAndView mv) {
		
		//List<Shopping> shoppingList = shoppingService.ShoppingList();
		
		// mv.addObject("shoppingList", shoppingList);
		mv.setViewName("shopping/shoppingRanking");
		
		return mv;
	}
	
	// 쇼핑 신상품
	@GetMapping("/newProduct")
	public ModelAndView shoppingNewProduct(ModelAndView mv, @RequestParam int page) {
		Map<String, Object> map = shoppingService.ShoppingList(page);
		
		mv.addObject("shoppingList", map.get("shoppingList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		
		mv.setViewName("shopping/shoppingNewProduct");
		
		return mv;
	}
	
	// 쇼핑 식품
	@GetMapping("/foodProduct")
	public ModelAndView shoppingFoodProduct(ModelAndView mv, @RequestParam int page) {
		Map<String, Object> map = shoppingService.foodShoppingList(page);
		
		mv.addObject("shoppingList", map.get("shoppingList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		
		mv.setViewName("shopping/shoppingFoodProduct");
		
		return mv;
	}
	
	// 쇼핑 음료
	@GetMapping("/beverageProduct")
	public ModelAndView shoppingBeverageProduct(ModelAndView mv, @RequestParam int page) {
		Map<String, Object> map = shoppingService.beverageShoppingList(page);
		
		mv.addObject("shoppingList", map.get("shoppingList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		
		mv.setViewName("shopping/shoppingBeverageProduct");
		
		return mv;
	}
	
	// 쇼핑 운동용품
	@GetMapping("/goodsProduct")
	public ModelAndView shoppingGoodsProduct(ModelAndView mv, @RequestParam int page) {
		Map<String, Object> map = shoppingService.goodsShoppingList(page);
		
		mv.addObject("shoppingList", map.get("shoppingList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		
		mv.setViewName("shopping/shoppingGoodsProduct");
		
		return mv;
	}
	
	// 쇼핑 상세페이지
	@GetMapping("/detail")
	public ModelAndView shoppingDetail(ModelAndView mv, @RequestParam int productNo) {
		
		Product shoppingDetail = shoppingService.shoppingDetail(productNo);
		
		mv.addObject("shoppingDetail", shoppingDetail);
		mv.setViewName("shopping/shoppingProductDetail");
		
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

	@PostMapping("/payment") 
	public String shoppingPaymentInfo(@RequestParam("select1") String select1, @RequestParam("amount") int amount, @RequestParam("sum") int sum, Model model, @RequestParam int productNo ) { 
		
		log.info(select1 + "select1" + amount + "amount" + sum + "sum");
		Product shoppingPayment = shoppingService.shoppingPayment(productNo);
		
		model.addAttribute("shoppingPayment", shoppingPayment);
		model.addAttribute("select1", select1);
		model.addAttribute("amount", amount);
		model.addAttribute("sum", sum);
		
		return "/shopping/shoppingPayment"; 
	}
	

	
	
	
	
}
