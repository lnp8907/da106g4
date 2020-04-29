package com.mycourse.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.course.model.CourseService;
import com.course.model.CourseVO;
import com.mycourse.model.MyCourseService;
import com.mycourse.model.MycourseVO;

@MultipartConfig
@WebServlet({"/back-end/course/MycourseServlet" })
public class MyCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
		
		
		if ("changeStatus".equals(action)) { // 來自listAllEmp.jsp
			
				Map<String,String[]> changeStatus = new HashMap<String, String[]>();
				String[] course_id = req.getParameterValues("course_id");
				String[] member_id = req.getParameterValues("member_id");
				String[] oldStatus = req.getParameterValues("oldStatus");
				String[] course_status=  req.getParameterValues("course_status");
				for(int i=0; i<course_status.length; i++) {
					if("".equals(course_status[i].trim())) {
						course_status[i] = oldStatus[i];
					}
				}
				changeStatus.put("course_id", course_id);
				changeStatus.put("member_id", member_id);
				changeStatus.put("course_status", course_status);
			
				/*************************** 2.開始刪除資料 ***************************************/
				MyCourseService myCourseService = new MyCourseService();
				myCourseService.changeOneStatus(changeStatus);

				/*************************** 3.更改完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/course/backEndCoursePage.jsp?pageType=mycourseList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
		}

	
		
		if ("getOne_ForMycourse".equals(action)) {

			try {
				// Retrieve form parameters.
				String course_id = req.getParameter("course_id");
				MyCourseService myCourseService = new MyCourseService();
				List<MycourseVO> list  = myCourseService.findJoinedMemberByPrimaryKey(course_id);
				req.setAttribute("getOne_ForMycourse", list); // 資料庫取出的empVO物件,存入req
				req.setAttribute("course_id", course_id);
				boolean openModal = true;
				req.setAttribute("openModal", openModal);

				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/course/backEndCoursePage.jsp?pageType=mycourseList.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		

		if ("getOne_For_Update".equals(action)) { // 來自listAllCourse.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String course_id = req.getParameter("course_id");

				/*************************** 2.開始查詢資料 ****************************************/
				CourseService courseService = new CourseService();
				CourseVO courseVO = courseService.getOneCourse(course_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("courseVO", courseVO); // 資料庫取出的courseVO物件,存入req
				String url = "/front-end/course/update_course_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_course_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/course/listAllCourse.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_course_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String course_id = req.getParameter("course_id");
				String course_name = req.getParameter("course_name");
				String course_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(<>\\: )]{2,20}$";
				if (course_name == null || course_name.trim().length() == 0) {
					errorMsgs.add("課程名稱: 請勿空白");
				} else if (!course_name.trim().matches(course_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("課程名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}

				String member_id = req.getParameter("member_id");
				String member_idReg = "^[0-9]{6}$";
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("課程編號: 請勿空白");
				} else if (!member_id.trim().matches(member_idReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("課程編號: 只能是數字, 且長度必需為6");
				}

				Part photo = req.getPart("course_photo");
				byte[] course_photo = null;
				if (photo == null || photo.getSize() == 0) {
					HttpSession session = req.getSession();
					course_photo = (byte[]) session.getAttribute("course_photo");
					session.removeAttribute("course_photo");
				} else {
					course_photo = getPictureByteArray(photo);
				}

				String course_type = req.getParameter("course_type").trim();
				if (course_type == null || "請選擇".equals(course_type)) {
					errorMsgs.add("請選擇課程風格");
				}

				java.sql.Timestamp course_start = null;
				try {
					course_start = java.sql.Timestamp.valueOf(req.getParameter("course_start").trim());
				} catch (IllegalArgumentException e) {
					course_start = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入開課時間!");
				}

				java.sql.Date end_app = null;
				try {
					end_app = java.sql.Date.valueOf(req.getParameter("end_app").trim());
				} catch (IllegalArgumentException e) {
					end_app = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名截止日!");
				}

				Integer num_max = Integer.valueOf(req.getParameter("num_max").trim());
				if (num_max == null || num_max == 0) {
					errorMsgs.add("請選擇課程限制人數");
				}

				Integer course_price = null;
				try {
					course_price = new Integer(req.getParameter("course_price").trim());
					if (course_price < 0) {
						errorMsgs.add("課程價格不得為負數.");
					}
				} catch (NumberFormatException e) {
					course_price = 0;
					errorMsgs.add("請填寫課程價格.");
				}

				String course_loca = req.getParameter("course_loca");
				String course_loca_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (course_loca == null || course_loca.trim().length() == 0) {
					errorMsgs.add("上課地點: 請勿空白");
				} else if (course_loca_nameReg.trim().matches(course_loca_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add(course_loca + "上課地點: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}

				StringBuilder course_detailSb = new StringBuilder();
				String course_detail = "";
				String[] course_detail1 = req.getParameterValues("course_detail1");
				if (course_detail1 == null || course_detail1.length == 0) {
					errorMsgs.add("請填寫學習菜單");
				} else {
					for (int i = 0; i < course_detail1.length; i++) {
						if (course_detail1[i] == null || course_detail1[i].trim().length() == 0) {
							errorMsgs.add("學習菜單欄位不得為空或刪除多餘的食材欄位");
							break;
						}
						if (i == course_detail1.length - 1)
							course_detailSb.append(course_detail1[i] + "-");
						else
							course_detailSb.append(course_detail1[i] + "/");

					}

				}
				String[] course_detail2 = req.getParameterValues("course_detail2");
				if (course_detail2 == null || course_detail2.length == 0) {
					errorMsgs.add("請填寫學習重點");
				} else {
					for (int i = 0; i < course_detail2.length; i++) {
						if (course_detail2[i] == null || course_detail2[i].trim().length() == 0) {
							errorMsgs.add("學習重點欄位不得為空或刪除多餘的食材欄位");
							break;
						}
						course_detailSb.append(course_detail2[i] + "/");
					}

				}

				course_detail = course_detailSb.toString();

				CourseVO courseVO = new CourseVO();
				courseVO.setCourse_id(course_id);
				courseVO.setCourse_name(course_name);
				courseVO.setMember_id(member_id);
				courseVO.setCourse_photo(course_photo);
				courseVO.setCourse_type(course_type);
				courseVO.setCourse_start(course_start);
				courseVO.setEnd_app(end_app);
				courseVO.setNum_max(num_max);
				courseVO.setCourse_price(course_price);
				courseVO.setCourse_loca(course_loca);
				courseVO.setCourse_detail(course_detail);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("courseVO", courseVO); // 含有輸入格式錯誤的courseVO物件,也存入req
					req.setAttribute("course_detail1", course_detail1);
					req.setAttribute("course_detail2", course_detail2);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/course/update_course_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				CourseService courseService = new CourseService();
				courseVO = courseService.updateCourse(course_id, member_id, course_type, course_name, num_max,
						course_start, course_price, course_detail, end_app, course_photo, course_loca);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("courseVO", courseVO);
				courseVO.setApp_num(Integer.parseInt(req.getParameter("app_num")));// 為了轉交頁面可以呈現報名人數
				String url = "/front-end/course/listOneCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCourse.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/course/addCourse.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addCourse.jsp的請求
			String course_id = req.getParameter("course_id");
			String member_id = req.getParameter("course_id");
			Integer pay_price = new Integer(req.getParameter("pay_price"));
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/		

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("courseVO", courseVO); // 含有輸入格式錯誤的courseVO物件,也存入req
//					req.setAttribute("course_detail1", course_detail1);
//					req.setAttribute("course_detail2", course_detail2);
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/course/addCourse.jsp");
//					failureView.forward(req, res);
//					return;
		

				/*************************** 2.開始新增資料 ***************************************/
				MyCourseService mycourseService = new MyCourseService();
				mycourseService.insert(course_id, member_id, pay_price);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/course/listAllCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCourse.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/course/addCourse.jsp");
				failureView.forward(req, res);
			
		}

		if ("delete".equals(action))

		{ // 來自listAllCourse.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String course_id = req.getParameter("course_id");

				/*************************** 2.開始刪除資料 ***************************************/
				CourseService courseService = new CourseService();
				courseService.deleteCourse(course_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/course/listAllCourseManagement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/course/listAllCourse.jsp");
				failureView.forward(req, res);
			}
		}
	}

	public static byte[] getPictureByteArray(Part part) throws IOException {
		InputStream fis = part.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();
	}

}