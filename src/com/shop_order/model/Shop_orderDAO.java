package com.shop_order.model;

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

import com.order_detail.model.Order_detailVO;
import com.product.model.ProductVO;

public class Shop_orderDAO implements Shop_orderDAO_interface{

	//資料庫連結
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT =
			"		INSERT INTO SHOP_ORDER (order_no,member_id,order_status,total,pay_type,dv_address) VALUES ('OR-'||to_CHAR(current_timestamp,'YYYY-MM-DD')||'-'||LPAD(to_char(SQ_ORDER_NO.NEXTVAL),6,'0'),?,? ,?, ?, ?)";
		
	private static final String GET_ALL_STMT = 
		"SELECT order_no,member_id,order_status,order_time,total,pay_type,dv_address FROM SHOP_ORDER order by ORDER_NO";
	private static final String GET_ONE_STMT = 
		"SELECT order_no,member_id,order_status,order_time,total,pay_type,dv_address FROM SHOP_ORDER where ORDER_NO = ?";
	 private static final String DELETEALL = "DELETE FROM ORDER_DETAIL WHERE ORDER_NO = ?";
	private static final String DELETE = 
		"DELETE FROM SHOP_ORDER where order_no = ? ";
	private static final String UPDATE = 
		"UPDATE SHOP_ORDER set order_status=?, total=?,dv_address=? where order_no = ?";
	private static final String CHANGESTATUS = 
			"UPDATE SHOP_ORDER set order_status=?where order_no = ?";
	
	@Override
	public void update(Shop_orderVO shop_ordervo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
	            pstmt.setInt(1, shop_ordervo.getOrder_status());
	            pstmt.setInt(2, shop_ordervo.getTotal());
	            pstmt.setString(3, shop_ordervo.getDv_address());
	            pstmt.setString(4, shop_ordervo.getOrder_no());
			    pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
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
	public void delete(String order_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,order_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("並未刪除 "+ se.getMessage());
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
	public Shop_orderVO findByPrimaryKey(String order_no) {
		// TODO Auto-generated method stub
		Shop_orderVO shop_ordervo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, order_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shop_ordervo = new Shop_orderVO();
				shop_ordervo.setOrder_no(rs.getString("order_no"));
				shop_ordervo.setMember_id(rs.getString("member_id"));
				shop_ordervo.setOrder_status(rs.getInt("order_status"));
				shop_ordervo.setOrder_time(rs.getTimestamp("order_time"));
				shop_ordervo.setTotal(rs.getInt("total"));
				shop_ordervo.setPay_type(rs.getInt("pay_type"));
				shop_ordervo.setDv_address(rs.getString("dv_address"));

			}	
			// Handle any driver errors
		} catch (SQLException se) {
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
		return shop_ordervo;
	}
	@Override
	public List<Shop_orderVO> getAll() {
		// TODO Auto-generated method stub
		List<Shop_orderVO> list = new ArrayList<Shop_orderVO>();
		 Shop_orderVO  shop_ordervo= null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shop_ordervo = new Shop_orderVO();
				shop_ordervo.setOrder_no(rs.getString("order_no"));
				shop_ordervo.setMember_id(rs.getString("member_id"));
				shop_ordervo.setOrder_status(rs.getInt("order_status"));
				shop_ordervo.setOrder_time(rs.getTimestamp("order_time"));
				shop_ordervo.setTotal(rs.getInt("total"));
				shop_ordervo.setPay_type(rs.getInt("pay_type"));
				shop_ordervo.setDv_address(rs.getString("dv_address"));
				list.add(shop_ordervo); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
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
		return list;
	}
	@Override
	public List<Shop_orderVO> getOrderBYMEMBER(String member_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updatetotal(Shop_orderVO shop_ordervo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insert(Shop_orderVO shop_ordervo, List<Order_detailVO> list) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
