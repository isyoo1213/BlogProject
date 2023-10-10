package com.fastcampus.jblog.biz.category;

import java.util.List;

import com.fastcampus.jblog.biz.blog.BlogVO;

public interface CategoryService {

	void insertCategory(CategoryVO categoryVO);
	
	void updateCategory(CategoryVO categoryVO);
	
	void deleteCategory(CategoryVO categoryVO);
	
	CategoryVO getCategory(CategoryVO categoryVO);
	
	List<CategoryVO> getCategoryList(BlogVO blogVO);


}
