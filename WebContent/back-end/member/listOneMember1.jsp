<%@page import="com.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	String[] validation = new String[] { "未驗證","已驗證" };
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單筆會員管理資訊</title>
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
				<th class="cell100 column4">會員編號</th>
				<th class="cell100 column4">帳號</th>
				<th class="cell100 column2">會員姓名</th>
				<th class="cell100 column4">信箱</th>
				<th class="cell100 column4">驗證狀態</th>
			</tr>
		</thead>
		<tbody>
			<form method="post" action="member.do" id="update_form">
				<tr class="row100 body">
					<td class="cell100 column4" style="background-color:#FFF">${memberVO.member_id}</td>
					<td class="cell100 column4"><input type="text" name="account" value="${memberVO.account}"></td>
					<td class="cell100 column2"><input type="text" name="member_name" value="${memberVO.member_name}"> </td>
					<td class="cell100 column4"><input type="text" name="email"value="${memberVO.email}"></td>
					<td class="cell100 column4"><select name="validation">
							<%
								for (int i = 0; i < validation.length; i++) {
							%>
							<option value="<%=i%>"
								<%=(i == memberVO.getValidation()) ? "selected" : ""%>><%=validation[i]%></option>
							<%
								}
							%>
					</select></td>
<!-- 					<td class="cell100 column5"> -->
<!-- 					<select name="gender"> -->
<%-- 					<option value="0" ${staffVO.gender==0?'selected':''}>男</option> --%>
<%-- 					<option value="1"${staffVO.gender==1?'selected':''}>女</option> --%>
<!-- 					</select> -->
<!-- 					</td> -->
				</tr>
				<input type="hidden" name="oldMember" value="${memberVO.member_id}">
				<input type="hidden" name="whichPage"
					value="<%=request.getParameter("whichPage")%>"> <input
					type="hidden" name="member_id" value="${memberVO.member_id}">
				<input type="hidden" name="action" value="update1">
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