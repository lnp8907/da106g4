package com.product.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shop.meth.CompositeQuery;




public class ProductJDBCDAO implements ProductDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";
	
	
	
	private static final String ADD_PECIPE = "INSERT INTO PRODUCT (PRODUCT_ID, RECIPE_ID,PRODUCT_TYPE, PRODUCT_PRICE, PRODUCT_STATUS) VALUES ( SQ_PRODUCT_ID.NEXTVAL, ?, '料理組合包', 0, 2)";
	private static final String INSERT_STMT = "INSERT INTO PRODUCT (PRODUCT_ID, RECIPE_ID,PRODUCT_TYPE, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_PHOTO, PRODUCT_STATUS, CARBOHYDRATE, PROTEIN, FAT, CALORIE, VITAMIN_B, VITAMIN_C,SALT,VAGETABLE ,CONTENT) VALUES ( SQ_PRODUCT_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?,?,?,?,?)";
    private static final String GET_ALL_STMT = "SELECT PRODUCT_ID, RECIPE_ID, PRODUCT_TYPE, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_PHOTO, PRODUCT_STATUS, CARBOHYDRATE, PROTEIN, FAT, CALORIE, VITAMIN_B, VITAMIN_C,SALT,VAGETABLE,CONTENT FROM PRODUCT order by PRODUCT_id";
	private static final String GET_ONE_STMT = "SELECT product_id, recipe_id, product_type, product_name, product_price, product_photo, product_status, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C,salt,vagetable,content FROM PRODUCT WHERE PRODUCT_ID = ?";
	private static final String GET_ONE_STMT_BYR = "SELECT product_id, recipe_id, product_type, product_name, product_price, product_photo, product_status, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C,salt,vagetable,content FROM PRODUCT WHERE recipe_id = ?";
	private static final String DELETE = "DELETE FROM PRODUCT WHERE product_id = ?";
	private static final String UPDATE = "UPDATE PRODUCT SET  PRODUCT_TYPE=?, PRODUCT_NAME=?, PRODUCT_PRICE=?, PRODUCT_STATUS=?, carbohydrate=?, protein=?, fat=?, calorie=?, vitamin_B=?, vitamin_C=?,CONTENT=?,product_photo=?,SALT=?,VAGETABLE=? WHERE PRODUCT_ID = ?";
	private static final String UPDATEPICTURE = "UPDATE PRODUCT SET  product_photo=?  WHERE product_id = ?";
	private static final String CHANGESTATUS="UPDATE PRODUCT SET PRODUCT_STATUS=? WHERE product_id = ?";
	private static final String IS_Product_id_fk =	"select product_id from order_detail order by product_id";
	private static final String GET_ONE_TYPE =	"SELECT PRODUCT_ID, RECIPE_ID, PRODUCT_TYPE, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_PHOTO, PRODUCT_STATUS, CARBOHYDRATE, PROTEIN, FAT, CALORIE, VITAMIN_B, VITAMIN_C,SALT,VAGETABLE,CONTENT FROM PRODUCT WHERE product_type=? order by PRODUCT_ID";
	

	@Override
	public void changestatus(String product_id,Integer product_status) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHANGESTATUS);
			pstmt.setInt(1, product_status);
			pstmt.setString(2, product_id);
			

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
	public void updatepicture(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEPICTURE);

			pstmt.setBytes(1, productVO.getProduct_photo());

			pstmt.setString(2, productVO.getProduct_id());

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
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, productVO.getRecipe_id());	
			pstmt.setString(2, productVO.getProduct_type());
			pstmt.setString(3, productVO.getProduct_name());
		
			pstmt.setInt(4, productVO.getProduct_price());
			pstmt.setBytes(5, productVO.getProduct_photo());
			pstmt.setDouble(6, productVO.getProduct_status());
			pstmt.setDouble(7, productVO.getCarbohydrate());
			
			pstmt.setDouble(8, productVO.getProtein());
			pstmt.setDouble(9, productVO.getFat());
			pstmt.setDouble(10, productVO.getCalorie());
			pstmt.setDouble(11, productVO.getVitamin_B());
			pstmt.setDouble(12, productVO.getVitamin_C());
			pstmt.setDouble(13, productVO.getSalt());
			pstmt.setDouble(14, productVO.getVagetbale());
			pstmt.setString(15, productVO.getContent());

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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getProduct_type());
			pstmt.setString(2, productVO.getProduct_name());
			pstmt.setInt(3, productVO.getProduct_price());
			
