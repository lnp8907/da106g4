package com.livestream.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadBlob {
	private static final String URL = "jdbc:oracle:thin:@localhost:49161:xe";
	private static final String USER = "DA106_G4";
	private static final String PASSWORD = "DA106_G4";
	private static final String SQL = "SELECT PICTURE FROM LIVESTREAM WHERE livestream_id = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);
			ResultSet rs2 = null;
			
			int b = 0;
			for (int i = 410007; i <= 410011; i++) {
				String livestream_id = String.valueOf(i);
				String filePath = "Output/" + ++b + ".jpg";
				// 2. getBytes
				pstmt.setString(1, livestream_id);
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
		String sql_query = "SELECT RECIPE_PHOTO FROM RECIPE WHERE recipe_id = ?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);
				FileOutputStream out = new FileOutputStream("Output/image03.png");) {
			ps.setString(1, "510012");
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					// 取得資料庫內的圖並存檔
					byte[] image = rs.getBytes(1);
					out.write(image);
					out.close();
					System.out.println("Image saved...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Handle with byte array data
	public static void readPicture(byte[] bytes ,String filePath) throws IOException {
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

}
