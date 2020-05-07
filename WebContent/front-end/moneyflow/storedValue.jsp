<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*"%>
<%@page import="com.moneyflow.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
MemberVO membervo =(MemberVO) request.getAttribute("memberVO");
MoneyflowVO moneyflowvo =(MoneyflowVO) request.getAttribute("moneyflowVO");

session.setAttribute("location", request.getRequestURI());

%>
<%
				Map<String, Integer> storedValue1 = new HashMap<>();
                storedValue1.put("0", 165);
 				storedValue1.put("1", 520);
 				storedValue1.put("2", 1140);
 				storedValue1.put("3", 2065);
 				storedValue1.put("4", 4460);
			
				application.setAttribute("storedValue", storedValue1);

			%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>儲值</title>






<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>











<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>






<style>
input {
	border-radius: 50px;
}

``````````````
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
	width: 450px;
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
<body>
<!-- 	<SCRIPT SRC="HTTPS://CODE.JQUERY.COM/JQUERY-3.4.1.JS" -->
<!-- 		INTEGRITY="SHA256-WPOOHJOQMQQYKL9FCCASB9O0KWACQJPFTUBLTYOVVVU=" -->
<!-- 		CROSSORIGIN="ANONYMOUS"></SCRIPT> -->
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="MoneyflowServlet.do" name="upateform" id="changeStatus"
		enctype="multipart/form-data">



		<table>
			<tr>
				
				<!-- 按鈕 -->
				<td>165點:<input type="submit" class="btn-primary" name="send165"></td>
		</table>
		

		
		<br> <input type="hidden" name="action" value="insert"> 
			
		
			
               <input type="hidden" name="money" value="<%=storedValue1.get("0") %>">

	
	
	
	
	
	
	
	
	
	
	
	
	
	</FORM>
	
	
	
	
	
	<FORM METHOD="post" ACTION="MoneyflowServlet.do" name="upateform" id="upateform"
		enctype="multipart/form-data">



		<table>
			<tr>
				
				<!-- 按鈕 -->
				<td>520點:<input type="submit" class="btn-primary" name="send520"></td>
		</table>
		

		
		<br> <input type="hidden" name="action" value="insert"> 
			
		
			
               <input type="hidden" name="money" value="<%=storedValue1.get("1") %>">



	</FORM>
	
	
	<FORM METHOD="post" ACTION="MoneyflowServlet.do" name="upateform" id="upateform"
		enctype="multipart/form-data">



		<table>
			<tr>
				
				<!-- 按鈕 -->
				<td>1140點:<input type="submit" class="btn-primary" name="send1140"></td>
		</table>
		

		
		<br> <input type="hidden" name="action" value="insert"> 
			
		
			
               <input type="hidden" name="money" value="<%=storedValue1.get("2") %>">



	</FORM>
	
	<FORM METHOD="post" ACTION="MoneyflowServlet.do" name="upateform" id="upateform"
		enctype="multipart/form-data">



		<table>
			<tr>
				
				<!-- 按鈕 -->
				<td>2065點:<input type="submit" class="btn-primary" name="send2065"></td>
		</table>
		

		
		<br> <input type="hidden" name="action" value="insert"> 
			
		
			
               <input type="hidden" name="money" value="<%=storedValue1.get("3") %>">



	</FORM>
	
	
	<FORM METHOD="post" ACTION="MoneyflowServlet.do" name="upateform" id="upateform"
		enctype="multipart/form-data">



		<table>
			<tr>
				
				<!-- 按鈕 -->
				<td>4460點:<input type="submit" class="btn-primary" name="send4460"></td>
		</table>
		

		
		<br> <input type="hidden" name="action" value="insert"> 
			
	
			
               <input type="hidden" name="money" value="<%=storedValue1.get("4") %>">



	</FORM>
	
	


	
	




</body>



</html>















