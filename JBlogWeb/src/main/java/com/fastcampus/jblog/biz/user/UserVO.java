package com.fastcampus.jblog.biz.user;

import lombok.Data;

//BLOG_USER와 매핑되는 클래스

@Data
public class UserVO {
	
	private int userId;
	private String id;
	private String password;
	private String userName;
	private String role;

}
