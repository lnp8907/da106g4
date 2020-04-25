package com.other.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Base64;


public class UploadClobPhoto {

	private static final String URL = "jdbc:oracle:thin:@104.199.145.46:1521:xe";
	private static final String USER = "DA106_G4";
	private static final String PASSWORD = "DA106_G4";
	private static final String SQL = "UPDATE RECIPE SET RECIPE_PHOTO=? WHERE RECIPE_ID = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		
//		食譜
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);
						
			/*
			 * Base64是一種能將任意Binary資料用64種字元組合成字串的方法，而這個Binary資料和字串資料彼此之間是可以互相轉換的，十分方便。
			 * 在實際應用上，Base64除了能將Binary資料可視化之外，也常用來表示字串加密過後的內容
			 * 
			 * Java 8的java.util套件中，新增了Base64的類別，可以用來處理Base64的編碼與解碼
			 * Java 8提供的Base64擁有更好的效能，要比sun.misc套件提供的還要快至少11倍， * 比Apache Commons Codec提供的還要快至少3倍。
			 * 因此在Java上若要使用Base64，建議使用這個Java 8底下的java.util套件所提供的Base64類別。
			*/
						
			
			// setCLOB
			String base64Image = "data:image/jpeg;base64,";
			Base64.Encoder encoder = Base64.getEncoder(); 
			int b = 1;

			for (int i = 510001; i <= 510018; i++) {
				String recipe_id = String.valueOf(i);
				String filePath = "image/recipe/" + b++ + ".jpg";
				final String picCode = encoder.encodeToString(getPictureByteArray(filePath));//將圖片陣列編碼成BASE64
				
				final String pic = base64Image + picCode;
				pstmt.setString(1, pic);
				pstmt.setString(2, recipe_id);
				pstmt.executeUpdate();
				pstmt.clearParameters();
				// 清空裡面參數，重覆使用已取得的PreparedStatement物件
			}

			System.out.println("修改成功");

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
	}		

	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	
}

