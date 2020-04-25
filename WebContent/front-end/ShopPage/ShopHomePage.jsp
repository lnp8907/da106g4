<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<title>商城首頁</title>
<head>
<!-- JQ -->
    <script src="../../js/jquery-3.4.1.min.js"></script>
        <script src="../../js/jquery-migrate-1.4.1.min.js"> </script>
    

<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=3.0">
<title>FoodpornShop</title>
<link rel="stylesheet" href="../../css/header-sider.css">
<link rel="icon" href="../../image/head-FoodPron_Logo.ico"
	type="image/x-icon">
<link rel="shortcut icon" href="../../image/head-FoodPron_Logo.ico"
	type="image/x-icon" />

<!-- BOOT -->
<script src="../../js/bootstrap.js"></script>
<link rel="stylesheet" href="../../css/bootstrap.css">

<!-- 首頁CSS -->
   <link rel="stylesheet" href="../../css/frontEnd.css">
    <link rel="stylesheet" href="../../css/header-sider.css">
    <link rel="icon" href="../../image/head-FoodPron_Logo.ico" type="image/x-icon">
    <link rel="shortcut icon" href="../../image/head-FoodPron_Logo.ico" type="image/x-icon" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>


<!-- 廣告連播套件 -->
<link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick-theme.css"/>

    <script type="text/javascript" src="../../css/slick-1.8.1/slick/slick.js"></script>



	
	<!-- 商城CSS -->
	<link rel="stylesheet" href="css/productPage.css">
<!-- JQ -->


<!--套件UI-->
<link rel="stylesheet" href="../../css/semantic.min.css">
<script src="../../js/semantic.min.js"></script>



</head>
<%
				Map<Integer, String> producttype2 = new HashMap<>();
                producttype2.put(0, "所有商品");
				producttype2.put(1, "水果類");
				producttype2.put(2, "肉類");
				producttype2.put(3, "蔬菜類");
				producttype2.put(4, "乳品類");
				producttype2.put(5, "魚貝類");
				producttype2.put(6, "菇類");
				producttype2.put(7, "穀物類");
				producttype2.put(8, "澱粉類");
				producttype2.put(9, "酒類");
				producttype2.put(10, "油脂類");
				producttype2.put(11, "調味及香辛料類");

				session.setAttribute("producttype2", producttype2);


			%>

<body>
	<header>
		<!--以下是上方選單,你用不到,不要動!!!-->
		<!--以下是上方選單,你用不到,不要動!!!-->
		<!--以下是上方選單,你用不到,不要動!!!-->
		<div id="top-logo" class="logo">
			<a href="frontEnd.html" title="回首頁"><img class="logo-photo"
				src="../../image/Handdrawn Circle Logo.png" alt="logo"></a>
		</div>
		<div class="function">
			<div class="function-list">
				<div class="member-center">
					<a href="#"><span class="member-center-spann">會員中心</span></a>
				</div>
			</div>
			<div class="function-list">
				<div class="shop-car">
					<a href="#"><img class="header-icon"
						src="../../image/shopping-cart-icon.png" alt="shopping-cart">
						<div class="herder-icon-span">
							<span class="shop-car-span">購物車</span>
						</div> </a>
				</div>
			</div>
			<div class="login">
				<div class="function-list">
					<a href="#"><img class="header-icon" src="../../image/user-icon.png"
						alt="login-icon">
						<div class="herder-icon-span">
							<span class="login-span">登入</span>
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
								class="dropdown-first-img" src="image/ico_gnav_recipes_book.svg"><span
								class="dropdown-first-a-span">食譜主頁</span></a></li>
						<li><a href="#">特輯食譜</a></li>
						<li><a href="#">推薦食譜</a></li>
						<li><a href="#">建立食譜</a></li>
					</ul></li>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="image/livestream-icon.png"><span class="menu-span">直播專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img"
								src="image/ico_gnav_recipes_movie.svg"><span
								class="dropdown-first-a-span">直播主頁</span></a></li>
						<li><a href="#">直播預告</a></li>
						<li><a href="#">熱門直播</a></li>
						<li><a href="#">建立直播預告</a></li>
					</ul></li>
				<li class="dropdown dropdown-shop"><a><img
						class="access-menu-icon" src="image/shop-icon.png"><span
						class="menu-span">食材商城</span></a>
					<ul id="dropdown-shop-ul">
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img"
								src="image/ico_gnav_recipes_salad.svg"><span
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
				<li class="dropdown"><a><img class="../../access-menu-icon"
						src="image/course-icon.png"><span class="menu-span">料理課程</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img" src="image/ico_gnav_recipes_pot.svg"><span
								class="dropdown-first-a-span">課程主頁</span></a></li>
						<li><a href="#">熱門課程</a></li>
						<li><a href="#">建立料理課程</a></li>
					</ul></li>
			</ul>
		</nav>
	</header>
	<!-- end of header-->
	<!--上方選單尾巴-->
	<!-- 這個是登箱製作,你不要動! -->
	<div class="login-wrap">
		<div class="login-html">
			<img class="login-close" src="../../image/close.png" alt="close"> <input
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
						<label for="user" class="label">Username</label> <input id="user"
							type="text" class="input">
					</div>
					<div class="group">
						<label for="pass" class="label">Password</label> <input id="pass"
							type="password" class="input" data-type="password">
					</div>
					<div class="group">
						<label for="pass" class="label">Repeat Password</label> <input
							id="pass" type="password" class="input" data-type="password">
					</div>
					<div class="group">
						<label for="pass" class="label">Email Address</label> <input
							id="pass" type="text" class="input">
					</div>
					<div class="group">
						<input type="submit" class="button" value="Sign Up">
					</div>
					<div class="hr"></div>
					<div class="foot-lnk">
						<label for="tab-1">Already Member? 
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end of login-->
	<!--登箱結束-->
	<!--一鍵置頂-->
	<div class="pagetop">
		<a href="#top-logo"><img src="../../image/go-top-page.png"
			alt="go-top-page"></a>
	</div>
	<!-- end of pagetop-->
	<!--*************************************以下開放編輯▼**************************************-->
	<!-- 主要內容從這裡開始!!!請自行編輯調整,請勿更動載入的CSS檔案自行生成 -->
	<!-- 主要內容從這裡開始!!!請自行編輯調整,請勿更動載入的CSS檔案自行生成 -->
	<!-- 主要內容從這裡開始!!!請自行編輯調整,請勿更動載入的CSS檔案自行生成 -->
	<!-- 不要動外部的css檔和js檔案!-->
	<!-- 不要動外部的css檔和js檔案!-->
	<!-- 不要動外部的css檔和js檔案!-->



	<main>
		<%
			Map<Integer, String> producttype = new HashMap<Integer, String>();
