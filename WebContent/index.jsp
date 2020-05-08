	<%@page import="com.order_detail.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="com.member.model.*"%>
    	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    	
    <%@ page import="java.util.*"%>
    
<%
//購物車獲取
Vector<Order_detailVO> productlist;
if ((Vector<Order_detailVO>) session.getAttribute("productCar") == null) {
	productlist = new Vector<Order_detailVO>();

} else {
	productlist = (Vector<Order_detailVO>) session.getAttribute("productCar");
}

    String member_id =(String) session.getAttribute("member_id");
	out.println(member_id);
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	/*MemberVO 可以使用的屬性
	member_id
	member_name
	nickname
	member_status (0.普通會員 1.廚師)
	balance	
	*/
%>
<%@ page import="java.util.*"%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=3.0">
<title>Foodporn</title>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/frontEnd.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/header-sider.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/slick/slick.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/slick/slick-theme.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/homePage.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/CarMessage.css">
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<link rel="icon" href="<%=request.getContextPath() %>/image/head-FoodPron_Logo.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=request.getContextPath() %>/image/head-FoodPron_Logo.ico"
	type="image/x-icon" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script src="<%=request.getContextPath() %>/slick/slick.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/homePage.js" type="text/javascript" charset="utf-8"></script>
<script>
	$(document).ready(function() {
		getLatest();
		getMostPopular();
		getCourse();
		getLivestream();
		getMostPopLS();
		getMonth();
	})
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
								<input type="hidden" name="action" value="login">
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
						<div class="carmessage2" style="display: none">${fn:length(productcarlist)}</div>
						<div class="carmessagecircle" style="display: none">more</div>



						<div class="herder-icon-span">
							<span class="shop-car-span">購物車</span>

						</div>
					</a>
				</div>
								<!-- 購物車 -->
				
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
				<li class="dropdown"><a href="#"><img class="access-menu-icon"
						src="<%=request.getContextPath() %>/image/recipe-icon.png"><span class="menu-span">食譜專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img" src="<%=request.getContextPath() %>/image/ico_gnav_recipes_book.svg"><span
								class="dropdown-first-a-span">食譜主頁</span></a></li>
