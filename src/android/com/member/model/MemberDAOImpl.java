package android.com.member.model;

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

public class MemberDAOImpl implements MemberDAO {
	private static final String INSERT_STMT = "INSERT INTO MEMBER(account, password, name, email) VALUES(?, ?, ?, ?)";
//	private static final String UPDATE_STMT = "UPDATE member SET password = ?, name = ?, email = ? WHERE userid = ?";
	private static final String DONATE_STMT = "UPDATE member SET balance=balance-100 where ACCOUNT = ?";
	private static final String GETDONATE_STMT = "UPDATE member SET balance=balance+100 where MEMBER_ID= ?";
	private static final String FIND_BY_ID_PASWD = "SELECT * FROM MEMBER where account = ? AND password = ?";
	private static final String FIND_Name_BY_ACCOUNT = "SELECT member_name FROM MEMBER where ACCOUNT = ?";
	private static final String CHECK_ID_EXIST = "SELECT ACCOUNT FROM MEMBER where ACCOUNT = ?";
	private static final String FIND_Id_BY_ACCOUNT = "SELECT member_id FROM MEMBER where ACCOUNT = ?";
	private static final String FIND_NICKNAME_BY_ID = "SELECT nickname FROM MEMBER where member_id = ?";
	private static final String FIND_Photo_BY_ACCOUNT = "SELECT member_photo FROM MEMBER where ACCOUNT = ?";
	//private static final String FIND_Pic_BY_STATUS = "SELECT member_photo FROM MEMBER where STATUS = ?";
	private static final String FIND_Balance_BY_ACCOUNT = "SELECT balance FROM MEMBER where ACCOUNT = ?";
	private static final String BALANCE_ENOUGH_STMT = "SELECT BALANCE FROM MEMBER where MEMBER_ID = ?";
	private static final String PAY_PRODUCT_STMT = "UPDATE MEMBER SET BALANCE = ? where MEMBER_ID = ?";
	private static final String FIND_ONE_BY_PASWD = "SELECT * FROM MEMBER where account = ? AND password = ?";
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
	
	@Override
	public boolean add(Member member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isAdded = false;

		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, member.getAccount());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMember_name());
			pstmt.setString(4, member.getEmail());

			int count = pstmt.executeUpdate();
			isAdded = count > 0 ? true : false;

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
		return isAdded;
	}
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
	public Member findNameByAccount(String account) {
		Member member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_Name_BY_ACCOUNT);

			pstmt.setString(1, account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new Member();
				member.setAccount(rs.getString(1));
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
		return member;
	}

	@Override
	public List<Member> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMember(String account, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isMember = false;
		try {
			conn = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			ps = conn.prepareStatement(FIND_BY_ID_PASWD);
			ps.setString(1, account);
			ps.setString(2, password);
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
	public boolean isUserIdExist(String account) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isUserIdExist = false;
		try {
			conn = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			ps = conn.prepareStatement(CHECK_ID_EXIST);
			ps.setString(1, account);
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


	@Override
	public boolean donate(String account) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean dd = false;
		try {
			conn = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			ps = conn.prepareStatement(DONATE_STMT);
			
			ps.setString(1, account);
			
			int count = ps.executeUpdate();
			dd = count > 0 ? true : false;
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
		return dd;
	}

	@Override
	public byte[] findPhotoByAccount(String account) {
		byte[] picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_Photo_BY_ACCOUNT);

			pstmt.setString(1, account);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				picture = rs.getBytes(1);
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
		return picture;
}

	@Override
	public Member findIdByAccount(String account) {
		Member member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_Id_BY_ACCOUNT);

			pstmt.setString(1, account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new Member();
				member.setAccount(rs.getString(1));
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
		return member;
	}
	@Override
	public Member findBalanceByAccount(String account) {
		Member member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_Balance_BY_ACCOUNT);

			pstmt.setString(1, account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new Member();
				member.setAccount(rs.getString(1));
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
		return member;
	}
	@Override
	public boolean getDonate(String member_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean dd = false;
		try {
			conn = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			ps = conn.prepareStatement(GETDONATE_STMT);
			
			ps.setString(1, member_id);
			
			int count = ps.executeUpdate();
			dd = count > 0 ? true : false;
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
		return dd;
	}
	
	@Override
	public Integer getBalance(String member_id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer balance = null;
		
		try {
			conn = ds.getConnection();

			ps = conn.prepareStatement(BALANCE_ENOUGH_STMT);
			ps.setString(1, member_id);

			rs = ps.executeQuery();

			while (rs.next()) {
				balance = rs.getInt("BALANCE");
			}

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
		return balance;
	}
	
	@Override
	public boolean balanceEnough(String member_id, Integer sum) {
		System.out.println(member_id + sum);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();

			ps = conn.prepareStatement(BALANCE_ENOUGH_STMT);
			ps.setString(1, member_id);

			rs = ps.executeQuery();

			Integer balance = null;
			while (rs.next()) {
				balance = rs.getInt("BALANCE");
			}

			if (balance == null) {
				return false;
			}

			if (balance < sum) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
		return false;
	}
	
	@Override
	public boolean payProduct(String member_id, Integer sum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean dd = false;
		Integer balance = getBalance(member_id);//取得會員點數餘額
		Integer new_balance = balance - sum;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(PAY_PRODUCT_STMT);
			ps.setInt(1, new_balance);
			ps.setString(2, member_id);

			int count = ps.executeUpdate();
			dd = count > 0 ? true : false;

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
		return dd;
	}
	
	@Override
	public Member findOneByAccountAndPassword(String account, String password) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Member member = new Member();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(FIND_ONE_BY_PASWD);
			ps.setString(1, account);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				member.setMember_id(rs.getString("member_id"));
				member.setAccount(rs.getString("account"));
				member.setPassword(rs.getString("password"));
				member.setMember_name(rs.getString("member_name"));
				member.setMember_address(rs.getString("member_address"));
				member.setMember_creditcard(rs.getString("member_creditcard"));
				member.setBalance(rs.getInt("Balance"));
				member.setCellphone(rs.getString("CELLPHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setNickname(rs.getString("nickname"));
			}
			System.out.println(member);

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
		return member;
	}

	@Override
	public Member findNickname(String member_id) {//FIND_NICKNAME_BY_ID
		Member member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(MyData.URL, MyData.USER,
					MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_NICKNAME_BY_ID);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new Member();
				member.setNickname(rs.getString("nickname"));
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
		return member;
	}
}
