package com.example.demo.main.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.main.model.dao.BannerMapper;
import com.example.demo.main.model.vo.Banner;

@Service("BannerService")
public class BannerServiceImpl implements BannerService{
	
	private final BannerMapper bannerMapper;

	@Autowired
	public BannerServiceImpl(BannerMapper bannerMapper) {
		this.bannerMapper = bannerMapper;
	}
	
	@Override
	public List<Banner> BannerList() {
		return bannerMapper.BannerList();
	}

	@Override
	public Banner BannerSelect(int main_no) {
		return bannerMapper.BannerSelect(main_no);
	}

	@Override
	public List<Banner> findAllBanner() {
		return bannerMapper.findAllBanner();
	}


}
