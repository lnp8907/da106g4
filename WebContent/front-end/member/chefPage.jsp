<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.recipe_favorite.model.RecipeFavoriteServiec"%>
<%@page import="com.recipe.model.*"%>
<%@page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%

	String member_id = (String) request.getAttribute("member_id");
if(member_id==null)
	member_id = request.getParameter("member_id");
	RecipeService recipeService = new RecipeService();
	List<RecipeVO> list = recipeService.getChefCooked(member_id);
	MemberService memberService = new MemberService();
	MemberVO memberVO = memberService.getOneMember(member_id);
	RecipeFavoriteServiec recipeFavoriteServiec = new RecipeFavoriteServiec();
	request.setAttribute(member_id, "member_id");
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
			<a href="<%=request.getContextPath() %>/index.jsp" title="回首頁"><img class="logo-photo"
				src="<%=request.getContextPath() %>/image/FoodPron_Logo.png" alt="logo"></a>
		</div>
		<div class="function">
			<div class="function-list">
				<a href="#"></a>
				<div class="member-center">
				<a href="<%=request.getContextPath() %>/front-end/member/TestMemberHomepage.jsp" id="member-center">
					<span class="member-center-spann">會員中心</span>
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
		
				
				
				
				
				
				<!-- 購物車 -->
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
				<div class="follow">
									<button id="follow_btn">追蹤</button> 
				</div>
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
						});

			})
</script>
<c:if test="${!(memberVO==null)}">
<script >

$(document).ready(
		function() {
$("#follow_btn").click(
		function() {
			var target = $(this);
			$.ajax({
						type : "GET",
						url : "ajaxResponse.do",
						data : {
							"action" : 'follow',
							"chef_id" : '<%=memberVO.getMember_id()%>',
							"member_id": ${sessionScope.memberVO.member_id} 
						},
						dataType : "json",
						success : function() {

						},
						error : function() {

						}
					})
		})

		})
</script>
</c:if>
</html>