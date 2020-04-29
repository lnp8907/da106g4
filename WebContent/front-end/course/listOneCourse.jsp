<%@page import="com.mycourse.model.MyCourseService"%>
<%@page import="com.member.model.*"%>
<%@page import="com.recipe.model.RecipeService"%>
<%@page import="com.recipe_style.model.RecipeStyleService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.course.model.CourseVO"%>

<%
	RecipeService recipeService = new RecipeService();
	MemberService memberService = new MemberService();
	MyCourseService myCourseService = new MyCourseService();
	CourseVO courseVO = (CourseVO) request.getAttribute("courseVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	MemberVO memberVO = memberService.getOneMember(courseVO.getMember_id());
	boolean isFull = myCourseService.isFull(courseVO.getNum_max(), courseVO.getCourse_id());
	boolean isApplied = myCourseService.isApplied(courseVO.getCourse_id(),"810009");
	String[] course_detail1; 
	String[] course_detail2;
	String[] token = courseVO.getCourse_detail().split("-");
	course_detail1 = token[0].split("/");
	course_detail2 = token[1].split("/");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>課程資訊</title>
<link rel="stylesheet" href="/DA106_G4_Foodporn_Git/front-end/course/courseCSS/listOneCourseCSS.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<style>
.chef-info, .racipe-detal-button, .racipe-detal-main-data {
	display: inline-block;
	width: 100%;
	padding: 4px;
	margin-bottom: 10px;
	color: #778;
}

.chef-info-pic {
	width: 50px;
	height: 50px;
	overflow: hidden;
	border-radius: 25px;
	display: inline-block;
}

.chef-info-pic img {
	width: 100%;
	height: 100%;
}

.chef-info-detal {
	display: inline-block;
	width: 155px;
	vertical-align: top;
}

.chef-info-detal span {
	width: 45%;
	margin-right: 15px;
	font-size: 12px;
	color: #AAA;
}

.chef-info {
	border-bottom: 3px solid #EEE;
}

.chef-info form {
	display: block;
	margin: 2px 0;
	text-align: right;
	margin-bottom: 5px;
}

.chef-info button {
	width: 35%;
	height: 35px;
	border-radius: 5px;
	margin-right: 38px;
	color: #AAA;
	border: solid 2px #ACF;
	color: #ACF;
}

.chef-info button:hover {
	cursor: pointer;
	background-color: #EDFDFF;
}

#applyFor, #cancel {
	width: 100px;
	height: 48px;
	font-size: 16px;
	font-weight: 600;
	background-color: #FF5757;
	color: white;
	border: 2px solid #FF5757;
	border-radius: 10px;
	box-shadow: 1px 1px 2px 2px #AAA;
	display: inline-block;
	margin-right: 25px;
	margin-top: 30px;
}
#applyFor:hover, #cancel:hover{
cursor:pointer;
}

.racipe-detal-main-data-ul {
	width: 250px;
	margin-top: 30px;
}

.chef-info {
	width: 250px;
}

.racipe-detal-main-data {
	width: 280px;
	vertical-align: top;
	margin-left: 30px;
}

.racipe-main {
	width: 1080px;
}

#applyFor-div {
	width: 100%;
}

.recipe-photo {
	width: 680px;
	height: 425px;
}

.racipe-header h2 {
	font-size: 36px;
}
.r-detal-info-list-container {
    width: 47%;
    }
</style>
</head>

