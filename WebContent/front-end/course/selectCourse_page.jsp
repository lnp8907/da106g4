<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Course: Home</title>
<link rel="stylesheet" href="courseCSS/select_pageCSS.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<style>
.errorMsgs{
text-align:right;

}
</style>
</head>

<body>
	<div class="form">
		<fieldset>
			<div class="title">
				<h2>Foodporn Course: Home</h2>
				<p>This is the Home page for Foodporn Course: Home</p>
				<div class="errorMsgs">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul >
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red; list-style:none;">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				</div>
			</div>
			<h3>資料查詢</h3>
			<div>
				<label class="mid">查詢所有課程</label> <input type="button" value="查詢"
					onclick="location.href='listAllCourse.jsp'">
			</div>

			<div>
				<form method="post" action="CourseServlet">
					<label class=" mid">輸入課程編號(如210001):</label> <input type="text"
						name="course_id"> <input type="hidden" name="action"
						value="getOne_For_Display"> <input type="submit"
						value="確認">
				</form>
			</div>

			<jsp:useBean id="CourseService" scope="page"
				class="com.course.model.CourseService" />
			<div>
				<form method="post" action="CourseServlet">
					<label class="mid">選擇課程編號:</label> <select name="course_id"
						id="select-choice-2">
						<option value="">選擇課程編號</option>
						<c:forEach var="courseVo" items="${CourseService.all}">
							<option value="${courseVo.course_id}">${courseVo.course_id}</option>
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
				</form>
			</div>
			<div>
				<form method="post" action="CourseServlet">
					<label class="mid">選擇課程名稱:</label> <select name="course_id"
						id="select-choice-2">
						<option value="">選擇課程名稱</option>
						<c:forEach var="courseVo" items="${CourseService.all}">
							<option value="${courseVo.course_id}">${courseVo.course_name}</option>
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display" >
					<input type="submit" value="送出" style="margin-top:20px">
				</form>
			</div>
			<h3>課程管理</h3>
			<div>
				<label class="mid">新增課程</label> <input type="button" value="新增"
					onclick="location.href='addCourse.jsp'">
			</div>
						<div>
				<label class="mid">管理所有課程</label> <input type="button" value="管理"
					onclick="location.href='listAllCourseManagement.jsp'">
			</div>

		</fieldset>

	</div>

</body>
</html>