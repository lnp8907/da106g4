package com.member.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.member.model.MemberDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.moneyflow.model.MoneyflowDAO;
import com.moneyflow.model.MoneyflowJDBCDAO;
import com.moneyflow.model.MoneyflowService;
import com.moneyflow.model.MoneyflowVO;





@MultipartConfig
@WebServlet("/front-end/moneyflow/MoneyflowServlet.do")


public class MoneyflowServlet extends HttpServlet{

	//String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄;

	private static final long serialVersionUID = 1L;

    public MoneyflowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("進入"+"MoneyflowServlet");
		
		
		
		//========================================購物測試2===============================================================================================
				if("consume3".equals(action)) {
					System.out.println("進入"+"ACTIN=consume2");

					Integer money =Integer.valueOf(req.getParameter("money")) ;//取金流
					//String money = req.getParameter("money");
			System.out.println(money);
					String member_id= (String) req.getSession().getAttribute("member_id");//取會員編號
			System.out.println(member_id);
					

				

					MemberService svc = new MemberService();
					MemberVO memberVO=new MemberVO();
					memberVO= svc.getOneMember(member_id);
					
					System.out.println("現有"+memberVO.getBalance());
					if(memberVO.getBalance() >= money) {
						
						memberVO.setBalance(memberVO.getBalance()- money);
						System.out.println("更新"+memberVO.getBalance());
		
					}else {
						
						    boolean openModal = true;
				    	    req.setAttribute("openModal", openModal);
						    System.out.println(openModal);
					}
	

					
	
					
					Integer balance =Integer.valueOf(memberVO.getBalance()) ;//取金流
					
					
				//MemberService memberSvc = new MemberService();//更改會員總點數
				//memberSvc.updateStoredValue(member_id, balance);
					svc.updateStoredValue(member_id, balance);
				//-------------------------------
//				  boolean openModal = true;
//				    req.setAttribute("openModal", openModal);
//				    System.out.println(openModal);
				//----------------------------------	
				
//				String url = "/back-end/member/listAllMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
				
					String url = "/front-end/moneyflow/TestConsume.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

				}
				
				
		//============================================================================================================================
		
		
		
		
		
		
		
		
		
		
		
		
		
	//========================================購物測試===============================================================================================
		if("consume2".equals(action)) {
			System.out.println("進入"+"ACTIN=consume2");

			Integer money =Integer.valueOf(req.getParameter("money")) ;//取金流
			//String money = req.getParameter("money");
	System.out.println(money);
			String member_id= (String) req.getSession().getAttribute("member_id");//取會員編號
	System.out.println(member_id);
			

		

			MemberService svc = new MemberService();
			MemberVO memberVO=new MemberVO();
			memberVO= svc.getOneMember(member_id);
System.out.println("現有"+memberVO.getBalance());

			memberVO.setBalance(memberVO.getBalance()- money);
System.out.println("更新"+memberVO.getBalance());
			
			Integer balance =Integer.valueOf(memberVO.getBalance()) ;//取金流
			
			
		//MemberService memberSvc = new MemberService();//更改會員總點數
		//memberSvc.updateStoredValue(member_id, balance);
			svc.updateStoredValue(member_id, balance);
		//-------------------------------
//		  boolean openModal = true;
//		    req.setAttribute("openModal", openModal);
//		    System.out.println(openModal);
		//----------------------------------	
		
		String url = "/back-end/member/listAllMember.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
		successView.forward(req, res);
		
	

		}
		
		
//============================================================================================================================
		if("insert".equals(action)) {
			System.out.println("進入"+"ACTIN=insert");

			Integer money =Integer.valueOf(req.getParameter("money")) ;//取金流
			//String money = req.getParameter("money");
	System.out.println(money);
			String member_id= (String) req.getSession().getAttribute("member_id");//取會員編號
	System.out.println(member_id);
			
			MoneyflowVO moneyflowVO=new MoneyflowVO();
			moneyflowVO.setMember_id(member_id);
			moneyflowVO.setMoney(money);
			
			
			
			MoneyflowService mfDao = new MoneyflowService();//處理金流
			
			mfDao.insert(member_id, 0, money);
			
			

			MemberService svc = new MemberService();
			MemberVO memberVO=new MemberVO();
			memberVO= svc.getOneMember(member_id);
			System.out.println("現有"+memberVO.getBalance());

			memberVO.setBalance(memberVO.getBalance()+ money);
			System.out.println("新"+memberVO.getBalance());
			
			Integer balance =Integer.valueOf(memberVO.getBalance()) ;//取金流
			
			
		//MemberService memberSvc = new MemberService();//更改會員總點數
		//memberSvc.updateStoredValue(member_id, balance);
			svc.updateStoredValue(member_id, balance);
		//-------------------------------
//		  boolean openModal = true;
//		    req.setAttribute("openModal", openModal);
//		    System.out.println(openModal);
		//----------------------------------	
		
//		String url = "/back-end/moneyflow/listAllMoneyflow.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//		successView.forward(req, res);
			String location = (String) req.getSession().getAttribute("location");
			
System.out.println("新"+location);
			
			res.sendRedirect(location);
			
			
			
//		String url = "/front-end/moneyflow/storedValue.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//		successView.forward(req, res);

		}
		
		
		
