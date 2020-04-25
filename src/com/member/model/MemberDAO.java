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
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO MEMBER (MEMBER_ID,ACCOUNT,PASSWORD,EMAIL) VALUES (SQ_MEMBER_ID.NEXTVAL, ?, ?, ?)";
	//private static final String INSERT_STMT = "INSERT INTO MEMBER (MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,EMAIL) VALUES (SQ_MEMBER_ID.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,to_char(BIRTHDAY,'yyyy-mm-dd') BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE,CHIEFAPPLY_STATUS FROM MEMBER order by MEMBER_ID";
	private static final String GET_ONE_STMT = "SELECT MEMBER_ID,ACCOUNT,PASSWORD,MEMBER_NAME,GENDER,to_char(BIRTHDAY,'yyyy-mm-dd') BIRTHDAY,CELLPHONE,EMAIL,NICKNAME,MEMBER_PHOTO,VALIDATION,LICENSE,MEMBER_STATUS,MEMBER_ADDRESS,MEMBER_CREDITCARD,BALANCE,CHIEFAPPLY_STATUS FROM MEMBER where MEMBER_ID = ?";
	private static final String DELETE = "DELETE FROM MEMBER where MEMBER_ID = ?";
	//會員更新基本資料  VALIDATION=?  LICENSE=?, MEMBER_STATUS=?, MEMBER_CREDITCARD=?, BALANCE=?, CHIEFAPPLY_STATUS=?
//	private static final String UPDATE = "UPDATE MEMBER set  GENDER=?, BIRTHDAY=?, CELLPHONE=?, NICKNAME=?, MEMBER_PHOTO=?, MEMBER_ADDRESS=? where MEMBER_ID = ?";
	private static final String UPDATE = "UPDATE MEMBER set MEMBER_ID=?,PASSWORD=?,MEMBER_NAME=?, MEMBER_ADDRESS=? where MEMBER_ID = ?";
	//private static final String UPDATE = "UPDATE MEMBER set GENDER=?, BIRTHDAY=?, CELLPHONE=?, NICKNAME=?, MEMBER_ADDRESS=?, VALIDATION=? , MEMBER_STATUS=?, MEMBER_CREDITCARD=?, BALANCE=?, CHIEFAPPLY_STATUS=? where MEMBER_ID = ?";
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

		
			pstmt.executeUpdate();

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
	public void update(MemberVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setInt(1, empVO.getGender());
//			pstmt.setDate(2, empVO.getBirthday());
//			pstmt.setString(3, empVO.getCellphone());
//			pstmt.setString(4, empVO.getNickname());	
//			pstmt.setBytes(5, empVO.getMember_photo());
//			pstmt.setString(6, empVO.getMember_address());		
			pstmt.setString(4, empVO.getMember_address());	
			pstmt.setString(3, empVO.getMember_name());	
			pstmt.setString(2, empVO.getPassword());	
			pstmt.setString(1, empVO.getMember_id());	

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
	public void delete(String empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, empno);

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
	public MemberVO findByPrimaryKey(String MEMBER_ID) {

		MemberVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, MEMBER_ID);

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

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
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

	@Override
	public void update_To_Chef(MemberVO empVO) {

		
	}

	@Override
	public void updateStoredValue(MemberVO empVO) {

		
	}

	@Override
	public void update_by_self(MemberVO empVO) {
		
		
	}
}
