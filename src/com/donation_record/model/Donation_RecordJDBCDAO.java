package com.donation_record.model;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class Donation_RecordJDBCDAO implements DonationRecordDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:XE";
	String userid = "DA106_G4";
	String passwd = "DA106_G4";

	private static final String INSERT_STMT = 
		"INSERT INTO donation_record (donation_id,livestream_id,member_id,donation_date,donation_cost) VALUES (?, ?, ?, TO_TIMESTAMP(SYSDATE,'DD-MM-YYYY HH24:MI:SS'), ?)";
	// (('DN-'||LPAD (TO_TIMESTAMP(SYSDATE,'DD-MM-YYYY HH24:MI:SS')) '-'||LAPD(to_char(SQ_DONATION_ID.NEXTVAL), 5, '0')), ?, ?, ?, ?)"
	private static final String GET_ALL_STMT =
		"SELECT donation_id,livestream_id,member_id,donation_date,donation_cost FROM donation_record order by donation_id";
	private static final String GET_ONE_STMT = 
		"SELECT donation_id,livestream_id,member_id,donation_date,donation_cost FROM donation_record where livestream_id = ?";
	private static final String DELETE = 
		"DELETE FROM donation_record where donation_id = ?";
	private static final String UPDATE = 
		"UPDATE donation_record set livestream_id=?, member_id=?, donation_date=?, donation_cost=? where donation_id = ?";

	@Override
	public void insert(Donation_RecordVO donation_RecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, donation_RecordVO.getDonation_id());
			pstmt.setString(2, donation_RecordVO.getLivestream_id());
			pstmt.setString(3, donation_RecordVO.getMember_id());
			pstmt.setTimestamp(4, donation_RecordVO.getDonation_date());
			pstmt.setInt(5, donation_RecordVO.getDonation_cost());
			
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
	public void update(Donation_RecordVO donation_RecordVO) {
//
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, donation_RecordVO.getLivestream_id());
			pstmt.setString(2, donation_RecordVO.getMember_id());
			pstmt.setTimestamp(3, donation_RecordVO.getDonation_date());
			pstmt.setInt(4, donation_RecordVO.getDonation_cost());
			pstmt.setString(5, donation_RecordVO.getDonation_id());


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
//
	@Override
	public void delete(String donation_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, donation_id);

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
	public Donation_RecordVO findByPrimaryKey(String livestream_id) {

		Donation_RecordVO donation_RecordVO = null;
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
				
				donation_RecordVO = new Donation_RecordVO();
				donation_RecordVO.setDonation_id(rs.getString("donation_id"));
				donation_RecordVO.setLivestream_id(rs.getString("livestream_id"));
				donation_RecordVO.setMember_id(rs.getString("member_id"));
				donation_RecordVO.setDonation_date(rs.getTimestamp("donation_date"));
				donation_RecordVO.setDonation_cost(rs.getInt("donation_cost"));
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
		return donation_RecordVO;
	}

	@Override
	public List<Donation_RecordVO> getAll() {
		List<Donation_RecordVO> list = new ArrayList<Donation_RecordVO>();
		Donation_RecordVO donation_RecordVO = null;

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
				
				donation_RecordVO = new Donation_RecordVO();
				donation_RecordVO.setDonation_id(rs.getString("donation_id"));
				donation_RecordVO.setLivestream_id(rs.getString("livestream_id"));
				donation_RecordVO.setMember_id(rs.getString("member_id"));
				donation_RecordVO.setDonation_date(rs.getTimestamp("donation_date"));
				donation_RecordVO.setDonation_cost(rs.getInt("donation_cost"));
				list.add(donation_RecordVO); // Store the row in the list
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

		Donation_RecordJDBCDAO dao = new Donation_RecordJDBCDAO();

		// 新增
		Donation_RecordVO livestreamVO1 = new Donation_RecordVO();
		livestreamVO1.setDonation_id("DN-2020-02-14-000001");
		livestreamVO1.setLivestream_id("410012");
		livestreamVO1.setMember_id("810009");
		livestreamVO1.setDonation_date(Timestamp.valueOf("2020-02-14 21:21:21"));
		livestreamVO1.setDonation_cost(1000);
		dao.insert(livestreamVO1);

		// 修改
//		Donation_RecordVO livestreamVO2 = new Donation_RecordVO();
//		livestreamVO2.setDonation_id("DN-2020-02-14-000001");
//		livestreamVO2.setLivestream_id("410012");
//		livestreamVO2.setMember_id("810006");
//		livestreamVO2.setDonation_date(Timestamp.valueOf("2020-02-14 21:21:21"));
//		livestreamVO2.setDonation_cost(1000);
//		dao.update(livestreamVO2);
//
//		// 刪除
//		dao.delete("410000");
//
//		// 查詢 
//		Donation_RecordVO livestreamVO3 = dao.findByPrimaryKey("410001");
//		livestreamVO2.setDonation_id("DN-2020-02-14-000001");
//		livestreamVO2.setLivestream_id("410012");
//		livestreamVO2.setMember_id("810006");
//		livestreamVO2.setDonation_date(Timestamp.valueOf("2020-02-14 21:21:21"));
//		livestreamVO2.setDonation_cost(1000);
//		System.out.println("---------------------");
//
//		// 查詢
//		List<Donation_RecordVO> list = dao.getAll();
//		for (Donation_RecordVO aLive : list) {
//			livestreamVO2.setDonation_id("DN-2020-02-14-000001");
//		livestreamVO2.setLivestream_id("410012");
//		livestreamVO2.setMember_id("810006");
//		livestreamVO2.setDonation_date(Timestamp.valueOf("2020-02-14 21:21:21"));
//		livestreamVO2.setDonation_cost(1000);
//			System.out.println();
//		}
	}

}