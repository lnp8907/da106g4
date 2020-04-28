package com.recipe.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order_detail.model.Order_detailVO;
import com.product.model.ProductDAO;
import com.product.model.ProductVO;
import com.shop_order.model.Shop_orderVO;

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

	private static final String INSERT_STMT = "INSERT INTO RECIPE (recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake, recipe_content) VALUES ( sq_recipe_id.NEXTVAL,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = "SELECT recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake, recipe_content FROM RECIPE order by recipe_id";
	private static final String GET_ONE_STMT = "SELECT recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake, recipe_content FROM RECIPE WHERE recipe_id = ?";
	private static final String DELETE = "DELETE FROM RECIPE WHERE recipe_id = ?";
	private static final String UPDATE = "UPDATE RECIPE SET rcstyle_no=?, member_id=?, recipe_name=?, recipe_type=?,recipe_ingredient=?, recipe_step=?, recipe_photo=?, cook_time=?, calo_intake=?, salt_intake=?, protein_intake=?, fat_intake=?, carbo_intake=?, vitamin_b=?, vitamin_c=?, vage_intake=?, recipe_content=? WHERE recipe_id = ?";
	private static final String CHEFCOOKEDNUM = "SELECT COUNT(1) FROM RECIPE WHERE member_id = ?";
	private static final String CHEFCOOKED = "SELECT * FROM RECIPE WHERE member_id = ? AND recipe_status = 4";
	private static final String CHANGESTATUS = "UPDATE RECIPE SET recipe_status=? where recipe_id = ?";
	private static final String GET_ALL_FOR_FRONT = "SELECT recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake, recipe_content FROM RECIPE  where recipe_status = 4 order by recipe_id";
	private static final String GET_LATEST = "SELECT * FROM (SELECT * FROM RECIPE WHERE RECIPE_STATUS = 4 ORDER BY RECIPE_ULDATE DESC) WHERE ROWNUM = 1"; 
	private static final String GET_MOST_POPULAR = "SELECT * FROM (SELECT * FROM RECIPE WHERE RECIPE_STATUS = 4 ORDER BY REFOLLOW_NUM DESC) WHERE ROWNUM = 1";	
	private static final String UPDATE_FOLLOW_NUM = "UPDATE RECIPE SET REFOLLOW_NUM = ? WHERE RECIPE_ID = ? ";
	
	@Override

	public RecipeVO_saved getLatest() {
		RecipeVO_saved recipeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LATEST);
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
		System.out.println("DAO_IN");
		return recipeVO;

	}
	
	@Override

	public RecipeVO_saved getMostPopular() {
		RecipeVO_saved recipeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MOST_POPULAR);
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
	public Integer updateFollowNum(String recipe_id,Integer followNum) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FOLLOW_NUM);
			pstmt.setInt(1, followNum);
			pstmt.setString(2, recipe_id);
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
		return followNum;

	}
	
	@Override
	public void changeStatus(String recipe_id,Integer recipe_status) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGESTATUS);
			pstmt.setInt(1, recipe_status);
			pstmt.setString(2, recipe_id);

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

	
	
	public List<RecipeVO_saved> getAllWithTerm(Map<String, String[]> map, String orderBy) {
		List<RecipeVO_saved> list = new ArrayList<RecipeVO_saved>();
		RecipeVO_saved recipeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String tern1 = "";
		String tern2 = "";
		String sql = "";
		
		tern1 = map.get("selectedType")[0];

		if (orderBy.isEmpty()) {
			orderBy = "RECIPE_ID";
		}

		if (map.get("keyword").length == 1) {
			tern2 = "(RECIPE_NAME LIKE ('%" + map.get("keyword")[0] + "%') OR RECIPE_INGREDIENT LIKE ('%"
					+ map.get("keyword")[0] + "%'))";
		} else {
			tern2 = "(RECIPE_NAME LIKE ('%" + map.get("keyword")[0] + "%') OR RECIPE_INGREDIENT LIKE ('%"
					+ map.get("keyword")[0] + "%')";// 這裡少一個括號,為了接下來串間完後必須要再補上
			for (int i = 1; i < map.get("keyword").length; i++) {
				if (i == map.get("keyword").length - 1) {
					tern2 += " AND RECIPE_INGREDIENT LIKE ('%" + map.get("keyword")[i] + "%'))";
				} else {
					tern2 += " AND RECIPE_INGREDIENT LIKE ('%" + map.get("keyword")[i] + "%')";
				}
			}
		}
		
		sql = "SELECT * FROM RECIPE WHERE " + tern1 + " AND RECIPE_STATUS = 4 AND" + tern2 + " ORDER BY " + orderBy;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
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
				recipeVO.setIngredients_str("recipe_ingredient");
				String[] tokens = rs.getString("recipe_ingredient").split(",");
				String[] unitContainer = new String[(tokens.length) * 2];
				String[] ingredientNames = new String[tokens.length];
				String ingredientName = "";
				for (int i = 0; i < tokens.length; i++) {
					int a = 0;
					unitContainer = tokens[i].split(":");
					ingredientNames[i] = unitContainer[a];
					ingredientName += (i == tokens.length - 1) ? ingredientNames[i] : ingredientNames[i] + "、";
					a += 2;
				}
				recipeVO.setIngredients_str(ingredientName);

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

	public List<RecipeVO> getChefCooked(String member_id) {
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		RecipeVO recipeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHEFCOOKED);
			pstmt.setString(1, member_id);
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
	public Integer getChefCookedNum(String member_id) {
		Integer count = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHEFCOOKEDNUM);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipeVO 也稱為 Domain objects
				count = rs.getInt(1);

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
		return count;
	}
	

	
	@Override
    public void insert(RecipeVO recipeVO,ProductVO productVO){

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			//自增主鍵生成
			con.setAutoCommit(false);
			String cols[] = {"recipe_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);

		
			
			pstmt.setString(1, recipeVO.getRcstyle_no());
			pstmt.setString(2, recipeVO.getMember_id());
			pstmt.setString(3, recipeVO.getRecipe_name());
			pstmt.setString(4, recipeVO.getRecipe_type());
			pstmt.setString(5, recipeVO.getRecipe_ingredient());
			pstmt.setString(6, recipeVO.getRecipe_step());
			pstmt.setString(7, recipeVO.getRecipe_photo());
			pstmt.setInt(8, recipeVO.getCook_time());
			pstmt.setDouble(9, recipeVO.getCalo_intake());
			pstmt.setDouble(10, recipeVO.getSalt_intake());
			pstmt.setDouble(11, recipeVO.getProtein_intake());
			pstmt.setDouble(12, recipeVO.getFat_intake());
			pstmt.setDouble(13, recipeVO.getCarbo_intake());
			pstmt.setDouble(14, recipeVO.getVitamin_b());
			pstmt.setDouble(15, recipeVO.getVitamin_c());
			pstmt.setDouble(16, recipeVO.getVage_intake());
			pstmt.setString(17, recipeVO.getRecipe_content());

			pstmt.executeUpdate();
            String next_recipe_id=null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_recipe_id = rs.getString(1);
				System.out.println("自增主鍵值: " + next_recipe_id );
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			ProductDAO dao=new ProductDAO();
				
			productVO.setRecipe_id(next_recipe_id);
			dao.addReceipe(productVO,con);
				
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("新增食譜" + next_recipe_id +"編號"+ productVO.getRecipe_id()
					+ "商品同時被新增");
            

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
			pstmt.setString(5, recipeVO.getRecipe_ingredient());
			pstmt.setString(6, recipeVO.getRecipe_step());
			pstmt.setString(7, recipeVO.getRecipe_photo());
			pstmt.setInt(8, recipeVO.getCook_time());
			pstmt.setDouble(9, recipeVO.getCalo_intake());
			pstmt.setDouble(10, recipeVO.getSalt_intake());
			pstmt.setDouble(11, recipeVO.getProtein_intake());
			pstmt.setDouble(12, recipeVO.getFat_intake());
			pstmt.setDouble(13, recipeVO.getCarbo_intake());
			pstmt.setDouble(14, recipeVO.getVitamin_b());
			pstmt.setDouble(15, recipeVO.getVitamin_c());
			pstmt.setDouble(16, recipeVO.getVage_intake());
			pstmt.setString(17, recipeVO.getRecipe_content());

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
			pstmt.setString(5, recipeVO.getRecipe_ingredient());
			pstmt.setString(6, recipeVO.getRecipe_step());
			pstmt.setString(7, recipeVO.getRecipe_photo());
			pstmt.setInt(8, recipeVO.getCook_time());
			pstmt.setDouble(9, recipeVO.getCalo_intake());
			pstmt.setDouble(10, recipeVO.getSalt_intake());
			pstmt.setDouble(11, recipeVO.getProtein_intake());
			pstmt.setDouble(12, recipeVO.getFat_intake());
			pstmt.setDouble(13, recipeVO.getCarbo_intake());
			pstmt.setDouble(14, recipeVO.getVitamin_b());
			pstmt.setDouble(15, recipeVO.getVitamin_c());
			pstmt.setDouble(16, recipeVO.getVage_intake());
			pstmt.setString(17, recipeVO.getRecipe_content());
			pstmt.setString(18, recipeVO.getRecipe_id());
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
	public void delete(String recipe_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, recipe_id);

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
	public List<RecipeVO_saved> getAllForFrontEnd() {

		List<RecipeVO_saved> list = new ArrayList<RecipeVO_saved>();
		RecipeVO_saved recipeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FOR_FRONT);
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
				recipeVO.setIngredients_str("recipe_ingredient");
				String[] tokens = rs.getString("recipe_ingredient").split(",");
				String[] unitContainer = new String[(tokens.length) * 2];
				String[] ingredientNames = new String[tokens.length];
				String ingredientName = "";
				for (int i = 0; i < tokens.length; i++) {
					int a = 0;
					unitContainer = tokens[i].split(":");
					ingredientNames[i] = unitContainer[a];
					ingredientName += (i == tokens.length - 1) ? ingredientNames[i] : ingredientNames[i] + "、";
					a += 2;
				}
				recipeVO.setIngredients_str(ingredientName);

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


}