// 								List A=new ArrayList();
// 						        for (int i = 0; i < 11; i++) {
// 						            A.add(i);
// 						        }
// 						        for (Object a:A
// 						             ){
// 						            System.out.println(a);

// 						        }
// 						      Collections.shuffle(A);
	        
				
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

				int t = 0;
			%>
			
			
			
		<article id="shop-article">
		<!-- 搜尋列請填入首頁 -->
		<div id="ShopPathLocation">
		<div class="ui breadcrumb ShopPathLocation">
    <a class="section">foodporn</a>
    <i class="right angle icon divider"></i>

    <div class="active section">商城首頁</div>
</div>
</div>


			<!-- 以下開始你的各種標籤 -->

		
			<!--搜尋列-->
			<!--            <div class="ShopLsearch">-->
			<!--                <div class="input-group ShopSearchInput">-->
			<!--                    <input type="text" class="form-control " placeholder="搜尋"/>-->
			<!--                    <span class="input-group-btn">-->
			<!--               <button type="submit" class="btn btn-info btn-search glyphicon glyphicon-search">搜尋</button>-->
			<!--            </span>-->
			<!--                </div>-->
			<!--        </div>-->





			<!--搜尋-->
			<div id="ShopLsearch">
			
			<form METHOD="post" ACTION="ProductPage">
						<div class=" ui icon input">

   
 
			<select class="ui compact selection dropdown" name="product_type">
							<c:forEach var="producttype2" items="${producttype2}">
		<option value="${producttype2.key==0? null:producttype2.value}">${producttype2.value}</option>
			</c:forEach>
			</select>
 <input type="text"  placeholder="你想找甚麼..." name='product_name'>
 		  <i id='searchproduct' class=" inverted search link icon"></i>
	
 
  <input id='sendsearch' type="submit" style='display:none' >
  <input type="hidden" value="searchproduct" name="action">
  <script>
  $('#searchproduct').click(function(){
	  $('#sendsearch').click();	  
  });
  
  
  
  </script>
  </div>
</form>
</div>
			<!--廣告-->
			<div id="shopad1" class="productad">
	<div>
					<img src="../../image/ad/DA106-G4%20PROJECT%20FOODPORN.png" alt="">
				</div>
				<div>
					<img src="../../image/ad/EnjoyYourLivestream.png" alt="">
				</div>
				<div>
					<img src="../../image/ad/recipeBG.png" alt="">
				</div>
				</div>

			
		



			<!--商品成列2-->
			
			<section id="shop">
				<div class="article-container">
					<!--商品成列-->
					<div id="ShopListLocation">
						<ul class="imglist">

							<c:forEach var="producttype" items="${producttype}">
							
							
							
								<li class="imglist-li"id="typeli<%=t %>" ><a href="ProductPage?product_type=${producttype.value}&action=goProductPage">
								 <img
										class="imglist-li-img" src='../../image/shop/${producttype.key}.png' alt="圖片不見了" >
										 <span
										class="imglist-li-span">${producttype.value} </span>
								</a>
								</li>
								<%
									t++;
								%>
							</c:forEach>




						</ul>

					</div>
					<a href="ProductPage?product_type=all&action=goProductPage">
					
					<span class="article-section-seemore">前往購物</span>
					
					
					</a>

				</div>
				<div class="article-section-description"
					id="article-section-description-shop"></div>
				</div>
			</section>
			<!-- -------- -->







			<!--食譜-->

			<div class="multiple-items repiceslider">
				<div>
					<img src="MALLimage/TEST2.png" alt="">
				</div>
				<div>
					<img src="MALLimage/testp1.png" alt="">
				</div>
				<div>
					<img src="MALLimage/TEST2.png" alt="">
				</div>
				<div>
					<img src="MALLimage/testp1.png" alt="">
				</div>
				<div>
					<img src="MALLimage/TEST2.png" alt="">
				</div>
				<div>
					<img src="MALLimage/testp1.png" alt="">
				</div>
				<div>
					<img src="MALLimage/TEST2.png" alt="">
				</div>
			</div>
			<!-- end of Mallproduct -->
			<div>
				<span class="article-section-seemore">查看瀏覽紀錄</span>
			</div>




		</article>
	</main>
	<!--主要內容到此結束-->
	<!--*************************************以上開放編輯▲**************************************-->

	<footer>
		<!--這個是底部,你用不到-->


	</footer>

	<script>
	$('.productad').slick({
		  dots: true,
		  infinite: true,
		  speed: 500,
		  fade: true,
		  cssEase: 'linear'
		});
	</script>

</body>





</html>