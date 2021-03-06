package android.com.livestream_collection.model;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.livestream.model.LivestreamDAO;
import android.com.livestream.model.LivestreamDAOImpl;

public class Livestream_CollectionDAOImpl implements Livestream_CollectionDAO {

	/// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		private static final String INSERT_STMT = 
			"INSERT INTO livestream_collection (livestream_id,member_id) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT livestream_id,member_id FROM livestream_collection order by livestream_id";
		private static final String GET_ONE_STMT = //get lsid by member_id
			"SELECT * FROM livestream_collection where member_id = ?";
		private static final String DELETE = 
			"DELETE FROM livestream_collection where livestream_id = ?";
		private static final String UPDATE = 
				"UPDATE livestream_collection set member_id=? where livestream_id = ?";
	@Override
	public void insert(Livestream_Collection livestream_CollectionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
//		File file = new File("./logo_phpbb.jpg"); 
//        int length = (int) file.length(); 
//        InputStream fin = new FileInputStream(file); 
//        
//        File file2 = new File("./logo_phpbb.jpg"); 
//        int length2 = (int) file2.length(); 
//        InputStream fin2 = new FileInputStream(file2); 

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, livestream_CollectionVO.getLivestream_id());
			pstmt.setString(2, livestream_CollectionVO.getMember_id());

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
	public void update(Livestream_Collection livestream_CollectionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, livestream_CollectionVO.getLivestream_id());
			pstmt.setString(2, livestream_CollectionVO.getMember_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String livestream_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, livestream_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	//@Override
	public List<Livestream_Collection> findByPrimaryKey(String member_id) {
		List<Livestream_Collection> lcList = new ArrayList<>();
		Livestream_Collection livestream_CollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			//LivestreamDAO lsDao = new LivestreamDAOImpl();
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			//String livestream_id;
			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				livestream_CollectionVO = new Livestream_Collection();
				livestream_CollectionVO.setLivestream_id(rs.getString(1));
//				livestream_CollectionVO.setMember_id(rs.getString("member_id"));
				lcList.add(livestream_CollectionVO);
				//System.out.println(livestream_CollectionVO);
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
		return lcList;
	}

	@Override
	public List<Livestream_Collection> getAll() {
		List<Livestream_Collection> list = new ArrayList<Livestream_Collection>();
		Livestream_Collection livestream_CollectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				livestream_CollectionVO = new Livestream_Collection();
				livestream_CollectionVO.setLivestream_id(rs.getString("livestream_id"));
				livestream_CollectionVO.setMember_id(rs.getString("member_id"));
				list.add(livestream_CollectionVO); // Store the row in the list
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
		return list;
	}
}