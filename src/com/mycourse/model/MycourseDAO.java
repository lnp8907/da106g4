package com.mycourse.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MycourseDAO implements MycourseDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO MYCOURSE (COURSE_ID, MEMBER_ID,PAY_PRICE) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT COURSE_ID, MEMBER_ID, APP_STATUS,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME, PAY_PRICE FROM MYCOURSE order by APP_STATUS";
	private static final String GET_ONE_STMT = "SELECT COURSE_ID, MEMBER_ID, APP_STATUS,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME, PAY_PRICE FROM MYCOURSE where COURSE_ID = ? and MEMBER_ID = ? ";
	private static final String DELETE = "DELETE FROM MYCOURSE where COURSE_ID = ? and MEMBER_ID = ? ";
	private static final String UPDATE = "UPDATE MYCOURSE set  APP_STATUS =?, PAY_PRICE =?, CREATE_TIME= ? where COURSE_ID = ? and MEMBER_ID = ?";
	private static final String GET_JOINED_MEMBER_STMT = "SELECT COURSE_ID, MEMBER_ID, APP_STATUS,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME , PAY_PRICE FROM MYCOURSE where COURSE_ID = ? ORDER BY APP_STATUS, MEMBER_ID ";
	private static final String GET_JOINING_COURSE_STMT = "SELECT COURSE_ID, MEMBER_ID, APP_STATUS,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME , PAY_PRICE FROM MYCOURSE where MEMBER_ID = ? ORDER BY APP_STATUS,COURSE_ID ";
	private static final String CHANGE_ONE_STATUS = "UPDATE MYCOURSE set  APP_STATUS =? where COURSE_ID = ? and MEMBER_ID = ?";
	private static final String CHANGE_ALL_STATUS = "UPDATE MYCOURSE set  APP_STATUS =? where COURSE_ID = ? AND  APP_STATUS != 4 ";
	private static final String GET_APPLIED_NUM = "SELECT COUNT(1) FROM MYCOURSE  where COURSE_ID = ? AND (APP_STATUS=0 OR APP_STATUS=1 OR APP_STATUS=2) ";
	private static final String GET_ISAPPLIED = "SELECT COUNT(1) FROM MYCOURSE  where COURSE_ID = ? AND MEMBER_ID = ? ";
	private static final String GET_ALL_CHECK_NUM = "SELECT COUNT(1) FROM MYCOURSE  where APP_STATUS = 2 ";
	private static final String GET_ONE_CHECK_NUM = "SELECT COUNT(1) FROM MYCOURSE  where  course_id = ? AND APP_STATUS = 2 ";

	@Override
	public Integer getOneCheckNum(String course_id) {
		Integer count = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CHECK_NUM);

			pstmt.setString(1, course_id);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);

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
		return count;
	}

	@Override
	public Integer getAllCheckNum() {
		Integer count = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_CHECK_NUM);

			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);

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
		return count;
	}

	@Override
	public boolean isApplied(String course_id, String member_id) {
		Integer count = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ISAPPLIED);

			pstmt.setString(1, course_id);
			pstmt.setString(2, member_id);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);

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
		if (count < 1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isFull(Integer num_max, String course_id) {
		Integer count = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_APPLIED_NUM);

			pstmt.setString(1, course_id);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);

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
		if (num_max > count) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Integer appliedNum(String course_id) {
		Integer count = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_APPLIED_NUM);

			pstmt.setString(1, course_id);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);

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
		return count;
	}

	@Override
	public void changeAllStatus(String course_id, Integer statusNum) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_ALL_STATUS);

			pstmt.setInt(1, statusNum);
			pstmt.setString(2, course_id);
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
	public void changeOneStatus(String course_id,String member_id, String course_status) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_ONE_STATUS);
			pstmt.setInt(1, Integer.parseInt(("course_status")));
			pstmt.setString(2, ("course_id"));
			pstmt.setString(3, ("member_id"));
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
	public void changeOneStatus(Map<String,String[]> changeStatus) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int count =0;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_ONE_STATUS);
			while(count<changeStatus.size()) {
			pstmt.setInt(1, Integer.parseInt(changeStatus.get("course_status")[count]));
			pstmt.setString(2, changeStatus.get("course_id")[count]);
			pstmt.setString(3, changeStatus.get("member_id")[count]);
			pstmt.executeUpdate();
			count++;
			}
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
	public void insert(MycourseVO mycourseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mycourseVO.getCourse_id());
			pstmt.setString(2, mycourseVO.getMember_id());
			pstmt.setInt(3, mycourseVO.getPay_price());
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
	public void update(MycourseVO mycourseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, mycourseVO.getApp_status());
			pstmt.setInt(2, mycourseVO.getPay_price());
			pstmt.setTimestamp(3, mycourseVO.getCreate_time());
			pstmt.setString(4, mycourseVO.getCourse_id());
			pstmt.setString(5, mycourseVO.getMember_id());
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
	public void delete(String course_id, String member_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, course_id);
			pstmt.setString(2, member_id);

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
	public MycourseVO findByPrimaryKey(String course_id, String member_id) {
		MycourseVO mycourseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, course_id);
			pstmt.setString(2, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// mycourseVO 也稱為 Domain objects
				mycourseVO = new MycourseVO();
				mycourseVO.setCourse_id(rs.getString("COURSE_ID"));
				mycourseVO.setMember_id(rs.getString("MEMBER_ID"));
				mycourseVO.setApp_status(rs.getInt("APP_STATUS"));
				mycourseVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				mycourseVO.setPay_price(rs.getInt("PAY_PRICE"));
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
		return mycourseVO;
	}

	public List<MycourseVO> findJoinedMemberByPrimaryKey(String course_id) {
		List<MycourseVO> list = new ArrayList<MycourseVO>();
		MycourseVO mycourseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_JOINED_MEMBER_STMT);

			pstmt.setString(1, course_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// mycourseVO 也稱為 Domain objects
				mycourseVO = new MycourseVO();
				mycourseVO.setCourse_id(rs.getString("COURSE_ID"));
				mycourseVO.setMember_id(rs.getString("MEMBER_ID"));
				mycourseVO.setApp_status(rs.getInt("APP_STATUS"));
				mycourseVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				mycourseVO.setPay_price(rs.getInt("PAY_PRICE"));
				list.add(mycourseVO);
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

	public List<MycourseVO> findJoingCourseByPrimaryKey(String member_id) {
		List<MycourseVO> list = new ArrayList<MycourseVO>();
		MycourseVO mycourseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_JOINING_COURSE_STMT);

			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// mycourseVO 也稱為 Domain objects
				mycourseVO = new MycourseVO();
				mycourseVO.setCourse_id(rs.getString("COURSE_ID"));
				mycourseVO.setMember_id(rs.getString("MEMBER_ID"));
				mycourseVO.setApp_status(rs.getInt("APP_STATUS"));
				mycourseVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				mycourseVO.setPay_price(rs.getInt("PAY_PRICE"));
				list.add(mycourseVO);
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

	@Override
	public List<MycourseVO> getAll() {
		List<MycourseVO> list = new ArrayList<MycourseVO>();
		MycourseVO mycourseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// mycourseVO 也稱為 Domain objects
				mycourseVO = new MycourseVO();
				mycourseVO.setCourse_id(rs.getString("COURSE_ID"));
				mycourseVO.setMember_id(rs.getString("MEMBER_ID"));
				mycourseVO.setApp_status(rs.getInt("APP_STATUS"));
				mycourseVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				mycourseVO.setPay_price(rs.getInt("PAY_PRICE"));
				list.add(mycourseVO);
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
