package com.member.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberVO;

public class MemberDAO implements MemberDAO_interface {


	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	private static final String GETLIVESTREAM="SELECT MEMBER_ID FROM MEMBER WHERE livestream_status=1";
	
	
	
	private static final String INSERT_STMT = "INSERT INTO MEMBER (MEMBER_ID,ACCOUNT,PASSWORD,EMAIL) VALUES (SQ_MEMBER_ID.NEXTVAL, ?, ?, ?)";

	//private static final String INSERT_STMT = "INSERT INTO MEMBER (MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE) VALUES (SQ_MEMBER_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,to_char(BIRTHDAY,'yyyy-mm-dd') BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE,CHIEFAPPLY_STATUS FROM MEMBER order by member_status,member_id";

	
	//////////////////////////////////////////
	private static final String GET_ALL_STMT_NEW = "SELECT MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,to_char(BIRTHDAY,'yyyy-mm-dd') BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE,CHIEFAPPLY_STATUS,LIVE_STATUS FROM MEMBER order by member_status,member_id";
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
	private static final String UPDATE_TO_CHEF = "UPDATE MEMBER set MEMBER_ID = ?, license=?, chiefapply_status=? where MEMBER_ID = ?";
	private static final String UPDATE_STOREDVALUE = "UPDATE MEMBER set MEMBER_ID=?, BALANCE=? where MEMBER_ID = ?";
	private static final String UPDATE_BY_SELF = "UPDATE MEMBER set MEMBER_ID = ?, MEMBER_NAME=?,NICKNAME=?, ACCOUNT=?, PASSWORD=?, EMAIL=?, BIRTHDAY=?, CELLPHONE=?, GENDER=?, MEMBER_ADDRESS=?, member_photo=? where MEMBER_ID = ?";

	/////////////
	private static final String UPDATE_LIVESTREAM_STATUS = "UPDATE MEMBER set livestream_status=? where MEMBER_ID = ?";
//////////////////
	
	private static final String UPDATE_SUCCESS = "UPDATE MEMBER set EMAIL = ?, VALIDATION = ? where EMAIL = ?";
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
			con = ds.getConnection();

			pstmt = con.prepareStatement(CHANGEONLINE);
	
			pstmt.setInt(1, num);	
			pstmt.setString(2, member_id);	
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGEOFFLINE);
	
			pstmt.setInt(1, num);	
			pstmt.setString(2, member_id);	
			pstmt.executeUpdate();
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
//			con = ds.getConnection();
//			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEVALIDATION);
	
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setInt(2, empVO.getValidation());
			pstmt.setString(3, empVO.getMember_id());	
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATECHIEFAPPLY_STATUS);

			pstmt.setInt(2, empVO.getChiefapply_status());	
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setInt(3, empVO.getMember_status());
			
			pstmt.setString(4, empVO.getMember_id());	
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			
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
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			
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

			con = ds.getConnection();
			
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

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATEBACK_END);

			pstmt.setString(2, empVO.getAccount());
			pstmt.setString(3, empVO.getMember_name());
			pstmt.setString(4, empVO.getEmail());
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setString(6, empVO.getMember_id());	
			pstmt.setInt(5, empVO.getValidation());
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATECARDNUMBER);

			pstmt.setString(2, empVO.getMember_creditcard());
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setString(3, empVO.getMember_id());	
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE_SUCCESS);

			pstmt.setInt(2, empVO.getValidation());
			pstmt.setString(1, empVO.getEmail());	
			pstmt.setString(3, empVO.getEmail());	
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE_STOREDVALUE);

			pstmt.setInt(2, empVO.getBalance());
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setString(3, empVO.getMember_id());	
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			
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
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			
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
			pstmt.setString(4, empVO.getAccount());
			pstmt.setString(5, empVO.getPassword());
			pstmt.setString(2, empVO.getMember_name());
			pstmt.setString(3, empVO.getNickname());
			pstmt.setInt(9, empVO.getGender());
			pstmt.setDate(7, empVO.getBirthday());
			pstmt.setString(8, empVO.getCellphone());
			pstmt.setString(6, empVO.getEmail());
	        pstmt.setBytes(11, empVO.getMember_photo());
			pstmt.setString(10, empVO.getMember_address());
			pstmt.setString(1, empVO.getMember_id());
			pstmt.setString(12, empVO.getMember_id());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			
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
		}catch (SQLException se) {
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

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE_TO_CHEF);

			
			

			
	        pstmt.setBytes(2, empVO.getLicense());
	        pstmt.setInt(3, empVO.getChiefapply_status());
			
			pstmt.setString(1, empVO.getMember_id());	
			pstmt.setString(4, empVO.getMember_id());	
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, empno);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			
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

			con = ds.getConnection();
			
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

			con = ds.getConnection();
			
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
				empVO.setLivestream_status(rs.getInt("LIVE_STATUS"));
				list.add(empVO); // Store the row in the list
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
