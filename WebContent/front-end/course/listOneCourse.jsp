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
	course_detail1 = token[0].split("%");
	course_detail2 = token[1].split("%");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>課程資訊</title>
<link rel="stylesheet" href="/DA106_G4_Foodporn_Git/front-end/course/courseCSS/listOneCourseCSS.css">
<link rel="stylesheet" href="../../css/frontEnd.css">
<link rel="stylesheet" href="../../slick/slick.css">
<link rel="stylesheet" href="../../slick/slick-theme.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
	<link rel="stylesheet" href="../../css/CarMessage.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<style>
*{
font-family: "微軟正黑體";
}
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
.menu-span{
    color: #E4002B;
}
.notice-span,.login-span,.shop-car-span{
    color: #E4002B !important;
}
</style>
</head>

<body>
<header>
		<div id="top-logo" class="logo">
			<a href="/DA106_G4_Foodporn_Git/index.jsp" title="回首頁"><img
				class="logo-photo" src="../../image/FoodPron_Logo.png" alt="logo"></a>
		</div>
		<div class="function">
			<div class="function-list">
				<a href="#"></a>
				<div class="member-center">
					<a href="front-end/member/TestMemberHomepage.jsp"
						id="member-center"> <span class="member-center-spann">會員中心</span>
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
				<div class="login">
					<a href="#"><img class="header-icon" src="../../image/user-icon.png"
						alt="login-icon">
						<div class="herder-icon-span">
							<span class="login-span">登入</span>
						</div> </a>
				</div>
				<div class="shop-car">
					<a href="front-end/ShopPage/ProductPage?action=checktpage1">
						<div class="carmessage1">
							<img class="header-icon" src="../../image/shopping-cart-icon.png"
								alt="shopping-cart">
						</div>
						<div class="carmessage2">1</div>
						<div class="carmessagecircle" style="display: none">more</div>
						<div class="herder-icon-span">
							<span class="shop-car-span">購物車</span>
						</div>
					</a>
				</div>
				<div class="notice">
					<a href="#"><img class="header-icon" src="../../image/ico_notice.png"
						alt="notice-icon">
						<div class="herder-icon-span">
							<span class="notice-span">通知總覽</span>
						</div> </a>
				</div>

			</div>

		</div>
		<!-- end of function-->
		<nav id="navigation">
			<ul>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="../../image/recipe-icon.png"><span class="menu-span">食譜專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img" src="../../image/ico_gnav_recipes_book.svg"><span
								class="dropdown-first-a-span">食譜主頁</span></a></li>
						<li><a href="#">特輯食譜</a></li>
						<li><a href="#">推薦食譜</a></li>
						<li><a
							href="front-end/recipe/recipeHomepage.jsp?pageType=addRecipe.jsp">建立食譜</a></li>
					</ul></li>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="../../image/livestream-icon.png"><span class="menu-span">直播專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img"
								src="../../image/ico_gnav_recipes_movie.svg"><span
								class="dropdown-first-a-span">直播主頁</span></a></li>
						<li><a href="#">直播預告</a></li>
						<li><a href="#">熱門直播</a></li>
						<li><a href="#">建立直播預告</a></li>
					</ul></li>
				<li class="dropdown dropdown-shop"><a><img
						class="access-menu-icon" src="../../image/shop-icon.png"><span
						class="menu-span">食材商城</span></a>
					<ul id="dropdown-shop-ul">
						<li><a class="dropdown-first-a"
							href="front-end/ShopPage/ShopHomePage.jsp"><img
								class="dropdown-first-img"
								src="../../image/ico_gnav_recipes_salad.svg"><span
								class="dropdown-first-a-span">食材商城主頁</span></a></li>
						<li><a href="#">調味料</a></li>
						<li><a href="#">果醬</a></li>
						<li><a href="#">麵粉</a></li>
						<li><a href="#">酒類</a></li>
						<li><a href="#">蔬菜</a></li>
						<li><a href="#">水果</a></li>
						<li><a href="#">海鮮</a></li>
						<li><a href="#">肉類</a></li>
						<li><a href="#">乳製品</a></li>
						<li><a href="#">香料</a></li>
						<li><a href="#">罐頭</a></li>
						<li><a href="#">乾貨</a></li>
					</ul></li>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="../../image/course-icon.png"><span class="menu-span">料理課程</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img" src="../../image/ico_gnav_recipes_pot.svg"><span
								class="dropdown-first-a-span">課程主頁</span></a></li>
						<li><a href="#">熱門課程</a></li>
						<li><a href="#">建立料理課程</a></li>
					</ul></li>
			</ul>
		</nav>
	</header>
	<!-- end of header-->

	<!-- 登箱開始 -->

	<div class="login-wrap">
		<div class="login-html">
			<img class="login-close" src="../../image/close.png" alt="close"> <input
				id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">Sign In</label> <input id="tab-2"
				type="radio" name="tab" class="sign-up"><label for="tab-2"
				class="tab">Sign Up</label>
			<div class="login-form">
				<form method="POST" action="member.do">
					<div class="sign-in-htm">
						<div class="group">
							<label for="user" class="label">Account</label> <input
								name="mem_id" id="user" type="text" class="input">
						</div>
						<div class="group">
							<label for="pass" class="label">Password</label> <input
								name="psw" id="pass" type="password" class="input"
								data-type="password">
						</div>
						<input type="hidden" name="action" value="login">
						<div class="group">
							<input id="check" type="checkbox" class="check" checked>
							<label for="check"><span class="icon"></span> Keep me
								Signed in</label>
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
				<div class="sign-up-htm">
					<div class="group">
						<label for="newuser" class="label">User</label> <input
							id="newuser" type="text" class="input">
					</div>
					<div class="group">
						<label for="newpass" class="label">Password</label> <input
							id="newpass" type="password" class="input" data-type="password">
					</div>
					<div class="group">
						<label for="renewpass" class="label">Repeat Password</label> <input
							id="renewpass" type="password" class="input" data-type="password">
					</div>
					<div class="group">
						<label for="email" class="label">Email Address</label> <input
							id="email" type="email" class="input">
					</div>
					<div class="group">
						<input type="submit" class="button" value="Sign Up">
					</div>
					<div class="hr"></div>
					<div class="foot-lnk">
						<label for="tab-1">Already Member?</label>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- end of login-->

	<div class="pagetop">
		<img src="../../image/go-top-page.png" alt="BackTop" id="BackTop">
		<!--一鍵置頂-->
	</div>
	<!-- end of pagetop-->
	<main>
		<article>


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
<!-- 						<button class="chef-follow" name="chef_follow">追蹤</button> -->
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