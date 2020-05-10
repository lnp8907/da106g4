package com.notice.controller;

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

import com.notice.model.NoticeService;
import com.notice.model.NoticeVO;
import com.order_detail.model.Order_detailVO;
import com.product.model.ProductService;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("進入通知控制器");
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		
		if ("Click".equals(action)) { // 來自addEmp.jsp的請求
			   List<String> errorMsgs = new LinkedList<String>();
			   // Store this set in the request scope, in case we need to
			   // send the ErrorPage view.
			   req.setAttribute("errorMsgs", errorMsgs);

			   try {
			    /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			    String notice_id = req.getParameter("notice_id").trim();
			    /*************************** 2.開始查詢資料 ***************************************/
			    System.out.println(notice_id);
			    NoticeService noticeSvc = new NoticeService();
			    noticeSvc.changeStatus(notice_id, 1);
			    /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			    res.setContentType("text/plain");
//			    res.setCharacterEncoding("UTF-8");
//			    PrintWriter out = res.getWriter();
//			    out.write(jsobj.toString());
//			    out.flush();
//			    out.close();
			    /*************************** 其他可能的錯誤處理 **********************************/
			   }catch (Exception e) {
			    errorMsgs.add("無法取得資料:" + e.getMessage());
			    RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp");
			    failureView.forward(req, res);
			   }
			   
			  }

	}

}
