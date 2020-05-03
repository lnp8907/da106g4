package com.power.model;

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

import com.power.model.PowerDAO_interface;
import com.power.model.PowerVO;

public class PowerDAO implements PowerDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String INSERT_STMT = "INSERT INTO POWER (POWER_NO,POWER_NAME) VALUES (SQ_POWER.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = "SELECT POWER_NO,POWER_NAME FROM POWER order by POWER_NO";
	private static final String GET_ONE_STMT = "SELECT POWER_NO,POWER_NAME FROM POWER where POWER_NO = ?";
	private static final String DELETE = "DELETE FROM POWER where POWER_NO = ?";
	private static final String UPDATE = "UPDATE POWER set POWER_NO=?, POWER_NAME=? where POWER_NO = ?";

@Override
public void insert(PowerVO powerVO) {

	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);

		

		pstmt.setString(1, powerVO.getPower_no());
		pstmt.setString(2, powerVO.getPower_name());
	
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
public void update(PowerVO powerVO) {

	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE);

		pstmt.setString(1, powerVO.getPower_no());
		pstmt.setString(2, powerVO.getPower_name());
		
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
public PowerVO findByPrimaryKey(String STAFF_ID) {

	PowerVO powerVO = null;
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
			powerVO = new PowerVO();
			powerVO.setPower_no(rs.getString("STAFF_ID"));
			powerVO.setPower_name(rs.getString("POWER_NO"));
			

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

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVO �]�٬� Domain objects
			powerVO = new PowerVO();
			powerVO.setPower_no(rs.getString("POWER_NO"));
			powerVO.setPower_name(rs.getString("POWER_NAME"));
			list.add(powerVO); // Store the row in the list
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
