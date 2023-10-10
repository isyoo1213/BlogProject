package com.fastcampus.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.user.UserService;
import com.fastcampus.jblog.biz.user.UserVO;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private BlogService blogService;
	
	@RequestMapping("/loginView.do")
	public String loginView() {
		System.out.println("loginView() 실행");
		return "bloglogin";
	}
	
	@RequestMapping("/insertUser.do")
	public String insertUser(UserVO vo) {
		userService.insertUser(vo);
		System.out.println("insertUser() 실행");
		return "/";
	}
	
	@RequestMapping("/login.do")
	public String login( UserVO userVO, HttpSession session, Model model) {
		System.out.println("login() 실행");
		UserVO user = userService.getUser(userVO);

		if(user != null && user.getPassword().equals(userVO.getPassword())) {

			session.setAttribute("user", user);
			
			return "forward:/";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
		
	}
	
}
