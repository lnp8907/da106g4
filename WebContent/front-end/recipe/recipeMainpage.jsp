<%@page import="com.recipe.model.RecipeVO_saved"%>
<%@page import="java.util.List"%>

<%@page import="com.recipe.model.RecipeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<% 
	RecipeService recipeService = new RecipeService();
	List<RecipeVO_saved> list = recipeService.getAllForFrontEnd();
	pageContext.setAttribute("list", list);
	%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=3.0">
<title>Foodporn</title>

 <link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/css/CarMessage.css"/> 
<link rel="stylesheet" href="../../css/frontEnd.css">
<link rel="stylesheet" href="../../css/header-sider.css">
<link rel="stylesheet" href="../../slick/slick.css">
<link rel="stylesheet" href="../../slick/slick-theme.css">
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
<script src="../../slick/slick.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../../js/homePage.js" type="text/javascript"
	charset="utf-8"></script>
<script>
		$(document).ready(function() {
	 		getLatest();
	 		getMostPopular();
	// 		getCourse();
	 		getMonth();
	 	})
</script>

<style>
.main-page-card {
	display: inline-block;
    width: 25%;
    height: 280px;
    margin: 5px 44px 50px;
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
    width: 1100px;
    margin: 0 auto;
    
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

#article-recipe {
    margin-top: 0px;
        padding-top: 60px;
}
#article-section-seemore-recipe{
margin:10px auto;
}
#page{text-align: center;
    color: #E4002B;}
</style>
</head>

<body>
	<header>
		<div id="top-logo" class="logo">
<a href="<%=request.getContextPath()%>/index.jsp" title="回首頁"><img class="logo-photo"
				src="<%=request.getContextPath()%>/image/FoodPron_Logo.png" alt="logo"></a>
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

				
				
				
				
				
				
				
		
				
			<c:if test='${empty member_id}'>

			


				<div class="login">
					<a href="#"><img class="header-icon"
						src="../../image/user-icon.png" alt="login-icon">
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

				
				
				
				
<!-- 				<div class="shop-car"> -->
<!-- 					<a href="#"><img class="header-icon" -->
<!-- 						src="../../image/shopping-cart-icon.png" alt="shopping-cart"> -->
<!-- 						<div class="herder-icon-span"> -->
<!-- 							<span class="shop-car-span">購物車</span> -->
<!-- 						</div> </a> -->
<!-- 				</div> -->

<div class="shop-car">
					<a href="<%=request.getContextPath() %>/front-end/ShopPage/ProductPage?action=checktpage1">
					
						<div class="carmessage1">
					<img class="header-icon"
						src="<%=request.getContextPath() %>/image/shopping-cart-icon.png" alt="shopping-cart">
						
						
						</div>
							<div class="carmessage2" style="display: none">${fn:length(productcarlist)}</div>
						<div class="carmessagecircle" style="display: none">more</div>
					
					
						
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
	<h1></h1>
		<article>
			<section>
				<div class="search">
					<form method="post" action="RecipeServlet" name="search">
						<div class="search-select">

							<select name="selectedType"
								OnChange="Buildkey(this.selectedIndex);">
								<option value="">請選擇</option>
								<option value="rcstyle_no">風格</option>
								<option value="recipe_type">類型</option>
								<option value="calo_intake">卡洛里</option>
								<option value="cook_time">時間</option>
							</select> <select name="selectedValue" id="selected">
								<option value=""></option>
							</select>
						</div>
						<div class="search-bar">
							<input name="pageType" value="searchRecipe.jsp" type="hidden">
							<input type="search" name="keyword"
								placeholder="食譜名稱  蔬菜  等....(請以空格隔開)"> <input
								type="hidden" name="action" value="search">
							<button type=submit>食譜搜尋</button>
						</div>
					</form>
				</div>
			</section>
<section id="recipe">
				<!--食譜專區-->
				<div class="article-container" id="article-recipe">
					<div class="article-section-description"
						id="article-section-description-recipe">
						<div class="article-section-description-recipe-left">
						<span style="/* position: absolute; *//* left: 12%; *//* top: 30%; */vertical-align: top;display: inline-block;background-color: white;width: 120px;height: 120px;line-height: 120px;border-radius: 130px;font-size: 40px;" id="month">4月</span>
											<span class="article-section-description-recipe-left-span" id="blessing">Lorem,
								ipsum dolor sit amet consectetur adipisicing elit.</span> 
						</div>
						<div id="article-section-description-recipe-right-1" class="article-section-description-recipe-right"
							data-aos="fade-up">
							<div class="article-recipe-right-title">
								<h3 id="recipe-right-title-h3-1">最新推薦</h3>
								<a href="" id="recipe-right-title-a-1">
									<p id="recipe-right-title-p-1">Lorem ipsum dolor si
										blanditiis!</p>
							</div>
							<div class="article-recipe-right-photo-div">
								<img id="article-recipe-right-photo-1"
									src="<%=request.getContextPath()%>/image/bibimbap-4887417_960_720.jpg" alt="">
							</div>
							</a>
						</div>
						<div class="article-section-description-recipe-right"
							data-aos="fade-up">
							<div class="article-recipe-right-title">
								<h3 id="recipe-right-title-h3-2">人氣食譜</h3>
								<a href="" id="recipe-right-title-a-2">
									<p id="recipe-right-title-p-2">Lorem ipsum dolor si
										blanditiis!</p>
							</div>
							<div class="article-recipe-right-photo-div">
								<img id="article-recipe-right-photo-2"
									src="<%=request.getContextPath()%>/image/salmon-1485014_960_720.jpg" alt="">
							</div>
							</a>
						</div>
					</div>

				</div>
			</section>
			<!-- end of recipe-->
			<section>
			
