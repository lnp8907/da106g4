package com.staff.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.authority.model.AuthorityService;
import com.staff.model.StaffService;
import com.staff.model.StaffVO;

/**
 * Servlet implementation class StaffServlet
 */
@WebServlet("/back-end/staff/StaffServlet")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StaffServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
		
		if ("getOne_ForStaff".equals(action)) { // 來自select_page.jsp的請求

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String staff_id = req.getParameter("staff_id");
				String whichPage = req.getParameter("whichPage");
				String pageType = req.getParameter("pageType");
				boolean openModal = true;
				req.setAttribute("openModalUpdate", openModal);
				
				/*************************** 2.開始查詢資料 *****************************************/
				StaffService staffService = new StaffService();
				StaffVO staffVO = staffService.findByPrimaryKey(staff_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("staffVO", staffVO); // 資料庫取出的courseVO物件,存入req
				String url = "";
				if(pageType==null||pageType.trim().equals("")) {					
				}
				url = "/back-end/staff/staffPage.jsp?whichPage=" + whichPage;
//				url = "/back-end/staff/staffPage.jsp?pageType="+pageType;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
		}
		
		
		//使用Ajax 成功後不在跳轉業面
		if ("updateForSetting".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/			
			String staff_id = req.getParameter("staff_id");
			String staff_name = req.getParameter("staff_name");
			String ameleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
			if (staff_name == null || staff_name.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!staff_name.trim().matches(ameleReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}
			
			String staff_password = req.getParameter("staff_password");
			
			String passReg = "^[(a-zA-Z0-9_)]{2,20}$";
			if (staff_password == null || staff_password.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!staff_password.trim().matches(passReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("密碼:只能是英文字母、數字和_ , 且長度必需在2到20之間");
			}
			String password2  = req.getParameter("password2");
			if(!(password2.equals(staff_password))) {
				errorMsgs.add("密碼: 請再次確認密碼是否一致");
			}

			String phone = req.getParameter("phone");
			String phoneReg = "^[(0-9)]{10}$";
			if (phone == null || phone.trim().length() == 0) {
				errorMsgs.add("電話: 請勿空白");
			} else if (!phone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("電話: 只能是數字且長度");
			}


			String email = req.getParameter("email");
			String emailReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$";
			if (email == null || email.trim().length() == 0) {
				errorMsgs.add("EMAIL: 請勿空白");
			} else if (!email.trim().matches(emailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("EMAIL格是不正確");
			}


			Integer gender = new Integer(req.getParameter("gender"));
			Integer staff_status = new Integer(req.getParameter("staff_status"));
			
			StaffVO staffVO = new StaffVO();
			staffVO.setStaff_id(staff_id);
			staffVO.setStaff_name(staff_name);
			staffVO.setGender(gender);
			staffVO.setPhone(phone);
			staffVO.setStaff_password(staff_password);
			staffVO.setStaff_status(staff_status);
			staffVO.setEmail(email);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的物件,也存入req
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/staffPage.jsp?pageType=staffSetting.jsp");
				failureView.forward(req, res);
				return;				
			}
			//開始修改資料////////////////////////////////////////////////////////
			StaffService staffService = new StaffService();
			staffVO = staffService.updateWithPassword(staff_id, staff_password, staff_name, gender, phone, email,staff_status);
			RequestDispatcher succeseView = req
					.getRequestDispatcher("/back-end/staff/staffPage.jsp?pageType=staffSetting.jsp");
			req.setAttribute("staffVO", staffVO); 
			req.setAttribute("seccese", "seccese"); 
			succeseView.forward(req, res);				
	}
		
		
		
		if ("insert".equals(action)) {
			String pageType = req.getParameter("pageType");
			List<String> errorMsgs = new LinkedList<String>();

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String staff_name = req.getParameter("staff_name");
			String ameleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
			if (staff_name == null || staff_name.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!staff_name.trim().matches(ameleReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}

			String phone = req.getParameter("phone");
			String phoneReg = "^[(0-9)]{10}$";
			if (phone == null || phone.trim().length() == 0) {
				errorMsgs.add("電話: 請勿空白");
			} else if (!phone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("電話: 只能是數字且長度");
			}


			String email = req.getParameter("email");
			String emailReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$";
			if (email == null || email.trim().length() == 0) {
				errorMsgs.add("EMAIL: 請勿空白");
			} else if (!email.trim().matches(emailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("EMAIL格是不正確");
			}


			Integer gender = new Integer(req.getParameter("gender"));
			Integer staff_status = null;
			try {
				staff_status = new Integer(req.getParameter("staff_status".trim()));		
			}catch(NumberFormatException e){
				errorMsgs.add("部門: 請選擇部門");
			}


			StaffVO staffVO = new StaffVO();
			staffVO.setStaff_name(staff_name);
			staffVO.setGender(gender);
			staffVO.setPhone(phone);
			staffVO.setStaff_status(staff_status);
			staffVO.setEmail(email);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的物件,也存入req
				req.setAttribute("errorMsgs", errorMsgs);				
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/staffPage.jsp?pageType="+pageType);
				failureView.forward(req, res);
				return;
			}	

			/*************************** 2.開始新增資料 *****************************************/
			StaffService staffService = new StaffService();
			String staff_password = getRandomPassword(6);//產生密碼
			
			// 傳送密碼
			String to = email;
			String subject = "員工密碼通知";
			String messageText = "Hello! " + staff_name + " 請謹記此密碼: " + staff_password + "\n" + " (已經啟用)";
			MailService.sendMail(to, subject, messageText);
			staffVO.setStaff_password(staff_password);
			
			//新增資料
			staffVO = staffService.insert(staff_password, staff_name, gender, phone, staff_status, email);
			RequestDispatcher succeseView = req
					.getRequestDispatcher("/back-end/staff/staffPage.jsp");
			succeseView.forward(req, res);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			} catch (Exception e) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/recipe/backEndRecipePage.jsp?pageType=recipeStyleList.jsp");
//				failureView.forward(req, res);
//			}

		}
		if ("update".equals(action)) {
			
				List<String> errorMsgs = new LinkedList<String>();
				String whichPage = req.getParameter("whichPage");
//				try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/			
				String staff_id = req.getParameter("staff_id");
				String staff_name = req.getParameter("staff_name");
				String ameleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (staff_name == null || staff_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!staff_name.trim().matches(ameleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}

				String phone = req.getParameter("phone");
				String phoneReg = "^[(0-9)]{10}$";
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話: 請勿空白");
				} else if (!phone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電話: 只能是數字且長度");
				}


				String email = req.getParameter("email");
				String emailReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$";
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("EMAIL: 請勿空白");
				} else if (!email.trim().matches(emailReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("EMAIL格是不正確");
				}


				Integer gender = new Integer(req.getParameter("gender"));
				Integer staff_status = new Integer(req.getParameter("staff_status"));


				StaffVO staffVO = new StaffVO();
				staffVO.setStaff_id(staff_id);
				staffVO.setStaff_name(staff_name);
				staffVO.setGender(gender);
				staffVO.setPhone(phone);
				staffVO.setStaff_status(staff_status);
				staffVO.setEmail(email);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的物件,也存入req
					req.setAttribute("errorMsgs", errorMsgs);	
					boolean openModal = true;
					req.setAttribute("openModal", openModal);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/staffPage.jsp?whichPage="+whichPage);
					failureView.forward(req, res);
					return;				
				}
				//開始修改資料////////////////////////////////////////////////////////
				StaffService staffService = new StaffService();
				if(staff_status.equals(3)) {
					//狀態碼為3的話表示已離職,請此必須清楚所有權限
					AuthorityService authorizationsSvc = new AuthorityService();
					authorizationsSvc.delete(staff_id);
				}
				
				staffVO = staffService.update(staff_id, staff_status, staff_name, gender, phone, email);	
				req.setAttribute("oldSff_id", staff_id);
				RequestDispatcher succeseView = req
						.getRequestDispatcher("/back-end/staff/staffPage.jsp?whichPage="+whichPage);
				succeseView.forward(req, res);				
		}
	}

	public String getRandomPassword(int length) {
		String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);// 0~61
			sf.append(str.charAt(number));

		}
		return sf.toString();
	}
}
