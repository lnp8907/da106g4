package android.com.coupon.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.com.coupon.model.CouponDAO;
import android.com.coupon.model.CouponVO;
import android.com.main.ImageUtil;
import android.com.product.model.ProductDAO;
import android.com.product.model.ProductVO;

public class CouponServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		CouponDAO dao = new CouponDAO();
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

		if ("getAll".equals(action)) {
			List<CouponVO> couponList = dao.getAll();
			writeText(res, gson.toJson(couponList));
			// 圖片請求
		} else if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String c_no = jsonObject.get("c_no").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = dao.getImage(c_no);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			os.write(image);

		} else if ("discount".equals(action)) {
			ProductDAO pDAO = new ProductDAO();			
			String coupon_code = jsonObject.get("coupon_code").getAsString();
			CouponVO couponVO = dao.getDiscountByCode(coupon_code);
			
			
			
			
			List<String> product_ids = pDAO.getProduct_idByC_no(coupon_code);
			List<ProductVO> productVOs = new ArrayList();

			for (String product_id : product_ids) {
//				System.out.println(product_id);
				ProductVO productVO = pDAO.findByPrimaryKey(product_id);
				productVOs.add(productVO);
//				System.out.println(productVOs);
			}
			
			res.setContentType("text/html; charset=UTF-8");// 輸出的Type設定
			PrintWriter out = res.getWriter();// 拿輸出的工具
			out.print(gson.toJson(couponVO));
			out.close();
			System.out.println("couponVO: " + gson.toJson(couponVO));
		}

		else {
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
		CouponDAO dao = new CouponDAO();
		List<CouponVO> couponList = dao.getAll();
		writeText(res, new Gson().toJson(couponList));
	}

}
