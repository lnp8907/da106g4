package android.com.instant_delivery_order.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.recipe_order_details.model.RecipeOrderDetailsDAO;
import android.com.recipe_order_details.model.RecipeOrderDetailsVO;
import android.com.member.model.MemberDAO;
import android.com.member.model.MemberDAOImpl;

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

	private static final String GET_ALL_STMT = "SELECT * FROM INSTANT_DELIVERY_ORDER order by IDO_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM INSTANT_DELIVERY_ORDER where IDO_NO = ?";
//  Android使用
	                                                                 
	private static final String UPDATE_STAFF_ID_STMT = "UPDATE INSTANT_DELIVERY_ORDER SET STAFF_ID = ? , O_STATUS = ? where IDO_NO = ? ";
	private static final String GET_NEED_DELIVERY_ORDER_STMT = "SELECT * FROM INSTANT_DELIVERY_ORDER where O_STATUS = ? order by IDO_NO";
	private static final String GET_ALL_BY_MEMBER_ID_STMT = "SELECT * FROM INSTANT_DELIVERY_ORDER where member_id = ? order by IDO_NO";
	private static final String INSERT_STMT = "INSERT INTO INSTANT_DELIVERY_ORDER (IDO_NO,MEMBER_ID,P_METHOD,P_STATUS,TOTAL,D_ADDRESS,QRCODE) VALUES (to_char('IO')||'-'||to_char(sysdate,'yyyy-mm-dd')||'-'||LPAD(to_char(SQ_IDO_NO.NEXTVAL),6,'0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ONE_ORDER_PRODUCT_STMT = "SELECT * FROM RECIPE_ORDER_DETAILS where ido_no = ? order by product_id";
	private static final String UPDATE_O_STATUS = "UPDATE INSTANT_DELIVERY_ORDER SET O_STATUS = ? where IDO_NO = ?";

	//	private static final String DELETE = 
//			"DELETE FROM INSTANT_DELIVERY_ORDER where IDO_NO = ?";
//	private static final String UPDATE = 
//			"UPDATE INSTANT_DELIVERY_ORDER set MEMBER_ID=?,STAFF_ID=?,P_METHOD=?,P_STATUS=?,TOTAL=?,D_ADDRESS=?,QRCODE=?,O_STATUS=? where IDO_NO = ?";

	@Override
	synchronized public String insertWithDetails(InstantDeliveryOrderVO instantDeliveryOrderVO,
			List<RecipeOrderDetailsVO> recipeOrderDetailsList) {
//		System.out.println(instantDeliveryOrderVO.getTotal());
		Integer total = instantDeliveryOrderVO.getTotal();
		String member_id = instantDeliveryOrderVO.getMember_id();
		Integer p_method = instantDeliveryOrderVO.getP_method();

		if (p_method != 1) { //付款方式不為貨到付款
			MemberDAO mDAO = new MemberDAOImpl();
			if (!(mDAO.balanceEnough(member_id, total))) {
				return null; // 餘額不足新增失敗
			}
			
			if (!(mDAO.payProduct(member_id, total))) {
				return null; // 扣款失敗
			}
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		String next_ido_no = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			String[] cols = { "ido_no" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, p_method);
			if (p_method == 1) {// 貨到付款
				pstmt.setInt(3, 1);// 未繳費
			} else {
				pstmt.setInt(3, 0);// 已繳費
			}

			pstmt.setInt(4, total);
			pstmt.setString(5, instantDeliveryOrderVO.getD_address());
			pstmt.setString(6, "這是Base64");
			pstmt.executeUpdate();
//			pstmt.setString(1, instantDeliveryOrderVO.getMember_id());
//			pstmt.setInt(2, instantDeliveryOrderVO.getP_method());
//			pstmt.setInt(3, instantDeliveryOrderVO.getP_status());
//			pstmt.setInt(4, instantDeliveryOrderVO.getTotal());
//			pstmt.setString(5, instantDeliveryOrderVO.getD_address());
//			pstmt.setString(6, instantDeliveryOrderVO.getQrcode());
//			pstmt.setInt(7, instantDeliveryOrderVO.getO_status());
//			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_ido_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_ido_no + "(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			RecipeOrderDetailsDAO rodDAO = new RecipeOrderDetailsDAO();
			System.out.println("recipeOrderDetailsList.size()-A= " + recipeOrderDetailsList.size());
			for (RecipeOrderDetailsVO recipeOrderDetailsVO : recipeOrderDetailsList) {
				recipeOrderDetailsVO.setIDO_no(next_ido_no);
				rodDAO.insertWithOrder(recipeOrderDetailsVO, con);
			}
			con.commit();
			con.setAutoCommit(true);
			System.out.println("recipeOrderDetailsList.size()-B= " + recipeOrderDetailsList.size());
			System.out.println("新增訂單編號" + next_ido_no + "時，共有明細" + recipeOrderDetailsList.size() + "筆同時被新增");

			// Handle any driver errors
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
		return next_ido_no;
	}

//	@Override
//	public void update(InstantDeliveryOrderVO instantDeliveryOrderVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, instantDeliveryOrderVO.getMember_id());
//			pstmt.setString(2, instantDeliveryOrderVO.getStaff_id());
//			pstmt.setInt(3, instantDeliveryOrderVO.getP_method());
//			pstmt.setInt(4, instantDeliveryOrderVO.getP_status());
//			pstmt.setInt(5, instantDeliveryOrderVO.getTotal());
//			pstmt.setString(6, instantDeliveryOrderVO.getD_address());
//			pstmt.setString(7, instantDeliveryOrderVO.getQrcode());
//			pstmt.setInt(8, instantDeliveryOrderVO.getO_status());
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void delete(String ido_no) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, ido_no);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

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
//				instantDeliveryOrderVO.setQrcode(rs.getString("QRCODE"));
				instantDeliveryOrderVO.setO_status(rs.getInt("O_STATUS"));
