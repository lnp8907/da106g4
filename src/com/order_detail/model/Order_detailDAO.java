package com.order_detail.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order_detailDAO implements Order_detailDAO_interface{
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
    private static final String INSERT_STMT = "INSERT INTO ORDER_DETAIL (ORDER_NO,PRODUCT_ID,QUANTITY,PRICE) VALUES ( SQ_ORDER_NO.NEXTVAL, ?, ?, ?)";
    private static final String GET_ALL_STMT = "SELECT ORDER_NO,PRODUCT_ID,QUANTITY,PRICE FROM ORDER_DETAIL order by ORDER_NO";
    private static final String GET_ONE_STMT = "SELECT ORDER_NO,PRODUCT_ID,QUANTITY,PRICE FROM ORDER_DETAIL WHERE ORDER_NO = ?";
    private static final String DELETE = "DELETE FROM ORDER_DETAIL WHERE ORDER_NO = ? AND PRODUCT_ID = ?";
    private static final String UPDATE = "UPDATE ORDER_DETAIL SET  QUANTITY=?, PRICE=? WHERE  ORDER_NO= ?";
    private static final String UPDATEPRICE = "UPDATE ORDER_DETAIL SET   PRICE=? WHERE  ORDER_NO= ? ";
   
    
    
    public void updateprice(Order_detailVO order_detailvo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPRICE);
			pstmt.setInt(1, order_detailvo.getPrice());
			pstmt.setString(2, order_detailvo.getorder_no());
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
    public void update(Order_detailVO order_detailvo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, order_detailvo.getQuantity());
			pstmt.setInt(2, order_detailvo.getPrice());
			pstmt.setString(3, order_detailvo.getorder_no());
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
    public void delete(String order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, order_no);
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
    public List<Order_detailVO> getAll() {
    	List<Order_detailVO> list = new ArrayList();
    	Order_detailVO order_detailvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detailvo = new Order_detailVO();
				order_detailvo.setorder_no(rs.getString("order_no"));
				order_detailvo.setProduct_id(rs.getString("product_id"));
				order_detailvo.setQuantity(rs.getInt("quantity"));
				order_detailvo.setPrice(rs.getInt("price"));
				list.add(order_detailvo); // Store the row in the list
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
    public Order_detailVO findByPrimaryKey(String ORDER_NO,String product_id) {
    	Order_detailVO order_detailvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ORDER_NO);
			pstmt.setString(2,product_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detailvo = new Order_detailVO();
				order_detailvo.setorder_no(rs.getString("order_no"));
				order_detailvo.setProduct_id(rs.getString("product_id"));
				order_detailvo.setQuantity(rs.getInt("quantity"));
				order_detailvo.setPrice(rs.getInt("price"));
			}
			// Handle any driver errors
		} catch (SQLException  se) {
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
		return order_detailvo;    }


	@Override
	public List<Order_detailVO> getdetail(String order_no) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteOne(String order_no, String product_id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Integer getOrderDetailTotal(String order_no) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void insert(Order_detailVO order_detailvo, Connection con) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void addDetail(Order_detailVO order_detailvo) {
		// TODO Auto-generated method stub
		
	}
}