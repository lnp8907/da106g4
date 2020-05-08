<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.course.model.CourseVO"%>
<%
	CourseVO courseVO = (CourseVO) request.getAttribute("courseVO");
	String[] course_detail1 = (String[]) request.getAttribute("course_detail1");
	String[] course_detail2 = (String[]) request.getAttribute("course_detail2");
%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>課程新增</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" href="courseCSS/addCourseCSS.css">
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "微軟正黑體", Georgia, 'Times New Roman', Times, serif;
	font-size: 14px;
}
</style>
</head>

<body>
	<div id="insert-recipe-container">
		<form action="CourseServlet" method="post"
			enctype="multipart/form-data" class="insert-recipe-main">
			<div class="insert-recipe-main-form">
				<h2 class="recipe-title">
					<textarea name="course_name" rows="1" placeholder="請填寫課程名稱"><%=(courseVO == null) ? "" : courseVO.getCourse_name()%></textarea>
				</h2>
				<div class="recipe-aside">
					<div class="member_id-container">
						<h3>廚師編號</h3>
						<input type="text" name="member_id" placeholder="請輸入廚師編號"
							class="cook-time-option"
							value="<%=(courseVO == null) ? "" : courseVO.getMember_id()%>">
					</div>
					<figure class="recipe-aside-image">
						<img src="../../image/icon/uploadPic.png" id="uploadFile">
						<input id="imageFile" type="file" name="course_photo">
					</figure>
					<div class="recipe-style">
						<h3>食譜風格</h3>
						<select name="course_type">
							<option value="">請選擇</option>
							<%
								String[] type = {"請選擇", "中式", "台式", "日式","義式", "法式", "韓式", "泰式", "美式", "其他"};
								for (int i = 0; i < type.length; i++) {
									if (((courseVO == null) ? "請選擇" : courseVO.getCourse_type()).equals(type[i])) {
							%>
							<option value=<%=type[i]%> selected><%=type[i]%></option>
							<%
								} else {
							%>
							<option value=<%=type[i]%>><%=type[i]%></option>
							<%
								}
								}
							%>
						</select>
					</div>
				</div>
				<!-- end of recipe-aside -->
				<div class="edit-recipe-aside">
					<div class="recipe-info">
						<div class="cook-time">
							<h3>開課時間</h3>
							<input type="text" id="f_date2" name="course_start"
								class="cook-time-option">
						</div>
						<div class="cook-time">
							<h3>報名截止日</h3>
							<input type="text" name="end_app" class="cook-time-option"
								id="f_date1">
						</div>
						<div class="cook-time">
							<h3>課程最大人數</h3>
							<select name="num_max" class="cook-time-option">
								<option value="0">未設定</option>
								<%
									Integer[] time = {5, 10, 15, 20, 25, 30};
									for (int i = 0; i < time.length; i++) {
										if (((courseVO == null) ? 0 : courseVO.getNum_max()) == time[i]) {
								%>
								<option value=<%=time[i]%> selected><%=time[i]%></option>

								<%
									} else {
								%>
								<option value=<%=time[i]%>><%=time[i]%></option>
								<%
									}
									}
								%>
							</select>
						</div>
						<div class="cook-time">
							<h3>課程價格</h3>
							<input type="text" name="course_price"
								class="cook-time-option course_price"
								value="<%=(courseVO == null) ? "" : courseVO.getCourse_price()%>">
						</div>
						<div class="cook-time">
							<h3>上課地點</h3>
							<input type="text" name="course_loca" class="cook-time-option"
								value="<%=(courseVO == null) ? "" : courseVO.getCourse_loca()%>">
						</div>
					</div>
					<!-- end of recipe-info-->
				</div>
				<!-- end of edit-recipe-aside -->
				<div class="reccipe-step">
					<h3>課程內容</h3>
					<div class="course-container">
						<h4>學習菜單</h4>
						<div class="ingredient-group" id="course-recipe-group">
							<div class="ingredient-group-row" style="display: none;"></div>
							<%
								for (int i = 0; i < ((courseVO == null||course_detail1==null) ? 3 : course_detail1.length); i++) {
							%>
							<div class="ingredient-group-row">
								<input type="text" class="recipe-ingredient-row-name"
									name="course_detail1" placeholder="輸入課程料理"
									value="<%=courseVO == null ? "" : course_detail1[i]%>"><span
									class=" recipe-ingredient-delete"></span>
							</div>
							
						
						<%
								}
							%>
						</div>
						<span class="course-recipe-add">加入學習菜單</span>
					</div> 
					<div class="course-container">
						<h4>學習重點</h4>
						<div class="ingredient-group" id="course-point-group">
							<div class="ingredient-group-row" style="display: none;">
							</div>
							<%
								for (int i = 0; i < ((courseVO == null||course_detail2==null) ? 3 : course_detail2.length); i++) {
							%>
							<div class="ingredient-group-row course-point-row">
								<textarea class="recipe-ingredient-row-name"
									name="course_detail2" placeholder="輸入課程重點..."><%=courseVO == null ? "" : course_detail2[i]%></textarea>
								<span class="recipe-ingredient-delete"></span>
							</div>
						
						
							<%
								}
							%>
						</div>
						<span class="recipe-point-add">加入學習重點</span>
					</div>
				</div>
			</div>
			<!-- end of insert-recipe-main-form-->
			<div class="navi">
				<button class="navi-button" id="insert" type="submit" name="action"
					value="insert">新增</button>
				<button class="navi-button" id="reset" type="reset">清空</button>
				<a href="listAllCourseManagement.jsp"><button class="navi-button" id="cancel" type="button">取消</button></a>
			</div>
			<div class="errorMess">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</form>
	</div>
	<!-- end of insert-recipe-container-->
	<script>
		$.getScript("courseCSS/addCourseJS.js");
	</script>
</body>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
<script>


<%java.sql.Date end_app = null;
			end_app = (courseVO == null || courseVO.getEnd_app() == null)
					? new java.sql.Date(System.currentTimeMillis())
					: courseVO.getEnd_app();

			java.sql.Timestamp course_start = null;
			try {
				course_start = courseVO.getCourse_start();
			} catch (Exception e) {
				course_start = new java.sql.Timestamp(System.currentTimeMillis());
			}%>
			
			
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : <%=end_app%>,
<%-- 		value : //<%=end_app%>, --%>
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	minDate:'+1970-01-07', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	
	
	$.datetimepicker.setLocale('zh');
	$('#f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker:true,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
		value : '<%=course_start%>',
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	            '2017/07/10',  // 起始日
		minDate : '+1970-01-14', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});


	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>

</html>