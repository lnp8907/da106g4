<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@page import="com.member.model.*" %>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
     MemberVO membervo = (MemberVO) request.getAttribute("membervo"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
     
%>

<html>
<head>
<title>�d�߳�@�|��</title>

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
		 <h3>�|����� - ListOneMember.jsp</h3>
		 <h4><a href="MemberPage.jsp"><img src="images/getout.jpg" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|���s��</th>
			<th>�|���m�W</th>
			<th>�b��</th>
			<th>�q��</th>
			<th>email</th>
			<th>�a�}</th>
			<th>�ͤ�</th>
			<th>�I�ƾl�B</th>
			<th>�|���Y�K</th>
			<th>�|������</th>
			<th>�|�����A</th>
			<th>�ҷ�</th>
			<th>�ҷӼf�X���A</th>
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
<%-- 		<td><%="�Ϥ�"%></td> --%>
<td><img src=DBGifReader4.do?photo_type=mempic&member_id=${membervo.member_id} width=50 height=50></td>
		<td><%=membervo.getMember_status()%></td>
		<td><%=membervo.getValidation()%></td>
<%-- 			<td><%="�Ϥ�"%></td> --%>
<td><img src=DBGifReader4.do?photo_type=license&member_id=${membervo.member_id} width=50 height=50></td>
		<td><%=membervo.getChiefapply_status()%></td>

	</tr>
</table>

</body>
</html>