package com.livestream.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.livestream.model.LsService;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/Update_StreamServlet")  // 來自直播主RTCPeerConnection.jsp-[儲存錄影]的請求-2/2
public class Update_StreamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("download影片，並且upload上傳到資料庫...............");
		
			req.setCharacterEncoding("UTF-8");
			res.setContentType("Content-Type; video/webm");
			
			byte[] buffer = new byte[1024 * 1024];
			InputStream in = req.getInputStream();  
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1){
			    out.write(buffer, 0, bytesRead);
			}
			out.close();
			in.close();
			byte[] blob = out.toByteArray();
			
			HttpSession session = req.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
			MemberService memberService = new MemberService();
			memberService.changeOnline(memberVO.getMember_id(), 0);//0為下線狀態
			System.out.println("OfflineOK");

			
			String livestream_id = (String)session.getAttribute("livestream_id");
			Integer lsViewNum = (Integer)session.getAttribute("lsViewNum");
			LsService LiveStreamSvs = new LsService();
			Integer status = 3; //直播結束狀態為3
			System.out.println("lsViewNum:"+lsViewNum);
			LiveStreamSvs.updateAfterOnline(livestream_id,blob,lsViewNum,status);
			
			session.removeAttribute("livestream_id");
			session.removeAttribute("lsViewNum");
			
		System.out.println("資料庫Update完成....................................");	
		
	}

}
