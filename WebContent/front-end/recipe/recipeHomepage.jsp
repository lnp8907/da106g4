<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String pageType = request.getParameter("pageType");
	if (pageType == null) {
		pageType = (String) request.getAttribute("pageType");
	}
	request.setAttribute("pageType", pageType);
	
%>
    	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
	// 	$(document).ready(function() {
	// 		getLatest();
	// 		getMostPopular();
	// 		getCourse();
	// 		getMonth();
	// 	})
</script>
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
				<li class="dropdown"><a href="front-end/course/listAllCourse.jsp"><img class="access-menu-icon"
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
			<section>
				<jsp:include page="<%=pageType%>" />



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
	
</body>

</html>