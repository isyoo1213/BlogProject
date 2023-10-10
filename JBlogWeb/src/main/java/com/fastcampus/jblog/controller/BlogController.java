package com.fastcampus.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.blog.SearchVO;
import com.fastcampus.jblog.biz.user.UserVO;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//인덱스 페이지에 대한 요청 처리
	@RequestMapping("/")
	public String loginIndex( HttpSession session, Model model) {
	
		if(session.getAttribute("user") != null) {
			BlogVO userBlog = blogService.getUserBlog((UserVO)session.getAttribute("user"));
			
			session.setAttribute("userBlog", userBlog);
		}

		List<BlogVO> blogList = blogService.getBlogList();
		model.addAttribute("blogList", blogList);

		return "forward:/index.jsp";

	}
	
	//블로그 관리 메인 화면에 대한 요청 처리
	@RequestMapping("/adminBasicView.do")
	public String adminBasicView(@RequestParam(value="userId") int userId, HttpSession session, Model model) {
		System.out.println("adminBasicView() 실행");
		return "blogadmin_basic";
	}
	
	@RequestMapping("/updateBasic.do")
	public String updateBasic(BlogVO blogVO, HttpSession session, Model model) {
		System.out.println("updateBasic() 실행");
		
		blogService.updateBlog(blogVO);
		model.addAttribute("blogVO", blogVO);
		
		return "blogadmin_basic";
	}
	
	//블로그 메인 화면에 대한 요청 처리
	@RequestMapping("/blogMain.do")
	public String blogMain(@RequestParam(value="blog_id") int blog_id, BlogVO blogVO, HttpSession session, Model model) {
		System.out.println("blogMain() 실행");

		blogVO.setBlog_id(blog_id);
		blogVO = blogService.getBlog(blogVO);
		
		model.addAttribute("selectedBlog", blogVO);
		
		return "blogmain";
	}
	
	//블로그 검색에 대한 요청 처리
	@RequestMapping("/searchBlog.do")
	public String searchBlog(SearchVO search, Model model) {
		System.out.println("searchBlog() 수행");
		
		List<BlogVO> searchedBlog = blogService.searchBlog(search, model);
		model.addAttribute("blogList", searchedBlog);
		
		return "forward:/index.jsp";
	}
	
	//블로그 생성 화면에 대한 요청 처리
	@RequestMapping("/insertBlogView.do")
	public String createBoard(BlogVO vo, HttpSession session) {
		return "blogcreate";
	}
	
	//블로그 생성에 대한 요청 처리
	@RequestMapping("/insertBlog.do")
	public String insertBlog(BlogVO vo, HttpSession session) {
		
		System.out.println("insertBoard() 실행");
		
		int userId = ((UserVO)session.getAttribute("user")).getUserId();
		System.out.println("세션에서 가져온 userId : " + userId);
		
		String userName = ((UserVO)session.getAttribute("user")).getUserName();
		System.out.println("세션에서 가져온 userName : " + userName);
		
		vo.setUser_id(userId);
		System.out.println("BlogVO 객체에 userId 세팅");
		
		vo.setBlog_id(userId);
		System.out.println("BlogVO 객체에 BlogId세팅");
		
		blogService.insertBlog(vo);
		
		System.out.println("insertBlog() 실행");
		session.setAttribute("blog", vo);
	
		return "forward:/";
	}
	
	@RequestMapping("/deleteBlog.do")
	public String deleteBlog(BlogVO vo, HttpSession session, Model model) {
		blogService.deleteBlog(vo);
		
		return "forward:/";
	}
	
}