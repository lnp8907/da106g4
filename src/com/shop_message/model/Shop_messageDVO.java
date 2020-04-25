package com.shop_message.model;

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
import com.shop_order.model.Shop_orderVO;

public class Shop_messageDVO implements Shop_messageDVO_interface {
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
    private static final String INSERT_STMT = "INSERT INTO SHOP_MESSAGE (MESSAGE_NO,MEMBER_ID,ORDER_NO,SHOP_MESSAGE,MAMESSAGE_STATUS,MAMESSAGE_STATUS) VALUES ( SQ_MAMESSAGE_NO.NEXTVAL, ?, ?, ?,CURRENT_TIMESTAMP, ?)";
    private static final String GET_ALL_STMT = "SELECT MESSAGE_NO,MEMBER_ID,ORDER_NO,SHOP_MESSAGE,MAMESSAGE_TIME,MAMESSAGE_STATUS FROM SHOP_MESSAGE order by MEMBER_ID";
    private static final String GET_ONE_STMT = "SELECT MESSAGE_NO,MEMBER_ID,ORDER_NO,SHOP_MESSAGE,MAMESSAGE_TIME,MAMESSAGE_STATUS FROM SHOP_MESSAGE WHERE MESSAGE_NO = ?";
    private static final String DELETE = "DELETE FROM SHOP_MESSAGE WHERE MESSAGE_NO = ?";
    private static final String UPDATE = "UPDATE PRODUCT SET MAMESSAGE_STATUS=?";
	@Override
	public void insert(Shop_messageVO shop_messagevo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, shop_messagevo.getMember_id());
			pstmt.setString(2, shop_messagevo.getOrder_no());
			pstmt.setString(3, shop_messagevo.getShop_message());
			pstmt.setInt(4, shop_messagevo.getMessage_status());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Shop_messageVO shop_messagevo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, shop_messagevo.getMessage_status());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String message_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, message_no);
			pstmt.executeUpdate();
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
	public Shop_messageVO findByPrimaryKey(String message_no) {
		// TODO Auto-generated method stub
		Shop_messageVO shop_messagevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, message_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shop_messagevo = new Shop_messageVO();
				shop_messagevo.setMessage_no(rs.getString("message_no"));
				shop_messagevo.setMember_id(rs.getString("member_id"));
				shop_messagevo.setOrder_no(rs.getString("order_no"));
				shop_messagevo.setShop_message(rs.getString("shop_message"));
				shop_messagevo.setMessage_time(rs.getTimestamp("message_time"));
				shop_messagevo.setMessage_status(rs.getInt("message_statu"));
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
			
	}}
		return shop_messagevo; 
			}
	@Override
	public List<Shop_messageVO> getAll() {
		// TODO Auto-generated method stub
		List<Shop_messageVO> list = new ArrayList();
		Shop_messageVO  shop_messagevo= null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shop_messagevo = new Shop_messageVO();
				shop_messagevo.setMessage_no(rs.getString("message_no"));
				shop_messagevo.setMember_id(rs.getString("member_id"));
				shop_messagevo.setOrder_no(rs.getString("order_no"));
				shop_messagevo.setShop_message(rs.getString("shop_message"));
				shop_messagevo.setMessage_time(rs.getTimestamp("message_time"));
				shop_messagevo.setMessage_status(rs.getInt("message_statu"));
				list.add(shop_messagevo); // Store the row in the list
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
	
}
