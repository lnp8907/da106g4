package com.livestream_collection.model;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class LiveStream_CollectionJDBCDAO implements Livestream_CollectionDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = 
		"INSERT INTO livestream_collection (livestream_id,member_id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT livestream_id,member_id FROM livestream_collection order by livestream_id";
	private static final String GET_ONE_STMT = 
		"SELECT livestream_id,member_id FROM livestream_collection where livestream_id = ?";
	private static final String DELETE = 
		"DELETE FROM livestream_collection where livestream_id = ?";
	private static final String UPDATE = 
			"UPDATE livestream_collection set member_id=? where livestream_id = ?";

	@Override
	public void insert(Livestream_CollectionVO livestream_CollectionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, livestream_CollectionVO.getLivestream_id());
			pstmt.setString(2, livestream_CollectionVO.getMember_id());
		
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(Livestream_CollectionVO livestream_CollectionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, livestream_CollectionVO.getLivestream_id());
			pstmt.setString(2, livestream_CollectionVO.getMember_id());
			


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String livestream_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, livestream_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public Livestream_CollectionVO findByPrimaryKey(String livestream_id) {

		Livestream_CollectionVO livestream_CollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, livestream_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				
				livestream_CollectionVO = new Livestream_CollectionVO();
				livestream_CollectionVO.setLivestream_id(rs.getString("livestream_id"));
				livestream_CollectionVO.setMember_id(rs.getString("member_id"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return livestream_CollectionVO;
	}

	@Override
	public List<Livestream_CollectionVO> getAll() {
		List<Livestream_CollectionVO> list = new ArrayList<Livestream_CollectionVO>();
		Livestream_CollectionVO livestream_CollectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				
				livestream_CollectionVO = new Livestream_CollectionVO();
				livestream_CollectionVO.setLivestream_id(rs.getString("livestream_id"));
				livestream_CollectionVO.setMember_id(rs.getString("member_id"));
				list.add(livestream_CollectionVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		LiveStream_CollectionJDBCDAO dao = new LiveStream_CollectionJDBCDAO();

		// 新增
		Livestream_CollectionVO livestreamVO1 = new Livestream_CollectionVO();
		livestreamVO1.setLivestream_id("410012");
		livestreamVO1.setMember_id("810009");
		
		dao.insert(livestreamVO1);

		// 修改
//		Livestream_CollectionVO livestreamVO2 = new Livestream_CollectionVO();
//		livestreamVO2.setLivestream_id("410012");
//		livestreamVO2.setMember_id("810005");
//		
//		
//		dao.update(livestreamVO2);

		// 刪除
//		dao.delete("410000");

		// 查詢 
//		LivestreamVO livestreamVO3 = dao.findByPrimaryKey("410004");
//		System.out.print(livestreamVO3.getLivestream_id() + ",");
//		System.out.print(livestreamVO3.getMember_id() + ",");
//		
//		System.out.println("---------------------");

		// 查詢
//		List<LivestreamVO> list = dao.getAll();
//		for (LivestreamVO aLive : list) {
//			System.out.print(aLive.getLivestream_id() + ",");
//			System.out.print(aLive.getMember_id() + ",");
//			
//			System.out.println();
//		}
	}
	
}