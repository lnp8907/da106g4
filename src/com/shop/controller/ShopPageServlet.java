package com.shop.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class ShopPageServlet
 */
@WebServlet("/back-end/ShopPageServlet")
public class ShopPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShopPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		System.out.println("得到換頁請求");
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if ("listallEX".equals(action)) {
			System.out.println("接到換頁全部商品除食譜請求");
			session.setAttribute("backendpage","listallEX" );
			String url = "/back-end/shop_backendPage.jsp";  
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
		if ("listAllReceipe".equals(action)) {
			System.out.println("接到換頁全部食譜請求");
			session.setAttribute("backendpage","listAllReceipe" );
			String url = "/back-end/shop_backendPage.jsp";  
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
		
		
		
		
		
		
		
		if ("nextPage".equals(action)) {
			String product_type = new String(req.getParameter("product_type").trim());
			String whichPage = new String(req.getParameter("whichPage").trim());
			System.out.println(product_type);
			System.out.println(whichPage);
			req.setAttribute("product_type", product_type);
			String url = "/front-end/ShopPage/ShopProductPage.jsp" + "?whichPage=" + whichPage;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("goProductPage".equals(action)) {
			Collection productlist = null;
			ProductService svc = new ProductService();
			System.out.println("前往商品頁面");
			String product_type = null;
			try {
				product_type = new String(req.getParameter("product_type").trim());
				if (product_type.equals("所有商品") || product_type.equals("all")) {
					product_type = "所有商品";
					productlist = svc.getAllProduct(0);
				} else {

					productlist = svc.gettypelist(product_type, 0);

				}

			} catch (NumberFormatException e) {
				product_type = null;
			}

			session.setAttribute("productlist", productlist);
			session.setAttribute("product_type", product_type);
			String url = "/front-end/ShopPage/ShopProductPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("goDetailPage".equals(action)) {
			System.out.println("前往商品詳細頁面");
			String product_id = null;
			System.out.println(req.getParameter("product_id"));
			try {
				product_id = new String(req.getParameter("product_id").trim());
			} catch (NumberFormatException e) {
				product_id = null;
			}
			req.setAttribute("product_id", product_id);
			String url = "/front-end/ShopPage/ShopDetailPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");		
		String action = req.getParameter("action");
		if("searchproduct".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();
			Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
			if (req.getParameter("whichPage") == null){
				HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
				session.setAttribute("map",map1);
				map = map1;
			} 
			
			
			ProductService Svc = new ProductService();
			List<ProductVO> list  = Svc.getAll_ZERO(map);
			req.setAttribute("product_type", "搜尋結果");
			req.setAttribute("Query", list); 
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/ShopPage/ShopProductPage.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			
		
			
		}
		
		doGet(req, res);
	}
}
