<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.course.model.CourseVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	CourseVO courseVO = (CourseVO) request.getAttribute("courseVO");
	String from = (String)request.getAttribute("from");//得知該參數由哪個網頁傳遞近來
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單筆課程管理資訊</title>
</head>
<body>
	<table class="table">
		<thead>
			<tr class="row100 head">
				<th class="cell100 column4">課程編號</th>
				<th class="cell100 column4">廚師編號</th>
				<th class="cell100 column1">課程名稱</th>
				<th class="cell100 column2">開課日期</th>
				<th class="cell100 column2">課程狀態</th>
				<th class="cell100 column4">更改狀態</th>
			</tr>
		</thead>

		<tbody>
			<tr class="row100 body">
				<td class="cell100 column4">${courseVO.course_id}</td>
				<td class="cell100 column4">${courseVO.member_id}</td>
				<td class="cell100 column1">${courseVO.course_name}</td>
				<td class="cell100 column2"><fmt:formatDate
						value="${courseVO.course_start}" pattern="yyyy/MM/dd HH:mm" /></td>
				<td class="cell100 column4">${status[courseVO.course_status]}</td>
				<td class="cell100 column4">
					<form method="post" action="CourseServlet" name="changeStatus" id="changeStatus">
						<select name="course_status">
							<option value="">請選擇修改</option>
							<option value="0">成功開課</option>
							<option value="3">取消開課</option>
							<option value="1">結束課程</option>
						</select> 
						<input type="hidden" name="oldStatus" value="${courseVO.course_status}">
						<input type="hidden" name="from" value="${from}">
						<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
								<input type="hidden" name="course_id"
							value="${courseVO.course_id}"> <input type="hidden"
							name="action" value="changeStatus">
					</form>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>