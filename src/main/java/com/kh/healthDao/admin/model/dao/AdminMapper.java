package com.kh.healthDao.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.admin.model.vo.Category;
import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.admin.model.vo.Option;
import com.kh.healthDao.admin.model.vo.Notice;
import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.mypage.model.vo.MemberSound;
import com.kh.healthDao.mypage.model.vo.Qna;



@Mapper
public interface AdminMapper {

	public void register(Product product);

	// 상품 등록
	public int registProduct(Product product);

	// 상품 재고
	public List<Product> listProductInventory();

	public int insertproductStock(Product product);

	public int getinventoryCount();

	public List<Product> listProductInventory(Map<String, Object> pageRow);

	public Product pLPopupSu(Product product);

	// 재고 추가
	public int stockPlus(Product product);










	// 쿠폰등록
	public int couponInput(Coupon coupon);

	// 모든 쿠폰 리스트 갯수
	public int allCouponListCount();

	// 모든 쿠폰 리스트
	public List<Coupon> allCouponList(Map<String, Object> pageRow);
	
	// 공지사항 등록
	public int noticeInsert(Notice notice);
	
	// 모든 공지사항 갯수 
	public int allNoticeListCount();
	
	// 공지사항 리스트
	public List<Coupon> allNoticeList(Map<String, Object> pageRow);
	
	// 공지사항 디테일
	public Notice noticeDetail(int nNo);
	// 공지사항 수정
	public int noticeModify(Notice notice);
	// 공지사항 조회수
	public int viewUpdate(int nNo);
	// 공지사항 최근 5개
	public List<Notice> newfiveNoticeList();

	public int memberSoundInsert(MemberSound ms);

	public int registCategory(Product product);

	public int registOption(Product product);

	public int memberSoundListCount();

	public List<Coupon> memberSoundList(Map<String, Object> pageRow);

	public MemberSound memberSoundDetail(int cNo);

	public int memberSoundModify(MemberSound ms);

	public List<Member> memberInfoView();

	public List<Member> trainerInfoView();

	public int memberInfoMf(Member member);

	public int memberInfoCodeMf(Member member);


	

	// public List<Product> RegistProduct(Product product);

	
}
