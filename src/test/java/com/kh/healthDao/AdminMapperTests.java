package com.kh.healthDao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.healthDao.admin.model.dao.AdminMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTests {
	
	@Autowired
	private AdminMapper mapper;
	
	public void Product() {
		
		com.kh.healthDao.admin.model.vo.Product product = new com.kh.healthDao.admin.model.vo.Product();
		
	}

}
