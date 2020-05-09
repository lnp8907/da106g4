package com.moneyflow.model;

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

public class MoneyflowDAO implements MoneyflowDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	 private static final String INSERT_STMT = "INSERT INTO MONEYFLOW (MONEYFLOW_NO,MEMBER_ID,MONEYFLOW_STATUS,MONEY) VALUES ('MN-'||to_CHAR(current_timestamp,'YYYY-MM-DD')||'-'||LPAD(to_char(SQ_MONEYFLOW_NO.NEXTVAL),6,'0'), ?, ?, ?)";
	    private static final String GET_ALL_STMT = "SELECT MONEYFLOW_NO,MEMBER_ID,MONEYFLOW_STATUS,MONEY,CHARGE_TIME FROM MONEYFLOW order by MONEYFLOW_NO";
	    private static final String GET_ONE_STMT = "SELECT MONEYFLOW_NO,MEMBER_ID,MONEYFLOW_STATUS,MONEY,CHARGE_TIME FROM MONEYFLOW WHERE MONEYFLOW_NO = ?";
	    private static final String DELETE = "DELETE FROM MONEYFLOW WHERE MONEYFLOW_NO = ? ";
	    private static final String UPDATE = "UPDATE MONEYFLOW SET  MEMBER_ID=?, MONEYFLOW_STATUS=?, MONEY=? WHERE  MONEYFLOW_NO= ? ";
	    private static final String CHAGESTATUS = "UPDATE MONEYFLOW SET   MONEYFLOW_STATUS=? WHERE  MONEYFLOW_NO= ? ";

	    
	    public void changestatus(String moneyflolw_no,int status) {
	    	Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(CHAGESTATUS);
				pstmt.setInt(1, status);
				pstmt.setString(2, moneyflolw_no);
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
		
		public void changestatus(MoneyflowVO moneyfloevo) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(CHAGESTATUS);
				pstmt.setInt(1, moneyfloevo.getMoneyflow_status());
				pstmt.setString(2, moneyfloevo.getMember_id());
			

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
		public void insert(MoneyflowVO moneyfloevo) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, moneyfloevo.getMember_id());			
				pstmt.setInt(2, moneyfloevo.getMoneyflow_status());
				pstmt.setInt(3, moneyfloevo.getMoney());
				

				pstmt.executeUpdate();

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
		public void update(MoneyflowVO moneyfloevo) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, moneyfloevo.getMember_id());
				pstmt.setInt(2, moneyfloevo.getMoneyflow_status());
				pstmt.setInt(3, moneyfloevo.getMoney());
				pstmt.setString(4, moneyfloevo.getMember_id());

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
		public void delete(String moneyflow_no) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				pstmt.setString(1, moneyflow_no);
				pstmt.executeUpdate();
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
		public MoneyflowVO findByPrimaryKey(String moneyflow_no) {
			// TODO Auto-generated method stub
			MoneyflowVO moneyfloevo = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setString(1, moneyflow_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					moneyfloevo = new MoneyflowVO();
					moneyfloevo.setMoneyflow_no(rs.getString("flow_no"));
					moneyfloevo.setMember_id(rs.getString("Member_id"));
					moneyfloevo.setMoneyflow_status(rs.getInt("Moneyflow_status"));
					moneyfloevo.setMoney(rs.getInt("Money"));
					moneyfloevo.setCharge_time(rs.getTimestamp("Charge_time"));
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
			return moneyfloevo;
		}

		@Override
		public List<MoneyflowVO> getAll() {
			// TODO Auto-generated method stub
			List<MoneyflowVO> list = new ArrayList();
			MoneyflowVO moneyfloevo = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					moneyfloevo = new MoneyflowVO();
					moneyfloevo.setMoneyflow_no(rs.getString("flow_no"));
					moneyfloevo.setMember_id(rs.getString("Member_id"));
					moneyfloevo.setMoneyflow_status(rs.getInt("Moneyflow_status"));
					moneyfloevo.setMoney(rs.getInt("Money"));
					moneyfloevo.setCharge_time(rs.getTimestamp("Charge_time"));
					list.add(moneyfloevo); // Store the row in the list
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
