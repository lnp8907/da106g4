package com.recipe_style.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeStyleJDBCDAO implements RecipeStyleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = "INSERT INTO RECIPE_STYLE (RCSTYLE_NO, RCSTYLE) VALUES (SQ_RCSTYLE_NO.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = "SELECT RCSTYLE_NO,RCSTYLE FROM RECIPE_STYLE order by RCSTYLE_NO";
	private static final String GET_ONE_STMT = "SELECT RCSTYLE_NO,RCSTYLE FROM RECIPE_STYLE WHERE RCSTYLE_NO = ?";
	private static final String DELETE = "DELETE FROM RECIPE_STYLE WHERE RCSTYLE_NO = ?";
	private static final String UPDATE = "UPDATE RECIPE_STYLE SET RCSTYLE = ? WHERE RCSTYLE_NO = ?";

	@Override
	public void insert(RecipeStyleVO recipeStyleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recipeStyleVO.getRcstyle());
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
	public void update(RecipeStyleVO recipeStyleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipeStyleVO.getRcstyle());
			pstmt.setString(2, recipeStyleVO.getRcstyle_no());
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
	public void delete(String rcstyle_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rcstyle_no);
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
	public RecipeStyleVO findByPrimaryKey(String rcstyle_no) {

		RecipeStyleVO recipeStyleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rcstyle_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO 也稱為 Domain objects
				recipeStyleVO = new RecipeStyleVO();
				recipeStyleVO.setRcstyle_no(rs.getString("RCSTYLE_NO"));
				recipeStyleVO.setRcstyle(rs.getString("RCSTYLE"));
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
		return recipeStyleVO;
	}

	@Override
	public List<RecipeStyleVO> getAll() {
		List<RecipeStyleVO> list = new ArrayList<RecipeStyleVO>();
		RecipeStyleVO recipeStyleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO 也稱為 Domain objects
				recipeStyleVO = new RecipeStyleVO();
				recipeStyleVO.setRcstyle_no(rs.getString("RCSTYLE_NO"));
				recipeStyleVO.setRcstyle(rs.getString("RCSTYLE"));
				list.add(recipeStyleVO);
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

		RecipeStyleJDBCDAO jdbc = new RecipeStyleJDBCDAO();

		// 新增
		RecipeStyleVO rc1 = new RecipeStyleVO();
		rc1.setRcstyle("美式");

		jdbc.insert(rc1);

		// 修改
//		RecipeStyleVO rc2 = new RecipeStyleVO();
//		rc2.setRcstyle_no("520002");
//		rc2.setRcstyle("World");
//
//		jdbc.update(rc2);

		// 刪除
//		jdbc.delete("520000");

		// 查詢
//		RecipeStyleVO rc3 = jdbc.findByPrimaryKey("520001");
//		System.out.print(rc3.getRcstyle_no() + ",");
//		System.out.print(rc3.getRcstyle() + ",");
//
//		System.out.println();
//		System.out.println("---------------------");

		// 查詢所有
		List<RecipeStyleVO> list = jdbc.getAll();
		for (RecipeStyleVO rc4 : list) {
			System.out.print(rc4.getRcstyle_no() + ",");
			System.out.print(rc4.getRcstyle());
			System.out.println();
		}
	}
}
