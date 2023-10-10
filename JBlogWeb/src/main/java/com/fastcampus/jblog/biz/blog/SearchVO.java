package com.fastcampus.jblog.biz.blog;

import lombok.Data;

@Data
public class SearchVO {
	
	private String searchCondition;
	private String searchKeyword;

	public SearchVO() {
		this.searchCondition = "TITLE";
		this.searchKeyword = "";
	}
}
