package com.fastcampus.jblog.biz.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.common.JDBCUtil;

@Repository
public class CategoryDAO {
	
	private final String CATEGORY_INSERT 	= "insert into category(blog_id, category_id, category_name, display_type, cnt_display_post, description) values (?, (select nvl(max(category_id), 0) + 1 from category), ?, ?, ?, ?)";
	private final String CATEGORY_UPDATE 	= "update category set category_name = ?, display_type = ?, cnt_display_post = ?, description = ?, category_id=? where category_id = ?";
	private final String CATEGORY_DELETE 	= "delete category where category_id = ?";
	private final String CATEGORY_GET 		= "select * from category where category_id=?";
	private final String CATEGORY_LIST   	= "select * from category where blog_id=? order by category_id desc";		

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public void insertCategory(CategoryVO categoryVO) {
		System.out.println("insertCategory() 실행");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_INSERT);
			stmt.setInt(1, categoryVO.getBlog_id());
			stmt.setString(2, categoryVO.getCategory_name());
			stmt.setString(3, categoryVO.getDisplay_type());
			stmt.setInt(4, categoryVO.getCnt_display_post());
			stmt.setString(5, categoryVO.getDescription());
//			stmt.setString(7, vo.getDisplay_type());
//			stmt.setString(8, vo.getDisplay_type());
			stmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}
	
	public void updateCategory(CategoryVO categoryVO) {
		System.out.println("updateCategory() 실행");
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_UPDATE);
			stmt.setString(1, categoryVO.getCategory_name());
			stmt.setString(2, categoryVO.getDisplay_type());
			stmt.setInt(3, categoryVO.getCnt_display_post());
			stmt.setString(4, categoryVO.getDescription());
			stmt.setInt(5, categoryVO.getCategory_id());
			stmt.setInt(6, categoryVO.getCategory_id());
			stmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}
	
	public void deleteCategory(CategoryVO categoryVO) {
		System.out.println("deleteCategory() 실행");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_DELETE);
			stmt.setInt(1, categoryVO.getCategory_id());
			stmt.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public CategoryVO getCategory( CategoryVO categoryVO ) {
		System.out.println("getCategory() 실행");
		
		CategoryVO category = null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_GET);
			stmt.setInt(1, categoryVO.getCategory_id());
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				category = new CategoryVO();
				category.setBlog_id(rs.getInt("BLOG_ID"));
				category.setCategory_id(rs.getInt("CATEGORY_ID"));
				category.setCategory_name(rs.getString("CATEGORY_NAME"));
				category.setDisplay_type(rs.getString("DISPLAY_TYPE"));
				category.setCnt_display_post(rs.getInt("CNT_DISPLAY_POST"));
				category.setDescription(rs.getString("DESCRIPTION"));
				category.setCreated_date(rs.getDate("CREATED_DATE"));
				category.setModified_date(rs.getDate("MODIFIED_DATE"));
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return category;
	}
	
	public List<CategoryVO> getCategoryList(BlogVO blogVO){
		System.out.println("getCategoryList() 실행");
		List<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_LIST);
			stmt.setInt(1, blogVO.getBlog_id());
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setBlog_id(rs.getInt("BLOG_ID"));
				category.setCategory_id(rs.getInt("CATEGORY_ID"));
				category.setCategory_name(rs.getString("CATEGORY_NAME"));
				category.setDisplay_type(rs.getString("DISPLAY_TYPE"));
				category.setCnt_display_post(rs.getInt("CNT_DISPLAY_POST"));
				category.setDescription(rs.getString("DESCRIPTION"));
				category.setCreated_date(rs.getDate("CREATED_DATE"));
				category.setModified_date(rs.getDate("MODIFIED_DATE"));
				categoryList.add(category);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return categoryList;
	}



}
