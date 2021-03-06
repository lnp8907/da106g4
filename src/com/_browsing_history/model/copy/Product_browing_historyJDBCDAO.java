package com._browsing_history.model.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;


import javax.sql.DataSource;

public class Product_browing_historyJDBCDAO implements Product_browing_historyDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";
	private static DataSource ds = null;

	
	private static final String INSERT_STMT =
			"		INSERT INTO PRODUCT_BROWSING_HISTORY (member_id,product_id) VALUES ( ?, ?)";
		
	private static final String DELETE = 
			"DELETE FROM PRODUCT_BROWSING_HISTORY where member_id = ? and product_id=? ";
	private static final String DELETEAll = 
			"DELETE FROM PRODUCT_BROWSING_HISTORY where member_id = ? ";
	
	private static final String GET_ALL_STMT = "SELECT  MEMBER_ID, product_id FROM PRODUCT_BROWSING_HISTORY where member_id = ?";

	
	
	
	@Override
	public void insert(Product_browing_historyVO VO) {
		//獲取全部陣列
	 System.out.println("DAO紀錄新增開始");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, VO.getMember_id());	
			pstmt.setString(2, VO.getProduct_id());
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
	public void delete(Product_browing_historyVO VO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, VO.getMember_id());	
			pstmt.setString(2, VO.getProduct_id());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("並未刪除 " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public Set<Product_browing_historyVO> getmemberList(String member_id) {
		Set<Product_browing_historyVO> list = new LinkedHashSet<Product_browing_historyVO>();
		Product_browing_historyVO VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1,member_id);	

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// mycourseVO 也稱為 Domain objects
				VO = new Product_browing_historyVO();
				VO.setMember_id(rs.getString("MEMBER_ID"));
				VO.setProduct_id(rs.getString("product_id"));

				list.add(VO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public void deleteAll(String member_id ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(DELETEAll);
			pstmt.setString(1, member_id);	
System.out.println("刪除全部");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("並未刪除 " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
public static void main(String[] args) {
	Product_browing_historyJDBCDAO dao=new Product_browing_historyJDBCDAO();
Product_browing_historyVO VO=new Product_browing_historyVO();
Product_browing_historyService pvhSvc=new Product_browing_historyService();
pvhSvc.insert("810005", "320012");
//VO.setMember_id("810005");
//VO.setProduct_id("320012");
//	dao.insert(VO);
}
}
