package com.livestream_notice.model;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class LiveStream_NoticeJDBCDAO implements Livestream_NoticeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = 
		"INSERT INTO livestream_notice (lsnotice_id,livestream_id,member_id,notice_time,content,co_status) VALUES (sq_lsnotice_id.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT lsnotice_id,livestream_id,member_id,notice_time,content,co_status FROM livestream_notice order by lsnotice_id";
	private static final String GET_ONE_STMT = 
		"SELECT lsnotice_id,livestream_id,member_id,notice_time,content,co_status FROM livestream_notice WHERE lsnotice_id = ?";
	private static final String DELETE = 
		"DELETE FROM livestream where livestream_id = ?";
	private static final String UPDATE = 
			"UPDATE livestream_notice set livestream_id=?, member_id=?, notice_time=?, content=?, co_status=? where lsnotice_id = ?";

	@Override
	public void insert(Livestream_NoticeVO livestream_NoticeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, livestream_NoticeVO.getLivestream_id());
			pstmt.setString(2, livestream_NoticeVO.getMember_id());
			pstmt.setTimestamp(3, livestream_NoticeVO.getNotice_time());
			pstmt.setString(4, livestream_NoticeVO.getContent());
			pstmt.setInt(5, livestream_NoticeVO.getCo_status());
			
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
	public void update(Livestream_NoticeVO livestream_NoticeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, livestream_NoticeVO.getLivestream_id());
			pstmt.setString(2, livestream_NoticeVO.getMember_id());
			pstmt.setTimestamp(3, livestream_NoticeVO.getNotice_time());
			pstmt.setString(4, livestream_NoticeVO.getContent());
			pstmt.setInt(5, livestream_NoticeVO.getCo_status());
			pstmt.setString(6, livestream_NoticeVO.getLsnotice_id());
			
		


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
	public void delete(String lsnotice_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, lsnotice_id);

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
	public Livestream_NoticeVO findByPrimaryKey(String lsnotice_id) {

		Livestream_NoticeVO livestream_NoticeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		LiveStream_NoticeJDBCDAO dao = new LiveStream_NoticeJDBCDAO();

		// 新增
//		Livestream_NoticeVO livestream_NoticeVO1 = new Livestream_NoticeVO();
//		livestream_NoticeVO1.setLivestream_id("410013");
//		livestream_NoticeVO1.setMember_id("810010");
//		livestream_NoticeVO1.setNotice_time(Timestamp.valueOf("2020-02-15 00:00:00"));
//		livestream_NoticeVO1.setContent("敬愛的會員~今天有直播，記得進來跟廚師一起做菜學習喔！");
//		livestream_NoticeVO1.setCo_status(1);
//		dao.insert(livestream_NoticeVO1);

		// 修改
		Livestream_NoticeVO livestreamVO2 = new Livestream_NoticeVO();
		livestreamVO2.setLsnotice_id("420001");
		livestreamVO2.setLivestream_id("410013");
		livestreamVO2.setMember_id("810008");
		livestreamVO2.setNotice_time(Timestamp.valueOf("2020-02-15 00:00:00"));
		livestreamVO2.setContent("敬愛的會員~今天有直播，記得進來跟廚師一起做菜學習喔！");
		livestreamVO2.setCo_status(1);
		dao.update(livestreamVO2);

		// 刪除
//		dao.delete("410000");

		// 查詢 
//		LivestreamVO livestreamVO3 = dao.findByPrimaryKey("410004");
//		livestreamVO2.setLsnotice_id("420001");
//		livestreamVO2.setLivestream_id("810005");
//		livestreamVO2.setMember_id("");
//		livestreamVO2.setNotice_time();
//		livestreamVO2.setContent("");
//		livestreamVO2.setCo_status(new byte[2]);
//		System.out.println("---------------------");

		// 查詢
//		List<LivestreamVO> list = dao.getAll();
//		for (LivestreamVO aLive : list) {
//			livestreamVO2.setLsnotice_id("420001");
//		livestreamVO2.setLivestream_id("810005");
//		livestreamVO2.setMember_id("");
//		livestreamVO2.setNotice_time();
//		livestreamVO2.setContent("");
//		livestreamVO2.setCo_status(new byte[2]);
//			System.out.println();
//		}
	}
	
}