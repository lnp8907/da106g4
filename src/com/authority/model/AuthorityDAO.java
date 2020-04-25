package com.authority.model;

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

import com.member.model.MemberVO;
import com.member_follow.model.Member_followDAO_interface;
import com.member_follow.model.Member_followVO;

public class AuthorityDAO implements AuthorityDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String INSERT_STMT = "INSERT INTO AUTHORITY(STAFF_ID,POWER_NO) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT STAFF_ID,POWER_NO FROM AUTHORITY order by STAFF_ID";
	private static final String GET_ONE_STMT = "SELECT STAFF_ID,POWER_NO FROM AUTHORITY where STAFF_ID = ?";
	private static final String DELETE = "DELETE FROM AUTHORITY where STAFF_ID = ?";
	private static final String UPDATE = "UPDATE AUTHORITY set STAFF_ID=?, POWER_NO=? where STAFF_ID = ? and POWER_NO = ?";

@Override
public void insert(AuthorityVO authorityVO) {

	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);

		

		pstmt.setString(1, authorityVO.getStaff_id());
		pstmt.setString(2, authorityVO.getPower_no());
	
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
public void update(AuthorityVO authorityVO) {

	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE);

		pstmt.setString(1, authorityVO.getStaff_id());
		pstmt.setString(2, authorityVO.getPower_no());
		
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
public AuthorityVO findByPrimaryKey(String STAFF_ID) {

	AuthorityVO authorityVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMT);

		pstmt.setString(1, STAFF_ID);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVo �]�٬� Domain objects
			authorityVO = new AuthorityVO();
			authorityVO.setStaff_id(rs.getString("STAFF_ID"));
			authorityVO.setPower_no(rs.getString("POWER_NO"));
			

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
	return authorityVO;
}


@Override
public AuthorityVO findByPrimaryKey_1(String STAFF_ID) {

	AuthorityVO authorityVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMT);

		pstmt.setString(1, STAFF_ID);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVo �]�٬� Domain objects
			authorityVO = new AuthorityVO();
			authorityVO.setStaff_id(rs.getString("STAFF_ID"));
			authorityVO.setPower_no(rs.getString("POWER_NO"));
			

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
	return authorityVO;
}



@Override
public List<AuthorityVO> getall() {
	List<AuthorityVO> list = new ArrayList<AuthorityVO>();
	AuthorityVO authorityVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVO �]�٬� Domain objects
			authorityVO = new AuthorityVO();
			authorityVO.setStaff_id(rs.getString("STAFF_ID"));
			authorityVO.setPower_no(rs.getString("POWER_NO"));
			list.add(authorityVO); // Store the row in the list
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
