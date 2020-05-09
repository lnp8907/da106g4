package com.livestream.model;

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

public class LivestreamDAO implements LivestreamDAO_interface {

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
		"INSERT INTO livestream (livestream_id,member_id,livestream_date,video,status,picture,introduction,title,watched_num) VALUES (SQ_LIVESTREAM_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
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
	private static final String GET_FOR_HOMEPAGE = "SELECT * FROM LIVESTREAM WHERE STATUS = 1 ORDER BY LIVESTREAM_DATE";
	private static final String GET_MOST_POPULAR = "SELECT * FROM(SELECT * FROM LIVESTREAM WHERE VIDEO IS NOT NULL  ORDER BY WATCHED_NUM DESC) WHERE ROWNUM = 1";
	private static final String GETLASTESTONE = " SELECT * FROM(SELECT * FROM LIVESTREAM WHERE MEMBER_ID = ? AND STATUS = 1 ORDER BY LIVESTREAM_DATE DESC) WHERE ROWNUM = 1";
	private static final String UPDATEAFTEROL = "UPDATE livestream set video = ?, watched_num = ? , status = ? where livestream_id = ?";
	private static final String UPDATEONLINE = "UPDATE livestream set status = ? where livestream_id = ?";
	
	@Override
	public void updateForOnline(String livestream_id,Integer status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEONLINE);


			pstmt.setInt(1, status);
			pstmt.setString(2, livestream_id);

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
	public void updateAfterOnline (String livestream_id,byte[] video,Integer watched_num,Integer status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEAFTEROL);


			pstmt.setBytes(1, video);
			pstmt.setInt(2, watched_num);
			pstmt.setInt(3, status);
			pstmt.setString(4, livestream_id);

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
	public LivestreamVO getLatestOneLs(String member_id) {
		LivestreamVO livestreamVO = new LivestreamVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETLASTESTONE);
			pstmt.setString(1, member_id);
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
				livestreamVO.setWatched_num(rs.getInt("watched_num"));
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
		
		return livestreamVO;
	}
	
	//@Override
		public LivestreamVO getMostPopLS() {

			LivestreamVO livestreamVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_MOST_POPULAR);

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
					livestreamVO.setWatched_num(rs.getInt("watched_num"));
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
			return livestreamVO;
		}
	
	
	public List<LivestreamVO> getFourForHomePage(){
		List<LivestreamVO> list = new ArrayList<LivestreamVO>();
		LivestreamVO livestreamVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FOR_HOMEPAGE);
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
	@Override
	public void insert(LivestreamVO livestreamVO) {

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

			pstmt.setString(1, livestreamVO.getMember_id());
			pstmt.setDate(2, livestreamVO.getLivestream_date());
			pstmt.setBytes(3, livestreamVO.getVideo());
			pstmt.setInt(4, livestreamVO.getStatus());
			pstmt.setBytes(5, livestreamVO.getPicture());
			pstmt.setString(6, livestreamVO.getIntroduction());
			pstmt.setString(7, livestreamVO.getTitle());
			pstmt.setInt(8, livestreamVO.getWatched_num());

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
	public void update(LivestreamVO livestreamVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
	public LivestreamVO findByPrimaryKey(String livestream_id) {

		LivestreamVO livestreamVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
				livestreamVO.setWatched_num(rs.getInt("watched_num"));
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

			con = ds.getConnection();
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

	@Override
	public void updatestatus(LivestreamVO livestreamVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setInt(1, livestreamVO.getStatus());
			pstmt.setString(2, livestreamVO.getLivestream_id());

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



}