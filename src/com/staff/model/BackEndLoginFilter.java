package com.staff.model;



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

public class BackEndLoginFilter implements Filter{
 
 @Override
 public void init(FilterConfig filterConfig) throws ServletException {
  
 }
 
 @Override
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
   throws IOException, ServletException {
//	 System.out.println(1);
//  HttpServletRequest req = (HttpServletRequest)request;
//  HttpServletResponse res = (HttpServletResponse)response;
//  HttpSession session = req.getSession();
//  String staff_id = (String)session.getAttribute("staff_id");
// if(staff_id == null) {
//	
////   session.setAttribute("location2", req.getRequestURI());
//   System.out.println(2);
//    // 登入換地址，此處也要換http://localhost:8081/DA106_G4_Foodporn_Git/backEnd2.jsp
//   return;
//  }else {
//	  System.out.println(3);
//   chain.doFilter(request, response);
	 System.out.println(1);
  HttpServletRequest req = (HttpServletRequest)request;
  HttpServletResponse res = (HttpServletResponse)response;
  HttpSession session = req.getSession();
  String staff_id = (String)session.getAttribute("staff_id");
  System.out.println(staff_id);
  if(staff_id == null) {
	 
   session.setAttribute("location2", req.getRequestURI());
   System.out.println(2);
   res.sendRedirect(req.getContextPath() + "/backEnd_Login.jsp");  // 登入換地址，此處也要換
   return;
  }else {
	  System.out.println(3);
   chain.doFilter(request, response);
  }
  
 }
 
 @Override
 public void destroy() {
  
 }
}