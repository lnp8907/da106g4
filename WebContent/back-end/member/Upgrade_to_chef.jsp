<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*"%>
<%
	MemberVO membervo = (MemberVO) request.getAttribute("membervo");
%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>更改會員資訊</title>



<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
	<SCRIPT SRC="HTTPS://CODE.JQUERY.COM/JQUERY-3.4.1.JS"
		INTEGRITY="SHA256-WPOOHJOQMQQYKL9FCCASB9O0KWACQJPFTUBLTYOVVVU="
		CROSSORIGIN="ANONYMOUS"></SCRIPT>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="member.do" name="upateform" id="upateform"
		enctype="multipart/form-data">






		<table>
		

			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>

				<td><%=membervo.getMember_id()%></td>

			</tr>

			<tr>
				<td>會員姓名:</td>
				<td><%=membervo.getMember_name()%></td>
			</tr>

			<tr>
				<td>會員帳號:</td>
				<td><%=membervo.getAccount()%></td>
			</tr>
			
		


			<tr>
				<td>廚師證照:</td>
				<!-- 按鈕 -->
				<td><input type="file" id="imgView" name="license" size="45"
					accept="image/gif, image/jpeg, image/png"> 
					<img src=DBGifReader4.do?photo_type=license&member_id=<%=membervo.getMember_id()%> id="preview_progressbar" width=100px height=100px; /></td>

			</tr>


			




		</table>
		<br> <input type="hidden" name="action" value="updateToChef">
		 <input
			type="hidden" name="member_id" value="<%=membervo.getMember_id()%>">
			
				 <input
			type="hidden" name="member_name" value="<%=membervo.getMember_name()%>">
				 <input
			type="hidden" name="account" value="<%=membervo.getAccount()%>">
			
              <input type="submit" value="送出申請" id="send" name="send">

<input
			type="hidden" name="chiefapply_status" value="<%=membervo.getChiefapply_status()%>">


	</FORM>
	

	
	
	
	

	<script>
		$("#imgView").change(function() {

			readURL(this);
		});

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#preview_progressbar")
							.attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			
			}
			return;
     	}
		

		</script>


</body>



</html>