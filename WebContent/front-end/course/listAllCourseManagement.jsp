<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
String member_id =(String) session.getAttribute("member_id");
	CourseService courseService = new CourseService();
	List<CourseVO> list = courseService.getChefCourse(member_id);
// List<CourseVO> list = courseService.getAll();
	pageContext.setAttribute("list", list);

%>

<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>所有食譜清單</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="courseCSS/courseList.css">
<style>
.page2 a{
margin:0 3px;
}

</style>
</head>

<body>
	<h1>
		<a href="selectCourse_page.jsp">回首頁</a>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</h1>
	<div class="recipe-main-list">
		<div class="recipe-main-list-header">
			<h3>所有食譜清單</h3>
			<span><%@ include file="page1.file"%></span>
		</div>
		<ul class="course=list">
			<c:forEach var="CourseVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<li class="recipe-item"><img
					src="<%=request.getContextPath()+"/front-end/course/photo?course_id="%>${CourseVO.course_id}"
					alt="">
					<div class="recipe-item-caption">
						<div class="recipe-item-caption-header">
							<h4 class="recipe-item-tile">
								<a class="show-one-link"
									href="<%=request.getContextPath()%>/front-end/course/CourseServlet?action=getOne_For_Display&course_id=${CourseVO.course_id}">${CourseVO.course_name}</a>
							</h4>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/course/CourseServlet"
								style="margin-bottom: 0px;">
								<button class="update" value="修改">
									<img src="../../image/icon/update.png" title="修改" alt="修改">
								</button>
								<input type="hidden" name="course_id"
									value="${CourseVO.course_id}"> <input type="hidden"
									name="action" value="getOne_For_Update">
							</FORM>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/course/CourseServlet"
								style="margin-bottom: 0px;">
								<button class="delete" value="刪除">
									<img src="../../image/icon/delete.png" title="刪除" alt="刪除">
								</button>
								<input type="hidden" name="course_id"
									value="${CourseVO.course_id}"> <input type="hidden"
									name="action" value="delete">
							</FORM>
						</div>
						<p class="recipe-create-time">開課時間:<fmt:formatDate
								value="${CourseVO.course_start}" pattern="yyyy/MM/dd HH:mm" /></p>
						<p class="recipe-item-ingredient">課程價格：${CourseVO.course_price}</p>
					</div></li>
			</c:forEach>
		</ul>
		<div class="include-page2">
			<%@ include file="page2.file"%>
		</div>
	</div>
	<script>
		$('.delete').click(function() {
			if (window.confirm('你確定要刪除嗎?')) {
				return;
			} else {
				//關閉預設行為
				event.preventDefault();
			}

		});
	</script>
</body>

</html>