package android.com.product.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

import android.com.recipe.model.RecipeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductDAO implements ProductDAO_interface {
    //資料庫連結
    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT where product_status = 0 and recipe_id is not null order by recipe_id";
	private static final String GET_PRODUCT_ID_BY_C_NO_STMT = "SELECT * FROM COUPON_DETAILS where C_NO = ?";
	private static final String GET_ONE_STMT = "SELECT product_id, recipe_id, product_type, product_name, product_price, product_status, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, content FROM PRODUCT WHERE product_id = ?";
	private static final String FIND_IMG_BY_PRODUCT_ID = "SELECT product_photo FROM prod.uct WHERE product_id = ?";
	
	

  
    @Override
    public ProductVO findByPrimaryKey(String product_id) {
    	ProductVO productVO = null;
		String recipe_id ="";
		RecipeDAO reDao = new RecipeDAO();
		 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, product_id);
			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getString("product_id"));
				productVO.setRecipe_id(recipe_id = rs.getString("recipe_id"));
				productVO.setProduct_type(rs.getString("product_type"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_status(rs.getInt("product_status"));
				productVO.setCarbohydrate(rs.getDouble("carbohydrate"));
				productVO.setProtein(rs.getDouble("protein"));
				productVO.setFat(rs.getDouble("fat"));
				productVO.setCalorie(rs.getDouble("calorie"));
				productVO.setVitamin_B(rs.getDouble("vitamin_B"));
				productVO.setVitamin_C(rs.getDouble("vitamin_C"));
				productVO.setContent(reDao.getContentByPrimaryKey(recipe_id, con));
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
		return productVO;
    }

    @Override
    public List<ProductVO> getAll() {
    	List<ProductVO> list = new ArrayList();
    	ProductVO productVO = null;
    	RecipeDAO reDao = new RecipeDAO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			String recipe_id="";
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getString("product_id"));
				productVO.setRecipe_id(recipe_id=rs.getString("recipe_id"));
				System.out.println(recipe_id);
				productVO.setProduct_type(rs.getString("product_type"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_status(rs.getInt("product_status"));
//				productVO.setCarbohydrate(rs.getDouble("carbohydrate"));
//				productVO.setProtein(rs.getDouble("protein"));
//				productVO.setFat(rs.getDouble("fat"));
//				productVO.setCalorie(rs.getDouble("calorie"));
//				productVO.setVitamin_B(rs.getDouble("vitamin_B"));
//				productVO.setVitamin_C(rs.getDouble("vitamin_C"));
				productVO.setContent(reDao.getContentByPrimaryKey(recipe_id, con));
				System.out.println(productVO.getContent());
				list.add(productVO);
			
			}	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public byte[] getImage(String product_id) {
		
		byte[] product_photo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		RecipeDAO recipedao = new RecipeDAO();
		
		try {
			con = ds.getConnection();
			recipedao.getImage(product_id);
//			pstmt = con.prepareStatement(FIND_IMG_BY_PRODUCT_ID);
			
//			pstmt.setString(1, product_id);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				product_photo = rs.getBytes(1);
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
		return product_photo;
	}

	@Override
	public List<String> getProduct_idByC_no(String c_no) {
		List<String> list = new ArrayList();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_PRODUCT_ID_BY_C_NO_STMT);
			pstmt.setString(1, c_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("product_id"));
			}	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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