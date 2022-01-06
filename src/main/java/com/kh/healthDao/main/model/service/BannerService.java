package com.kh.healthDao.main.model.service;

import java.util.List;

import com.kh.healthDao.main.model.vo.Banner;

public interface BannerService {

	List<Banner> bannerAllList();

	int insertBanner(Banner banner, String originFileName, String path, String savedName);

}
