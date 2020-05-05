package android.com.recipe_order_details.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RecipeOrderDetailsDAO implements RecipeOrderDetailsDAO_interface {

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

	private static final String INSERT_STMT = 
		"INSERT INTO RECIPE_ORDER_DETAILS (IDO_NO,PRODUCT_ID,QUANTITY,PRICE) VALUES (?, ?, ? , ?)";
//	private static final String GET_ALL_STMT = 
//		"SELECT * FROM RECIPE_ORDER_DETAILS order by IDO_NO";
	private static final String GET_ONE_STMT_IDO_NO = 
		"SELECT * FROM RECIPE_ORDER_DETAILS  where IDO_NO = ?";
//	private static final String GET_ONE_STMT_PRODUCT_ID = 
//		"SELECT * FROM RECIPE_ORDER_DETAILS  where PRODUCT_ID = ?";
//	private static final String DELETE = 
//		"DELETE FROM RECIPE_ORDER_DETAILS where IDO_NO = ? and PRODUCT_ID = ?";
//	private static final String UPDATE = 
//		"UPDATE RECIPE_ORDER_DETAILS set IDO_NO=?, PRODUCT_ID=?, QUANTITY=?, PRICE=? where IDO_NO = ? and PRODUCT_ID = ?";

	@Override
	public void insertWithOrder(RecipeOrderDetailsVO recipeOrderDetailsVO, Connection con) {

//		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recipeOrderDetailsVO.getIDO_no());
			pstmt.setString(2, recipeOrderDetailsVO.getProduct_id());
			pstmt.setInt(3, recipeOrderDetailsVO.getQuantity());
			pstmt.setInt(4, recipeOrderDetailsVO.getPrice());
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
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
		}

	}

//	@Override
//	public void update(RecipeOrderDetailsVO recipeOrderDetailsVO, RecipeOrderDetailsVO recipeOrderDetailsVO_1) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, recipeOrderDetailsVO.getIDO_no());
//			pstmt.setString(2, recipeOrderDetailsVO.getProduct_id());
//			pstmt.setInt(3, recipeOrderDetailsVO.getQuantity());
//			pstmt.setInt(4, recipeOrderDetailsVO.getPrice());
//			pstmt.setString(5, recipeOrderDetailsVO_1.getIDO_no());
//			pstmt.setString(6, recipeOrderDetailsVO_1.getProduct_id());
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
//	public void delete(String ido_no, String product_id) {
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
//			pstmt.setString(2, product_id);
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
	public List<RecipeOrderDetailsVO> findByPrimaryKey_IDO_no(String ido_no) {
		List<RecipeOrderDetailsVO> list = new ArrayList<RecipeOrderDetailsVO>();
		RecipeOrderDetailsVO recipeOrderDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_IDO_NO);
			
			pstmt.setString(1, ido_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipeOrderDetailsVO 也稱為 Domain objects
				recipeOrderDetailsVO = new RecipeOrderDetailsVO();
				recipeOrderDetailsVO.setIDO_no(rs.getString("IDO_NO"));
				recipeOrderDetailsVO.setProduct_id(rs.getString("PRODUCT_ID"));
				recipeOrderDetailsVO.setQuantity(rs.getInt("QUANTITY"));
				recipeOrderDetailsVO.setPrice(rs.getInt("PRICE"));
				list.add(recipeOrderDetailsVO);
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

//	@Override
//	public List<RecipeOrderDetailsVO> findByPrimaryKey_Product_id(String product_id) {
//		List<RecipeOrderDetailsVO> list = new ArrayList<RecipeOrderDetailsVO>();
//		RecipeOrderDetailsVO recipeOrderDetailsVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT_PRODUCT_ID);
//			
//			pstmt.setString(1, product_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// recipeOrderDetailsVO 也稱為 Domain objects
//				recipeOrderDetailsVO = new RecipeOrderDetailsVO();
//				recipeOrderDetailsVO.setIDO_no(rs.getString("IDO_NO"));
//				recipeOrderDetailsVO.setProduct_id(rs.getString("PRODUCT_ID"));
//				recipeOrderDetailsVO.setQuantity(rs.getInt("QUANTITY"));
//				recipeOrderDetailsVO.setPrice(rs.getInt("PRICE"));
//				list.add(recipeOrderDetailsVO);
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return list;
//	}

//	@Override
//	public List<RecipeOrderDetailsVO> getAll() {
//		List<RecipeOrderDetailsVO> list = new ArrayList<RecipeOrderDetailsVO>();
//		RecipeOrderDetailsVO recipeOrderDetailsVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// recipeOrderDetailsVO 也稱為 Domain objects
//				recipeOrderDetailsVO = new RecipeOrderDetailsVO();
//				recipeOrderDetailsVO.setIDO_no(rs.getString("IDO_NO"));
//				recipeOrderDetailsVO.setProduct_id(rs.getString("PRODUCT_ID"));
//				recipeOrderDetailsVO.setQuantity(rs.getInt("QUANTITY"));
//				recipeOrderDetailsVO.setPrice(rs.getInt("PRICE"));
//				list.add(recipeOrderDetailsVO); 
//				// Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return list;
//	}


}
