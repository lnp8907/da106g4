package com.staff.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.authority.model.AuthorityService;
import com.member.model.mailThread;
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

		HttpSession session = req.getSession();
		String loginPath = "/backEnd_Login.jsp";
		String indexPath = "/backEnd2.jsp";
		System.out.println(action);
		


		if ("loginOUT".equals(action)) { // 登出  OK
			session.invalidate();
			String URL=req.getContextPath()+"/backEnd_Login.jsp";
			res.sendRedirect(URL);
	
		}
		
		
		
		
		
	
	
	

		if ("login".equals(action)) { // 登入   OK
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String staff_id = req.getParameter("staff_id");
				if (staff_id == null || (staff_id.trim()).length() == 0) {
					errorMsgs.add("帳號不得為空白");
				}
				
				String psw = req.getParameter("psw");
				if (psw == null || (psw.trim()).length() == 0) {
					errorMsgs.add("密碼不得為空白");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String URL=req.getContextPath()+"/backEnd_Login.jsp";
					res.sendRedirect(URL);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StaffService staffSvc = new StaffService();
				StaffVO staffVO = staffSvc.getfindOnePK(staff_id);
				
				if (staffVO == null || !staffVO.getStaff_password().equals(psw)) {
					errorMsgs.add("帳號或密碼有誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String URL=req.getContextPath()+"/backEnd_Login.jsp";
					res.sendRedirect(URL);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				
				
				
			
				
				
				
				
				
				
				
				
				
				session.setAttribute("staff_id", staffVO.getStaff_id());
				AuthorityService authorityService = new AuthorityService();
				
				Set<String> powerList = authorityService.findPowerByEmpno(staff_id);
				if(powerList ==null) {
					powerList = new HashSet<String>();
				}
				session.setAttribute("powerList",powerList);
				session.setAttribute("staffVO", staffVO); // 資料庫取出的VO物件,存入Session
				indexPath = req.getContextPath() + "/backEnd2.jsp";
				String originalJSP = (String)session.getAttribute("originalJSP");  // 
				if(originalJSP != null) {
					indexPath = originalJSP;
					session.removeAttribute("originalJSP");
				}
				res.sendRedirect(indexPath);
				return;
				
//				RequestDispatcher successView = req.getRequestDispatcher(indexPath); 
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				String URL=req.getContextPath()+"/backEnd_Login.jsp";
				res.sendRedirect(URL);
			}
		}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//	if("login2".equals(action)) {
//
//			
//		
//			
//	
//			
//			
////			if(staff_id==null || staff_id.trim().length()==0) {
////			
////			}
////			if(staff_id==null || staff_id.trim().length()==0) {
////		
////			}
//			String login2= (Integer)req.getSession().getAttribute("login2")+"";
//		
//			if(!login2.equals("1")) {
//				String staff_id=req.getParameter("staff_id").trim();
//				String psw=req.getParameter("psw").trim();
//				
//				System.out.println(staff_id);
//				System.out.println(psw);
//			if(allowUser(staff_id,psw)==1) {
//				//登入不成功
//				System.out.println("沒有此帳號");
//				String URL=req.getContextPath()+"/index.html";
//				res.sendRedirect(URL);
//				return;
//			}else if(allowUser(staff_id,psw)==4) {
//				//登入不成功
//				System.out.println("密碼錯誤");
//				String URL=req.getContextPath()+"/index.html";
//				res.sendRedirect(URL);
//				return;	
//				
//			}else {
//				StaffService staffSvc=new StaffService();
//				StaffVO staffVO=null;
//				staffVO=staffSvc.getfindOnePK(staff_id);
//				AuthorityService authorityService = new AuthorityService();
//				
//				Set<String> powerList = authorityService.findPowerByEmpno(staff_id);
//				if(powerList ==null) {
//					powerList = new HashSet<String>();
//				}
//				HttpSession session=req.getSession();
//				session.setAttribute("staffVO", staffVO);

//				
//				
//				

//				
//				
		
//session.setAttribute("staff_id", staffVO.getStaff_id());
//session.setAttribute("staff_name", staffVO.getStaff_name());
//
//session.setAttribute("powerList",powerList);
//session.setAttribute("login2", 1);
//String login21= (Integer)req.getSession().getAttribute("login2")+"";
//System.out.println("login21"+login21);		
//
//    
//session.setAttribute("staff_status", 1);
//		        String sessionstaff_status= (Integer)req.getSession().getAttribute("staff_status")+"";
//System.out.println("session中staff_status"+sessionstaff_status);		
//
//
//
//
//
//
//
//
//				String sessionstaff_id= (String) req.getSession().getAttribute("staff_id");
//				
//System.out.println("session中staff_id"+sessionstaff_id);
//				try {
//					String location2=(String)session.getAttribute("location2");
//					if (location2 != null) {
//System.out.println("location2="+location2);
//						session.removeAttribute("location2");  
//				        res.sendRedirect(location2);            
//				        return;
//				    }else {
//				    	 session.invalidate();
//				    	res.sendRedirect(req.getContextPath()+"/backEnd2.jsp");
////				    	String location22 = (String) req.getSession().getAttribute("location2");
////						
////				    	System.out.println("新"+location22);
////				    				
////				    				res.sendRedirect(location22);
//				    	
//				    }
//				
//					
//				}catch(Exception ignored) { 
//				res.sendRedirect(req.getContextPath()+"/front-end/homepage.jsp"); }
//			}
//			
//			}else {
//				System.out.println("e04");
//				
//				HttpSession session = req.getSession();
//				
//				try {
//					String location2=(String)session.getAttribute("location2");
//					if (location2 != null) {
//System.out.println("location2="+location2);
//						session.removeAttribute("location2");  
//				        session.invalidate();
//				        res.sendRedirect(location2);      
//				
//				        return;
//				    }else {
//				    	
//				    	
//				    	res.sendRedirect(req.getContextPath()+"/backEnd2.jsp");
////				    	String location22 = (String) req.getSession().getAttribute("location2");
////						
////				    	System.out.println("新"+location22);
////				    				
////				    				res.sendRedirect(location22);
//				    	
//				    }
//				
//					
//				}catch(Exception ignored) { 
//				res.sendRedirect(req.getContextPath()+"/front-end/homepage.jsp"); }
//			}
//				
//			}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
		
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
			String staff_id = null;
			// 傳送密碼
//			String to = email;
//			String subject = "員工密碼通知";
//			String messageText = "Hello! " + staff_name + " 請謹記此密碼: " + staff_password + "\n" + " (已經啟用)";
//			MailService.sendMail(to, subject, messageText);
			staffVO.setStaff_password(staff_password);
			
			//新增資料取得員工編號
			staff_id = staffService.insert(staff_password, staff_name, gender, phone, staff_status, email);
			
			
			
			
			
			
			
			String ip = "localhost";
			String to = email;
			String subject = "員工身分激活通知";
			String messageText = "Hello! " + email + " 這是你的員工編號: " + staff_id + "\n" + " 這是你的密碼: " + staff_password + "\n" + " (已經啟用)";
			
//			sendMail(to, subject, messageText);
			MailService mailService = new MailService(email, subject, messageText);
			    mailService.start();
			
			
			
			
			
			
			
			
			    String URL=req.getContextPath()+"/back-end/staff/staffPage.jsp?state=sucess2";
				res.sendRedirect(URL);
			
//			RequestDispatcher succeseView = req
//					.getRequestDispatcher("/back-end/staff/staffPage.jsp?state=sucess2");
//			succeseView.forward(req, res);
			    
			    
			    
			    
			    
			    
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
