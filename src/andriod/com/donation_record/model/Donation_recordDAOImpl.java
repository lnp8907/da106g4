package andriod.com.donation_record.model;

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

public class Donation_recordDAOImpl implements DonationRecordDAO {

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
				//livestream_id暫時不送
	private static final String INSERT_STMT = 
			"INSERT INTO donation_record (donation_id,member_id,donation_date,donation_cost) VALUES "
			+ "(('DN-'||to_CHAR(current_timestamp,'YYYY-MM-DD')||'-'||LPAD(to_char(SQ_DONATION_ID.NEXTVAL),6,'0')), ?, CURRENT_TIMESTAMP, ?)";
		private static final String GET_ALL_STMT =
			"SELECT donation_id,livestream_id,member_id,donation_date,donation_cost FROM donation_record order by donation_id";
		private static final String GET_ONE_STMT = 
			"SELECT donation_id,livestream_id,member_id,donation_date,donation_cost FROM donation_record where livestream_id = ?";
		private static final String DELETE = 
			"DELETE FROM donation_record where donation_id = ?";
		private static final String UPDATE = 
			"UPDATE donation_record set livestream_id=?, member_id=?, donation_cost=? where donation_id = ?";

	@Override
	public boolean insert(Donation_record donation_RecordVO) {
		boolean bb = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			//pstmt.setString(1, donation_RecordVO.getLivestream_id());
			pstmt.setString(1, donation_RecordVO.getMember_id());
			pstmt.setInt(2, donation_RecordVO.getDonation_cost());

			//pstmt.executeUpdate();
			int count = pstmt.executeUpdate();
			bb = count > 0 ? true : false;
			System.out.println(count);
//			bb = true;
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

	@Override
	public void update(Donation_record donation_RecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, donation_RecordVO.getLivestream_id());
			pstmt.setString(2, donation_RecordVO.getMember_id());
			pstmt.setInt(3, donation_RecordVO.getDonation_cost());
			pstmt.setString(4, donation_RecordVO.getDonation_id());

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
	public void delete(String donation_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, donation_id);

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
	public Donation_record findByPrimaryKey(String livestream_id) {

		Donation_record donation_RecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, livestream_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				donation_RecordVO = new Donation_record();
				donation_RecordVO.setDonation_id(rs.getString("donation_id"));
				donation_RecordVO.setLivestream_id(rs.getString("livestream_id"));
				donation_RecordVO.setMember_id(rs.getString("member_id"));
				donation_RecordVO.setDonation_date(rs.getTimestamp("donation_date"));
				donation_RecordVO.setDonation_cost(rs.getInt("donation_cost"));
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
		return donation_RecordVO;
	}

	@Override
	public List<Donation_record> getAll() {
		List<Donation_record> list = new ArrayList<Donation_record>();
		Donation_record donation_RecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				donation_RecordVO = new Donation_record();
				donation_RecordVO.setDonation_id(rs.getString("donation_id"));
				donation_RecordVO.setLivestream_id(rs.getString("livestream_id"));
				donation_RecordVO.setMember_id(rs.getString("member_id"));
				donation_RecordVO.setDonation_date(rs.getTimestamp("donation_date"));
				donation_RecordVO.setDonation_cost(rs.getInt("donation_cost"));
				list.add(donation_RecordVO); 
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