package com.coupon_details.model;

import java.util.*;
import java.sql.*;


public class CouponDetailsJDBCDAO implements CouponDetailsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = 
			"INSERT INTO COUPON_DETAILS (C_NO,RECIPE_ID) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM COUPON_DETAILS order by C_NO";
		private static final String GET_ONE_STMT_C_NO = 
			"SELECT * FROM COUPON_DETAILS  where C_NO = ?";
		private static final String GET_ONE_STMT_RECIPE_ID = 
			"SELECT * FROM COUPON_DETAILS  where RECIPE_ID = ?";
		private static final String DELETE = 
			"DELETE FROM COUPON_DETAILS where C_NO = ? and RECIPE_ID = ?";
		private static final String UPDATE = 
			"UPDATE COUPON_DETAILS set C_NO=?, RECIPE_ID=? where C_NO =? and RECIPE_ID = ?";


	@Override
	public void insert(CouponDetailsVO couponDetailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, couponDetailsVO.getC_no());
			pstmt.setString(2, couponDetailsVO.getRecipe_id());
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
	public void update(CouponDetailsVO couponDetailsVO, CouponDetailsVO couponDetailsVO_1) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponDetailsVO.getC_no());
			pstmt.setString(2, couponDetailsVO.getRecipe_id());
			pstmt.setString(3, couponDetailsVO_1.getC_no());
			pstmt.setString(4, couponDetailsVO_1.getRecipe_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String c_no, String recipe_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, c_no);
			pstmt.setString(2, recipe_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<CouponDetailsVO> findByPrimaryKey_C_no(String c_no) {
		List<CouponDetailsVO> list = new ArrayList<CouponDetailsVO>();
		CouponDetailsVO couponDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_C_NO);

			pstmt.setString(1, c_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponDetailsVO 也稱為 Domain objects
				couponDetailsVO = new CouponDetailsVO();
				couponDetailsVO.setC_no(rs.getString("C_NO"));
				couponDetailsVO.setRecipe_id(rs.getString("RECIPE_ID"));
				list.add(couponDetailsVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<CouponDetailsVO> findByPrimaryKey_Recipe_id(String recipe_id) {
		List<CouponDetailsVO> list = new ArrayList<CouponDetailsVO>();
		CouponDetailsVO couponDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_RECIPE_ID);

			pstmt.setString(1, recipe_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponDetailsVO 也稱為 Domain objects
				couponDetailsVO = new CouponDetailsVO();
				couponDetailsVO.setC_no(rs.getString("C_NO"));
				couponDetailsVO.setRecipe_id(rs.getString("RECIPE_ID"));
				list.add(couponDetailsVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<CouponDetailsVO> getAll() {
		List<CouponDetailsVO> list = new ArrayList<CouponDetailsVO>();
		CouponDetailsVO couponDetailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponDetailsVO 也稱為 Domain objects
				couponDetailsVO = new CouponDetailsVO();
				couponDetailsVO.setC_no(rs.getString("C_NO"));
				couponDetailsVO.setRecipe_id(rs.getString("RECIPE_ID"));
				list.add(couponDetailsVO);
				// Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

		CouponDetailsJDBCDAO boj = new CouponDetailsJDBCDAO();

		// 新增
//		CouponDetailsVO couponDetails01 = new CouponDetailsVO();
//		couponDetails01.setC_no("740008");
//		couponDetails01.setRecipe_id("510004");
//		boj.insert(couponDetails01);
//		System.out.println("新增成功");
		
		// 修改
//		CouponDetailsVO couponDetails02 = new CouponDetailsVO();
//		CouponDetailsVO couponDetails03 = new CouponDetailsVO();
//		couponDetails02.setC_no("740008");
//		couponDetails02.setRecipe_id("510002");
//		couponDetails03.setC_no("740009");
//		couponDetails03.setRecipe_id("510001");
//		boj.update(couponDetails02,couponDetails03);
//		System.out.println("修改成功");

		// 刪除
//		boj.delete("740009","510005");
//		System.out.println("刪除成功");

		// 查詢
		List<CouponDetailsVO> list = boj.findByPrimaryKey_C_no("740007");
		for(CouponDetailsVO cd : list) {
			System.out.print(cd.getC_no() + ",");
			System.out.print(cd.getRecipe_id() + ",");
			System.out.println();
		}
			System.out.println("---------------------");
				
		List<CouponDetailsVO> list2 = boj.findByPrimaryKey_Recipe_id("510003");
		for(CouponDetailsVO cd2 : list2) {
			System.out.print(cd2.getC_no() + ",");
			System.out.print(cd2.getRecipe_id() + ",");
			System.out.println();
		}
			System.out.println("---------------------");
		// 查詢所有
		List<CouponDetailsVO> list3 = boj.getAll();
		for (CouponDetailsVO cd3 : list3) {
			System.out.print(cd3.getC_no() + ",");
			System.out.print(cd3.getRecipe_id() + ",");
			System.out.println();
		}
	}
}

