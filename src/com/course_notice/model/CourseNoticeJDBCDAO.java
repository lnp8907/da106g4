package com.course_notice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseNoticeJDBCDAO implements CourseNoticeDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = 
			"INSERT INTO COURSE_NOTICE (CSNOTICE_NO, MEMBER_ID,	COURSE_ID, NOTICE, NOTICE_STATUS) VALUES (SQ_CSNOTICE_NO.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT CSNOTICE_NO ,COURSE_ID,	MEMBER_ID, NOTICE, NOTICE_STATUS,to_char(NOTICE_TIME,'yyyy-mm-dd hh:mm:ss') NOTICE_TIME FROM COURSE_NOTICE order by CSNOTICE_NO";
	private static final String GET_ONE_STMT = 
			"SELECT CSNOTICE_NO ,COURSE_ID,	MEMBER_ID, NOTICE, NOTICE_STATUS,to_char(NOTICE_TIME,'yyyy-mm-dd hh:mm:ss') NOTICE_TIME FROM COURSE_NOTICE where CSNOTICE_NO = ?";
	private static final String DELETE = 
			"DELETE FROM COURSE_NOTICE where CSNOTICE_NO = ?";
	private static final String UPDATE = 
			"UPDATE COURSE_NOTICE set MEMBER_ID=?,COURSE_ID=?,NOTICE=?,NOTICE_TIME=?,NOTICE_STATUS=? where CSNOTICE_NO = ?";

	@Override
	public void insert(CourseNoticeVO courseNoticeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, courseNoticeVO.getMember_id());
			pstmt.setString(2, courseNoticeVO.getCourse_id());
			pstmt.setString(3, courseNoticeVO.getNotice());
			pstmt.setInt(4, courseNoticeVO.getNotice_status());
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
	public void update(CourseNoticeVO courseNoticeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, courseNoticeVO.getMember_id());
			pstmt.setString(2, courseNoticeVO.getCourse_id());
			pstmt.setString(3, courseNoticeVO.getNotice());
			pstmt.setTimestamp(4, courseNoticeVO.getNotice_time());
			pstmt.setInt(5, courseNoticeVO.getNotice_status());
			pstmt.setString(6, courseNoticeVO.getCsnotice_no());
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
	public void delete(String csnotice_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, csnotice_no);

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
	public CourseNoticeVO findByPrimaryKey(String csnotice_no) {

		CourseNoticeVO courseNoticeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, csnotice_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseNoticeVO 也稱為 Domain objects
				courseNoticeVO = new CourseNoticeVO();
				courseNoticeVO.setCsnotice_no(rs.getString("CSNOTICE_NO"));
				courseNoticeVO.setMember_id(rs.getString("MEMBER_ID"));
				courseNoticeVO.setCourse_id(rs.getString("COURSE_ID"));
				courseNoticeVO.setNotice(rs.getString("NOTICE"));
				courseNoticeVO.setNotice_time(rs.getTimestamp("NOTICE_TIME"));
				courseNoticeVO.setNotice_status(rs.getInt("NOTICE_STATUS"));
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
		return courseNoticeVO;
	}

	@Override
	public List<CourseNoticeVO> getAll() {
		List<CourseNoticeVO> list = new ArrayList<CourseNoticeVO>();
		CourseNoticeVO courseNoticeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseNoticeVO 也稱為 Domain objects
				courseNoticeVO = new CourseNoticeVO();
				courseNoticeVO.setCsnotice_no(rs.getString("CSNOTICE_NO"));
				courseNoticeVO.setMember_id(rs.getString("MEMBER_ID"));
				courseNoticeVO.setCourse_id(rs.getString("COURSE_ID"));
				courseNoticeVO.setNotice(rs.getString("NOTICE"));
				courseNoticeVO.setNotice_time(rs.getTimestamp("NOTICE_TIME"));
				courseNoticeVO.setNotice_status(rs.getInt("NOTICE_STATUS"));
				list.add(courseNoticeVO);
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

		CourseNoticeJDBCDAO boj = new CourseNoticeJDBCDAO();

		// 新增
		CourseNoticeVO coupon01 = new CourseNoticeVO();
		coupon01.setMember_id("810003");
		coupon01.setCourse_id("C00001");
		coupon01.setNotice("Hello,你在嗎?");
		coupon01.setNotice_status(0);
		boj.insert(coupon01);

		// 修改
		CourseNoticeVO coupon02 = new CourseNoticeVO();
		coupon02.setCsnotice_no("220001");
		coupon02.setMember_id("810003");
		coupon02.setCourse_id("C00001");
		coupon02.setNotice("Hello,我在阿");
		coupon02.setNotice_time(java.sql.Timestamp.valueOf("2020-03-23 16:30:00"));
		coupon02.setNotice_status(1);

		boj.update(coupon02);

		// 刪除
//		boj.delete("220005");

		// 查詢
		CourseNoticeVO coupon03 = boj.findByPrimaryKey("220001");
		System.out.print(coupon03.getCsnotice_no() + ",");
		System.out.print(coupon03.getMember_id() + ",");
		System.out.print(coupon03.getCourse_id() + ",");
		System.out.print(coupon03.getNotice() + ",");
		System.out.print(coupon03.getNotice_time() + ",");
		System.out.print(coupon03.getNotice_status() + ",");
		System.out.println();
		System.out.println("---------------------");

		// 查詢所有
		List<CourseNoticeVO> list = boj.getAll();
		for (CourseNoticeVO aCoupon : list) {
			System.out.print(aCoupon.getCsnotice_no() + ",");
			System.out.print(aCoupon.getMember_id() + ",");
			System.out.print(aCoupon.getCourse_id() + ",");
			System.out.print(aCoupon.getNotice() + ",");
			System.out.print(aCoupon.getNotice_time() + ",");
			System.out.print(aCoupon.getNotice_status() + ",");
			System.out.println();
		}
	}

}
