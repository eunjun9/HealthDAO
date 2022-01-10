package com.kh.healthDao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.kh.healthDao.admin.model.dao.AdminMapper;


@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTests {
	
	@Autowired
	private AdminMapper mapper;
	
	public void Product() {
		
		com.kh.healthDao.admin.model.vo.Product product = new com.kh.healthDao.admin.model.vo.Product();
		
	}

}
