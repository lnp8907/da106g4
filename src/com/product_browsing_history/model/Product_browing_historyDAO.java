package com.product_browsing_history.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mycourse.model.MycourseVO;

public class Product_browing_historyDAO implements Product_browing_historyDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT =
			"		INSERT INTO PRODUCT_BROWSING_HISTORY (member_id,product_id) VALUES ( ?, ?)";
		
	private static final String DELETE = 
			"DELETE FROM PRODUCT_BROWSING_HISTORY where member_id = ? and product_id=? ";
	private static final String DELETEAll = 
			"DELETE FROM PRODUCT_BROWSING_HISTORY where member_id = ? ";
	
	private static final String GET_ALL_STMT = "SELECT  MEMBER_ID, product_id FROM PRODUCT_BROWSING_HISTORY where member_id=?";

	
	
	
	@Override
	public void insert(Product_browing_historyVO VO) {
		System.out.println("DAO準備新增瀏覽紀錄");
		//獲取全部陣列
	 Product_browing_historyDAO_interface dao=new Product_browing_historyDAO();
	 List <Product_browing_historyVO> list= dao.getmemberList(VO.getMember_id());
		if(list.contains(VO)) {
			System.out.println("刪除");
			dao.delete(VO);
		}
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, VO.getMember_id());	
			pstmt.setString(2, VO.getProduct_id());
			pstmt.executeUpdate();

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
	public void delete(Product_browing_historyVO VO) {
		System.out.println("刪除開始");

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, VO.getMember_id());	
			pstmt.setString(2, VO.getProduct_id());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("並未刪除 " + e.getMessage());
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
	public List<Product_browing_historyVO> getmemberList(String member_id) {
		List<Product_browing_historyVO> list = new LinkedList<Product_browing_historyVO>();
		Product_browing_historyVO VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1,member_id);	

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// mycourseVO 也稱為 Domain objects
				VO = new Product_browing_historyVO();
				VO.setMember_id(rs.getString("MEMBER_ID"));
				VO.setProduct_id(rs.getString("product_id"));
				System.out.println("VO有"+VO);
				
				list.add(VO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		System.out.println("獲取LIST:"+list);

		return list;
		
	}

	@Override
	public void deleteAll(String member_id ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {	
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETEAll);
			pstmt.setString(1, member_id);	
System.out.println("刪除全部");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("並未刪除 " + e.getMessage());
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

}
