<%@page import="com.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.mycourse.model.*"%>
<%
	MyCourseService myCourseService = new MyCourseService();
	MemberService memberService = new MemberService();
 	List<MycourseVO> list = (List<MycourseVO>) request.getAttribute("getOne_ForMycourse");
 	String course_id = (String) request.getAttribute("course_id");//讓page1可以動態取的course_id;
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("memberService", memberService);
	pageContext.setAttribute("status", new String[] { "報名中", "課程結束", "申請取消報名","課程被取消","取消報名"});

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單筆課程管理資訊</title>
</head>
<body>
	<b>&nbsp;位會員報名</b>
	<table class="table">
		<thead>
			<tr class="row100 head">
				<th class="cell100 column4">課程編號</th>
				<th class="cell100 column4">會員編號</th>
				<th class="cell100 column4">會員名稱</th>
				<th class="cell100 column4">報名時間</th>
				<th class="cell100 column4">交費金額</th>
				<th class="cell100 column4">報名狀態</th>
				<th class="cell100 column4">更改狀態</th>
			</tr>
		</thead>
		<tbody>
		<form method="post" action="MycourseServlet" name="changeStatus" id="changeStatus">
			<c:forEach var="MycourseVO" items="${list}" >
				<tr class="row100 body">
					<td class="cell100 column4">${MycourseVO.course_id}</td>
					<td class="cell100 column4">${MycourseVO.member_id}</td>
					<td class="cell100 column4">${memberService.getOneMember(MycourseVO.member_id).member_name}</td>
					<td class="cell100 column4"><fmt:formatDate
									value="${MycourseVO.create_time}" pattern="yyyy/MM/dd HH:mm" /></td>
					<td class="cell100 column4">${MycourseVO.pay_price}</td>
					<td class="cell100 column4">${status[MycourseVO.app_status]}</td>
					<td class="cell100 column4">
							<select name="course_status">
								<option value=" ">請選擇</option>
								<option value="1">${status[1]}</option>
								<option value="4">${status[4]}</option>
								<option value="0">${status[0]}</option>
							</select> 
							<input type="hidden" name="oldStatus" value="${MycourseVO.app_status}">
							<input type="hidden" name="course_id" value="${MycourseVO.course_id}"> 
							<input type="hidden" name="member_id" value="${MycourseVO.member_id}">
							<input type="hidden" name="action" value="changeStatus">
						
						</td>
					</tr>
				</c:forEach>
				</form>		
			
		</tbody>
				
	</table>
</body>
</html>