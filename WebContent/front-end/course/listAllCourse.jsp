<%@page import="com.mycourse.model.MyCourseService"%>
<%@page import="com.course.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="courseService" class="com.course.model.CourseService"/>
<jsp:useBean id="myCourseService" class="com.mycourse.model.MyCourseService"/>
<%
	List<CourseVO> list = courseService.getForFront();
	List<CourseVO> listTopSix = courseService.getTopSix();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("listTopSix", listTopSix);
%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>所有課程</title>
<!-- <link rel="stylesheet" href="../../css/frontEnd.css"> -->
<!-- <link rel="stylesheet" href="../../slick/slick.css"> -->
<!-- <link rel="stylesheet" href="../../slick/slick-theme.css"> -->
<link rel="stylesheet" href="css/courseCSS.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<!-- <script src="./../../slick/slick.js" type="text/javascript" charset="utf-8"></script> -->
<style>
* {
	font-family: 'Noto Sans TC', sans-serif;
	font-weight: 400;
}
.course-over-hint{
    position: absolute;
    background-color: red;
    color: white;
    display: inline-block;
    width: 200px;
    text-align: center;
    height: 40px;
    line-height: 40px;
    transform: rotate(45deg);
    right: -50px;
    top: 30px;
    font-size: 18;
}
</style>
</head>

<body>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<section id="course">
		<!--課程專區-->
		<div class="article-container">
			<div data-aos="fade-up" class="course-title">
				<h2 data-aos="fade-up " class="course-title-h2">課程專區</h2>
				<span class="ariticle-section-cption">Cooking Course</span>
			</div>
			<div
				class="article-section-description article-section-description-course">
				<!--課程陳列-->
				<div id="course-list-div">
					<ul class="course-list-ul">
						<div
							style="width: 250px; margin-left: 70%; margin-bottom: 10px; text-align: center; color: #BBB"><%@ include
								file="coursePage1.file"%></div>
						<c:forEach var="courseVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<li class="course-list-li"><a href=>
									<div class="course-li-img-div" style="position: relative;">					
										<span class="course-over-hint" style="display:${courseService.isCourseOver(courseVO.course_id)?'':'none'}">課程已經結束</span>
										<a href="course.do?action=getOne_For_Display&course_id=${courseVO.course_id}"><img class="course-li-img" id="course-li-title-1"
											src="photo?course_id=${courseVO.course_id}"
											alt="${courseVO.course_name}的課程圖片">></a>
									</div> <span class="course-li-title" id="course-li-title-1">${courseVO.course_name}</span>
									<div class="course-list-date-left">
										<span class="course-list-date-span">開課時間:</span> <span
											class="course-date-li" id="course-date-li-1"><fmt:formatDate
												value="${courseVO.course_start}" pattern="yyyy/MM/dd HH:mm" /></span>
									</div> <span class="course-list-date-span">報名截止:</span> <span
									class="course-date-li" id="course-date-li-1">${courseVO.end_app}</span>
									<div class="course-list-date-left">
										<span class="course-list-date-span">報名人數:</span> <span
											class="course-date-li" id="course-date-li-1">${myCourseService.appliedNum(courseVO.course_id)}/${courseVO.num_max}</span>
									</div> <span class="course-price"> <span
										class="course-list-date-span">課程價格:</span> <span
										class="course-date-li" id="course-date-li-1">NT${courseVO.course_price}$</span>
								</span>
							</a></li>
						</c:forEach>
					</ul>
					<div style="width: 300px; margin: 20px auto 0; text-align: center"><%@ include
							file="coursePage2.file"%></div>
					<!--end of 課程規劃-->
				</div>
				<!-- end of Course -->
				<!--課程橫幅陳列-->
				<div class="course-sider-list">
					<c:forEach var="courseVO" items="${listTopSix}">
						<div class="course-sider-list-viewer">

							<div class="course-li-img-div" id="course-sider-list-link-1">
								<img class="course-li-img" id="course-li-title-1"
									src="photo?course_id=${courseVO.course_id}"
									alt="${courseVO.course_name}的課程圖片">
							</div>
							<a href=""> <span class="course-li-title"
								id="course-li-title-1">${courseVO.course_name}</span> <span
								class="course-list-date-span">開課時間</span> <span
								class="course-date-li" id="course-date-li-2"><fmt:formatDate
										value="${courseVO.course_start}" pattern="yyyy/MM/dd HH:mm" />
							</span>
							</a>
						</div>

					</c:forEach>

				</div>
			</div>
		</div>
	</section>
	<!-- end of course-->
<script src="slick/slick.js" type="text/javascript" charset="utf-8"></script>
	<script>
		$(document).ready(function() {
			$('.course-sider-list').slick({
				dots : true,
				centerMode : true,
				centerPadding : '60px',
				slidesToShow : 3,
				responsive : [ {
					breakpoint : 768,
					settings : {
						arrows : false,
						centerMode : true,
						centerPadding : '40px',
						slidesToShow : 3
					}
				}, {
					breakpoint : 480,
					settings : {
						arrows : false,
						centerMode : true,
						centerPadding : '40px',
						slidesToShow : 1
					}
				} ]
			});
		});
	</script>
</body>

</html>