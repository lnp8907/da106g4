package com.shop.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.order_detail.model.Order_detailVO;
import com.ordermanager.shop.OrderService;
import com.shop_order.model.Shop_orderJDBCDAO;
import com.shop_order.model.Shop_orderVO;

@WebServlet("/front-end/ShopPage/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setCharacterEncoding("UTF-8");
		String str;
		String action = req.getParameter("action");

		if ("changestatus".equals(action)) {
			//
			System.out.println("收到!開始修改訂單狀態");

			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String order_no = new String(req.getParameter("order_no"));
				System.out.println("訂單狀態" +order_no );
				
				Integer order_status = Integer.valueOf(req.getParameter("order_status"));
				System.out.println("訂單狀態" +order_status );
				String whichPage = new String(req.getParameter("whichPage"));

			
			

		
				Shop_orderJDBCDAO orderdao = new Shop_orderJDBCDAO();
				Shop_orderVO VO = orderdao.findByPrimaryKey(order_no);

				VO.setOrder_status(order_status);
				System.out.println("設置狀態:" + order_status);
				System.out.println("VO放置成功");

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordvo", VO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

			
				
				System.out.println("進入修改程序");
				/*************************** 2.開始修改資料 *****************************************/
				OrderService Svc = new OrderService();

				VO = Svc.changestatus(order_status, order_no);
				System.out.println("開始修改資料");

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("ordvo", VO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/shop_order/Order_backendPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		HttpSession session = req.getSession();
		if ("lookmore".equals(action)) {
			System.out.println("收到!LOOKMORE跳窗啟動");
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String order_no = new String(req.getParameter("order_no"));
				System.out.println("訂單編號:" + order_no);
				OrderService Svc = new OrderService();
				Shop_orderVO VO = Svc.getOneOrder(order_no);
				System.out.println(VO + "VO放置成功");
				String pagemessage = new String(req.getParameter("Order_statusPage"));
				System.out.println("路徑指令:"+pagemessage);
				System.out.println("路徑" + req.getParameter("url"));
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordvo", VO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if ("waitpage".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);
					req.setAttribute("Order_statusPage", "waitpage");


				}
				else if ("traveling".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);

					req.setAttribute("Order_statusPage", "traveling");

				}
				else if ("complete".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);

					req.setAttribute("Order_statusPage", "complete");

				}
				else	if ("cancel".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);

					req.setAttribute("Order_statusPage", "cancel");

				}
				/*************************** 準備轉交(Send the Success view) *************/
				OrderService ordSvc = new OrderService();
				List<Order_detailVO> list = ordSvc.getdetail(order_no);
				req.setAttribute("dialoglist", list); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("opendialog", "lookmore");
//				String url="/back-end/shop_order/orderupatepage.jsp?whichPage="+whichPage;
//				String whichPage=req.getParameter("whichPage");

				String url = "/back-end/shop_order/Order_backendPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateAddress".equals(action)) {
			System.out.println("收到!跳窗啟動");
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String order_no = new String(req.getParameter("order_no"));
				System.out.println("訂單編號:" + order_no);
				OrderService Svc = new OrderService();
				Shop_orderVO VO = Svc.getOneOrder(order_no);
				System.out.println(VO + "VO放置成功");

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordvo", VO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
					failureView.forward(req, res);
					return;
				}	
				/*************************** 準備轉交(Send the Success view) *************/
				req.setAttribute("dialogordvo", VO); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("opendialog", "addressupdate");
				String url = "/back-end/shop_order/Order_backendPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
				failureView.forward(req, res);
			}
		}

