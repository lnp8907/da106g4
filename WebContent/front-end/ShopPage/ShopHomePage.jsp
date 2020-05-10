<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@page import="com.order_detail.model.*"%>
	
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
     <link rel="stylesheet" type="text/css"href="../../css/CarMessage.css"/> 
    

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
Vector<Order_detailVO> productlist;
if ((Vector<Order_detailVO>) session.getAttribute("productCar") == null) {
	productlist = new Vector<Order_detailVO>();

} else {
	productlist = (Vector<Order_detailVO>) session.getAttribute("productCar");
}

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
<c:set var="productlist" value="<%=productlist %>"/>

<body>
會員ID:  ${member_id}

		<header>
		<div id="top-logo" class="logo">
			<a href="<%=request.getContextPath() %>/index.jsp" title="回首頁"><img class="logo-photo"
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
					<a href="<%=request.getContextPath() %>/front-end/ShopPage/ProductPage?action=checktpage1">
					
						<div class="carmessage1">
					<img class="header-icon"
						src="<%=request.getContextPath() %>/image/shopping-cart-icon.png" alt="shopping-cart">
						
						
						</div>
							<div class="carmessage2" style="display: none" >${fn:length(productCarlist)}</div>
						<div class="carmessagecircle" style="display: none">more</div>
					
					
						
						<div class="herder-icon-span">
							<span class="shop-car-span">購物車</span>
							
						</div> </a>
				</div>
				
				
				<div style="display: none" class="notice">
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
			Map<Integer, String> productTOPtype = new HashMap<Integer, String>();
			
								productTOPtype.put(0, "水果類");
								productTOPtype.put(1, "肉類");
								productTOPtype.put(2, "蔬菜類");
								productTOPtype.put(3, "乳品類");
								productTOPtype.put(4, "魚貝類");
								productTOPtype.put(5, "菇類");
								productTOPtype.put(6, "穀物類");
								productTOPtype.put(7, "澱粉類");
								productTOPtype.put(8, "酒類");
								productTOPtype.put(9, "調味料及香辛料類");
								productTOPtype.put(10, "油脂類");
								productTOPtype.put(11, "所有商品");				
				request.setAttribute("productTOPtype", productTOPtype);
//producttype
			%>							<c:forEach var="productTOPtype" items="${productTOPtype}">
									<li><a href="<%=request.getContextPath() %>/front-end/ShopPage/ProductPage?product_type=${productTOPtype.value}&action=goProductPage">${productTOPtype.value}</a></li>
			
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
		<img src="<%=request.getContextPath() %>/image/go-top-page.png" alt="BackTop" id="BackTop">
		<!--一鍵置頂-->
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
    <a class="section" href="<%=request.getContextPath() %>/index.jsp">foodporn</a>
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

<!-- 			<div class="multiple-items repiceslider"> -->
<!-- 				<div> -->
<!-- 					<img src="MALLimage/TEST2.png" alt=""> -->
<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<img src="MALLimage/testp1.png" alt=""> -->
<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<img src="MALLimage/TEST2.png" alt=""> -->
<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<img src="MALLimage/testp1.png" alt=""> -->
<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<img src="MALLimage/TEST2.png" alt=""> -->
<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<img src="MALLimage/testp1.png" alt=""> -->
<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<img src="MALLimage/TEST2.png" alt=""> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<!-- end of Mallproduct -->
			
	        <c:if test="${member_id!=null}">

	                <%@ include file="ProductCarousel.jsp" %>
  </div>	
			
				<span class="article-section-seemore">查看瀏覽紀錄</span>
			</div>	                

	        </c:if>
	        
	
		</article>
	</main>
	<!--主要內容到此結束-->
	<!--*************************************以上開放編輯▲**************************************-->

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

	<script>
	$('.productad').slick({
		  dots: true,
		  infinite: true,
		  speed: 500,
		  fade: true,
		  cssEase: 'linear'
		});
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