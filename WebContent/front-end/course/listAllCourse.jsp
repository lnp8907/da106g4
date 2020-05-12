<%@page import="com.mycourse.model.MyCourseService"%>
<%@page import="com.course.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="courseService" class="com.course.model.CourseService" />
<jsp:useBean id="myCourseService"
	class="com.mycourse.model.MyCourseService" />
<%
	String member_id = (String) session.getAttribute("member_id");
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
<link rel="stylesheet" href="../../css/frontEnd.css">
<link rel="stylesheet" href="../../slick/slick.css">
<link rel="stylesheet" href="../../slick/slick-theme.css">
<link rel="stylesheet" href="courseCSS/courseCSS.css">
<link rel="stylesheet" href="../../css/CarMessage.css">
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

.course-over-hint {
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
	<header>
		<div id="top-logo" class="logo">
			<a href="<%=request.getContextPath() %>/index.jsp" title="回首頁"><img class="logo-photo"
				src="<%=request.getContextPath() %>/image/FoodPron_Logo.png" alt="logo"></a>
		</div>
		<div class="function">
			<div class="function-list">
				<a href="#"></a>
				<div class="member-center">
<<<<<<< HEAD
					<a href="<%=request.getContextPath() %>/front-end/member/TestMemberHomepage.jsp" id="member-center">
						 <span class="member-center-spann">會員中心</span>
=======
				<a href="<%=request.getContextPath() %>/front-end/member/TestMemberHomepage.jsp" id="member-center">
					<span class="member-center-spann">會員中心</span>
>>>>>>> branch 'master' of https://github.com/lnp8907/da106g4.git
					</a>
					
					
				</div>
				
			</div>
			<div class="function-list">
				<div class="menu">
					<input type="checkbox" href="#" class="menu-open menu-icon"
						name="menu-open" id="menu-open" /> <label
						class="menu-open-button" for="menu-open"> <span
						class="lines line-1"></span> <span class="lines line-2"></span> <span
						class="lines line-3"></span>
					</label>
				</div>
<<<<<<< HEAD
=======
				
				
				
				
				
				
				
		
				
>>>>>>> branch 'master' of https://github.com/lnp8907/da106g4.git
			<c:if test='${empty member_id}'>
			
				<div class="login">
					<a href="#"><img class="header-icon" src="image/user-icon.png"
						alt="login-icon">
						<div class="herder-icon-span">
							<span class="login-span">登入</span>
						</div> </a>
				</div>
			</c:if>	
			
			<c:if test='${not empty member_id}'>
	
					<div class="login">
				
					<a> <img class="header-icon" src="<%=request.getContextPath() %>/image/logout.png"
						alt="login-icon">
					
							<span class="login-span">登出</span>
							<form method="POST" action="member.do">	
								<input type="hidden" name="action" value="loginOUT">
								<input class="login-out" type="submit" name="action" style= "display:none;">
						</form>	 </a>
				</div>

				</c:if>	
				<script>
				$('.login-span').click(function(){
					$('.login-out').click();
				})
				
				
				
				
				</script>
<<<<<<< HEAD
=======
		
				
				
				
				
				
				<!-- 購物車 -->
>>>>>>> branch 'master' of https://github.com/lnp8907/da106g4.git
				<div class="shop-car">
					<a
						href="<%=request.getContextPath()%>/front-end/ShopPage/ProductPage?action=checktpage1">

						<div class="carmessage1">
							<img class="header-icon"
								src="<%=request.getContextPath()%>/image/shopping-cart-icon.png"
								alt="shopping-cart">


						</div>
						<div class="carmessage2" style="display: none">${fn:length(productCarlist)}</div>
						<div class="carmessagecircle" style="display: none">more</div>



						<div class="herder-icon-span">
							<span class="shop-car-span">購物車</span>

						</div>
					</a>
				</div>
								<!-- 購物車 -->
			<c:if test="${not empty member_id}">
				<!-- <div class="demo"> -->
				<div>
					<div id="launch" class="notice">
						<a href="#">
							<img class="header-icon" src="<%=request.getContextPath() %>/image/ico_notice.png" alt="notice-icon">
							<div class="herder-icon-span">
								<span class="notice-span">通知總覽</span>
							</div> 
						</a>
					</div>
					 <!-- <a  class="" href="#">Launch Popup</a> -->
				</div>
			</c:if>
			</div>

		</div>
		<!-- end of function-->
		<nav id="navigation">
			<ul>
				<li class="dropdown"><a href="<%=request.getContextPath()%>/front-end/recipe/recipeMainpage.jsp"><img class="access-menu-icon"
						src="<%=request.getContextPath() %>/image/recipe-icon.png"><span class="menu-span">食譜專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="<%=request.getContextPath()%>/front-end/recipe/recipeMainpage.jsp"><img
								class="dropdown-first-img" src="<%=request.getContextPath() %>/image/ico_gnav_recipes_book.svg"><span
								class="dropdown-first-a-span">食譜主頁</span></a></li>
<!-- 						<li><a href="#">特輯食譜</a></li> -->
<!-- 						<li><a href="#">推薦食譜</a></li> -->
					<c:if test="${memberVO.member_status==1}">
						<li><a href="<%=request.getContextPath() %>/front-end/recipe/addRecipe.jsp">建立食譜</a></li>
					</c:if>
	
					</ul></li>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="<%=request.getContextPath() %>/image/livestream-icon.png"><span class="menu-span">直播專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="<%=request.getContextPath()%>/front-end/livestream/livestreamHomePage.jsp"><img
								class="dropdown-first-img"
								src="<%=request.getContextPath() %>/image/ico_gnav_recipes_movie.svg"><span
								class="dropdown-first-a-span">直播主頁</span></a></li>
<!-- 						<li><a href="livestream.jsp">直播預告</a></li> -->
					<c:if test="${memberVO.member_status==1}">
						<li><a href="<%=request.getContextPath() %>/front-end/livestream/livestream.jsp#${member_id}">開啟直播</a></li>
						
						</c:if>
					</ul></li>
				<li class="dropdown dropdown-shop"><a href="<%=request.getContextPath()%>/front-end/ShopPage/ShopHomePage.jsp"><img
						class="access-menu-icon" src="<%=request.getContextPath() %>/image/shop-icon.png"><span
						class="menu-span">食材商城</span></a>
					<ul id="dropdown-shop-ul">
						<li><a class="dropdown-first-a" href="<%=request.getContextPath()%>/front-end/ShopPage/ShopHomePage.jsp"><img
								class="dropdown-first-img"
								src="<%=request.getContextPath() %>/image/ico_gnav_recipes_salad.svg"><span
								class="dropdown-first-a-span">食材商城主頁</span></a></li>
								<%
			Map<Integer, String> producttype = new HashMap<Integer, String>();
			
				producttype.put(0, "水果類");
				producttype.put(1, "肉類");
				producttype.put(2, "蔬菜類");
				producttype.put(3, "乳品類");
				producttype.put(4, "魚貝類");
				producttype.put(5, "菇類");
				producttype.put(6, "穀物類");
				producttype.put(7, "澱粉類");
				producttype.put(8, "酒類");
				producttype.put(9, "調味料及香辛料類");
				producttype.put(10, "油脂類");
				producttype.put(11, "所有商品");				
				request.setAttribute("producttype", producttype);

			%>							<c:forEach var="producttype" items="${producttype}">
									<li><a href="<%=request.getContextPath() %>/front-end/ShopPage/ProductPage?product_type=${producttype.value}&action=goProductPage">${producttype.value}</a></li>
			
										</c:forEach>
			

					</ul></li>
				<li class="dropdown"><a href="<%=request.getContextPath() %>/front-end/course/listAllCourse.jsp"><img class="access-menu-icon"
						src="<%=request.getContextPath() %>/image/course-icon.png"><span class="menu-span">料理課程</span></a>
					<ul>
						<li><a href="<%=request.getContextPath() %>/front-end/course/listAllCourse.jsp" class="dropdown-first-a" href="front-end/course/listAllCourse.jsp"><img
								class="dropdown-first-img" src="<%=request.getContextPath() %>/image/ico_gnav_recipes_pot.svg"><span
								class="dropdown-first-a-span">課程主頁</span></a></li>
<!-- 						<li><a href="#">熱門課程</a></li> -->
					<c:if test="${memberVO.member_status==1}">
						<li><a href="<%=request.getContextPath() %>/front-end/course/addCourse.jsp">建立料理課程</a></li>
						</c:if>
					</ul></li>
			</ul>
		</nav>
	</header>
	<!-- end of header-->	
<!-- 登箱開始 -->

<c:if test='${empty member_id}'>
	<div class="login-wrap">
		<div class="login-html">
			<img class="login-close" src="image/close.png" alt="close"> <input
				id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">Sign In</label> <input id="tab-2"
				type="radio" name="tab" class="sign-up"><label for="tab-2"
				class="tab">Sign Up</label>
			<div class="login-form">
<form method="POST" action="member.do">
				<div class="sign-in-htm">
					<div class="group">
						<label for="user" class="label">Account</label> <input name="mem_id" id="user"
							type="text" class="input">
					</div>
					<div class="group">
						<label for="pass" class="label">Password</label> <input name="psw"  id="pass"
							type="password" class="input" data-type="password">
					</div>
<input type="hidden" name="action" value="login">
					<div class="group">
						<input id="check" type="checkbox" class="check" checked> <label
							for="check"><span class="icon"></span> Keep me Signed in</label>
					</div>
					<div class="group SignIn">
						<input type="submit" class="button" value="Sign In">
					</div>
					<div class="hr"></div>
					<div class="foot-lnk">
						<a href="#forgot" class="foot-lnk-a">Forgot Password?</a>
					</div>
 
				</div>
</form>
<form method="POST" action="member.do">
				<div class="sign-up-htm">
					<div class="group">
						<label for="newuser" class="label">Account</label> <input
							id="newuser" type="text" name="account1" class="input">
					</div>
					<div class="group">
						<label for="newpass" class="label">Password</label> <input
							id="newpass" type="password" name="password" class="input" data-type="password">
					</div>
					<div class="group">
						<label for="renewpass" class="label">Repeat Password</label> <input
							id="renewpass" type="password" name="password2" class="input" data-type="password">
					</div>
					<div class="group">
						<label for="email" class="label">Email Address</label> <input
							id="email" type="email" name="email" class="input">
					</div>
<input type="hidden" name="action" value="insert">					
					<div class="group">
						<input type="submit" class="button" value="Sign Up">
					</div>
					<div class="hr"></div>
					<div class="foot-lnk">
						<label for="tab-1">Already Member?</label>
					</div>
				</div>
</form>				
			</div>
		</div>

	</div>
	
			<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</c:if>
	<!-- end of login-->
	
	
	

	
	<div class="pagetop">
		<img src="<%=request.getContextPath() %>/image/go-top-page.png" alt="BackTop" id="BackTop">
		<!--一鍵置頂-->
	</div>
	<!-- end of pagetop-->
	<main>
		<article>
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
												<span class="course-over-hint"
													style="display:${courseService.isCourseOver(courseVO.course_id)?'':'none'}">課程已經結束</span>
												<a
													href="course.do?action=getOne_For_Display&course_id=${courseVO.course_id}"><img
													class="course-li-img" id="course-li-title-1"
													src="photo?course_id=${courseVO.course_id}"
													alt="${courseVO.course_name}的課程圖片">></a>
											</div> <span class="course-li-title" id="course-li-title-1">${courseVO.course_name}</span>
											<div class="course-list-date-left">
												<span class="course-list-date-span">開課時間:</span> <span
													class="course-date-li" id="course-date-li-1"><fmt:formatDate
														value="${courseVO.course_start}"
														pattern="yyyy/MM/dd HH:mm" /></span>
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
							<div
								style="width: 300px; margin: 20px auto 0; text-align: center"><%@ include
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
		</article>
		<!-- end of article-->
	</main>
	<!-- end of main -->
	<footer>
		<div class="footer-bg">
			<div class="footer-murmur">
				<img src="../../image/FoodPron_Logo_white.png" alt="logo"
					data-aos="zoom-in">
				<ul>
					<li class="footer-li-fist">逛其他</li>
					<li>直播專區</li>
					<li>食材商城</li>
					<li>料理課程</li>
				</ul>
				<ul>
					<li class="footer-li-fist">逛食譜</li>
					<li>熱門食譜</li>
					<li>新到食譜</li>
					<li>全部分類</li>
				</ul>
				<ul>
					<li class="footer-li-fist">會員服務</li>
					<li>我的收藏</li>
					<li>帳號設定</li>
					<li>忘記密碼</li>
					<li>我的訂單</li>
				</ul>
				<ul>
					<li class="footer-li-fist">關於我們</li>
					<li>公司資訊</li>
					<li>品牌資產</li>
					<li>服務條款</li>
					<li>隱私權政策</li>
				</ul>
			</div>
		</div>
		<div class="footer-copyright">Copyright &copy; DA106-G4 Foodporn
			All rights reserved.</div>

	</footer>
	<script>
		$('#BackTop').click(function() {
			$('html,body').animate({
				scrollTop : 0
			}, 333);
		});
		$(window).scroll(function() {
			if ($(this).scrollTop() > 450) {
				$('#BackTop').fadeIn(222);
			} else {
				$('#BackTop').stop().fadeOut(222);
			}
		});

		//畫面捲動時隱藏
		window.onresize = function() {
			if ($(window).width() > 767) {
				$("nav").show();
			} else
				$("nav").hide();
		}
	</script>
	<script>
		$(".menu-open").on("click", function() {
			$("nav").slideToggle();
			$(this).toggleClass("active");
			$('body,html').toggleClass('add')
		});
	</script>
	<script src="../../slick/slick.js" type="text/javascript"
		charset="utf-8"></script>
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