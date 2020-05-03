package com.coupon.model;

import java.util.*;
import java.sql.*;

public class CouponJDBCDAO implements CouponDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = "INSERT INTO COUPON (C_NO, C_NAME, C_PICTURE, DISCOUNT, START_DATE, END_DATE, COUPON_CODE) VALUES (SQ_C_NO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT C_NO,C_NAME,C_PICTURE,DISCOUNT,to_char(START_DATE,'yyyy-mm-dd') START_DATE,to_char(END_DATE,'yyyy-mm-dd') END_DATE, COUPON_CODE FROM COUPON order by C_NO";
	private static final String GET_ONE_STMT = "SELECT C_NO,C_NAME,C_PICTURE,DISCOUNT,to_char(START_DATE,'yyyy-mm-dd') START_DATE,to_char(END_DATE,'yyyy-mm-dd') END_DATE, COUPON_CODE FROM COUPON where C_NO = ?";
	private static final String DELETE = "DELETE FROM COUPON where C_NO = ?";
	private static final String UPDATE = "UPDATE COUPON set C_NAME=?, C_PICTURE=?, DISCOUNT=?, START_DATE=?, END_DATE=?, COUPON_CODE=? where C_NO = ?";

	@Override
	public void insert(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, couponVO.getC_name());
			pstmt.setBytes(2, couponVO.getC_picture());
			pstmt.setInt(3, couponVO.getDiscount());
			pstmt.setDate(4, couponVO.getStart_date());
			pstmt.setDate(5, couponVO.getEnd_date());
			pstmt.setString(6, couponVO.getCoupon_code());
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
	public void update(CouponVO couponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponVO.getC_name());
			pstmt.setBytes(2, couponVO.getC_picture());
			pstmt.setInt(3, couponVO.getDiscount());
			pstmt.setDate(4, couponVO.getStart_date());
			pstmt.setDate(5, couponVO.getEnd_date());
			pstmt.setString(6, couponVO.getCoupon_code());
			pstmt.setString(7, couponVO.getC_no());
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
	public void delete(String c_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, c_no);

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
	public CouponVO findByPrimaryKey(String c_no) {

		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				couponVO.setCoupon_code(rs.getString("COUPON_CODE"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				couponVO.setCoupon_code(rs.getString("COUPON_CODE"));
				list.add(couponVO);
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

		CouponJDBCDAO boj = new CouponJDBCDAO();

		// 新增
//		CouponVO coupon01 = new CouponVO();
//		coupon01.setC_name("指定料理包享95折優惠");
//		coupon01.setC_picture(new byte[3]);
//		coupon01.setDiscount(95);
//		coupon01.setStart_date(java.sql.Date.valueOf("2020-06-01"));
//		coupon01.setEnd_date(java.sql.Date.valueOf("2020-07-01"));
//		coupon01.setCoupon_code(getAuthCode());
//		boj.insert(coupon01);
//		System.out.println("新增成功");
  
//		// 修改
//		CouponVO coupon02 = new CouponVO();
//		coupon02.setC_no("730005");
//		coupon02.setC_name("UpVer2");
//		coupon02.setC_picture(new byte[3]);
//		coupon02.setDiscount(95);
//		coupon02.setStart_date(java.sql.Date.valueOf("2020-03-22"));
//		coupon02.setEnd_date(java.sql.Date.valueOf("2020-03-23"));
//		coupon02.setCoupon_code(getAuthCode());
//		boj.update(coupon02);
//		System.out.println("修改成功");	
//
//		// 刪除
//		boj.delete("730005");
//		System.out.println("刪除成功");
//
		// 查詢
		CouponVO coupon03 = boj.findByPrimaryKey("730006");
		System.out.print(coupon03.getC_no() + ",");
		System.out.print(coupon03.getC_name() + ",");
		System.out.print(coupon03.getC_picture() + ",");
		System.out.print(coupon03.getDiscount() + ",");
		System.out.print(coupon03.getStart_date() + ",");
		System.out.print(coupon03.getEnd_date() + ",");
		System.out.println(coupon03.getCoupon_code() + ",");
		System.out.println("---------------------");
//
//		// 查詢所有
//		List<CouponVO> list = boj.getAll();
//		for (CouponVO aCoupon : list) {
//			System.out.print(aCoupon.getC_no() + ",");
//			System.out.print(aCoupon.getC_name() + ",");
//			System.out.print(aCoupon.getC_picture() + ",");
//			System.out.print(aCoupon.getDiscount() + ",");
//			System.out.print(aCoupon.getStart_date() + ",");
//			System.out.print(aCoupon.getEnd_date() + ",");
//			System.out.println();
//		}
	}

//	private static String getAuthCode() {
//		String AuthCode = "";
//		char c[] = new char[62];
//		char s = 48;
//		for (int i_01 = 0; i_01 < 62; i_01++) {
//			c[i_01] = s;
//			if (s == 57)
//				s = 64;
//			else if (s == 90)
//				s = 96;
//			s++;
//		}
//		for (int i_02 = 0; i_02 < 6; i_02++) {
//			int k = (int) (Math.random() * 62);
//			AuthCode += String.valueOf(c[k]);
//		}
//		return AuthCode;
//	}
}