//結帳

		if ("addorder".equals(action)) {
			System.out.println(action);
			Vector<Order_detailVO> productlist = (Vector<Order_detailVO>) session.getAttribute("checkoutlist");
			Integer pay_type = Integer.valueOf(req.getParameter("pay_type"));

			String member_id = req.getParameter("member_id");
			System.out.println("網頁獲取" + req.getParameter("member_id"));
			System.out.println("member_id:" + member_id);
			System.out.println("SESSION獲取member_id:" + session.getAttribute("member_id"));

			MemberService svc1 = new MemberService();
			Integer total = productlist.stream().mapToInt(p -> p.getPrice() * p.getQuantity()).sum();
			System.out.println("程式算的金額為:" + total);

			if (pay_type == 0) {
				
				MemberService msvc= new MemberService();
				MemberVO memberVO = msvc.getOneMember(member_id);

				System.out.println("現有" + memberVO.getBalance());
				if (memberVO.getBalance() >= total) {

					memberVO.setBalance(memberVO.getBalance() - total);
					System.out.println("更新" + memberVO.getBalance());
					Integer balance = Integer.valueOf(memberVO.getBalance());// 取金流

					svc1.updateStoredValue(member_id, balance);

				} else {

					boolean openModal = true;
					req.setAttribute("openModal", openModal);
					System.out.println(openModal);
					Integer balance = Integer.valueOf(memberVO.getBalance());// 取金流

					// MemberService memberSvc = new MemberService();//更改會員總點數
					// memberSvc.updateStoredValue(member_id, balance);
					svc1.updateStoredValue(member_id, balance);
					String url = "/front-end/ShopPage/ProductCheckoutPage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);
					return;
				}

			}

			System.out.println("接收訂單");

			for (Order_detailVO qq : productlist) {
				System.out.println("商品ID為" + qq.getProduct_id() + "數量為:" + qq.getQuantity() + "價格:" + qq.getPrice());

			}

//Integer total2 =Integer.valueOf(req.getParameter("total"));
//
//System.out.println("網頁來的金額為:"+total2);

			System.out.println("查詢結果");

			try {
				String address1 = null;
				String address2 = null;

				String address3 = null;
				try {
					address1 = new String(req.getParameter("address1"));
					address2 = new String(req.getParameter("address2"));

					address3 = new String(req.getParameter("dv_address"));
				} catch (Exception e) {
					errorMsgs.add("地址異常");

				}
				if (address1 == null || (address1.trim()).length() == 0) {
					errorMsgs.add("請選縣市OK?");
				}
				if (address2 == null || (address2.trim()).length() == 0) {
					errorMsgs.add("請選區好嗎?");
				}

				if (address3 == null || (address3.trim()).length() == 0) {
					errorMsgs.add("請填入地址OK?");
				}

				else if (address3.length() < 6 || address3.length() > 30) {
					errorMsgs.add("地址字數異常");
				}
				System.out.println("城市:" + address1);
				System.out.println("驗證結束");
				req.setAttribute("errorMsgs", errorMsgs);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("address1", address1);
					req.setAttribute("address2", address2);

					req.setAttribute("dv_address", address3);
					RequestDispatcher failureView = req

							.getRequestDispatcher("/front-end/ShopPage/ProductCheckoutPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String dv_address = address1 + "/" + address2 + "/" + address3;
				System.out.println("串接地址:" + dv_address);
				System.out.println("地址驗證結束");

				Integer order_status = Integer.valueOf(new String(req.getParameter("order_status")));
				System.out.println("傳過來的值地址為:" + dv_address + "會員帳號:" + member_id + "支付方式" + pay_type + "訂單總金額" + total
						+ "訂單狀態:" + order_status);
				OrderService svc = new OrderService();
				Shop_orderVO shop_ordervo = new Shop_orderVO();
				shop_ordervo.setMember_id(member_id);
				shop_ordervo.setOrder_status(order_status);
				shop_ordervo.setTotal(total);
				shop_ordervo.setPay_type(pay_type);
				shop_ordervo.setDv_address(dv_address);
				svc.addOrder(shop_ordervo, productlist);
				session.removeAttribute("checkoutlist");
				session.removeAttribute("selecttlist");
				session.removeAttribute("productCar");
				session.removeAttribute("buyProductlist");
				String url = "/front-end/ShopPage/CheckFinish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 查詢訂單明細
		if ("getorderdetail".equals(action)) { // 來自select_page.jsp的請求

			req.setAttribute("errorMsgs", errorMsgs);

			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				str = req.getParameter("order_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_order/listAllShopOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String order_no = null;
				try {
					order_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_order/listAllShopOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				OrderService ordSvc = new OrderService();
				Shop_orderVO ordVO = ordSvc.getOneOrder(order_no);
				if (ordVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_order/listAllShopOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

				req.setAttribute("order_no", str);
				session.setAttribute("order_no", str);
				String url = "/back-end/shop_order/listOneShopOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/listAllShopOrder.jsp");
				failureView.forward(req, res);
			}
		}

//刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或
																// 【/dept/listEmps_ByDeptno.jsp】 或 【
																// /dept/listAllDept.jsp】 或 【
																// /emp/listEmps_ByCompositeQuery.jsp】
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("獲取資訊");
				String url = requestURL;
				System.out.println("獲得網址" + url);

				/*************************** 1.接收請求參數 ***************************************/
				String order_no = new String(req.getParameter("order_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				OrderService ordSvc = new OrderService();
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				ordSvc.delete(order_no);
				System.out.println("開始刪除");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/listAllShopOrder.jsp");
				failureView.forward(req, res);
			}
		}
//		// 修改訂單頁面
//		if ("OrderUpdatepage".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				String order_no = new String(req.getParameter("order_no"));
//
//				/*************************** 2.開始查詢資料 ****************************************/
//				OrderService ordSvc = new OrderService();
//				Shop_orderVO ordvo = ordSvc.getOneOrder(order_no);
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("ordvo", ordvo); // 資料庫取出的empVO物件,存入req
//				String url = "/back-end/shop_order/orderupatepage.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/listAllShopOrder.jsp");
//				failureView.forward(req, res);
//			}
//		}

		// 修改訂單
		if ("OrderUpdate".equals(action)) {
			//
			System.out.println("收到!開始修改訂單地址");

			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String order_no = new String(req.getParameter("order_no"));

				Integer order_status = 0;
				System.out.println("現在訂單狀態" +order_status );

				try {
					order_status = Integer.valueOf(req.getParameter("order_status"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("請選擇狀態\n" + "目前訂單狀態:" + order_status);
				}

				String address3 = req.getParameter("dv_address");
				String address1 = req.getParameter("address1");
				System.out.println(address1);
				String address2 = req.getParameter("address2");
				System.out.println(address2);

				String dv_address = address1 + "/" + address2 + "/" + address3;

				String member_id = new String(req.getParameter("member_id"));

				System.out.println(order_no);
				System.out.println(order_status);
				System.out.println(dv_address);
				System.out.println(member_id);
				Shop_orderJDBCDAO orderdao = new Shop_orderJDBCDAO();
				Shop_orderVO VO = orderdao.findByPrimaryKey(order_no);

				VO.setOrder_status(order_status);
				System.out.println("設置狀態:" + order_status);
				VO.setDv_address(dv_address);

				System.out.println("VO放置成功");
				String pagemessage = new String(req.getParameter("Order_statusPage"));

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordvo", VO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				
				if ("waitpage".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);
					req.setAttribute("Order_statusPage", "waitpage");


				}
				else if ("traveling".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);

					req.setAttribute("Order_statusPage", "traveling");

				}
				else if ("complete".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);

					req.setAttribute("Order_statusPage", "complete");

				}
				else	if ("cancel".equals(pagemessage)) {
					System.out.println("設置"+pagemessage);

					req.setAttribute("Order_statusPage", "cancel");

				}
				System.out.println("進入修改程序");
				/*************************** 2.開始修改資料 *****************************************/
				OrderService Svc = new OrderService();

				VO = Svc.upateOrder(order_status, dv_address, order_no);
				System.out.println("開始修改資料");

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("ordvo", VO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/shop_order/Order_backendPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/orderupatepage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("OrderByMmber".equals(action)) {

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				str = req.getParameter("member_id");
				if (str == null || (str.trim()).length() == 0) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_order/listAllShopOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_order/listAllShopOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				req.setAttribute("member_id", str);
				session.setAttribute("order_no", str);
				String url = "/back-end/shop_order/listAllShopOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/order_manager_page.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
