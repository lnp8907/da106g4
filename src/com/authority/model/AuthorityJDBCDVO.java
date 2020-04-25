package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member_follow.model.Member_followJDBCDAO;
import com.member_follow.model.Member_followVO;

//import com.foodporn.member_follow.model. AuthorityVO;

public class AuthorityJDBCDVO implements AuthorityDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";
	
	private static final String INSERT_STMT = "INSERT INTO AUTHORITY(STAFF_ID,POWER_NO) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT STAFF_ID,POWER_NO FROM AUTHORITY order by STAFF_ID";
	private static final String GET_ONE_STMT = "SELECT STAFF_ID,POWER_NO FROM AUTHORITY where STAFF_ID = ?";
	private static final String DELETE = "DELETE FROM AUTHORITY where STAFF_ID = ?";
	private static final String UPDATE = "UPDATE AUTHORITY set STAFF_ID=?, POWER_NO=? where STAFF_ID = ? and POWER_NO = ?";

	@Override
	public void insert( AuthorityVO authorityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, authorityVO.getStaff_id());
			pstmt.setString(2, authorityVO.getPower_no());


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
	public void update( AuthorityVO authorityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, authorityVO.getStaff_id());
			pstmt.setString(2, authorityVO.getPower_no());


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
	
	
	public  AuthorityVO findByPrimaryKey_1(String member_id1) {

		 AuthorityVO authorityVO = null;
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
				authorityVO = new  AuthorityVO();
				authorityVO.setStaff_id(rs.getString("MEMBER_ID"));
				authorityVO.setPower_no(rs.getString("MEMBER_ID_0"));
			    
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
		return authorityVO;
	}


	@Override
	public  AuthorityVO findByPrimaryKey(String member_id1) {

		 AuthorityVO authorityVO = null;
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
				authorityVO = new  AuthorityVO();
				authorityVO.setStaff_id(rs.getString("MEMBER_ID"));
				authorityVO.setPower_no(rs.getString("MEMBER_ID_0"));

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
		return authorityVO;
	}

	@Override
	public List< AuthorityVO> getall() {
		List< AuthorityVO> list = new ArrayList< AuthorityVO>();
		 AuthorityVO authorityVO = null;

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
				authorityVO = new  AuthorityVO();
				authorityVO.setStaff_id(rs.getString("MEMBER_ID"));
				authorityVO.setPower_no(rs.getString("MEMBER_ID_0"));
				list.add(authorityVO);
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
		

		AuthorityJDBCDVO dao = new AuthorityJDBCDVO();

          for(int i = 1;i <12; i++) {
			
		AuthorityVO authorityVO = new AuthorityVO();
			authorityVO.setStaff_id("910004");
			String s =String.valueOf(920000+i);
			authorityVO.setPower_no(s);
				dao.insert(authorityVO);
          }

          
          for(int i = 1;i <14;i++) {
  			
      		AuthorityVO authorityVO = new AuthorityVO();
      			authorityVO.setStaff_id("910007");
      			String s =String.valueOf(920000+i);
      			authorityVO.setPower_no(s);
      				dao.insert(authorityVO);
                }

	}



}
