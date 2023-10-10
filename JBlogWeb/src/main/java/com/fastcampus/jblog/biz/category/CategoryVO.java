package com.fastcampus.jblog.biz.category;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryVO {

	private int blog_id; 
	private int category_id;
	private String category_name;// NOT NULL,
	private String display_type;// 	NOT NULL,
	private int cnt_display_post;// 	NOT NULL,
	private String description;
	private Date created_date = new Date();
	private Date modified_date = new Date();
	
	public CategoryVO() {
		this.category_name = "";
		this.display_type="TITLE";
		this.cnt_display_post=5;
	}
	
}
