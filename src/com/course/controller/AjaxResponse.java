package com.course.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.mycourse.model.MyCourseService;
import com.recipe_favorite.model.RecipeFavoriteServiec;

@WebServlet({ "/front-end/course/ajaxResponse.do","/courseAjaxResponse.do" })
public class AjaxResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxResponse() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain");
		
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
	
		if ("cancel".equals(action)) { // 來自addCourse.jsp的請求
			String course_id = req.getParameter("course_id");
			String member_id = req.getParameter("member_id");
			String course_status = "2";
			
			MyCourseService mycourseService = new MyCourseService();
			mycourseService.changeOneStatus(course_id, member_id, course_status);
				/*********************** 1.接收請求參數 - 查詢是否重複報名 *************************/
		
		
				out.write("OK");			
				
			}

		if ("insert".equals(action)) { // 來自addCourse.jsp的請求
			String course_id = req.getParameter("course_id");
			String member_id = req.getParameter("member_id");
			Integer pay_price = new Integer(req.getParameter("pay_price"));
			
			MyCourseService mycourseService = new MyCourseService();
			JSONObject obj = new JSONObject();
			Integer appliedNum = null;
			
				/*********************** 1.接收請求參數 - 查詢是否重複報名 *************************/
			
			if(mycourseService.isApplied(course_id, member_id)) {
				appliedNum = mycourseService.appliedNum(course_id);
				try {
					obj.put("message", "請勿重複報名!");
					obj.put("appliedNum", appliedNum);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.write(obj.toString());

			}else {
				/*************************** 2.開始新增資料 ***************************************/	
				mycourseService.insert(course_id, member_id, pay_price);
				appliedNum = mycourseService.appliedNum(course_id);
			
				try {
					obj.put("message", "報名成功!");
					obj.put("appliedNum", appliedNum);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		
				out.write(obj.toString());			
				
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
