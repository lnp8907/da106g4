	<%@page import="java.util.stream.Collectors"%>
	
	
	
	<%@page import="com.order_detail.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="com.member.model.*"%>
    	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    	
    <%@ page import="java.util.*"%>
    <%@ page import="com.notice.model.*"%>
    
<%
//購物車獲取
Vector<Order_detailVO> productlist;
if ((Vector<Order_detailVO>) session.getAttribute("productCar") == null) {
	productlist = new Vector<Order_detailVO>();

} else {
	productlist = (Vector<Order_detailVO>) session.getAttribute("productCar");
}

    String member_id =(String) session.getAttribute("member_id");
	//out.println(member_id);
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	/*MemberVO 可以使用的屬性
	member_id
	member_name
	nickname
	member_status (0.普通會員 1.廚師)
	balance	
	*/
	
	//拿出6則通知
	if(memberVO!=null){
	  NoticeService noticeSvc = new NoticeService();
	  if (noticeSvc.getAllByMb_id_2(member_id)!=null){
		 List<NoticeVO> noticeList = noticeSvc.getAllByMb_id_2(member_id);
	 	 pageContext.setAttribute("noticeList", noticeList);
	  }
	}
%>
<%@ page import="java.util.*"%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=3.0">
<title>Foodporn</title>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/facebookLightbox.css">
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

<link rel="stylesheet" href="css/liveMainPage.css">



 <script type="text/javascript" src="<%=request.getContextPath() %>/javascript/jquery-3.2.1.min.js"></script> 
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script src="<%=request.getContextPath() %>/slick/slick.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/homePage.js" type="text/javascript" charset="utf-8"></script>
<script>
	$(document).ready(function() {
// 		getLatest();
// 		getMostPopular();
// 		getCourse();
		getLivestream();
		getMostPopLS();
// 		getMonth();
	})
</script>
<style>
.livelist {
    margin: 30px auto;
    width: 75%;
    height: 700px;
    background-color: #f8f8f8;
}
.livelist .listcontext {
    width: 95%;
    margin: auto;
    text-align: center;
}
.livelisttitle {
    text-align: center;
    width: auto; 
    height: auto; 
    margin: 20px auto;
    font-size: 62px;
}
</style>
</head>

<body>
	<header>
	<!-- notice start -->
<div id="fblightbox" style="width:20rem;">
  <div class="fblightbox-wrap">
    <div class="fblightbox-header">
      <h3>Foodporn </h3>
    </div>
    <div class="fblightbox-content">
      <div>
	       <!-- 所有訊息 -->
	       <c:forEach var="noticeVO" items="${noticeList}">
	       <a class="messageDivA" href="#" >
	        <c:if test="${noticeVO.notice_status==0}">
	        <div class="msgNotRead readOrNotRead message" style="border-bottom:1px solid #E4002B" >
	        <input type="hidden" name="notice_id" class="messageNoticeId" value="${noticeVO.notice_id}">
	        </c:if>
	        
	        <c:if test="${noticeVO.notice_status==1}">
	        <div class="msgRead readOrNotRead" style="border-bottom:1px solid #E4002B">
	        </c:if>
	         <div>${noticeVO.content}</div>
	         <div>${noticeVO.notice_time} </div>
	        </div>
	       </a> 
	       </c:forEach>
	      </div>
    </div>
    <div class="fblightbox-footer">
      <a href="#" class="fbbutton">See More</a>
      <a href="#" id="close" class="fbbutton">Close</a>
    </div>
  </div>
</div>
<!-- notice over -->




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

			 <%
                                MemberService memSvc=new MemberService();
                                List<MemberVO> Llist=memSvc.getAll();
                                 Llist=Llist.stream().filter(P->P.getLivestream_status()==2).filter(p->p.getMember_status()==1).collect(Collectors.toList());
                            %>
                                <c:set var="Livelist" value="<%=Llist %>"/>
<%--                                 		 <c:if test="${fn:length(Livelist)}"> --%>
<%--                                 		 </c:if> --%>

                                
                                          <section>
                                
<div class="livelisttitle">正在直播中</div>
    	<%@ include file="file/page1.file" %> 

<div class="livelist" >
    <div class="listcontext">
    <ul>

    
    	<c:forEach var="Livelist" items="${Livelist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
    
            <li ><a
                    href="<%=request.getContextPath() %>/front-end/livestream/livestream.jsp#${Livelist.member_id}" >
                <div class="livedivlist">


                    <img src="<%=request.getContextPath() %>/back-end/member/DBGifReader4.do?photo_type=mempic&member_id=${Livelist.member_id}" height="250" width="250" alt="毛妮 你處理一下圖片"/></div>
                <div  >
              
                    ${Livelist.member_name}
                </div>

            </a></li>
       
	</c:forEach>

    </ul>

</div>


</div>   
                                </section>
                             
			<!-- end of course-->

		</article>
		<!-- end of article-->
	</main>
	
	
	<!-- end of main -->
	<footer>
		<div  class="footer-bg">
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
	<script src="../../javascript/header_sider.js" type="text/javascript"
		charset="utf-8"></script>
	<!-- JavasScript for LogForm -->
	<script src="../../javascript/loginForm.js" type="text/javascript"
		charset="utf-8"></script>
<!-- jquery -->

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
						
<script>
var fblightbox = $('#fblightbox');
//fblightbox.css({'margin-right':'-'+(fblightbox.width()/200)+'px','margin-top':'-'+(fblightbox.height()/1)+'px'});

$("#launch").click(function() {
  $('.overlay').fadeIn();
  fblightbox.fadeIn();
});
$("#close").click(function() {
  $('.overlay').fadeOut();
  fblightbox.fadeOut();
});

<%-- $(".message").click(function(){
	$(this).attr("style", "background-color:white");
	//ajax
	$.ajax({
     	url:'NoticeServlet',
     	type:"POST",
     	data:{
     		notice_id:"<%=notice.getProduct_price()%>",
     		action:"Click",
     	},
     	success:function(data){
     	}
});
	
}); --%>
//訊息的燈箱開始
var msgLightBox = $('.msgLightBox');
//var msgNotRead = $('.msgNotRead');

$('.message').click(function(){
var message = $(this);
alert($(this).children('.messageNoticeId').val());
 $.ajax({
 type: "GET",
 url:'NoticeServlet',
   data: {"action":"Click", "notice_id":$(this).children('.messageNoticeId').val()},
   dataType: "text",
   success: function (data){
	   message.attr("style", "background-color:white");
   },     
  error: function(){
  message.attr("style", "background-color:white");
  }
   
  });
});
/* function readClick(e){
	//$(this).css("background-color", "white");
	$(this).attr("style", "background-color:white");
	console.log($(this));
	alert($(this));
} */

</script>
</body>

</html>