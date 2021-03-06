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

	int deleteBanner(int value);

	int deleteBanner2(int value);

	int deleteBanner3(int value);

	List<Banner> bannerRankList();

	int getBannerListCount();

	List<Banner> findBannreList(Map<String, Object> pageRow);

	Banner bannerSelect(int main_no);

	String findFileName(int main_no);

	int updateOnlyBanner(Banner banner);

	int updateFileBanner(Map<String, Object> map);
}
