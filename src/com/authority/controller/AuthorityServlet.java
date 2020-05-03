package com.authority.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.authority.model.AuthorityService;
import com.staff.model.StaffService;
import com.staff.model.StaffVO;


public class AuthorityServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) {

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String staff_id = req.getParameter("staff_id");
				String whichPage = req.getParameter("whichPage");
				boolean openModal = true;
				req.setAttribute("openModalPower", openModal);
				
				/****************************
				 * 2.開始查詢資料
				 ******************************************/
				StaffService staffService = new StaffService();
				StaffVO staffVO = staffService.findByPrimaryKey(staff_id);
				AuthorityService authoritySvc = new AuthorityService();
				Set<String> authorities = authoritySvc.findPowerByEmpno(staff_id);


				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("authorities", authorities);
				req.setAttribute("staffVO", staffVO);
				
				String url ="/back-end/staff/staffPage.jsp?whichPage=" + whichPage;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
		}
		
		if ("update".equals(action)) {

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String whichPage = req.getParameter("whichPage");
				String staff_id = req.getParameter("staff_id");
				String[] powerList = req.getParameterValues("power");

				/*************************** 2.開始修改資料 *****************************************/
				AuthorityService authorizationsSvc = new AuthorityService();
				if(powerList==null) {
					//空值表示沒有擁有任何權限
					authorizationsSvc.delete(staff_id);
				}else {
					authorizationsSvc.insert(staff_id,powerList);					
				}
				
				Set<String> set = authorizationsSvc.findPowerByEmpno(staff_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				
				req.setAttribute("set", set);
				req.setAttribute("staff_id", staff_id);
				String url = "/back-end/staff/staffPage.jsp?whichPage="+whichPage;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/

		}
	}

}
