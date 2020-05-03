package com.product_browsing_history.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/product_browing_historyServlet")
public class Product_browing_historyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Product_browing_historyServlet() {
        super();
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