<!-- 						<li><a href="#">特輯食譜</a></li> -->
<!-- 						<li><a href="#">推薦食譜</a></li> -->
					<c:if test="${memberVO.member_status==1}">
						<li><a href="front-end/recipe/addRecipe.jsp">建立食譜</a></li>
					</c:if>
	
					</ul></li>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="<%=request.getContextPath() %>/image/livestream-icon.png"><span class="menu-span">直播專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="<%=request.getContextPath()%>/front-end/livestream/livestreamHomePage.jsp"><img
								class="dropdown-first-img"
								src="<%=request.getContextPath() %>/image/ico_gnav_recipes_movie.svg"><span
								class="dropdown-first-a-span">直播主頁</span></a></li>
						<li><a href="livestream.jsp">直播預告</a></li>
					<c:if test="${memberVO.member_status==1}">
						<li><a href="front-end/livestream/livestream.jsp#${member_id}">開啟直播</a></li>
						
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
							id="newuser" type="text" class="input">
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
</c:if>
	<!-- end of login-->
	
	
	

	
	<div class="pagetop">
		<img src="<%=request.getContextPath() %>/image/go-top-page.png" alt="BackTop" id="BackTop">
		<!--一鍵置頂-->
	</div>
	<!-- end of pagetop-->
	<main>
		<article>
			<!--廣告橫幅區-->
			<section id="ad">
				<div class="slider">
					<div class="slide_viewer">
						<div class="slide_group">
							<div class="slide" style="cursor: pointer;"
								onclick="window.location.href='#';"></div>
							<div class="slide" style="cursor: pointer;"
								onclick="window.location.href='#';"></div>
							<div class="slide" style="cursor: pointer;"
								onclick="window.location.href='#';"></div>
							<div class="slide" style="cursor: pointer;"
								onclick="window.location.href='#';"></div>
							<div class="slide" style="cursor: pointer;"
								onclick="window.location.href='#';"></div>
						</div>
					</div>
				</div>
					<!-- </div>End // .slider -->
					<div class="slide_buttons"></div>
			</section>
			<!-- end of ad -->

			<section id="recipe">
				<!--食譜專區-->
				<div class="article-container" id="article-recipe">
					<h2 class="ariticle-section-racipe-h2">食譜專區</h2>
					<span class="ariticle-section-cption">Recipe</span>
					<div class="article-section-description"
						id="article-section-description-recipe">
						<div class="article-section-description-recipe-left">
						<span style="/* position: absolute; *//* left: 12%; *//* top: 30%; */vertical-align: top;display: inline-block;background-color: white;width: 120px;height: 120px;line-height: 120px;border-radius: 130px;font-size: 40px;" id="month">4月</span>
											<span class="article-section-description-recipe-left-span" id="blessing">Lorem,
								ipsum dolor sit amet consectetur adipisicing elit.</span> <span
								class="article-section-seemore"
								id="article-section-seemore-recipe">更多食譜...</span>
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
									src="image/bibimbap-4887417_960_720.jpg" alt="">
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
									src="image/salmon-1485014_960_720.jpg" alt="">
							</div>
							</a>
						</div>
					</div>

				</div>
			</section>
			<!-- end of recipe-->

			<section>
				<!--直播專區-->
				<div class="article-container" id="livestream" data-aos="fade-up">
					<h2 class="article-section-tiltle">直播專區</h2>
					<span class="ariticle-section-cption">Livestream</span>
					<div id="article-container-livestream">
						<div class="article-section-description-livestream">
							<div class="article-section-description-livestream-left"
								id="livestream-left-1">
								<div class="livestream-left-1-video-container">
								<video id="livestream-left-1-video" loop="loop"
                                     src="" autoplay
                                     muted controls></video>
								</div>
								<div id="livestream-left-1-video-title"
									class="livestream-left-1-video-title">
									<a href="">
										<h3 id="livestream-left-1-video-h3"></h3>
									</a>
									<p id="livestream-left-1-video-p"></p>
								</div>
							</div>
							<div class="article-section-description-livestream-rightgroup">
								<div class="article-section-description-livestream-right"
									data-aos="fade-up" id="livestream-right-1">
									<div class="livestream-right-photo-div">
										<a href="" id="livestream-right-link-1"><img
											id="livestream-right-photo-1" src="image/chef/chef1.jpg"
											alt="" class="livestream-right-img">
									</div>
									<div class="livestream-right-title">
										<h3 id="livestream-right-title-chef-1"></h3>
										</a>
										<p id="livestream-right-livestream-name-1"></p>
									</div>
								</div>
								<div class="article-section-description-livestream-right"
									data-aos="fade-up" id="livestream-right-2">
									<div class="livestream-right-photo-div">
										<a href="" id="livestream-right-link-2"><img
											id="livestream-right-photo-2" src=""
											alt="">
									</div>
									<div class="livestream-right-title">
										<h3 id="livestream-right-title-chef-2"></h3>
										</a>
										<p id="livestream-right-livestream-name-2"></p>
									</div>
								</div>

								<div class="article-section-description-livestream-right"
									data-aos="fade-up" id="livestream-right-3">
									<div class="livestream-right-photo-div">
										<a href="" id="livestream-right-link-3"><img
											id="livestream-right-photo-3" src=""
											alt="">
									</div>
									<div class="livestream-right-title">
										<h3 id="livestream-right-title-chef-3"></h3>
										</a>
										<p id="livestream-right-livestream-name-3"></p>
									</div>
								</div>

								<div class="article-section-description-livestream-right"
									data-aos="fade-up" id="livestream-right-4">
									<div class="livestream-right-photo-div">
										<a href="" id="livestream-right-link-4"><img
											id="livestream-right-photo-4" src=""
											alt="">
									</div>
									<div class="livestream-right-title">
										<h3 id="livestream-right-title-chef-4"></h3>
										</a>
										<p id="livestream-right-livestream-name-4"></p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- end of livestream-->
					<div data-aos="fade-up">
						<span class="article-section-seemore">更多線上直播...</span>
					</div>
				</div>
			</section>
			<!-- end of livestream-->

			<section id="shop">
				<!--食材商城-->
				<div class="article-container">
					<div class="shop-slider-contain" data-aos="fade-up"
						data-aos-duration="2000">
						<h2>食材商城</h2>
						<span class="ariticle-section-cption">Ingredient store</span>
						<div class="shop-slider">
							<div class="shop-slide_viewer">
								<div class="shop-slide_group">
									<div class="shop-slide" style="cursor: pointer;"
										onclick="window.location.href='#';">
										<div class="shop-slide-article-and-photo">
											<div class="shop-slide-article">
												<p class="shop-slide-article-and-photo-recommend">食材推薦</p>
												<p class="shop-slide-article-and-photo-title">大蔥</p>
												<p class="shop-slide-article-and-photo-p">大蔥植株地上部的外形很像洋蔥。
													葉圓筒形而中空，它的葉鞘莖部包含成假莖， 若行培土軟化，便造成棍狀的“蔥白”。蔥白是主要食用部分</p>
											</div>
											<div class="shop-slide-photo">
												<img class="shop-slide-article-and-photo-img"
													src="image/ingredient/大蔥.jpg">
											</div>
										</div>
									</div>
									<div class="shop-slide" style="cursor: pointer;"
										onclick="window.location.href='#';">
										<div class="shop-slide-article-and-photo">
											<div class="shop-slide-article">
												<p class="shop-slide-article-and-photo-recommend">食材推薦</p>
												<p class="shop-slide-article-and-photo-title">南瓜</p>
												<p class="shop-slide-article-and-photo-p">
													原產墨西哥到中美洲一帶，世界各地普遍栽培。明代傳入中國，現南北各地廣泛種植<br>
													南瓜的果實作餚饌，亦可代糧食。全株各部又供藥用，種子含南瓜子氨基酸，有清熱除濕、驅蟲的功效，對血吸蟲有控制和殺滅的作用。
												</p>
											</div>
											<div class="shop-slide-photo">
												<img class="shop-slide-article-and-photo-img"
													src="image/ingredient/南瓜.jpg">
											</div>
										</div>
									</div>
									<div class="shop-slide" style="cursor: pointer;"
										onclick="window.location.href='#';">
										<div class="shop-slide-article-and-photo">
											<div class="shop-slide-article">
												<p class="shop-slide-article-and-photo-recommend">食材推薦</p>
												<p class="shop-slide-article-and-photo-title">番茄</p>
												<p class="shop-slide-article-and-photo-p">
													即西紅柿，是管狀花目、茄科、番茄屬的一種一年生或多年生草本植物。<br>
													原產南美洲，中國南北方廣泛栽培。番茄的果實營養豐富，具特殊風味。可以生食、煮食、加工番茄醬、汁或整果罐藏。
												</p>
											</div>
											<div class="shop-slide-photo">
												<img class="shop-slide-article-and-photo-img"
													src="image/ingredient/番茄.jpg">
											</div>
										</div>
									</div>
									<div class="shop-slide" style="cursor: pointer;"
										onclick="window.location.href='#';">
										<div class="shop-slide-article-and-photo">
											<div class="shop-slide-article">
												<p class="shop-slide-article-and-photo-recommend">食材推薦</p>
												<p class="shop-slide-article-and-photo-title">竹筍</p>
												<p class="shop-slide-article-and-photo-p">
													在中國自古被當作“菜中珍品”。竹筍是中國傳統佳餚，味香質脆，食用和栽培歷史極為悠久。《詩經》中就有“加豆之實，筍菹魚醢”，表明了人民食用竹筍有2500年以至3000年的歷史。
												</p>
											</div>
											<div class="shop-slide-photo">
												<img class="shop-slide-article-and-photo-img"
													src="image/ingredient/竹筍.jpg">
											</div>
										</div>
									</div>
									<div class="shop-slide" style="cursor: pointer;"
										onclick="window.location.href='#';">
										<div class="shop-slide-article-and-photo">
											<div class="shop-slide-article">
												<p class="shop-slide-article-and-photo-recommend">食材推薦</p>
												<p class="shop-slide-article-and-photo-title">馬鈴薯</p>
												<p class="shop-slide-article-and-photo-p">
													全球第四大重要的糧食作物，馬鈴薯塊莖含有大量的澱粉，能為人體提供豐富的熱量，且富含蛋白質、氨基酸及多種維生素、礦物質，尤其是其維生素含量是所有糧食作物中最全的。
												</p>
											</div>
											<div class="shop-slide-photo">
												<img class="shop-slide-article-and-photo-img"
													src="image/ingredient/馬鈴薯.jpg">
											</div>
										</div>
									</div>
									<!-- End // .shop-slide -->
									<div class="shop-slide" style="cursor: pointer;"
										onclick="window.location.href='#';">
										<div class="shop-slide-article-and-photo">
											<div class="shop-slide-article">
												<p class="shop-slide-article-and-photo-recommend">食材推薦</p>
												<p class="shop-slide-article-and-photo-title">高麗菜</p>
												<p class="shop-slide-article-and-photo-p">
													高麗菜我國各地都有栽培。高麗菜來自歐洲地中海地區是西方人最為重要的蔬菜之一。高麗菜大約有400個品種，包括有開花的捲心菜、莖捲心菜、光葉和捲葉捲心菜。
												</p>
											</div>
											<div class="shop-slide-photo">
												<img class="shop-slide-article-and-photo-img"
													src="image/ingredient/高麗菜.jpg">
											</div>
										</div>
									</div>
									<!-- End // .shop-slide -->
									<div class="shop-slide" style="cursor: pointer;"
										onclick="window.location.href='#';">
										<div class="shop-slide-article-and-photo">
											<div class="shop-slide-article">
												<p class="shop-slide-article-and-photo-recommend">食材推薦</p>
												<p class="shop-slide-article-and-photo-title">碗豆</p>
												<p class="shop-slide-article-and-photo-p">
													原產地中海和中亞細亞地區，是世界重要的栽培作物之一。種子及嫩莢、嫩苗均可食用；種子含澱粉、油脂，可作藥用，有強壯、利尿、止瀉之效；莖葉能清涼解暑，並作綠肥、飼料或燃料。
												</p>
											</div>
											<div class="shop-slide-photo">
												<img class="shop-slide-article-and-photo-img"
													src="image/ingredient/豌豆.jpg">
											</div>
										</div>
									</div>
									<!-- End // .shop-slide -->
								</div>
								<!-- End // .shop-slide_group -->
							</div>
							<!-- End // .shop-slide-viewer -->
						</div>
						<!-- End // .shop-slider -->
						<div class="shop-slide_buttons"></div>
					</div>
					<!-- End // .shop-slider-contain -->
					<!--商品成列-->
					<div id="mallproduct">
					
						<ul class="imglist">
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=水果類&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/0.png" alt=""> <span
									class="imglist-li-span">水果類</span>
							</a></li>		
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=肉類&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/1.png" alt=""> <span
									class="imglist-li-span">肉類</span>
							</a></li>
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=蔬菜類&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/2.png" alt=""> <span
									class="imglist-li-span">蔬菜類</span>
							</a></li>
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=乳品類&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/3.png" alt=""> <span
									class="imglist-li-span">乳品類</span>
							</a></li>
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=魚貝類&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/4.png" alt=""> <span
									class="imglist-li-span">魚貝類</span>
							</a></li>
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=菇類&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/5.png" alt=""> <span
									class="imglist-li-span">菇類</span>
							</a></li>
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=澱粉類&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/6.png" alt=""> <span
									class="imglist-li-span">穀物類</span>
							</a></li>
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=澱粉類&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/7.png" alt=""> <span
									class="imglist-li-span">澱粉類</span>
							</a></li>
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=酒類<&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/8.png" alt=""> <span
									class="imglist-li-span">酒類</span>
							</a></li>
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=調味料及辛香料類&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/9.png" alt=""> <span
									class="imglist-li-span">調味料及辛香料類</span>
							</a></li>
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=油脂類&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/10.png" alt=""> <span
									class="imglist-li-span">油脂類</span>
							</a></li>
							<li class="imglist-li" data-aos="zoom-in"><a href="front-end/ShopPage/ProductPage?product_type=所有商品&action=goProductPage"> <img
									class="imglist-li-img" src="image/shop/11.png" alt=""> <span
									class="imglist-li-span">所有商品</span>
							</a></li>
						</ul>

						<!--商品陳列-->
					</div>
					<!-- end of Mallproduct -->
					<div data-aos="zoom-in">
					<a href="front-end/ShopPage/ShopHomePage.jsp">
						<span class="article-section-seemore">更多食材...</span>
						</a>
					</div>

				</div>
				<!--end of article-container  -->
				<div class="article-section-description"
					id="article-section-description-shop"></div>
				<!-- end of article-section-description-->
			</section>
			<!-- end of shop-->
			<hr>
			<section class="middle-ad" data-aos="fade-up">
				<div class="middle-ad-contain">
					<img src="image/ad/禮券.jpg" alt="禮券">
				</div>
			</section>
			<hr>
			<section id="course">
				<!--課程專區-->
				<div class="article-container">
					<h2 data-aos="fade-up">課程專區</h2>
					<div data-aos="fade-up">
						<span class="ariticle-section-cption">Cooking Course</span>
					</div>
					<div
						class="article-section-description article-section-description-course">
						<!--課程陳列-->
						<div id="course-list-div">
							<ul class="course-list-ul" id="course-list-ul">	
							</ul>
							<!--end of 課程規劃-->
						</div>
						<!-- end of Course -->
						<!--課程橫幅陳列-->
						<div class="course-sider-list" data-aos="fade-up" data-aos-duration="2000" id="course-sider-list">
						</div>
					</div>
					<div data-aos="fade-up" data-aos-duration="2000">
						<span
							class="article-section-seemore article-section-seemore-course">更多料理課程...</span>
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
				<img src="image/FoodPron_Logo_white.png" alt="logo"
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
	<script>
		AOS.init();
	</script>
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