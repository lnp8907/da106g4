package android.com.staff.controller;

import android.com.staff.model.Staff;
import android.com.staff.model.StaffDAO;
import android.com.staff.model.StaffDAOImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class StaffServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		StaffDAO staffDao = new StaffDAOImpl();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if (action.equals("isMember")) {
			String staff_id = jsonObject.get("staff_id").getAsString();
			String staff_password = jsonObject.get("staff_password").getAsString();
			writeText(res,	String.valueOf(staffDao.isMember(staff_id, staff_password)));
		} else if (action.equals("isUserIdExist")) {
			String staff_id = jsonObject.get("staff_id").getAsString();
			writeText(res, String.valueOf(staffDao.isUserIdExist(staff_id)));
		} 
//		else if (action.equals("add")) {
//			Member member = gson.fromJson(jsonObject.get("member").getAsString(), Member.class);
//			writeText(res, String.valueOf(memberDao.add(member)));
//		} 
		else if (action.equals("findById")) {
			String staff_id = jsonObject.get("staff_id").getAsString();
			Staff staff = staffDao.findById(staff_id);
			writeText(res, staff == null ? "" : gson.toJson(staff));
		} 
//		else if (action.equals("update")) {
//			Member member = gson.fromJson(jsonObject.get("member").getAsString(), Member.class);
//			writeText(res, String.valueOf(memberDao.update(member)));
//		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);

	}
}
