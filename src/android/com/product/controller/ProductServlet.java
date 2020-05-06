package android.com.product.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.com.coupon.model.CouponDAO;
import android.com.coupon.model.CouponDAO_interface;
import android.com.main.ImageUtil;
import android.com.product.model.ProductDAO;
import android.com.product.model.ProductDAO_interface;
import android.com.product.model.ProductVO;
import android.com.recipe.model.RecipeDAO;
import android.com.recipe.model.RecipeDAO_interface;
import android.com.recipe.model.RecipeVO;

public class ProductServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		ProductDAO_interface dao = new ProductDAO();
		RecipeDAO recipeDAO = new RecipeDAO();
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if ("getAll".equals(action)) {
			System.out.println("商品getAll進來");
			List<ProductVO> productList = dao.getAll();
			System.out.println("商品getAll出來");
//			String recipe_id_json = jsonObject.get("recipe_id").getAsString();
//			if(recipe_id_json != null) {
//				System.out.println(recipe_id_json);
//				RecipeDAO_interface dao_recipe = new RecipeDAO();
//				List<RecipeVO> recipeList= dao_recipe.getAll();
//				writeText(res, gson.toJson(recipeList));
//			)

			writeText(res, gson.toJson(productList));
			// 圖片請求
		} else if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String recipe_id = jsonObject.get("recipe_id").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			String image = recipeDAO.getImage(recipe_id);
			// String 轉byte[];
			byte[] imageByByte = Base64.getDecoder().decode(image.split(",")[1]);
			if (imageByByte != null) {
				// 縮圖 in server side
				imageByByte = ImageUtil.shrink(imageByByte, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(imageByByte.length);
			}
			os.write(imageByByte);

		} else if ("getSomeByC_no".equals(action)) {
			String c_no = jsonObject.get("c_no").getAsString();
			List<String> product_ids = dao.getProduct_idByC_no(c_no);
			System.out.println(product_ids);
			List<ProductVO> productVOs = new ArrayList();

			for (String product_id : product_ids) {
				System.out.println(product_id);
				ProductVO productVO = dao.findByPrimaryKey(product_id);
				productVOs.add(productVO);
				System.out.println(productVOs);
			}
			writeText(res, gson.toJson(productVOs));
		} else if("getOneById".equals(action)) {
			String product_id = jsonObject.get("product_id").getAsString();
			ProductVO productVO = dao.findByPrimaryKey(product_id);
			writeText(res, gson.toJson(productVO));
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
		ProductDAO_interface dao = new ProductDAO();
		List<ProductVO> productList = dao.getAll();
		writeText(res, new Gson().toJson(productList));
	}

}
