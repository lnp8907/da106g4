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

import com.member.model.MemberJDBCDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;




@MultipartConfig
//@WebServlet("/StaffServlet")


public class StaffServlet extends HttpServlet{

	String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄;

	private static final long serialVersionUID = 1L;

    public StaffServlet() {
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
		
		if("getOneMemberDisplay".equals(action)) {
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
							.getRequestDispatcher("/back-end/member/StaffPage.jsp");
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
							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
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
							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("membervo", membervo); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/member_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		

//		if ("memberTestToChef".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String member_id = req.getParameter("member_id");
//				
//				/***************************2.開始查詢資料****************************************/
//				MemberService memberSvc = new MemberService();
//				MemberVO memberVO = memberSvc.getOneMember(member_id);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("membervo", memberVO);         // 資料庫取出的empVO物件,存入req
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
		
		
		
		

		
		if("memberTestToChef".equals(action)) {
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
							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
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
							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
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
							.getRequestDispatcher("/back-end/member/MemberPage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("membervo", membervo); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/Upgrade_to_chef.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/MemberPage.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		
		if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			//try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			

			//String member_id =new String(req.getParameter("member_id").trim());
				
			//String account =req.getParameter("account").trim();
			
				String account = req.getParameter("account");
				System.out.println(account);
				
		
			
			
				String password =new String(req.getParameter("password").trim());
				//String password = req.getParameter("password");
				System.out.println(password);

               // String password2 = null;
				
				String password2 =new String(req.getParameter("password2").trim());
				//String password2 = req.getParameter("password2");
				//String email =new String(req.getParameter("email"));
			
				String email = req.getParameter("email");
				System.out.println(email);


				
				if (password.equals(password2)) {
					System.out.println("");
				} else {
				errorMsgs.add("請重新輸入密碼");}
				
				
				
		
				
				


				MemberVO membervo= new MemberVO();
				
				membervo.setAccount(account);
				membervo.setPassword(password);
				membervo.setEmail(email);
				
				
				if (!errorMsgs.isEmpty()) {
				//	req.setAttribute("membervo", membervo);
				
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.insertmem(account, password, email);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("異常:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member/addMember.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String member_id = req.getParameter("member_id");
				
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(member_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/update_member.jsp";
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
		
		
		
		
		
		
		
		
	
	if ("updateToChef".equals(action)) { // 來自update_member.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
     		try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//類型用下拉試選單
     			String member_id = req.getParameter("member_id");
			
				
				System.out.println(member_id);
				
				String member_name = req.getParameter("member_name");
			
				System.out.println(member_name);
				
				
				String account =new String(req.getParameter("account").trim());
				
				
				
				System.out.println(account);
				
				
				
				
				Integer chiefapply_status = null;
//				try {
					
					chiefapply_status = 1;
//				} catch (NumberFormatException e) {
//					chiefapply_status = 0;
//					errorMsgs.add("狀態請填0或1的數字數字");
//				}
//				if(chiefapply_status>1) {
//					chiefapply_status=0;
//				}
				
				
				
			
				
				
				
				
					byte[] license = null;
					Part filePart2 = req.getPart("license");
					try {
						if(filePart2.getSize()==0) {
						
							MemberService memberSvc = new MemberService();
						
							MemberVO memberVOOld = memberSvc.getOneMember(member_id);
							
							license = memberVOOld.getLicense();
							errorMsgs.add("請上傳證照資料");
						
						}
							else {
							String fileName = Paths.get(filePart2.getSubmittedFileName()).getFileName().toString();
							System.out.println(fileName);
							
							InputStream fileContent = filePart2.getInputStream();
							license = inputStreamToByte(fileContent);
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
				MemberVO memberVO=new MemberVO();

				memberVO.setMember_id(member_id);
				
				memberVO.setMember_name(member_name);
				
				memberVO.setChiefapply_status(chiefapply_status);
				
				memberVO.setAccount(account);
				
				
				memberVO.setLicense(license);

				System.out.println(memberVO);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("123");
					req.setAttribute("membervo", memberVO);// 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/Upgrade_to_chef.jsp");
					System.out.println("123");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
//				memberVO = memberSvc.update(account, password, member_name,
//						gender, birthday, cellphone, email,
//						nickname, member_photo, validation, license, member_status,member_address, member_creditcard, balance, chiefapply_status);
				memberVO = memberSvc.update_To_Chef(member_id, account, member_name, license, chiefapply_status);
//				memberVO = memberSvc.update(password, member_name,member_address);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("membervo", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/Upgrade_to_chef.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		/////////////////////////////////////////////////////////////
		
	if("memberTestToUpdate".equals(action)) {
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
						.getRequestDispatcher("/back-end/member/MemberPage.jsp");
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
						.getRequestDispatcher("/back-end/member/MemberPage.jsp");
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
						.getRequestDispatcher("/back-end/member/MemberPage.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("membervo", membervo); // 資料庫取出的empVO物件,存入req
//			String url = "/front-end/member/update_by_self.jsp";
			
			String url = "/front-end/member/member.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/member/MemberPage.jsp");
			failureView.forward(req, res);
		}
		
	}
	
	
	
	
	

	
	
	
//	
	if ("updateBySelf".equals(action)) { 
		System.out.println(action);
	List<String> errorMsgs = new LinkedList<String>();
	// Store this set in the request scope, in case we need to
	// send the ErrorPage view.
	req.setAttribute("errorMsgs", errorMsgs);

		try {
		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		//類型用下拉試選單
			String member_id = req.getParameter("member_id");
			System.out.println(member_id);
		
		String member_name = req.getParameter("member_name");
	
		System.out.println(member_name);
		
		
		String password = req.getParameter("password");
		
		
		System.out.println(password);
		
		
		String account =new String(req.getParameter("account").trim());
		
		
		
		System.out.println(account);
		
		
		byte[] member_photo = null;
		Part filePart = req.getPart("member_photo");
		try {
			if(filePart.getSize()==0) {
				MemberService memberSvc = new MemberService();
				MemberVO memberVOOld = memberSvc.getOneMember(member_id);
				member_photo = memberVOOld.getMember_photo();
				
			}else {
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				System.out.println(fileName);
				
				InputStream fileContent = filePart.getInputStream();
				member_photo = inputStreamToByte(fileContent);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(member_photo);
		
		String email =new String(req.getParameter("email").trim());
		
		
		System.out.println(email);
			
		java.sql.Date birthday = null;
		try {
			birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
		} catch (IllegalArgumentException e) {
			birthday=new java.sql.Date(System.currentTimeMillis());
			errorMsgs.add("生日請輸入日期!");
		}
		System.out.println(birthday);
		
		
		String cellphone = req.getParameter("cellphone");
		
		
		System.out.println(cellphone);
		
		
		
		Integer gender = null;
		try {
			
			gender = new Integer(req.getParameter("gender").trim());
		} catch (NumberFormatException e) {
			gender = 0;
			errorMsgs.add("性別請填0或1的數字");
		}
		if(gender>1) {
			gender=0;
		}
		
		
		
		System.out.println(gender);
		
		
		
		
		
		
		
		String member_address = req.getParameter("member_address");
		String dv_addressameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{5,15}$";
		if (member_address == null || member_address.trim().length() == 0) {
			errorMsgs.add("地址請勿空白");
		} else if(!member_address.trim().matches(dv_addressameReg)) {
			errorMsgs.add("只能是中、英文字母、數字");
        }
		
		System.out.println(member_address);
		
		MemberVO memberVO=new MemberVO();

		memberVO.setMember_id(member_id);
		
		memberVO.setMember_name(member_name);
		
		memberVO.setAccount(account);
		
		memberVO.setPassword(password);
		

		
		memberVO.setEmail(email);
		
		memberVO.setBirthday(birthday);
		
		memberVO.setCellphone(cellphone);
		
		memberVO.setGender(gender);
		
		memberVO.setMember_address(member_address);
		
		memberVO.setMember_photo(member_photo);
		
		
		
		
		
		
		
		
		
	

		System.out.println(memberVO);
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			System.out.println("123");
			req.setAttribute("membervo", memberVO);// 含有輸入格式錯誤的empVO物件,也存入req
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/member/update_by_self.jsp");
			System.out.println("123");
			failureView.forward(req, res);
			return; //程式中斷
		}
		
		/***************************2.開始修改資料*****************************************/
		MemberService memberSvc = new MemberService();
		System.out.println("4444444444444444");
		System.out.println(member_id+ member_name+ account+ password+ email+ birthday+ cellphone+ gender+ member_address+ member_photo);
		memberVO = memberSvc.update_by_self(member_id, member_name, account, password, email, birthday, cellphone, gender, member_address, member_photo);
		System.out.println("144444444444444423");
		/***************************3.修改完成,準備轉交(Send the Success view)*************/
		req.setAttribute("membervo", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
		String url = "/back-end/member/listAllMember.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
		successView.forward(req, res);

		/***************************其他可能的錯誤處理*************************************/
	} catch (Exception e) {
		
		errorMsgs.add("修改資料失敗:"+e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/front-end/member/update_by_self.jsp");
		failureView.forward(req, res);
	}
	
	}
//		
//	}
	
	///////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
		
		
		
//新增
		if ("update".equals(action)) { // 來自update_member.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
     		try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//類型用下拉試選單
				String account =new String(req.getParameter("account").trim());
				
				String password = req.getParameter("password");
				String passwordReg = "^[(a-zA-Z0-9_)]{5,20}$";
				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!password.trim().matches(passwordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼只能是英文字母與數字 , 且長度必需在5到20之間");
	            }
				
				

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("生日請輸入日期!");
				}
		
				
				
				String member_id = req.getParameter("member_id");
				if (member_id == null || (member_id.trim()).length() == 0) {
					errorMsgs.add("不得為空值");
				}
				
				//會員姓名
				String member_name = req.getParameter("member_name");
				String enameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if (member_name == null || member_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!member_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字 , 且長度必需在2到10之間");
	            }

				
				Integer gender = null;
				try {
					
					gender = new Integer(req.getParameter("gender").trim());
				} catch (NumberFormatException e) {
					gender = 0;
					errorMsgs.add("性別請填0或1的數字");
				}
				if(gender>1) {
					gender=0;
				}
				
				
				String cellphone = req.getParameter("cellphone");
				String ecellphoneReg = "^09[0-9]{2}-\\d{6}$";
				
//				if (cellphone == null || cellphone.trim().length() == 0) {
//					errorMsgs.add("電話請勿空白");
//				} else if(!cellphone.trim().matches(ecellphoneReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("電話長度必須符合格式");
//	            }
//				
//				
				String email =new String(req.getParameter("email").trim());
				
				
				
				
				String nickname =new String(req.getParameter("nickname").trim());
				
				
//		
//				
//				}
				Integer validation = null;
				try {
					
					validation = new Integer(req.getParameter("validation").trim());
				} catch (NumberFormatException e) {
					validation = 0;
					errorMsgs.add("狀態請填0或1的數字數字");
				}
				if(validation>1) {
					validation=0;
				}
//				
//				
//              
//				
//				
//				
//				
				Integer member_status = null;
				try {
					
					member_status = new Integer(req.getParameter("member_status").trim());
				} catch (NumberFormatException e) {
					member_status = 0;
					errorMsgs.add("狀態請填0或1的數字數字");
				}
				if(member_status>1) {
					member_status=0;
				}
			
				
				
				String member_address = req.getParameter("member_address");
				String dv_addressameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{5,15}$";
				if (member_address == null || member_address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				} else if(!member_address.trim().matches(dv_addressameReg)) {
					errorMsgs.add("只能是中、英文字母、數字");
	            }
				
				System.out.println(member_address);
				String member_creditcard = req.getParameter("member_creditcard");
				String member_creditcardReg = "^\\d{16}$";
				if (member_creditcard == null || member_creditcard.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} else if(!member_creditcard.trim().matches(member_creditcardReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("信用卡長度必須符合格式");
	            }
				
				Integer balance = null;
				try {
					balance = new Integer(req.getParameter("balance").trim());
				} catch (NumberFormatException e) {
					balance = null;
					errorMsgs.add("請填數字.");
				}
//				
//
				Integer chiefapply_status = null;
//				try {
					
					chiefapply_status = new Integer(req.getParameter("chiefapply_status").trim());
//				} catch (NumberFormatException e) {
//					chiefapply_status = 0;
//					errorMsgs.add("狀態請填0或1的數字數字");
//				}
//				if(chiefapply_status>1) {
//					chiefapply_status=0;
//				}
//		
					
					byte[] member_photo = null;
					Part filePart = req.getPart("member_photo");
					try {
						if(filePart.getSize()==0) {
							MemberService memberSvc = new MemberService();
							MemberVO memberVOOld = memberSvc.getOneMember(member_id);
							member_photo = memberVOOld.getMember_photo();
							
						}else {
							String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
							System.out.println(fileName);
							
							InputStream fileContent = filePart.getInputStream();
							member_photo = inputStreamToByte(fileContent);
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					byte[] license = null;
					Part filePart2 = req.getPart("license");
					try {
						if(filePart2.getSize()==0) {
							MemberService memberSvc = new MemberService();
							MemberVO memberVOOld = memberSvc.getOneMember(member_id);
							license = memberVOOld.getLicense();
							
						}else {
							String fileName = Paths.get(filePart2.getSubmittedFileName()).getFileName().toString();
							System.out.println(fileName);
							
							InputStream fileContent = filePart2.getInputStream();
							license = inputStreamToByte(fileContent);
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					
					
					
					
					
					
					
					

				MemberVO memberVO=new MemberVO();
				memberVO.setAccount(account);
				memberVO.setPassword(password);
				memberVO.setMember_name(member_name);
				memberVO.setGender(gender);
				memberVO.setBirthday(birthday);
				memberVO.setCellphone(cellphone);
				memberVO.setEmail(email);
				memberVO.setNickname(nickname);
				memberVO.setMember_photo(member_photo);
				memberVO.setValidation(validation);
				memberVO.setLicense(license);
				memberVO.setMember_status(member_status);
				memberVO.setMember_address(member_address);
				memberVO.setMember_creditcard(member_creditcard);
				memberVO.setBalance(balance);
				memberVO.setChiefapply_status(chiefapply_status);
				memberVO.setMember_id(member_id);

				System.out.println(memberVO);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					req.setAttribute("memberVO", memberVO);// 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
//				memberVO = memberSvc.update(account, password, member_name,
//						gender, birthday, cellphone, email,
//						nickname, member_photo, validation, license, member_status,member_address, member_creditcard, balance, chiefapply_status);
				memberVO = memberSvc.update(member_id,account, password, member_name,
						gender, birthday, cellphone, email,
						nickname, validation, member_status,member_address, member_creditcard, balance, chiefapply_status, member_photo, license);
//				memberVO = memberSvc.update(password, member_name,member_address);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/update_member.jsp");
				failureView.forward(req, res);
			}
			
		}
//處理新增

	}
		public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
			System.out.println("header=" + header); // 測試用
			String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
			System.out.println("filename=" + filename); // 測試用
			if (filename.length() == 0) {
				return null;
			}
			return filename;
		}
		public static byte[] inputStreamToByte(InputStream is) throws Exception{
	        BufferedInputStream bis = new BufferedInputStream(is);
	        byte [] a = new byte[1000];
	        int len = 0;
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        while((len = bis.read(a))!=-1){
	            bos.write(a, 0, len);
	        }
	        bis.close();
	        bos.close();
	        return bos.toByteArray();
	    }

		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	

