package com.fastcampus.jblog.biz.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.fastcampus.jblog.biz.common.JDBCUtil;
import com.fastcampus.jblog.biz.user.UserVO;

@Repository
public class BlogDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	
	
	private final String BLOG_INSERT 	    = "insert into blog(blog_id, title, tag, cnt_display_post, status, user_id) " + 
            								  "values (?, ?, ?, ?, ?, ?)";
	private final String BLOG_UPDATE	    = "update blog set title = ?, tag = ?, cnt_display_post = ? where blog_id = ?";
	private final String BLOG_DELETE        = "delete blog where blog_id = ?";
	private final String BLOG_GET 		    = "select * from blog where user_id = ?";
	private final String BLOG_LIST	        = "select blog.blog_id, blog.title, blog.tag, blog.cnt_display_post, blog.status, blog_user.user_name, blog.status from blog, blog_user where blog.user_id=blog_user.user_id order by blog.title";
	private final String BLOG_LIST_TITLE   	= "select b.blog_id, b.cnt_display_post, b.status, b.title, b.tag, u.user_name from blog b, blog_user u where b.user_id=u.user_id and b.title like '%'||?||'%' order by b.title";
	private final String BLOG_LIST_TAG     	= "select b.blog_id, b.cnt_display_post, b.status, b.title, b.tag, u.user_name from blog b, blog_user u where b.user_id=u.user_id and b.tag like '%'||?||'%' order by b.title";
	private final String BLOG_LIST_BLOGGER 	= "select b.blog_id, b.cnt_display_post, b.status, b.title, b.tag, u.user_name from blog b, blog_user u where b.user_id=u.user_id and u.user_name like '%'||?||'%' order by b.title";
	
	public void insertBlog(BlogVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_INSERT);
			stmt.setInt		(1, vo.getBlog_id());
			stmt.setString	(2, vo.getTitle());
			stmt.setString	(3, vo.getTag());
			stmt.setInt		(4, vo.getCnt_display_post());
			stmt.setString	(5, vo.getStatus());
			stmt.setInt		(6, vo.getUser_id());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public void updateBlog(BlogVO vo) {
		try {
			System.out.println(vo.getTitle());
			System.out.println(vo.getTag());
			System.out.println(vo.getBlog_id());
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_UPDATE);
			stmt.setString	(1, vo.getTitle());
			stmt.setString	(2, vo.getTag());
			stmt.setInt		(3, vo.getCnt_display_post());
			stmt.setInt		(4, vo.getBlog_id());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}	
	}
	
	public void deleteBlog(BlogVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_DELETE);
			stmt.setInt		(1, vo.getBlog_id());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public BlogVO getBlog(BlogVO blogVO) {
		BlogVO blog = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_GET);
			stmt.setInt(1, blogVO.getBlog_id());
			rs = stmt.executeQuery();
			if(rs.next()) {
				blog = new BlogVO();
				blog.setBlog_id(rs.getInt("blog_id"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setTag(rs.getString("TAG"));
				blog.setCnt_display_post(rs.getInt("CNT_DISPLAY_POST"));
				blog.setStatus(rs.getString("STATUS"));
				blog.setUser_id(rs.getInt("USER_ID"));
				System.out.println(blog.getBlog_id());
				System.out.println(blog.getTitle());
				System.out.println(blog.getUser_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return blog;
	}
	
	public List<BlogVO> getBlogList(){
		System.out.println("getBlogList() 실행");
		List<BlogVO> blogList = new ArrayList<BlogVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_LIST);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				BlogVO blog = new BlogVO();
				blog.setBlog_id(rs.getInt("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setTag(rs.getString("TAG"));
				blog.setCnt_display_post(rs.getInt("CNT_DISPLAY_POST"));
				blog.setStatus(rs.getString("STATUS"));
				blog.setUser_name(rs.getString("USER_NAME"));
				System.out.println(blog.getTitle() + "의 블로그가 bloglist에 등록됨");
				blogList.add(blog);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return blogList;
	}
	
	public List<BlogVO> searchBlog(SearchVO search, Model model){
		System.out.println("SearchBlog() 실행");
		List<BlogVO> searchedBlog = new ArrayList<BlogVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			if(search.getSearchCondition().equals("TITLE")) {
				stmt = conn.prepareStatement(BLOG_LIST_TITLE);
			} else if(search.getSearchCondition().equals("TAG")) {
				stmt = conn.prepareStatement(BLOG_LIST_TAG);
			} else if(search.getSearchCondition().equals("BLOGGER")) {
				stmt = conn.prepareStatement(BLOG_LIST_BLOGGER);
			} 
	
			stmt.setString(1, "%" + search.getSearchKeyword() + "%");
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				BlogVO blog = new BlogVO();
				blog.setTitle(rs.getString("TITLE"));
				blog.setUser_name(rs.getString("USER_NAME"));
				blog.setStatus(rs.getString("STATUS"));
				searchedBlog.add(blog);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return searchedBlog;
	}

	public BlogVO getUserBlog(UserVO userVO) {
		BlogVO blog = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_GET);
			stmt.setInt(1, userVO.getUserId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				blog = new BlogVO();
				blog.setBlog_id(rs.getInt("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setTag(rs.getString("TAG"));
				blog.setCnt_display_post(rs.getInt("CNT_DISPLAY_POST"));
				blog.setStatus(rs.getString("STATUS"));
				blog.setUser_id(rs.getInt("USER_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return blog;
	}
	
	
}
