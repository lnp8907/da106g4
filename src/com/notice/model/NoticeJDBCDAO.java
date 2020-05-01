package com.notice.model;

import java.util.*;

import com.member_follow.model.Member_followJDBCDAO;
import com.member_follow.model.Member_followVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;





public class NoticeJDBCDAO implements NoticeDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";
    private static final String INSERT_STMT = "INSERT INTO NOTICE (NOTICE_ID,MEMBER_ID,NOTICE_CATEGORY,CONTENT,NOTICE_STATUS) VALUES ('N'||LPAD(to_char(SQ_NOTICE_ID.NEXTVAL),6,'0'), ?, ?, ?, ?)";
    private static final String GET_ALL_STMT = "SELECT NOTICE_ID,MEMBER_ID,NOTICE_CATEGORY,CONTENT,NOTICE_TIME,NOTICE_STATUS FROM NOTICE order by NOTICE_ID";
    private static final String GET_ONE_STMT = "SELECT NOTICE_ID,MEMBER_ID,NOTICE_CATEGORY,CONTENT,NOTICE_TIME,NOTICE_STATUS FROM NOTICE WHERE ORDER_NO = ?";
    private static final String DELETE = "DELETE FROM NOTICE WHERE NOTICE_ID = ? ";
    private static final String UPDATE = "UPDATE NOTICE SET  MEMBER_ID=?, NOTICE_CATEGORY=?, CONTENT=?, NOTICE_STATUS=? WHERE  NOTICE_ID= ? ";
    private static final String CHAGESTATUS = "UPDATE NOTICE SET NOTICE_STATUS=? WHERE NOTICE_ID= ? ";

    
    
 
    public void changestatus(String notice_id,int notice_status) {
    	Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHAGESTATUS);
			pstmt.setInt(1, notice_status);
			pstmt.setString(2, notice_id);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public void changestatus(NoticeVO noticevo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHAGESTATUS);
			pstmt.setInt(1, noticevo.getNotice_status());
			pstmt.setString(2, noticevo.getNotice_id());
		

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void insert(NoticeVO noticevo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, noticevo.getMember_id());			
			pstmt.setInt(2, noticevo.getNotice_category());
			pstmt.setString(3, noticevo.getContent());
			pstmt.setInt(4, noticevo.getNotice_status());
			
		
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(NoticeVO noticevo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, noticevo.getMember_id());
			pstmt.setInt(2, noticevo.getNotice_category());
			pstmt.setString(3, noticevo.getContent());
			pstmt.setInt(4, noticevo.getNotice_status());
			pstmt.setString(5, noticevo.getNotice_id());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(String notice_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, notice_id);
			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException se) {
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
	public NoticeVO findByPrimaryKey(String notice_id) {
		// TODO Auto-generated method stub
		NoticeVO noticevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		
		
		
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, notice_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				noticevo = new NoticeVO();
				noticevo.setNotice_id(rs.getString("NOTICE_ID"));
				noticevo.setMember_id(rs.getString("Member_id"));
				noticevo.setContent(rs.getString("CONTENT"));
				noticevo.setNotice_time(rs.getTimestamp("NOTICE_TIME"));
				noticevo.setNotice_status(rs.getInt("NOTICE_STATUS"));
				noticevo.setNotice_category(rs.getInt("NOTICE_CATEGORY"));
			}
			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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
		return noticevo;
	}

	@Override
	public List<NoticeVO> getAll() {
		// TODO Auto-generated method stub
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeVO noticevo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				noticevo = new NoticeVO();
				noticevo.setNotice_id(rs.getString("NOTICE_ID"));
				noticevo.setMember_id(rs.getString("Member_id"));
				noticevo.setContent(rs.getString("CONTENT"));
				noticevo.setNotice_time(rs.getTimestamp("NOTICE_TIME"));
				noticevo.setNotice_status(rs.getInt("NOTICE_STATUS"));
				noticevo.setNotice_category(rs.getInt("NOTICE_CATEGORY"));
				list.add(noticevo); // Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			 
		NoticeJDBCDAO dao=new NoticeJDBCDAO();
		

			NoticeJDBCDAO dao1=new NoticeJDBCDAO();
			NoticeVO vo=new NoticeVO();
			vo.setMember_id("810001");
			vo.setNotice_category(0);
	    	vo.setContent("https://tw.yahoo.com/");
            vo.setNotice_status(0);
			dao1.insert(vo);
			
			
			
			
			

	
		
		
		

				
			//更新
		

			//刪除



//			EmpVO empVO3 = dao.findByPrimaryKey("810000");
//			System.out.print(empVO3.getMember_id() + ",");
//			System.out.print(empVO3.getAccount() + ",");
//			System.out.print(empVO3.getPassword() + ",");
//			System.out.print(empVO3.getMember_name() + ",");
//			System.out.print(empVO3.getGender() + ",");
//			System.out.print(empVO3.getBirthday() + ",");
//			System.out.println(empVO3.getCellphone()+ ",");	
//			System.out.print(empVO3.getEmail() + ",");
//			System.out.print(empVO3.getNickname() + ",");
//			System.out.print(empVO3.getMEMBER_PHOTO() + ",");
//			System.out.print(empVO3.getValidation() + ",");
//			System.out.print(empVO3.geyLicense() + ",");
//			System.out.print(empVO3.getMember_status() + ",");
//			System.out.println(empVO3.getMember_address() + ",");	
//			System.out.print(empVO3.getMember_creditcard() + ",");
//			System.out.print(empVO3.getBalance() + ",");
//			System.out.println(empVO3.getChiefapply_status());
//			System.out.println("---------------------");

			// �d��
//			List<NoticeVO> list = dao.getAll();
//			for (NoticeVO aEmp : list) {
//				System.out.print(aEmp.getMoneyflow_no() + ",");
//				System.out.print(aEmp.getMember_id() + ",");
//				System.out.print(aEmp.getMoneyflow_status() + ",");
//				System.out.print(aEmp.getMoney() + ",");
//				System.out.print(aEmp.getCharge_time() + ",");
//				System.out.println();
			}	
	}

