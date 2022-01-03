package com.example.demo.main.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.main.model.vo.Banner;

@Mapper
public interface BannerMapper {
	List<Banner> bannerAllList();
}
