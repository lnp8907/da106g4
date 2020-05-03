package com.instant_delivery_order.model;

import java.util.*;
import java.sql.*;


public class InstantDeliveryOrderJDBCDAO implements InstantDeliveryOrderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";
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

	@Override
	public void insert(InstantDeliveryOrderVO instantDeliveryOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, instantDeliveryOrderVO.getMember_id());
			pstmt.setInt(2, instantDeliveryOrderVO.getP_method());
			pstmt.setInt(3, instantDeliveryOrderVO.getP_status());
			pstmt.setInt(4, instantDeliveryOrderVO.getTotal());
			pstmt.setString(5, instantDeliveryOrderVO.getD_address());
			pstmt.setString(6, instantDeliveryOrderVO.getQrcode());
			pstmt.setInt(7, instantDeliveryOrderVO.getO_status());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(InstantDeliveryOrderVO instantDeliveryOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setString(1, instantDeliveryOrderVO.getMember_id());
			pstmt.setString(2, instantDeliveryOrderVO.getStaff_id());
			pstmt.setInt(3, instantDeliveryOrderVO.getP_method());
			pstmt.setInt(4, instantDeliveryOrderVO.getP_status());
			pstmt.setInt(5, instantDeliveryOrderVO.getTotal());
			pstmt.setString(6, instantDeliveryOrderVO.getD_address());
			pstmt.setString(7, instantDeliveryOrderVO.getQrcode());
			pstmt.setInt(8, instantDeliveryOrderVO.getO_status());
			pstmt.setString(9, instantDeliveryOrderVO.getIdo_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String ido_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ido_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public InstantDeliveryOrderVO findByPrimaryKey(String ido_no) {

		InstantDeliveryOrderVO instantDeliveryOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		InstantDeliveryOrderJDBCDAO boj = new InstantDeliveryOrderJDBCDAO();

//		// 新增
//		InstantDeliveryOrderVO instantDeliveryOrder01 = new InstantDeliveryOrderVO();
//		instantDeliveryOrder01.setMember_id("810001");
//		instantDeliveryOrder01.setP_method(0);
//		instantDeliveryOrder01.setP_status(0);
//		instantDeliveryOrder01.setTotal(100);
//		instantDeliveryOrder01.setD_address("地址");
//		instantDeliveryOrder01.setQrcode("QR");
//		instantDeliveryOrder01.setO_status(0);
//		boj.insert(instantDeliveryOrder01);
//		System.out.println("新增成功");
//
//		// 修改
//		InstantDeliveryOrderVO instantDeliveryOrder02 = new InstantDeliveryOrderVO();
//		instantDeliveryOrder02.setIdo_no("IO-2020-03-28-000002");
//		instantDeliveryOrder02.setMember_id("810003");
//		instantDeliveryOrder02.setStaff_id("910003");
//		instantDeliveryOrder02.setP_method(1);
//		instantDeliveryOrder02.setP_status(1);
//		instantDeliveryOrder02.setTotal(100);
//		instantDeliveryOrder02.setD_address("地址");
//		instantDeliveryOrder02.setQrcode("QR");
//		instantDeliveryOrder02.setO_status(0);
//		boj.update(instantDeliveryOrder02);
//		System.out.println("修改成功");
////
////		// 刪除
////		boj.delete("IO-2020-03-25-000007");
////		System.out.println("刪除成功");

		// 查詢
//		InstantDeliveryOrderVO instantDeliveryOrder03 = boj.findByPrimaryKey("IO-2020-03-14-000001");
//		System.out.print(instantDeliveryOrder03.getIdo_no() + ",");
//		System.out.print(instantDeliveryOrder03.getMember_id() + ",");
//		System.out.print(instantDeliveryOrder03.getStaff_id() + ",");
//		System.out.print(instantDeliveryOrder03.getO_time() + ",");
//		System.out.print(instantDeliveryOrder03.getP_method() + ",");
//		System.out.print(instantDeliveryOrder03.getP_status() + ",");
//		System.out.print(instantDeliveryOrder03.getTotal() + ",");
//		System.out.print(instantDeliveryOrder03.getD_address() + ",");
//		System.out.print(instantDeliveryOrder03.getQrcode() + ",");
//		System.out.print(instantDeliveryOrder03.getO_status() + ",");
//		System.out.print(instantDeliveryOrder03.getOc_time() + ",");
//		System.out.println();
//		System.out.println("---------------------");

		// 查詢所有
		List<InstantDeliveryOrderVO> list = boj.getAll();
		for (InstantDeliveryOrderVO aInstantDeliveryOrder : list) {
			System.out.print(aInstantDeliveryOrder.getIdo_no() + ",");
			System.out.print(aInstantDeliveryOrder.getMember_id() + ",");
			System.out.print(aInstantDeliveryOrder.getStaff_id() + ",");
			System.out.print(aInstantDeliveryOrder.getO_time() + ",");
			System.out.print(aInstantDeliveryOrder.getP_method() + ",");
			System.out.print(aInstantDeliveryOrder.getP_status() + ",");
			System.out.print(aInstantDeliveryOrder.getTotal() + ",");
			System.out.print(aInstantDeliveryOrder.getD_address() + ",");
			System.out.print(aInstantDeliveryOrder.getQrcode() + ",");
			System.out.print(aInstantDeliveryOrder.getO_status() + ",");
			System.out.print(aInstantDeliveryOrder.getOc_time() + ",");
			System.out.println();
		}
	}

	@Override
	public void changePayStatus(String ido_no, Integer p_status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeOrderStatus(String ido_no, Integer o_status) {
		// TODO Auto-generated method stub
		
	}
	
}


