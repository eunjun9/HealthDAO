package com.kh.healthDao.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.admin.model.dao.AdminMapper;
import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.admin.model.vo.Notice;
import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.common.model.vo.Paging;
import com.kh.healthDao.mypage.model.vo.Qna;


@Service("adminService")
public class AdminServiceImpl implements AdminService, CouponService, NoticeService{

	private final AdminMapper adminMapper; 
	
	@Autowired
	public AdminServiceImpl(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	
	// 상품 등록
	@Override
	public int RegistProduct(Product product) {
		return adminMapper.RegistProduct(product);
	}

	
	@Override
	public Map<String, Object> inventoryPaging(int page) {
		int listCount = adminMapper.getinventoryCount();
		Paging pi = new Paging(page, listCount, 5, 5);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Product> ProductList = adminMapper.listProductInventory(pageRow);
		
		Map<String, Object> inv = new HashMap<>();
		
		inv.put("listCount", listCount);
		inv.put("ProductList", ProductList);
		inv.put("pi", pi);
		
		return inv;
	}


	@Override
	public Product pLPopupSu(Product product) {
		
		return adminMapper.pLPopupSu(product);
	}


	@Override
	public int stockPlus(Product product) {
		return adminMapper.stockPlus(product);
	}




	@Override
	public int couponInput(Coupon coupon) {
		return adminMapper.couponInput(coupon);
	}



	@Override
	public Map<String, Object> allCouponList(int page) {
		int listCount = adminMapper.allCouponListCount();
		Paging pi = new Paging(page, listCount, 5, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Coupon> couponList = adminMapper.allCouponList(pageRow);
		
		Map<String, Object> coupon = new HashMap<>();
		
		coupon.put("listCount", listCount);
		coupon.put("couponList", couponList);
		coupon.put("pi", pi);
		
		return coupon;
	}

	@Override
	public void registNewProduct(Product newProduct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int noticeInsert(Notice notice) {		
		return adminMapper.noticeInsert(notice);
	}

	@Override
	public Map<String, Object> allNoticeList(int page) {
		int listCount = adminMapper.allNoticeListCount();
		Paging pi = new Paging(page, listCount, 5, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Coupon> noticeList = adminMapper.allNoticeList(pageRow);
		
		Map<String, Object> notice = new HashMap<>();
		
		notice.put("listCount", listCount);
		notice.put("noticeList", noticeList);
		notice.put("pi", pi);
		
		return notice;
	}

	@Override
	public Notice noticeDetail(int nNo) {	
		return adminMapper.noticeDetail(nNo);
	}

	@Override
	public int noticeModify(Notice notice) {
		return adminMapper.noticeModify(notice);
	}

	@Override
	public int viewUpdate(int nNo) {		
		return adminMapper.viewUpdate(nNo);
	}

	@Override
	public List<Notice> newfiveNoticeList() {
		return adminMapper.newfiveNoticeList();
	}

	
	
	
	

}
