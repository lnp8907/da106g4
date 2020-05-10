package com.notice.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
<<<<<<< HEAD
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		
		if (action.equals("Click")) {
			System.out.println("新增購物車" + "ID:" + req.getParameter("product_id"));

			NoticeVO notice_id = getNotice_id(req);
//			if (notice_id == null) {
//				productlist = new Vector<Order_detailVO>();
//				productlist.add(oneproduct);
//
//			} else {
//				if (productlist.contains(oneproduct)) {
//					Order_detailVO inner = productlist.get(productlist.indexOf(oneproduct));
//					inner.setQuantity(inner.getQuantity() + oneproduct.getQuantity());
//				} else {
//					productlist.add(oneproduct);
//				}
//			}

		}
=======
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		System.out.println("進入通知控制器");

>>>>>>> branch 'master' of https://github.com/lnp8907/da106g4.git
	}

	// 取得notice_id
		private NoticeVO getNotice_id(HttpServletRequest req) {
			Integer product_price = 0;
			String notice_id = null;

			if (req.getParameter("notice_id") != null) {

				ProductService psvc = new ProductService();
				product_id = psvc.getbyreceipe(req.getParameter("recipe_id")).getProduct_id();
				product_price = psvc.getbyreceipe(req.getParameter("recipe_id")).getProduct_price();
			} else {
				product_id = req.getParameter("product_id");
				product_price = Integer.valueOf(req.getParameter("product_price"));

			}
			System.out.println("ID為:" + product_id);
			System.out.println("價格為:" + product_price);

			Integer quantity = Integer.valueOf(req.getParameter("quantity"));
			NoticeVO a = new NoticeVO();

			a.setProduct_id(product_id);
			a.setPrice(product_price);
			a.setQuantity(quantity);
			System.out.println("數量:" + quantity);

			return a;

		}
}
