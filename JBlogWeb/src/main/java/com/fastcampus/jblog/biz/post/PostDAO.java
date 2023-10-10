package com.fastcampus.jblog.biz.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.biz.common.JDBCUtil;

@Repository
public class PostDAO {
	
	private final String POST_INSERT 	= "insert into post(post_id, category_id, title, content, created_date) values (?, ?, ?, ?, ?)";
	private final String POST_UPDATE 	= "update blog_user set role = ? where id = ?";
	private final String POST_DELETE 	= "delete blog_user where id = ?";
	private final String POST_GET 		= "select * from blog_user where id = ? and password = ?";
	private final String POST_LIST   	= "select * from blog_user order by seq desc";		
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public void insertPost(PostVO vo) {
		System.out.println("insertPost() 실행");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_INSERT);
			stmt.setInt(1, vo.getPost_id());
			stmt.setInt(2, vo.getCategory_id());
			stmt.setString(3, vo.getTitle());
			stmt.setString(4, vo.getContent());
//			stmt.setDate(5, vo.getCreated_date());
			stmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
		
		
	}

}
