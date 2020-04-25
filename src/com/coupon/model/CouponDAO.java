package com.coupon.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class CouponDAO implements CouponDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO COUPON (C_NO,C_NAME,C_PICTURE,DISCOUNT,START_DATE,END_DATE) VALUES (SQ_C_N.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT C_NO,C_NAME,C_PICTURE,DISCOUNT,to_char(START_DATE,'yyyy-mm-dd') START_DATE,to_char(END_DATE,'yyyy-mm-dd') END_DATE FROM COUPON order by C_NO";
	private static final String GET_ONE_STMT = 
		"SELECT C_NO,C_NAME,C_PICTURE,DISCOUNT,to_char(START_DATE,'yyyy-mm-dd') START_DATE,to_char(END_DATE,'yyyy-mm-dd') END_DATE FROM COUPON where C_NO = ?";
	private static final String DELETE = 
		"DELETE FROM COUPON where C_NO = ?";
	private static final String UPDATE = 
		"UPDATE COUPON set C_NAME=?, C_PICTURE=?, DISCOUNT=?, START_DATE=?, END_DATE=? where C_NO = ?";

	@Override
	public void insert(CouponVO couponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, couponVO.getC_name());
			pstmt.setBytes(2, couponVO.getC_picture());
			pstmt.setInt(3, couponVO.getDiscount());
			pstmt.setDate(4, couponVO.getStart_date());
			pstmt.setDate(5, couponVO.getEnd_date());
			pstmt.executeUpdate();
			
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
	public void update(CouponVO couponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponVO.getC_name());
			pstmt.setBytes(2, couponVO.getC_picture());
			pstmt.setInt(3, couponVO.getDiscount());
			pstmt.setDate(4, couponVO.getStart_date());
			pstmt.setDate(5, couponVO.getEnd_date());
			pstmt.setString(6, couponVO.getC_no());
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
	public void delete(String c_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, c_no);

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
	public CouponVO findByPrimaryKey(String c_no) {

		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, c_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO 也稱為 Domain objects
				couponVO = new CouponVO();
				couponVO.setC_no(rs.getString("C_NO"));
				couponVO.setC_name(rs.getString("C_NAME"));
				couponVO.setC_picture(rs.getBytes("C_PICTURE"));
				couponVO.setDiscount(rs.getInt("DISCOUNT"));
				couponVO.setStart_date(rs.getDate("START_DATE"));
				couponVO.setEnd_date(rs.getDate("END_DATE"));
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
		return couponVO;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO 也稱為 Domain objects
				couponVO = new CouponVO();
				couponVO.setC_no(rs.getString("C_NO"));
				couponVO.setC_name(rs.getString("C_NAME"));
				couponVO.setC_picture(rs.getBytes("C_PICTURE"));
				couponVO.setDiscount(rs.getInt("DISCOUNT"));
				couponVO.setStart_date(rs.getDate("START_DATE"));
				couponVO.setEnd_date(rs.getDate("END_DATE"));
				list.add(couponVO); 
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