package com.member.model;

import java.util.*;

import com.course.model.CourseVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	
	
	private static final String GETLIVESTREAM="SELECT MEMBER_ID FROM MEMBER WHERE livestream_status=1";
	
	
	
	private static final String INSERT_STMT = "INSERT INTO MEMBER (MEMBER_ID,ACCOUNT,PASSWORD,EMAIL) VALUES (SQ_MEMBER_ID.NEXTVAL, ?, ?, ?)";

	//private static final String INSERT_STMT = "INSERT INTO MEMBER (MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE) VALUES (SQ_MEMBER_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,to_char(BIRTHDAY,'yyyy-mm-dd') BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE,CHIEFAPPLY_STATUS FROM MEMBER order by member_status,member_id";

	
	//////////////////////////////////////////
	private static final String GET_ALL_STMT_NEW = "SELECT MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,to_char(BIRTHDAY,'yyyy-mm-dd') BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE,CHIEFAPPLY_STATUS,livestream_status FROM MEMBER order by member_status,member_id";
///////////////////////////////////////////////////
	
	
	
	
	
	private static final String GET_ONE_STMT = "SELECT MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,to_char(BIRTHDAY,'yyyy-mm-dd') BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE,CHIEFAPPLY_STATUS FROM MEMBER where MEMBER_ID = ?";
	
	
	
	
	////////////////////////////////////////////////
	private static final String GET_ONE_STMT2 = "SELECT MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,to_char(BIRTHDAY,'yyyy-mm-dd') BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE,CHIEFAPPLY_STATUS FROM MEMBER where ACCOUNT = ?";
	//////////////////////////////////////////////////
	
	
	private static final String GET_ONE_STMTNEW = "SELECT MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,to_char(BIRTHDAY,'yyyy-mm-dd') BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE,CHIEFAPPLY_STATUS,livestream_status FROM MEMBER where MEMBER_ID = ?";
	
	
	//////////////////////////////////////////////////////
	private static final String GET_ONE_STMT2NEW = "SELECT MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,to_char(BIRTHDAY,'yyyy-mm-dd') BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE,CHIEFAPPLY_STATUS,livestream_status FROM MEMBER where ACCOUNT = ?";
//////////////////////////////////////////////////////////
	
	
	private static final String DELETE = "DELETE FROM MEMBER where MEMBER_ID = ?";
	//private static final String UPDATE = "UPDATE MEMBER set ACCOUNT=?, PASSWORD=?, MEMBER_NAME=?, GENDER=?, BIRTHDAY=?, CELLPHONE=?, EMAIL=?, NICKNAME=?, MEMBER_PHOTO=?, VALIDATION=?, LICENSE=?, MEMBER_STATUS=?, MEMBER_ADDRESS=?, MEMBER_CREDITCARD=?, BALANCE=?, CHIEFAPPLY_STATUS=? where MEMBER_ID = ?";
//	private static final String UPDATE = "UPDATE MEMBER set CHIEFAPPLY_STATUS=? where MEMBER_ID = ?";
	//private static final String UPDATE = "UPDATE MEMBER set MEMBER_ID=?,PASSWORD=?,MEMBER_NAME=?, MEMBER_ADDRESS=? where MEMBER_ID = ?";
	private static final String UPDATE = "UPDATE MEMBER set MEMBER_ID = ?, ACCOUNT=?, PASSWORD=?, MEMBER_NAME=?, GENDER=?, BIRTHDAY=?, CELLPHONE=?, EMAIL=?, NICKNAME=?, VALIDATION=?, MEMBER_STATUS=?, MEMBER_ADDRESS=?, MEMBER_CREDITCARD=?, BALANCE=?, CHIEFAPPLY_STATUS=?, member_photo=?, license=? where MEMBER_ID = ?";
	//
	private static final String UPDATENEW = "UPDATE MEMBER set MEMBER_ID = ?, ACCOUNT=?, PASSWORD=?, MEMBER_NAME=?, GENDER=?, BIRTHDAY=?, CELLPHONE=?, EMAIL=?, NICKNAME=?, VALIDATION=?, MEMBER_STATUS=?, MEMBER_ADDRESS=?, MEMBER_CREDITCARD=?, BALANCE=?, CHIEFAPPLY_STATUS=?, member_photo=?, license=?,livestream_status=? where MEMBER_ID = ?";
