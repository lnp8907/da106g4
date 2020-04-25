package com.ingredient.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class IngredientDAO implements IngredientDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO INGREDIENT (ingredient_id, ingredient_type, ingredient_name, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, salt, vagetable) VALUES ( SQ_INGREDIENT_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ONE_STMT = "SELECT ingredient_id, ingredient_type, ingredient_name, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, salt, vagetable FROM INGREDIENT WHERE INGREDIENT_ID = ? ";
	private static final String GET_ALL_STMT = "SELECT ingredient_id, ingredient_type, ingredient_name, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, salt, vagetable FROM INGREDIENT order by ingredient_id ";
	private static final String DELETE = "DELETE FROM INGREDIENT WHERE ingredient_id = ?";
	private static final String UPDATE = "UPDATE INGREDIENT SET ingredient_type=?, ingredient_name =?, carbohydrate=?, protein=?, fat=?, calorie=?, vitamin_B=?, vitamin_C=?, salt=?, vagetable=? WHERE ingredient_id = ?";
	private static final String GET_INTAKE = " SELECT carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, salt, vagetable FROM INGREDIENT WHERE ingredient_name like(?) and rownum = 1 ";
	@Override
	public void insert(IngredientVO ingredientVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ingredientVO.getIngredient_type());
			pstmt.setString(2, ingredientVO.getIngredient_name());
			pstmt.setDouble(3, ingredientVO.getCarbohydrate());
			pstmt.setDouble(4, ingredientVO.getProtein());
			pstmt.setDouble(5, ingredientVO.getFat());
			pstmt.setDouble(6, ingredientVO.getCalorie());
			pstmt.setDouble(7, ingredientVO.getVitamin_B());
			pstmt.setDouble(8, ingredientVO.getVitamin_C());
			pstmt.setDouble(9, ingredientVO.getSalt());
			pstmt.setDouble(10, ingredientVO.getVgetable());
			pstmt.executeUpdate();

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

	}

	@Override
	public void update(IngredientVO ingredientVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ingredientVO.getIngredient_type());
			pstmt.setString(2, ingredientVO.getIngredient_name());
			pstmt.setDouble(3, ingredientVO.getCarbohydrate());
			pstmt.setDouble(4, ingredientVO.getProtein());
			pstmt.setDouble(5, ingredientVO.getFat());
			pstmt.setDouble(6, ingredientVO.getCalorie());
			pstmt.setDouble(7, ingredientVO.getVitamin_B());
			pstmt.setDouble(8, ingredientVO.getVitamin_C());
			pstmt.setDouble(9, ingredientVO.getSalt());
			pstmt.setDouble(10, ingredientVO.getVgetable());
			pstmt.setString(11, ingredientVO.getIngredient_id());

			pstmt.executeUpdate();

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
	public void delete(String ingredient_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ingredient_id);

			pstmt.executeUpdate();

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
	public IngredientVO findByPrimaryKey(String ingredient_id) {

		IngredientVO ingredientVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ingredient_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ingredientVO = new IngredientVO();
				ingredientVO.setIngredient_id(rs.getString("INGREDIENT_ID"));
				ingredientVO.setIngredient_type(rs.getString("INGREDIENT_TYPE"));
				ingredientVO.setIngredient_name(rs.getString("INGREDIENT_NAME"));
				ingredientVO.setCarbohydrate(rs.getDouble("CARBOHYDRATE"));
				ingredientVO.setProtein(rs.getDouble("PROTEIN"));
				ingredientVO.setFat(rs.getDouble("FAT"));
				ingredientVO.setCalorie(rs.getDouble("CALORIE"));
				ingredientVO.setVitamin_B(rs.getDouble("VITAMIN_B"));
				ingredientVO.setVitamin_C(rs.getDouble("VITAMIN_C"));
				ingredientVO.setSalt(rs.getDouble("SALT"));
				ingredientVO.setVgetable(rs.getDouble("VAGETABLE"));
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
		return ingredientVO;
	}
	
	
	
	@Override
	public IngredientVO getIntakeByName(String ingredient_name){
		IngredientVO ingredientVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ingredient_name = "%"+ ingredient_name + "%";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_INTAKE);	
			pstmt.setString(1, ingredient_name);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ingredientVO = new IngredientVO();
				ingredientVO.setCarbohydrate(rs.getDouble("CARBOHYDRATE"));
				ingredientVO.setProtein(rs.getDouble("PROTEIN"));
				ingredientVO.setFat(rs.getDouble("FAT"));
				ingredientVO.setCalorie(rs.getDouble("CALORIE"));
				ingredientVO.setVitamin_B(rs.getDouble("VITAMIN_B"));
				ingredientVO.setVitamin_C(rs.getDouble("VITAMIN_C"));
				ingredientVO.setSalt(rs.getDouble("SALT"));
				ingredientVO.setVgetable(rs.getDouble("VAGETABLE"));
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
		return ingredientVO;
	}

	@Override
	public List<IngredientVO> getAll() {
		List<IngredientVO> list = new ArrayList<IngredientVO>();
		IngredientVO ingredientVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipeVO 也稱為 Domain objects
				ingredientVO = new IngredientVO();
				ingredientVO.setIngredient_id(rs.getString("INGREDIENT_ID"));
				ingredientVO.setIngredient_type(rs.getString("INGREDIENT_TYPE"));
				ingredientVO.setIngredient_name(rs.getString("INGREDIENT_NAME"));
				ingredientVO.setCarbohydrate(rs.getDouble("CARBOHYDRATE"));
				ingredientVO.setProtein(rs.getDouble("PROTEIN"));
				ingredientVO.setFat(rs.getDouble("FAT"));
				ingredientVO.setCalorie(rs.getDouble("CALORIE"));
				ingredientVO.setVitamin_B(rs.getDouble("VITAMIN_B"));
				ingredientVO.setVitamin_C(rs.getDouble("VITAMIN_C"));
				ingredientVO.setSalt(rs.getDouble("SALT"));
				ingredientVO.setVgetable(rs.getDouble("VAGETABLE"));

				list.add(ingredientVO);
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

}
