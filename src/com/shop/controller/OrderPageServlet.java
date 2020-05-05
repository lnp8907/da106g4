package com.shop.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OrderPageServlet
 */
@WebServlet("/back-end/shop_order/OrderPage")
public class OrderPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("處理換頁");
		List<String> errorMsgs = new LinkedList<String>();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		if ("waitpage".equals(action)) {
			System.out.println("收到!準備前往待出貨訂單");
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String url = "/back-end/shop_order/Order_backendPage.jsp";

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("pagemessage", "waitpage");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("traveling".equals(action)) {
			System.out.println("收到!準備前往待出貨訂單");
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String url = "/back-end/shop_order/Order_backendPage.jsp";

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("pagemessage", "traveling");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("changepage".equals(action)) {
			String pagemessage = req.getParameter("pagemessage");
			System.out.println("位置訊息" + pagemessage);
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String url = "/back-end/shop_order/Order_backendPage.jsp";

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				if ("waitpage".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);
					req.setAttribute("Order_statusPage", "0");


				}
				else if ("traveling".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);

					req.setAttribute("Order_statusPage", "1");

				}
				else if ("complete".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);

					req.setAttribute("Order_statusPage", "2");

				}
				else	if ("cancel".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);

					req.setAttribute("Order_statusPage", "3");

				}
System.out.println("開始前往"+pagemessage);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
				failureView.forward(req, res);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
