package com.fastcampus.jblog.biz.blog;

import java.util.List;

import org.springframework.ui.Model;

import com.fastcampus.jblog.biz.user.UserVO;

public interface BlogService {
	
	void insertBlog(BlogVO vo);
	
	void updateBlog(BlogVO vo);
	
	void deleteBlog(BlogVO vo);
	
	BlogVO getBlog(BlogVO vo);
	
	BlogVO getUserBlog(UserVO vo);
	
	List<BlogVO> getBlogList();
	
	List<BlogVO> searchBlog(SearchVO search, Model model);

	
	
	

}