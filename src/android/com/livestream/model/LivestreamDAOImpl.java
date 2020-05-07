package android.com.livestream.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.main.MyData;

import android.com.livestream.model.Livestream;

public class LivestreamDAOImpl implements LivestreamDAO{
	
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
//to_char(livestream_date,'yyyy-mm-dd') livestream_date
		private static final String INSERT_STMT = 
			"INSERT INTO livestream (livestream_id,livestream_date,picture,introduction,title,member_id) VALUES (SQ_LIVESTREAM_ID.NEXTVAL, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = //for watch trailer
			"SELECT livestream_id,member_id,livestream_date,introduction,title FROM livestream where member_id = ? AND status = 0 ";
		private static final String GET_ONE_STMT = //for Member
			"SELECT livestream_id,picture,title FROM livestream where livestream_id = ?";
		private static final String DELETE = 
			"DELETE FROM livestream where livestream_id = ?";
		private static final String UPDATE = 
			"UPDATE livestream set member_id=?, livestream_date=?, video=?, status=?, picture=?, introduction=?, title=?, watched_num=? where livestream_id = ?";
		private static final String UPDATESTATUS = 
				"UPDATE livestream set status=? where livestream_id = ?";
		private static final String FIND_PIC_BY_STATUS = "SELECT picture FROM livestream where livestream_id = ? ";
		private static final String FIND_TITLE_BY_ID = "SELECT title FROM livestream where livestream_id = ? ";
		private static final String FIND_MEMBER_BY_ID = "SELECT member_id FROM livestream where livestream_id = ? ";
		private static final String FIND_SOME_BY_STATUS = "SELECT livestream_id,member_id,status,title FROM livestream where STATUS = ? ";
		@Override
		public boolean insert(Livestream livestream) {
			boolean bb = false;
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setDate(1, livestream.getLivestream_date());
				pstmt.setBytes(2, livestream.getPicture());
				pstmt.setString(3, livestream.getIntroduction());
				pstmt.setString(4, livestream.getTitle());
				pstmt.setString(5, livestream.getMember_id());
//				pstmt.setString(1, livestreamVO.getMember_id());
//				pstmt.setDate(2, livestreamVO.getLivestream_date());
//				pstmt.setBytes(3, livestreamVO.getVideo());
//				pstmt.setInt(4, livestreamVO.getStatus());
//				pstmt.setBytes(5, livestreamVO.getPicture());
//				pstmt.setString(6, livestreamVO.getIntroduction());
//				pstmt.setString(7, livestreamVO.getTitle());
//				pstmt.setInt(8, livestreamVO.getWatched_num());

				int count = pstmt.executeUpdate();
				bb = count > 0 ? true : false;
				bb = true;
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
				return bb;
		}

//		@Override
//		public void update(Livestream livestreamVO) {
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(UPDATE);
//
//				pstmt.setString(1, livestreamVO.getMember_id());
//				pstmt.setDate(2, livestreamVO.getLivestream_date());
//				pstmt.setBytes(3, livestreamVO.getVideo());//????
//				pstmt.setInt(4, livestreamVO.getStatus());
//				pstmt.setBytes(5, livestreamVO.getPicture());
//				pstmt.setString(6, livestreamVO.getIntroduction());
//				pstmt.setString(7, livestreamVO.getTitle());
//				pstmt.setInt(8, livestreamVO.getWatched_num());
//				pstmt.setString(9, livestreamVO.getLivestream_id());
//
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//		}
//
//		@Override
//		public void delete(String livestream_id) {
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(DELETE);
//
//				pstmt.setString(1, livestream_id);
//
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//		}
		//@Override
		public Livestream findByPrimaryKey(String livestream_id) {
			Connection con = null;
			Livestream livestreamVO = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, livestream_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					livestreamVO = new Livestream();
					livestreamVO.setLivestream_id(rs.getString("livestream_id"));
//					livestreamVO.setMember_id(rs.getString("member_id"));
//					livestreamVO.setLivestream_date(rs.getDate("livestream_date"));
//					livestreamVO.setVideo(rs.getBytes("video"));
//					livestreamVO.setStatus(rs.getInt("status"));
					livestreamVO.setPicture(rs.getBytes("picture"));
//					livestreamVO.setIntroduction(rs.getString("introduction"));
					livestreamVO.setTitle(rs.getString("title"));
//					livestreamVO.setWatched_num(rs.getInt("watched_num"));
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
		public Livestream findByStatus(String member_id) {
			Connection con = null;
			Livestream livestreamVO = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);

				pstmt.setString(1, member_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					livestreamVO = new Livestream();
					livestreamVO.setLivestream_id(rs.getString("livestream_id"));
//					livestreamVO.setMember_id(rs.getString("member_id"));
					livestreamVO.setLivestream_date(rs.getDate("livestream_date"));
//					livestreamVO.setVideo(rs.getBytes("video"));
//					livestreamVO.setStatus(rs.getInt("status"));
//					livestreamVO.setPicture(rs.getBytes("picture"));
					livestreamVO.setIntroduction(rs.getString("introduction"));
					livestreamVO.setTitle(rs.getString("title"));
//					livestreamVO.setWatched_num(rs.getInt("watched_num"));
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
//
//		@Override
//		public void updatestatus(Livestream livestreamVO) {
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(UPDATESTATUS);
//
//				pstmt.setInt(1, livestreamVO.getStatus());
//				pstmt.setString(2, livestreamVO.getLivestream_id());
//
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//		}

		@Override
		public List<Livestream> findByCategory(int status) {
			List<Livestream> lsList = new ArrayList<>();
			Livestream livestream = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
				pstmt = con.prepareStatement(FIND_SOME_BY_STATUS);
				pstmt.setInt(1, status);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					livestream = new Livestream();
					livestream.setLivestream_id(rs.getString("livestream_id"));
					livestream.setMember_id(rs.getString("member_id"));
					livestream.setStatus(rs.getInt("status"));
					livestream.setTitle(rs.getString("title"));
					lsList.add(livestream);
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
			return lsList;
		}


		@Override
		public byte[] findPicByStatus(String livestream_id) {
			byte[] picture = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = DriverManager.getConnection(MyData.URL, MyData.USER,
						MyData.PASSWORD);
				pstmt = con.prepareStatement(FIND_PIC_BY_STATUS);

				pstmt.setString(1, livestream_id);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					picture = rs.getBytes(1);
				}
			}catch (SQLException se) {
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
			return picture;
		}

		@Override
		public Livestream findTitleById(String livestream_id) {//FIND_TITLE_BY_ID
			Connection con = null;
			Livestream livestreamVO = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(FIND_TITLE_BY_ID);

				pstmt.setString(1, livestream_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					livestreamVO = new Livestream();
					livestreamVO.setTitle(rs.getString("title"));
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
		public Livestream findCidByLsid(String livestream_id) {//FIND_MEMBER_BY_ID
			Connection con = null;
			Livestream livestreamVO = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(FIND_MEMBER_BY_ID);

				pstmt.setString(1, livestream_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					livestreamVO = new Livestream();
					livestreamVO.setMember_id(rs.getString("member_id"));
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

}
