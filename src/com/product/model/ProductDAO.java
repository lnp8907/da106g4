package com.product.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

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
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
	private static final String INSERT_STMT = "INSERT INTO PRODUCT (product_id, recipe_id, product_name, product_price, product_photo, product_status, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, content, product_type) VALUES (  SQ_PRODUCT_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?,?,?)";
//	private static final String INSERT_STMT = "INSERT INTO PRODUCT (product_id, recipe_id, product_type, product_name, product_price, product_photo, product_status, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, content) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT product_id, recipe_id, product_type, product_name, product_price, product_photo, product_status, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, content FROM PRODUCT order by product_id";
	private static final String GET_ONE_STMT = "SELECT product_id, recipe_id, product_type, product_name, product_price, product_photo, product_status, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, content FROM PRODUCT WHERE product_id = ?";
	private static final String DELETE = "DELETE FROM PRODUCT WHERE product_id = ?";
	private static final String UPDATE = "UPDATE PRODUCT SET recipe_id=?, product_type=?, product_name=?, product_price=?, product_photo=?, product_status=?, carbohydrate=?, protein=?, fat=?, calorie=?, vitamin_B=?, vitamin_C=?, content=? WHERE product_id = ?";


    @Override
    public void insert(ProductVO productVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, productVO.getProduct_id());
            pstmt.setString(2, productVO.getProduct_type());
            pstmt.setString(3, productVO.getProduct_name());
            pstmt.setInt(4, productVO.getProduct_price());
            pstmt.setBytes(5, productVO.getProduct_photo());
            pstmt.setInt(6, productVO.getProduct_status());
            pstmt.setDouble(7, productVO.getCarbohydrate());
            pstmt.setDouble(8, productVO.getProtein());
            pstmt.setDouble(9, productVO.getFat());
            pstmt.setDouble(10, productVO.getCalorie());
            pstmt.setDouble(11, productVO.getVitamin_B());
            pstmt.setDouble(12, productVO.getVitamin_C());
            pstmt.setString(13, productVO.getContent());
            pstmt.executeUpdate();

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
    public void update(ProductVO productVO) {
    	Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
                pstmt.setString(1, productVO.getRecipe_id());			
	            pstmt.setString(2, productVO.getProduct_type());
	            pstmt.setString(3, productVO.getProduct_name());
	            pstmt.setInt(4, productVO.getProduct_price());
	            pstmt.setBytes(5, productVO.getProduct_photo());
	            pstmt.setInt(6, productVO.getProduct_status());
	            pstmt.setDouble(7, productVO.getCarbohydrate());
	            pstmt.setDouble(8, productVO.getProtein());
	            pstmt.setDouble(9, productVO.getFat());
	            pstmt.setDouble(10, productVO.getCalorie());
	            pstmt.setDouble(11, productVO.getVitamin_B());
	            pstmt.setDouble(12, productVO.getVitamin_C());
	            pstmt.setString(13, productVO.getContent());
	            pstmt.setString(14, productVO.getProduct_id());


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
    public void delete(String product_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,product_id);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("並未刪除 "+ se.getMessage());
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

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
	public void updatepicture(ProductVO productVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

		

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
	public boolean isproductid(String productid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProductVO> gettypelist(String product_type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashSet<ProductVO> GetRecripeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ProductVO> getAllExceprRecipe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getlistzize(Collection list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ProductVO> getAllExceprRecipe(Integer product_status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> gettypelist(String product_type, Integer product_status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ProductVO> GetRecripeList(Integer product_status) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<ProductVO> getAll(Map<String, String[]> map, Integer product_status) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void changestatus(String product_id, Integer product_status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRecipe(ProductVO productvo) {
		// TODO Auto-generated method stub
		
	}

	

	
}