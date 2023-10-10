package com.fastcampus.jblog.biz.blog;

import lombok.Data;

@Data
public class BlogVO {
	
	private int blog_id;
	private String title;
	private String tag;
	private int cnt_display_post;
	private String status;
	private int user_id;
	
	private String User_name;
	
	public BlogVO() {
		
		this.cnt_display_post = 5;
		this.status = "운영";
		
	}
	
}
