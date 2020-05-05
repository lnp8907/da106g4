package android.com.coupon.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CouponDAO implements CouponDAO_interface {

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

	private static final String GET_ALL_STMT = "SELECT C_NO,C_NAME,DISCOUNT,to_char(START_DATE,'yyyy-mm-dd') START_DATE,to_char(END_DATE,'yyyy-mm-dd') END_DATE, COUPON_CODE FROM COUPON order by C_NO";
	private static final String GET_DISCOUNT_BY_COUPON_CODE_STMT = "SELECT * FROM COUPON where coupon_code = ?";
	private static final String FIND_IMG_BY_C_NO = "SELECT c_picture FROM coupon WHERE c_no = ?";

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO 也稱為 Domain objects
				couponVO = new CouponVO();
				couponVO.setC_no(rs.getString("C_NO"));
				couponVO.setC_name(rs.getString("C_NAME"));
				couponVO.setDiscount(rs.getInt("DISCOUNT"));
				couponVO.setStart_date(rs.getDate("START_DATE"));
				couponVO.setEnd_date(rs.getDate("END_DATE"));
				couponVO.setCoupon_code(rs.getString("COUPON_CODE"));
				list.add(couponVO);
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
	public byte[] getImage(String c_no) {
		byte[] c_picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_IMG_BY_C_NO);

			pstmt.setString(1, c_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				c_picture = rs.getBytes(1);
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
		return c_picture;
	}

	@Override
	public CouponVO getDiscountByCode(String coupon_code) {
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DISCOUNT_BY_COUPON_CODE_STMT);
			pstmt.setString(1, coupon_code);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setC_no(rs.getString("C_NO"));
				couponVO.setC_name(rs.getString("C_NAME"));
				couponVO.setDiscount(rs.getInt("DISCOUNT"));
				couponVO.setStart_date(rs.getDate("START_DATE"));
				couponVO.setEnd_date(rs.getDate("END_DATE"));
				couponVO.setCoupon_code(rs.getString("COUPON_CODE"));
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
		return couponVO;
	}
}