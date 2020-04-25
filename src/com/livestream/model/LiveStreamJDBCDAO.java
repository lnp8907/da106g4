package com.livestream.model;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class LiveStreamJDBCDAO implements LivestreamDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = 
		"INSERT INTO livestream (livestream_id,member_id,livestream_date,video,status,picture,introduction,title,watched_num) VALUES (sq_livestream_id.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT livestream_id,member_id,to_char(livestream_date,'yyyy-mm-dd') livestream_date,video,status,picture,introduction,title,watched_num FROM livestream order by livestream_id";
	private static final String GET_ONE_STMT = 
		"SELECT livestream_id,member_id,to_char(livestream_date,'yyyy-mm-dd') livestream_date,video,status,picture,introduction,title,watched_num FROM livestream where livestream_id = ?";
	private static final String DELETE = 
		"DELETE FROM livestream where livestream_id = ?";
	private static final String UPDATE = 
			"UPDATE livestream set member_id=?, livestream_date=?, video=?, status=?, picture=?, introduction=?, title=?, watched_num=? where livestream_id = ?";
	private static final String UPDATESTATUS = 
			"UPDATE livestream set status=? where livestream_id = ?";
	@Override
	public void insert(LivestreamVO livestreamVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, livestreamVO.getMember_id());
			pstmt.setDate(2, livestreamVO.getLivestream_date());
			pstmt.setBytes(3, livestreamVO.getVideo());
			pstmt.setInt(4, livestreamVO.getStatus());
			pstmt.setBytes(5, livestreamVO.getPicture());
			pstmt.setString(6, livestreamVO.getIntroduction());
			pstmt.setString(7, livestreamVO.getTitle());
			pstmt.setInt(8, livestreamVO.getWatched_num());
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
	public void update(LivestreamVO livestreamVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, livestreamVO.getMember_id());
			pstmt.setDate(2, livestreamVO.getLivestream_date());
			pstmt.setBytes(3, livestreamVO.getVideo());//????
			pstmt.setInt(4, livestreamVO.getStatus());
			pstmt.setBytes(5, livestreamVO.getPicture());
			pstmt.setString(6, livestreamVO.getIntroduction());
			pstmt.setString(7, livestreamVO.getTitle());
			pstmt.setInt(8, livestreamVO.getWatched_num());
			pstmt.setString(9, livestreamVO.getLivestream_id());


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
	public LivestreamVO findByPrimaryKey(String livestream_id) {

		LivestreamVO livestreamVO = null;
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
				
				livestreamVO = new LivestreamVO();
				livestreamVO.setLivestream_id(rs.getString("livestream_id"));
				livestreamVO.setMember_id(rs.getString("member_id"));
				livestreamVO.setLivestream_date(rs.getDate("livestream_date"));
				livestreamVO.setVideo(rs.getBytes("video"));
				livestreamVO.setStatus(rs.getInt("status"));
				livestreamVO.setPicture(rs.getBytes("picture"));
				livestreamVO.setIntroduction(rs.getString("introduction"));
				livestreamVO.setTitle(rs.getString("title"));
				livestreamVO.setWatched_num(rs.getInt("picture"));
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
		return livestreamVO;
	}

	@Override
	public List<LivestreamVO> getAll() {
		List<LivestreamVO> list = new ArrayList<LivestreamVO>();
		LivestreamVO livestreamVO = null;

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
				
				livestreamVO = new LivestreamVO();
				livestreamVO.setLivestream_id(rs.getString("livestream_id"));
				livestreamVO.setMember_id(rs.getString("member_id"));
				livestreamVO.setLivestream_date(rs.getDate("livestream_date"));
				livestreamVO.setVideo(rs.getBytes("video"));
				livestreamVO.setStatus(rs.getInt("status"));
				livestreamVO.setPicture(rs.getBytes("picture"));
				livestreamVO.setIntroduction(rs.getString("introduction"));
				livestreamVO.setTitle(rs.getString("title"));
				livestreamVO.setWatched_num(rs.getInt("watched_num"));
				list.add(livestreamVO); // Store the row in the list
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

		LiveStreamJDBCDAO dao = new LiveStreamJDBCDAO();

		// 新增
//		LivestreamVO livestreamVO1 = new LivestreamVO();
//		livestreamVO1.setMember_id("810006");
//		livestreamVO1.setLivestream_date(java.sql.Date.valueOf("2020-02-15"));
//		livestreamVO1.setVideo(new byte[2]);
//		livestreamVO1.setStatus(3);
//		livestreamVO1.setPicture(new byte[2]);
//		livestreamVO1.setIntroduction("全程大約一小時，漂亮又好吃的杯子蛋糕，送人自用兩相宜！");
//		livestreamVO1.setTitle("漂亮杯子蛋糕～");
//		livestreamVO1.setWatched_num(74);
//		dao.insert(livestreamVO1);

		// 修改
		LivestreamVO livestreamVO2 = new LivestreamVO();
		livestreamVO2.setLivestream_id("410012");
		livestreamVO2.setMember_id("810005");
		livestreamVO2.setLivestream_date(java.sql.Date.valueOf("2020-02-14"));
		livestreamVO2.setVideo(new byte[2]);
		livestreamVO2.setStatus(3);
		livestreamVO2.setPicture(new byte[2]);
		livestreamVO2.setIntroduction("全程大約一小時，快來看看");
		livestreamVO2.setTitle("美味的法式烤雞～");
		livestreamVO2.setWatched_num(58);
		
		dao.update(livestreamVO2);

		// 刪除
//		dao.delete("410000");

		// 查詢 
//		LivestreamVO livestreamVO3 = dao.findByPrimaryKey("410004");
//		System.out.print(livestreamVO3.getLivestream_id() + ",");
//		System.out.print(livestreamVO3.getMember_id() + ",");
//		System.out.print(livestreamVO3.getLivestream_date() + ",");
//		System.out.print(livestreamVO3.getVideo() + ",");
//		System.out.print(livestreamVO3.getStatus() + ",");
//		System.out.print(livestreamVO3.getPicture() + ",");
//		System.out.println(livestreamVO3.getIntroduction() + ",");
//		System.out.print(livestreamVO3.getTitle() + ",");
//		System.out.print(livestreamVO3.getWatched_num());
//		System.out.println("---------------------");

		// 查詢
//		List<LivestreamVO> list = dao.getAll();
//		for (LivestreamVO aLive : list) {
//			System.out.print(aLive.getLivestream_id() + ",");
//			System.out.print(aLive.getMember_id() + ",");
//			System.out.print(aLive.getLivestream_date() + ",");
//			System.out.print(aLive.getVideo() + ",");
//			System.out.print(aLive.getStatus() + ",");
//			System.out.print(aLive.getPicture() + ",");
//			System.out.println(aLive.getIntroduction() + ",");
//			System.out.print(aLive.getTitle() + ",");
//			System.out.print(aLive.getWatched_num());
//			System.out.println();
//		}
	}

	@Override
	public void updatestatus(LivestreamVO livestreamVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTATUS);
			
			pstmt.setInt(1, livestreamVO.getStatus());
			pstmt.setString(2, livestreamVO.getLivestream_id());

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
	
}