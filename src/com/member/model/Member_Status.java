package com.member.model;



import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;

public class Member_Status implements Filter{
 
 @Override
 public void init(FilterConfig filterConfig) throws ServletException {
  
 }
 
 @Override
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
   throws IOException, ServletException {

	 
	 System.out.println(1);
  HttpServletRequest req = (HttpServletRequest)request;
  HttpServletResponse res = (HttpServletResponse)response;
  HttpSession session = req.getSession();
  String member_id = (String)session.getAttribute("member_id");
  
  
  
  
  
  
  
  String member_status= (Integer)req.getSession().getAttribute("member_status")+"";
  
           if(member_status.equals("0") ) {
        		  System.out.println("非廚師");
        	   res.sendRedirect(req.getContextPath()+"/front-end/member/Permissions.jsp");  // 登入換地址，此處也要換
        	   return;
        	  }else {
        		  System.out.println("是廚師");
        	   chain.doFilter(request, response);
        	  }
  

  
 }
 
 @Override
 public void destroy() {
  
 }
}