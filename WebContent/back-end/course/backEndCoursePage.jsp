<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.mycourse.model.*"%>
<%@page import="com.course.model.*"%>
<%@page import="java.util.List"%>
<%
	CourseService courseService = new CourseService();
	Integer manageNum = courseService.getManageNum();
	MyCourseService myCourseService = new MyCourseService();
	Integer mycourseManageNum = myCourseService.getAllCheckNum();
	request.setAttribute("status", new String[] { "開課中", "課程結束", "取消開課申請", "取消開課", "開課申請" });
	String pageType = (String) request.getParameter("pageType");
	if (pageType == null) {
		pageType = "courseAllList.jsp";
	}
	request.setAttribute("pageType", pageType);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>課程管理</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/backEndCourse.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.modal-content{
width:1020px;
}
</style>
<body>
	<div class="container">
		<div class="box">
			<!--這裡是左邊選單-->
			<div class="left-bar">
				<img src="../../image/logo_nohead.png" alt="LOGO" width="200"
					height="200">
				<div class="menu-group">
				<a class="menu-group-a"
						href="<%=request.getContextPath() + "/backEnd.html"%>"><span style="font-size:30px;">回首頁</span></a>
					<a class="menu-group-a"
						href="<%=request.getContextPath() + "/back-end/course/backEndCoursePage.jsp?pageType=courseAllList.jsp"%>"><span>課程列表</span></a>
					<a class="menu-group-a"
						href="<%=request.getContextPath() + "/back-end/course/backEndCoursePage.jsp?pageType=courseCheckList.jsp"%>"
						<span>課程審核<span id="courseManageNum" style="display:<%=(manageNum==0)?"none":""%>" class="count"><%=(manageNum<10)?manageNum:"9+"%></span></span></a> 
						<a class="menu-group-a"
						href="<%=request.getContextPath() + "/back-end/course/backEndCoursePage.jsp?pageType=mycourseList.jsp"%>"
						<span>課程報名管理<span id="courseApplyMabageNum" style="display:<%=(mycourseManageNum==0)?"none":""%>" class="count"><%=(mycourseManageNum<10)?mycourseManageNum:"9+"%></span></span></a>
					<div class="building"></div>
				</div>
				<span class="fa fa-sign-out">登出</span>
			</div>
			<div class="wrapper">
				<div class="content">
					<jsp:include page="<%=pageType%>" />
				</div>
			</div>
		</div>
	</div>
</body>

</html>