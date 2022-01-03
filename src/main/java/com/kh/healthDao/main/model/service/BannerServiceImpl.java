package com.kh.healthDao.main.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.main.model.dao.BannerMapper;
import com.kh.healthDao.main.model.vo.Banner;
import com.kh.healthDao.main.model.vo.PageInfo;


@Service("BannerService")
public class BannerServiceImpl implements BannerService{
	
	private final BannerMapper bannerMapper;

	@Autowired
	public BannerServiceImpl(BannerMapper bannerMapper) {
		this.bannerMapper = bannerMapper;
	}


//	@Override
//	public Map<String, Object> bannerAllList(int page) {
//		
//		int listCount = bannerMapper.getListCount();
//		
//		PageInfo pi = new PageInfo(page, listCount, 10, 10);
//		
//		List<Banner> bannerList = bannerMapper.bannerAllList(pi);
//		
//		Map<String, Object> returnMap = new HashMap<>();
//		
//		returnMap.put("pi", pi);
//		returnMap.put("bannerList", bannerList);
//		
//		return returnMap;
//	}


	@Override
	public List<Banner> bannerAllList() {
		return bannerMapper.bannerAllList();
	}
	

}
