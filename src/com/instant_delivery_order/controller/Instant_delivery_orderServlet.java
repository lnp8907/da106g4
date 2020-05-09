package com.instant_delivery_order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.instant_delivery_order.model.InstantDeliveryOrderService;
import com.instant_delivery_order.model.InstantDeliveryOrderVO;
import com.ordermanager.shop.OrderService;
import com.recipe_order_details.model.RecipeOrderDetailsService;
import com.recipe_order_details.model.RecipeOrderDetailsVO;

@WebServlet("/back-end/Instant_order/Instant_delivery_orderServlet")
public class Instant_delivery_orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Instant_delivery_orderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();

		if ("lookmore".equals(action)) {
			
			System.out.println("收到!LOOKMORE跳窗啟動");
			String pagemessage = (String) req.getParameter("pagemessage");
			System.out.println("收到pagemessage"+pagemessage);
			String whichPage = (String) req.getParameter("whichPage");
			System.out.println("收到whichPage"+whichPage);
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String ido_no = new String(req.getParameter("ido_no"));
				System.out.println("訂單編號:" + ido_no);
				OrderService Svc = new OrderService();
				InstantDeliveryOrderService IDSvc=new InstantDeliveryOrderService();
				
				InstantDeliveryOrderVO VO = IDSvc.getOneOrder(ido_no);
				System.out.println(VO + "VO放置成功");
			
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordvo", VO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
					failureView.forward(req, res);
					return;
				}

		
				/*************************** 準備轉交(Send the Success view) *************/
				RecipeOrderDetailsService ordSvc = new RecipeOrderDetailsService();
				
				List<RecipeOrderDetailsVO> list = ordSvc.getdetail(ido_no);
				req.setAttribute("dialoglist", list); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("opendialog", "lookmore");
//				String url="/back-end/shop_order/orderupatepage.jsp?whichPage="+whichPage;
//				String whichPage=req.getParameter("whichPage");

				String url = "/back-end/Instant_order/Instant_order_backendPage.jsp?whichPage="+whichPage+"&pagemessage"+pagemessage;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if (action.equals("cencelorder")) {
			
			String pagemessage = (String) req.getParameter("pagemessage");
			System.out.println("收到pagemessage"+pagemessage);
			String whichPage = (String) req.getParameter("whichPage");
			System.out.println("收到whichPage"+whichPage);
			System.out.println("取消訂單請求");
			String ido_no = (String) req.getParameter("ido_no");
			System.out.println("收到ID為:" + ido_no);
			InstantDeliveryOrderService svc = new InstantDeliveryOrderService();
			svc.changeOrderStatus(ido_no, 3);
			String url = "/back-end/Instant_order/Instant_order_backendPage.jsp?whichPage="+whichPage+"&pagemessage"+pagemessage;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
//................................................
		if (action.equals("all")) {

			System.out.println("全部清單請求");
			req.setAttribute("pagemessage", "all");
			String url = "/back-end/Instant_order/Instant_order_backendPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if (action.equals("traveling")) {
				System.out.println("運送中清單請求");
				req.setAttribute("pagemessage", "traveling");
				String url = "/back-end/Instant_order/Instant_order_backendPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

		}	
	//....................................................
			if ("getPositon".equals(action)) {
			String ido_no = req.getParameter("ido_no");
			System.out.println("配送位置請求");
			req.setAttribute("ido_no", ido_no); // 資料庫取出的empVO物件,存入req
			boolean openModal = true;
			req.setAttribute("openModal", openModal);
			req.setAttribute("pagemessage", "traveling");
			String url = "/back-end/Instant_order/Instant_order_backendPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
