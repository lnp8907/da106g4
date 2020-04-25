package com.livestream_notice.model;

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

public class Livestream_NoticeDAO implements Livestream_NoticeDAO_interface {

	/// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO livestream_notice (lsnotice_id,livestream_id,member_id,content,co_status) VALUES (sq_lsnotice_id.NEXTVAL, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT lsnotice_id,livestream_id,member_id,notice_time,content,co_status FROM livestream_notice order by lsnotice_id";
		private static final String GET_ONE_STMT = 
			"SELECT lsnotice_id,livestream_id,member_id,notice_time,content,co_status FROM livestream_notice WHERE lsnotice_id = ?";
		private static final String DELETE = 
			"DELETE FROM livestream_notice where livestream_id = ?";
		private static final String UPDATE = 
			"UPDATE livestream_notice set livestream_id=?, member_id=?, content=?, co_status=? where lsnotice_id = ?";

	@Override
	public void insert(Livestream_NoticeVO livestream_NoticeVO) {

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


			pstmt.setString(1, livestream_NoticeVO.getLivestream_id());
			pstmt.setString(2, livestream_NoticeVO.getMember_id());
			pstmt.setString(3, livestream_NoticeVO.getContent());
			pstmt.setInt(4, livestream_NoticeVO.getCo_status());

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
	public void update(Livestream_NoticeVO livestream_NoticeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, livestream_NoticeVO.getLivestream_id());
			pstmt.setString(2, livestream_NoticeVO.getMember_id());
			pstmt.setString(3, livestream_NoticeVO.getContent());
			pstmt.setInt(4, livestream_NoticeVO.getCo_status());
			pstmt.setString(5, livestream_NoticeVO.getLsnotice_id());
		

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
	public void delete(String lsnotice_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, lsnotice_id);

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
	public Livestream_NoticeVO findByPrimaryKey(String lsnotice_id) {

		Livestream_NoticeVO livestream_NoticeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, lsnotice_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				livestream_NoticeVO = new Livestream_NoticeVO();
				livestream_NoticeVO.setLsnotice_id(rs.getString("lsnotice_id"));
				livestream_NoticeVO.setLivestream_id(rs.getString("livestream_id"));
				livestream_NoticeVO.setMember_id(rs.getString("member_id"));
				livestream_NoticeVO.setNotice_time(rs.getTimestamp("notice_time"));
				livestream_NoticeVO.setContent(rs.getString("content"));
				livestream_NoticeVO.setCo_status(rs.getInt("co_status"));
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
		return livestream_NoticeVO;
	}

	@Override
	public List<Livestream_NoticeVO> getAll() {
		List<Livestream_NoticeVO> list = new ArrayList<Livestream_NoticeVO>();
		Livestream_NoticeVO livestream_NoticeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				livestream_NoticeVO = new Livestream_NoticeVO();
				livestream_NoticeVO.setLsnotice_id(rs.getString("lsnotice_id"));
				livestream_NoticeVO.setLivestream_id(rs.getString("livestream_id"));
				livestream_NoticeVO.setMember_id(rs.getString("member_id"));
				livestream_NoticeVO.setNotice_time(rs.getTimestamp("notice_time"));
				livestream_NoticeVO.setContent(rs.getString("content"));
				livestream_NoticeVO.setCo_status(rs.getInt("co_status"));
				list.add(livestream_NoticeVO); // Store the row in the list
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