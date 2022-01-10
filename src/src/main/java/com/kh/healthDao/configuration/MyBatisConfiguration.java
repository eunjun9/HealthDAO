package com.kh.healthDao.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages="com.kh.healthDao", annotationClass=Mapper.class)
public class MyBatisConfiguration {

}
