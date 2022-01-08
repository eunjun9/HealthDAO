package com.kh.healthDao.main.model.service;

import java.util.List;
import java.util.Map;

import com.kh.healthDao.main.model.vo.Banner;

public interface BannerService {

	int insertBanner(Banner banner, String originFileName, String path, String savedName);

	List<Banner> bannerRankList();

	Map<String, Object> findBannerList(int page);

	Banner bannerSelect(int main_no);

	int bannerUpdate(Map<String, Object> map);

	int deleteBanner(int value);
}