<body>
	<div class="racipe-main">
		<div class="racipe-header">
			<h2 class="course-header-h2"><%=courseVO.getCourse_name()%></h2>
			<div class="racipe-header-aside"></div>
		</div>
		<div class="racipe-detal">
			<div class="recipe-photo">
				<img src="<%="photo?course_id=" + courseVO.getCourse_id()%>" alt="">
			</div>
			<div class="racipe-detal-main-data">
				<div class="chef-info">
					<div class="chef-info-pic">
						<img
							src="<%=request.getContextPath()%>/front-end/member/photo?member_id=<%=courseVO.getMember_id()%>"
							alt="廚師頭貼">
					</div>
					<div class="chef-info-detal">
						<h4>
							<a
								href="<%=request.getContextPath()%>/front-end/recipe/RecipeServlet?action=getChef_For_Display&member_id=<%=courseVO.getMember_id()%>"><%=memberVO.getMember_name()%></a>
						</h4>
						<span><%=recipeService.getChefCookedNum(courseVO.getMember_id())%>&nbsp;&nbsp;食譜</span>
						<span>999&nbsp;&nbsp;粉絲</span>
					</div>
					<form method="post" action="RecipeServlet">
						<button class="chef-follow" name="chef_follow">追蹤</button>
						<input type="hidden" value="<%=courseVO.getMember_id()%>"
							name="chef_id"> <input type="hidden" value="member_id"
							name="member_id">
					</form>
				</div>
				<ul class="racipe-detal-main-data-ul">
					<li>食譜類型<span class="racipe-detal-main-data-span"><%=courseVO.getCourse_type()%></span></li>
					<li>開課時間<span class="racipe-detal-main-data-span"><fmt:formatDate
								value="${courseVO.course_start}" pattern="yyyy/MM/dd HH:mm" /></span></li>
					<li>課程價格<span class="racipe-detal-main-data-span"><%=courseVO.getCourse_price()%>&nbsp;復胖幣</span></li>
					<li>報名人數<span class="racipe-detal-main-data-span"><span
							class="appliedNum"><%=myCourseService.appliedNum(courseVO.getCourse_id())%></span>/<%=courseVO.getNum_max()%></span></li>
					<li>報名截止時間<span class="racipe-detal-main-data-span"><%=courseVO.getEnd_app()%></span></li>
				</ul>
				<div id="applyFor-div">
					<!-- 先寫死到時候再改 -->
					<!-- 先寫死到時候再改 -->
					<!-- 先寫死到時候再改 -->
					<!-- 先寫死到時候再改 -->
					<!-- 					<input type="hidden" class="member-id" name="member_id"	value="810009">  -->
					<%-- 					<input type="hidden" class="course_id"name="course_id" value="<%=courseVO.getCourse_id()%>"> --%>
					<!-- 					<input type="hidden" class="action" name="action"value="insert">  -->
					<%-- 					<input type="hidden" class="pay_price" name="pay_price"	value="<%=courseVO.getCourse_price()%>">  --%>
					<button type="submit" id="applyFor" <%=isFull ? "disabled" : ""%> style="display:<%=isApplied? "none" : ""%>" ><%=isFull ? "人數已滿" : "立即報名"%></button>
					<button type="submit" id="cancel"  style="display:<%=isApplied? "" : "none"%>">取消報名</button>
				</div>
			</div>
		</div>
		<div class="racipe-detal-info-list">
			<div class="racipe-detal-description">
				<h2>課程內容</h2>
			</div>
			<div class="r-detal-info-list-container">
				<div class="r-detal-info-list-hd">
					<h3>學習菜單</h3>
				</div>
				<div
					class="r-detal-info-list-descript r-detal-info-list-descript-left">
					<ul>
						<%
							for (int i = 0; i < course_detail1.length; i++) {
						%>
						<li><%=course_detail1[i]%></li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<div
				class="r-detal-info-list-container r-detal-info-list-container-right">
				<div class="r-detal-info-list-hd">
					<h3>學習重點</h3>
				</div>
				<div class="r-detal-info-list-descript">
					<ul>
						<%
							for (int i = 0; i < course_detail2.length; i++) {
						%>
						<li><%=course_detail2[i]%></li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
<script>
	//練習使用AJAX實現按讚功能
	$(document).ready(function() {
		$("#applyFor").click(function() {
			// 		 debugger; debug用
			$.ajax({
				type : "POST",
				url : "ajaxResponse.do",
				data : {"action" : "insert","course_id":<%=courseVO.getCourse_id()%>,"member_id" : "810009","pay_price":"<%=courseVO.getCourse_price()%>"
				},
				dataType : "json",
				success : function(data) {
					alert(data.message);
					$(".appliedNum").text(data.appliedNum);
					if(data.message='報名成功'){
						$("#applyFor").hide();
						$("#cancel").show();
						 
					}
				},
				error : function(data) {
					alert('Ajax失敗');
				}
			})
		})
		$("#cancel").click(function() {
			// 		 debugger; debug用
			$.ajax({
				type : "POST",
				url : "ajaxResponse.do",
				data : {"action" : "cancel","course_id":<%=courseVO.getCourse_id()%>,"member_id" : "810009"
				},
				dataType : "json",
				success : function(data) {
					$(".appliedNum").text(data.appliedNum);
					if(data.message==='取消報名申請成功!'){
					alert(data.message);
					}
				},
				error : function(data) {
					alert('系統連線異常');
				}
			})
		})
	})
</script>

</html>