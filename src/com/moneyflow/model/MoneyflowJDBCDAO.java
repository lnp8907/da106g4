package com.moneyflow.model;

import java.util.*;

import com.member_follow.model.Member_followJDBCDAO;
import com.member_follow.model.Member_followVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;





public class MoneyflowJDBCDAO implements MoneyflowDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHAGESTATUS);
			pstmt.setInt(1, status);
			pstmt.setString(2, moneyflolw_no);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHAGESTATUS);
			pstmt.setInt(1, moneyfloevo.getMoneyflow_status());
			pstmt.setString(2, moneyfloevo.getMember_id());
		

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, moneyfloevo.getMember_id());			
			pstmt.setInt(2, moneyfloevo.getMoneyflow_status());
			pstmt.setInt(3, moneyfloevo.getMoney());
			

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, moneyfloevo.getMember_id());
			pstmt.setInt(2, moneyfloevo.getMoneyflow_status());
			pstmt.setInt(3, moneyfloevo.getMoney());
			pstmt.setString(4, moneyfloevo.getMember_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, moneyflow_no);
			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, moneyflow_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				moneyfloevo = new MoneyflowVO();
				moneyfloevo.setMoneyflow_no(rs.getString("Moneyflow_no"));
				moneyfloevo.setMember_id(rs.getString("Member_id"));
				moneyfloevo.setMoneyflow_status(rs.getInt("Moneyflow_status"));
				moneyfloevo.setMoney(rs.getInt("Money"));
				moneyfloevo.setCharge_time(rs.getTimestamp("Charge_time"));
			}
			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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
		List<MoneyflowVO> list = new ArrayList<MoneyflowVO>();
		MoneyflowVO moneyfloevo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				moneyfloevo = new MoneyflowVO();
				moneyfloevo.setMoneyflow_no(rs.getString("Moneyflow_no"));
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		MoneyflowJDBCDAO dao=new MoneyflowJDBCDAO();
		
		for(int i=0;i<6;i++) {
			int ii=i+1;
			MoneyflowJDBCDAO dao1=new MoneyflowJDBCDAO();
			MoneyflowVO vo=new MoneyflowVO();
			Random r = new Random();
			int i1 = r.nextInt(9999)+100 ;
			int i2 = r.nextInt(2)+1;
			i2--;
			vo.setMember_id("81000"+ii);
			vo.setMoneyflow_status(i2);
			vo.setMoney(i1);
			dao1.insert(vo);
		}

		
		

				
			//更新
		

			//刪除



//			EmpVO empVO3 = dao.findByPrimaryKey("810000");
//			System.out.print(empVO3.getMember_id() + ",");
//			System.out.print(empVO3.getAccount() + ",");
//			System.out.print(empVO3.getPassword() + ",");
//			System.out.print(empVO3.getMember_name() + ",");
//			System.out.print(empVO3.getGender() + ",");
//			System.out.print(empVO3.getBirthday() + ",");
//			System.out.println(empVO3.getCellphone()+ ",");	
//			System.out.print(empVO3.getEmail() + ",");
//			System.out.print(empVO3.getNickname() + ",");
//			System.out.print(empVO3.getMEMBER_PHOTO() + ",");
//			System.out.print(empVO3.getValidation() + ",");
//			System.out.print(empVO3.geyLicense() + ",");
//			System.out.print(empVO3.getMember_status() + ",");
//			System.out.println(empVO3.getMember_address() + ",");	
//			System.out.print(empVO3.getMember_creditcard() + ",");
//			System.out.print(empVO3.getBalance() + ",");
//			System.out.println(empVO3.getChiefapply_status());
//			System.out.println("---------------------");

			// �d��
//			List<MoneyflowVO> list = dao.getAll();
//			for (MoneyflowVO aEmp : list) {
//				System.out.print(aEmp.getMoneyflow_no() + ",");
//				System.out.print(aEmp.getMember_id() + ",");
//				System.out.print(aEmp.getMoneyflow_status() + ",");
//				System.out.print(aEmp.getMoney() + ",");
//				System.out.print(aEmp.getCharge_time() + ",");
//				System.out.println();
			}	
	}

