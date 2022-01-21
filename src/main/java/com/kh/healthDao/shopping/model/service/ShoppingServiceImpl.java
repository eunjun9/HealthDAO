package com.kh.healthDao.shopping.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.common.model.vo.Paging;
import com.kh.healthDao.manager.model.vo.Payment;
import com.kh.healthDao.member.model.vo.UserImpl;
import com.kh.healthDao.mypage.model.service.MyInfoService;
import com.kh.healthDao.mypage.model.vo.Address;
import com.kh.healthDao.shopping.model.dao.ShoppingMapper;


@Service("ShoppingService")
public class ShoppingServiceImpl implements ShoppingService{
	
	private final ShoppingMapper shoppingMapper;
	private MyInfoService myInfoService;

	@Autowired
	public ShoppingServiceImpl(ShoppingMapper shoppingMapper, MyInfoService myInfoService) {
		this.shoppingMapper = shoppingMapper;
		this.myInfoService = myInfoService;
	}

	@Override
	public List<Product> recoRankList() {
		return shoppingMapper.recoRankList();
	}

	@Override
	public List<Product> recentList(int[] addList) {
		List<Product> pdtList = new ArrayList<>();
		Product pdt = new Product();		
		//System.out.println("length : "+addList.length);

		for(int i=0; i<addList.length; i++) {
			pdt = shoppingMapper.recentList(addList[i]);
			pdtList.add(pdt);
		}
		// System.out.println("리스트 : " + pdtList);
		return pdtList;
	}
	
	@Override
	public Map<String, Object> pdtList() {
		int listCount = shoppingMapper.pdtListCount();
		List<Product> pdtList = shoppingMapper.pdtList();
		List<Product> recoList = shoppingMapper.recoList();
		int recoCount = shoppingMapper.recoListCount();
		
		Map<String, Object> pdtMap = new HashMap<>();
		pdtMap.put("listCount", listCount);
		pdtMap.put("pdtList", pdtList);
		pdtMap.put("recoList", recoList);
		pdtMap.put("recoCount", recoCount);
		
		return pdtMap;
	}
	
	// 찜한 상품
	public Map<String, Object> wishList(int userNo) {
		int listCount = shoppingMapper.wishListCount(userNo);
		List<Product> wishList = shoppingMapper.wishList(userNo);
		
		Map<String, Object> wishMap = new HashMap<>();
		wishMap.put("wishListCount", listCount);
		wishMap.put("wishList", wishList);
		
		return wishMap;
	}

	@Override
	public Product detailPdt(int productNo) {
		return shoppingMapper.detailPdt(productNo);
	}

	
	/* 추천 상품 */
	@Override
	public int insertReco(int productNo, int productRank) {
		return shoppingMapper.insertReco(productNo, productRank);
	}

	@Override
	public Product selectReco(int productNo) {
		return shoppingMapper.selectReco(productNo);
	}

	@Override
	public int modifyReco(int productNo, int productRank) {
		return shoppingMapper.modifyReco(productNo, productRank);
	}

	@Override
	public int deleteReco(int productNo) {
		return shoppingMapper.deleteReco(productNo);
	}
	
	/* 찜한 상품 */
	@Override
	public Product wishChk(int productNo, int userNo) {
		return shoppingMapper.wishChk(productNo, userNo);
	}
	@Override
	public int insertWish(int productNo, int userNo) {
		return shoppingMapper.insertWish(productNo, userNo);
	}
	@Override
	public int deleteWish(int productNo, int userNo) {
		return shoppingMapper.deleteWish(productNo, userNo);
	}
	@Override
	public int deleteWishPdt(int productNo, int userNo) {
		return shoppingMapper.deleteWish(productNo, userNo);
	}
	
	// 상품 리스트에서 찜한 상품들 확인
	@Override
	public List likeList(int userNo) {
		return shoppingMapper.findLikeList(userNo);
	}
  
	/* 상품 음료 */
	@Override
	public List<Product> beverageShoppingList() {
		return shoppingMapper.beverageShoppingList();
	}

	/* 상품 운동기구 */
	@Override
	public List<Product> goodsShoppingList() {
		return shoppingMapper.goodsShoppingList();
	}

	/* 상품 상세페이지 */
	@Override
	public Product shoppingDetail(int productNo) {
		return shoppingMapper.shoppingDetail(productNo);
	}

	/*신상품*/
	@Override
	public Map<String, Object> ShoppingList(int page) {
		int listCount = shoppingMapper.shoppingListCount();
		Paging pi = new Paging(page, listCount, 5, 12);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Product> shoppingList = shoppingMapper.shoppingList(pageRow);
		
		Map<String, Object> newProductMap = new HashMap<>();
		
		newProductMap.put("listCount", listCount);
		newProductMap.put("shoppingList", shoppingList);
		newProductMap.put("pi", pi);
		
		return newProductMap;
	}

