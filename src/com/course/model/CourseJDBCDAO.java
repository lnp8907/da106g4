package com.course.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseJDBCDAO implements CourseDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = "INSERT INTO COURSE (COURSE_ID, MEMBER_ID, COURSE_TYPE, COURSE_NAME, COURSE_STATUS, APP_NUM, NUM_MAX, COURSE_START, COURSE_PRICE, COURSE_DETAIL, END_APP, COURSE_PHOTO, COURSE_LOCA) VALUES (SQ_COURSE_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT COURSE_ID, MEMBER_ID, COURSE_TYPE, COURSE_NAME, COURSE_STATUS, APP_NUM, NUM_MAX,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME,to_char(COURSE_START,'yyyy-mm-dd hh:mm:ss') COURSE_START,COURSE_PRICE, COURSE_DETAIL,to_char(END_APP,'yyyy-mm-dd') END_APP, COURSE_PHOTO, COURSE_LOCA FROM COURSE order by COURSE_ID";
	private static final String GET_ONE_STMT = "SELECT COURSE_ID, MEMBER_ID, COURSE_TYPE, COURSE_NAME, COURSE_STATUS, APP_NUM, NUM_MAX,to_char(CREATE_TIME,'yyyy-mm-dd hh:mm:ss') CREATE_TIME,to_char(COURSE_START,'yyyy-mm-dd hh:mm:ss') COURSE_START,COURSE_PRICE, COURSE_DETAIL,to_char(END_APP,'yyyy-mm-dd') END_APP, COURSE_PHOTO, COURSE_LOCA FROM COURSE where COURSE_ID = ?";
	private static final String DELETE = "DELETE FROM COURSE where COURSE_ID = ?";
	private static final String UPDATE = "UPDATE COURSE set MEMBER_ID= ?, COURSE_TYPE= ?, COURSE_NAME= ?, COURSE_STATUS= ?, APP_NUM= ?, NUM_MAX= ?, COURSE_START= ?, COURSE_PRICE= ?, COURSE_DETAIL= ?, END_APP= ?, COURSE_PHOTO= ?, COURSE_LOCA=? where COURSE_ID = ?";
	private static final String GET_SIX_STMT = "SELECT COURSE_ID, MEMBER_ID,COURSE_NAME,COURSE_START,COURSE_PRICE,COURSE_PHOTO FROM COURSE where rownum  between 1 and 6 order by CREATE_TIME desc";

	@Override
	public void insert(CourseVO courseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, courseVO.getMember_id());
			pstmt.setString(2, courseVO.getCourse_type());
			pstmt.setString(3, courseVO.getCourse_name());
			pstmt.setInt(4, courseVO.getCourse_status());
			pstmt.setInt(5, courseVO.getApp_num());
			pstmt.setInt(6, courseVO.getNum_max());
			pstmt.setTimestamp(7, courseVO.getCourse_start());
			pstmt.setInt(8, courseVO.getCourse_price());
			pstmt.setString(9, courseVO.getCourse_detail());
			pstmt.setDate(10, courseVO.getEnd_app());
			pstmt.setBytes(11, courseVO.getCourse_photo());
			pstmt.setString(12, courseVO.getCourse_loca());
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
	public void update(CourseVO courseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, courseVO.getMember_id());
			pstmt.setString(2, courseVO.getCourse_type());
			pstmt.setString(3, courseVO.getCourse_name());
			pstmt.setInt(4, courseVO.getCourse_status());
			pstmt.setInt(5, courseVO.getApp_num());
			pstmt.setInt(6, courseVO.getNum_max());
			pstmt.setTimestamp(7, courseVO.getCourse_start());
			pstmt.setInt(8, courseVO.getCourse_price());
			pstmt.setString(9, courseVO.getCourse_detail());
			pstmt.setDate(10, courseVO.getEnd_app());
			pstmt.setBytes(11, courseVO.getCourse_photo());
			pstmt.setString(12, courseVO.getCourse_loca());
			pstmt.setString(13, courseVO.getCourse_id());
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
	public void delete(String course_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, course_id);

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
	public CourseVO findByPrimaryKey(String course_id) {

		CourseVO courseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, course_id);

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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


	
		@Override
		public List<CourseVO> getTopSix() {
			List<CourseVO> list = new ArrayList<CourseVO>();
			CourseVO courseVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
					courseVO.setCourse_photo(rs.getBytes("COURSE_PHOTO"));;
					list.add(courseVO);
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

		CourseJDBCDAO jdbc = new CourseJDBCDAO();

		// 新增
//		int ran = (int) Math.random() * 28 + 1;
//		int ran2 = (int) Math.random() * 4 + 5;
//		int ran3 = (int) Math.random() * 9 + 10;
//		String hour = String.valueOf(ran3);
//		String date = String.valueOf(ran);
//		String month = String.valueOf(ran2);
//		String monthNext = String.valueOf(ran2 - 1);
//		month = "0"+month;
//		if (ran < 10) {
//			date = "0"+date;
//		}
//
//		CourseVO course01 = new CourseVO();
//		course01.setMember_id("81000"+String.valueOf((int)Math.random()*5+1));
//		course01.setCourse_type("點心");
//		course01.setCourse_name("手作點心:森林巧克力蛋糕");
//		course01.setCourse_status(1);
//		course01.setApp_num((int) Math.random() * 10 + 5);
//		course01.setNum_max((int) Math.random() * 4 * 5 + 10);
//		course01.setCourse_start(java.sql.Timestamp.valueOf("2020-" + month + "-" + date + " " + hour + ":00:00"));
//		course01.setCourse_price((int)Math.random()*5000+1000);
//		course01.setCourse_detail("巧克力蛋糕是一種以巧克力製成的蛋糕，於生日派對及婚禮常見，也是常見的甜品之一。巧克力蛋糕有時被誤稱為黑森林蛋糕，雖然兩者實際上有分別。");
//		course01.setEnd_app(java.sql.Date.valueOf("2020-" + monthNext + "-" + date));
//		course01.setCourse_photo(jdbc.getPic("image/course/10.jpg"));
//		course01.setCourse_loca("320桃園市中壢區中大路300號");
//
//		jdbc.insert(course01);

		// 修改
//		CourseVO course02 = new CourseVO();
//		course02.setCourse_id("210001");
//		course02.setMember_id("C0002");
//		course02.setCourse_type("點心");
//		course02.setCourse_name("泰式酸辣湯");
//		course02.setCourse_status(3);
//		course02.setApp_num(45);
//		course02.setNum_max(99);
//		course02.setCourse_start(java.sql.Date.valueOf("2220-06-21"));
//		course02.setCourse_end(java.sql.Date.valueOf("2220-06-23"));
//		course02.setCourse_price(999999);
//		course02.setCourse_detail("噁心譚德塞,喜愛泡泡菜");
//		course02.setEnd_app(java.sql.Date.valueOf("2220-05-24"));
//		course02.setCourse_photo(jdbc.getPic("image/tandersai.jpg"));
//		course02.setCourse_loca("錫大北七路");

//		jdbc.update(course02);

		// 刪除
//		jdbc.delete("210002");

		// 查詢
//		CourseVO course03 = jdbc.findByPrimaryKey("210001");
//		System.out.print(course03.getCourse_id() + ",");
//		System.out.print(course03.getMember_id() + ",");
//		System.out.print(course03.getCourse_type() + ",");
//		System.out.print(course03.getCourse_name() + ",");
//		System.out.print(course03.getCourse_status() + ",");
//		System.out.print(course03.getApp_num() + ",");
//		System.out.print(course03.getNum_max() + ",");
//		System.out.print(course03.getCreate_time() + ",");
//		System.out.print(course03.getCourse_start() + ",");
//		System.out.print(course03.getCourse_end() + ",");
//		System.out.print(course03.getCourse_price() + ",");
//		System.out.print(course03.getCourse_detail() + ",");
//		System.out.print(course03.getEnd_app() + ",");
//		System.out.print(course03.getCourse_photo() + ",");
//		System.out.print(course03.getCourse_loca() + ",");
//		System.out.println();
//		System.out.println("---------------------");

		// 查詢所有
		List<CourseVO> list = jdbc.getAll();
		for (CourseVO aCourse : list) {
			System.out.print(aCourse.getCourse_id() + ",");
			System.out.print(aCourse.getMember_id() + ",");
			System.out.print(aCourse.getCourse_type() + ",");
			System.out.print(aCourse.getCourse_name() + ",");
			System.out.print(aCourse.getCourse_status() + ",");
			System.out.print(aCourse.getApp_num() + ",");
			System.out.print(aCourse.getNum_max() + ",");
			System.out.print(aCourse.getCreate_time() + ",");
			System.out.print(aCourse.getCourse_start() + ",");
			System.out.print(aCourse.getCourse_price() + ",");
			System.out.print(aCourse.getCourse_detail() + ",");
			System.out.print(aCourse.getEnd_app() + ",");
			System.out.print(aCourse.getCourse_photo() + ",");
			System.out.print(aCourse.getCourse_loca() + ",");
			System.out.println();
		}
	}

	private byte[] getPic(String path) {
		File file = new File(path);
		byte[] pic = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			pic = new byte[fis.available()];
			while (fis.available() != 0) {
				fis.read(pic);
			}
			fis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pic;
	}

}
