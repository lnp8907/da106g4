package com.recipe.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RecipeJNIDAO implements RecipeDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO RECIPE (recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake) VALUES ( sq_recipe_id.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = 
			"SELECT recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake FROM RECIPE order by recipe_id";
	private static final String GET_ONE_STMT = 
			"SELECT recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake FROM RECIPE WHERE recipe_id = ?";
	private static final String DELETE = 
			"DELETE FROM RECIPE WHERE recipe_id = ?";
	private static final String UPDATE = 
			"UPDATE RECIPE SET rcstyle_no=?, member_id=?, recipe_name=?, recipe_type=?, recipe_status=?, refollow_num=?, recipe_uldate=?, recipe_ingredient=?, recipe_step=?, recipe_photo=?, cook_time=?, calo_intake=?, salt_intake=?, protein_intake=?, fat_intake=?, carbo_intake=?, vitamin_b=?, vitamin_c=?, vage_intake=? WHERE recipe_id = ?";

	@Override
	public void insert(RecipeVO recipeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recipeVO.getRcstyle_no());
			pstmt.setString(2, recipeVO.getMember_id());
			pstmt.setString(3, recipeVO.getRecipe_name());
			pstmt.setString(4, recipeVO.getRecipe_type());
			pstmt.setInt(5, recipeVO.getRecipe_status());
			pstmt.setInt(6, recipeVO.getRefollow_num());
			pstmt.setDate(7, recipeVO.getRecipe_uldate());
			pstmt.setString(8, recipeVO.getRecipe_ingredient());
			pstmt.setString(9, recipeVO.getRecipe_step());
			pstmt.setString(10, recipeVO.getRecipe_photo());
			pstmt.setInt(11, recipeVO.getCook_time());
			pstmt.setDouble(12, recipeVO.getCalo_intake());
			pstmt.setDouble(13, recipeVO.getSalt_intake());
			pstmt.setDouble(14, recipeVO.getProtein_intake());
			pstmt.setDouble(15, recipeVO.getFat_intake());
			pstmt.setDouble(16, recipeVO.getCarbo_intake());
			pstmt.setDouble(17, recipeVO.getVitamin_b());
			pstmt.setDouble(18, recipeVO.getVitamin_c());
			pstmt.setDouble(19, recipeVO.getVage_intake());
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
	public void update(RecipeVO recipeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, recipeVO.getRcstyle_no());
			pstmt.setString(2, recipeVO.getMember_id());
			pstmt.setString(3, recipeVO.getRecipe_name());
			pstmt.setString(4, recipeVO.getRecipe_type());
			pstmt.setInt(5, recipeVO.getRecipe_status());
			pstmt.setInt(6, recipeVO.getRefollow_num());
			pstmt.setDate(7,recipeVO.getRecipe_uldate());
			pstmt.setString(8, recipeVO.getRecipe_ingredient());
			pstmt.setString(9, recipeVO.getRecipe_step());
			pstmt.setString(10, recipeVO.getRecipe_photo());
			pstmt.setInt(11, recipeVO.getCook_time());
			pstmt.setDouble(12, recipeVO.getCarbo_intake());
			pstmt.setDouble(13, recipeVO.getSalt_intake());
			pstmt.setDouble(14, recipeVO.getProtein_intake());
			pstmt.setDouble(15, recipeVO.getFat_intake());
			pstmt.setDouble(16, recipeVO.getCarbo_intake());
			pstmt.setDouble(17, recipeVO.getVitamin_b());
			pstmt.setDouble(18, recipeVO.getVitamin_c());
			pstmt.setDouble(19, recipeVO.getVage_intake());
			pstmt.setString(20, recipeVO.getRecipe_id());
			
			pstmt.executeUpdate();

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
	public void delete(String recipe_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, recipe_id);

			pstmt.executeUpdate();

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
	
	public RecipeVO_saved findByPrimaryKeyForSaved(String recipe_id) {
		RecipeVO_saved recipeVO = null;
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
				recipeVO = new RecipeVO_saved();
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
	public List<RecipeVO_saved> addAllIngredientsStr(){
		return null;
	}
	
}
