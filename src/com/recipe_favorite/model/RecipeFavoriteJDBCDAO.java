package com.recipe_favorite.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coupon.model.CouponVO;

public class RecipeFavoriteJDBCDAO implements RecipeFavoriteDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = "INSERT INTO RECIPE_FAVORITE (recipe_id, member_id) VALUES ( ?, ?) ";
	private static final String GET_ALL_STMT = "SELECT * FROM RECIPE_FAVORITE order by recipe_id";
	private static final String GET_ONE_FOLLOWED_STMT = "SELECT * FROM RECIPE_FAVORITE WHERE recipe_id = ?";
	private static final String GET_ONE_FOLLOWING_STMT = "SELECT * FROM RECIPE_FAVORITE WHERE member_id = ?";
	private static final String DELETE = "DELETE FROM RECIPE_FAVORITE WHERE recipe_id = ? AND member_id = ?";
	private static final String UPDATE = "UPDATE RECIPE_FAVORITE SET recipe_id=?, member_id=? WHERE recipe_id = ? AND member_id= ? ";

	@Override
	public void insert(RecipeFavoriteVO recipe_favoriteVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recipe_favoriteVO.getRecipe_id());
			pstmt.setString(2, recipe_favoriteVO.getMember_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(RecipeFavoriteVO recipe_favoriteVO,RecipeFavoriteVO recipe_favoriteVO_1) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipe_favoriteVO.getRecipe_id());
			pstmt.setString(2, recipe_favoriteVO.getMember_id());
			pstmt.setString(3, recipe_favoriteVO_1.getRecipe_id());
			pstmt.setString(4, recipe_favoriteVO_1.getMember_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String recipe_id, String member_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, recipe_id);
			pstmt.setString(2, member_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public List<RecipeFavoriteVO> findFollowedByPrimaryKey(String recipe_id) {

		List<RecipeFavoriteVO> list = new ArrayList<RecipeFavoriteVO>();
		RecipeFavoriteVO recipe_favoriteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FOLLOWED_STMT);

			pstmt.setString(1, recipe_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_favoriteVO 也稱為 Domain objects
				recipe_favoriteVO = new RecipeFavoriteVO();
				recipe_favoriteVO.setRecipe_id(rs.getString("recipe_id"));
				recipe_favoriteVO.setMember_id(rs.getString("member_id"));
				list.add(recipe_favoriteVO);
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
	public List<RecipeFavoriteVO> findFollowingByPrimaryKey(String member_id) {

		List<RecipeFavoriteVO> list = new ArrayList<RecipeFavoriteVO>();
		RecipeFavoriteVO recipe_favoriteVO = null;
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
				recipe_favoriteVO = new RecipeFavoriteVO();
				recipe_favoriteVO.setMember_id(rs.getString("member_id"));
				recipe_favoriteVO.setRecipe_id(rs.getString("recipe_id"));
				list.add(recipe_favoriteVO);
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
	public List<RecipeFavoriteVO> getAll() {
		List<RecipeFavoriteVO> list = new ArrayList<RecipeFavoriteVO>();
		RecipeFavoriteVO recipe_favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_favoriteVO 也稱為 Domain objects
				recipe_favoriteVO = new RecipeFavoriteVO();
				recipe_favoriteVO.setRecipe_id(rs.getString("recipe_id"));
				recipe_favoriteVO.setMember_id(rs.getString("member_id"));
				list.add(recipe_favoriteVO);
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

	public static void main(String[] args) {
		RecipeFavoriteJDBCDAO jdbc = new RecipeFavoriteJDBCDAO();

		// 新增
//		RecipeFavoriteVO obj1 = new RecipeFavoriteVO();
//		obj1.setRecipe_id("510014");
//		obj1.setMember_id("C0003");
//		jdbc.insert(obj1);

		// 修改
		RecipeFavoriteVO obj2 = new RecipeFavoriteVO();
		RecipeFavoriteVO obj4 = new RecipeFavoriteVO();
		obj2.setRecipe_id("510030");
		obj2.setMember_id("C0001");
		obj4.setRecipe_id("510031");
		obj4.setMember_id("C0002");
		jdbc.update(obj2, obj4);

		// 刪除
//		jdbc.delete("510014", "C0003");

		// 查詢追蹤該食譜的會員
		List<RecipeFavoriteVO> list1 = jdbc.findFollowedByPrimaryKey("510030");
		for (RecipeFavoriteVO obj3 : list1) {
		System.out.print(obj3.getRecipe_id() + ",");
		System.out.println(obj3.getMember_id() + ",");
		}
		
		System.out.println("=======================");
		// 查詢會員追蹤的食譜
		List<RecipeFavoriteVO> list2 = jdbc.findFollowingByPrimaryKey("C0001");
		for (RecipeFavoriteVO obj5 : list2) {
		System.out.print(obj5.getMember_id() + ",");
		System.out.println(obj5.getRecipe_id() + ",");
		}
		
		System.out.println("=======================");
		// 查詢所有
		List<RecipeFavoriteVO> list3 = jdbc.getAll();
		for (RecipeFavoriteVO obj6 : list3) {
			System.out.print(obj6.getRecipe_id() + ",");
			System.out.print(obj6.getMember_id() + ",");
			System.out.println();
		}

	}

}
