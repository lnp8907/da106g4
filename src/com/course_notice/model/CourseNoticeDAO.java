package com.course_notice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CourseNoticeDAO implements CourseNoticeDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO COURSE_NOTICE (CSNOTICE_NO, MEMBER_ID,	COURSE_ID, NOTICE, NOTICE_STATUS) VALUES (SQ_CSNOTICE_NO.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT CSNOTICE_NO ,COURSE_ID,	MEMBER_ID, NOTICE, NOTICE_STATUS,to_char(NOTICE_TIME,'yyyy-mm-dd hh:mm:ss') NOTICE_TIME FROM COURSE_NOTICE order by CSNOTICE_NO";
	private static final String GET_ONE_STMT = "SELECT CSNOTICE_NO ,COURSE_ID,	MEMBER_ID, NOTICE, NOTICE_STATUS,to_char(NOTICE_TIME,'yyyy-mm-dd hh:mm:ss') NOTICE_TIME FROM COURSE_NOTICE where CSNOTICE_NO = ?";
	private static final String DELETE = "DELETE FROM COURSE_NOTICE where CSNOTICE_NO = ?";
	private static final String UPDATE = "UPDATE COURSE_NOTICE set MEMBER_ID=?,COURSE_ID=?,NOTICE=?,NOTICE_TIME=?,NOTICE_STATUS=? where CSNOTICE_NO = ?";

	@Override
	public void insert(CourseNoticeVO courseNoticeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, courseNoticeVO.getMember_id());
			pstmt.setString(2, courseNoticeVO.getCourse_id());
			pstmt.setString(3, courseNoticeVO.getNotice());
			pstmt.setInt(4, courseNoticeVO.getNotice_status());
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, courseNoticeVO.getMember_id());
			pstmt.setString(2, courseNoticeVO.getCourse_id());
			pstmt.setString(3, courseNoticeVO.getNotice());
			pstmt.setTimestamp(4, courseNoticeVO.getNotice_time());
			pstmt.setInt(5, courseNoticeVO.getNotice_status());
			pstmt.setString(6, courseNoticeVO.getCsnotice_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String csnotice_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, csnotice_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public CourseNoticeVO findByPrimaryKey(String csnotice_no) {

		CourseNoticeVO courseNoticeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
}