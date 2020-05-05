package android.com.livestream_collection.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.com.livestream_collection.model.Livestream_Collection;
import android.com.livestream_collection.model.Livestream_CollectionDAO;
import android.com.livestream_collection.model.Livestream_CollectionDAOImpl;
import android.com.member.model.Member;


public class CollectionServlet extends HttpServlet {
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
		Livestream_CollectionDAO lcDao = new Livestream_CollectionDAOImpl();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if (action.equals("findlsBymemberid")) {
			String member_id = jsonObject.get("member_id").getAsString();
			List<Livestream_Collection> lsId = lcDao.findByPrimaryKey(member_id);
			writeText(res, gson.toJson(lsId));
			//writeText(res, lsId == null ? "" : gson.toJson(lsId));
		}
//		else if (action.equals("findNameByAccount")) {
//			String account = jsonObject.get("account").getAsString();
//			Member member = memberDao.findNameByAccount(account);
//			writeText(res, member == null ? "" : gson.toJson(member));
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
