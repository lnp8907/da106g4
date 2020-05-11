package com.staff.model;

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

import com.staff.model.StaffVO;



public class StaffDAO  implements StaffDAO_interface {
	
	 private static DataSource ds = null;
	    static {
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
	    }
	    private static final String INSERT_STMT = "INSERT INTO STAFF (STAFF_ID,STAFF_PASSWORD,STAFF_NAME,GENDER,PHONE,STAFF_STATUS,EMAIL) VALUES ( SQ_STAFF_ID.NEXTVAL,?,?, ?, ?, ?,?)";
	    private static final String GET_ALL_STMT = "SELECT STAFF_ID,EMAIL,STAFF_NAME,GENDER,PHONE,STAFF_STATUS FROM STAFF order by STAFF_ID";
	    private static final String GET_ONE_STMT = "SELECT STAFF_ID,EMAIL, STAFF_PASSWORD,STAFF_NAME,GENDER,PHONE,STAFF_STATUS FROM STAFF WHERE STAFF_ID = ?";   
	    private static final String UPDATEALL = "UPDATE STAFF SET  staff_status=?, STAFF_NAME=?, GENDER=? , PHONE=?, EMAIL=? WHERE  STAFF_ID= ?";
	    private static final String UPDATE_WITH_PASSWORD = " UPDATE STAFF SET  staff_password=?, STAFF_NAME=?, GENDER=? , PHONE=?, EMAIL=?,staff_status=? WHERE  STAFF_ID= ?";
	    //只修改密碼
	    private static final String UPDATE_PASSWORD = "UPDATE STAFF SET  STAFF_PASSWORD=? WHERE  STAFF_ID= ?";
	    private static final String CHANGE_STATUS = "UPDATE STAFF SET  STAFF_STATUS=? WHERE  STAFF_ID= ?";
	    private static final String GET_ONE_STMT2 = "SELECT STAFF_ID,EMAIL,STAFF_PASSWORD,STAFF_NAME,GENDER,PHONE,STAFF_STATUS FROM STAFF where STAFF_ID = ?";
	   
	    
	    
	    public StaffVO findPK(String staff_id) {

//			StaffVO empVO = null;
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//
//			try {
//
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);
//				pstmt = con.prepareStatement(GET_ONE_STMT2);
//
//				pstmt.setString(1,staff_id);
//
//				rs = pstmt.executeQuery();
				

				StaffVO empVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_STMT2);
					pstmt.setString(1, staff_id);
					rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					empVO = new StaffVO();
					empVO.setStaff_id(rs.getString("STAFF_ID"));
					empVO.setEmail(rs.getString("EMAIL"));
					empVO.setStaff_password(rs.getString("STAFF_PASSWORD"));
					empVO.setStaff_status(rs.getInt("STAFF_STATUS"));
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
			return empVO;
		}
	    
	    
	    
	    
	    
	    
	    
	@Override
	public String insert(StaffVO staffvo) {
		String staff_id = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();	
			String cols[] = {"staff_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
		
			pstmt.setString(1, staffvo.getStaff_password());
			pstmt.setString(2, staffvo.getStaff_name());
			pstmt.setInt(3, staffvo.getGender());
			pstmt.setString(4, staffvo.getPhone());
			pstmt.setInt(5, staffvo.getStaff_status());
			pstmt.setString(6, staffvo.getEmail());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			staff_id = rs.getString(1);
	
			rs.close();
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
		return staff_id;
		
	
	}
	@Override
	public void updateWithPassword(StaffVO staffvo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_WITH_PASSWORD);

			pstmt.setString(1, staffvo.getStaff_password());
			pstmt.setString(2, staffvo.getStaff_name());
			pstmt.setInt(3, staffvo.getGender());
			pstmt.setString(4, staffvo.getPhone());
			pstmt.setString(5, staffvo.getEmail());
			pstmt.setInt(6,staffvo.getStaff_status());
			pstmt.setString(7, staffvo.getStaff_id());
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
	public void update(StaffVO staffvo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEALL);

			pstmt.setInt(1, staffvo.getStaff_status());
			pstmt.setString(2, staffvo.getStaff_name());
			pstmt.setInt(3, staffvo.getGender());
			pstmt.setString(4, staffvo.getPhone());
			pstmt.setString(5, staffvo.getEmail());
			pstmt.setString(6, staffvo.getStaff_id());
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
	public StaffVO findByPrimaryKey(String staff_id) {
		StaffVO staffvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, staff_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				staffvo = new StaffVO();
				staffvo.setStaff_id(rs.getString("staff_id"));
				staffvo.setEmail(rs.getString("email"));
				staffvo.setStaff_password(rs.getString("staff_password"));
				staffvo.setStaff_name(rs.getString("staff_name"));
				staffvo.setGender(rs.getInt("gender"));
				staffvo.setPhone(rs.getString("phone"));
				staffvo.setEmail(rs.getString("email"));
				staffvo.setStaff_status(rs.getInt("staff_status"));
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
			
	}}
		return staffvo; 
	}

	@Override
	public List<StaffVO> getAll() {
		// TODO Auto-generated method stub
		List<StaffVO> list = new ArrayList();
		StaffVO  staffvo= null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				staffvo = new StaffVO();
				staffvo.setStaff_id(rs.getString("staff_id"));
				staffvo.setEmail(rs.getString("email"));
				staffvo.setStaff_name(rs.getString("staff_name"));
				staffvo.setGender(rs.getInt("gender"));
				staffvo.setPhone(rs.getString("phone"));
				staffvo.setStaff_status(rs.getInt("staff_status"));
				staffvo.setEmail(rs.getString("email"));
				list.add(staffvo); // Store the row in the list
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
	public void changeStatus(String staff_id, Integer status) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(CHANGE_STATUS);

				pstmt.setInt(1,status);
				pstmt.setString(2,staff_id);

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
	public void resetPassword(String staff_id,String password) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PASSWORD);

			pstmt.setString(1,password);
			pstmt.setString(2,staff_id);

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

}
