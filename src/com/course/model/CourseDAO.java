package com.course.model;

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

import com.recipe.model.RecipeVO;

public class CourseDAO implements CourseDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO COURSE (COURSE_ID, MEMBER_ID, COURSE_TYPE, COURSE_NAME,NUM_MAX, COURSE_START, COURSE_PRICE, COURSE_DETAIL, END_APP, COURSE_PHOTO, COURSE_LOCA) VALUES (SQ_COURSE_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT COURSE_ID, MEMBER_ID, COURSE_TYPE, COURSE_NAME, COURSE_STATUS, APP_NUM, NUM_MAX,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME,to_char(COURSE_START,'yyyy-mm-dd hh:mm:ss') COURSE_START,COURSE_PRICE, COURSE_DETAIL,to_char(END_APP,'yyyy-mm-dd') END_APP, COURSE_PHOTO, COURSE_LOCA FROM COURSE WHERE (COURSE_STATUS = 0 OR COURSE_STATUS = 1 OR COURSE_STATUS = 3) order by course_status,COURSE_START,course_id";
	private static final String GET_FOR_FONT = "SELECT COURSE_ID, MEMBER_ID, COURSE_TYPE, COURSE_NAME, COURSE_STATUS, APP_NUM, NUM_MAX,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME,to_char(COURSE_START,'yyyy-mm-dd hh:mm:ss') COURSE_START,COURSE_PRICE, COURSE_DETAIL,to_char(END_APP,'yyyy-mm-dd') END_APP, COURSE_PHOTO, COURSE_LOCA FROM COURSE  WHERE (COURSE_STATUS = 0 or COURSE_STATUS = 1) order by course_status,COURSE_START,course_id";
	private static final String GET_ONE_STMT = "SELECT COURSE_ID, MEMBER_ID, COURSE_TYPE, COURSE_NAME, COURSE_STATUS, APP_NUM, NUM_MAX,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME,to_char(COURSE_START,'yyyy-mm-dd hh:mm:ss') COURSE_START,COURSE_PRICE, COURSE_DETAIL,to_char(END_APP,'yyyy-mm-dd') END_APP, COURSE_PHOTO, COURSE_LOCA FROM COURSE where COURSE_ID = ?";
	private static final String DELETE = "DELETE FROM COURSE where COURSE_ID = ?";
	private static final String UPDATE = "UPDATE COURSE set COURSE_TYPE= ?, COURSE_NAME= ?,NUM_MAX= ?, COURSE_START= ?, COURSE_PRICE= ?, COURSE_DETAIL= ?, END_APP= ?, COURSE_PHOTO= ?, COURSE_LOCA=? where COURSE_ID = ?";
	private static final String GET_SIX_STMT = " SELECT * FROM (SELECT COURSE_ID, MEMBER_ID,COURSE_NAME,COURSE_START,COURSE_PRICE,COURSE_PHOTO FROM COURSE WHERE COURSE_STATUS = 0 order by CREATE_TIME DESC) where rownum between 1 and 6 ";
	private static final String GET_FOR_HOMEPAGE = " SELECT * FROM (SELECT COURSE_ID, MEMBER_ID,COURSE_NAME,COURSE_START,COURSE_PRICE,COURSE_PHOTO FROM COURSE WHERE COURSE_STATUS = 0 order by COURSE_START ) where rownum between 1 and 4 ";
	private static final String GET_CHEF_COURSE = "SELECT COURSE_ID, MEMBER_ID, COURSE_TYPE, COURSE_NAME, COURSE_STATUS, APP_NUM, NUM_MAX,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME,to_char(COURSE_START,'yyyy-mm-dd hh:mm:ss') COURSE_START,COURSE_PRICE, COURSE_DETAIL,to_char(END_APP,'yyyy-mm-dd') END_APP, COURSE_PHOTO, COURSE_LOCA FROM COURSE where MEMBER_ID = ? AND COURSE_STATUS != 2 ";
	private static final String GET_FOR_MANAGE = "SELECT COURSE_ID, MEMBER_ID, COURSE_TYPE, COURSE_NAME, COURSE_STATUS, APP_NUM, NUM_MAX,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME,to_char(COURSE_START,'yyyy-mm-dd hh:mm:ss') COURSE_START,COURSE_PRICE, COURSE_DETAIL,to_char(END_APP,'yyyy-mm-dd') END_APP, COURSE_PHOTO, COURSE_LOCA FROM COURSE  WHERE (COURSE_STATUS = 2 OR COURSE_STATUS =4) order by course_status,COURSE_START,course_id";
	private static final String GET_MANAGE_NUM = "SELECT count(1) FROM COURSE  WHERE (COURSE_STATUS = 2 or COURSE_STATUS = 4) ";
	private static final String UPDATE_STATUS = "UPDATE COURSE SET COURSE_STATUS = ? WHERE COURSE_ID = ? ";
	private static final String GET_FOR_MYCOURSE_LIST = "SELECT COURSE_ID, MEMBER_ID, COURSE_TYPE, COURSE_NAME, COURSE_STATUS, APP_NUM, NUM_MAX,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME,to_char(COURSE_START,'yyyy-mm-dd hh:mm:ss') COURSE_START,COURSE_PRICE, COURSE_DETAIL,to_char(END_APP,'yyyy-mm-dd') END_APP, COURSE_PHOTO, COURSE_LOCA FROM COURSE WHERE COURSE_STATUS = 0 order by course_status,COURSE_START,course_id";
	private static final String IS_COURSE_OVER = "SELECT COURSE_STATUS FROM COURSE where course_id = ?";

	@Override
	public boolean isCourseOver(String course_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean b = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(IS_COURSE_OVER);
			pstmt.setString(1, course_id);
			rs = pstmt.executeQuery();
			rs.next();
			if(1 == rs.getInt(1)) {
				b = true;
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return b;
	}
	@Override
	public List<CourseVO> getForMycourseList(){
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FOR_MYCOURSE_LIST);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseVO 也稱為 Domain objects
				courseVO = new CourseVO();
				courseVO.setCourse_id(rs.getString("COURSE_ID"));
				courseVO.setMember_id(rs.getString("MEMBER_ID"));
				courseVO.setCourse_type(rs.getString("COURSE_TYPE"));
				courseVO.setCourse_name(rs.getString("COURSE_NAME"));
				courseVO.setCourse_status(rs.getInt("COURSE_STATUS"));
				courseVO.setApp_num(rs.getInt("APP_NUM"));
				courseVO.setNum_max(rs.getInt("NUM_MAX"));
				courseVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				courseVO.setCourse_start(rs.getTimestamp("COURSE_START"));
				courseVO.setCourse_price(rs.getInt("COURSE_PRICE"));
				courseVO.setCourse_detail(rs.getString("COURSE_DETAIL"));
				courseVO.setEnd_app(rs.getDate("END_APP"));
				courseVO.setCourse_photo(rs.getBytes("COURSE_PHOTO"));
				courseVO.setCourse_loca(rs.getString("COURSE_LOCA"));
				list.add(courseVO);
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
	public void updateStatus(String course_id,Integer statusCode) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);


			pstmt.setInt(1,statusCode);
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
	public Integer getManageNum() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer count = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MANAGE_NUM);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<CourseVO> getForManage() {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FOR_MANAGE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseVO 也稱為 Domain objects
				courseVO = new CourseVO();
				courseVO.setCourse_id(rs.getString("COURSE_ID"));
				courseVO.setMember_id(rs.getString("MEMBER_ID"));
				courseVO.setCourse_type(rs.getString("COURSE_TYPE"));
				courseVO.setCourse_name(rs.getString("COURSE_NAME"));
				courseVO.setCourse_status(rs.getInt("COURSE_STATUS"));
				courseVO.setApp_num(rs.getInt("APP_NUM"));
				courseVO.setNum_max(rs.getInt("NUM_MAX"));
				courseVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				courseVO.setCourse_start(rs.getTimestamp("COURSE_START"));
				courseVO.setCourse_price(rs.getInt("COURSE_PRICE"));
				courseVO.setCourse_detail(rs.getString("COURSE_DETAIL"));
				courseVO.setEnd_app(rs.getDate("END_APP"));
				courseVO.setCourse_photo(rs.getBytes("COURSE_PHOTO"));
				courseVO.setCourse_loca(rs.getString("COURSE_LOCA"));
				list.add(courseVO);
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
	public List<CourseVO> getForFront() {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FOR_FONT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseVO 也稱為 Domain objects
				courseVO = new CourseVO();
				courseVO.setCourse_id(rs.getString("COURSE_ID"));
				courseVO.setMember_id(rs.getString("MEMBER_ID"));
				courseVO.setCourse_type(rs.getString("COURSE_TYPE"));
				courseVO.setCourse_name(rs.getString("COURSE_NAME"));
				courseVO.setCourse_status(rs.getInt("COURSE_STATUS"));
				courseVO.setApp_num(rs.getInt("APP_NUM"));
				courseVO.setNum_max(rs.getInt("NUM_MAX"));
				courseVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				courseVO.setCourse_start(rs.getTimestamp("COURSE_START"));
				courseVO.setCourse_price(rs.getInt("COURSE_PRICE"));
				courseVO.setCourse_detail(rs.getString("COURSE_DETAIL"));
				courseVO.setEnd_app(rs.getDate("END_APP"));
				courseVO.setCourse_photo(rs.getBytes("COURSE_PHOTO"));
				courseVO.setCourse_loca(rs.getString("COURSE_LOCA"));
				list.add(courseVO);
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
	public List<CourseVO> getChefCourse(String member_id) {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CHEF_COURSE);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseVO 也稱為 Domain objects
				courseVO = new CourseVO();
				courseVO.setCourse_id(rs.getString("COURSE_ID"));
				courseVO.setMember_id(rs.getString("MEMBER_ID"));
				courseVO.setCourse_type(rs.getString("COURSE_TYPE"));
				courseVO.setCourse_name(rs.getString("COURSE_NAME"));
				courseVO.setCourse_status(rs.getInt("COURSE_STATUS"));
				courseVO.setApp_num(rs.getInt("APP_NUM"));
				courseVO.setNum_max(rs.getInt("NUM_MAX"));
				courseVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				courseVO.setCourse_start(rs.getTimestamp("COURSE_START"));
				courseVO.setCourse_price(rs.getInt("COURSE_PRICE"));
				courseVO.setCourse_detail(rs.getString("COURSE_DETAIL"));
				courseVO.setEnd_app(rs.getDate("END_APP"));
				courseVO.setCourse_loca(rs.getString("COURSE_LOCA"));
				list.add(courseVO);
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
	public void insert(CourseVO courseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, courseVO.getMember_id());
			pstmt.setString(2, courseVO.getCourse_type());
			pstmt.setString(3, courseVO.getCourse_name());
			pstmt.setInt(4, courseVO.getNum_max());
			pstmt.setTimestamp(5, courseVO.getCourse_start());
			pstmt.setInt(6, courseVO.getCourse_price());
			pstmt.setString(7, courseVO.getCourse_detail());
			pstmt.setDate(8, courseVO.getEnd_app());
			pstmt.setBytes(9, courseVO.getCourse_photo());
			pstmt.setString(10, courseVO.getCourse_loca());
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
	public void update(CourseVO courseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, courseVO.getCourse_type());
			pstmt.setString(2, courseVO.getCourse_name());
			pstmt.setInt(3, courseVO.getNum_max());
			pstmt.setTimestamp(4, courseVO.getCourse_start());
			pstmt.setInt(5, courseVO.getCourse_price());
			pstmt.setString(6, courseVO.getCourse_detail());
			pstmt.setDate(7, courseVO.getEnd_app());
			pstmt.setBytes(8, courseVO.getCourse_photo());
			pstmt.setString(9, courseVO.getCourse_loca());
			pstmt.setString(10, courseVO.getCourse_id());
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
	public CourseVO findByPrimaryKey(String c_no) {

		CourseVO courseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, c_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseVO 也稱為 Domain objects
				courseVO = new CourseVO();
				courseVO.setCourse_id(rs.getString("COURSE_ID"));
				courseVO.setMember_id(rs.getString("MEMBER_ID"));
				courseVO.setCourse_type(rs.getString("COURSE_TYPE"));
				courseVO.setCourse_name(rs.getString("COURSE_NAME"));
				courseVO.setCourse_status(rs.getInt("COURSE_STATUS"));
				courseVO.setApp_num(rs.getInt("APP_NUM"));
				courseVO.setNum_max(rs.getInt("NUM_MAX"));
				courseVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				courseVO.setCourse_start(rs.getTimestamp("COURSE_START"));
				courseVO.setCourse_price(rs.getInt("COURSE_PRICE"));
				courseVO.setCourse_detail(rs.getString("COURSE_DETAIL"));
				courseVO.setEnd_app(rs.getDate("END_APP"));
				courseVO.setCourse_photo(rs.getBytes("COURSE_PHOTO"));
				courseVO.setCourse_loca(rs.getString("COURSE_LOCA"));
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
		return courseVO;
	}

	@Override
	public List<CourseVO> getAll() {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseVO 也稱為 Domain objects
				courseVO = new CourseVO();
				courseVO.setCourse_id(rs.getString("COURSE_ID"));
				courseVO.setMember_id(rs.getString("MEMBER_ID"));
				courseVO.setCourse_type(rs.getString("COURSE_TYPE"));
				courseVO.setCourse_name(rs.getString("COURSE_NAME"));
				courseVO.setCourse_status(rs.getInt("COURSE_STATUS"));
				courseVO.setApp_num(rs.getInt("APP_NUM"));
				courseVO.setNum_max(rs.getInt("NUM_MAX"));
				courseVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				courseVO.setCourse_start(rs.getTimestamp("COURSE_START"));
				courseVO.setCourse_price(rs.getInt("COURSE_PRICE"));
				courseVO.setCourse_detail(rs.getString("COURSE_DETAIL"));
				courseVO.setEnd_app(rs.getDate("END_APP"));
				courseVO.setCourse_photo(rs.getBytes("COURSE_PHOTO"));
				courseVO.setCourse_loca(rs.getString("COURSE_LOCA"));
				list.add(courseVO);
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
	public List<CourseVO> getForHomePage() {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FOR_HOMEPAGE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseVO 也稱為 Domain objects
				courseVO = new CourseVO();
				courseVO.setCourse_id(rs.getString("COURSE_ID"));
				courseVO.setMember_id(rs.getString("MEMBER_ID"));
				courseVO.setCourse_name(rs.getString("COURSE_NAME"));
				courseVO.setCourse_start(rs.getTimestamp("COURSE_START"));
				courseVO.setCourse_price(rs.getInt("COURSE_PRICE"));
				courseVO.setCourse_photo(rs.getBytes("COURSE_PHOTO"));

				list.add(courseVO);
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
	public List<CourseVO> getTopSix() {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SIX_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseVO 也稱為 Domain objects
				courseVO = new CourseVO();
				courseVO.setCourse_id(rs.getString("COURSE_ID"));
				courseVO.setMember_id(rs.getString("MEMBER_ID"));
				courseVO.setCourse_name(rs.getString("COURSE_NAME"));
				courseVO.setCourse_start(rs.getTimestamp("COURSE_START"));
				courseVO.setCourse_price(rs.getInt("COURSE_PRICE"));
				courseVO.setCourse_photo(rs.getBytes("COURSE_PHOTO"));

				list.add(courseVO);
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
