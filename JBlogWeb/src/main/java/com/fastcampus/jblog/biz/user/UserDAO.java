package com.fastcampus.jblog.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.biz.common.JDBCUtil;

@Repository
public class UserDAO  {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private final String USER_INSERT 	= "insert into blog_user values (?, ?, ?, ?, ?)";
	private final String USER_UPDATE 	= "update blog_user set role = ? where id = ?";
	private final String USER_DELETE 	= "delete blog_user where id = ?";
	private final String USER_GET 		= "select * from blog_user where id = ? and password = ?";
	private final String USER_LIST   	= "select * from blog_user order by seq desc";		
	
	public void insertUser(UserVO vo) {
		System.out.println("===> JDBC 기반으로 insertUser() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_INSERT);
			stmt.setInt(1, vo.getUserId());
			stmt.setString(2, vo.getId());
			stmt.setString(3, vo.getUserName());
			stmt.setString(4, vo.getRole());
			stmt.setString(5, vo.getPassword());
			stmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public void updateUser(UserVO vo) {
		
		System.out.println("===> JDBC 기반으로 updateUser() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_UPDATE);
			stmt.setString(1, vo.getRole());
			stmt.setString(2, vo.getId());
			stmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public void deleteUser(UserVO vo) {
		
		System.out.println("===> JDBC 기반으로 deleteUser() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_DELETE);
			stmt.setString(1, vo.getId());
			stmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserVO();
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setRole(rs.getString("ROLE"));
				user.setPassword(rs.getString("PASSWORD"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
	
public List<UserVO> getUserList(UserVO vo) {
		
		System.out.println("===> JDBC 기반으로 getUserList() 기능 처리");
		
		List<UserVO> userList = new ArrayList<UserVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				UserVO user = new UserVO();
				user.setUserId(rs.getInt("USER_ID"));
				user.setId(rs.getString("ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setRole(rs.getString("ROLE"));
				user.setPassword(rs.getString("PASSWORD"));
				userList.add(user);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		} 
		return userList;
	}


	
}
