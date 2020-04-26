package com.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;

/**
 * Servlet implementation class Productchange
 */
@WebServlet("/shop_product/Productchange")
public class ProductChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("處理狀態");
		System.out.println("商品ID:"+req.getParameter("product_id"));
		ProductService svc =new ProductService();
		String product_id=req.getParameter("product_id");
		Integer product_status=Integer.valueOf(req.getParameter("product_status")) ;
		System.out.println("狀態:"+product_status);

		svc.changestatus(product_id, product_status);
		
		req.setCharacterEncoding("UTF-8");
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
