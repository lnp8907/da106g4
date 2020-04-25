
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="<%=request.getContextPath() %>/plugin/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=3.0">
<title>FoodpornShop</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/frontEnd.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/header-sider.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/slick/slick.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/slick/slick-theme.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/homePage.css">
<link rel="icon" href="<%=request.getContextPath() %>/image/head-FoodPron_Logo.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=request.getContextPath() %>/image/head-FoodPron_Logo.ico"
	type="image/x-icon" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script src="<%=request.getContextPath() %>/slick/slick.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/homePage.js" type="text/javascript" charset="utf-8"></script>
<%@page import="javax.websocket.Extension.Parameter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- UI套件 -->
  <link rel="stylesheet" type="text/css" href=".<%=request.getContextPath() %>/css/semantic.min.css">
    <script src="<%=request.getContextPath() %>/plugin/Semantic-UI/semantic.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/productPage.css">


<meta charset="UTF-8">
<title>商城頁面</title>

</head>

<body>
	<header>
		<div id="top-logo" class="logo">
			<a href="/DA106_G4_Foodporn/" title="回首頁"><img class="logo-photo"
				src="<%=request.getContextPath() %>/image/FoodPron_Logo.png" alt="logo"></a>
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
					<a href="#"><img class="header-icon" src="<%=request.getContextPath() %>/image/user-icon.png"
						alt="login-icon">
						<div class="herder-icon-span">
							<span class="login-span">登入</span>
						</div> </a>
				</div>
				<div class="shop-car">
					<a href="#"><img class="header-icon"
						src="<%=request.getContextPath() %>/image/shopping-cart-icon.png" alt="shopping-cart">
						<div class="herder-icon-span">
							<span class="shop-car-span">購物車</span>
						</div> </a>
				</div>
				<div class="notice">
					<a href="#"><img class="header-icon" src="<%=request.getContextPath() %>/image/ico_notice.png"
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
						src="<%=request.getContextPath() %>/image/recipe-icon.png"><span class="menu-span">食譜專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img" src="<%=request.getContextPath() %>/image/ico_gnav_recipes_book.svg"><span
								class="dropdown-first-a-span">食譜主頁</span></a></li>
						<li><a href="#">特輯食譜</a></li>
						<li><a href="#">推薦食譜</a></li>
						<li><a href="front-end/recipe/recipeHomepage.jsp?pageType=addRecipe.jsp">建立食譜</a></li>
					</ul></li>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="<%=request.getContextPath() %>/image/livestream-icon.png"><span class="menu-span">直播專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img"
								src="<%=request.getContextPath() %>/image/ico_gnav_recipes_movie.svg"><span
								class="dropdown-first-a-span">直播主頁</span></a></li>
						<li><a href="#">直播預告</a></li>
						<li><a href="#">熱門直播</a></li>
						<li><a href="#">建立直播預告</a></li>
					</ul></li>
				<li class="dropdown dropdown-shop"><a><img
						class="access-menu-icon" src="<%=request.getContextPath() %>/image/shop-icon.png"><span
						class="menu-span">食材商城</span></a>
					<ul id="dropdown-shop-ul">
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img"
								src="<%=request.getContextPath() %>/image/ico_gnav_recipes_salad.svg"><span
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
						src="<%=request.getContextPath() %>/image/course-icon.png"><span class="menu-span">料理課程</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img" src="<%=request.getContextPath() %>/image/ico_gnav_recipes_pot.svg"><span
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
			<img class="login-close" src="image/close.png" alt="close"> <input
				id="tab-1" type="radio" name="tab" class="sign-in" checked><label
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
						<label for="tab-1">Already Member?</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end of login-->
	<div class="pagetop">
		<img src="image/go-top-page.png" alt="BackTop" id="BackTop">
		<!--一鍵置頂-->
	</div>

<!-- 內容 -->



























	<footer>
		<div class="footer-bg">
			<div class="footer-murmur">
				<img src="<%=request.getContextPath() %>/image/FoodPron_Logo_white.png" alt="logo"
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
	<script src="javascript/header_sider.js" type="text/javascript"
		charset="utf-8"></script>
	<!-- JavasScript for LogForm -->
	<script src="javascript/loginForm.js" type="text/javascript"
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

	<!-- JavasScript for shop-slide -->
	<script>
		$(".shop-slide-article-and-photo").hover(function() {
			$(".shop-slide-article-and-photo-img").removeClass('imgSmall');
			$(".shop-slide-article-and-photo-img").addClass('imgScale');
		});
		$(".shop-slide-article-and-photo").mouseleave(function() {
			$(".shop-slide-article-and-photo-img").addClass('imgSmall');
		});

		/* 直播專區 right scale 效果 */
		$(".article-section-description-livestream-right").hover(function() {
			$(".livestream-right-img", this).addClass('imgScale');
		});
		$(".imglist-li a").hover(function() {
			$(".imglist-li-span", this).removeClass('textRecoverColor');
			$(".imglist-li-img", this).removeClass('imgSmall');
			$(".imglist-li-img", this).addClass('imgScale');
			$(".imglist-li-span", this).addClass('textChangeColor');
		});
		$(".imglist-li a").mouseleave(function() {
			$(".imglist-li-span", this).addClass('textRecoverColor');
			$(".imglist-li-img", this).addClass('imgSmall');
		});
	</script>
	<script>
		$(".menu-open").on("click", function() {
			$("nav").slideToggle();
			$(this).toggleClass("active");
			$('body,html').toggleClass('add')
		});
	</script>

</body>

</html>