//
	private static final String UPDATE_TO_CHEF = "UPDATE MEMBER set MEMBER_ID = ?, ACCOUNT=?, MEMBER_NAME=?, license=?, chiefapply_status=? where MEMBER_ID = ?";
	private static final String UPDATE_STOREDVALUE = "UPDATE MEMBER set MEMBER_ID=?, BALANCE=? where MEMBER_ID = ?";
	private static final String UPDATE_BY_SELF = "UPDATE MEMBER set MEMBER_ID = ?, MEMBER_NAME=?, ACCOUNT=?, PASSWORD=?, EMAIL=?, BIRTHDAY=?, CELLPHONE=?, GENDER=?, MEMBER_ADDRESS=?, member_photo=? where MEMBER_ID = ?";

	/////////////
	private static final String UPDATE_LIVESTREAM_STATUS = "UPDATE MEMBER set livestream_status=? where MEMBER_ID = ?";
//////////////////
	
	private static final String UPDATE_SUCCESS = "UPDATE MEMBER set ACCOUNT = ?, VALIDATION = ? where ACCOUNT = ?";
	private static final String DUPLICATE_ACCOUNT = "SELECT ACCOUNT? FROM MEMBER where ACCOUNT = ?";
	
	private static final String UPDATECARDNUMBER = "UPDATE MEMBER set MEMBER_ID = ?, MEMBER_CREDITCARD = ? where MEMBER_ID = ?";
	private static final String UPDATEBACK_END = "UPDATE MEMBER set MEMBER_ID = ?, ACCOUNT = ?, MEMBER_NAME = ?, EMAIL = ?, VALIDATION = ? where MEMBER_ID = ?";
	private static final String CHIEFAPPLY_STATUS = "SELECT MEMBER_ID, ACCOUNT, MEMBER_NAME, EMAIL, MEMBER_STATUS, CHIEFAPPLY_STATUS FROM MEMBER WHERE (CHIEFAPPLY_STATUS = 1) order by chiefapply_status,member_id";
	private static final String VALIDATION = "SELECT MEMBER_ID, ACCOUNT, EMAIL, MEMBER_STATUS, VALIDATION FROM MEMBER WHERE (VALIDATION = 0) order by chiefapply_status,member_id";
	private static final String UPDATECHIEFAPPLY_STATUS = "UPDATE MEMBER set MEMBER_ID = ?, CHIEFAPPLY_STATUS=?, MEMBER_STATUS=? where MEMBER_ID = ?";
	private static final String UPDATEVALIDATION = "UPDATE MEMBER set MEMBER_ID = ?, VALIDATION=? where MEMBER_ID = ?";
	private static final String CHANGEONLINE = "UPDATE MEMBER set LIVE_STATUS = ?  where MEMBER_ID = ?";
	private static final String CHANGEOFFLINE = "UPDATE MEMBER set LIVE_STATUS = ?  where MEMBER_ID = ?";
	
	
	
	@Override
	  public void changeOnline(String member_id,Integer num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHANGEONLINE);
	
			pstmt.setInt(1, num);	
			pstmt.setString(2, member_id);	
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
      public void changeOffline(String member_id,Integer num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHANGEOFFLINE);
	
			pstmt.setInt(1, num);	
			pstmt.setString(2, member_id);	
			pstmt.executeUpdate();
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
	
	
	//	@Override
