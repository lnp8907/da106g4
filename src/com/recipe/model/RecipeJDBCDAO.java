package com.recipe.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class RecipeJDBCDAO implements RecipeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = "INSERT INTO RECIPE (recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake, recipe_content) VALUES ( sq_recipe_id.NEXTVAL,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = "SELECT recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake, recipe_content FROM RECIPE order by recipe_id";
	private static final String GET_ONE_STMT = "SELECT recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate, recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake, recipe_content FROM RECIPE WHERE recipe_id = ?";
	private static final String DELETE = "DELETE FROM RECIPE WHERE recipe_id = ?";
	private static final String UPDATE = "UPDATE RECIPE SET rcstyle_no=?, member_id=?, recipe_name=?, recipe_type=?, recipe_status=?, refollow_num=?, recipe_uldate=?, recipe_ingredient=?, recipe_step=?, recipe_photo=?, cook_time=?, calo_intake=?, salt_intake=?, protein_intake=?, fat_intake=?, carbo_intake=?, vitamin_b=?, vitamin_c=?, vage_intake=?, recipe_content=? WHERE recipe_id = ?";

	@Override
	public void insert(RecipeVO recipeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			pstmt.setString(20, recipeVO.getRecipe_content());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(RecipeVO recipeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

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
			pstmt.setDouble(12, recipeVO.getCarbo_intake());
			pstmt.setDouble(13, recipeVO.getSalt_intake());
			pstmt.setDouble(14, recipeVO.getProtein_intake());
			pstmt.setDouble(15, recipeVO.getFat_intake());
			pstmt.setDouble(16, recipeVO.getCarbo_intake());
			pstmt.setDouble(17, recipeVO.getVitamin_b());
			pstmt.setDouble(18, recipeVO.getVitamin_c());
			pstmt.setDouble(19, recipeVO.getVage_intake());
			pstmt.setString(20, recipeVO.getRecipe_content());
			pstmt.setString(21, recipeVO.getRecipe_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String recipe_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, recipe_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	
	public RecipeVO_saved findByPrimaryKeyForSaved(String recipe_id) {
		RecipeVO_saved recipeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			return recipeVO;
	}
	

	@Override
	public RecipeVO findByPrimaryKey(String recipe_id) {

		RecipeVO recipeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				recipeVO.setRecipe_content(rs.getString("recipe_content"));

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	public List<RecipeVO_saved> addAllIngredientsStr() {

		List<RecipeVO_saved> list = new ArrayList<RecipeVO_saved>();
		RecipeVO_saved recipeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
	


	public static void main(String[] args) throws UnsupportedEncodingException {
		RecipeJDBCDAO dao = new RecipeJDBCDAO();
//		final Base64 base64 = new Base64();
//		final String encodedText = base64.encodeToString(dao.getPic("image/recipe/7.jpg"));
//		final String encodedText1 = base64.encodeToString(dao.getPic("image/recipe/1.jpg"));

		// 解碼
//		System.out.println(new String(base64.decode(encodedText), "UTF-8"));

//		RecipeJDBCDAO dao = new RecipeJDBCDAO();
//		DecimalFormat df= new DecimalFormat("#.#");
//		RecipeVO recipe01 = new RecipeVO();
//		
//		//*
//		String rs = "台式";
//		switch (rs) {case "中式":rs = "520000";break;case "台式":rs = "520001";break;case "義式":rs = "520002";	break;case "法式":rs = "520003";break;case "韓式":rs = "520004";break;	case "泰式":rs = "520005";break;case "日式":rs = "520006";	break;}
//		
//		// 新增
//		//*
//		recipe01.setRecipe_name("豆花黑蜂蜜");
//		//*
//		recipe01.setRecipe_type("點心");
//		//*
//		recipe01.setRecipe_content("由豆漿和明膠固化而成的易大豆花。撒上大量含有很多礦物質的黑蜂蜜。可以充分享受豆漿原味的料理。");
//		//*
//		recipe01.setRecipe_step("將水，瓊脂粉和糖粉放入鍋中，煮沸後加熱1分鐘。*（1）將豆漿和杏仁精華混合。*去除粗熱後，將其轉移到存儲容器中，然後在冰箱中冷卻。*結合黑蜂蜜的成分，煮至濃稠。*將（3）放在碗中，蓋上黑蜂蜜。");
//		//*
//		recipe01.setRecipe_ingredient("豆漿:250ml,水:150ml,粉末瓊脂:1克（1/2茶匙）,糖:2湯匙,杏仁精華:5滴,黑蜂蜜:1/2茶匙,紅糖:50g");
//		//*
//
//		
//		recipe01.setRcstyle_no(rs);
//		recipe01.setRecipe_status(4);
//		recipe01.setRefollow_num((int)(Math.random()*1001));
//		recipe01.setMember_id("81000" + (int)(Math.random()*6));
//		recipe01.setRecipe_uldate(java.sql.Date.valueOf("20" + (int)(Math.random()*2 + 18) + "-0" + (int)(Math.random()*9+1) + "-" + (int)(Math.random()*3) + (int)(Math.random()*8+1)));
//		recipe01.setRecipe_photo(null);
//		recipe01.setCook_time(((int)(Math.random()*9)+2)*5); //烹煮時間
//		recipe01.setCalo_intake(new Double(0).valueOf((df.format((Math.random()*300)+50)))); //卡洛里
//		recipe01.setSalt_intake(new Double(0).valueOf((df.format((Math.random()*1)+1)))); //食鹽
//		recipe01.setProtein_intake(new Double(0).valueOf((df.format((Math.random()*4)+1)))); //蛋白質 たんぱく質
//		recipe01.setFat_intake(new Double(0).valueOf((df.format((Math.random()*10)+10)))); //脂質
//		recipe01.setCarbo_intake(new Double(0).valueOf((df.format((Math.random()*5)+5)))); //碳水化物
//		recipe01.setVitamin_b(new Double(0).valueOf((df.format((Math.random()*1))))); //維他命B ビタミンB
//		recipe01.setVitamin_c(new Double(0).valueOf((df.format((Math.random()*10)+8)))); //維他命C ビタミンC
//		recipe01.setVage_intake(new Double(0).valueOf((df.format((Math.random()*30)+1)))); //食物繊維
//		dao.insert(recipe01);
//		recipe01.setRecipe_id("510011");
//		dao.update(recipe01);

		// 修改
//		RecipeVO recipe02 = new RecipeVO();
//		recipe02.setRecipe_id("510030");
//		recipe02.setRcstyle_no("520002");
//		recipe02.setMember_id("C0001");
//		recipe02.setRecipe_name("打爆韓國魚");
//		recipe02.setRecipe_type("小丑");
//		recipe02.setRecipe_status(4);
//		recipe02.setRefollow_num(-999);
//		recipe02.setRecipe_uldate(java.sql.Date.valueOf("2020-02-02"));
//		recipe02.setRecipe_ingredient("我現在要出征,現在要出征~");
//		recipe02.setRecipe_step("罷免所有");
//		recipe02.setRecipe_photo(dao.getPic("image/tandersai.jpg"));
//		recipe02.setCook_time(45);
//		recipe02.setCalo_intake(68.0);
//		recipe02.setSalt_intake(6.5);
//		recipe02.setProtein_intake(55.1);
//		recipe02.setFat_intake(8.6);
//		recipe02.setCarbo_intake(9.9);
//		recipe02.setVitamin_b(7.7);
//		recipe02.setVitamin_c(4.5);
//		recipe02.setVage_intake(1.2);
//		recipe02.setRecipe_content("Hello,World");
//		
//		dao.update(recipe02);

		// 刪除
//		dao.delete("510007");

		// 查詢
//		RecipeVO recipe03 = dao.findByPrimaryKey("510030");
//		System.out.print(recipe03.getRecipe_id() + ",");
//		System.out.print(recipe03.getRcstyle_no() + ",");
//		System.out.print(recipe03.getMember_id() + ",");
//		System.out.print(recipe03.getRecipe_name() + ",");
//		System.out.print(recipe03.getRecipe_type() + ",");
//		System.out.print(recipe03.getRecipe_status() + ",");
//		System.out.print(recipe03.getRefollow_num() + ",");
//		System.out.print(recipe03.getRecipe_uldate() + ",");
//		System.out.print(recipe03.getRecipe_ingredient() + ",");
//		System.out.print(recipe03.getRecipe_step() + ",");
//		System.out.print(recipe03.getRecipe_photo() + ",");
//		System.out.print(recipe03.getCook_time() + ",");
//		System.out.print(recipe03.getCarbo_intake() + ",");
//		System.out.print(recipe03.getSalt_intake() + ",");
//		System.out.print(recipe03.getProtein_intake() + ",");
//		System.out.print(recipe03.getFat_intake() + ",");
//		System.out.print(recipe03.getCarbo_intake() + ",");
//		System.out.print(recipe03.getVitamin_b() + ",");
//		System.out.print(recipe03.getVitamin_c() + ",");
//		System.out.print(recipe03.getVage_intake() + ",");
//		System.out.print(aRecipe.getRecipe_content() + ",");
//		System.out.println();
//		System.out.println("--------------------------------------------------------------------------------");

		// 查詢所有
//		List<RecipeVO> list = dao.getAll();
//		for (RecipeVO aRecipe : list) {
//			System.out.print(aRecipe.getRecipe_id() + ",");
//			System.out.print(aRecipe.getRcstyle_no() + ",");
//			System.out.print(aRecipe.getMember_id() + ",");
//			System.out.print(aRecipe.getRecipe_name() + ",");
//			System.out.print(aRecipe.getRecipe_type() + ",");
//			System.out.print(aRecipe.getRecipe_status() + ",");
//			System.out.print(aRecipe.getRefollow_num() + ",");
//			System.out.print(aRecipe.getRecipe_uldate() + ",");
//			System.out.print(aRecipe.getRecipe_ingredient() + ",");
//			System.out.print(aRecipe.getRecipe_step() + ",");
//			System.out.print(aRecipe.getRecipe_photo() + ",");
//			System.out.print(aRecipe.getCook_time() + ",");
//			System.out.print(aRecipe.getCarbo_intake() + ",");
//			System.out.print(aRecipe.getSalt_intake() + ",");
//			System.out.print(aRecipe.getProtein_intake() + ",");
//			System.out.print(aRecipe.getFat_intake() + ",");
//			System.out.print(aRecipe.getCarbo_intake() + ",");
//			System.out.print(aRecipe.getVitamin_b() + ",");
//			System.out.print(aRecipe.getVitamin_c() + ",");
//			System.out.print(aRecipe.getVage_intake() + ",");
//			System.out.print(aRecipe.getRecipe_content() + ",");
//			System.out.println();
//		}

		// 切所有資料的字串
//		List list = dao.getAllForSpit();
//		int i = 0;
//		String[] tokens2 = new String[list.size()];
//		for (Object aRecipe : list) {
//			tokens2[i] = ((RecipeVO)aRecipe).getRecipe_ingredient();
//			i++;
//		}
//		String[][] tokens = new String[list.size()][];
//		String[] ingredients = new String[list.size()];
//		for (int j = 0; j < list.size(); j++) {
//			tokens[j] = tokens2[j].split(",");
//			String[] ingredientContainer = new String[(tokens[j].length) * 2];
//			String[] ingredientName = new String[tokens[j].length];
//			String temp = "";
//			for (int j2 = 0; j2 < tokens[j].length; j2++) {
//				int a = 0;
//				ingredientContainer = tokens[j][j2].split(":");
//				ingredientName[j2] = ingredientContainer[a];
//				if (j2 == tokens[j].length - 1) {
//					temp += ingredientName[j2];
//				} else {
//					temp += ingredientName[j2] + "、";
//				}
//				a += 2;
//			}
//			ingredients[j] = temp;
//		}
		
		
		//切割單筆資料 取出名稱
//		RecipeVO recipe03 = dao.findByPrimaryKey("510047");
//		String[] tokens = recipe03.getRecipe_ingredient().split(",");
//		String[] unitContainer = new String[(tokens.length) * 2];
//		String[] ingredientNames = new String[tokens.length];
//		String[] ingredientNums = new String[tokens.length];
//		String ingredientName = "";
//		for (int i = 0; i < tokens.length; i++) {
//			int a = 0, b = 0;
//			unitContainer = tokens[i].split(":");
//			ingredientNames[i] = unitContainer[a];
//			ingredientNums[i] = unitContainer[++a];
//			ingredientName+= (i==tokens.length-1)? ingredientNames[i]:ingredientNames[i]+"、";
//			a++;
//		}
		
//		RecipeVO_saved recipe03 = dao.findByPrimaryKeyForSaved("510037");
//		String[] tokens = recipe03.getRecipe_ingredient().split(",");
//		String[] unitContainer = new String[(tokens.length) * 2];
//		String[] unitContainer2 = new String[(tokens.length) * 2];
//		String[] ingredientName = new String[tokens.length];
//		String[] ingredientNums = new String[tokens.length];
//		String[] ingredientNum = new String[tokens.length];
//		String[] ingredientUnit = new String[tokens.length];
//		for (int i = 0; i < tokens.length; i++) {
//			int a = 0, b = 0;
//			unitContainer = tokens[i].split(":");
//			ingredientName[i] = unitContainer[a];
//			ingredientNums[i] = unitContainer[++a];
//			unitContainer2 = ingredientNums[i].split("/");
//			ingredientNum[i] = unitContainer2[b];
//			ingredientUnit[i] = unitContainer2[++b];
//			a++;
//			b++;
//			System.out.print(ingredientName[i]);
//			System.out.print(ingredientNum[i]);
//			System.out.print(ingredientUnit[i]);
//		}
//		
//
//		//字串切割步驟
//		String[] recipe_steps = recipe03.getRecipe_step().split("/");
//	for (int j = 0; j < recipe_steps.length; j++) {
//		
//		System.out.print(recipe_steps[j]);
//	}

		String str = "手作豆腐(高湯醬油風味)/雞肉真薯揚/京風鹽麹燉野菜/黑豆炊飯/蔬菜白和/抹茶芭芭露亞|手作豆腐：以豆漿自製豆腐，並自製鰹魚醬油搭配。/雞肉真薯揚: 雞絞肉製作丸子，外頭裹上五色粉油炸。/京風鹽麴燉野菜：可學習用皮製作雞肉福袋。/野菜白和: 白和即為白芝麻豆腐醬，與蔬菜拌勻做成涼拌菜。/抹茶芭芭露亞：學習吉利丁的使用方式";
		String[] course_detail1;
		String[] course_detail2;
		String[] token = str.split("：");
		course_detail1 = token[0].split("/");
		course_detail2 = token[1].split("/");
		for(int i= 0; i<token.length;i++) {
			System.out.println(token[i]);
//			System.out.print(course_detail2[i]);
		}
//		System.out.print(course_detail1);
		// 切單筆資料的字串 分離單位 名稱 數量
//		RecipeVO recipe03 = dao.findByPrimaryKey("510047");
////		
////		//取的食材
//		String[] tokens = recipe03.getRecipe_ingredient().split(",");
//		String[] unitContainer = new String[(tokens.length) * 2];
//		String[] unitContainer2 = new String[(tokens.length) * 2];
//		String[] ingredientName = new String[tokens.length];
//		String[] ingredientNums = new String[tokens.length];
//		String[] ingredientNum = new String[tokens.length];
//		String[] ingredientUnit = new String[tokens.length];
//		String str = "";
//		for (int i = 0; i < tokens.length; i++) {
//			int a = 0, b = 0;
//			unitContainer = tokens[i].split(":");
//			ingredientName[i] = unitContainer[a];
//			ingredientNums[i] = unitContainer[++a];
//			unitContainer2 = ingredientNums[i].split(" ");
//			ingredientNum[i] = unitContainer2[b];
//			ingredientUnit[i] = unitContainer2[++b];
//			
//			a++;
//			b++;
//		}
////		
		
		//獲取步驟
//		String[] tokens = recipe03.getRecipe_step().split(":");
//		for (int i = 0; i < tokens.length; i++) {
//			System.out.println(tokens[i]);
//		}
		
		

	}

	@SuppressWarnings("unused")
	private byte[] getPic(String path) {
		File file = new File(path);
		byte[] pic = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			pic = new byte[fis.available()];
			while (fis.available() != 0) {
				fis.read(pic);
			}
			fis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pic;
	}
}
