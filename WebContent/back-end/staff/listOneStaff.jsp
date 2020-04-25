<%@page import="com.staff.model.StaffVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	StaffVO staffVO = (StaffVO) request.getAttribute("staffVO");
	String[] status = new String[] { "外送員", "正職員工", "最高管理員", "已離職" };
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單筆課程管理資訊</title>
<style>
input{
text-align:center;
display:inline-block;
background-color:#fa7f7f2b;
width:100%;
}
.column2{
width:16%;
}
.column5{
width:5%;
}
</style>
</head>
<body>
	<table class="table">
		<thead>
			<tr class="row100 head">
				<th class="cell100 column4">員工編號</th>
				<th class="cell100 column4">員工姓名</th>
				<th class="cell100 column2">電子信箱</th>
				<th class="cell100 column4">連絡電話</th>
				<th class="cell100 column4">職位</th>
				<th class="cell100 column5">性別</th>
			</tr>
		</thead>
		<tbody>
			<form method="post" action="StaffServlet" id="update_form">
				<tr class="row100 body">
					<td class="cell100 column4" style="background-color:#FFF">${staffVO.staff_id}</td>
					<td class="cell100 column4"><input type="text" name="staff_name" value="${staffVO.staff_name}"></td>
					<td class="cell100 column2"><input type="text" name="email" value="${staffVO.email}"> </td>
					<td class="cell100 column4"><input type="text" name="phone"value="${staffVO.phone}"></td>
					<td class="cell100 column4"><select name="staff_status">
							<%
								for (int i = 0; i < status.length; i++) {
							%>
							<option value="<%=i%>"
								<%=(i == staffVO.getStaff_status()) ? "selected" : ""%>><%=status[i]%></option>
							<%
								}
							%>
					</select></td>
					<td class="cell100 column5">
					<select name="gender">
					<option value="0" ${staffVO.gender==0?'selected':''}>男</option>
					<option value="1"${staffVO.gender==1?'selected':''}>女</option>
					</select>
					</td>
				</tr>
				<input type="hidden" name="oldStatus" value="${staffVO.staff_id}">
				<input type="hidden" name="whichPage"
					value="<%=request.getParameter("whichPage")%>"> <input
					type="hidden" name="staff_id" value="${staffVO.staff_id}">
				<input type="hidden" name="action" value="update">
			</form>
		</tbody>
	</table>
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