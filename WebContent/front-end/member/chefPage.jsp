<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.recipe_favorite.model.RecipeFavoriteServiec"%>
<%@page import="com.recipe.model.*"%>
<%@page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String member_id = (String) request.getAttribute("member_id");
	RecipeService recipeService = new RecipeService();
	List<RecipeVO> list = recipeService.getChefCooked(member_id);
	MemberService memberService = new MemberService();
	MemberVO memberVO = memberService.getOneMember(member_id);
	RecipeFavoriteServiec recipeFavoriteServiec = new RecipeFavoriteServiec();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("recipeFavoriteServiec", recipeFavoriteServiec);//取得按讚數
	String pageType = (String) request.getAttribute("pageType");
	if (pageType == null) {
		pageType = "recipePage.jsp";
	}
	request.setAttribute("pageType", pageType);
%>



<!DOCTYPE html>
<html lang="zh">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>廚師個人頁面</title>
<link rel="stylesheet" href="../../css/frontEnd.css">
<link rel="stylesheet" href="../../css/header-sider.css">
<link rel="stylesheet" href="../../css/homePage.css">
<link rel="stylesheet" href="../../css/searchRecipeCSS.css">
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<link rel="icon" href="../../image/head-FoodPron_Logo.ico"
	type="../../image/x-icon">
<link rel="shortcut icon" href="../../image/head-FoodPron_Logo.ico"
	type="../../image/x-icon" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script src="../../js/homePage.js" type="text/javascript"
	charset="utf-8"></script>

</head>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: '微軟正黑體', Arial, Helvetica, sans-serif;
	outline: none;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	color: #E4002B;
	text-decoration: underline;
	cursor: pointer;
}

.main-page {
	width: 996px;
	margin: 0 auto;
}

.main-page-header {
	width: 100%;
	padding: 0 20px;
	height: 260px;
}

.main-page-header-left {
	width: 48%;
	text-align: left;
	display: inline-block;
	vertical-align: middle;
	padding-left: 90px;
}

.main-page-header-left h2 {
	font-size: 40px;
	font-weight: 600;
	letter-spacing: -1px;
}

.main-page-header-left span {
	font-size: 14px;
	font-weight: 600;
	padding-left: 5px;
	color: rgb(151, 151, 151);
}

.main-page-header-right {
	width: 48%;
	text-align: right;
	display: inline-block;
	vertical-align: middle;
	height: 100%;
	padding: 3%;
	margin-top: 10px;
}

.chef-photo {
	width: 150px;
	height: 150px;
	overflow: hidden;
	border-radius: 75px;
	margin-left: 58%;
	margin-top: 20px;
}

.chef-photo img {
	width: 100%;
	height: 100%;
}

.main-page-header-right form {
	width: 50%;
	text-align: center;
	margin-left: 52%;
	margin-top: 20px;
}

.main-page-header-right button {
	width: 58px;
	height: 48px;
	border: 0;
	border-radius: 10px;
	background-color: #E4002B;
	color: white;
	font-size: 15px;
	font-weight: 600;
	box-shadow: 1px 1px 1px rgba(54, 27, 27, 0.7);
}

.main-page-header-right button:hover {
	cursor: pointer;
	box-shadow: 2px 2px 2px rgba(110, 110, 110, 0.7);
}

.main-page-navi {
	height: 55px;
	padding-left: 30px;
	width: 65%;
	margin-left: 30px;
	margin-bottom: 20px;
	padding-top: 10px;
	border-bottom: 1px solid lightgray;
}

.main-page-navi button {
	width: 55px;
	height: 40px;
	border: 0;
	border-radius: 15px;
	color: rgb(151, 151, 151);
	font-size: 16px;
	font-weight: 600;
	margin-left: 5px;
	background-color: white;
}

.main-page-navi button:hover {
	cursor: pointer;
	background-color: rgb(240, 240, 240);
	color: rgb(0, 0, 0);
	box-shadow: 1px 1px 1px rgba(110, 110, 110, 0.7);
}

.main-page-card {
	display: inline-block;
	width: 29%;
	height: 280px;
	margin: 5px 10px 50px;
}

.main-page-card-pic {
	overflow: hidden;
	width: 100%;
	height: 75%;
	border-radius: 10px;
}

.main-page-card-pic img {
	max-width: 100%;
	height: 100%;
}

.main-page-card-info>div {
	display: inline-block;
	width: 68%;
	text-align: left;
}

.main-page-card-info>form {
	display: inline-block;
	width: 30%;
	text-align: right;
}

.main-page-card-info button {
	width: 58px;
	height: 48px;
	border: 0;
	border-radius: 10px;
	background-color: #E4002B;
	color: white;
	font-size: 15px;
	font-weight: 600;
	box-shadow: 1px 1px 1px rgba(54, 27, 27, 0.7);
}

.main-page-card-info button:hover {
	cursor: pointer;
	box-shadow: 2px 2px 2px rgba(110, 110, 110, 0.7);
}

.main-page-card-container {
	width: 100%;
	margin: 0 auto;
	margin-left: 20px;
}

.main-page-card-info h4 {
	margin-top: 10px;
}

.main-page-card-info span {
	margin-left: 5px;
	margin-top: -10px;
	font-size: 13px;
	color: rgb(151, 151, 151);
}

.include-page {
	display: block;
	margin: 5px 0 10px 45px;
}

#recipe-btn {
	background-color: rgb(240, 240, 240);
}

.main-page-card-pic:hover {
	box-shadow: 8px 8px 5px rgba(110, 110, 110, 0.2);
}

