package com.shop_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.order_detail.model.Order_detailJDBCDAO;
import com.order_detail.model.Order_detailVO;


public class Shop_orderJDBCDAO implements Shop_orderDAO_interface{



	//資料庫連結
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT =
			"		INSERT INTO SHOP_ORDER (order_no,member_id,order_status,total,pay_type,dv_address) VALUES ('OR-'||to_CHAR(current_timestamp,'YYYY-MM-DD')||'-'||LPAD(to_char(SQ_ORDER_NO.NEXTVAL),6,'0'),?,? ,?, ?, ?)";
		
	private static final String GET_ALL_STMT = 
		"SELECT order_no,member_id,order_status,order_time,total,pay_type,dv_address FROM SHOP_ORDER order by ORDER_NO";
	private static final String GET_ONE_STMT = 
		"SELECT order_no,member_id,order_status,order_time,total,pay_type,dv_address FROM SHOP_ORDER where ORDER_NO = ?";
	private static final String DELETE = 
		"DELETE FROM SHOP_ORDER where order_no = ? ";
	private static final String UPDATE = 
		"UPDATE SHOP_ORDER set order_status=?,dv_address=? where order_no = ?";
	private static final String UPDATESTATUS = 
			"UPDATE SHOP_ORDER set order_status=? where order_no = ?";
	private static final String UPDATETOTAL = 
			"UPDATE SHOP_ORDER set  total=? where order_no = ?";
	private static final String GetFRESH="select   order_no FROM shop_order  WHERE rownum = 1  ORDER BY order_no DESC";
	private static final String GET_ONE_STMT_BYORDER = 
			"SELECT order_no,member_id,order_status,order_time,total,pay_type,dv_address FROM SHOP_ORDER where member_id = ?";
	
	
	public String getfresh() {
		String no=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 Shop_orderVO  shop_ordervo= null;


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GetFRESH);
			    pstmt.executeUpdate();
				rs = pstmt.executeQuery();
				shop_ordervo=new Shop_orderVO();
				while (rs.next()) {
					shop_ordervo.setOrder_no(rs.getString("order_no"));

					
				}
				no=shop_ordervo.getOrder_no();
			// Handle any SQL errors
		} catch (SQLException se) {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return no;
		
		
		
		

	}
	
	
	public void updatetotal(Shop_orderVO shop_ordervo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATETOTAL);
			
	            pstmt.setInt(1, shop_ordervo.getTotal());
	            pstmt.setString(2, shop_ordervo.getOrder_no());
			    pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
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
	public void insert(Shop_orderVO shop_ordervo,List<Order_detailVO>list) {
		 Connection con = null;
	        PreparedStatement pstmt = null;

	        try {
	        	Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				con.setAutoCommit(false);
				String cols[] = {"order_no"};
				pstmt = con.prepareStatement(INSERT_STMT,cols);
	            pstmt.setString(1, shop_ordervo.getMember_id());
	            pstmt.setInt(2, shop_ordervo.getOrder_status());
	            pstmt.setInt(3, shop_ordervo.getTotal());
	            pstmt.setInt(4, shop_ordervo.getPay_type());
	            pstmt.setString(5, shop_ordervo.getDv_address());
	            pstmt.executeUpdate();
	            String next_order_no=null;
				ResultSet rs = pstmt.getGeneratedKeys();

				if (rs.next()) {
					next_order_no = rs.getString(1);
					System.out.println("自增主鍵值= " + next_order_no );
				} else {
					System.out.println("未取得自增主鍵值");
				}
				rs.close();
				
	            //新增訂單明細
				Order_detailJDBCDAO dao=new Order_detailJDBCDAO();
				System.out.println("list.size()-A="+list.size());
				int ii=1;
				for (Order_detailVO detail : list) {
					
					System.out.println("新增開始"+ii);
				detail.setorder_no(next_order_no);
				dao.insert(detail,con);
				ii++;
					
				}
				con.commit();
				con.setAutoCommit(true);
				System.out.println("list.size()-B="+list.size());
				System.out.println("新增部門編號" + next_order_no + list.size()
						+ "明細同時被新增");
	            
	            
	            
	            
	            
	            
	        } catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-dept");
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
	public void update(Shop_orderVO shop_ordervo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
	            pstmt.setInt(1, shop_ordervo.getOrder_status());  
	            pstmt.setString(2, shop_ordervo.getDv_address());
	            pstmt.setString(3, shop_ordervo.getOrder_no());
			    pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1,order_no);
			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException se) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public static void main(String[] args) {
		Shop_orderJDBCDAO dao=new Shop_orderJDBCDAO();
		List<Shop_orderVO> list=dao.getAll();

		for(Shop_orderVO a:list) {
			System.out.println(a.getOrder_no());
		}
	
	
	}


	@Override
	public List<Shop_orderVO> getOrderBYMEMBER(String member_id) {
		List<Shop_orderVO> list = new ArrayList<Shop_orderVO>();

		Shop_orderVO shop_ordervo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_BYORDER);
			pstmt.setString(1, member_id);
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
		return list;
		
		
		
		
		
	}


	@Override
	public void changestatus(Shop_orderVO shop_ordervo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTATUS);
			
	            pstmt.setInt(1, shop_ordervo.getOrder_status());  
	            pstmt.setString(2, shop_ordervo.getOrder_no());
			    pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
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
