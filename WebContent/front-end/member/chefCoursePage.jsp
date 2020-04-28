<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.member.model.*"%>
<%@page import="com.course.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String member_id = (String) request.getAttribute("member_id");
	CourseService courseService = new CourseService();
	List<CourseVO> list = courseService.getChefCourse(member_id);
	MemberService memberService = new MemberService();
	MemberVO memberVO = memberService.getOneMember(member_id);
	pageContext.setAttribute("list", list);
%>
		<span class="include-page"><%@ include file="page1.file"%><b>課程</b></span>
		<div class="main-page-card-container">
			<c:forEach var="courseVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<div class="main-page-card">
					<div class="main-page-card-pic">
						<img src="<%=request.getContextPath()%>/front-end/course/photo?course_id=${courseVO.course_id}">		
					</div>
					<div class="main-page-card-info">
						<div>
							<h4>
								<a class="show-one-link"
									href="<%=request.getContextPath()%>/CourseServlet?action=getOne_For_Display&course_id=${courseVO.course_id}">${courseVO.course_name}</a>
							</h4>
							<span>${courseVO.app_num}位報名</span>
						</div>
						<form action="">
							<input type="hidden" name="action" value="follow_recipe">
							<input type="hidden" name="member_id" value="member_id">
							<input type="hidden" name="recipe_id"
								value="$(RecipeVO.recipe_id)">
							<button>報名</button>
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- end of main-page-card-container-->
		<div class="include-page2">
			<%@ include file="page2.file"%>
		</div>
	<!-- end of main-page-->