	/*식품*/
	@Override
	public Map<String, Object> foodShoppingList(int page) {
		int listCount = shoppingMapper.foodShoppingListCount();
		Paging pi = new Paging(page, listCount, 5, 12);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Product> shoppingList = shoppingMapper.foodShoppingList(pageRow);
		
		Map<String, Object> foodProductMap = new HashMap<>();
		
		foodProductMap.put("listCount", listCount);
		foodProductMap.put("shoppingList", shoppingList);
		foodProductMap.put("pi", pi);
		
		return foodProductMap;
	}

	/* 상품 음료 페이지 */
	@Override
	public Map<String, Object> beverageShoppingList(int page) {
		int listCount = shoppingMapper.beverageShoppingListCount();
		Paging pi = new Paging(page, listCount, 5, 12);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Product> shoppingList = shoppingMapper.beverageShoppingList(pageRow);
		
		Map<String, Object> beverageProductMap = new HashMap<>();
		
		beverageProductMap.put("listCount", listCount);
		beverageProductMap.put("shoppingList", shoppingList);
		beverageProductMap.put("pi", pi);
		
		return beverageProductMap; 
		
	}

	/* 상품 운동기구 */
	@Override
	public Map<String, Object> goodsShoppingList(int page) {
		int listCount = shoppingMapper.goodsShoppingListCount();
		Paging pi = new Paging(page, listCount, 5, 12);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Product> shoppingList = shoppingMapper.goodsShoppingList(pageRow);
		
		Map<String, Object> goodsProductMap = new HashMap<>();
		
		goodsProductMap.put("listCount", listCount);
		goodsProductMap.put("shoppingList", shoppingList);
		goodsProductMap.put("pi", pi);
		
		return goodsProductMap; 
	}

	/* 상품 주문 */
	@Override
	public Product shoppingPayment(int productNo) {
		return shoppingMapper.shoppingPayment(productNo);
	}

	/*배송지 관리*/
	@GetMapping("/deli")
	public ModelAndView deliView(ModelAndView mv, @AuthenticationPrincipal UserImpl userImpl) {
		int userNo = userImpl.getUserNo();
		
		List<Address> addressList = myInfoService.deliView(userNo);
		
		mv.addObject("addressList", addressList);
		mv.setViewName("mypage/deliModify");
		
		return mv;
	}

	@Override
	public List<Address> deliView(int userNo) {
		return shoppingMapper.deliView(userNo);
	}

	@Override
	public List<Product> searchList(String searchPdt) {
		return shoppingMapper.searchList(searchPdt);
	}

	@Override
	public int paymentInfoInsert(List<Payment> paymentList) {
		
		int result = 0;
		
		
		int result1 = shoppingMapper.paymentInfoInsert(paymentList.get(0));
		
		// 디테일 인서트
		int productCount = paymentList.get(0).getProductList().size();
		int result2 = 0;
		for(int i = 0; i < productCount; i++) {
			Product product = paymentList.get(0).getProductList().get(i);
			result2 = shoppingMapper.paymentDetailInsert(product);			
		}
		
		if(result1 > 0 && result2 > 0) {
			result = 1;
		}
		
		return result;
	}

	@Override
	public List<Product> shoppingReview(int productNo) {
		return shoppingMapper.shoppingReview(productNo);
	}

	// 리뷰 개수
	@Override
	public int sumReview(int productNo) {

		String result = shoppingMapper.sumReview(productNo);
		int sumReview = 0;

		if(result != null) {
			sumReview = Integer.parseInt(result);
		}
		
		return sumReview;

	}

	// 리뷰 평균
	@Override
	public float avgStar(int productNo) {
		String result = shoppingMapper.avgStar(productNo);
		float avgStar = 0;
		if(result != null) {
			avgStar = Float.parseFloat(result);
		}
	
		return avgStar;
	}

	@Override
	public Map<String, Object> rankList() {
		
		List<Product> rankAll = shoppingMapper.rankAllList();
		List<Product> rankFood = shoppingMapper.rankSelectList("식품");
		List<Product> rankBeverage = shoppingMapper.rankSelectList("음료");
		List<Product> rankGoods = shoppingMapper.rankSelectList("운동기구");
		
		Map<String, Object> rankList = new HashMap<>();

		rankList.put("rankAll", rankAll);
		rankList.put("rankFood", rankFood);
		rankList.put("rankBeverage", rankBeverage);
		rankList.put("rankGoods", rankGoods);

//		System.out.println("ddd" + rankList.get("rankAll"));
//		System.out.println("ddd" + rankList.get("rankFood"));
//		System.out.println("ddd" + rankList.get("rankBeverage"));
//		System.out.println("ddd" + rankList.get("rankGoods"));
		
		return rankList;

	}
}
