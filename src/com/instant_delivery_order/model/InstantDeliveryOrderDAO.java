package com.instant_delivery_order.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class InstantDeliveryOrderDAO implements InstantDeliveryOrderDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//
	private static final String INSERT_STMT = 
			"INSERT INTO INSTANT_DELIVERY_ORDER (IDO_NO,MEMBER_ID,P_METHOD,P_STATUS,TOTAL,D_ADDRESS,QRCODE,O_STATUS) VALUES (to_char('IO')||'-'||to_char(sysdate,'yyyy-mm-dd')||'-'||LPAD(to_char(SQ_IDO_NO.NEXTVAL),6,'0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM INSTANT_DELIVERY_ORDER order by IDO_NO";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM INSTANT_DELIVERY_ORDER where IDO_NO = ?";
	private static final String DELETE = 
			"DELETE FROM INSTANT_DELIVERY_ORDER where IDO_NO = ?";
	private static final String UPDATE = 
			"UPDATE INSTANT_DELIVERY_ORDER set MEMBER_ID=?,STAFF_ID=?,P_METHOD=?,P_STATUS=?,TOTAL=?,D_ADDRESS=?,QRCODE=?,O_STATUS=? where IDO_NO = ?";
	private static final String CHANGE_PAY_STATUS ="UPDATE INSTANT_DELIVERY_ORDER set P_STATUS=? where IDO_NO = ?"; 
	private static final String CHANGE_ORDER_STATUS ="UPDATE INSTANT_DELIVERY_ORDER set O_STATUS=? where IDO_NO = ?"; 
	
	@Override
	public void insert(InstantDeliveryOrderVO instantDeliveryOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, instantDeliveryOrderVO.getMember_id());
			pstmt.setInt(2, instantDeliveryOrderVO.getP_method());
			pstmt.setInt(3, instantDeliveryOrderVO.getP_status());
			pstmt.setInt(4, instantDeliveryOrderVO.getTotal());
			pstmt.setString(5, instantDeliveryOrderVO.getD_address());
			pstmt.setString(6, instantDeliveryOrderVO.getQrcode());
			pstmt.setInt(7, instantDeliveryOrderVO.getO_status());
			pstmt.executeUpdate();
			
			// Handle any driver errors
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
	public void update(InstantDeliveryOrderVO instantDeliveryOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, instantDeliveryOrderVO.getMember_id());
			pstmt.setString(2, instantDeliveryOrderVO.getStaff_id());
			pstmt.setInt(3, instantDeliveryOrderVO.getP_method());
			pstmt.setInt(4, instantDeliveryOrderVO.getP_status());
			pstmt.setInt(5, instantDeliveryOrderVO.getTotal());
			pstmt.setString(6, instantDeliveryOrderVO.getD_address());
			pstmt.setString(7, instantDeliveryOrderVO.getQrcode());
			pstmt.setInt(8, instantDeliveryOrderVO.getO_status());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String ido_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ido_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public InstantDeliveryOrderVO findByPrimaryKey(String ido_no) {

		InstantDeliveryOrderVO instantDeliveryOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ido_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// instantDeliveryOrderVO 也稱為 Domain objects
				instantDeliveryOrderVO = new InstantDeliveryOrderVO();
				instantDeliveryOrderVO.setIdo_no(rs.getString("IDO_NO"));
				instantDeliveryOrderVO.setMember_id(rs.getString("MEMBER_ID"));
				instantDeliveryOrderVO.setStaff_id(rs.getString("STAFF_ID"));
				instantDeliveryOrderVO.setO_time(rs.getTimestamp("O_TIME"));
				instantDeliveryOrderVO.setP_method(rs.getInt("P_METHOD"));
				instantDeliveryOrderVO.setP_status(rs.getInt("P_STATUS"));
				instantDeliveryOrderVO.setTotal(rs.getInt("TOTAL"));
				instantDeliveryOrderVO.setD_address(rs.getString("D_ADDRESS"));
				instantDeliveryOrderVO.setQrcode(rs.getString("QRCODE"));
				instantDeliveryOrderVO.setO_status(rs.getInt("O_STATUS"));
				instantDeliveryOrderVO.setOc_time(rs.getTimestamp("OC_TIME"));
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
		return instantDeliveryOrderVO;
	}

	@Override
	public List<InstantDeliveryOrderVO> getAll() {
		List<InstantDeliveryOrderVO> list = new ArrayList<InstantDeliveryOrderVO>();
		InstantDeliveryOrderVO instantDeliveryOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// instantDeliveryOrderVO 也稱為 Domain objects
				instantDeliveryOrderVO = new InstantDeliveryOrderVO();
				instantDeliveryOrderVO.setIdo_no(rs.getString("IDO_NO"));
				instantDeliveryOrderVO.setMember_id(rs.getString("MEMBER_ID"));
				instantDeliveryOrderVO.setStaff_id(rs.getString("STAFF_ID"));
				instantDeliveryOrderVO.setO_time(rs.getTimestamp("O_TIME"));
				instantDeliveryOrderVO.setP_method(rs.getInt("P_METHOD"));
				instantDeliveryOrderVO.setP_status(rs.getInt("P_STATUS"));
				instantDeliveryOrderVO.setTotal(rs.getInt("TOTAL"));
				instantDeliveryOrderVO.setD_address(rs.getString("D_ADDRESS"));
				instantDeliveryOrderVO.setQrcode(rs.getString("QRCODE"));
				instantDeliveryOrderVO.setO_status(rs.getInt("O_STATUS"));
				instantDeliveryOrderVO.setOc_time(rs.getTimestamp("OC_TIME"));
				list.add(instantDeliveryOrderVO); 
				// Store the row in the list
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
/*柏宇新增方法*/
	@Override
	public void changePayStatus(String ido_no, Integer p_status) {
//0已繳費 1未繳費
		
		Connection con = null;
		PreparedStatement pstmt = null;
if(p_status>1) {
	p_status=1;
}
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_PAY_STATUS);

			pstmt.setInt(1, p_status);
			pstmt.setString(2, ido_no);
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void changeOrderStatus(String ido_no,Integer o_status) {
		System.out.println("更改訂單狀態");
		//更改訂單狀態
		Connection con = null;
		PreparedStatement pstmt = null;
if(o_status>3) {
	o_status=4;
}
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_ORDER_STATUS);

			pstmt.setInt(1, o_status);
			pstmt.setString(2, ido_no);
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
}
