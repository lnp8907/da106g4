package com.livestream.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.livestream.model.LivestreamVO;
import com.livestream.model.LsService;
import com.member.model.MemberService;


@WebServlet("/InsertOrDelete_StreamServlet")
public class InsertOrDelete_StreamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("updateAfterOnline".equals(action)) { // 來自直播主RTCPeerConnection.jsp-[結束錄影]的請求-1/2

			 System.out.println("insert...........................");
				
			 	//修改廚師直播狀態為Offline
			 	MemberService memberService = new MemberService();
			 	String member_id=req.getParameter("member_id");
			 	Integer num = 0; //
			 	memberService.changeOffline(member_id, num);
			 	
			 	
			 	
			 	LsService liveStreamSvc = new LsService();
			 	Integer lsViewNum =Integer.valueOf(req.getParameter("lsViewNum"));
				String livestream_id = req.getParameter("livestream_id");
				Integer status = 3; //直播結束狀態為3
				liveStreamSvc.updateAfterOnline(livestream_id, null, lsViewNum,status);
				HttpSession session = req.getSession();
				session.setAttribute("livestream_id", livestream_id);
				session.setAttribute("lsViewNum", lsViewNum);
				
			 System.out.println("update完成.......................");

        }
		

//		if ("delete".equals(action)) { // 來直播管理listAllStream.jsp-[刪除]的請求
//
//			 System.out.println("delete...........................");
//			
//			 LsService liveStreamSvc = new LsService();
//				String lsId=req.getParameter("lsId").toUpperCase();
//				liveStreamSvc.delete(lsId);
//			
//			 System.out.println("delete完成.......................");	
//				
//			    String url = "/listAllStream.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllStream.jsp.jsp
//				successView.forward(req, res);
//		}
		
	}
}
