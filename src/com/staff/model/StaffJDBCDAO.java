package com.staff.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodporn.member.model.MemberJDBCDAO;
import com.foodporn.member.model.MemberVO;

public class StaffJDBCDAO implements StaffDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";
	 private static final String INSERT_STMT = "INSERT INTO STAFF (STAFF_ID,STAFF_ACCOUNT,STAFF_PASSWORD,STAFF_NAME,GENDER,PHONE,STAFF_STATUS) VALUES ( SQ_STAFF_ID.NEXTVAL,?,?,?, ?, ?, ?)";
	    private static final String GET_ALL_STMT = "SELECT STAFF_ID,STAFF_ACCOUNT,STAFF_PASSWORD,STAFF_NAME,GENDER,PHONE,STAFF_STATUS FROM STAFF order by STAFF_ID";
	    private static final String GET_ONE_STMT = "SELECT STAFF_ID,STAFF_ACCOUNT,STAFF_PASSWORD,STAFF_NAME,GENDER,PHONE,STAFF_STATUS FROM STAFF WHERE STAFF_ID = ?";
	    private static final String DELETE = "DELETE FROM STAFF WHERE STAFF_ID = ? ";
	private static final String UPDATEALL = "UPDATE STAFF SET  STAFF_ACCOUNT=?, STAFF_PASSWORD=?, STAFF_NAME=?, GENDER=? , PHONE=?, STAFF_STATUS=? WHERE  STAFF_ID= ?";
	// 只修改密碼
	private static final String UPDATEPASSWORD = "UPDATE STAFF SET   STAFF_PASSWORD=? WHERE  STAFF_ID= ?";

	// 只修改密碼
	public void updatepassword(StaffVO staffvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEPASSWORD);
			pstmt.setString(1, staffvo.getStaff_password());
			pstmt.setString(2, staffvo.getStaff_id());
			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException se) {
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

	}

	@Override
	public void insert(StaffVO staffvo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, staffvo.getStaff_account());
			pstmt.setString(2, staffvo.getStaff_password());
			pstmt.setString(3, staffvo.getStaff_name());
			pstmt.setInt(4, staffvo.getGender());
			pstmt.setString(5, staffvo.getPhone());
			pstmt.setInt(6, staffvo.getStaff_status());

			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException se) {
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

	}

	@Override
	public void update(StaffVO staffvo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEALL);
			pstmt.setString(1, staffvo.getStaff_account());
			pstmt.setString(2, staffvo.getStaff_password());
			pstmt.setString(3, staffvo.getStaff_name());
			pstmt.setInt(4, staffvo.getGender());
			pstmt.setString(5, staffvo.getPhone());
			pstmt.setInt(6, staffvo.getStaff_status());
			pstmt.setString(7, staffvo.getStaff_id());
			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException se) {
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

	}

	@Override
	public void delete(String staff_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, staff_id);
			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException se) {
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
	public StaffVO findByPrimaryKey(String staff_id) {
		StaffVO staffvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, staff_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				staffvo = new StaffVO();
				staffvo.setStaff_id(rs.getString("staff_id"));
				staffvo.setStaff_account(rs.getString("staff_account"));
				staffvo.setStaff_password(rs.getString("staff_password"));
				staffvo.setStaff_name(rs.getString("staff_name"));
				staffvo.setGender(rs.getInt("gender"));
				staffvo.setPhone(rs.getString("phone"));
				staffvo.setStaff_status(rs.getInt("staff_status"));

			}
			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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
		return staffvo;
	}

	@Override
	public List<StaffVO> getAll() {
		// TODO Auto-generated method stub
		List<StaffVO> list = new ArrayList();
		StaffVO staffvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				staffvo = new StaffVO();
				staffvo.setStaff_id(rs.getString("staff_id"));
				staffvo.setStaff_account(rs.getString("staff_account"));
				staffvo.setStaff_password(rs.getString("staff_password"));
				staffvo.setStaff_name(rs.getString("staff_name"));
				staffvo.setGender(rs.getInt("gender"));
				staffvo.setPhone(rs.getString("phone"));
				staffvo.setStaff_status(rs.getInt("staff_status"));
				list.add(staffvo); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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

			StaffJDBCDAO dao = new StaffJDBCDAO();

		
			StaffVO staffVO01 = new StaffVO();
			staffVO01.setStaff_account("g0123456");
			staffVO01.setStaff_password("123456");
			staffVO01.setStaff_name("賴和駿");
			staffVO01.setGender(0);
			staffVO01.setPhone("0965825888");
			staffVO01.setStaff_status(0);				
			dao.insert(staffVO01);

			
			StaffVO staffVO02 = new StaffVO();
			staffVO02.setStaff_account("pn536546");
			staffVO02.setStaff_password("a151341");
			staffVO02.setStaff_name("陳俊宏");
			staffVO02.setGender(0);
			staffVO02.setPhone("0986358977");
			staffVO02.setStaff_status(0);				
			dao.insert(staffVO02);

			StaffVO staffVO03 = new StaffVO();
			staffVO03.setStaff_account("bcb565437");
			staffVO03.setStaff_password("p84516");
			staffVO03.setStaff_name("張昱宏");
			staffVO03.setGender(0);
			staffVO03.setPhone("0978885635");
			staffVO03.setStaff_status(0);				
			dao.insert(staffVO03);
			
			StaffVO staffVO04 = new StaffVO();
			staffVO04.setStaff_account("h76468");
			staffVO04.setStaff_password("no845145");
			staffVO04.setStaff_name("李珮君");
			staffVO04.setGender(0);
			staffVO04.setPhone("0968932566");
			staffVO04.setStaff_status(1);				
			dao.insert(staffVO04);
			
			StaffVO staffVO05 = new StaffVO();
			staffVO05.setStaff_account("qe373338");
			staffVO05.setStaff_password("y546815");
			staffVO05.setStaff_name("許敬妏");
			staffVO05.setGender(1);
			staffVO05.setPhone("0985856332");
			staffVO05.setStaff_status(1);				
			dao.insert(staffVO05);
			
			StaffVO staffVO06 = new StaffVO();
			staffVO06.setStaff_account("s46785365");
			staffVO06.setStaff_password("e756456");
			staffVO06.setStaff_name("陳協源");
			staffVO06.setGender(0);
			staffVO06.setPhone("0965485252");
			staffVO06.setStaff_status(1);				
			dao.insert(staffVO06);
			
			StaffVO staffVO07 = new StaffVO();
			staffVO07.setStaff_account("g576878");
			staffVO07.setStaff_password("u844646");
			staffVO07.setStaff_name("井惠音");
			staffVO07.setGender(1);
			staffVO07.setPhone("0963658924");
			staffVO07.setStaff_status(2);				
			dao.insert(staffVO07);
			
			StaffVO staffVO08 = new StaffVO();
			staffVO08.setStaff_account("g34536725");
			staffVO08.setStaff_password("e1231541");
			staffVO08.setStaff_name("羅心佳");
			staffVO08.setGender(1);
			staffVO08.setPhone("0968523569");
			staffVO08.setStaff_status(2);				
			dao.insert(staffVO08);
			
			StaffVO staffVO09 = new StaffVO();
			staffVO09.setStaff_account("a9354678");
			staffVO09.setStaff_password("r175664");
			staffVO09.setStaff_name("吳佩生");
			staffVO09.setGender(1);
			staffVO09.setPhone("0978214568");
			staffVO09.setStaff_status(2);				
			dao.insert(staffVO09);
			
			StaffVO staffVO10 = new StaffVO();
			staffVO10.setStaff_account("ja7865488");
			staffVO10.setStaff_password("p75464");
			staffVO10.setStaff_name("吳長茵");
			staffVO10.setGender(1);
			staffVO10.setPhone("0989691211");
			staffVO10.setStaff_status(3);				
			dao.insert(staffVO10);
			
			StaffVO staffVO11 = new StaffVO();
			staffVO11.setStaff_account("dt565345");
			staffVO11.setStaff_password("m1324684");
			staffVO11.setStaff_name("張博諭");
			staffVO11.setGender(0);
			staffVO11.setPhone("0963589847");
			staffVO11.setStaff_status(3);				
			dao.insert(staffVO11);
		
			
			// 更新
//			EmpVO empVO07 = new EmpVO();
//			empVO07.setMember_id("810165");
//			empVO07.setAccount("B156852");
//			empVO07.setPassword("578512");
//			empVO07.setMember_name("許嘉宏");
//			empVO07.setGender(0);
//			empVO07.setBirthday(java.sql.Date.valueOf("1986-06-13"));
//			empVO07.setCellphone("0987563583");
//			empVO07.setEmail(getAuthCode() + "@gmail.com");
//			empVO07.setNickname("宏哥");
//			empVO07.setMember_photo(dao.getPic("data/S__14147589.jpg"));
//			empVO07.setValidation(0);
//			empVO07.setMember_status(1);
//			empVO07.setMember_address("台中市南屯區惠中路三段73號");
//			empVO07.setMember_creditcard("5371039536580853");
//			empVO07.setBalance(1000);
//			empVO07.setChiefapply_status(1);
//			empVO07.setMember_photo(dao.getPic("data/4.jpg"));
//		//empVO07.setLicense(dao.getPic("data/S__14147589.jpg"));
//			dao.update(empVO07);

			
			
			
			// 刪除
			//dao.delete("910000");



			
			List<StaffVO> list = dao.getAll();
			for (StaffVO aEmp : list) {
				System.out.print(aEmp.getStaff_id() + ",");
				System.out.print(aEmp.getStaff_account() + ",");
				System.out.print(aEmp.getStaff_password() + ",");
				System.out.print(aEmp.getStaff_name() + ",");
				System.out.print(aEmp.getGender() + ",");
				System.out.println(aEmp.getPhone() + ",");
				System.out.print(aEmp.getStaff_status() + ",");				
				System.out.println();
			}
	}

}
