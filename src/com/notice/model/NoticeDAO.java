package com.notice.model;

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

import com.moneyflow.model.MoneyflowDAO_interface;
import com.moneyflow.model.MoneyflowVO;

public class NoticeDAO implements NoticeDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	    private static final String INSERT_STMT = "INSERT INTO NOTICE (NOTICE_ID,MEMBER_ID,NOTICE_CATEGORY,CONTENT,NOTICE_STATUS) VALUES ('N'||LPAD(to_char(SQ_NOTICE_ID.NEXTVAL),6,'0'), ?, ?, ?, ?)";
	    private static final String GET_ALL_STMT = "SELECT NOTICE_ID,MEMBER_ID,NOTICE_CATEGORY,CONTENT,NOTICE_TIME,NOTICE_STATUS FROM NOTICE order by NOTICE_ID";
	    private static final String GET_ONE_STMT = "SELECT NOTICE_ID,MEMBER_ID,NOTICE_CATEGORY,CONTENT,NOTICE_TIME,NOTICE_STATUS FROM NOTICE WHERE ORDER_NO = ?";
	    private static final String DELETE = "DELETE FROM NOTICE WHERE NOTICE_ID = ? ";
	    private static final String UPDATE = "UPDATE NOTICE SET  MEMBER_ID=?, NOTICE_CATEGORY=?, CONTENT=?, NOTICE_STATUS=? WHERE  NOTICE_ID= ? ";
	    private static final String CHAGESTATUS = "UPDATE NOTICE SET NOTICE_STATUS=? WHERE NOTICE_ID= ? ";

	    
	    public void changestatus(String notice_id,int notice_status) {
	    	Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(CHAGESTATUS);
				pstmt.setInt(1, notice_status);
				pstmt.setString(2, notice_id);
				pstmt.executeUpdate();

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		
		public void changestatus(NoticeVO noticevo) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(CHAGESTATUS);
				pstmt.setInt(1, noticevo.getNotice_status());
				pstmt.setString(2, noticevo.getNotice_id());
			

				pstmt.executeUpdate();

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void insert(NoticeVO noticevo) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
			
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, noticevo.getMember_id());			
				pstmt.setInt(2, noticevo.getNotice_category());
				pstmt.setString(3, noticevo.getContent());
				pstmt.setInt(4, noticevo.getNotice_status());
				
			
				pstmt.executeUpdate();

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
		public void update(NoticeVO noticevo) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, noticevo.getMember_id());
				pstmt.setInt(2, noticevo.getNotice_category());
				pstmt.setString(3, noticevo.getContent());
				pstmt.setInt(4, noticevo.getNotice_status());
				pstmt.setString(5, noticevo.getNotice_id());
				pstmt.executeUpdate();

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void delete(String notice_id) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				pstmt.setString(1, notice_id);
				pstmt.executeUpdate();
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public NoticeVO findByPrimaryKey(String notice_id) {
			// TODO Auto-generated method stub
			NoticeVO noticevo = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			
			
			
			
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setString(1, notice_id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					noticevo = new NoticeVO();
					noticevo.setNotice_id(rs.getString("NOTICE_ID"));
					noticevo.setMember_id(rs.getString("Member_id"));
					noticevo.setContent(rs.getString("CONTENT"));
					noticevo.setNotice_time(rs.getTimestamp("NOTICE_TIME"));
					noticevo.setNotice_status(rs.getInt("NOTICE_STATUS"));
					noticevo.setNotice_category(rs.getInt("NOTICE_CATEGORY"));
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
			return noticevo;
		}

		@Override
		public List<NoticeVO> getAll() {
			// TODO Auto-generated method stub
			List<NoticeVO> list = new ArrayList<NoticeVO>();
			NoticeVO noticevo = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					noticevo = new NoticeVO();
					noticevo.setNotice_id(rs.getString("NOTICE_ID"));
					noticevo.setMember_id(rs.getString("Member_id"));
					noticevo.setContent(rs.getString("CONTENT"));
					noticevo.setNotice_time(rs.getTimestamp("NOTICE_TIME"));
					noticevo.setNotice_status(rs.getInt("NOTICE_STATUS"));
					noticevo.setNotice_category(rs.getInt("NOTICE_CATEGORY"));
					list.add(noticevo); // Store the row in the list
				}
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			}  finally {
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
