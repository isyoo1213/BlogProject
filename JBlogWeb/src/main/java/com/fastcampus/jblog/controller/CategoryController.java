package com.fastcampus.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/insertCategoryView.do")
	public String insertCategoryView(CategoryVO categoryVO, HttpSession session, Model model) {
		System.out.println("insertCategoryView() 실행");
		
		BlogVO userBlog = (BlogVO)session.getAttribute("userBlog");
		List<CategoryVO> categoryList =	categoryService.getCategoryList(userBlog);
		model.addAttribute("categoryList", categoryList);
		
		return "blogadmin_category";
		
	}
	
	@RequestMapping("insertCategory.do")
	public String insertCategory(CategoryVO categoryVO, HttpSession session, Model model) {
		System.out.println("insertCategory() 실행");
		
		BlogVO userBlog = (BlogVO)session.getAttribute("userBlog");
		categoryVO.setBlog_id(userBlog.getBlog_id());
		
		categoryService.insertCategory(categoryVO);
		model.addAttribute("category", categoryVO);
		
		List<CategoryVO> categoryList =	categoryService.getCategoryList(userBlog);
		model.addAttribute("categoryList", categoryList);
		
		return "blogadmin_category";
		
	}
	
	@RequestMapping("/updateCategoryView.do")
	public String updateCategoryView(@RequestParam(value="category_id") int category_id, CategoryVO categoryVO, HttpSession session, Model model) {
		System.out.println("updateCategoryView() 실행");
		BlogVO userBlog = (BlogVO) session.getAttribute("userBlog");
		categoryVO.setCategory_id(category_id);

		categoryVO = categoryService.getCategory(categoryVO);
		System.out.println(categoryVO.getCategory_name());
		System.out.println(categoryVO.getCategory_id());
		
		model.addAttribute("category", categoryVO);
		
		
		
		List<CategoryVO> categoryList = categoryService.getCategoryList(userBlog);
		model.addAttribute("categoryList", categoryList);
		
		return "blogadmin_category_set";
	}
	
	@RequestMapping("/updateCategory.do")
	public String updateCategory(CategoryVO categoryVO, HttpSession session, Model model) {
		System.out.println("updateCategory() 실행");
		
		
//		categoryVO =(CategoryVO)model.getAttribute("category");

//		categoryService.updateCategory((CategoryVO)model.getAttribute("category"));
		categoryService.updateCategory(categoryVO);
		model.addAttribute("category", categoryVO);
		
		BlogVO userBlog = (BlogVO) session.getAttribute("userBlog");
		List<CategoryVO> categoryList = categoryService.getCategoryList(userBlog);
		model.addAttribute("categoryList", categoryList);
		
		return "blogadmin_category";
		
	}
	
	@RequestMapping("/deleteCategory.do")
	public String deleteCategory(@RequestParam(value="category_id") int category_id, CategoryVO categoryVO, Model model, HttpSession session) {
		
		categoryVO.setCategory_id(category_id);
		categoryVO = categoryService.getCategory(categoryVO);
		categoryService.deleteCategory(categoryVO);
		
		List<CategoryVO> categoryList = categoryService.getCategoryList((BlogVO)session.getAttribute("userBlog"));
		model.addAttribute("categoryList", categoryList);
		
		return "blogadmin_category";
	}

}
