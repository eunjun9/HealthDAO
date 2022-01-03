package com.kh.healthDao.main.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.main.model.dao.BannerMapper;
import com.kh.healthDao.main.model.vo.Banner;


@Service("BannerService")
public class BannerServiceImpl implements BannerService{
	
	private final BannerMapper bannerMapper;

	@Autowired
	public BannerServiceImpl(BannerMapper bannerMapper) {
		this.bannerMapper = bannerMapper;
	}

	@Override
	public List<Banner> bannerAllList() {
		return bannerMapper.bannerAllList();
	}
	

}
