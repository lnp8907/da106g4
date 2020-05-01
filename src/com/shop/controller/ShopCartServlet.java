package com.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order_detail.model.Order_detailVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.sun.org.apache.bcel.internal.generic.RETURN;

@WebServlet("/front-end/ShopPage/ShopCart")
public class ShopCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShopCartServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("處理購物車");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String product_id = "";
		String recipe_id = "";
		Vector<Order_detailVO> checktlist;
		Vector<Order_detailVO> selecttlist = null;

		if (req.getParameter("recipe_id") != null) {
			recipe_id = req.getParameter("recipe_id");
			ProductService psvc = new ProductService();
			System.out.println("獲取食譜ID為:" + req.getParameter("recipe_id"));
			product_id = psvc.getbyreceipe(req.getParameter("recipe_id")).getProduct_id();

		} else {
			product_id = req.getParameter("product_id");

		}
		System.out.println("商品ID" + product_id);
		@SuppressWarnings("unchecked")
		Vector<Order_detailVO> productlist = (Vector<Order_detailVO>) session.getAttribute("productCar");
		String action = req.getParameter("action");
		System.out.println("行為是:" + req.getParameter("action"));
		// 判斷行動

		if (!action.equals("CHECKOUT")) {
			// 若是刪除
			if (action.equals("REMOVE")) {
				System.out.println("進入移除");
				int remove = Integer.valueOf(req.getParameter("del"));
				System.out.println(remove);
				productlist.remove(remove);

			}
			// 新增
			else if (action.equals("ADD")) {
				System.out.println("新增購物車" + "ID:" + req.getParameter("product_id"));

				Order_detailVO oneproduct = getProduct(req);
				if (productlist == null) {
					productlist = new Vector<Order_detailVO>();
					productlist.add(oneproduct);

				} else {
					if (productlist.contains(oneproduct)) {
						Order_detailVO inner = productlist.get(productlist.indexOf(oneproduct));
						inner.setQuantity(inner.getQuantity() + oneproduct.getQuantity());
					} else {
						productlist.add(oneproduct);
					}
				}

			}

			else if (action.equals("ADDR")) {
				System.out.println("新增食譜");
				System.out.println("新增購物車" + "ID:" + product_id);

				Order_detailVO oneproduct = getProduct(req);
				if (productlist == null) {
					productlist = new Vector<Order_detailVO>();
					productlist.add(oneproduct);

				} else {
					if (productlist.contains(oneproduct)) {
						Order_detailVO inner = productlist.get(productlist.indexOf(oneproduct));
						inner.setQuantity(inner.getQuantity() + oneproduct.getQuantity());
					} else {
						productlist.add(oneproduct);
					}
				}

			}
			System.out.println("購物車長度:" + productlist.size());
			if (action.equals("ADD")) {
				PrintWriter out = res.getWriter();
				out.println(productlist.size());
				return;

			}
/*********單選按鈕操作*********/

			if (action.equals("finallcarlist")) {
				System.out.println("判斷購買清單");
				selecttlist = productlist;
				session.setAttribute("selecttlist", selecttlist);

				return;
			}
			if (action.equals("clearlist")) {
				System.out.println("清除購買清單");
				selecttlist = null;
				session.setAttribute("selecttlist", selecttlist);

				return;
			}

			if (action.equals("removelist")) {
				System.out.println("移除清單");
				System.out.println("獲取索引" + req.getParameter("remmoveid"));
//				selecttlist=productlist;
				if(session.getAttribute("selecttlist")==null) {
				selecttlist = (Vector<Order_detailVO>) productlist.stream()
						.filter(p -> !p.getProduct_id().equals(req.getParameter("remmoveid")))
						.collect(Collectors.toCollection(Vector::new));

				System.out.println(selecttlist);}
				else if(session.getAttribute("selecttlist")!=null){
					selecttlist=((Vector<Order_detailVO>)session.getAttribute("selecttlist")).stream()
							.filter(p -> !p.getProduct_id().equals(req.getParameter("remmoveid")))
							.collect(Collectors.toCollection(Vector::new));
				}
				session.setAttribute("selecttlist", selecttlist);
				return;
			}
/*--------------------單選新增--------------------------*/
			if (action.equals("addlist")) {
				System.out.println("新增清單");
				System.out.println("獲取索引" + req.getParameter("remmoveid"));
//				selecttlist=productlist;
				if(session.getAttribute("selecttlist")==null) {
				selecttlist = (Vector<Order_detailVO>) productlist.stream()
						.filter(p -> p.getProduct_id().equals(req.getParameter("addid")))
						.collect(Collectors.toCollection(Vector::new));

				System.out.println(selecttlist);}
				else if(session.getAttribute("selecttlist")!=null){
					selecttlist=(Vector<Order_detailVO>)session.getAttribute("selecttlist");
					Order_detailVO addproduct=productlist.parallelStream().
							filter(p->p.getProduct_id().equals(req.getParameter("addid")))
							.findAny().get();
					selecttlist.add(addproduct);
					System.out.println("清單"+addproduct);}

				
				session.setAttribute("selecttlist", selecttlist);
				return;
			}
			
			
			
			
			
			
			// --------------
			selecttlist=(Vector<Order_detailVO>)session.getAttribute("productCar");
			session.setAttribute("selecttlist", productlist);
			session.setAttribute("productCar", productlist);
			if (action.equals("REMOVE")) {
				System.out.println("刪除後轉移");
				req.setAttribute("checktpage", "checktpage1");
				String url = "/front-end/ShopPage/ShopCarPage.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;

			} else if (action.equals("ADDR")) {
				req.setAttribute("recipe_id", recipe_id);
				String url = "/front-end/recipe/RecipeServlet?action=getOne_For_Display&recipe_id=" + recipe_id;
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;

			}

			else {

				req.setAttribute("product_id", product_id);

				System.out.println("開始轉移");
				String url = "/front-end/ShopPage/ShopDetailPage.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
		}

	}

	// 設計取得商品資訊
	private Order_detailVO getProduct(HttpServletRequest req) {
		Integer product_price = 0;
		String product_id = null;

		if (req.getParameter("recipe_id") != null) {

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
		Order_detailVO a = new Order_detailVO();

		a.setProduct_id(product_id);
		a.setPrice(product_price);
		a.setQuantity(quantity);
		System.out.println("數量:" + quantity);

		return a;

	}

}
