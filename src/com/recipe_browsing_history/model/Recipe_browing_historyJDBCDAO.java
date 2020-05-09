package com.recipe_browsing_history.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


import javax.sql.DataSource;

import com.recipe_favorite.model.RecipeFavoriteVO;

public class Recipe_browing_historyJDBCDAO implements Recipe_browing_historyDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";
	private static DataSource ds = null;

	
	private static final String INSERT_STMT =
			"		INSERT INTO RECIPE_BROWSING_HISTORY (member_id,recipe_id) VALUES ( ?, ?)";
		
	private static final String DELETE = 
			"DELETE FROM RECIPE_BROWSING_HISTORY where member_id = ? and recipe_id=? ";
	private static final String DELETEAll = 
			"DELETE FROM RECIPE_BROWSING_HISTORY where member_id = ? ";
	
	private static final String GET_ALL_STMT = "SELECT  MEMBER_ID, recipe_id FROM RECIPE_BROWSING_HISTORY where member_id = ?";

	private static final String GET_ONE_FOLLOWING_STMT = "SELECT * FROM RECIPE_BROWSING_HISTORY WHERE member_id = ?";
	
	
	
	@Override
	public List<Recipe_browing_historyVO> findFollowingByPrimaryKey(String member_id) {

		List<Recipe_browing_historyVO> list = new ArrayList<Recipe_browing_historyVO>();
		Recipe_browing_historyVO recipe_browing_historyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FOLLOWING_STMT);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_favoriteVO 也稱為 Domain objects
				recipe_browing_historyVO = new Recipe_browing_historyVO();
				recipe_browing_historyVO.setMember_id(rs.getString("member_id"));
				recipe_browing_historyVO.setRecipe_id(rs.getString("recipe_id"));
				list.add(recipe_browing_historyVO);
				// Store the row in the list

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void insert(Recipe_browing_historyVO VO) {
		//獲取全部陣列
	 System.out.println("DAO紀錄新增開始");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, VO.getMember_id());	
			pstmt.setString(2, VO.getRecipe_id());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Recipe_browing_historyVO VO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, VO.getMember_id());	
			pstmt.setString(2, VO.getRecipe_id());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("並未刪除 " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Set<Recipe_browing_historyVO> getmemberList(String member_id) {
		Set<Recipe_browing_historyVO> list = new LinkedHashSet<Recipe_browing_historyVO>();
		Recipe_browing_historyVO VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1,member_id);	

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// mycourseVO 也稱為 Domain objects
				VO = new Recipe_browing_historyVO();
				VO.setMember_id(rs.getString("MEMBER_ID"));
				VO.setRecipe_id(rs.getString("recipe_id"));

				list.add(VO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
		
	}

	@Override
	public void deleteAll(String member_id ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(DELETEAll);
			pstmt.setString(1, member_id);	
System.out.println("刪除全部");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("並未刪除 " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
public static void main(String[] args) {
	Recipe_browing_historyJDBCDAO dao=new Recipe_browing_historyJDBCDAO();
Recipe_browing_historyVO VO=new Recipe_browing_historyVO();
Recipe_browing_historyService pvhSvc=new Recipe_browing_historyService();
pvhSvc.insert("810005", "320012");
//VO.setMember_id("810005");
//VO.setRecipe_id("320012");
//	dao.insert(VO);
}
}
