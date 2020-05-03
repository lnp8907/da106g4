<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@page import="com.member.model.*" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
     MemberVO membervo = (MemberVO) request.getAttribute("membervo"); //EmpServlet.java(Concroller), 存入req的empVO物件
     
%>

<html>
<head>
<title>查詢單一會員</title>

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
	width: 600px;
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
	<tr><td>
		 <h3>會員資料 - ListOneMember.jsp</h3>
		 <h4><a href="MemberPage.jsp"><img src="images/getout.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
			<th>會員姓名</th>
			<th>帳號</th>
			<th>電話</th>
			<th>email</th>
			<th>地址</th>
			<th>生日</th>
			<th>點數餘額</th>
			<th>會員頭貼</th>
			<th>會員類型</th>
			<th>會員狀態</th>
			<th>證照</th>
			<th>證照審合狀態</th>
	</tr>
	<tr>
		
		<td><%=membervo.getMember_id()%></td>
		<td><%=membervo.getMember_name()%></td>
		<td><%=membervo.getAccount()%></td>
		<td><%=membervo.getCellphone()%></td>
		<td><%=membervo.getEmail()%></td>
		<td><%=membervo.getMember_address()%></td>
			<td><%=membervo.getBirthday()%></td>
		<td><%=membervo.getBalance()%></td>
<%-- 		<td><%="圖片"%></td> --%>
<td><img src=DBGifReader4.do?photo_type=mempic&member_id=${membervo.member_id} width=50 height=50></td>
		<td><%=membervo.getMember_status()%></td>
		<td><%=membervo.getValidation()%></td>
<%-- 			<td><%="圖片"%></td> --%>
<td><img src=DBGifReader4.do?photo_type=license&member_id=${membervo.member_id} width=50 height=50></td>
		<td><%=membervo.getChiefapply_status()%></td>

	</tr>
</table>

</body>
</html>