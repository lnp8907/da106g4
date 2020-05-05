package android.com.recipe.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.com.main.ImageUtil;
import android.com.recipe.model.RecipeDAO;
import android.com.recipe.model.RecipeDAO_interface;
import android.com.recipe.model.RecipeVO;
import android.com.recipe.model.RecipeVO_A;

public class RecipeServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		RecipeDAO_interface dao = new RecipeDAO();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		Integer imageSize = jsonObject.get("imageSize").getAsInt();

		if ("getAll".equals(action)) {
			List<RecipeVO_A> recipeList = dao.getAllWithPrice();
			writeText(res, gson.toJson(recipeList));
			// 不用圖片請求 已經用BASE64包在物件裡
			// 為了效率之後要在這邊轉成byte[]在送出
			for (RecipeVO_A aRecipeVO_A : recipeList) {
				OutputStream os = res.getOutputStream();
				String base64 = aRecipeVO_A.getRecipe_photo();
				byte[] dedcodedString = Base64.getDecoder().decode(base64);
				if (dedcodedString != null) {
					// 縮圖 in server side
					dedcodedString = ImageUtil.shrink(dedcodedString, imageSize);
					res.setContentType("image/jpeg");
					res.setContentLength(dedcodedString.length);
				}
				os.write(dedcodedString);
			}
		} else {
			writeText(res, "");
		}
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RecipeDAO_interface dao = new RecipeDAO();
		List<RecipeVO> recipeList = dao.getAll();
		writeText(res, new Gson().toJson(recipeList));
	}

}
