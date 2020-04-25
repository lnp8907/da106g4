package com.ingredient.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientJDBCDAO implements IngredientDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setString(1, ingredientVO.getIngredient_id());
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(IngredientVO ingredientVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String ingredient_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ingredient_id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("並未刪除 " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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

				list.add(ingredientVO);

			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	public static void main(String args[]) {

		IngredientJDBCDAO dao = new IngredientJDBCDAO();
//		IngredientVO daoT01 = new IngredientVO();
//		daoT01.setIngredient_type("蔬菜");
//		daoT01.setIngredient_name("測試黃金高麗菜");
//		daoT01.setCarbohydrate(17.5);
//		daoT01.setProtein(0.91);
//		daoT01.setFat(0.11);
//		daoT01.setCalorie(90.0);
//		daoT01.setVitamin_B(0.17);
//		daoT01.setVitamin_C(0.08);
//		daoT01.setSalt(0.17);
//		daoT01.setVgetable(0.91);
//		dao.insert(daoT01);
		// 更新
//		IngredientVO daoT02 = new IngredientVO();
//		daoT02.setIngredient_id("551557");
//		daoT02.setIngredient_type("蔬菜");
//		daoT02.setIngredient_name("測試黃金");
//		daoT02.setCarbohydrate(17.5);
//		daoT02.setProtein(0.91);
//		daoT02.setFat(0.11);
//		daoT02.setCalorie(90.0);
//		daoT02.setVitamin_B(0.17);
//		daoT02.setVitamin_C(0.08);
//		daoT02.setSalt(0.17);
//		daoT02.setVgetable(0.91);
//		dao.update(daoT02);

//		dao.delete("551557");
//		System.out.println("已刪除");

//		IngredientVO daoT03 = dao.findByPrimaryKey("551539");
//		System.out.print(daoT03.getIngredient_id()+",");
//		System.out.print(daoT03.getIngredient_type()+",");
//		System.out.print(daoT03.getIngredient_name()+",");
//		System.out.print(daoT03.getCarbohydrate()+",");
//		System.out.print(daoT03.getProtein()+",");
//		System.out.print(daoT03.getFat()+",");
//		System.out.print(daoT03.getCalorie()+",");
//		System.out.print(daoT03.getVitamin_B()+",");
//		System.out.print(daoT03.getVitamin_C()+",");
//		System.out.print(daoT03.getSalt()+",");
//		System.out.println(daoT03.getVgetable());
//		System.out.println("---------------我是分隔線----------------------------");
		
		IngredientVO daoT03 = dao.getIntakeByName("高麗菜");
		System.out.print(daoT03.getCarbohydrate()+",");
		System.out.print(daoT03.getProtein()+",");
		System.out.print(daoT03.getFat()+",");
		System.out.print(daoT03.getCalorie()+",");
		System.out.print(daoT03.getVitamin_B()+",");
		System.out.print(daoT03.getVitamin_C()+",");
		System.out.print(daoT03.getSalt()+",");
		System.out.println(daoT03.getVgetable());
		System.out.println("---------------我是分隔線----------------------------");

//		List<IngredientVO> list = dao.getAll();
//		for (IngredientVO ingredient : list) {
//			System.out.print(ingredient.getIngredient_id()+",");
//			System.out.print(ingredient.getIngredient_type()+",");
//			System.out.print(ingredient.getIngredient_name()+",");
//			System.out.print(ingredient.getCarbohydrate()+",");
//			System.out.print(ingredient.getProtein()+",");
//			System.out.print(ingredient.getFat()+",");
//			System.out.print(ingredient.getCalorie()+",");
//			System.out.print(ingredient.getVitamin_B()+",");
//			System.out.print(ingredient.getVitamin_C()+",");
//			System.out.print(ingredient.getSalt()+",");
//			System.out.println(ingredient.getVgetable());
//		}
	}

}
