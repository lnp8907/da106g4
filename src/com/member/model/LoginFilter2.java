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

public class LoginFilter2 implements Filter{
 
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
  if(member_id == null) {
	  System.out.println(2);
   session.setAttribute("location", req.getRequestURI());
   res.sendRedirect(req.getContextPath() + "/index.html");  // 登入換地址，此處也要換
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