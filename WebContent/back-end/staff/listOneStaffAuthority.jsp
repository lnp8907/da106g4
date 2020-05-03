<%@page import="com.power.model.PowerVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="com.authority.model.AuthorityService"%>
<%@page import="com.power.model.PowerService"%>
<%@page import="com.staff.model.StaffVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	StaffVO staffVO = (StaffVO) request.getAttribute("staffVO");
	PowerService powerService = new PowerService();
	List<PowerVO> list = powerService.getAll();
	Set<String> authorities = (Set<String>) request.getAttribute("authorities");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單筆課程管理資訊</title>
<style>
input {
	text-align: center;
	display: inline-block;
	background-color: #fa7f7f2b;
	width: 100%;
}

.column2 {
	width: 16%;
}

.column5 {
	width: 5%;
}
</style>
</head>
<body>

<span style="margin-right:10px;"><%=staffVO.getStaff_id() %></span>
<span><%=staffVO.getStaff_name() %></span>
<br>
<form method="post" action="AuthorityServlet" id="power_form">
<table>
	<%
		for (PowerVO a : list) {
	%>
	<tr>
	<td style="width:50px;"><%=a.getPower_name()%></td><td style="width:10px;"><input name="power" type="checkbox" value="<%=a.getPower_no()%>"
		<%for (String b : authorities) {
			if (b.equals(a.getPower_no())){%>
		<%="checked"%> 
		<%}}%>>
		
		
	</td></tr>	
	<%
		}
	%>
	
</table>
	<input type="hidden" name="staff_id" value="<%=staffVO.getStaff_id() %>">
	<input type="hidden" name="action" value="update" >

</form>	
	<div class="errorMess">
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
</body>
</html>