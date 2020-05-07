package android.com.instant_delivery_order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import android.com.instant_delivery_order.model.InstantDeliveryOrderDAO;
import android.com.instant_delivery_order.model.InstantDeliveryOrderVO;
import android.com.instant_delivery_order.model.OrderProductVO;
import android.com.product.model.ProductDAO;
import android.com.product.model.ProductVO;
import android.com.recipe_order_details.model.RecipeOrderDetailsDAO;
import android.com.recipe_order_details.model.RecipeOrderDetailsVO;

public class OrderServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		InstantDeliveryOrderDAO instantDeliveryOrderDAO = new InstantDeliveryOrderDAO();
		String action = jsonObject.get("action").getAsString();
//		String member_id = jsonObject.get("member_id").getAsString();


		if ("add".equals(action)) {
			String orderJson = jsonObject.get("order").getAsString();  
			String member_id = jsonObject.get("member_id").getAsString();
			// 從Android Studio傳來的訂單主打VO
			InstantDeliveryOrderVO instantDeliveryOrder = gson.fromJson(orderJson, InstantDeliveryOrderVO.class);
			// 宣告裝訂單明細的list
			List<RecipeOrderDetailsVO> recipeOrderDetailsList = new ArrayList<>();
			// 從訂單主打裡拿裝商品的list跑foreach()
			List<OrderProductVO> orderProductVOList = instantDeliveryOrder.getOrderProductVOList();
			for (OrderProductVO orderProudctVO : orderProductVOList) {
				// 將訂單的商品資訊加到訂單明細物件的list裡
				RecipeOrderDetailsVO recipeOrderDetailsVO = new RecipeOrderDetailsVO();

				recipeOrderDetailsVO.setProduct_id(orderProudctVO.getProduct_id());
				recipeOrderDetailsVO.setQuantity(orderProudctVO.getQuantity());
				recipeOrderDetailsVO.setPrice(orderProudctVO.getProduct_price());
				recipeOrderDetailsList.add(recipeOrderDetailsVO);
			}

			InstantDeliveryOrderVO instantDeliveryOrderVO = null;
			// 藉由同時新增訂單主打和訂單明細順便回傳自增主鍵值
			String next_ido_no = instantDeliveryOrderDAO.insertWithDetails(instantDeliveryOrder,
					recipeOrderDetailsList);
			// 主鍵值不為空時(代表新增成功，前面的商品list有進資料庫)就用這個值查訂單主打VO，把前面的的訂單主打拿到的商品list裝進去，並回傳到Android
			// Studio
			if (next_ido_no != null) {
				instantDeliveryOrderVO = instantDeliveryOrderDAO.findByPrimaryKey(next_ido_no);
				instantDeliveryOrderVO.setOrderProductVOList(orderProductVOList);
			}
			writeText(res, gson.toJson(instantDeliveryOrderVO));

		} else if ("getAll".equals(action)) {
			String member_id = jsonObject.get("member_id").getAsString();
//			String start = jsonObject.get("start").getAsString();
//			String end = jsonObject.get("end").getAsString();
			List<InstantDeliveryOrderVO> instantDeliveryOrderVOList = instantDeliveryOrderDAO.getAll(member_id);
			if (instantDeliveryOrderVOList != null && !instantDeliveryOrderVOList.isEmpty()) {
				// JOIN Product data from RecipeOrderDetails

				RecipeOrderDetailsDAO recipeOrderDetailsDao = new RecipeOrderDetailsDAO();

				ProductDAO productDao = new ProductDAO();

				List<OrderProductVO> orderProductList = null;
				for (InstantDeliveryOrderVO order : instantDeliveryOrderVOList) {
					String ido_no = order.getIdo_no();

					orderProductList = new ArrayList<>();

					List<RecipeOrderDetailsVO> recipeOrderDetailsList = recipeOrderDetailsDao
							.findByPrimaryKey_IDO_no(ido_no);
					for (RecipeOrderDetailsVO recipeOrderDetailsVO : recipeOrderDetailsList) {
						ProductVO productVO = productDao.findByPrimaryKey(recipeOrderDetailsVO.getProduct_id());

						OrderProductVO orderProductVO = new OrderProductVO(productVO,
								recipeOrderDetailsVO.getQuantity());

						orderProductList.add(orderProductVO);
					}
					order.setOrderProductVOList(orderProductList);
				}
				writeText(res, gson.toJson(instantDeliveryOrderVOList));
			}

		} else if ("qrcode".equals(action)) {
			String ido_no = jsonObject.get("ido_no").getAsString();
			String o_status = jsonObject.get("o_status").getAsString();
			String p_method = jsonObject.get("p_method").getAsString();
			boolean b= instantDeliveryOrderDAO.finishOrder(ido_no, Integer.valueOf(o_status), Integer.valueOf(p_method));
			writeText(res, gson.toJson(b));
		} else if ("getDeliveryOrder".equals(action)) {
			String o_status = jsonObject.get("o_status").getAsString();
			List<InstantDeliveryOrderVO> list = new ArrayList();
			list = instantDeliveryOrderDAO.getDeliveryOrder(Integer.valueOf(o_status));
			writeText(res, gson.toJson(list));
		} else if ("updateStaffId".equals(action)) {
			String staff_id = jsonObject.get("staff_id").getAsString();
			String ido_no = jsonObject.get("ido_no").getAsString();
			String o_status = jsonObject.get("o_status").getAsString();
			
			boolean b= instantDeliveryOrderDAO.updateStaffId(staff_id, ido_no, o_status);
			writeText(res, gson.toJson(b));
	
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
