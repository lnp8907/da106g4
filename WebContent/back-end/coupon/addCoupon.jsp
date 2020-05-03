<%@page import="com.coupon.model.CouponVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
	String[] product_id = (String[]) request.getAttribute("product_id");
%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>課程新增</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/addCourseCSS.css">
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "微軟正黑體", Georgia, 'Times New Roman', Times, serif;
}
</style>
</head>

<body>
	<div id="insert-recipe-container">
		<form action="coupon.do" method="post"
			enctype="multipart/form-data" class="insert-recipe-main">
			<div class="insert-recipe-main-form">
				<h2 class="recipe-title">
					<textarea name="c_name" rows="1" placeholder="請填寫優惠券名稱"><%=(couponVO == null) ? "" : couponVO.getC_name()%></textarea>
				</h2>
				<div class="recipe-aside">
							<figure class="recipe-aside-image">
						<img src="../../image/icon/uploadPic.png" id="uploadFile">
						<input id="imageFile" type="file" name="c_picture">
					</figure>
					<div class="recipe-style">
						<h3>折數</h3><input type="text" name="discount"
								class="cook-time-option course_price"
								value="<%=(couponVO == null) ? "" : couponVO.getDiscount()%>">
			
					</div>
				</div>
				<!-- end of recipe-aside -->
				<div class="edit-recipe-aside">
					<div class="recipe-info">
						<div class="cook-time">
							<h3>優惠券開始時間</h3>
							<input type="text" id="f_date2" name="start_date"
								class="cook-time-option">
						</div>
						<div class="cook-time">
							<h3>優惠券結束時間</h3>
							<input type="text" name="end_date" class="cook-time-option"
								id="f_date1">
						</div>
						
						<div class="cook-time">
							<h3>優惠券代碼</h3>
							<input type="text" name="coupon_code"
								class="cook-time-option course_price"
								value="<%=(couponVO == null) ? "" : couponVO.getCoupon_code()%>">
						</div>
					</div>
					<!-- end of recipe-info-->
				</div>
				<!-- end of edit-recipe-aside -->
				<div class="reccipe-step">
					<h3>適用食譜</h3>
					<div class="course-container">
						<div class="ingredient-group" id="course-recipe-group">
							<div class="ingredient-group-row" style="display: none;"></div>
							<%
								for (int i = 0; i < ((couponVO == null||product_id==null) ? 3 : product_id.length); i++) {
							%>
							<div class="ingredient-group-row">
								<input type="text" class="recipe-ingredient-row-name"
									name="product_id" placeholder="輸入商品編號"
									value="<%=couponVO == null ? "" : product_id[i]%>"><span
									class=" recipe-ingredient-delete"></span>
							</div>								
					<%
								}
							%>
						</div>
						<span class="course-recipe-add">加入食譜</span>
					</div> 
				</div>
			</div>
			<!-- end of insert-recipe-main-form-->
			<div class="navi">
				<button class="navi-button" id="insert" type="submit" name="action"
					value="insert">新增</button>
				<button class="navi-button" id="reset" type="reset">清空</button>
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
		$.getScript("js/addCourseJS.js");
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
var date;
$('#f_date2').change(function(){
	date=$(this).val();
	$('#f_date1').val(date);
});	



<%java.sql.Date end_app = null;
			end_app = (couponVO == null || couponVO.getEnd_date() == null)
					? new java.sql.Date(System.currentTimeMillis())
					: couponVO.getEnd_date();

			java.sql.Date course_start = null;
			try {
				course_start = couponVO.getStart_date();
			} catch (Exception e) {
				course_start = new java.sql.Date(System.currentTimeMillis());
			}%>	
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : date,
<%-- 		value : //<%=end_app%>, --%>
	//disabledDate:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	minDate:'+1970-01-07', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	$.datetimepicker.setLocale('zh');
	$('#f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
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