<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.moneyflow.model.*"%>




<%
	MoneyflowService psvc = new MoneyflowService();
	List<MoneyflowVO> list = psvc.getAll();
	pageContext.setAttribute("list", list);
	
%>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有金流</title>



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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有金流資訊</h3>
				<h4>
					<a href="MoneyflowPage.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


<br>
<!-- 	<form METHOD="POST"  ACTION="moneyflow.do"> -->
<!--     <input type="submit" value="✚會員註冊"> -->
<!--     <input type="hidden" name="action" value="addMember"> -->
    
<!-- 	</form> -->
<!-- 	<br> -->

	<table>
		<tr>
			<th>金流編號</th>
			<th>會員編號</th>
			<th>交易狀態</th>
            <th>金額</th>
            <th>交易日期</th>
            
			<th>修改</th>
<!-- 			<th>刪除</th> -->
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="moneyflowvo" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${moneyflowvo.moneyflow_no}</td>
			    <td>${moneyflowvo.member_id}</td> 
							<%
					Map<Integer, String> map = new HashMap<>();
						map.put(0, "儲值");
						map.put(1, "提款");					
						request.setAttribute("moneyflow_status", map);
				%>
				<c:set var="moneyflow_status1" value="${moneyflowvo.moneyflow_status}" />
							
				<td>${moneyflow_status [moneyflow_status1] }</td>
				<td>${moneyflowvo.money}</td>			
	            <td>${moneyflowvo.charge_time}</td> 
				

				<td>
					<!-- 修改 -->
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/member/moneyflow.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
<!-- 						<input type="hidden" -->
<%-- 							name="flow_no" value="${moneyflowvo.flow_no}"> --%>
<!-- 							<input type="hidden" -->
<%-- 							name="member_id" value="${moneyflowvo.member_id}"> --%>
<!-- 							<input type="hidden" -->
<%-- 							name="moneyflow_status" value="${moneyflowvo.moneyflow_status}"> --%>
<!-- 								<input type="hidden" -->
<%-- 							name="money" value="${moneyflowvo.money}"> --%>
							
<!-- 							<input type="hidden" -->
<%-- 							name="charge_time" value="${moneyflowvo.charge_time}"> --%>
							
				
						<!-- 更新 -->
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					刪除 -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/member/moneyflow.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 							name="member_id1" value="${moneyflowvo.member_id}"> <input --%>
<!-- 							type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>
		</c:forEach>
	</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	

	<%@ include file="page2.file"%>
	
	

</body>
</html>