//	public List<MemberVO> Duplicate_Account(String account) {
//		List<MemberVO> list = new ArrayList<MemberVO>();
//		MemberVO empVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DUPLICATE_ACCOUNT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVO �]�٬� Domain objects
//				empVO = new MemberVO();
//				empVO.setAccount(rs.getString("ACCOUNT"));
//				empVO.setAccount(rs.getString("ACCOUNT"));
//			
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//		}
//		return list;
//	}
	@Override
	public void UpdateValidation(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEVALIDATION);
	
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setInt(2, empVO.getValidation());
			pstmt.setString(3, empVO.getMember_id());	
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
	public void UpdateChiefapplyStatus(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATECHIEFAPPLY_STATUS);

			pstmt.setInt(2, empVO.getChiefapply_status());	
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setInt(3, empVO.getMember_status());
			
			pstmt.setString(4, empVO.getMember_id());	
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
	
	
	
	
	public List<MemberVO> getliving() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETLIVESTREAM);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setLivestream_status(rs.getInt("Livestream_status"));
			
				list.add(memberVO);
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
	public List<MemberVO> getChiefapplyStatus() {

	

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHIEFAPPLY_STATUS);

			rs = pstmt.executeQuery();
		


			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getString("MEMBER_ID"));
				memberVO.setAccount(rs.getString("ACCOUNT"));
				memberVO.setMember_name(rs.getString("MEMBER_NAME"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setMember_status(rs.getInt("MEMBER_STATUS"));
				memberVO.setChiefapply_status(rs.getInt("CHIEFAPPLY_STATUS"));
			
				list.add(memberVO);
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
	public List<MemberVO> getValidation() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(VALIDATION);

			rs = pstmt.executeQuery();
		


			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getString("MEMBER_ID"));
				memberVO.setAccount(rs.getString("ACCOUNT"));
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setMember_status(rs.getInt("MEMBER_STATUS"));
				memberVO.setValidation(rs.getInt("VALIDATION"));
			
			
				list.add(memberVO);
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
	public void updateback_end(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEBACK_END);

			pstmt.setString(2, empVO.getAccount());
			pstmt.setString(3, empVO.getMember_name());
			pstmt.setString(4, empVO.getEmail());
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setString(6, empVO.getMember_id());	
			pstmt.setInt(5, empVO.getValidation());
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
	public void updateCardNumber(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATECARDNUMBER);

			pstmt.setString(2, empVO.getMember_creditcard());
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setString(3, empVO.getMember_id());	
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
	public void update_Success(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_SUCCESS);

			pstmt.setInt(2, empVO.getValidation());
			pstmt.setString(1, empVO.getAccount());	
			pstmt.setString(3, empVO.getAccount());	
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
	public void updateStoredValue(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STOREDVALUE);

			pstmt.setInt(2, empVO.getBalance());
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setString(3, empVO.getMember_id());	
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
	public void insert(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, empVO.getAccount());
			pstmt.setString(2, empVO.getPassword());
			
			pstmt.setString(3, empVO.getEmail());

//			pstmt.setString(1, empVO.getAccount());
//			pstmt.setString(2, empVO.getPassword());
//			pstmt.setString(3, empVO.getMember_name());
//			pstmt.setInt(4, empVO.getGender());
//			pstmt.setDate(5, empVO.getBirthday());
//			pstmt.setString(6, empVO.getCellphone());
//			pstmt.setString(7, empVO.getEmail());
//			pstmt.setString(8, empVO.getNickname());
//			pstmt.setBytes(9, empVO.getMember_photo());
//			pstmt.setInt(10, empVO.getValidation());
//			pstmt.setBytes(11, empVO.getLicense());
//			pstmt.setInt(12, empVO.getMember_status());
//			pstmt.setString(13, empVO.getMember_address());
//			pstmt.setString(14, empVO.getMember_creditcard());
//			pstmt.setInt(15, empVO.getBalance());
			//pstmt.setInt(16, empVO.getChiefapply_status());

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
	
	//////////////////////////////////////////////////////////
	@Override
	public void update_by_self(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_BY_SELF);

//			pstmt.setString(2, empVO.getAccount());
//			pstmt.setString(3, empVO.getPassword());
//			pstmt.setString(1, empVO.getMember_name());
//			pstmt.setInt(7, empVO.getGender());
//			pstmt.setDate(4, empVO.getBirthday());
//			pstmt.setString(6, empVO.getCellphone());
//			pstmt.setString(5, empVO.getEmail());
//	        pstmt.setBytes(9, empVO.getMember_photo());
//			pstmt.setString(8, empVO.getMember_address());
//			pstmt.setString(10, empVO.getMember_id());
			pstmt.setString(3, empVO.getAccount());
			pstmt.setString(4, empVO.getPassword());
			pstmt.setString(2, empVO.getMember_name());
			pstmt.setInt(8, empVO.getGender());
			pstmt.setDate(6, empVO.getBirthday());
			pstmt.setString(7, empVO.getCellphone());
			pstmt.setString(5, empVO.getEmail());
	        pstmt.setBytes(10, empVO.getMember_photo());
			pstmt.setString(9, empVO.getMember_address());
			pstmt.setString(1, empVO.getMember_id());
			pstmt.setString(11, empVO.getMember_id());
			
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

	
	
	
	////////////////////////////////////////////////////////////
	
	
	
	
	
	
	

	@Override
	public void update(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(2, empVO.getAccount());
			pstmt.setString(3, empVO.getPassword());
			pstmt.setString(4, empVO.getMember_name());
			pstmt.setInt(5, empVO.getGender());
			pstmt.setDate(6, empVO.getBirthday());
			pstmt.setString(7, empVO.getCellphone());
			pstmt.setString(8, empVO.getEmail());
		pstmt.setString(9, empVO.getNickname());
	pstmt.setBytes(16, empVO.getMember_photo());
			pstmt.setInt(10, empVO.getValidation());
	pstmt.setBytes(17, empVO.getLicense());
			pstmt.setInt(11, empVO.getMember_status());
			pstmt.setString(12, empVO.getMember_address());
			pstmt.setString(13, empVO.getMember_creditcard());
			pstmt.setInt(14, empVO.getBalance());
			pstmt.setInt(15, empVO.getChiefapply_status());

			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setString(18, empVO.getMember_id());	
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
	public void update_To_Chef(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_TO_CHEF);

			pstmt.setString(2, empVO.getAccount());
			
			pstmt.setString(3, empVO.getMember_name());
			
	        pstmt.setBytes(4, empVO.getLicense());
	        pstmt.setInt(5, empVO.getChiefapply_status());
			
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setString(6, empVO.getMember_id());	
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
	public MemberVO findByPrimaryKey(String empno) {

		MemberVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new MemberVO();
				empVO.setMember_id(rs.getString("MEMBER_ID"));
				empVO.setAccount(rs.getString("ACCOUNT"));
				empVO.setPassword(rs.getString("PASSWORD"));
				empVO.setMember_name(rs.getString("MEMBER_NAME"));
				empVO.setGender(rs.getInt("GENDER"));
				empVO.setBirthday(rs.getDate("BIRTHDAY"));
				empVO.setCellphone(rs.getString("CELLPHONE"));
				empVO.setEmail(rs.getString("EMAIL"));
				empVO.setNickname(rs.getString("NICKNAME"));
				empVO.setMember_photo(rs.getBytes("MEMBER_PHOTO"));
				empVO.setValidation(rs.getInt("VALIDATION"));
				empVO.setLicense(rs.getBytes("LICENSE"));
				empVO.setMember_status(rs.getInt("MEMBER_STATUS"));
				empVO.setMember_address(rs.getString("MEMBER_ADDRESS"));
				empVO.setMember_creditcard(rs.getString("MEMBER_CREDITCARD"));
				empVO.setBalance(rs.getInt("BALANCE"));
				empVO.setChiefapply_status(rs.getInt("CHIEFAPPLY_STATUS"));
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
		return empVO;
	}

	
	
	
	
	
	public MemberVO findPK(String account) {

		MemberVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT2);

			pstmt.setString(1,account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new MemberVO();
				empVO.setMember_id(rs.getString("MEMBER_ID"));
				empVO.setAccount(rs.getString("ACCOUNT"));
				empVO.setPassword(rs.getString("PASSWORD"));
				empVO.setMember_name(rs.getString("MEMBER_NAME"));
				empVO.setGender(rs.getInt("GENDER"));
				empVO.setBirthday(rs.getDate("BIRTHDAY"));
				empVO.setCellphone(rs.getString("CELLPHONE"));
				empVO.setEmail(rs.getString("EMAIL"));
				empVO.setNickname(rs.getString("NICKNAME"));
				empVO.setMember_photo(rs.getBytes("MEMBER_PHOTO"));
				empVO.setValidation(rs.getInt("VALIDATION"));
				empVO.setLicense(rs.getBytes("LICENSE"));
				empVO.setMember_status(rs.getInt("MEMBER_STATUS"));
				empVO.setMember_address(rs.getString("MEMBER_ADDRESS"));
				empVO.setMember_creditcard(rs.getString("MEMBER_CREDITCARD"));
				empVO.setBalance(rs.getInt("BALANCE"));
				empVO.setChiefapply_status(rs.getInt("CHIEFAPPLY_STATUS"));
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
		return empVO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	public MemberVO findPK(String member_id) {
//
//		MemberVO memberVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1,member_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				memberVO =new MemberVO();				
//				memberVO.setAccount(rs.getString("ACCOUNT"));
//			}
//
//			// Handle any driver errors
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return memberVO;
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_NEW);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				empVO = new MemberVO();
				empVO.setMember_id(rs.getString("MEMBER_ID"));
				empVO.setAccount((rs.getString("ACCOUNT")==null)?"尚無資料":rs.getString("ACCOUNT"));
				empVO.setPassword((rs.getString("PASSWORD")==null)?"尚無資料":rs.getString("PASSWORD"));
				empVO.setMember_name((rs.getString("MEMBER_NAME")==null)?"尚無資料":rs.getString("MEMBER_NAME"));
				empVO.setGender(rs.getInt("GENDER"));
				empVO.setBirthday(rs.getDate("BIRTHDAY"));
				empVO.setCellphone((rs.getString("CELLPHONE")==null)?"尚無資料":rs.getString("CELLPHONE"));
				empVO.setEmail((rs.getString("EMAIL")==null)?"尚無資料":rs.getString("EMAIL"));
				empVO.setNickname((rs.getString("NICKNAME")==null)?"尚無資料":rs.getString("NICKNAME"));
				empVO.setMember_photo(rs.getBytes("MEMBER_PHOTO"));
				empVO.setValidation(rs.getInt("VALIDATION"));
				empVO.setLicense(rs.getBytes("LICENSE"));
				empVO.setMember_status(rs.getInt("MEMBER_STATUS"));
				empVO.setMember_address((rs.getString("MEMBER_ADDRESS")==null)?"尚無資料":rs.getString("MEMBER_ADDRESS"));
				empVO.setMember_creditcard((rs.getString("MEMBER_CREDITCARD")==null)?"尚無資料":rs.getString("MEMBER_CREDITCARD"));
				empVO.setBalance(rs.getInt("BALANCE"));
				empVO.setChiefapply_status(rs.getInt("CHIEFAPPLY_STATUS"));
				empVO.setLivestream_status(rs.getInt("LIVESTREAM_STATUS"));
				list.add(empVO); // Store the row in the list
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

	private static String getAuthCode() {
		String AuthCode = "";
		char c[] = new char[62];
		char s = 48;
		for (int i_01 = 0; i_01 < 62; i_01++) {
			c[i_01] = s;
			if (s == 57)
				s = 64;
			else if (s == 90)
				s = 96;
			s++;
		}
		for (int i_02 = 0; i_02 < 10; i_02++) {
			int k = (int) (Math.random() * 62);
			AuthCode += String.valueOf(c[k]);
		}
		return AuthCode;
	}

	public static void main(String[] args) {

	MemberJDBCDAO dao = new MemberJDBCDAO();
	List<MemberVO> list=dao.getliving();
	for(MemberVO vo:list) {
		
		
		System.out.println(vo);
	}
//		 �s�W
//
//		int x = 1;
//        int y = 1;
//		EmpVO empVO02 = new EmpVO();
//		empVO02.setAccount("J544215415");
//		empVO02.setPassword("2458512");
//		empVO02.setMember_name("侯采辰");
//		empVO02.setGender(0);
//		empVO02.setBirthday(java.sql.Date.valueOf("2003-09-11"));
//		empVO02.setCellphone("0965842594");
//		empVO02.setEmail(getAuthCode() + "@gmail.com");
//		empVO02.setNickname("南楓");
//		empVO02.setMember_photo(dao.getPic("data/S__15032327.jpg"));
//		empVO02.setValidation(1);
//		empVO02.setMember_status(1);
//		empVO02.setMember_address("台中市南屯區惠中路二段158號");
//		empVO02.setMember_creditcard("5539784726579152");
//		empVO02.setBalance(1000000);
//	//	empVO02.setChiefapply_status(1);
//		empVO02.setLicense(dao.getPic("data/L00" + y + ".jpg"));
//		dao.insert(empVO02);
//		y++;
//		EmpVO empVO01 = new EmpVO();
//		empVO01.setAccount("J6152");
//		empVO01.setPassword("2458512");
//		empVO01.setMember_name("戈登·拉姆齊");
//		empVO01.setGender(0);
//		empVO01.setBirthday(java.sql.Date.valueOf("1987-10-01"));
//		empVO01.setCellphone("0932845698");
//		empVO01.setEmail(getAuthCode() + "@gmail.com");
//		empVO01.setNickname("Gordon");
//		empVO01.setMember_photo(dao.getPic("data/M" + x + ".jpg"));
//		empVO01.setValidation(1);
//		empVO01.setMember_status(1);
//		empVO01.setMember_address("台中市西屯區府會園道21號");
//		empVO01.setMember_creditcard("4539468639674598");
//		empVO01.setBalance(50000);
//		//empVO01.setChiefapply_status(1);
//		empVO01.setLicense(dao.getPic("data/L00" + y + ".jpg"));
//		dao.insert(empVO01);
//		x++;y++;
//
//		EmpVO empVO03 = new EmpVO();
//		empVO03.setAccount("P1546484");
//		empVO03.setPassword("2458512");
//		empVO03.setMember_name("黃書暐");
//		empVO03.setGender(0);
//		empVO03.setBirthday(java.sql.Date.valueOf("1988-10-11"));
//		empVO03.setCellphone("0948525631");
//		empVO03.setEmail(getAuthCode() + "@gmail.com");
//		empVO03.setNickname("Amos");
//		empVO03.setMember_photo(dao.getPic("data/M" + x + ".jpg"));
//		empVO03.setValidation(1);
//		empVO03.setMember_status(1);
//		empVO03.setMember_address("台北市松山區八德路四段21號");
//		empVO03.setMember_creditcard("1123456789123456");
//		empVO03.setBalance(7600);
//		//empVO03.setChiefapply_status(1);
//		empVO03.setLicense(dao.getPic("data/L00" + y + ".jpg"));
//		dao.insert(empVO03);
//		x++;y++;
//		
//		EmpVO empVO04 = new EmpVO();
//		empVO04.setAccount("f1567478");
//		empVO04.setPassword("2458512");
//		empVO04.setMember_name("馬英九 ");
//		empVO04.setGender(0);
//		empVO04.setBirthday(java.sql.Date.valueOf("1993-01-23"));
//		empVO04.setCellphone("0915685247");
//		empVO04.setEmail(getAuthCode() + "@gmail.com");
//		empVO04.setNickname("Bert");
//		empVO04.setMember_photo(dao.getPic("data/M" + x + ".jpg"));
//		empVO04.setValidation(1);
//		empVO04.setMember_status(1);
//		empVO04.setMember_address("台中市西屯區市政北三路109號");
//		empVO04.setMember_creditcard("5469420427851574");
//		empVO04.setBalance(86420);
//		//empVO04.setChiefapply_status(1);
//		empVO04.setLicense(dao.getPic("data/L00" + y + ".jpg"));
//		dao.insert(empVO04);
//		x++;y++;
//		
//		EmpVO empVO05 = new EmpVO();
//		empVO05.setAccount("kff2");
//		empVO05.setPassword("2458512");
//		empVO05.setMember_name("謝宸飛 ");
//		empVO05.setGender(0);
//		empVO05.setBirthday(java.sql.Date.valueOf("1994-01-13"));
//		empVO05.setCellphone("0965685246");
//		empVO05.setEmail(getAuthCode() + "@gmail.com");
//		empVO05.setNickname("Conrad");
//		empVO05.setMember_photo(dao.getPic("data/M" + x + ".jpg"));
//		empVO05.setValidation(1);
//		empVO05.setMember_status(1);
//		empVO05.setMember_address("台中市南屯區公益路二段618號");
//		empVO05.setMember_creditcard("5448416503351337");
//		empVO05.setBalance(8950);
//		//empVO05.setChiefapply_status(1);
//		empVO05.setLicense(dao.getPic("data/L00" + y + ".jpg"));
//		dao.insert(empVO05);
//		x++;y++;
//		
//		EmpVO empVO06 = new EmpVO();
//		empVO06.setAccount("B156852");
//		empVO06.setPassword("578512");
//		empVO06.setMember_name("陳俊仲");
//		empVO06.setGender(0);
//		empVO06.setBirthday(java.sql.Date.valueOf("1986-06-13"));
//		empVO06.setCellphone("0989658101");
//		empVO06.setEmail(getAuthCode() + "@gmail.com");
//		empVO06.setNickname("Evan");
//		empVO06.setMember_photo(dao.getPic("data/M" + x + ".jpg"));
//		empVO06.setValidation(1);
//		empVO06.setMember_status(1);
//		empVO06.setMember_address("台中市西屯區河南路四段159號");
//		empVO06.setMember_creditcard("5500692095014484");
//		empVO06.setBalance(7300);
//		//empVO06.setChiefapply_status(1);
//		empVO06.setLicense(dao.getPic("data/L00" + y + ".jpg"));
//		dao.insert(empVO06);
//		
//		// 已驗證會員，申請廚師資格，證照審核中
//		EmpVO empVO3 = new EmpVO();
//		empVO3.setAccount("B156152");
//		empVO3.setPassword("2458512");
//		empVO3.setMember_name("朱甯");
//		empVO3.setGender(1);
//		empVO3.setBirthday(java.sql.Date.valueOf("1990-07-05"));
//		empVO3.setCellphone("0945358790");
//		empVO3.setEmail(getAuthCode() + "@gmail.com");
//		empVO3.setNickname("Adele");
//		empVO3.setMember_photo(dao.getPic("data/M" + x + ".jpg"));
//		empVO3.setValidation(1);
//		empVO3.setMember_status(0);
//		empVO3.setMember_address("桃園市中壢區中央西路二段30號");
//		empVO3.setMember_creditcard("5540613331988531");
//		empVO3.setBalance(11500);
//		//empVO3.setChiefapply_status(0);
//		empVO3.setLicense(dao.getPic("data/L00" + y + ".jpg"));
//		dao.insert(empVO3);
//		
//		
//		x++;y++;
//		// 未驗證會員，驗證中
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setAccount("A12345");
//		empVO1.setPassword("123456");
//		empVO1.setMember_name("凃誼琳");
//		empVO1.setGender(1);
//		empVO1.setBirthday(java.sql.Date.valueOf("1996-01-01"));
//		empVO1.setCellphone("0963105604");
//		empVO1.setEmail(getAuthCode() + "@gmail.com");
//		empVO1.setNickname("琳");
//		empVO1.setMember_photo(dao.getPic("data/M" + x + ".jpg"));
////			empVO1.setMember_photo(new byte[100]);
//		empVO1.setValidation(0);
//		//empVO1.setLicense(new byte[100]);
//		empVO1.setMember_status(0);
//		empVO1.setMember_address("桃園市中壢區中央西路二段30號");
//		empVO1.setMember_creditcard("4556047574795358");
//		empVO1.setBalance(1000);
//		//empVO1.setChiefapply_status();
//		//empVO1.setLicense(dao.getPic("data/00" + y + ".jpg"));
//		dao.insert(empVO1);
//		x++;
//		
//		// 已驗證會員
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setAccount("B123456");
//		empVO2.setPassword("7123456");
//		empVO2.setMember_name("程潔 ");
//		empVO2.setGender(1);
//		empVO2.setBirthday(java.sql.Date.valueOf("1995-02-02"));
//		empVO2.setCellphone("0986585357");
//		empVO2.setEmail(getAuthCode() + "@gmail.com");
//		empVO2.setNickname("Maki");
//		empVO2.setMember_photo(dao.getPic("data/M" + x + ".jpg"));
//		empVO2.setValidation(1);
//		empVO2.setMember_status(0);
//		empVO2.setMember_address("桃園市中壢區龍岡路三段756號");
//		empVO2.setMember_creditcard("4539294319863842");
//		empVO2.setBalance(2000);
//		//empVO2.setChiefapply_status(0);
//		//empVO2.setLicense(dao.getPic("data/00" + y + ".jpg"));
//		dao.insert(empVO2);
//		x++;
//		
	
		
	
	
	
		// 更新 設 CHIEFAPPLY_STATUS = 1
//		for(int u = 1; u < 8; u++) {
//		MemberVO empVO07 = new MemberVO();
//		empVO07.setMember_id("81000"+u);
//		empVO07.setChiefapply_status(1);
//		dao.update(empVO07);
//		}
//		

		
		
		
		// 刪除
		// dao.delete("81005");

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
//		List<EmpVO> list = dao.getAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getMember_id() + ",");
//			System.out.print(aEmp.getAccount() + ",");
//			System.out.print(aEmp.getPassword() + ",");
//			System.out.print(aEmp.getMember_name() + ",");
//			System.out.print(aEmp.getGender() + ",");
//			System.out.print(aEmp.getBirthday() + ",");
//			System.out.println(aEmp.getCellphone() + ",");
//			System.out.print(aEmp.getEmail() + ",");
//			System.out.print(aEmp.getNickname() + ",");
//			System.out.print(aEmp.getMember_photo() + ",");
//			System.out.print(aEmp.getValidation() + ",");
//			System.out.print(aEmp.getLicense() + ",");
//			System.out.print(aEmp.getMember_status() + ",");
//			System.out.println(aEmp.getMember_address() + ",");
//			System.out.print(aEmp.getMember_creditcard() + ",");
//			System.out.print(aEmp.getBalance() + ",");
//			System.out.println(aEmp.getChiefapply_status());
//			System.out.println();
//		}
	}

	public static byte[] getPic(String path) {
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