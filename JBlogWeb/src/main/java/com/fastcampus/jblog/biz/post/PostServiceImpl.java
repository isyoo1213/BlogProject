package com.fastcampus.jblog.biz.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO postDAO;

	@Override
	public void insertPost(PostVO vo) {
		postDAO.insertPost(vo);

	}

}
