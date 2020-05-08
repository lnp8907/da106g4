

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadBlob {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "DA106_G4";
	private static final String PASSWORD = "DA106_G4";
	private static final String SQL = "UPDATE COURSE SET COURSE_PHOTO=? WHERE COURSE_ID = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		
//		食譜
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(SQL);
//
//			// 2. setBytes
//			int b = 0;
//			for (int i = 510000; i <= 510034; i++) {
//				String recipe_id = String.valueOf(i);
//				String filePath = "image/recipe/" + ++b + ".jpg";
//				byte[] pic = getPictureByteArray(filePath);
//				pstmt.setBytes(1, pic);
//				pstmt.setString(2, recipe_id);
//				pstmt.executeUpdate();
//				pstmt.clearParameters();
//				// 清空裡面參數，重覆使用已取得的PreparedStatement物件
//			}
//
//			System.out.println("修改成功");
//
//		} catch (ClassNotFoundException ce) {
//			System.out.println(ce);
//		} catch (SQLException se) {
//			System.out.println(se);
//		} catch (IOException ie) {
//			System.out.println(ie);
//		} finally {
//			// 依建立順序關閉資源 (越晚建立越早關閉)
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					System.out.println(se);
//				}
//			}
//
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException se) {
//					System.out.println(se);
//				}
//			}
//		}
		
		//課程假資料
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);

			// 2. setBytes
			int b = 0;
			for (int i = 210001; i <= 210010; i++) {
				String recipe_id = String.valueOf(i);
				String filePath = "course/" + ++b + ".jpg";
				byte[] pic = getPictureByteArray(filePath);
				pstmt.setBytes(1, pic);
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

	// 使用byte[]既傳統又沒效率的方式XDXD自己研發
	public static byte[] getPictureByteArray2(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[fis.available()];
		while (fis.available() != 0) {
			fis.read(buffer);
		}
		fis.close();

		return buffer;
	}
	
}