//			pstmt.setBytes(4, productVO.getProduct_photo());
			pstmt.setInt(4, productVO.getProduct_status());
			pstmt.setDouble(5, productVO.getCarbohydrate());
			pstmt.setDouble(6, productVO.getProtein());
			pstmt.setDouble(7, productVO.getFat());
			pstmt.setDouble(8, productVO.getCalorie());
			pstmt.setDouble(9, productVO.getVitamin_B());
			pstmt.setDouble(10, productVO.getVitamin_C());
//			pstmt.setDouble(11, productVO.getSalt());
//			pstmt.setDouble(12, productVO.getVagetbale());
			pstmt.setString(11, productVO.getContent());
			pstmt.setBytes(12, productVO.getProduct_photo());
			pstmt.setDouble(13, productVO.getSalt());

			pstmt.setDouble(14, productVO.getVagetbale());

			pstmt.setString(15, productVO.getProduct_id());

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
	public void delete(String product_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, product_id);

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
	public ProductVO findByPrimaryKey(String product_id) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, product_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getString("PRODUCT_ID"));
				productVO.setRecipe_id(rs.getString("RECIPE_ID"));
				productVO.setProduct_type(rs.getString("PRODUCT_TYPE"));
				productVO.setProduct_name(rs.getString("PRODUCT_NAME"));
				productVO.setProduct_price(rs.getInt("PRODUCT_PRICE"));
				productVO.setProduct_photo(rs.getBytes("PRODUCT_PHOTO"));
				productVO.setProduct_status(rs.getInt("PRODUCT_STATUS"));
				productVO.setCarbohydrate(rs.getDouble("CARBOHYDRATE"));
				productVO.setProtein(rs.getDouble("PROTEIN"));
				productVO.setFat(rs.getDouble("FAT"));
				productVO.setCalorie(rs.getDouble("CALORIE"));
				productVO.setVitamin_B(rs.getDouble("VITAMIN_B"));
				productVO.setVitamin_C(rs.getDouble("VITAMIN_c"));
				productVO.setContent(rs.getString("CONTENT"));
				productVO.setSalt(rs.getDouble("SALT"));
				productVO.setVagetbale(rs.getDouble("VAGETABLE"));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getString("product_id"));
				productVO.setRecipe_id(rs.getString("recipe_id"));
				productVO.setProduct_type(rs.getString("product_type"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_photo(rs.getBytes("product_photo"));
				productVO.setProduct_status(rs.getInt("product_status"));
				productVO.setCarbohydrate(rs.getDouble("carbohydrate"));
				productVO.setProtein(rs.getDouble("protein"));
				productVO.setFat(rs.getDouble("fat"));
				productVO.setCalorie(rs.getDouble("calorie"));
				productVO.setVitamin_B(rs.getDouble("vitamin_B"));
				productVO.setVitamin_C(rs.getDouble("vitamin_C"));
				productVO.setContent(rs.getString("content"));
				list.add(productVO);

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	//測試啦資料啦
	public static void main(String[] args) throws IOException {
		ProductJDBCDAO a=new ProductJDBCDAO();
//		List<ProductVO> list = new ArrayList();
//		String product_type="水果類";
//		list=a.gettypelist(product_type);
//		for(ProductVO z:list) {
//			System.out.println("商品ID:"+z.getProduct_id()+"/n"+"商品類型:"+z.getProduct_type()+"/n"+"品名:"+z.getProduct_name());
//		}
//		Set <ProductVO> list=new HashSet<ProductVO>();
//		list=a.GetRecripeList(1);
//		for(ProductVO s:list) {
//			System.out.println("商品ID:"+s.getProduct_id()+"品名:"+s.getProduct_name());
//		}

		
		
//		
//		List<ProductVO> list = new ArrayList();		
//		list=dao.getAll();
//		for(ProductVO s:list) {
//			System.out.println(s.getProduct_id());
//			System.out.println("************************-----------");
//		}
				//		// -------------------------------
//				ProductVO vo=dao.findByPrimaryKey("320006");
//				dao.changestatus(vo);
//				System.out.println(vo.getProduct_id());
//				System.out.println(vo.getProduct_status());
		
//		for(int i=1;i<61;i++) {
//			String k = "";
//			if(i<10) {
//				k+="0"+i;
//			}
//			else {
//				k+=i+"";
//			}
//			String a=System.getProperty("user.dir");	
//
//			a+="\\WebContent\\product\\picture";
//			a+="\\"+i+".jpg";
//			byte[] pic = getPictureByteArray(a);
//			ProductVO daoT02=new ProductVO();
//			daoT02.setProduct_id("3200"+k);
//			System.out.println(i);
//
//			System.out.println(k);
//
//			k="";
//			daoT02.setProduct_photo(pic);
//			System.out.println();
//			dao.updatepicture(daoT02);
//		}
//		
//	}
//	 static	public List <ProductVO> list() throws IOException {
//			int c=1;
//			int pc=1;
//			ProductJDBCDAO productdao = new ProductJDBCDAO();
//			List<ProductVO> list=new ArrayList<ProductVO>();
//			
//			ProductVO p011 = productdao.findByPrimaryKey("320003");
//			System.out.println(p011.getProduct_name());
//			p011.setProduct_id("320003");			
//			p011.setProduct_type("調味料");
//			p011.setProduct_name("牛頭牌沙茶醬 500g");
//			p011.setProduct_price(155505);
//			p011.setProduct_status(0);
//			p011.setCarbohydrate(1.0);
//			p011.setProtein(0.9);
//			p011.setSalt(0.5);
//			p011.setFat(7.0);
//			p011.setCalorie(71.0);
//			p011.setVitamin_B(0.1);
//			p011.setVitamin_C(0.1);
//			p011.setVagetbale(1.1);
//			p011.setContent("★沾水餃、配火鍋都美味UP! UP!!\r\n" + 
//					"★炒牛肉、炒三鮮甚至炒飯，都可以讓您美味滿桌喔！\r\n" + 
//					"★牛頭牌沙茶醬滷豬肉、滷牛肉、滷豆乾絕佳好滋味！");
//			productdao.update(p011);
			
//			list.add(p011);
//			///////////////////////////////////////////
//			ProductVO p012 = new ProductVO();
//			p012.setRecipe_id(null);
//			p012.setProduct_type("調味料");
//			p012.setProduct_name("大吟釀薄鹽醬油");
//			p012.setProduct_price(49);
//			p012.setProduct_status(0);
//			
//			p012.setCalorie(89.5);
//			p012.setProtein(7.78);
//			p012.setFat(0.0);
//			p012.setCarbohydrate(14.72);
//			p012.setVitamin_B(0.01);
//			p012.setVitamin_C(0.0);
//			p012.setContent("運用「大吟釀」等級的技術與設備，歷經數月釀造熟成，並減少15%用鹽進行發酵擺脫過去傳統醬油死鹹的印象，少了鹽份多了健康，口感純淨甘醇，用於簡單的蔬食料理，更能襯托食材的風味。");
//			list.add(p012);
//			///////////////////////////////////////////
//			ProductVO p021 = new ProductVO();
//			p021.setRecipe_id(null);
//			p021.setProduct_type("果醬");
//			p021.setProduct_name("義美草莓果粒醬");
//			p021.setProduct_price(155);
//			p021.setProduct_status(0);
//
//			p021.setCalorie(270.14);
//			p021.setProtein(0.35);
//			p021.setFat(1.21);
//			p021.setCarbohydrate(64.97);
//			p021.setVitamin_B(0.01);
//			p021.setVitamin_C(0.0);
//			p021.setContent("義美果粒醬可搭配土司、麵包、優格、冰淇淋、鬆餅等食用， 酸甜好滋味；亦可加入冰水或紅茶作成果汁或果茶，冷熱皆宜； 豐富多變的食用方式，是您自由搭配的好幫手。");
//			list.add(p021);
//			///////////////////////////////////////////
//			ProductVO p022 = new ProductVO();
//			p022.setRecipe_id(null);
//			p022.setProduct_type("果醬");
//			p022.setProduct_name("檸檬卡士達果醬");
//			p022.setProduct_price(79);
//			p022.setProduct_status(0);
//
//			p022.setCalorie(30.4);
//			p022.setProtein(0.81);
//			p022.setFat(0.95);
//			p022.setCarbohydrate(7.61);
//			p022.setVitamin_B(0.01);
//			p022.setVitamin_C(0.02);
//			p022.setContent("富含果皮及果肉在其中，連同果皮慢火熬煮，味道香濃!\r\n" + 
//					"選用蘇格蘭高品質的水果製成，自然香甜!\r\n" + 
//					"銅金色的罐蓋是梅凱最優品質的標幟!\r\n" + 
//					"遵循英國古法製成，不含人工色素及防腐劑！\r\n" + 
//					"梅凱果醬系列皆通過Kosher認證!\r\n" + 
//					"無麩質，全素食者可食!\r\n" + 
//					"迷你小巧罐，讓你簡單嚐鮮，不負擔！");
//			list.add(p022);
//			///////////////////////////////////////////
//			ProductVO p031= new ProductVO();
//			p031.setRecipe_id(null);
//			p031.setProduct_type("麵粉");
//			p031.setProduct_name("小麥好好小麥麵粉");
//			p031.setProduct_price(140);
//			p031.setProduct_status(0);
//
//			p031.setCalorie(364.01);
//			p031.setProtein(12.51);
//			p031.setFat(1.21);
//			p031.setCarbohydrate(75.4);
//			p031.setVitamin_B(0.01);
//			p031.setVitamin_C(0.01);
//			p031.setContent("無添加、種植過程無使用農藥、除草劑的本土小麥白麵粉，來自嘉義東石的小麥，委託專業麵粉廠研磨，可用於麵包、餅乾、中式麵點。");
//			list.add(p031);
//			///////////////////////////////////////////
//			ProductVO p032= new ProductVO();
//			p032.setRecipe_id(null);
//			p032.setProduct_type("麵粉");
//			p032.setProduct_name("麥典工坊麵包專用粉");
//			p032.setProduct_price(100);
//			p032.setProduct_status(0);
//
//			p032.setCalorie(332.01);
//			p032.setProtein(11.14);
//			p032.setFat(1.25);
//			p032.setCarbohydrate(73.41);
//			p032.setVitamin_B(0.01);
//			p032.setVitamin_C(0.01);
//			p032.setContent("★純粹小麥，不含任何改良添加物\r\n" + 
//					"★專為家用烘焙開發，適合家用攪拌機、製麵包機或手揉\r\n" + 
//					"★雙重保鮮包裝");
//			list.add(p032);
//			///////////////////////////////////////////
//			ProductVO p041= new ProductVO();
//			p041.setRecipe_id(null);
//			p041.setProduct_type("酒");
//			p041.setProduct_name("紅高粱檸檬酒");
//			p041.setProduct_price(790);
//			p041.setProduct_status(0);
//
//			p041.setCalorie(133.88);
//			p041.setProtein(0.14);
//			p041.setFat(0.00);
//			p041.setCarbohydrate(14.18);
//			p041.setVitamin_B(0.00);
//			p041.setVitamin_C(0.00);
//			p041.setContent("這款「紅高粱檸檬酒」，特別選用與屏東農場契作、品質最佳的青檸檬製作，經過清洗、風乾處理後，再進行釀酒程序。除了以裕豐釀業的優質紅高粱酒為基底，加入檸檬進行浸泡外，還需與檸檬自然發酵的檸檬酒混釀，所有商品釀製過程長達一年以上！");
//			list.add(p041);
//			///////////////////////////////////////////
//			ProductVO p042= new ProductVO();
//			p042.setRecipe_id(null);
//			p042.setProduct_type("酒");
//			p042.setProduct_name("普羅旺斯裘布粉紅酒");
//			p042.setProduct_price(875);
//			p042.setProduct_status(0);
//
//			p042.setCalorie(156.25);
//			p042.setProtein(0.00);
//			p042.setFat(0.00);
//			p042.setCarbohydrate(19.5);
//			p042.setVitamin_B(0.00);
//			p042.setVitamin_C(0.00);
//			p042.setContent("\r\n" + 
//					"早晨手工採收葡萄果實，大多數的葡萄直接榨汁後進入溫控不銹鋼槽釀造。浪漫優雅的淡粉色，鮮摘草莓與花香散發出夢幻般細緻香氣，味蕾充滿了覆盆子與野莓、柑橘等果香還有野生植物、石頭的風土氣息，清爽的酸度，最後展現出圓潤持久且帶有辛香料與香草氣息的尾韻。");
//			list.add(p042);
//			///////////////////////////////////////////
//			for(ProductVO p:list) {
//				if(pc==3) {
//					c++;
//					pc=1;
//				}
//			
//				String a=System.getProperty("user.dir");	
//				a+="\\WebContent\\product";
//				a+="\\P00"+c+"\\P00"+c+"_"+pc+".jpg";
//				byte[] pic = getPictureByteArray(a);
//				p.setProduct_photo(pic);
//				productdao.insert(p);
//				pc++;
//			}
//			return list;
//			
		}
	 public static byte[] getPictureByteArray(String path) throws IOException {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int i;
			while ((i = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			baos.close();
			fis.close();
			return baos.toByteArray();
		}
	@Override
	public boolean isproductid(String productid) {
		List<ProductVO> list = new ArrayList();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = true;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(IS_Product_id_fk);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			if(rs.getString("product_id").equals(productid)) {
				result= false;
			}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		if(result==false) {
			return false;
		}
		else {
			return true;
			
		}
	}
	@Override
	public List<ProductVO> gettypelist(String product_type) {
		List<ProductVO> list = new ArrayList<ProductVO>();

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_TYPE);

			pstmt.setString(1, product_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getString("product_id"));
				productVO.setRecipe_id(rs.getString("recipe_id"));
				productVO.setProduct_type(rs.getString("product_type"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_photo(rs.getBytes("product_photo"));
				productVO.setProduct_status(rs.getInt("product_status"));
				productVO.setCarbohydrate(rs.getDouble("carbohydrate"));
				productVO.setProtein(rs.getDouble("protein"));
				productVO.setFat(rs.getDouble("fat"));
				productVO.setCalorie(rs.getDouble("calorie"));
				productVO.setVitamin_B(rs.getDouble("vitamin_B"));
				productVO.setVitamin_C(rs.getDouble("vitamin_C"));
				productVO.setContent(rs.getString("content"));
				list.add(productVO);

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public Set<ProductVO> GetRecripeList() {
		
		Set<ProductVO> list = new LinkedHashSet<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(rs.getString("recipe_id")!=null) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getString("product_id"));
				productVO.setRecipe_id(rs.getString("recipe_id"));
				productVO.setProduct_type(rs.getString("product_type"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_photo(rs.getBytes("product_photo"));
				productVO.setProduct_status(rs.getInt("product_status"));
				productVO.setCarbohydrate(rs.getDouble("carbohydrate"));
				productVO.setProtein(rs.getDouble("protein"));
				productVO.setFat(rs.getDouble("fat"));
				productVO.setCalorie(rs.getDouble("calorie"));
				productVO.setVitamin_B(rs.getDouble("vitamin_B"));
				productVO.setVitamin_C(rs.getDouble("vitamin_C"));
				productVO.setContent(rs.getString("content"));
				list.add(productVO);
				}

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public Integer getlistzize(Collection list) {
		Integer size=list.size();
		return size;
	}
	@Override
	public List<ProductVO> gettypelist(String product_type, Integer product_status) {
System.out.print("獲得類型為"+product_type+"的商品");
		
		if(product_status==0) {
			System.out.println("且已上架");
		}
		else if(product_status==1) {
System.out.println("且未上架");
		}
		else {
			System.out.println("請勿搜尋1以上的數字"+"自動轉換0-上架搜尋");
			product_status=0;
			
		}
		List<ProductVO> list = new ArrayList<ProductVO>();

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		  String GET_ONE_TYPEby_status =	"SELECT PRODUCT_ID, RECIPE_ID, PRODUCT_TYPE, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_PHOTO, PRODUCT_STATUS, CARBOHYDRATE, PROTEIN, FAT, CALORIE, VITAMIN_B, VITAMIN_C,SALT,VAGETABLE,CONTENT FROM PRODUCT"
				  +" WHERE product_status="+ product_status
		  		+ " and product_type=? order by PRODUCT_ID";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_TYPEby_status);

			pstmt.setString(1, product_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getString("product_id"));
				productVO.setRecipe_id(rs.getString("recipe_id"));
				productVO.setProduct_type(rs.getString("product_type"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_photo(rs.getBytes("product_photo"));
				productVO.setProduct_status(rs.getInt("product_status"));
				productVO.setCarbohydrate(rs.getDouble("carbohydrate"));
				productVO.setProtein(rs.getDouble("protein"));
				productVO.setFat(rs.getDouble("fat"));
				productVO.setCalorie(rs.getDouble("calorie"));
				productVO.setVitamin_B(rs.getDouble("vitamin_B"));
				productVO.setVitamin_C(rs.getDouble("vitamin_C"));
				productVO.setContent(rs.getString("content"));
				list.add(productVO);

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		if(list.size()<=0) {
			System.out.println("無符合查詢");
		}
		return list;
	}
	@Override
	public Set<ProductVO> GetRecripeList(Integer product_status) {
		String GET_ALL_RECEIPEbySTATUS = "SELECT PRODUCT_ID, RECIPE_ID, PRODUCT_TYPE, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_PHOTO, PRODUCT_STATUS, CARBOHYDRATE, PROTEIN, FAT, CALORIE, VITAMIN_B, VITAMIN_C,SALT,VAGETABLE,CONTENT FROM PRODUCT "
				  +" WHERE product_status="+ product_status
				+ "order by PRODUCT_id";
		
System.out.print("獲取食譜清單");
		
		if(product_status==0) {
			System.out.println("且已上架");
		}
		else if(product_status==1) {
System.out.println("且未上架");
		}
		else {
			System.out.println("請勿搜尋1以上的數字"+"自動轉換0-上架搜尋");
			product_status=0;
			
		}
		
		Set<ProductVO> list = new LinkedHashSet<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_RECEIPEbySTATUS);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(rs.getString("recipe_id")!=null) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getString("product_id"));
				productVO.setRecipe_id(rs.getString("recipe_id"));
				productVO.setProduct_type(rs.getString("product_type"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_photo(rs.getBytes("product_photo"));
				productVO.setProduct_status(rs.getInt("product_status"));
				productVO.setCarbohydrate(rs.getDouble("carbohydrate"));
				productVO.setProtein(rs.getDouble("protein"));
				productVO.setFat(rs.getDouble("fat"));
				productVO.setCalorie(rs.getDouble("calorie"));
				productVO.setVitamin_B(rs.getDouble("vitamin_B"));
				productVO.setVitamin_C(rs.getDouble("vitamin_C"));
				productVO.setContent(rs.getString("content"));
				list.add(productVO);
				}

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		if(list.size()<=0) {
			System.out.println("無符合查詢");
		}
		return list;
	
	}
	//指定上架或下架
	@Override
    public List<ProductVO> getAll(Map<String, String[]> map,Integer product_status)
{
	List<ProductVO>list=new ArrayList<ProductVO>();
	ProductVO productVO=null;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		String finalSQL = "select * from product "
		          + CompositeQuery.get_WhereCondition(map,product_status)
		          + "order by product_id";
		pstmt = con.prepareStatement(finalSQL);
		System.out.println("SQL語法為:  "+finalSQL);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setProduct_id(rs.getString("product_id"));
			productVO.setRecipe_id(rs.getString("recipe_id"));
			productVO.setProduct_type(rs.getString("product_type"));
			productVO.setProduct_name(rs.getString("product_name"));
			productVO.setProduct_price(rs.getInt("product_price"));
			productVO.setProduct_photo(rs.getBytes("product_photo"));
			productVO.setProduct_status(rs.getInt("product_status"));
			productVO.setCarbohydrate(rs.getDouble("carbohydrate"));
			productVO.setProtein(rs.getDouble("protein"));
			productVO.setFat(rs.getDouble("fat"));
			productVO.setCalorie(rs.getDouble("calorie"));
			productVO.setVitamin_B(rs.getDouble("vitamin_B"));
			productVO.setVitamin_C(rs.getDouble("vitamin_C"));
			productVO.setContent(rs.getString("content"));
			list.add(productVO);
			
			}
		
		for(ProductVO a:list) {
			System.out.println("獲取"+a.getProduct_name());
		}
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
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
	public ProductVO byRecipe(String recipe_id) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_BYR);

			pstmt.setString(1, recipe_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getString("PRODUCT_ID"));
				productVO.setRecipe_id(rs.getString("RECIPE_ID"));
				productVO.setProduct_type(rs.getString("PRODUCT_TYPE"));
				productVO.setProduct_name(rs.getString("PRODUCT_NAME"));
				productVO.setProduct_price(rs.getInt("PRODUCT_PRICE"));
				productVO.setProduct_photo(rs.getBytes("PRODUCT_PHOTO"));
				productVO.setProduct_status(rs.getInt("PRODUCT_STATUS"));
				productVO.setCarbohydrate(rs.getDouble("CARBOHYDRATE"));
				productVO.setProtein(rs.getDouble("PROTEIN"));
				productVO.setFat(rs.getDouble("FAT"));
				productVO.setCalorie(rs.getDouble("CALORIE"));
				productVO.setVitamin_B(rs.getDouble("VITAMIN_B"));
				productVO.setVitamin_C(rs.getDouble("VITAMIN_c"));
				productVO.setContent(rs.getString("CONTENT"));
				productVO.setSalt(rs.getDouble("SALT"));
				productVO.setVagetbale(rs.getDouble("VAGETABLE"));
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
		return productVO;
	}
	@Override
	public void addReceipe(ProductVO productvo, Connection con) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changestatus(String product_id, Integer product_status, Integer price) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<ProductVO> getAllExceprRecipe() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ProductVO> getAllExceprRecipe(Integer product_status) {
		// TODO Auto-generated method stub
		return null;
	}
	
}