package com.instant_delivery_order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.instant_delivery_order.model.InstantDeliveryOrderService;
import com.product_browsing_history.model.Product_browing_historyService;

/**
 * Servlet implementation class Instant_delivery_orderServlet
 */
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

		if (action.equals("cencelorder")) {

			System.out.println("取消訂單請求");
			String ido_no = (String) req.getParameter("ido_no");
			System.out.println("收到ID為:" + ido_no);
			InstantDeliveryOrderService svc = new InstantDeliveryOrderService();
			svc.changeOrderStatus(ido_no, 3);
			String url = "/back-end/Instant_order/Instant_order_backendPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

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
