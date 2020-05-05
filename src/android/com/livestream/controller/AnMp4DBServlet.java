package android.com.livestream.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import android.com.main.MyData;

import android.com.livestream.model.LivestreamDAO;
import android.com.livestream.model.LivestreamDAOImpl;


public class AnMp4DBServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String livestream_id = req.getParameter("livestream_id");
//		Gson gson = new Gson();
//		BufferedReader br = req.getReader();
//		StringBuilder jsonIn = new StringBuilder();
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			jsonIn.append(line);
//		}
//		String outStr = "";
////		System.out.println("input: " + jsonIn);
//		LivestreamDAO livestreamDao = new LivestreamDAOImpl();
//		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		
		
//		Strinlivestream_id = jsonObject.get("livestream_id").getAsString();
//		String livestream_id = "410013";
		InputStream is = findMp4ById(livestream_id);//whyyyy
		BufferedInputStream bf = new BufferedInputStream(is);
		int size = bf.available();
		byte[] buffer = new byte[size];
		bf.read(buffer, 0, size);
		
		res.setContentType("video/mp4");
		res.setContentLength(buffer.length);
		ServletOutputStream out = res.getOutputStream();
		out.write(buffer);
		out.close();
		System.out.println("ok");
//		System.out.println(livestream_id);
//		System.out.println(size);
//		ServletContext context = getServletContext();
//		InputStream is = context.getResourceAsStream("/media/GoT.mp4");
//		int size = is.available();
//		byte[] buffer = new byte[size];
//		is.read(buffer, 0, size);
//
//		res.setContentType("video/mp4");
//		res.setContentLength(buffer.length);
//		ServletOutputStream out = res.getOutputStream();
//		out.write(buffer);
//		out.close();
	}

	public InputStream findMp4ById(String livestream_id) {
		try {
			//Class.forName("java:comp/env/jdbc/DA106_G4");
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "SELECT video FROM livestream WHERE livestream_id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setString(1, livestream_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// video file
				//InputStream videoStream = rs.getBinaryStream(1);
				InputStream videoStream = new ByteArrayInputStream(rs.getBytes(1));//byte to Binary
				return videoStream;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
