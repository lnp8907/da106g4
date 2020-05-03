package com.coupon_details.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CouponDetailsDAO implements CouponDetailsDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO COUPON_DETAILS (C_NO,PRODUCT_ID) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM COUPON_DETAILS order by C_NO";
	private static final String GET_ONE_STMT_C_NO = 
		"SELECT * FROM COUPON_DETAILS  where C_NO = ?";
	private static final String GET_ONE_STMT_RECIPE_ID = 
		"SELECT * FROM COUPON_DETAILS  where PRODUCT_ID = ?";
	private static final String DELETE = 
		"DELETE FROM COUPON_DETAILS where C_NO = ? and PRODUCT_ID = ?";
	private static final String UPDATE = 
		"UPDATE COUPON_DETAILS set C_NO=?, PRODUCT_ID=? where C_NO = ? and PRODUCT_ID = ?";

	@Override
	public void insert(String c_no, String[] product_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			for (int j = 0; j < product_id.length; j++) {
				pstmt.setString(1, c_no);
				pstmt.setString(2, product_id[j]);
				pstmt.executeUpdate();	
			}

			
			// Handle any driver errors
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
	public void update(CouponDetailsVO couponDetailsVO, CouponDetailsVO couponDetailsVO_1) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponDetailsVO.getC_no());
			pstmt.setString(2, couponDetailsVO.getProduct_id());
			pstmt.setString(3, couponDetailsVO_1.getC_no());
			pstmt.setString(4, couponDetailsVO_1.getProduct_id());
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, c_no);
			pstmt.setString(2, recipe_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_C_NO);
			
			pstmt.setString(1, c_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponDetailsVO 也稱為 Domain objects
				couponDetailsVO = new CouponDetailsVO();
				couponDetailsVO.setC_no(rs.getString("C_NO"));
				couponDetailsVO.setProduct_id(rs.getString("PRODUCT_ID"));
				list.add(couponDetailsVO);
			}

			// Handle any driver errors
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
	public List<CouponDetailsVO> findByPrimaryKey_Product_id(String recipe_id) {
		List<CouponDetailsVO> list = new ArrayList<CouponDetailsVO>();
		CouponDetailsVO couponDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_RECIPE_ID);
			
			pstmt.setString(1, recipe_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponDetailsVO 也稱為 Domain objects
				couponDetailsVO = new CouponDetailsVO();
				couponDetailsVO.setC_no(rs.getString("C_NO"));
				couponDetailsVO.setProduct_id(rs.getString("RECIPE_ID"));
				list.add(couponDetailsVO);
			}

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponDetailsVO 也稱為 Domain objects
				couponDetailsVO = new CouponDetailsVO();
				couponDetailsVO.setC_no(rs.getString("C_NO"));
				couponDetailsVO.setProduct_id(rs.getString("RECIPE_ID"));
				list.add(couponDetailsVO); 
				// Store the row in the list
			}

			// Handle any driver errors
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
}
