package com.power.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.power.model.PowerDAO_interface;
import com.power.model.PowerJDBCDAO;
import com.power.model.PowerVO;

public class PowerJDBCDAO implements PowerDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = "INSERT INTO POWER (POWER_NO,POWER_NAME) VALUES (SQ_POWER_NO.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = "SELECT POWER_NO,POWER_NAME FROM POWER order by POWER_NO";
	private static final String GET_ONE_STMT = "SELECT POWER_NO,POWER_NAME FROM POWER order by POWER_NO = ?";
	private static final String DELETE = "DELETE FROM POWER where POWER_NO = ?";
	private static final String UPDATE = "UPDATE POWER set POWER_NO=?, POWER_NAME=? where POWER_NO = ?";

	@Override
	public void insert(PowerVO powerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, powerVO.getPower_name());
			


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
	public void update(PowerVO powerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, powerVO.getPower_no());
			pstmt.setString(2, powerVO.getPower_name());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured123. " + se.getMessage());
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
	public void delete(String empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, empno);

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
	public PowerVO findByPrimaryKey(String member_id1) {

		PowerVO powerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, member_id1);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				powerVO = new PowerVO();
				powerVO.setPower_no(rs.getString("MEMBER_ID"));
				powerVO.setPower_name(rs.getString("MEMBER_ID_0"));

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
		return powerVO;
	}

	@Override
	public List<PowerVO> getall() {
		List<PowerVO> list = new ArrayList<PowerVO>();
		PowerVO powerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				powerVO = new PowerVO();
				powerVO.setPower_no(rs.getString("POWER_NO"));
				powerVO.setPower_name(rs.getString("POWER_NAME"));
				list.add(powerVO);
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
		

	PowerJDBCDAO dao = new PowerJDBCDAO();


		
		PowerVO powerVO = new PowerVO();
		
			powerVO.setPower_name("會員管理");		
			dao.insert(powerVO);
			
//		PowerVO powerVO1 = new PowerVO();	
//			powerVO1.setPower_name("直播管理");		
//			dao.insert(powerVO1);
			
		PowerVO powerVO2 = new PowerVO();
			powerVO2.setPower_name("課程管理");		
			dao.insert(powerVO2);
		
		PowerVO powerVO3 = new PowerVO();	
			powerVO3.setPower_name("開課審核");		
			dao.insert(powerVO3);
		
			PowerVO powerVO4 = new PowerVO();	
			powerVO4.setPower_name("優惠卷管理");		
			dao.insert(powerVO4);
			PowerVO powerVO5 = new PowerVO();	
			powerVO5.setPower_name("廚師管理");		
			dao.insert(powerVO5);			
			
			PowerVO powerVO7 = new PowerVO();	
			powerVO7.setPower_name("直播統計");		
			dao.insert(powerVO7);
			
			PowerVO powerVO8 = new PowerVO();	
			powerVO8.setPower_name("直播影片管理");		
			dao.insert(powerVO8);
					
			
			PowerVO powerVO9 = new PowerVO();	
			powerVO9.setPower_name("直播預告管理");		
			dao.insert(powerVO9);
			
			PowerVO powerVO10 = new PowerVO();	
			powerVO10.setPower_name("商品管理");		
			dao.insert(powerVO10);
			
			PowerVO powerVO11 = new PowerVO();	
			powerVO11.setPower_name("訂單管理");		
			dao.insert(powerVO11);
			
			PowerVO powerVO14 = new PowerVO();	
			powerVO14.setPower_name("即時配送管理");		
			dao.insert(powerVO14);
			
			PowerVO powerVO12 = new PowerVO();	
			powerVO12.setPower_name("權限設定");		
			dao.insert(powerVO12);

			PowerVO powerVO13 = new PowerVO();	
			powerVO13.setPower_name("管理者帳號管理");		
			dao.insert(powerVO13);
		
		
		
		//更新
//		EmpVO empVO07 = new EmpVO();
//		empVO07.setPower_no("810127");
//		empVO07.setAccount("B156852");
//		empVO07.setPassword("578512");
//		empVO07.setMember_name("許嘉宏");
//		empVO07.setGender(0);
//		empVO07.setBirthday(java.sql.Date.valueOf("1986-06-13"));				
//		empVO07.setCellphone("1912345678");
//		empVO07.setEmail(getAuthCode() + "@gmail.com");
//		empVO07.setNickname("宏哥");	
//		empVO07.setMember_photo(dao.getPic("data/"+x+".jpg"));
//		empVO07.setValidation(0);
//		empVO07.setMember_status(1);
//		empVO07.setMember_address("台中市南屯區惠中路三段73號");
//		empVO07.setMember_creditcard("5371039536580853");
//		empVO07.setBalance(1000);
//		empVO07.setChiefapply_status(1);
//		empVO07.setMember_photo(dao.getPic("data/4.jpg"));
//		dao.update(empVO07);		
	
		

		

		//刪除
	//	dao.delete("81005");

		//
//		EmpVO empVO3 = dao.findByPrimaryKey("810000");
//		System.out.print(empVO3.getPower_no() + ",");
//		System.out.print(empVO3.getAccount() + ",");
//		System.out.print(empVO3.getPassword() + ",");
//		System.out.print(empVO3.getMember_name() + ",");
//		System.out.print(empVO3.getGender() + ",");
//		System.out.print(empVO3.getBirthday() + ",");
//		System.out.println(empVO3.getCellphone()+ ",");	
//		System.out.print(empVO3.getEmail() + ",");
//		System.out.print(empVO3.getNickname() + ",");
//		System.out.print(empVO3.getMEMBER_PHOTO() + ",");
//		System.out.print(empVO3.getValidation() + ",");
//		System.out.print(empVO3.geyLicense() + ",");
//		System.out.print(empVO3.getMember_status() + ",");
//		System.out.println(empVO3.getMember_address() + ",");	
//		System.out.print(empVO3.getMember_creditcard() + ",");
//		System.out.print(empVO3.getBalance() + ",");
//		System.out.println(empVO3.getChiefapply_status());
//		System.out.println("---------------------");

		// �d��
		List<PowerVO> list = dao.getall();
		for (PowerVO aEmp : list) {
			System.out.print(aEmp.getPower_no() + ",");
			System.out.print(aEmp.getPower_name() + ",");
			System.out.println();
		}	


}}



