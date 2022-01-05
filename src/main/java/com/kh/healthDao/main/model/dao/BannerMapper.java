package com.kh.healthDao.main.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.main.model.vo.Banner;
import com.kh.healthDao.main.model.vo.PageInfo;


@Mapper
public interface BannerMapper {

	List<Banner> bannerAllList();

	int insertBanner(Map<String, Object> map);

	int insertFile(Map<String, Object> map);

	int insertFileDB();
}
