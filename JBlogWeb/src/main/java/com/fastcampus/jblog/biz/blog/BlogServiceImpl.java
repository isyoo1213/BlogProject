package com.fastcampus.jblog.biz.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fastcampus.jblog.biz.user.UserVO;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogDAO blogDAO;

	@Override
	public void insertBlog(BlogVO vo) {
		blogDAO.insertBlog(vo);
	}
	
	@Override
	public void updateBlog(BlogVO vo) {
		blogDAO.updateBlog(vo);
	}
	
	@Override
	public void deleteBlog(BlogVO vo) {
		blogDAO.deleteBlog(vo);
	}
	
	@Override
	public BlogVO getBlog(BlogVO vo) {
		return blogDAO.getBlog(vo);
	}
	
	@Override
	public BlogVO getUserBlog(UserVO vo) {
		return blogDAO.getUserBlog(vo);
	}

	@Override
	public List<BlogVO> getBlogList() {
		return blogDAO.getBlogList();
	}

	@Override
	public List<BlogVO> searchBlog(SearchVO search, Model model) {
		return blogDAO.searchBlog(search, model);
	}

}
