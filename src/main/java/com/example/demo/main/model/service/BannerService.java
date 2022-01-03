package com.example.demo.main.model.service;

import java.util.List;

import com.example.demo.main.model.vo.Banner;

public interface BannerService {
	
	List<Banner> findAllBanner();
	
	List<Banner> BannerList();

	Banner BannerSelect(int main_no);

}
