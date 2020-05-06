package android.com.staff.model;

//import idv.david.book.model.Book;
import android.com.main.MyData;

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

public class StaffDAOImpl implements StaffDAO {
//	private static final String INSERT_STMT = "INSERT INTO STAFF(staff_account, password, name, email) VALUES(?, ?, ?, ?)";
//	private static final String UPDATE_STMT = "UPDATE STAFF SET password = ?, name = ?, email = ? WHERE staff_account = ?";
	private static final String FIND_BY_ID_PASWD = "SELECT * FROM STAFF where staff_id = ? AND staff_password = ?";
	private static final String FIND_BY_ID = "SELECT * FROM STAFF where staff_id = ?";
	private static final String CHECK_ID_EXIST = "SELECT staff_id FROM STAFF where staff_id = ?";
	

//	public MemberDAOImpl() {
//		super();
//		try {
//			Class.forName("java:comp/env/jdbc/TestDB");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}  
	
	 private static DataSource ds = null;
	  static {
	   try {
	    Context ctx = new InitialContext();
	    ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
	   } catch (NamingException e) {
	    e.printStackTrace();
	   }
	  }
	
//	@Override
//	public boolean add(Member member) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		boolean isAdded = false;
//
//		try {
//			con = DriverManager.getConnection(MyData.URL, MyData.USER,
//					MyData.PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, member.getAccount());
//			pstmt.setString(2, member.getPassword());
////			pstmt.setString(3, member.getName());
////			pstmt.setString(4, member.getEmail());
//
//			int count = pstmt.executeUpdate();
//			isAdded = count > 0 ? true : false;
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return isAdded;
//	}
//
//	@Override
//	public boolean update(Member member) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		boolean isUpdated = false;
//		
//		try {
//			con = DriverManager.getConnection(MyData.URL, MyData.USER,
//					MyData.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE_STMT);
//
//			pstmt.setString(1, member.getPassword());
//			pstmt.setString(2, member.getName());
//			pstmt.setString(3, member.getEmail());
//			pstmt.setString(4, member.getAccount());
//
//			int count = pstmt.executeUpdate();
//			isUpdated = count > 0 ? true : false;
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return isUpdated;
//	}

//	@Override
//	public boolean delete(String account) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public Staff findById(String staff_id) {
		Staff staff = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ID);

			pstmt.setString(1, staff_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				staff = new Staff();
				staff.setStaff_id(rs.getString("STAFF_ID"));
				staff.setStaff_name(rs.getString("STAFF_NAME"));
				staff.setStaff_password(rs.getString("STAFF_PASSWORD"));
				staff.setPhone(rs.getString("PHONE"));
				staff.setStaff_status(rs.getInt("STAFF_STATUS"));
//				member.setName(rs.getString(3));
//				member.setEmail(rs.getString(4));
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
		return staff;
	}

	@Override
	public List<Staff> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMember(String staff_id, String staff_password) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isMember = false;
		try {
			
//			conn = ds.getConnection();
			conn = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			ps = conn.prepareStatement(FIND_BY_ID_PASWD);
			ps.setString(1, staff_id);
			ps.setString(2, staff_password);
			ResultSet rs = ps.executeQuery();
			isMember = rs.next();
			return isMember;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isMember;
	}

	@Override
	public boolean isUserIdExist(String staff_account) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isUserIdExist = false;
		try {
			conn = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			ps = conn.prepareStatement(CHECK_ID_EXIST);
			ps.setString(1, staff_account);
			ResultSet rs = ps.executeQuery();
			isUserIdExist = rs.next();
			return isUserIdExist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isUserIdExist;
	}

}
