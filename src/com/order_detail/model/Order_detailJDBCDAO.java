package com.order_detail.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;
import com.shop_order.model.Shop_orderJDBCDAO;
import com.shop_order.model.Shop_orderVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order_detailJDBCDAO implements Order_detailDAO_interface{
    //資料庫連結
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";
    private static final String INSERT_STMT = "INSERT INTO ORDER_DETAIL (ORDER_NO,PRODUCT_ID,QUANTITY,PRICE) VALUES ( ?, ?, ?, ?)";
    private static final String GET_ALL_STMT = "SELECT ORDER_NO,PRODUCT_ID,QUANTITY,PRICE FROM ORDER_DETAIL order by ORDER_NO";
   
   private static final String GET_ONE_STMT = "SELECT ORDER_NO,PRODUCT_ID,QUANTITY,PRICE FROM ORDER_DETAIL WHERE ORDER_NO = ? and PRODUCT_ID=?";
    
    private static final String DELETE = "DELETE FROM ORDER_DETAIL WHERE ORDER_NO = ? ";
    private static final String DELETEONE = "DELETE FROM ORDER_DETAIL WHERE ORDER_NO = ? and PRODUCT_ID=? ";
    private static final String UPDATE = "UPDATE ORDER_DETAIL SET  QUANTITY=?, PRICE=? WHERE  ORDER_NO=? and product_id=? ";
    private static final String GETPRICE ="SELECT PRODUCT_PRICE FROM PRODUCT WHERE PRODUCT_ID=?" ;

    private static final String GET_ONE_ORDERDETAIL = "SELECT ORDER_NO,PRODUCT_ID,QUANTITY,PRICE FROM ORDER_DETAIL WHERE ORDER_NO = ? ";

    public void deleteOne(String order_no ,String product_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETEONE);
			pstmt.setString(1, order_no);
			pstmt.setString(2, product_id);
			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException se) {
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
    
    public Integer getprice(String product_id) {
    	Integer getprice=null;
    	ProductVO productvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETPRICE);
			pstmt.setString(1,product_id );
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productvo = new ProductVO();	
				productvo.setProduct_price(rs.getInt("product_price"));
				getprice=productvo.getProduct_price();
			}
			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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
		return getprice;    }
    	
    	
    	
    	
    
    @Override
    public void insert(Order_detailVO order_detailvo, Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, order_detailvo.getorder_no());
			pstmt.setString(2, order_detailvo.getProduct_id());
			pstmt.setInt(3, order_detailvo.getQuantity());
			pstmt.setInt(4, order_detailvo.getPrice());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}

    @Override
    public void update(Order_detailVO order_detailvo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, order_detailvo.getQuantity());
			pstmt.setInt(2, order_detailvo.getPrice());
			pstmt.setString(3, order_detailvo.getorder_no());
			pstmt.setString(4, order_detailvo.getProduct_id());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
    public void delete(String order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, order_no);
			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException se) {
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
    public Order_detailVO findByPrimaryKey(String order_no,String product_id) {
    	Order_detailVO order_detailvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, order_no);
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
		} catch (SQLException | ClassNotFoundException se) {
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
    public List<Order_detailVO> getAll() {
    	List<Order_detailVO> list = new ArrayList();
    	Order_detailVO order_detailvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<Order_detailVO> getdetail(String order_no) {
		List<Order_detailVO> list = new ArrayList();
    	Order_detailVO order_detailvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ORDERDETAIL);
			pstmt.setString(1, order_no);
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
public Integer getOrderDetailTotal(String order_no) {
	
	Integer total=0;
	Order_detailVO order_detailvo = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ONE_ORDERDETAIL);
		pstmt.setString(1, order_no);
		rs = pstmt.executeQuery();
		List<Order_detailVO> list = new ArrayList();
		while (rs.next()) {
			order_detailvo = new Order_detailVO();
			order_detailvo.setorder_no(rs.getString("order_no"));
			order_detailvo.setProduct_id(rs.getString("product_id"));
			order_detailvo.setQuantity(rs.getInt("quantity"));
			order_detailvo.setPrice(rs.getInt("price"));
			list.add(order_detailvo); // Store the row in the list
			int p=order_detailvo.getQuantity();
			int p2=order_detailvo.getPrice();
			int p3=p*p2;
			total+=p3;
		}
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
	} catch (ClassNotFoundException e) {
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
	
	
	
	
	return total;
	
}
public static void main(String[] args) {
	Order_detailJDBCDAO dao=new Order_detailJDBCDAO();
   Order_detailVO vo= new Order_detailVO();
   vo.setorder_no("OR-2020-03-28-000001");
   vo.setPrice(999);
   vo.setQuantity(5);
   vo.setProduct_id("320018");
   dao.addDetail(vo);
//   vo.setorder_no("OR-2020-03-28-000001");
//   vo.setProduct_id("320016");
//   vo.setQuantity(9);
//   vo.setPrice(200);
//   dao.update(vo);
//   int uptotal=dao.getOrderDetailTotal("OR-2020-03-28-000001");
//   System.out.println(uptotal);
//   Shop_orderJDBCDAO orderdao=new Shop_orderJDBCDAO();
//	Shop_orderVO shop_ordervo=new Shop_orderVO();
//	shop_ordervo.setOrder_no("OR-2020-03-28-000001");
//	shop_ordervo.setTotal(uptotal);
//	orderdao.updatetotal(shop_ordervo);
//   
//	
	
}

@Override
public void addDetail(Order_detailVO order_detailvo) {
	
	 Connection con = null;
	 
	PreparedStatement pstmt = null;

	try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT_STMT);
		pstmt.setString(1, order_detailvo.getorder_no());
		pstmt.setString(2, order_detailvo.getProduct_id());
		pstmt.setInt(3, order_detailvo.getQuantity());
		pstmt.setInt(4, order_detailvo.getPrice());
		pstmt.executeUpdate();
	} catch (SQLException se) {
		if (con != null) {
			try {
				// 3●設定於當有exception發生時之catch區塊內
				System.err.print("Transaction is being ");
				System.err.println("rolled back-由-emp");
				con.rollback();
			} catch (SQLException excep) {
				throw new RuntimeException("rollback error occured. "
						+ excep.getMessage());
			}
		}
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
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
	}
	
}




}