<%@ include file="pages/page1.file"%>
<div class="main-page-card-container">

<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
	<c:forEach var="RecipeVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

		<div class="main-page-card">
			<div class="main-page-card-pic">
				<img src="${RecipeVO.recipe_photo}">
			</div>
			<div class="main-page-card-info">
				<div>
					<h4>
						<a class="show-one-link"
							href="<%=request.getContextPath()%>/front-end/recipe/RecipeServlet?action=getOne_For_Display&recipe_id=${RecipeVO.recipe_id}">${RecipeVO.recipe_name}</a>
					</h4>
					<span class="followNum" style="font-size:14px;margin-left:-0.5px;">廚師:</span><span style="font-size:14px;">${memberSvc.getOneMember(RecipeVO.member_id).member_name}</span>
				</div>
				<span class="recipeFollow">
					<button class="recipeFollow_btn">收藏</button> <input type="hidden"
					name="action" value="recipe_follow" class="action"> <!-- 							先假裝有會員810009按讚 -->
					<!-- 							先假裝有會員810009按讚 --> <!-- 							先假裝有會員810009按讚 --> <input
					type="hidden" name="member_id" value="810009" class="member_id">
					<input type="hidden" name="recipe_id" value="${RecipeVO.recipe_id}"
					class="recipe_id">
				</span>
			</div>
		</div>
	
	
	</c:forEach>
<!-- 	<span -->
<!-- 								class="article-section-seemore" -->
<!-- 								id="article-section-seemore-recipe"></span> -->
</div>
<%@ include file="pages/page2.file"%>



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

	<!-- JavasScript for shop-slide -->
	<script>
		$(".menu-open").on("click", function() {
			$("nav").slideToggle();
			$(this).toggleClass("active");
			$('body,html').toggleClass('add')
		});
	</script>
		<script>
		AOS.init();
	</script>
	
	<SCRIPT>
		key = new Array(4);
		key[0] = new Array;
		key[1] = new Array;
		key[2] = new Array;
		key[3] = new Array;
		key[4] = new Array;

		key[0][0] = "";

		key[1][0] = "中式";
		key[1][1] = "台式";
		key[1][2] = "義式";
		key[1][3] = "法式";
		key[1][4] = "韓式";
		key[1][5] = "泰式";
		key[1][6] = "美式";
		key[1][7] = "日式";

		key[2][0] = "沙拉";
		key[2][1] = "火鍋";
		key[2][2] = "點心";
		key[2][3] = "素食";
		key[2][4] = "蒸";
		key[2][5] = "炒";
		key[2][6] = "烤";
		key[2][7] = "炸";
		key[2][8] = "煮";
		key[2][9] = "滷";
		key[2][10] = "燉";
		key[2][11] = "煎";
		key[2][11] = "其他";

		key[3][0] = "0~200";
		key[3][1] = "200~400";
		key[3][2] = "400~600";
		key[3][3] = "600~800";
		key[3][4] = "800~";

		key[4][0] = "0~10";
		key[4][1] = "10~20";
		key[4][2] = "20~30";
		key[4][3] = "30~45";
		key[4][4] = "45~60";
		key[4][5] = "60~";

		//放值
		value = new Array;
		value[0] = new Array;
		value[1] = new Array;
		value[2] = new Array;
		value[3] = new Array;
		value[4] = new Array;

		value[0][0] = "";

		value[1][0] = "520000";
		value[1][1] = "520001";
		value[1][2] = "520002";
		value[1][3] = "520003";
		value[1][4] = "520004";
		value[1][5] = "520005";
		value[1][6] = "520006";
		value[1][7] = "520007";

		value[2][0] = "沙拉";
		value[2][1] = "火鍋";
		value[2][2] = "點心";
		value[2][3] = "素食";
		value[2][4] = "蒸";
		value[2][5] = "炒";
		value[2][6] = "烤";
		value[2][7] = "炸";
		value[2][8] = "煮";
		value[2][9] = "滷";
		value[2][10] = "燉";
		value[2][11] = "煎";
		value[2][11] = "其他";

		value[3][0] = "0~200";
		value[3][1] = "200~400";
		value[3][2] = "400~600";
		value[3][3] = "600~800";
		value[3][4] = "800~";

		value[4][0] = "0~10";
		value[4][1] = "10~20";
		value[4][2] = "20~30";
		value[4][3] = "30~45";
		value[4][4] = "45~60";
		value[4][5] = "60~";

		function Buildkey(num) {
			document.search.selectedValue.selectedIndex = 0;
			for (ctr = 0; ctr < key[num].length; ctr++) {
				document.search.selectedValue.options[ctr] = new Option(
						key[num][ctr], value[num][ctr]);
				//new Option( 顯示的內容 , Option的VALUE);
			}
			document.search.selectedValue.length = key[num].length;
		}
	</Script>
</body>
<script>

						if($(".carmessage2").html()>0 && $(".carmessage2").html()<10){
							$(".carmessagecircle").hide();
							$(".carmessage2").show();
						}
						else if($(".carmessage2").html()>9){
							$(".carmessage2").hide();
							$(".carmessagecircle").show();
						}
						else{
							$(".carmessagecircle").hide();
							$(".carmessage2").hide();

						}
						
						</script>
</html>