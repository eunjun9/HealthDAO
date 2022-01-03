package com.kh.healthDao.main.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.main.model.vo.Banner;
import com.kh.healthDao.main.model.vo.PageInfo;


@Mapper
public interface BannerMapper {
//	List<Banner> bannerAllList(PageInfo pi);
//	int getListCount();

	List<Banner> bannerAllList();
}
