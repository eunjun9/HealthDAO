package com.kh.healthDao.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	
	private int categoryId;			// 카테고리 번호
	private String categoryName;	// 카테고리 이름

}
