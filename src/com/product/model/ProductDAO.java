package com.product.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

import com.shop.meth.CompositeQuery;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGESTATUS);
			pstmt.setInt(1, product_status);
			pstmt.setString(2, product_id);
			

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
	public void updatepicture(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPICTURE);

			pstmt.setBytes(1, productVO.getProduct_photo());

			pstmt.setString(2, productVO.getProduct_id());

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
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
		
			
			con = ds.getConnection();
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

			
			con = ds.getConnection();
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
		}  finally {
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, product_id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("並未刪除 " + e.getMessage());
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
			
			con = ds.getConnection();
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
			
			con = ds.getConnection();
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
	public boolean isproductid(String productid) {
		List<ProductVO> list = new ArrayList();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = true;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(IS_Product_id_fk);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			if(rs.getString("product_id").equals(productid)) {
				result= false;
			}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
			
			con = ds.getConnection();
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
			
			con = ds.getConnection();
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
	public Set<ProductVO> getAllExceprRecipe() {
		System.out.println("獲得除了料理包外的商品");
		Set<ProductVO> list = new LinkedHashSet<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(rs.getString("recipe_id")==null||rs.getString("recipe_id").equals("")||rs.getString("recipe_id").equals("null")) {
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
	
	public Set<ProductVO> getAllExceprRecipe(Integer product_status) {
		System.out.println("獲得除了料理包外的商品");
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
		 String GETStatus = "SELECT PRODUCT_ID, RECIPE_ID, PRODUCT_TYPE, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_PHOTO, PRODUCT_STATUS, CARBOHYDRATE, PROTEIN, FAT, CALORIE, VITAMIN_B, VITAMIN_C,SALT,VAGETABLE,CONTENT FROM PRODUCT"
		 		
		 		+" WHERE product_status="+ product_status+
		 		
		" order by PRODUCT_id";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(GETStatus);

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETStatus);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(rs.getString("recipe_id")==null||rs.getString("recipe_id").equals("")||rs.getString("recipe_id").equals("null")) {
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
			
			con = ds.getConnection();
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
			
			con = ds.getConnection();
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
		
		con = ds.getConnection();
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
	public void addRecipe(ProductVO productvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
		
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_PECIPE);
			pstmt.setString(1, productvo.getRecipe_id());	
			

			pstmt.executeUpdate();

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
	public ProductVO byRecipe(String recipe_id) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
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

	
}