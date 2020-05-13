package com.member_follow.model;

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


public class Member_followDAO implements Member_followDAO_interface{
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static final String INSERT_STMT = "INSERT INTO MEMBER_FOLLOW (MEMBER_ID,FOLLOWED) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT MEMBER_ID,FOLLOWED FROM MEMBER_FOLLOW order by MEMBER_ID";
	
	private static final String GET_ONE_STMT = "SELECT MEMBER_ID,FOLLOWED FROM MEMBER_FOLLOW where MEMBER_ID = ?";
	private static final String GET_FOLLOW_STMT = "SELECT MEMBER_ID FROM MEMBER_FOLLOW where FOLLOWED= ?";	
	private static final String DELETE = "DELETE FROM MEMBER_FOLLOW where MEMBER_ID = ? AND FOLLOWED = ?";
	private static final String UPDATE = "UPDATE MEMBER_FOLLOW set MEMBER_ID=?, FOLLOWED=? where MEMBER_ID = ? and FOLLOWED = ?";

	@Override
	public void insert(Member_followVO member_followVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			

			pstmt.setString(1, member_followVO.getMember_id());
			pstmt.setString(2, member_followVO.getFollowed());
		
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
			System.out.println("追蹤過囉~,取消追蹤");
			delete(member_followVO);
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
	public void update(Member_followVO member_followVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, member_followVO.getMember_id());
//			pstmt.setString(2, member_followVO.getMember_id_0());
			
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
	public void delete(Member_followVO empVO) {
		Member_followVO member_followVO = empVO;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, member_followVO.getMember_id());
			pstmt.setString(2, member_followVO.getFollowed());

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
	public Member_followVO findByPrimaryKey(String MEMBER_ID) {

		Member_followVO member_followVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, MEMBER_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				member_followVO = new Member_followVO();
				member_followVO.setMember_id(rs.getString("MEMBER_ID"));
//				member_followVO.setMember_id_0(rs.getString("FOLLOWED"));
				

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
		return member_followVO;
	}
	
	
	@Override
	public Member_followVO findByPrimaryKey_1(String MEMBER_ID) {

		Member_followVO member_followVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, MEMBER_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				member_followVO = new Member_followVO();
				member_followVO.setMember_id(rs.getString("MEMBER_ID"));
//				member_followVO.setMember_id_0(rs.getString("FOLLOWED"));
				

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
		return member_followVO;
	}
	
	

	@Override
	public List<Member_followVO> getall() {
		List<Member_followVO> list = new ArrayList<Member_followVO>();
		Member_followVO member_followVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				member_followVO = new Member_followVO();
				member_followVO.setMember_id(rs.getString("MEMBER_ID"));
//				member_followVO.setMember_id_0(rs.getString("FOLLOWED"));
				list.add(member_followVO); // Store the row in the list
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
	public List<Member_followVO> getAllMemberByFollowed(String followed) {//GET_FOLLOW_STMT
		List<Member_followVO> list = new ArrayList<Member_followVO>();
		Member_followVO mb_followVO = null;
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;

		  try {

		//   Class.forName(driver);
		//   con = DriverManager.getConnection(url, userid, passwd);
		   con = ds.getConnection();
		   pstmt = con.prepareStatement(GET_FOLLOW_STMT);
		   
		   pstmt.setString(1, followed);
		   
		   rs = pstmt.executeQuery();

		   while (rs.next()) {
		    // empVO 也稱為 Domain objects
		    mb_followVO = new Member_followVO();
		    mb_followVO.setMember_id(rs.getString("member_id"));
		    list.add(mb_followVO); // Store the row in the list
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

