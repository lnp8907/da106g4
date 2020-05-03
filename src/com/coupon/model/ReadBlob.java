package com.coupon.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadBlob {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "DA106_G4";
	private static final String PASSWORD = "DA106_G4";
	private static final String SQL = "SELECT C_PICTURE FROM COUPON WHERE C_NO = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);
			ResultSet rs2 = null;

			int b = 0;
			for (int i = 740001; i <= 740005; i++) {
				int c_no = i;
				String filePath = "C:/javawork/image"+ ++b +".png";
				// 2. getBytes
				pstmt.setInt(1, c_no);
				rs2 = pstmt.executeQuery();
				rs2.next();
				byte[] pic = rs2.getBytes(1);
				readPicture(pic, filePath);

				// 清空裡面參數，重覆使用已取得的PreparedStatement物件
				pstmt.clearParameters();
			}

			System.out.println("查詢成功");
			rs2.close();

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}

		// 另一種寫法
//		String sql_query = "SELECT C_PICTURE FROM COUPON WHERE C_NO = ?";
//		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//				PreparedStatement ps = connection.prepareStatement(sql_query);
//				FileOutputStream out = new FileOutputStream("C:/javawork/image01.png");) {
//			ps.setString(1, "740005");
//			try (ResultSet rs = ps.executeQuery();) {
//				if (rs.next()) {
//					// 取得資料庫內的圖並存檔
//					byte[] image = rs.getBytes(1);
//					out.write(image);
//					out.close();
//					System.out.println("Image saved...");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	// Handle with byte array data
	public static void readPicture(byte[] bytes, String filePath) throws IOException {
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

}
