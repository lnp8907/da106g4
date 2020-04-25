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
import javax.websocket.Session;

import com.order_detail.model.Order_detailJDBCDAO;
import com.order_detail.model.Order_detailVO;
import com.ordermanager.shop.OrderDetailService;
import com.ordermanager.shop.OrderService;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.shop_order.model.Shop_orderJDBCDAO;
import com.shop_order.model.Shop_orderVO;

/**
 * Servlet implementation class detail
 */
@WebServlet("/detail")
public class detailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public detailServlet() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		// 刪除
		if ("detaildelete".equals(action)) { // 來自listAllEmp.jsp
			System.out.println("刪除程序啟動");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String order_no = new String(req.getParameter("order_no"));
				System.out.println(order_no);
				String product_id = new String(req.getParameter("product_id"));
				System.out.println(product_id);
				/*************************** 2.開始刪除資料 ***************************************/
				OrderDetailService detaildSvc = new OrderDetailService();
				detaildSvc.deleteOne(order_no, product_id);
				OrderService ordersvc = new OrderService();
				ordersvc.updatetotal(order_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/shop_order/listOneShopOrder.jsp";
				System.out.println(order_no);
				req.setAttribute("order_no", order_no);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/listOneShopOrder.jsp");
				failureView.forward(req, res);
			}
		}
		// 前往更改商品頁面
		if ("detailUpdatepage".equals(action)) { // 來自listAllEmp.jsp的請求
			System.out.println("前往修改頁面");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String order_no = new String(req.getParameter("order_no"));
				String product_id = new String(req.getParameter("product_id"));

				System.out.println(order_no);
				System.out.println(product_id);

				/*************************** 2.開始查詢資料 ****************************************/
				OrderDetailService ordSvc = new OrderDetailService();
				Order_detailVO detailvo = ordSvc.getone(order_no, product_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("detailvo", detailvo); // 資料庫取出的empVO物件,存入req
				req.setAttribute("order_no", detailvo.getorder_no());
				String url = "/back-end/shop_order/detailupatepage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/listOneShopOrder.jsp");
				failureView.forward(req, res);
			}
		}
		// 送出修改
		if ("DetailUpdate".equals(action)) {

			System.out.println("修改");
			String order_no = new String(req.getParameter("order_no"));
			String product_id = new String(req.getParameter("product_id"));
//底價
			
			Integer minprice = new Integer(req.getParameter("minprice"));
			System.out.println("底價");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				System.out.println(req.getParameter("quantity"));
				Integer quantity = 0;
				try {
					quantity = new Integer(req.getParameter("quantity").trim());
				} catch (NumberFormatException e) {
					quantity = 1;
					errorMsgs.add("數量請填數字或者是你調皮用了一個你買不起的數量");
				}
				Integer price = 0;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = minprice;
					errorMsgs.add("價格請填數字或者是你調皮用了一個你買不起的價格");
				}

				if (quantity > 100) {
					errorMsgs.add("這裡不是大盤商耶...");
				} else if (quantity < 0 || quantity == 0) {
					errorMsgs.add("付錢給別人?別鬧");

				}

				if (price > 1000) {
					errorMsgs.add("商品超過1000な....だと");
					

				}	else if(price<0) {
					quantity = 1;

					errorMsgs.add("別鬧 沒有負數的啦");
					
				} 
				
				
				else if (price < minprice) {
					errorMsgs.add("商品小於定價....不好吧");

				}
			

				Order_detailJDBCDAO dao = new Order_detailJDBCDAO();
				Order_detailVO VO = dao.findByPrimaryKey(order_no, product_id);

				VO.setPrice(price);
				VO.setQuantity(quantity);
				System.out.println(VO);
				if (!errorMsgs.isEmpty()) {
					System.out.println("進入錯誤格式");
					req.setAttribute("detailvo", VO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_order/detailupatepage.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				OrderDetailService Svc = new OrderDetailService();
//				VO = Svc.upOneDetail(quantity, price, order_no, product_id);
				Svc.upOneDetail(quantity, price, order_no, product_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

				req.setAttribute("order_no", order_no);
				req.setAttribute("ordvo", VO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/shop_order/listOneShopOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/detailupatepage.jsp");

				failureView.forward(req, res);
			}
		}
//前往新增頁面
		if ("addDetail".equals(action)) {
			System.out.println("轉向頁面");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String order_no = new String(req.getParameter("order_no"));

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				System.out.println("訂單編號:" + order_no);
				req.setAttribute("order_no", order_no);
				String url = "/back-end/shop_order/addDetailPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得明細:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/listOneShopOrder.jsp");
				failureView.forward(req, res);
			}
		}
//開始新增商品
		if ("sendaddDetail".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("獲取新增商品請求");
			String order_no = req.getParameter("order_no");
			String product_id = req.getParameter("product_id");
			OrderDetailService detailSvc = new OrderDetailService();

			List<String> errorMsgs = new LinkedList<String>();
			Order_detailVO detailvo = new Order_detailVO();
			List<Order_detailVO> alldetaillist = detailSvc.getOneOrderall(order_no);

			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("TRY和CATCH");
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				for (Order_detailVO s : alldetaillist) {
					String isproduct = s.getProduct_id();
					if (product_id.equals(isproduct)) {
						System.out.println("重複囉");
						errorMsgs.add("請勿增加重複的商品...");
					}

				}
				detailvo.setorder_no(order_no);
				detailvo.setProduct_id(product_id);
				System.out.println(order_no);
				System.out.println(product_id);

				Integer quantity = 0;
				try {
					quantity = new Integer(req.getParameter("quantity").trim());
				} catch (NumberFormatException e) {
					quantity = 0;
					errorMsgs.add("數量請填數字或者是你調皮用了一個你買不起的數量");
				}
				Integer price = 0;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("薪水請填數字或者是你調皮用了一個你買不起的價格");
				}

				if (quantity > 1000) {
					errorMsgs.add("不能賣這麼多啦不是大盤商耶...");
				} else if (quantity < 0) {
					errorMsgs.add("負數?別鬧");

				}

				if (price > 1000) {
					errorMsgs.add("商品超過1000な....だと");

				} else if (price < 0) {
					errorMsgs.add("商品10塊是賠本吧");

				}
				detailvo.setQuantity(quantity);
				detailvo.setPrice(price);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("order_detailvo", detailvo);
					req.setAttribute("order_no", order_no);
					// 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/addDetailPage.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				System.out.println("開始新增");
				detailvo = detailSvc.adddetail(quantity, price, order_no, product_id);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("order_no", order_no);
				String url = "/back-end/shop_order/listOneShopOrder.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				req.setAttribute("order_no", order_no);

				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/addDetailPage.jsp");
				failureView.forward(req, res);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

}
