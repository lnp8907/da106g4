package android.com.livestream.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import android.com.main.ImageUtil;
import android.com.member.model.Member;
import android.com.member.model.MemberDAO;
import android.com.member.model.MemberDAOImpl;
import android.com.livestream.model.Livestream;
import android.com.livestream.model.LivestreamDAO;
import android.com.livestream.model.LivestreamDAOImpl;

public class AnLivestreamServlet extends HttpServlet {
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
		String outStr = "";
		System.out.println("input: " + jsonIn);
		LivestreamDAO livestreamDao = new LivestreamDAOImpl();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		//Livestream livestreamd = gson.fromJson(jsonObject.get("").getAsString(), Livestream.class);
		
		
		//String chatName = (String) req.getAttribute("name123");//catch name

		if (action.equals("insert")) {
			System.out.println("aaaaa");
			String member_id = jsonObject.get("member_id").getAsString();
			String date = jsonObject.get("date").getAsString();
			String title = jsonObject.get("title").getAsString();
			String intro = jsonObject.get("intro").getAsString();
			String pic = jsonObject.get("pic").getAsString();
			byte[] image = Base64.getMimeDecoder().decode(pic);
			Livestream livestream = new Livestream();
			livestream.setMember_id(member_id);
			livestream.setLivestream_date(java.sql.Date.valueOf(date));
			livestream.setTitle(title);
			livestream.setIntroduction(intro);
			livestream.setPicture(image);
			livestreamDao.insert(livestream);
			//writeText(res, String.valueOf(livestreamDao.insert(livestream)));
		} 
		if ("findTrByStatus".equals(action)) {
			String member_id = jsonObject.get("member_id").getAsString();
			Livestream livestream = livestreamDao.findByStatus(member_id);
			JSONObject obj = new JSONObject();
			try {
				
				obj.put("livestream_id", livestream.getLivestream_id());
				obj.put("livestream_date", livestream.getLivestream_date());
				obj.put("status", livestream.getStatus());
				obj.put("introduction", livestream.getIntroduction());
				obj.put("title", livestream.getTitle());
			}catch (Exception e) {
				System.out.println("QQ");
			}
			writeText(res, livestream == null ? "" : obj.toString());
		}
		else if ("findByCategory".equals(action)) {
			int status = Integer.parseInt(jsonObject.get("status").getAsString());
			List<Livestream> lsList = livestreamDao.findByCategory(status);
			writeText(res, gson.toJson(lsList));
		}
		else if ("findTitleById".equals(action)) {
			String livestream_id = jsonObject.get("livestream_id").getAsString();
			Livestream title = livestreamDao.findTitleById(livestream_id);
			writeText(res, title == null ? "" : gson.toJson(title));
		}
		else if ("findCidByLsid".equals(action)) {
			MemberDAO memberDao = new MemberDAOImpl();
			String livestream_id = jsonObject.get("livestream_id").getAsString();
			Livestream livestreamVO = livestreamDao.findCidByLsid(livestream_id);
			String member_id =  livestreamVO.getMember_id();
			Member member = memberDao.findNickname(member_id);
			String nick= member.getNickname();
			JSONObject obj = new JSONObject();
			try {
				obj.put("member_id", member_id);
				obj.put("nickname", nick);
			}catch (Exception e) {
				System.out.println("QQ");
			}
			writeText(res, member_id == null ? "" : obj.toString());
		}
//		else if (action.equals("update")) {
//			Member member = gson.fromJson(jsonObject.get("member").getAsString(), Member.class);
//			writeText(res, String.valueOf(memberDao.update(member)));
//		}
		// 圖片請求
		if (action.equals("findPicByStatus")) {
			OutputStream os = res.getOutputStream();
			String livestream_id = jsonObject.get("livestream_id").getAsString();
			int photo = jsonObject.get("photo").getAsInt();
			
			byte[] image1 = livestreamDao.findPicByStatus(livestream_id);
			System.out.println(image1);
			if (image1 != null) {
				// 縮圖 in server side
				image1 = ImageUtil.shrink(image1, photo);
				res.setContentType("image/jpeg");
				res.setContentLength(image1.length);
			}
			os.write(image1);
		} else {
			writeText(res, "");
		}
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




