package com.fastcampus.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.biz.post.PostService;
import com.fastcampus.jblog.biz.post.PostVO;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("/insertPostView.do")
	public String insertPostView(HttpSession session, Model model) {
		System.out.println("insertPostView() 실행");
		return "blogadmin_post";
	}
	
	@RequestMapping("insertPost.do")
	public String insertPost(PostVO vo) {
		System.out.println("insertPost.do() 실행");
		postService.insertPost(vo);
		return "blogadmin_post";
	}

}
