package com.shop.controller;


import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Product_photoReader extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println(req.getParameter("product_id"));
	
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
					String product_id=null;
			try {
				product_id = new String(req.getParameter("product_id").trim());
			} catch (NumberFormatException e) {
				product_id = null;
			}
	
			//-------]資料庫處理
		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
				"SELECT PRODUCT_PHOTO FROM PRODUCT WHERE PRODUCT_ID = '" +product_id+"'" );
			if (rs.next()) {
				//讀取該欄位圖片
				
				
				
				
				
				
				
				
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("PRODUCT_PHOTO"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream in=getServletContext().getResourceAsStream("back-end/shop_product/Nodata/notfind.png");
				byte[] b=new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in=getServletContext().getResourceAsStream("back-end/shop_product/Nodata/notfind.png");
			byte[] b=new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		
		
		}
	}

	public void init() throws ServletException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "DA106_G4";
		String passwd = "DA106_G4";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
}