//				instantDeliveryOrderVO.setOc_time(rs.getTimestamp("OC_TIME"));
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

	@Override
	public List<InstantDeliveryOrderVO> getAll(String member_id) {
		InstantDeliveryOrderVO instantDeliveryOrderVO = null;
		List<InstantDeliveryOrderVO> list = new ArrayList<InstantDeliveryOrderVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMBER_ID_STMT);
			pstmt.setString(1, member_id);
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

	@Override
	public List<InstantDeliveryOrderVO> getDeliveryOrder(Integer o_status) {
		List<InstantDeliveryOrderVO> list = new ArrayList<InstantDeliveryOrderVO>();
		InstantDeliveryOrderVO instantDeliveryOrderVO = null;
		InstantDeliveryOrderDAO iDAO = new InstantDeliveryOrderDAO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NEED_DELIVERY_ORDER_STMT);
			
			pstmt.setInt(1, o_status);
			rs = pstmt.executeQuery();

			String ido_no ="";
			while (rs.next()) {
				// instantDeliveryOrderVO 也稱為 Domain objects
				instantDeliveryOrderVO = new InstantDeliveryOrderVO();
				instantDeliveryOrderVO.setIdo_no(ido_no= rs.getString("IDO_NO"));
				instantDeliveryOrderVO.setMember_id(rs.getString("MEMBER_ID"));
//				instantDeliveryOrderVO.setStaff_id(rs.getString("STAFF_ID"));
				instantDeliveryOrderVO.setO_time(rs.getTimestamp("O_TIME"));
				instantDeliveryOrderVO.setP_method(rs.getInt("P_METHOD"));
				instantDeliveryOrderVO.setP_status(rs.getInt("P_STATUS"));
				instantDeliveryOrderVO.setTotal(rs.getInt("TOTAL"));
				instantDeliveryOrderVO.setD_address(rs.getString("D_ADDRESS"));
//				instantDeliveryOrderVO.setQrcode(rs.getString("QRCODE"));
				instantDeliveryOrderVO.setO_status(rs.getInt("O_STATUS"));
//				instantDeliveryOrderVO.setOc_time(rs.getTimestamp("OC_TIME"));
				instantDeliveryOrderVO.setOrderProductVOList(iDAO.getOneOrderProductVO(ido_no, con));
				list.add(instantDeliveryOrderVO);
				// Store the row in the list
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

	@Override
	public boolean updateStaffId(String staff_id, String ido_no, String o_status) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean b = false;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(UPDATE_STAFF_ID_STMT);
			ps.setString(1, staff_id);
			ps.setString(2, o_status);
			ps.setString(3, ido_no);
			
			
			int count = ps.executeUpdate();
			b = count > 0 ? true : false; 


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	@Override
	public List<OrderProductVO> getOneOrderProductVO(String ido_no, Connection con) {

		List<OrderProductVO> list = new ArrayList<OrderProductVO>();

//		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ORDER_PRODUCT_STMT);
			pstmt.setString(1, ido_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
//				 instantDeliveryOrderVO 也稱為 Domain objects
				OrderProductVO orderProductVO = new OrderProductVO();
				orderProductVO.setProduct_id(rs.getString("PRODUCT_ID"));
				orderProductVO.setQuantity(rs.getInt("QUANTITY"));
				orderProductVO.setProduct_price(rs.getInt("PRICE"));
				list.add(orderProductVO);
				// Store the row in the list
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
		}

		return list;
	}

	@Override
	public boolean finishOrder(String ido_no, Integer o_status) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean b = false;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(UPDATE_O_STATUS);
			ps.setInt(1, o_status);
			ps.setString(2, ido_no);

			
			
			int count = ps.executeUpdate();
			b = count > 0 ? true : false; 


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}
}
