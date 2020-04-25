package com.member_follow.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberJDBCDAO;
import com.member.model.MemberVO;
import com.member_follow.model.Member_followDAO_interface;
import com.member_follow.model.Member_followVO;

public class Member_followJDBCDAO implements Member_followDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = "INSERT INTO MEMBER_FOLLOW (MEMBER_ID,FOLLOWED) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT MEMBER_ID,FOLLOWED FROM MEMBER_FOLLOW order by MEMBER_ID";
	private static final String GET_ONE_STMT = "SELECT MEMBER_ID,FOLLOWED FROM MEMBER_FOLLOW where MEMBER_ID = ?";
	private static final String DELETE = "DELETE FROM MEMBER_FOLLOW where MEMBER_ID = ?";
	private static final String UPDATE = "UPDATE MEMBER_FOLLOW set MEMBER_ID=?, FOLLOWED=? where MEMBER_ID = ? and FOLLOWED = ?";

	@Override
	public void insert(Member_followVO member_followVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, member_followVO.getMember_id());
			pstmt.setString(2, member_followVO.getMember_id_0());


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
	public void update(Member_followVO member_followVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, member_followVO.getMember_id());
			pstmt.setString(2, member_followVO.getMember_id_0());


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
	
	
	public Member_followVO findByPrimaryKey_1(String member_id1) {

		Member_followVO member_followVO = null;
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
				member_followVO = new Member_followVO();
				member_followVO.setMember_id(rs.getString("MEMBER_ID"));
				member_followVO.setMember_id_0(rs.getString("FOLLOWED"));
			    
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
		return member_followVO;
	}


	@Override
	public Member_followVO findByPrimaryKey(String member_id1) {

		Member_followVO member_followVO = null;
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
				member_followVO = new Member_followVO();
				member_followVO.setMember_id(rs.getString("MEMBER_ID"));
				member_followVO.setMember_id_0(rs.getString("FOLLOWED"));

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
		return member_followVO;
	}

	@Override
	public List<Member_followVO> getall() {
		List<Member_followVO> list = new ArrayList<Member_followVO>();
		Member_followVO member_followVO = null;

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
				member_followVO = new Member_followVO();
				member_followVO.setMember_id(rs.getString("MEMBER_ID"));
				member_followVO.setMember_id_0(rs.getString("FOLLOWED"));
				list.add(member_followVO);
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
		

	Member_followJDBCDAO dao = new Member_followJDBCDAO();


		
		Member_followVO member_followVO = new Member_followVO();
			member_followVO.setMember_id("810008");
			member_followVO.setMember_id_0("810001");			
			dao.insert(member_followVO);
			
			Member_followVO member_followVO1 = new Member_followVO();
			member_followVO1.setMember_id("810009");
			member_followVO1.setMember_id_0("810001");			
			dao.insert(member_followVO1);
			
			Member_followVO member_followVO2 = new Member_followVO();
			member_followVO2.setMember_id("810008");
			member_followVO2.setMember_id_0("810002");			
			dao.insert(member_followVO2);
		
			Member_followVO member_followVO3 = new Member_followVO();
			member_followVO3.setMember_id("810009");
			member_followVO3.setMember_id_0("810003");			
			dao.insert(member_followVO3);
		
		
		//更新
//		EmpVO empVO07 = new EmpVO();
//		empVO07.setMember_id("810127");
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
//		System.out.print(empVO3.getMember_id() + ",");
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
		List<Member_followVO> list = dao.getall();
		for (Member_followVO aEmp : list) {
			System.out.print(aEmp.getMember_id() + ",");
			System.out.print(aEmp.getMember_id_0() + ",");
			System.out.println();
		}	


}}
