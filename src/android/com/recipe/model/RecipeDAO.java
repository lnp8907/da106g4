package android.com.recipe.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.recipe.model.RecipeVO;

public class RecipeDAO implements RecipeDAO_interface {

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
	private static final String FIND_BY_TYPE = "SELECT * FROM RECIPE WHERE recipe_type = ?";
	private static final String FIND_BY_STYLE = "SELECT * FROM RECIPE WHERE rcstyle_no = ?";
	private static final String GET_ONE_STMT = "SELECT recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake, recipe_content FROM RECIPE WHERE recipe_id = ?";
	private static final String GET_ALL_STMT = "SELECT recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake, recipe_content FROM RECIPE order by recipe_id";
	private static final String JOIN_PRODUCT_AND_GET_ALL_STMT = "SELECT recipe.recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, recipe.vitamin_b, recipe.vitamin_c, vage_intake, recipe_content, product.product_price FROM recipe JOIN product ON recipe.recipe_id = product.recipe_id order by recipe.recipe_id";
//	private static final String GET_CONTENT = "SELECT recipe_content FROM recipe WHERE recipe_id = ?";
	private static final String GET_ONE_CONTNET = "SELECT recipe_content FROM recipe WHERE recipe_id = ?";
	private static final String GET_ONE_IMG = "SELECT recipe_photo FROM recipe WHERE recipe_id = ?";
	
	
	@Override
	public String getContentByPrimaryKey(String recipe_id, Connection con) {
//		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String content="";

		try {
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CONTNET);
			pstmt.setString(1, recipe_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// recipeVO 也稱為 Domain objects
				content = rs.getString(1);
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
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}			
		}
		System.out.println(content);
		return content;
	}
	
	@Override
	public List<RecipeVO> getAll() {
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		RecipeVO recipeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipeVO 也稱為 Domain objects
				recipeVO = new RecipeVO();
				recipeVO.setRecipe_id(rs.getString("recipe_id"));
				recipeVO.setRcstyle_no(rs.getString("rcstyle_no"));
				recipeVO.setMember_id(rs.getString("member_id"));
				recipeVO.setRecipe_name(rs.getString("recipe_name"));
				recipeVO.setRecipe_type(rs.getString("recipe_type"));
				recipeVO.setRecipe_status(rs.getInt("recipe_status"));
				recipeVO.setRefollow_num(rs.getInt("refollow_num"));
				recipeVO.setRecipe_uldate(rs.getDate("recipe_uldate"));
				recipeVO.setRecipe_ingredient(rs.getString("recipe_ingredient"));
				recipeVO.setRecipe_step(rs.getString("recipe_step"));
				recipeVO.setRecipe_photo(rs.getString("recipe_photo"));
				recipeVO.setCook_time(rs.getInt("cook_time"));
				recipeVO.setCalo_intake(rs.getDouble("calo_intake"));
				recipeVO.setSalt_intake(rs.getDouble("salt_intake"));
				recipeVO.setProtein_intake(rs.getDouble("protein_intake"));
				recipeVO.setFat_intake(rs.getDouble("fat_intake"));
				recipeVO.setCarbo_intake(rs.getDouble("carbo_intake"));
				recipeVO.setVitamin_b(rs.getDouble("vitamin_b"));
				recipeVO.setVitamin_c(rs.getDouble("vitamin_c"));
				recipeVO.setVage_intake(rs.getDouble("vage_intake"));
				recipeVO.setRecipe_content(rs.getString("recipe_content"));

				list.add(recipeVO); // Store the row in the list
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
	public RecipeVO findByPrimaryKey(String recipe_id) {

		RecipeVO recipeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, recipe_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipeVO 也稱為 Domain objects
				recipeVO = new RecipeVO();
				recipeVO.setRecipe_id(rs.getString("recipe_id"));
				recipeVO.setRcstyle_no(rs.getString("rcstyle_no"));
				recipeVO.setMember_id(rs.getString("member_id"));
				recipeVO.setRecipe_name(rs.getString("recipe_name"));
				recipeVO.setRecipe_type(rs.getString("recipe_type"));
				recipeVO.setRecipe_status(rs.getInt("recipe_status"));
				recipeVO.setRefollow_num(rs.getInt("refollow_num"));
				recipeVO.setRecipe_uldate(rs.getDate("recipe_uldate"));
				recipeVO.setRecipe_ingredient(rs.getString("recipe_ingredient"));
				recipeVO.setRecipe_step(rs.getString("recipe_step"));
				recipeVO.setRecipe_photo(rs.getString("recipe_photo"));
				recipeVO.setCook_time(rs.getInt("cook_time"));
				recipeVO.setCalo_intake(rs.getDouble("calo_intake"));
				recipeVO.setSalt_intake(rs.getDouble("salt_intake"));
				recipeVO.setProtein_intake(rs.getDouble("protein_intake"));
				recipeVO.setFat_intake(rs.getDouble("fat_intake"));
				recipeVO.setCarbo_intake(rs.getDouble("carbo_intake"));
				recipeVO.setVitamin_b(rs.getDouble("vitamin_b"));
				recipeVO.setVitamin_c(rs.getDouble("vitamin_c"));
				recipeVO.setRecipe_content(rs.getString("recipe_content"));
				recipeVO.setVage_intake(rs.getDouble("vage_intake"));

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
		return recipeVO;
	}

	@Override
	public List<RecipeVO> findByStyle(String rcstyle_no) {
		List<RecipeVO> recipeVOList = new ArrayList<>();
		RecipeVO recipeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement(FIND_BY_STYLE);
			pstmt.setString(1, rcstyle_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recipeVO = new RecipeVO();
				recipeVO.setRecipe_id(rs.getString("recipe_id"));
				recipeVO.setRcstyle_no(rcstyle_no);
				recipeVO.setMember_id(rs.getString("member_id"));
				recipeVO.setRecipe_name(rs.getString("recipe_name"));
				recipeVO.setRecipe_type(rs.getString("recipe_type"));
				recipeVO.setRecipe_status(rs.getInt("recipe_status"));
				recipeVO.setRefollow_num(rs.getInt("refollow_num"));
				recipeVO.setRecipe_uldate(rs.getDate("recipe_uldate"));
				recipeVO.setRecipe_ingredient(rs.getString("recipe_ingredient"));
				recipeVO.setRecipe_step(rs.getString("recipe_step"));
				recipeVO.setRecipe_photo(rs.getString("recipe_photo"));
				recipeVO.setCook_time(rs.getInt("cook_time"));
				recipeVO.setCalo_intake(rs.getDouble("calo_intake"));
				recipeVO.setSalt_intake(rs.getDouble("salt_intake"));
				recipeVO.setProtein_intake(rs.getDouble("protein_intake"));
				recipeVO.setFat_intake(rs.getDouble("fat_intake"));
				recipeVO.setCarbo_intake(rs.getDouble("carbo_intake"));
				recipeVO.setVitamin_b(rs.getDouble("vitamin_b"));
				recipeVO.setVitamin_c(rs.getDouble("vitamin_c"));
				recipeVO.setRecipe_content(rs.getString("recipe_content"));
				recipeVO.setVage_intake(rs.getDouble("vage_intake"));
				recipeVOList.add(recipeVO);
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
		return recipeVOList;
	}

	@Override
	public List<RecipeVO> findByType(String recipe_type) {
		List<RecipeVO> recipeVOList = new ArrayList<>();
		RecipeVO recipeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement(FIND_BY_TYPE);
			pstmt.setString(1, recipe_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recipeVO = new RecipeVO();
				recipeVO.setRecipe_id(rs.getString("recipe_id"));
				recipeVO.setRcstyle_no(rs.getString("rcstyle_no"));
				recipeVO.setMember_id(rs.getString("member_id"));
				recipeVO.setRecipe_name(rs.getString("recipe_name"));
				recipeVO.setRecipe_type(recipe_type);
				recipeVO.setRecipe_status(rs.getInt("recipe_status"));
				recipeVO.setRefollow_num(rs.getInt("refollow_num"));
				recipeVO.setRecipe_uldate(rs.getDate("recipe_uldate"));
				recipeVO.setRecipe_ingredient(rs.getString("recipe_ingredient"));
				recipeVO.setRecipe_step(rs.getString("recipe_step"));
				recipeVO.setRecipe_photo(rs.getString("recipe_photo"));
				recipeVO.setCook_time(rs.getInt("cook_time"));
				recipeVO.setCalo_intake(rs.getDouble("calo_intake"));
				recipeVO.setSalt_intake(rs.getDouble("salt_intake"));
				recipeVO.setProtein_intake(rs.getDouble("protein_intake"));
				recipeVO.setFat_intake(rs.getDouble("fat_intake"));
				recipeVO.setCarbo_intake(rs.getDouble("carbo_intake"));
				recipeVO.setVitamin_b(rs.getDouble("vitamin_b"));
				recipeVO.setVitamin_c(rs.getDouble("vitamin_c"));
				recipeVO.setRecipe_content(rs.getString("recipe_content"));
				recipeVO.setVage_intake(rs.getDouble("vage_intake"));
				recipeVOList.add(recipeVO);
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
		return recipeVOList;
	}

	@Override
	public List<RecipeVO_A> getAllWithPrice() {
		List<RecipeVO_A> list = new ArrayList<RecipeVO_A>();
		RecipeVO_A recipeVO_A = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			System.out.println("進來了");
			con = ds.getConnection();
			pstmt = con.prepareStatement(JOIN_PRODUCT_AND_GET_ALL_STMT);
			rs = pstmt.executeQuery();
//			System.out.println("進來了");
			while (rs.next()) {
				recipeVO_A = new RecipeVO_A();
				recipeVO_A.setRecipe_id(rs.getString("recipe_id"));
				recipeVO_A.setRcstyle_no(rs.getString("rcstyle_no"));
				recipeVO_A.setMember_id(rs.getString("member_id"));
				recipeVO_A.setRecipe_name(rs.getString("recipe_name"));
				recipeVO_A.setRecipe_type(rs.getString("recipe_type"));
				recipeVO_A.setRecipe_status(rs.getInt("recipe_status"));
				recipeVO_A.setRefollow_num(rs.getInt("refollow_num"));
				recipeVO_A.setRecipe_uldate(rs.getDate("recipe_uldate"));
				recipeVO_A.setRecipe_ingredient(rs.getString("recipe_ingredient"));
				recipeVO_A.setRecipe_step(rs.getString("recipe_step"));
				recipeVO_A.setRecipe_photo(rs.getString("recipe_photo"));
				recipeVO_A.setCook_time(rs.getInt("cook_time"));
				recipeVO_A.setCalo_intake(rs.getDouble("calo_intake"));
				recipeVO_A.setSalt_intake(rs.getDouble("salt_intake"));
				recipeVO_A.setProtein_intake(rs.getDouble("protein_intake"));
				recipeVO_A.setFat_intake(rs.getDouble("fat_intake"));
				recipeVO_A.setCarbo_intake(rs.getDouble("carbo_intake"));
				recipeVO_A.setVitamin_b(rs.getDouble("vitamin_b"));
				recipeVO_A.setVitamin_c(rs.getDouble("vitamin_c"));
				recipeVO_A.setVage_intake(rs.getDouble("vage_intake"));
				recipeVO_A.setRecipe_content(rs.getString("recipe_content"));
				recipeVO_A.setRecipe_price(rs.getInt("product_price"));
				list.add(recipeVO_A);
			}
			System.out.println(list);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(rs != null) {
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
	public String getImage(String recipe_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String clob = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_IMG);
			pstmt.setString(1, recipe_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				clob = rs.getString(1);	
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "+ e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return clob;
	}
	
}