		if("consume".equals(action)) {
			System.out.println("進入"+"ACTIN=consume");

			Integer money =Integer.valueOf(req.getParameter("money")) ;//取金流
			//String money = req.getParameter("money");
	System.out.println(money);
			String member_id= (String) req.getSession().getAttribute("member_id");//取會員編號
	System.out.println(member_id);
			
			MoneyflowVO moneyflowVO=new MoneyflowVO();
			moneyflowVO.setMember_id(member_id);
			moneyflowVO.setMoney(money);
			
			
			
			MoneyflowService mfDao = new MoneyflowService();//處理金流
			
			mfDao.insert(member_id, 1, money);
			
			

			MemberService svc = new MemberService();
			MemberVO memberVO=new MemberVO();
			memberVO= svc.getOneMember(member_id);
		//	System.out.println("現有"+memberVO.getBalance());

			memberVO.setBalance(memberVO.getBalance()- money);
		//	System.out.println("新"+memberVO.getBalance());
			
			Integer balance =Integer.valueOf(memberVO.getBalance()) ;//取金流
			svc.updateStoredValue(member_id, balance);
		//-------------------------------
//		  boolean openModal = true;
//		    req.setAttribute("openModal", openModal);
//		    System.out.println(openModal);
		//----------------------------------	
	
		
		RequestDispatcher failureView = req
				.getRequestDispatcher("/front-end/member/ChefWithdraw.jsp");
		 // 成功轉交 listOneEmp.jsp
		failureView.forward(req, res);
		
//		String url = "/front-end/moneyflow/storedValue.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//		successView.forward(req, res);

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if("getOneMoneyflowDisplay".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("moneyflow_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入金流編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String moneyflow_no = null;
				try {
					moneyflow_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("金流編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MoneyflowService moneyflowSvc = new MoneyflowService();
				MoneyflowVO moneyflowvo = moneyflowSvc.getOneMoneyflow(moneyflow_no);
				if (moneyflowvo == null) {
					errorMsgs.add("查無金流資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("moneyflowvo", moneyflowvo); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/listOneMoneyflow.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得金流資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/moneyflow/moneyflowpage.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
	//////////////////////////////////////////////////////////////////////////////////////////////////	
		
		
		
		
		if("memberStoredValue".equals(action)) {
			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("member_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/moneyflow/moneyflowpage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String member_id = null;
				try {
					member_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/moneyflow/moneyflowpage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO membervo = memberSvc.getOneMember(member_id);
				if (membervo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/moneyflow/Moneyflowpage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("membervo", membervo); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/moneyflow/storedValue.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/moneyflow/Moneyflowpage.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
//if 
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
		

//		if ("memberTestToChef".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String moneyflow_no = req.getParameter("moneyflow_no");
//				
//				/***************************2.開始查詢資料****************************************/
//				MoneyflowService moneyflowSvc = new MoneyflowService();
//				MoneyflowVO moneyflowVO = moneyflowSvc.getOneMoneyflow(moneyflow_no);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("membervo", moneyflowVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/back-end/member/Upgrade_to_chef.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member/listAllMember.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		
		

		
//		if("memberTestToChef".equals(action)) {
//			System.out.println(action);
//			List<String> errorMsgs = new LinkedList<String>();
//			
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("moneyflow_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入會員編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String moneyflow_no = null;
//				try {
//					moneyflow_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("會員編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				MoneyflowService moneyflowSvc = new MoneyflowService();
//				MoneyflowVO membervo = moneyflowSvc.getOneMoneyflow(moneyflow_no);
//				if (membervo == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("membervo", membervo); // 資料庫取出的empVO物件,存入req
//				String url = "/back-end/member/Upgrade_to_chef.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member/MemberPage.jsp");
//				failureView.forward(req, res);
//			}
//			
//		}
		
		
		
		
		
//		if ("insert".equals(action)) { 
//			
//			List<String> errorMsgs = new LinkedList<String>();
//
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			//try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//			
//
//			//String moneyflow_no =new String(req.getParameter("moneyflow_no").trim());
//				
//			//String account =req.getParameter("account").trim();
//			
//				String account = req.getParameter("account");
//				System.out.println(account);
//				
//		
//			
//			
//				String password =new String(req.getParameter("password").trim());
//				//String password = req.getParameter("password");
//				System.out.println(password);
//
//               // String password2 = null;
//				
//				String password2 =new String(req.getParameter("password2").trim());
//				//String password2 = req.getParameter("password2");
//				//String email =new String(req.getParameter("email"));
//			
//				String email = req.getParameter("email");
//				System.out.println(email);
//
//
//				
//				if (password.equals(password2)) {
//					System.out.println("");
//				} else {
//				errorMsgs.add("請重新輸入密碼");}
//				
//				
//				
//		
//				
//				
//
//
//				MoneyflowVO membervo= new MoneyflowVO();
//				
//				membervo.setAccount(account);
//				membervo.setPassword(password);
//				membervo.setEmail(email);
//				
//				
//				if (!errorMsgs.isEmpty()) {
//				//	req.setAttribute("membervo", membervo);
//				
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member/addMember.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				/***************************2.開始新增資料***************************************/
//				MoneyflowService moneyflowSvc = new MoneyflowService();
//				moneyflowSvc.insertmem(account, password, email);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/member/listAllMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
////			} catch (Exception e) {
////				errorMsgs.add("異常:"+e.getMessage());
////				RequestDispatcher failureView = req
////						.getRequestDispatcher("/back-end/member/addMember.jsp");
////				failureView.forward(req, res);
////			}
//		}
		
		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String moneyflow_no = req.getParameter("moneyflow_no");
				
				/***************************2.開始查詢資料****************************************/
				MoneyflowService moneyflowSvc = new MoneyflowService();
				MoneyflowVO moneyflowVO = moneyflowSvc.getOneMoneyflow(moneyflow_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("moneyflowVO", moneyflowVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/moneyflow/update_moneyflow.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
			
		
		
		if ("addMember".equals(action)) {
			System.out.println(action);
			String url = "/back-end/member/addMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
	
	}
		
		
		
		
		
		
		
		
	
//	if ("updateToChef".equals(action)) { // 來自update_member.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//     		try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				//類型用下拉試選單
//     			String moneyflow_no = req.getParameter("moneyflow_no");
//			
//				
//				System.out.println(moneyflow_no);
//				
//				String member_name = req.getParameter("member_name");
//			
//				System.out.println(member_name);
//				
//				
//				String account =new String(req.getParameter("account").trim());
//				
//				
//				
//				System.out.println(account);
//				
//				
//				
//				
//				Integer chiefapply_status = null;
////				try {
//					
//					chiefapply_status = 1;
////				} catch (NumberFormatException e) {
////					chiefapply_status = 0;
////					errorMsgs.add("狀態請填0或1的數字數字");
////				}
////				if(chiefapply_status>1) {
////					chiefapply_status=0;
////				}
//				
//				
//				
//			
//				
//				
//				
//				
//					byte[] license = null;
//					Part filePart2 = req.getPart("license");
//					try {
//						if(filePart2.getSize()==0) {
//						
//							MoneyflowService moneyflowSvc = new MoneyflowService();
//						
//							MoneyflowVO moneyflowVOOld = moneyflowSvc.getOneMoneyflow(moneyflow_no);
//							
//							license = moneyflowVOOld.getLicense();
//							errorMsgs.add("請上傳證照資料");
//						
//						}
//							else {
//							String fileName = Paths.get(filePart2.getSubmittedFileName()).getFileName().toString();
//							System.out.println(fileName);
//							
//							InputStream fileContent = filePart2.getInputStream();
//							license = inputStreamToByte(fileContent);
//						}
//						
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
//				
//				MoneyflowVO moneyflowVO=new MoneyflowVO();
//
//				moneyflowVO.setMember_id(moneyflow_no);
//				
//				moneyflowVO.setMember_name(member_name);
//				
//				moneyflowVO.setChiefapply_status(chiefapply_status);
//				
//				moneyflowVO.setAccount(account);
//				
//				
//				moneyflowVO.setLicense(license);
//
//				System.out.println(moneyflowVO);
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					System.out.println("123");
//					req.setAttribute("membervo", moneyflowVO);// 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member/Upgrade_to_chef.jsp");
//					System.out.println("123");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				MoneyflowService moneyflowSvc = new MoneyflowService();
////				moneyflowVO = moneyflowSvc.update(account, password, member_name,
////						gender, birthday, cellphone, email,
////						nickname, member_photo, validation, license, member_status,member_address, member_creditcard, balance, chiefapply_status);
//				moneyflowVO = moneyflowSvc.update_To_Chef(moneyflow_no, account, member_name, license, chiefapply_status);
////				moneyflowVO = moneyflowSvc.update(password, member_name,member_address);
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("membervo", moneyflowVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/back-end/member/listAllMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member/Upgrade_to_chef.jsp");
//				failureView.forward(req, res);
//			}
//			
//		}
		
		
		
		
		
		
		
		
//新增
//		if ("update".equals(action)) { // 來自update_member.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//     		try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				//類型用下拉試選單
//				String account =new String(req.getParameter("account").trim());
//				
//				String password = req.getParameter("password");
//				String passwordReg = "^[(a-zA-Z0-9_)]{5,20}$";
//				if (password == null || password.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else if(!password.trim().matches(passwordReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("密碼只能是英文字母與數字 , 且長度必需在5到20之間");
//	            }
//				
//				
//
//				java.sql.Date birthday = null;
//				try {
//					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
//				} catch (IllegalArgumentException e) {
//					birthday=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("生日請輸入日期!");
//				}
//		
//				
//				
//				String moneyflow_no = req.getParameter("moneyflow_no");
//				if (moneyflow_no == null || (moneyflow_no.trim()).length() == 0) {
//					errorMsgs.add("不得為空值");
//				}
//				
//				//會員姓名
//				String member_name = req.getParameter("member_name");
//				String enameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{2,10}$";
//				if (member_name == null || member_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else if(!member_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員姓名: 只能是中、英文字母、數字 , 且長度必需在2到10之間");
//	            }
//
//				
//				Integer gender = null;
//				try {
//					
//					gender = new Integer(req.getParameter("gender").trim());
//				} catch (NumberFormatException e) {
//					gender = 0;
//					errorMsgs.add("性別請填0或1的數字");
//				}
//				if(gender>1) {
//					gender=0;
//				}
//				
//				
//				String cellphone = req.getParameter("cellphone");
//				String ecellphoneReg = "^09[0-9]{2}-\\d{6}$";
//				
////				if (cellphone == null || cellphone.trim().length() == 0) {
////					errorMsgs.add("電話請勿空白");
////				} else if(!cellphone.trim().matches(ecellphoneReg)) { //以下練習正則(規)表示式(regular-expression)
////					errorMsgs.add("電話長度必須符合格式");
////	            }
////				
////				
//				String email =new String(req.getParameter("email").trim());
//				
//				
//				
//				
//				String nickname =new String(req.getParameter("nickname").trim());
//				
//				
////		
////				
////				}
//				Integer validation = null;
//				try {
//					
//					validation = new Integer(req.getParameter("validation").trim());
//				} catch (NumberFormatException e) {
//					validation = 0;
//					errorMsgs.add("狀態請填0或1的數字數字");
//				}
//				if(validation>1) {
//					validation=0;
//				}
////				
////				
////              
////				
////				
////				
////				
//				Integer member_status = null;
//				try {
//					
//					member_status = new Integer(req.getParameter("member_status").trim());
//				} catch (NumberFormatException e) {
//					member_status = 0;
//					errorMsgs.add("狀態請填0或1的數字數字");
//				}
//				if(member_status>1) {
//					member_status=0;
//				}
//			
//				
//				
//				String member_address = req.getParameter("member_address");
//				String dv_addressameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{5,15}$";
//				if (member_address == null || member_address.trim().length() == 0) {
//					errorMsgs.add("地址請勿空白");
//				} else if(!member_address.trim().matches(dv_addressameReg)) {
//					errorMsgs.add("只能是中、英文字母、數字");
//	            }
//				
//				System.out.println(member_address);
//				String member_creditcard = req.getParameter("member_creditcard");
//				String member_creditcardReg = "^\\d{16}$";
//				if (member_creditcard == null || member_creditcard.trim().length() == 0) {
//					errorMsgs.add("請勿空白");
//				} else if(!member_creditcard.trim().matches(member_creditcardReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("信用卡長度必須符合格式");
//	            }
//				
//				Integer balance = null;
//				try {
//					balance = new Integer(req.getParameter("balance").trim());
//				} catch (NumberFormatException e) {
//					balance = null;
//					errorMsgs.add("請填數字.");
//				}
////				
////
//				Integer chiefapply_status = null;
////				try {
//					
//					chiefapply_status = new Integer(req.getParameter("chiefapply_status").trim());
////				} catch (NumberFormatException e) {
////					chiefapply_status = 0;
////					errorMsgs.add("狀態請填0或1的數字數字");
////				}
////				if(chiefapply_status>1) {
////					chiefapply_status=0;
////				}
////		
//					
//					byte[] member_photo = null;
//					Part filePart = req.getPart("member_photo");
//					try {
//						if(filePart.getSize()==0) {
//							MoneyflowService moneyflowSvc = new MoneyflowService();
//							MoneyflowVO moneyflowVOOld = moneyflowSvc.getOneMoneyflow(moneyflow_no);
//							member_photo = moneyflowVOOld.getMember_photo();
//							
//						}else {
//							String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//							System.out.println(fileName);
//							
//							InputStream fileContent = filePart.getInputStream();
//							member_photo = inputStreamToByte(fileContent);
//						}
//						
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
//					
//					
//					
//					byte[] license = null;
//					Part filePart2 = req.getPart("license");
//					try {
//						if(filePart2.getSize()==0) {
//							MoneyflowService moneyflowSvc = new MoneyflowService();
//							MoneyflowVO moneyflowVOOld = moneyflowSvc.getOneMoneyflow(moneyflow_no);
//							license = moneyflowVOOld.getLicense();
//							
//						}else {
//							String fileName = Paths.get(filePart2.getSubmittedFileName()).getFileName().toString();
//							System.out.println(fileName);
//							
//							InputStream fileContent = filePart2.getInputStream();
//							license = inputStreamToByte(fileContent);
//						}
//						
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
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
//
//				MoneyflowVO moneyflowVO=new MoneyflowVO();
//				moneyflowVO.setAccount(account);
//				moneyflowVO.setPassword(password);
//				moneyflowVO.setMember_name(member_name);
//				moneyflowVO.setGender(gender);
//				moneyflowVO.setBirthday(birthday);
//				moneyflowVO.setCellphone(cellphone);
//				moneyflowVO.setEmail(email);
//				moneyflowVO.setNickname(nickname);
//				moneyflowVO.setMember_photo(member_photo);
//				moneyflowVO.setValidation(validation);
//				moneyflowVO.setLicense(license);
//				moneyflowVO.setMember_status(member_status);
//				moneyflowVO.setMember_address(member_address);
//				moneyflowVO.setMember_creditcard(member_creditcard);
//				moneyflowVO.setBalance(balance);
//				moneyflowVO.setChiefapply_status(chiefapply_status);
//				moneyflowVO.setMember_id(moneyflow_no);
//
//				System.out.println(moneyflowVO);
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					
//					req.setAttribute("moneyflowVO", moneyflowVO);// 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/moneyflow/update_moneyflow.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				MoneyflowService moneyflowSvc = new MoneyflowService();
////				moneyflowVO = moneyflowSvc.update(account, password, member_name,
////						gender, birthday, cellphone, email,
////						nickname, member_photo, validation, license, member_status,member_address, member_creditcard, balance, chiefapply_status);
//				moneyflowVO = moneyflowSvc.update(moneyflow_no,account, password, member_name,
//						gender, birthday, cellphone, email,
//						nickname, validation, member_status,member_address, member_creditcard, balance, chiefapply_status, member_photo, license);
////				moneyflowVO = moneyflowSvc.update(password, member_name,member_address);
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("moneyflowVO", moneyflowVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/back-end/member/listAllMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/moneyflow/update_moneyflow.jsp");
//				failureView.forward(req, res);
//			}
//			
//		}
////處理新增
//
	}
		
		
		
	}
	
	
	
	
	
	
	
	
	

