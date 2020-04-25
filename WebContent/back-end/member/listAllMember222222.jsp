<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%
	MemberService Psvc = new MemberService();
	List<MemberVO> list = Psvc.getAll();
	pageContext.setAttribute("list", list);
	
%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有會員</title>
<style>
.cell100 {
    text-align: center;
}
#update-info {
	border: 1px #E4002B solid;
	width: 50px;
	height: 40px;
	border-radius: 20px;
	background-color: #FF5757;
	color: #FFF;
	box-shadow: 2px 3px 3px #AAA
}
</style>





</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有會員</h3>
				<h4>
					<a href="MemberPage.jsp"><img src="images/back1.gif"
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
	<form METHOD="POST"  ACTION="member.do">
    <input type="submit" value="✚會員註冊">
    <input type="hidden" name="action" value="addMember">
    
	</form>
	<br>

	<table>
		<tr>
			<th>會員編號</th>
			<th>會員姓名</th>
			<th>帳號</th>
            <th>密碼</th>
            <th>性別</th>
			<th>電話</th>
			<th>email</th>
            <th>會員暱稱</th>
			<th>地址</th>
			<th>生日</th>
			<th>點數餘額</th>
            <th>信用卡</th>
			<th>會員頭貼</th>
			<th>會員類型</th>
			<th>會員狀態</th> 
			<th>證照</th>
			<th>證照審合狀態</th>

			<th>修改</th>
<!-- 			<th>刪除</th> -->
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="membervo" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${membervo.member_id}</td>
			    <td>${membervo.member_name}</td> 
				<td>${membervo.account}</td>
				<td>${membervo.password}</td>
				
				<%
					Map<Integer, String> map4 = new HashMap<>();
						map4.put(0, "男");
						map4.put(1, "女");
						
						request.setAttribute("gender", map4);
				%>
				<c:set var="gender1" value="${membervo.gender}" />
				<td>${ gender [gender1] }</td>
				
				<td>${membervo.cellphone}</td>
				<td>${membervo.email}</td>
				<td>${membervo.nickname}</td>
				<td>${membervo.member_address}</td>
				
				
		
				
				
				
	<td>${membervo.birthday}</td> 
				<td>${membervo.balance}</td>
				
				<td>${membervo.member_creditcard}</td>
				
				
				
				<td><img src=DBGifReader4.do?photo_type=mempic&member_id=${membervo.member_id} width=100 height=100></td>


				<%
					Map<Integer, String> map = new HashMap<>();
						map.put(0, "普通會員");
						map.put(1, "廚師");
						map.put(2, "已凍結");
						request.setAttribute("memberstatus", map);
				%>
				<c:set var="status" value="${membervo.member_status}" />
				<td>${ memberstatus [status] }</td>



				<% 
					Map<Integer, String> map1 = new HashMap<>();
 						map1.put(0, "未驗證");
						map1.put(1, "已驗證");
						request.setAttribute("validation", map1);
 			%>
 				<c:set var="validation1" value="${membervo.validation}" />
 				<td>${ validation [validation1] }</td>




				<td><img src=DBGifReader4.do?photo_type=license&member_id=${membervo.member_id} width=100 height=100></td>




<!--                    membervo.getMemStaus() == null; -->
				<%	
				
					Map<Integer, String> map2 = new HashMap<>();
				       
						 map2.put(0, "無須審核");
						 map2.put(1, "審核中"); 
						 map2.put(2, "審核通過");
						request.setAttribute("chiefapply_status", map2);
				%>
				<c:set var="chiefapply_status1" value="${membervo.chiefapply_status}" />
				<td>${ chiefapply_status [chiefapply_status1] }</td>









				<td>
					<!-- 修改 -->
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/member/member.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="member_id" value="${membervo.member_id}">
							<input type="hidden"
							name="password" value="${membervo.password}">
							<input type="hidden"
							name="member_name" value="${membervo.member_name}">
								<input type="hidden"
							name="member_address" value="${membervo.member_address}">
							
							<input type="hidden"
							name="account" value="${membervo.account}">
							
							<input type="hidden"
							name="account" value="${membervo.member_photo}">
							
							<input type="hidden"
							name="account" value="${membervo.license}">
							
								<input type="hidden"
							name="birthday" value="${membervo.birthday}">
								<input type="hidden"
							name="gender" value="${membervo.gender}">
								<input type="hidden"
							name="cellphone" value="${membervo.cellphone}">
								<input type="hidden"
							name="email" value="${membervo.email}">
								<input type="hidden"
							name="nickname" value="${membervo.nickname}">
								<input type="hidden"
							name="validation" value="${membervo.validation}">
								<input type="hidden"
							name="member_status" value="${membervo.member_status}">
						
							
							<input type="hidden"
							name="member_creditcard" value="${membervo.member_creditcard}">
							
									<input type="hidden"
							name="balance" value="${membervo.balance}">
									<input type="hidden"
							name="chiefapply_status" value="${membervo.chiefapply_status}">
							
							
							
							
							
							
						<!-- 更新 -->
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					刪除 -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/member/member.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 							name="member_id1" value="${membervo.member_id}"> <input --%>
<!-- 							type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>
		</c:forEach>
	</table>

	<%@ include file="page2.file"%>

</body>
</html>