package com.recipe_favorite.model;

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

public class RecipeFavoriteDAO implements RecipeFavoriteDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO RECIPE_FAVORITE (recipe_id, member_id) VALUES ( ?, ?) ";
	private static final String GET_ALL_STMT = "SELECT * FROM RECIPE_FAVORITE order by recipe_id";
	private static final String GET_ONE_FOLLOWED_STMT = "SELECT * FROM RECIPE_FAVORITE WHERE recipe_id = ?";
	private static final String GET_ONE_FOLLOWING_STMT = "SELECT * FROM RECIPE_FAVORITE WHERE member_id = ?";
	private static final String DELETE = "DELETE FROM RECIPE_FAVORITE WHERE recipe_id = ? AND member_id = ?";
	private static final String GET_ONE_FOLLOWED_COUNT = "SELECT COUNT(1) FROM RECIPE_FAVORITE WHERE recipe_id = ?";

	@Override
	public Integer getFollowedNum(String recipe_id) {

		Integer count = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FOLLOWED_COUNT);

			pstmt.setString(1, recipe_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_favoriteVO 也稱為 Domain objects
				count = rs.getInt(1);
				// Store the row in the list
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
	public void insert(RecipeFavoriteVO recipe_favoriteVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String recipe_id = recipe_favoriteVO.getRecipe_id();
			String member_id = recipe_favoriteVO.getMember_id();
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, recipe_id);
				pstmt.setString(2, member_id);

				pstmt.executeUpdate();

				// Handle any driver errors
				// Handle any SQL errors
			} catch (SQLException se) {
				// 如果新增失敗則標示PK重複可能性可高,因此可以先試著刪除
				delete(recipe_id,member_id);
				
			} catch (RuntimeException se) {
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
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

	}

	@Override
	public void delete(String recipe_id, String member_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, recipe_id);
			pstmt.setString(2, member_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public List<RecipeFavoriteVO> findFollowedByPrimaryKey(String recipe_id) {

		List<RecipeFavoriteVO> list = new ArrayList<RecipeFavoriteVO>();
		RecipeFavoriteVO recipe_favoriteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FOLLOWED_STMT);

			pstmt.setString(1, recipe_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_favoriteVO 也稱為 Domain objects
				recipe_favoriteVO = new RecipeFavoriteVO();
				recipe_favoriteVO.setRecipe_id(rs.getString("recipe_id"));
				recipe_favoriteVO.setMember_id(rs.getString("member_id"));
				list.add(recipe_favoriteVO);
				// Store the row in the list

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
	public List<RecipeFavoriteVO> findFollowingByPrimaryKey(String member_id) {

		List<RecipeFavoriteVO> list = new ArrayList<RecipeFavoriteVO>();
		RecipeFavoriteVO recipe_favoriteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FOLLOWING_STMT);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_favoriteVO 也稱為 Domain objects
				recipe_favoriteVO = new RecipeFavoriteVO();
				recipe_favoriteVO.setMember_id(rs.getString("member_id"));
				recipe_favoriteVO.setRecipe_id(rs.getString("recipe_id"));
				list.add(recipe_favoriteVO);
				// Store the row in the list

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
	public List<RecipeFavoriteVO> getAll() {
		List<RecipeFavoriteVO> list = new ArrayList<RecipeFavoriteVO>();
		RecipeFavoriteVO recipe_favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_favoriteVO 也稱為 Domain objects
				recipe_favoriteVO = new RecipeFavoriteVO();
				recipe_favoriteVO.setRecipe_id(rs.getString("recipe_id"));
				recipe_favoriteVO.setMember_id(rs.getString("member_id"));
				list.add(recipe_favoriteVO); // Store the row in the list
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
