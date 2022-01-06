package com.kh.healthDao.shopping.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.shopping.model.service.ShoppingService;




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
	public ModelAndView shoppingNewProduct(ModelAndView mv) {
		
		List<Product> shoppingList = shoppingService.ShoppingList();
		
		mv.addObject("shoppingList", shoppingList);
		mv.setViewName("shopping/shoppingNewProduct");
		
		return mv;
	}
	
	// 쇼핑 식품
	@GetMapping("/foodProduct")
	public ModelAndView shoppingFoodProduct(ModelAndView mv) {
		
		List<Product> shoppingList = shoppingService.foodShoppingList();
		
		mv.addObject("shoppingList", shoppingList);
		mv.setViewName("shopping/shoppingFoodProduct");
		
		return mv;
	}
	
	// 쇼핑 음료
	@GetMapping("/beverageProduct")
	public ModelAndView shoppingBeverageProduct(ModelAndView mv) {
		
		List<Product> shoppingList = shoppingService.beverageShoppingList();
		
		mv.addObject("shoppingList", shoppingList);
		mv.setViewName("shopping/shoppingBeverageProduct");
		
		return mv;
	}
	
	// 쇼핑 운동용품
	@GetMapping("/goodsProduct")
	public ModelAndView shoppingGoodsProduct(ModelAndView mv) {
		
		List<Product> shoppingList = shoppingService.goodsShoppingList();
		
		mv.addObject("shoppingList", shoppingList);
		mv.setViewName("shopping/shoppingGoodsProduct");
		
		return mv;
	}
	
//	@GetMapping("/detail")
//	public ModelAndView shoppingDetail(ModelAndView mv, @RequestParam int tNo) {
//		
//		Shopping trainer = ShoppingService.shoppingSelect(tNo);
//		
//		mv.addObject("trainer", trainer);
//		mv.setViewName("trainer/trainerDetail");
//		
//		return mv;
//	}

}