.recipeFollow {
	padding-left: 20px;
}

section{
margin-top:0px;
}
</style>

<body>
<header>
		<div id="top-logo" class="logo">
			<a href="/DA106_G4_Foodporn/" title="回首頁"><img class="logo-photo"
				src="../../image/FoodPron_Logo.png" alt="logo"></a>
		</div>
		<div class="function">
			<div class="function-list">
				<a href="#"></a>
				<div class="member-center">
					<span class="member-center-spann">會員中心</span>
				</div>
				</a>
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
					<a href="#"><img class="header-icon"
						src="../../image/user-icon.png" alt="login-icon">
						<div class="herder-icon-span">
							<span class="login-span">登入</span>
						</div> </a>
				</div>
				<div class="shop-car">
					<a href="#"><img class="header-icon"
						src="../../image/shopping-cart-icon.png" alt="shopping-cart">
						<div class="herder-icon-span">
							<span class="shop-car-span">購物車</span>
						</div> </a>
				</div>
				<div class="notice">
					<a href="#"><img class="header-icon"
						src="../../image/ico_notice.png" alt="notice-icon">
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
								class="dropdown-first-img"
								src="../../image/ico_gnav_recipes_book.svg"><span
								class="dropdown-first-a-span">食譜主頁</span></a></li>
						<li><a href="#">特輯食譜</a></li>
						<li><a href="#">推薦食譜</a></li>
						<li><a href="#">建立食譜</a></li>
					</ul></li>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="../../image/livestream-icon.png"><span
						class="menu-span">直播專區</span></a>
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
						<li><a class="dropdown-first-a" href="#"><img
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
								class="dropdown-first-img"
								src="../../image/ico_gnav_recipes_pot.svg"><span
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
			<img class="login-close" src="../../image/close.png" alt="close">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">Sign In</label> <input id="tab-2"
				type="radio" name="tab" class="sign-up"><label for="tab-2"
				class="tab">Sign Up</label>
			<div class="login-form">
				<div class="sign-in-htm">
					<div class="group">
						<label for="user" class="label">Username</label> <input id="user"
							type="text" class="input">
					</div>
					<div class="group">
						<label for="pass" class="label">Password</label> <input id="pass"
							type="password" class="input" data-type="password">
					</div>
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
						<label for="tab-1">Already Member? </label>
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
			<section>


	<div class="main-page">
		<div class="main-page-header">
			<div class="main-page-header-left">
				<h2 class="chef-name"><%=memberVO.getMember_name()%></h2>
<!-- 				<span class="chef-info">15名追蹤中</span> -->
			</div>
			<div class="main-page-header-right">
				<div class="chef-photo">
					<img
						src="<%=request.getContextPath()%>/front-end/member/photo?member_id=<%=memberVO.getMember_id()%>">
				</div>
				<form action="" class="follow">
<!-- 					<button>追蹤</button> -->
				</form>
			</div>
		</div>
		<!-- end of main-page-header-->
		<div class="main-page-navi">
			<form action="RecipeServlet" method="post">
				<button class="pageDisplay" id="recipe-btn">食譜</button>
				<button class="pageDisplay" id="course-btn">課程</button>
				<button class="pageDisplay" id="livestream-btn">直播</button>
				<button class="pageDisplay" id="follow">粉絲</button>
				<input type="hidden" name="action" value="getChef_For_Display">
				<input type="hidden" id="pageDisplayType" name="pageType"
					value=""> <input type="hidden" name="member_id"
					value="<%=memberVO.getMember_id()%>">
			</form>
		</div>
		<!-- end of main-page-navi -->
<jsp:include page="<%=pageType %>" />
	<!-- end of main-page-->
	
	</section>
		</article>
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
	<!-- JavasScript-->
	<!-- JavasScript-->
	<!-- JavasScript for Sider -->
	<script src="../../javascript/header_sider.js" type="text/javascript"
		charset="utf-8"></script>
	<!-- JavasScript for LogForm -->
	<script src="../../javascript/loginForm.js" type="text/javascript"
		charset="utf-8"></script>

	<!-- JavasScript for BackTop -->
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
</body>
<script>
	$(document).ready(function() {
		$('.pageDisplay').on('click', function() {
			var sendType = $(this).text();
			switch (sendType) {
			case '食譜':
				$("#pageDisplayType").val('recipePage.jsp');
				break;
			case '課程':
				$("#pageDisplayType").val('chefCoursePage.jsp');
				break;
			case '直播':
				$("#pageDisplayType").val('recipePage.jsp');
				break;
			case '粉絲':
				$("#pageDisplayType").val('recipePage.jsp');
				break;
			}
			$(this).parent('form').submit();
		});
	});
</script>

<!-- 停止表單跳轉 -->
<script src="http://malsup.github.com/jquery.form.js"></script>
<script>
	//練習使用AJAX實現按讚功能
	$(document).ready(
			function() {
				$(".recipeFollow_btn").click(
						function() {
							var target = $(this);
							$
									.ajax({
										type : "GET",
										url : "ajaxResponse.do",
										data : {
											"action" : target.siblings(
													'.action').val(),
											"recipe_id" : target.siblings(
													'.recipe_id').val(),
											"member_id" : target.siblings(
													'.member_id').val()
										},
										dataType : "json",
										success : function(data) {
											target.parent().prev().children(
													'span.followNum')
													.text(data);
										},
										error : function() {

										}
									})
						})
			})
</script>
</html>