package com.mycourse.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MycourseJDBCDAO implements MycourseDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = 
			"INSERT INTO MYCOURSE (COURSE_ID, MEMBER_ID, APP_STATUS, PAY_PRICE) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT COURSE_ID, MEMBER_ID, APP_STATUS,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME, PAY_PRICE FROM MYCOURSE order by COURSE_ID";
	private static final String GET_ONE_STMT = 
			"SELECT COURSE_ID, MEMBER_ID, APP_STATUS,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME, PAY_PRICE FROM MYCOURSE where COURSE_ID = ? and MEMBER_ID = ? ";
	private static final String DELETE = 
			"DELETE FROM MYCOURSE where COURSE_ID = ? and MEMBER_ID = ? ";
	private static final String UPDATE = 
			"UPDATE MYCOURSE set  APP_STATUS =?, PAY_PRICE =?, CREATE_TIME= ? where COURSE_ID = ? and MEMBER_ID = ?";
	private static final String GET_JOINED_MEMBER_STMT = "SELECT COURSE_ID, MEMBER_ID, APP_STATUS,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME , PAY_PRICE FROM MYCOURSE where COURSE_ID = ? order by MEMBER_ID ";
	
	private static final String GET_JOINING_COURSE_STMT = "SELECT COURSE_ID, MEMBER_ID, APP_STATUS,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME , PAY_PRICE FROM MYCOURSE where MEMBER_ID = ? order by COURSE_ID ";		
	
	@Override
	public void insert(MycourseVO mycourseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mycourseVO.getCourse_id());
			pstmt.setString(2, mycourseVO.getMember_id());
			pstmt.setInt(3, mycourseVO.getApp_status());
			pstmt.setInt(4, mycourseVO.getPay_price());
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
	public void update(MycourseVO mycourseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, mycourseVO.getApp_status());
			pstmt.setInt(2, mycourseVO.getPay_price());
			pstmt.setTimestamp(3, mycourseVO.getCreate_time());
			pstmt.setString(4, mycourseVO.getCourse_id());
			pstmt.setString(5, mycourseVO.getMember_id());
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
	public void delete(String course_id, String member_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, course_id);
			pstmt.setString(2, member_id);

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
	public MycourseVO findByPrimaryKey(String course_id, String member_id) {

		MycourseVO mycourseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return mycourseVO;
	}
	
	
	
	public List<MycourseVO> findJoinedMemberByPrimaryKey(String course_id){
		List<MycourseVO> list = new ArrayList<MycourseVO>();
		MycourseVO mycourseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	
	
    public List<MycourseVO> findJoingCourseByPrimaryKey(String member_id){
		List<MycourseVO> list = new ArrayList<MycourseVO>();
		MycourseVO mycourseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<MycourseVO> getAll() {
		List<MycourseVO> list = new ArrayList<MycourseVO>();
		MycourseVO mycourseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		MycourseJDBCDAO boj = new MycourseJDBCDAO();

		// 新增
		MycourseVO coupon01 = new MycourseVO();
		coupon01.setCourse_id("C00001");
		coupon01.setMember_id("810004");
		coupon01.setApp_status(0);
		coupon01.setPay_price(20000);
		boj.insert(coupon01);
		


//		// 修改
		MycourseVO coupon02 = new MycourseVO();
		coupon02.setCourse_id("C00001");
		coupon02.setMember_id("810003");
		coupon02.setApp_status(0);
		coupon02.setPay_price(200000);
		coupon02.setCreate_time(java.sql.Timestamp.valueOf("2020-04-15 10:30:35"));
		
		boj.update(coupon02);
//
//		// 刪除
		boj.delete("C00001","810004");
//
//		// 查詢
		MycourseVO coupon03 = boj.findByPrimaryKey("C00001","810003");
		System.out.print(coupon03.getCourse_id() + ",");
		System.out.print(coupon03.getMember_id() + ",");
		System.out.print(coupon03.getApp_status() + ",");
		System.out.print(coupon03.getCreate_time() + ",");
		System.out.print(coupon03.getPay_price() + ",");
		System.out.println();
		System.out.println("---------------------");
		
		//查詢參加該課程的會員
		List<MycourseVO> list = boj.findJoinedMemberByPrimaryKey("C00001");
		for (MycourseVO aCoupon : list) {
			System.out.print(aCoupon.getCourse_id() + ",");
			System.out.print(aCoupon.getMember_id() + ",");
			System.out.print(aCoupon.getApp_status() + ",");
			System.out.print(aCoupon.getCreate_time() + ",");
			System.out.print(aCoupon.getPay_price() + ",");
			System.out.println();
			System.out.println("---------------------");
		}
		
		//查詢該會員參加的課程
		List<MycourseVO> list1 = boj.findJoingCourseByPrimaryKey("810003");
		for (MycourseVO aCoupon : list1) {
			System.out.print(aCoupon.getCourse_id() + ",");
			System.out.print(aCoupon.getMember_id() + ",");
			System.out.print(aCoupon.getApp_status() + ",");
			System.out.print(aCoupon.getCreate_time() + ",");
			System.out.print(aCoupon.getPay_price() + ",");
			System.out.println();
			System.out.println("---------------------");
		}

		// 查詢所有
		List<MycourseVO> list2 = boj.getAll();
		for (MycourseVO aCoupon : list2) {
			System.out.print(aCoupon.getCourse_id() + ",");
			System.out.print(aCoupon.getMember_id() + ",");
			System.out.print(aCoupon.getApp_status() + ",");
			System.out.print(aCoupon.getCreate_time() + ",");
			System.out.print(aCoupon.getPay_price() + ",");
			System.out.println();
		}
	}
}


