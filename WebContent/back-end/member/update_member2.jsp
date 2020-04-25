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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js">

</script>





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
	<FORM METHOD="post" ACTION="member.do" name="upateform" id="upateform"
		enctype="multipart/form-data">



<table>
			<tr>
				<td>會員圖片:</td>
				<!-- 按鈕 -->
				<td><input type="file" id="imgView" name="member_photo"
					size="45" accept="image/gif, image/jpeg, image/png"> <img src=DBGifReader4.do?photo_type=mempic&member_id=<%=membervo.getMember_id()%> id="preview_progressbarTW_img" width=100px height=100px;/></td>

			</tr>


			<tr>
				<td>會員姓名:</td>
				<td><input type="TEXT" name="member_name" size="45"
					id=member_name value="<%=membervo.getMember_name()%>" /></td>
			</tr>

			<tr>
				<td>會員帳號:</td>
				<td><input type="TEXT" name="account" size="45" id=account
					value="<%=membervo.getAccount()%>" /></td>
			</tr>
			<tr>
				<td>會員密碼:</td>
				<td><input type="password" name="password" size="45" id=password
					value="<%=membervo.getPassword()%>" /></td>
					<td><label><input type="checkbox" id="show_password" size="45" />顯示密碼</label></td>
			</tr>



			<tr>
				<td>性別:</td>
				<td><input type="radio" name="gender" value=0 checked="<%=(membervo.getGender()==0)? "true": "false"%>"> 男<br>
					<input type="radio" name="gender" value=1 checked="<%=(membervo.getGender()==1)? "true": "false"%>"> 女<br></td>
			</tr>

			<tr>
				<td>電話:</td>
				<td><input type="TEXT" name="cellphone" size="45" id=cellphone
					value="<%=membervo.getCellphone()%>" /></td>
			</tr>


			<tr>
				<td>Email:</td>
				<td><input type="TEXT" name="email" size="45" id=email
					value="<%=membervo.getEmail()%>" /></td>
			</tr>










			<tr>
				<td>會員地址:</td>
				<td><input type="TEXT" name="member_address" size="45"
					id="address" value="<%=membervo.getMember_address()%>" /></td>
			</tr>
			<tr>
				<td>會員生日:</td>
				<td><input type="TEXT" name="birthday" size="45" id="birthday"
					 /></td>
			</tr>












			<!-- 	<tr> -->
			<!-- 		<td>驗證狀態:</td> -->
			<!-- 	<td>	 -->
			<!-- 		<input type="radio" name="member_status" value=0> 未驗證<br> -->
			<!--         <input type="radio" name="member_status" value=1> 已驗證<br> -->


			<!-- 	</td> -->
			<!-- 	</tr> -->




		</table>


<!-- 		<table> -->
		

<!-- 			<tr> -->
<!-- 				<td>會員編號:<font color=red><b>*</b></font></td> -->

<%-- 				<td><%=membervo.getMember_id()%></td> --%>

<!-- 			</tr> -->

<!-- 			<tr> -->
<!-- 				<td>會員姓名:</td> -->
<%-- 				<td><%=membervo.getMember_name()%></td> --%>
<!-- 			</tr> -->

<!-- 			<tr> -->
<!-- 				<td>會員帳號:</td> -->
<%-- 				<td><%=membervo.getAccount()%></td> --%>
<!-- 			</tr> -->
			
		


<!-- 			<tr> -->
<!-- 				<td>廚師證照:</td> -->
<!-- 				按鈕 -->
<!-- 				<td><input type="file" id="imgView" name="license" size="45" -->
<!-- 					accept="image/gif, image/jpeg, image/png">  -->
<%-- 					<img src=DBGifReader4.do?photo_type=license&member_id=<%=membervo.getMember_id()%> id="preview_progressbar" width=100px height=100px; /></td> --%>

<!-- 			</tr> -->


			




<!-- 		</table> -->
		<br> <input type="hidden" name="action" value="updateBySelf">
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
<%java.sql.Date birthday = null;
                birthday = (membervo == null || membervo.getBirthday() == null)
					? new java.sql.Date(System.currentTimeMillis())
					:membervo.getBirthday();
%>

 	$.datetimepicker.setLocale('zh');
	$('#birthday').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 60, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '<%=birthday%>',
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	startDate:	            '2000/01/01',  // 起始日
	//minDate:'-1970-01-07', // 去除今日(不含)之前
	maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});


	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
	
	
	

	<script>
		$("#imgView").change(function() {

			readURL(this);
		});

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#preview_progressbarTW_img")
							.attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			
			}
			return;
     	}
		

		</script>


</body>



</html>