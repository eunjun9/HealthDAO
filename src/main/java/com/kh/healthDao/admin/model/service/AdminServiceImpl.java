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
import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.mypage.model.vo.MemberSound;


@Service("adminService")
public class AdminServiceImpl implements AdminService, CouponService, NoticeService, MemberSoundService{

	private final AdminMapper adminMapper; 
	
	@Autowired
	public AdminServiceImpl(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
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


	@Override
	public int memberSoundInsert(MemberSound ms) {
		return adminMapper.memberSoundInsert(ms);
	}

	@Override
	public int insertproductStock(Product product) {
		return adminMapper.insertproductStock(product);
	}

	@Override
	public int registProduct(Product product,String originFile1, String originFile2, String changeFile1, String changeFile2 ) {
		Map<String, Object> map = new HashMap<>();
		
		product.setFile_path("/images/upload/product/");
		product.setChange_file1(changeFile1);
		product.setChange_file2(changeFile2);
		
		map.put("product", product);
		map.put("originFile1", originFile1);
		map.put("originFile2", originFile2);
		
		int result1 = adminMapper.registProduct(product);
		int result2 = adminMapper.productFileInsert(map);
		int result3 = adminMapper.productFileInsertDB(product);
		int result4 = adminMapper.productInfoFileInsert(map);
		int result5 = adminMapper.productInfoFileInsertDB(product);
		int result = 0;
		
		if(result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0 && result5 > 0) {
			result = 1;
		}
		
		return result;
	}

	@Override
	public int registOption(Product product) {
		return adminMapper.registOption(product);
	}

	@Override
	public Map<String, Object> memberSoundList(int page) {
		int listCount = adminMapper.memberSoundListCount();
		Paging pi = new Paging(page, listCount, 5, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Coupon> memberSoundList = adminMapper.memberSoundList(pageRow);
		
		Map<String, Object> memberSound = new HashMap<>();
		
		memberSound.put("listCount", listCount);
		memberSound.put("memberSoundList", memberSoundList);
		memberSound.put("pi", pi);
		
		return memberSound;
	}

	@Override
	public MemberSound memberSoundDetail(int cNo) {
		return adminMapper.memberSoundDetail(cNo);
	}

	@Override
	public int memberSoundModify(MemberSound ms) {
		return adminMapper.memberSoundModify(ms);
	}

	@Override
	public List<Member> memberInfoView() {
		return adminMapper.memberInfoView();
	}

	@Override
	public List<Member> trainerInfoView() {
		return adminMapper.trainerInfoView();
	}

	@Override
	public int memberInfoMf(Member member) {
		return adminMapper.memberInfoMf(member);
	}

	@Override
	public int memberInfoCodeMf(Member member) {
		return adminMapper.memberInfoCodeMf(member);
	}

	@Override
	public int trainerInfoCodeMf(Member member) {
		return adminMapper.trainerInfoCodeMf(member);
	}


	/* @Override
	public List<Product> listProduct(Product product) {
		return  adminMapper.RegistProduct(product);
	}
	*/
	
	
	

}
