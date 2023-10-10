package com.fastcampus.jblog.biz.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastcampus.jblog.biz.blog.BlogVO;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public void insertCategory(CategoryVO categoryVO) {
		categoryDAO.insertCategory(categoryVO);
	}
	
	@Override
	public void updateCategory(CategoryVO categoryVO) {
		categoryDAO.updateCategory(categoryVO);
	}

	@Override
	public void deleteCategory(CategoryVO categoryVO) {
		categoryDAO.deleteCategory(categoryVO);
	}

	@Override
	public CategoryVO getCategory(CategoryVO categoryVO) {
		return categoryDAO.getCategory(categoryVO);
	}

	@Override
	public List<CategoryVO> getCategoryList(BlogVO blogVO) {
		return categoryDAO.getCategoryList(blogVO);
	}


}
