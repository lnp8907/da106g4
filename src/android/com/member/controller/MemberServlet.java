package android.com.member.controller;

import android.com.main.ImageUtil;
import android.com.member.model.Member;
import android.com.member.model.MemberDAO;
import android.com.member.model.MemberDAOImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MemberServlet extends HttpServlet {
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
		MemberDAO memberDao = new MemberDAOImpl();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if (action.equals("isMember")) {
			String account = jsonObject.get("account").getAsString();
			String password = jsonObject.get("password").getAsString();
			writeText(res,	String.valueOf(memberDao.isMember(account, password)));
		} else if (action.equals("isUserIdExist")) {
			String account = jsonObject.get("account").getAsString();
			writeText(res, String.valueOf(memberDao.isUserIdExist(account)));
		} 
		else if (action.equals("add")) {
			Member member = gson.fromJson(jsonObject.get("member").getAsString(), Member.class);
			writeText(res, String.valueOf(memberDao.add(member)));
		} 
		else if (action.equals("findNameByAccount")) {
			String account = jsonObject.get("account").getAsString();
			Member member = memberDao.findNameByAccount(account);
			writeText(res, member == null ? "" : gson.toJson(member));
		}
		
		else if (action.equals("findBalanceByAccount")) {
			String account = jsonObject.get("account").getAsString();
			Member member = memberDao.findBalanceByAccount(account);
			writeText(res, member == null ? "" : gson.toJson(member));
		}
		else if (action.equals("donate")) {
			//System.out.println("donate method");
			String account = jsonObject.get("account").getAsString();
			writeText(res, String.valueOf(memberDao.donate(account)));
		} 
		else if (action.equals("getDonate")) {
			//System.out.println("donate method");
			String c_member_id = jsonObject.get("c_member_id").getAsString();
			writeText(res, String.valueOf(memberDao.getDonate(c_member_id)));
		} 
		else if (action.equals("findOneByAccountAndPassword")) {
			String account = jsonObject.get("account").getAsString();
			String password = jsonObject.get("password").getAsString();
			Member member = memberDao.findOneByAccountAndPassword(account, password);
			//req.setAttribute("name123", member.getName()); 
			writeText(res, member == null ? "" : gson.toJson(member));
		} 
		else if (action.equals("findIdByAccount")) {
			String account = jsonObject.get("account").getAsString();
			Member member = memberDao.findIdByAccount(account);
			//req.setAttribute("name123", member.getName()); 
			writeText(res, member == null ? "" : gson.toJson(member));
		} 
		else if (action.equals("findPicByAccount")) {
			OutputStream os = res.getOutputStream();
			String account = jsonObject.get("account").getAsString();
			int photo = jsonObject.get("photo").getAsInt();
			
			byte[] image = memberDao.findPhotoByAccount(account);
			System.out.println(image);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, photo);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			os.write(image);
		} else {
			writeText(res, "");
		}
		
//		if (action.equals("findPicByStatus")) {
//			OutputStream os = res.getOutputStream();
//			String status = jsonObject.get("status").getAsString();
//			int photo = jsonObject.get("photo").getAsInt();
//			
//			byte[] image1 = memberDao.findPicByStatus(status);
//			System.out.println(image1);
//			if (image1 != null) {
//				// 縮圖 in server side
//				image1 = ImageUtil.shrink(image1, photo);
//				res.setContentType("image/jpeg");
//				res.setContentLength(image1.length);
//			}
//			os.write(image1);
//		} else {
//			writeText(res, "");
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
