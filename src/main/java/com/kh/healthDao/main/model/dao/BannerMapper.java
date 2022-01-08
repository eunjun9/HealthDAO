package com.kh.healthDao.main.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.main.model.vo.Banner;


@Mapper
public interface BannerMapper {

	List<Banner> bannerAllList();

	int insertBanner(Map<String, Object> map);

	int insertFile(Map<String, Object> map);

	int insertFileDB();

	int deleteBanner(String[] arr);

	int deleteBanner2(String[] arr);

	int deleteBanner3(String[] arr);

	List<Banner> bannerRankList();

	int getBannerListCount();

	List<Banner> findBannreList(Map<String, Object> pageRow);

	Banner bannerSelect(int main_no);

	int bannerUpdate(Map<String, Object> map);

	int bannerImgUpdate(Map<String, Object> map);
}
