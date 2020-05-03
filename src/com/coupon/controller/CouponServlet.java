package com.coupon.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.coupon.model.*;
import com.coupon_details.model.CouponDetailsService;
import com.coupon_details.model.CouponDetailsVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class CouponServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

	
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String c_no = req.getParameter("c_no");
				String c_name = req.getParameter("c_name");
				String whichPage = req.getParameter("whichPage");
				boolean openModal = true;
				req.setAttribute("openModal", openModal);
				if(whichPage==null) {
					whichPage = "1";
				}
				/*************************** 2.開始查詢資料 *****************************************/
				CouponDetailsService couponDetailsService = new CouponDetailsService();
				List<CouponDetailsVO> list = couponDetailsService.findByPrimaryKey_C_no(c_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的couponVO物件,存入req
				req.setAttribute("c_name", c_name);
				String url = "/back-end/coupon/couponPage.jsp?whichPage="+whichPage;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/

		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String c_no = new String(req.getParameter("c_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				CouponService couponSvc = new CouponService();
				CouponVO couponVO = couponSvc.getOneCoupon(c_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("couponVO", couponVO); // 資料庫取出的couponVO物件,存入req
				String url = "/coupon/update_coupon_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_coupon_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		}		
		if ("update".equals(action)) { // 來自update_coupon_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String c_no = new String(req.getParameter("c_no").trim());
				String c_name = req.getParameter("c_name");
				String c_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (c_name == null || c_name.trim().length() == 0) {
					errorMsgs.add("優惠卷名稱: 請勿空白");
				} else if (!c_name.trim().matches(c_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("優惠卷名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}
				// 上傳圖片
				byte[] c_picture = null;
				
				Part part = req.getPart("c_picture");
				if(part.getSize() != 0) {//代表有上傳圖片
					InputStream in = part.getInputStream();
					c_picture = new byte[in.available()];
//					while((in.read(c_picture)) != -1) {}
					in.read(c_picture);
					in.close();
				}else {
					CouponService couponSvc = new CouponService();
					CouponVO couponVO = couponSvc.getOneCoupon(c_no);
					c_picture =couponVO.getC_picture();
				}
				
				

				Integer discount = null;
				try {
					discount = new Integer(req.getParameter("discount").trim());
						if(discount <=0 || discount >=100) {
							errorMsgs.add("優惠折扣只能1到99");
					}
				} catch (NumberFormatException e) {
					discount = 0;
					errorMsgs.add("優惠折扣請填數字.");
				}

				java.sql.Date start_date = null;
				try {
					start_date = java.sql.Date.valueOf(req.getParameter("start_date").trim());
				} catch (IllegalArgumentException e) {
					start_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				Date today = new Date();
				java.sql.Date end_date = null;
				try {
					end_date = java.sql.Date.valueOf(req.getParameter("end_date").trim());
				} catch (IllegalArgumentException e) {
					end_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				//判斷開始日期與結束日期是否合理
				if(!start_date.before(end_date)) {
					errorMsgs.add("開始與結束日期不合理!");
				}else if(start_date.before(today)){
					errorMsgs.add("開始日期已過期!");
				}
				
				String coupon_code = req.getParameter("coupon_code").trim();
				String c_codeReg = "^[(a-zA-Z0-9_)]{6}$";
				if (coupon_code == null || coupon_code.trim().length() == 0) {
					errorMsgs.add("優惠代碼請勿空白");
				} else if (!coupon_code.trim().matches(c_codeReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("優惠卷名稱: 只能是英文字母、數字和_ , 且長度必需在6");
				}

				CouponVO couponVO = new CouponVO();
				couponVO.setC_no(c_no);
				couponVO.setC_name(c_name);
				couponVO.setC_picture(c_picture);
				couponVO.setDiscount(discount);
				couponVO.setStart_date(start_date);
				couponVO.setEnd_date(end_date);
				couponVO.setCoupon_code(coupon_code);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的couponVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/update_coupon_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				CouponService couponSvc = new CouponService();
				couponVO = couponSvc.updateCoupon(c_no, c_name, c_picture, discount, start_date, end_date, coupon_code);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("couponVO", couponVO); // 資料庫update成功後,正確的的couponVO物件,存入req
				String url = "/coupon/listOneCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCoupon.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/update_coupon_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addCoupon.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);	
			CouponVO couponVO=null;
			String[] product_id=null;
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String c_no = new String(req.getParameter("c_no").trim());
				product_id=req.getParameterValues("product_id");
				if(product_id==null) {
					errorMsgs.add("請填寫適用食譜");
				}
				String c_name = req.getParameter("c_name");
				String c_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (c_name == null || c_name.trim().length() == 0) {
					errorMsgs.add("優惠卷名稱: 請勿空白");
				} else if (!c_name.trim().matches(c_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("優惠卷名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}
				
				// 上傳圖片
				Part part = req.getPart("c_picture");
				byte[] c_picture = null;
				if(part.getSize()==0) {
					errorMsgs.add("請上傳圖片");
				}else {
				InputStream in = part.getInputStream();
				c_picture = new byte[in.available()];
//				while((in.read(c_picture)) != -1) {}
				in.read(c_picture);
				in.close();
				}
				Integer discount = null;
				try {
					discount = new Integer(req.getParameter("discount").trim());
						if(discount <=0 || discount >=100) {
							errorMsgs.add("優惠折扣只能1到99");
					}
				} catch (NumberFormatException e) {
					discount = 0;
					errorMsgs.add("優惠折扣請填數字.");
				}
				Date today = new Date();
				java.sql.Date start_date = null;
				try {
					start_date = java.sql.Date.valueOf(req.getParameter("start_date").trim());
					
				} catch (IllegalArgumentException e) {
					start_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}

				java.sql.Date end_date = null;
				try {
					end_date = java.sql.Date.valueOf(req.getParameter("end_date").trim());
				} catch (IllegalArgumentException e) {
					end_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				//判斷開始日期與結束日期是否合理
				if(!start_date.before(end_date)) {
					errorMsgs.add("開始與結束日期不合理!");
				}else if(start_date.before(today)){
					errorMsgs.add("開始日期已過期!");
				}
				String coupon_code = req.getParameter("coupon_code").trim();
				String c_codeReg = "^[(a-zA-Z0-9_)]{6}$";
				if (coupon_code == null || coupon_code.trim().length() == 0) {
					errorMsgs.add("優惠代碼請勿空白");
				} else if (!coupon_code.trim().matches(c_codeReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("優惠卷名稱: 只能是英文字母、數字和_ , 且長度必需在6");
				}


				couponVO = new CouponVO();
//				couponVO.setC_no(c_no);
				couponVO.setC_name(c_name);
				couponVO.setC_picture(c_picture);
				couponVO.setDiscount(discount);
				couponVO.setStart_date(start_date);
				couponVO.setEnd_date(end_date);
				couponVO.setCoupon_code(coupon_code);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的couponVO物件,也存入req
					req.setAttribute("product_id", product_id);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/couponPage.jsp?pageType=addCoupon.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				CouponService couponSvc = new CouponService();
				couponVO = couponSvc.addCoupon(c_name, c_picture, discount, start_date, end_date, coupon_code);

				//新增適用範圍
				CouponDetailsService couponDetailsService = new CouponDetailsService();
				String c_no= couponVO.getC_no();
				couponDetailsService.insert(c_no, product_id);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 跟update不一樣的地方是 insert完是查詢全部 所以不用setAttribute給下一個網頁getAttribute
				String url = "/back-end/coupon/couponPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				//errorMsgs.add(e.getMessage());
				errorMsgs.add("商品編號有誤");
				req.setAttribute("couponVO", couponVO); 
				req.setAttribute("product_id", product_id); 
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/couponPage.jsp?pageType=addCoupon.jsp");		
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllCoupon.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String c_no = req.getParameter("c_no");

				/*************************** 2.開始刪除資料 ***************************************/
				CouponService couponSvc = new CouponService();
				couponSvc.deleteCoupon(c_no);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/coupon/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		}
	}
}