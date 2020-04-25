<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <%@page import="com.member.model.*" %>
<%
MemberVO membervo =(MemberVO) request.getAttribute("membervo");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊頁面</title>

<script
  src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
  integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
  crossorigin="anonymous">
  
  
  </script>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1000px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>新增商品</h3></td><td>
		 <h4><a href="MemberPage.jsp"><img src="<%=request.getContextPath()%>/image/FoodPron_Logo.png"width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<!-- 注意 -->
<FORM METHOD="post" ACTION="member.do" name="form1" >
<table>

	
		<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="account" size="45" /></td>
	</tr>
		<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="password" size="45" placeholder="請填入密碼"
			 value="<%= (membervo==null)? "" : membervo.getPassword()%>" /></td>
	</tr>

	<tr>
		<td>密碼確認:</td>
		<td><input type="TEXT" name="password2" size="45" placeholder="請再次填入密碼" 
			 value="<%= (membervo==null)? "" : membervo.getPassword()%>" /></td>
	</tr>
	<tr>
		<td>EMAIL:</td>
		<td><input type="TEXT" name="email" size="45" 
			 value="<%= (membervo==null)? "" : membervo.getEmail()%>" /></td>
	</tr>
	<tr>
	
	 </table> 
	 
	

		
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="註冊"></FORM>

</body>


</html>