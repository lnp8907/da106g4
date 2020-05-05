<%@page import="com.recipe_favorite.model.RecipeFavoriteServiec"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.recipe_style.model.RecipeStyleVO"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="com.recipe_style.model.RecipeStyleService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.recipe.model.*"%>
<%@page import="com.ingredient.model.IngredientVO"%>
<%@page import="com.ingredient.model.IngredientDAO"%>
<%@page import="java.util.List"%>

<%
	RecipeService recipeService = new RecipeService();
	MemberService memSrv = new MemberService();
	MemberVO hostVO = memSrv.getOneMember(request.getParameter("member_id"));
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	
%>
<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>

<%-- 模擬登入的hostID(直播主ID)為peter  --%>
<%
	//      String hostID = (String)session.getAttribute("hostID");
	//透過控制器得到的直播主ID
	String hostID = null;

	if (memberVO.getMember_status() == 1) {
		hostID = memberVO.getNickname();
		session.setAttribute("hostID", hostID);
	}
	if(hostID == null){
		hostID = request.getParameter("hostId");
	}
%>
<%-- 模擬登入的clientID(觀眾ID)為Anonymous  --%>
<%!int count = 0;%>
<%
	if (memberVO.getMember_status() != 1) {
		String clientID = memberVO.getNickname();
		if (clientID == null)
			clientID = "Anonymous" + (++count);
		session.setAttribute("clientID", clientID);

		System.out.println("--------------------------------------------");
		System.out.println(clientID);
		System.out.println("--------------------------------------------");
	}
%>




<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=3.0">
<title>Foodporn</title>
<link rel="stylesheet"
	href="../recipe/recipeCSS/listOneRecipeCssCopy.css">
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
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/webrtc/DetectRTC.js"></script>
<script src="js/webrtc/socket.io.js"> </script>
<script src="js/webrtc/adapter-latest.js"></script>
<script src="js/webrtc/IceServersHandler.js"></script>
<script src="js/webrtc/CodecsHandler.js"></script>
<script src="js/webrtc/RTCPeerConnection-v1.5.js"></script>
<script src="js/webrtc/broadcast.js"></script>

<script>
	// 	$(document).ready(function() {
	// 		getLatest();
	// 		getMostPopular();
	// 		getCourse();
	// 		getMonth();
	// 	})
</script>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "微軟正黑體", Georgia, 'Times New Roman', Times, serif;
	font-size: 14px;
}

.footer-bg {
	margin-top: 10px;
	background-color: white;
}

#livestream_section {
	width: 100%;
	margin: -10px auto;
	height: 850px;
	background-color: #f1f1f1;
	padding: 25px;
}

#livestream_container {
	margin: 10px auto;
	width: 75%;
	height: 100%;
}

.livestream_card {
	display: inline-block;
}

#livestream_left {
	width: 68.5%;
	height: 100%;
	display: inline-block;
	margin-right: 15px;
}

#livestream_right {
	display: inline-block;
	width: 29%;
	height: 100%;
	vertical-align: top;
}

#video {
	width: 98%;
	margin: 0 auto;
	height: 65%;
	background-color: white;
	border-radius: 8px;
}

#hot {
	width: 98%;
	height: 31%;
	margin: 0 auto;
	background-color: white;
	border-radius: 8px;
	margin-top: 30px;
}

#hot h2 {
	font-size: 26px;
	padding-left: 20px;
	padding-top: 5px;
}

.hot-card {
	display: inline-block;
	width: 21%;
	margin: 10px 1.5% 0;
	text-align: center;
	background-color: antiquewhite;
	height: 75%;
	border-radius: 30px;
	position: relitive;
	overflow: hidden;
	transition-duration: 500ms;
}

.hot-card:hover {
	transform: scale(1.1)
}

.hot-card img {
	width: 100%;
	height: 100%;
}

#host-info {
	height: 15%;
	background-color: white;
	border-radius: 8px;
	padding: 12px;
}

#chat-room {
	height: 59.5%;
	background-color: white;
	border-radius: 8px;
	margin-top: 20px;
	padding: 8px;
}

#dona {
	height: 20%;
	background-color: white;
	border-radius: 8px;
	margin-top: 20px;
	padding: 12px;
	color: black;
}

.hot-member-name {
	left: -50px;
	position: relative;
	bottom: 37px;
	font-weight: 800;
	font-size: 16px;
	color: white;
}

.chat-room-title {
	margin: 10px 20px;
	display: inline-block;
	font-size: 16px;
	color: black;
	font-weight: 600;
}

#messagesArea {
	display: block;
	width: 95%;
	margin: auto;
	height: 75%;
	border: none;
	resize: none
}

#message {
	display: block;
	width: 95%;
	margin: auto;
	margin-top: 12px;
	height: 45px;
	border-radius: 5px;
	border: 1px solid #999;
	padding-left: 15px;
	background-color: #eeeeee29;
}

.dona-items-card img {
	width: 100%;
}

.dona-items-card {
	display: inline-block;
	width: 29%;
	margin-left: 2%;
	margin-top: 10px;
}

.dona-items-card:hover {
	cursor: pointer;
}

#charge {
	display: inline-block;
	margin-left: 47%;
	width: 50px;
	height: 30px;
	text-align: center;
	line-height: 30px;
	border-radius: 5px;
	font-size: 15px;
	background-color: aliceblue;
	box-shadow: 3px 2px 2px #00000036;
}

#charge:hover {
	cursor: pointer;
}
</style>
</head>

<body onload="connect();">
	<header>
		<div id="top-logo" class="logo">
			<a href="<%=request.getContextPath()%>/" title="回首頁"><img
				class="logo-photo"
				src="<%=request.getContextPath()%>/image/FoodPron_Logo.png"
				alt="logo"></a>
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
			<section id="livestream_section">
				<div id="livestream_container">
					<div id="livestream_left">
						<div class="livestream_card" id="video">
						<div class="CustomCard hoverCustomCard">
							<div id="CustomCardheader"
								class="CustomCardheader text-white btn-warning">
								<input type="hidden" id="hostID" value="${hostID}" /> <input
									type="hidden" id="lsViewNum" value="0" />
								<%
									if (hostID != null) {
								%>
								<h5 id="subTitle" class="col pt-2">
									<strong>這是 ${hostID} 直播間</strong>
								</h5>
								<%
									} else {
								%>
								<h5 id="subTitle" class="col pt-2">
									<strong>您已經進入直播間</strong>
								</h5>
								<%
									}
								%>

								<i id="WebSocket-count" class="far pt-2 pr-3 float-right"
									style="position: absolute; right: 0; top: 0px">目前在線人數 - </i> <i
									id="WebRTC-count" class="far pt-2 pr-3 float-right"
									style="position: absolute; right: 0; top: 20px">WebRTC
									累計觀看人數 0 </i>
							</div>

							<div class="bottom mx-auto">
								<button class="btn-3d-can" id="record" style="display: none"
									disabled="disabled">開始錄影</button>
								<button class="btn-3d-can" id="download" style="display: none"
									disabled="disabled">儲存錄影</button>
								<button id="play" class="btn btn-success" style="display: none">播放</button>
								<div style="display: none">
									<p>
										Echo cancellation: <input type="checkbox"
											id="echoCancellation">
									</p>
									<div id="errorMsg"></div>
								</div>
							</div>
						</div>
						

							<section id="session1" class="experiment">
								<section <%=(hostID == null) ? "style='visibility: hidden;'" : ""%>>
									<select id="broadcasting-option" class="broadcasting-option">
										<option>Audio + Video</option>
										<option>Only Audio</option>
									</select> <input type="text" id="broadcast-name" class="broadcast-name"
										value="${hostID}">
									<button id="setup-new-broadcast">啟動新視頻</button>
								</section>
								<!-- list of all available broadcasting rooms -->
								<br>
								<table style="width: 100%;" id="rooms-list"></table>
								<!-- local/remote videos container -->
								<div id="videos-container"></div>
								<div class="visible">
									<div style="text-align: center;">
										<h2>
											<code>
												<strong id="unique-token">#123456789</strong>
											</code>
										</h2>
									</div>
								</div>
							</section>

							<script>
			 var visibleElements = document.getElementsByClassName('visible'),
             length = visibleElements.length;
             for (var i = 0; i < length; i++) {
                visibleElements[i].style.display = 'none';
             }
			</script>




						</div>
						<!-- 直播播放區結束 -->
						<div class="livestream_card" id="hot">
							<h2>熱門推薦</h2>
							<a href="#"><span class="hot-card"><img alt=""
									src="../image/TYPE ICON/5.png"> <span
									class="hot-member-name">AAAA</span> </span></a> <a href="#"><span
								class="hot-card"> <img alt=""
									src="../image/TYPE ICON/5.png"> <span
									class="hot-member-name">AAAA</span>
							</span></a> <a href="#"><span class="hot-card"> <img alt=""
									src="../image/TYPE ICON/5.png"> <span
									class="hot-member-name">AAAA</span>
							</span></a> <a href="#"><span class="hot-card"> <img alt=""
									src="../image/TYPE ICON/5.png"> <span
									class="hot-member-name">AAAA</span>
							</span></a>
						</div>
					</div>

					<div id="livestream_right">
						<div id="host-info">
							<div class="chef-info-pic">
								<img
									src="<%=request.getContextPath()%>/front-end/member/photo?member_id=<%=hostVO.getMember_id()%>"
									alt="廚師頭貼">
							</div>
							<div class="chef-info-detal">
								<h4>
									<a
										href="RecipeServlet?action=getChef_For_Display&member_id=<%=hostVO.getMember_id()%>"><%=hostVO.getMember_name()%></a>
								</h4>
								<span><%=recipeService.getChefCookedNum(hostVO.getMember_id())%>&nbsp;&nbsp;食譜</span>
								<span>999&nbsp;&nbsp;粉絲</span>
							</div>
							<form method="post" action="RecipeServlet">
								<button class="chef-follow" name="chef_follow" style="display:<%=memberVO == null ? "none" :""%>">追蹤</button>
								<input type="hidden" value="${hostID}" name="chef_id"> <input
									type="hidden" value="<%=memberVO == null ? "" :memberVO.getMember_id()%>"
									name="member_id">
							</form>
						</div>
						<div id="chat-room">
							<span class="chat-room-title">留言</span>
							<textarea id="messagesArea" class="panel message-area" readonly
								style="display: block"></textarea>
							<div class="panel input-area">
								<div id="webSocket-submit" class="g1">
									<input id="message" class="panel input-default" type="text"
										placeholder="留點訊息給廚師吧..."
										onkeydown="if (event.keyCode == 13) sendMessage();" /><br>
									<!-- 動態抓會員ID -->
									<input type="submit" id="sendMessage" class="btn btn-danger" value="送出訊息" onclick="sendMessage();" />
				
									<input id="userName" class="panel input-default" type="hidden"
										placeholder="暱稱" value="${(hostID!=null)? hostID :clientID}"
										readonly="readonly" />
									<!-- 					    <input type="submit" id="sendMessage" class="btn btn-danger" value="送出訊息" onclick="sendMessage();" /> -->
								</div>
							</div>
						</div>
						<div id="dona">
							<h3>
								我的富胖幣:<span style="color: red;">&nbsp;<%=memberVO == null ? "" : memberVO.getBalance()%></span>
								<span id="charge">儲值</span>
							</h3>
							<span class="dona-items-card"><img alt=""
								src="../image/TYPE ICON/6.png"></span> <span
								class="dona-items-card"><img alt=""
								src="../image/TYPE ICON/6.png"></span> <span
								class="dona-items-card"><img alt=""
								src="../image/TYPE ICON/6.png"></span>
						</div>
					</div>
				</div>
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
                var config = {
                    openSocket: function(config) {
//                      var SIGNALING_SERVER = 'https://socketio-over-nodejs2.herokuapp.com:443/';
//                         var SIGNALING_SERVER = 'https://54.148.206.111:9001/';
							var SIGNALING_SERVER = 'https://35.229.239.13:9001/';
                     config.channel = config.channel || location.href.replace(/\/|:|#|%|\.|\[|\]/g, '');
                        var sender = Math.round(Math.random() * 999999999) + 999999999;
// 						var sender = 123456789;
						console.log(config.channel);
                        io.connect(SIGNALING_SERVER).emit('new-channel', {
                            channel: config.channel,
                            sender: sender
                        });

                        var socket = io.connect(SIGNALING_SERVER + config.channel);
                        socket.channel = config.channel;
                        socket.on('connect', function () {
                            if (config.callback) config.callback(socket);
                        });

                        socket.send = function (message) {
                            socket.emit('message', {
                                sender: sender,
                                data: message
                            });
                        };

                        socket.on('message', config.onmessage);
                    },
                    onRemoteStream: function(htmlElement) {
                        videosContainer.appendChild(htmlElement);
                        rotateInCircle(htmlElement);
                    },
                    onRoomFound: function(room) {
//                         document.getElementById("subTitle").innerHTML = "<strong>您正準備觀看 " + room.roomName + " 的直播</strong>";
//                         var alreadyExist = document.querySelector('button[data-broadcaster="' + room.broadcaster + '"]');
//                         if (alreadyExist) return;

                        if (typeof roomsList === 'undefined') roomsList = document.body;

                        var tr = document.createElement('tr');
                        tr.innerHTML = '<td><strong>' + room.roomName + '</strong> is broadcasting his media!</td>' +
                            '<td><button class="join">&nbsp;Join&nbsp;</button></td>';
                        roomsList.appendChild(tr);
 
                        var joinRoomButton = tr.querySelector('.join');
                        joinRoomButton.setAttribute('data-broadcaster', room.broadcaster);
                        joinRoomButton.setAttribute('data-roomToken', room.broadcaster);
                        joinRoomButton.onclick = function() {
                        	document.getElementById("CustomCardheader").className = "CustomCardheader text-white btn-success";
                        	document.getElementById("webSocket-submit").className = "g2";
                        	document.getElementById("subTitle").innerHTML = "<strong2>您正在觀看 " + room.roomName + " 的直播</strong2>";
                        	document.getElementById('broadcast-name').value = room.roomName;
                            videosContainer.className = "videosContainer";                   	
                        	document.getElementById("slider").src = "images/tenor.gif";
                        	document.getElementById("WebRTC-count").innerHTML = "";
                            this.disabled = true;
//                          this.style.display = 'none';
                            
                            var broadcaster = this.getAttribute('data-broadcaster');
                            var roomToken = this.getAttribute('data-roomToken');
                            broadcastUI.joinRoom({
                                roomToken: roomToken,
                                joinUser: broadcaster
                            });
                            hideUnnecessaryStuff2();
                        };
                    },
                    onNewParticipant: function(numberOfViewers) {
                        document.title = 'Viewers: ' + numberOfViewers;
                        document.getElementById("WebRTC-count").innerHTML  = "WebRTC 累計觀看人數"+" "+numberOfViewers;
                        document.getElementById("lsViewNum").value  = numberOfViewers;
                    },
                    onReady: function() {
                        console.log('now you can open or join rooms');
                    }
                };

                function setupNewBroadcastButtonClickHandler() {
                	document.getElementById("subTitle").innerHTML = "<strong>${hostID}: 您正在直播</strong>"
                    $('#record').show();
  	                $('#download').show();
                    DetectRTC.load(function() {
                        captureUserMedia(function() {
                            var shared = 'video';
                            if (window.option == 'Only Audio') {
                                shared = 'audio';
                            }
                            if (window.option == 'Screen') {
                                shared = 'screen';
                            }

                            broadcastUI.createRoom({
                                roomName: (document.getElementById('broadcast-name') || { }).value || 'Anonymous',
                                isAudio: shared === 'audio'
                            });
                        });
                        hideUnnecessaryStuff();
                    });
                }

                function captureUserMedia(callback) {
                    var constraints = null;
                    window.option = broadcastingOption ? broadcastingOption.value : '';
                    if (option === 'Only Audio') {
                        constraints = {
                            audio: true,
                            video: false
                        };

                        if(DetectRTC.hasMicrophone !== true) {
                            alert('DetectRTC library is unable to find microphone; maybe you denied microphone access once and it is still denied or maybe microphone device is not attached to your system or another app is using same microphone.');
                        }
                    }
                    if (option === 'Screen') {
                        var video_constraints = {
                            mandatory: {
                                chromeMediaSource: 'screen'
                            },
                            optional: []
                        };
                        constraints = {
                            audio: false,
                            video: video_constraints
                        };

                        if(DetectRTC.isScreenCapturingSupported !== true) {
                           alert('DetectRTC library is unable to find screen capturing support. You MUST run chrome with command line flag "chrome --enable-usermedia-screen-capturing"');
                        }
                    }

                    if (option != 'Only Audio' && option != 'Screen' && DetectRTC.hasWebcam !== true) {
                        alert('DetectRTC library is unable to find webcam; maybe you denied webcam access once and it is still denied or maybe webcam device is not attached to your system or another app is using same webcam.');
                    }

                    var htmlElement = document.createElement(option === 'Only Audio' ? 'audio' : 'video');

                    htmlElement.muted = true;
                    htmlElement.volume = 0;

                    try {
                        htmlElement.setAttributeNode(document.createAttribute('autoplay'));
                        htmlElement.setAttributeNode(document.createAttribute('playsinline'));
                        htmlElement.setAttributeNode(document.createAttribute('controls'));
                    } catch (e) {
                        htmlElement.setAttribute('autoplay', true);
                        htmlElement.setAttribute('playsinline', true);
                        htmlElement.setAttribute('controls', true);
                    }
var mediaElement = getHTMLMediaElement(htmlElement, {
buttons: ['record-video']
});
                    var mediaConfig = {
                        video: htmlElement,
                        onsuccess: function(stream) {
                            config.attachStream = stream;
                            
                            videosContainer.appendChild(mediaElement);
                            rotateInCircle(htmlElement);
                            
                            callback && callback();
                        },
                        onerror: function() {
                            if (option === 'Only Audio') alert('unable to get access to your microphone');
                            else if (option === 'Screen') {
                                if (location.protocol === 'http:') alert('Please test this WebRTC experiment on HTTPS.');
                                else alert('Screen capturing is either denied or not supported. Are you enabled flag: "Enable screen capture support in getUserMedia"?');
                            } else alert('unable to get access to your webcam');
                        }
                    };
                    if (constraints) mediaConfig.constraints = constraints;
                    getUserMedia(mediaConfig);
                }

                var broadcastUI = broadcast(config);

                /* UI specific */
                var videosContainer = document.getElementById('videos-container') || document.body;
                var setupNewBroadcast = document.getElementById('setup-new-broadcast');
                var roomsList = document.getElementById('rooms-list');

                var broadcastingOption = document.getElementById('broadcasting-option');

                if (setupNewBroadcast) setupNewBroadcast.onclick = setupNewBroadcastButtonClickHandler;

                function hideUnnecessaryStuff() {
                	

                    var visibleElements = document.getElementsByClassName('visible'),
                        length = visibleElements.length;
                    for (var i = 0; i < length; i++) {
                        visibleElements[i].style.display = 'inline';
                    }
                }
                
                function hideUnnecessaryStuff2() {
                	connect();
                }

                function rotateInCircle(video) {
                    video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(0deg)';
                    setTimeout(function() {
                        video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(360deg)';
                    }, 1000);
  	                document.querySelector('button#record').disabled = false;
                	document.getElementById("session1").className = "experiment2";
                 }
                (function() {
                    var uniqueToken = document.getElementById('unique-token');
                    if (uniqueToken)
                        if (location.hash.length > 2) uniqueToken.parentNode.parentNode.parentNode.innerHTML = '<div class="share"><h2>&nbsp;<i class="fa fa-hand-o-right fa-2x"></i><a href="' + location.href + '" target="_blank"><b>由此分享此直播間的鏈接 </b></a></h2></div>';
                        else uniqueToken.innerHTML = uniqueToken.parentNode.parentNode.href = '#' + (Math.random() * new Date().getTime()).toString(36).toUpperCase().replace( /\./g , '-');
               			console.log(uniqueToken.parentNode.parentNode.href);
                })();
                
                
                

            </script>

<!-- =============================================以下為錄製、下載、與上傳============================================= -->
<script>
        'use strict';
        const mediaSource = new MediaSource();
        mediaSource.addEventListener('sourceopen', handleSourceOpen, false);
        let mediaRecorder;
        let recordedBlobs;  //錄製成功的Blob
        let sourceBuffer;

        const errorMsgElement = document.querySelector('span#errorMsg'); 
        const recordedVideo = document.querySelector('video#recorded');
        const recordButton = document.querySelector('button#record');
        recordButton.addEventListener('click', () => {
          if (recordButton.textContent === '開始錄影') {
adjustControls();
volumeControl.style.opacity = 1;
recordVideo.className = 'control record-video';
            startRecording();
          } else {
recordVideo.className = recordVideo.className.replace('record-video', 'stop-recording-video selected');
            stopRecording();
            recordButton.textContent = '開始錄影';
volumeControl.style.opacity = 1;
            playButton.disabled = false;
            //downloadButton.disabled = false;
          }
        });
 
        const playButton = document.querySelector('button#play');
        playButton.addEventListener('click', () => {
          const superBuffer = new Blob(recordedBlobs, {type: 'video/webm'});
          recordedVideo.src = null;
          recordedVideo.srcObject = null;
          recordedVideo.src = window.URL.createObjectURL(superBuffer);
          recordedVideo.controls = true;
          recordedVideo.play();
        });

        const downloadButton = document.querySelector('button#download');
        downloadButton.addEventListener('click', () => {
              document.querySelector('button#record').disabled = false;
              document.querySelector('button#download').disabled = true;
              const blob = new Blob(recordedBlobs, {type: 'video/webm'});	 
        	  var xhr = new XMLHttpRequest();
         	  xhr.open('POST', '<%=request.getContextPath()%>/Update_StreamServlet', true);
        	  xhr.onload = function(e) { console.log("loaded"); };
        	  xhr.onreadystatechange = function(){
        	      console.log("state: " + xhr.readyState);
        	  };
        	  // Listen to the upload progress.
        	  xhr.upload.onprogress = function(e) { console.log("uploading..."); };
        	  xhr.setRequestHeader("Content-Type", "video/webm");
        	  xhr.send(blob);
        	  swal(
            		  '你已儲存影片！',
            		  '可以去直播管理 listAllStream.jsp 確認',
            		  'success'
            	  )
volumeControl.style.opacity = 0;        
        });

        function handleSourceOpen(event) {
          console.log('MediaSource opened');
          sourceBuffer = mediaSource.addSourceBuffer('video/webm; codecs="vp8"');
          console.log('Source buffer: ', sourceBuffer);
        }

        function handleDataAvailable(event) {
          if (event.data && event.data.size > 0) {
            recordedBlobs.push(event.data);
          }
        }

        function startRecording() {
          recordedBlobs = [];
          let options = {mimeType: 'video/webm;codecs=vp9'};
          if (!MediaRecorder.isTypeSupported(options.mimeType)) {
            console.error(`${options.mimeType} is not Supported`);
            errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
            options = {mimeType: 'video/webm;codecs=vp8'};
            if (!MediaRecorder.isTypeSupported(options.mimeType)) {
              console.error(`${options.mimeType} is not Supported`);
              errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
              options = {mimeType: 'video/webm'};
              if (!MediaRecorder.isTypeSupported(options.mimeType)) {
                console.error(`${options.mimeType} is not Supported`);
                errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
                options = {mimeType: ''};
              }
            }
          }

          try {
            mediaRecorder = new MediaRecorder(window.stream, options);
          } catch (e) {
            console.error('Exception while creating MediaRecorder:', e);
            errorMsgElement.innerHTML = `Exception while creating MediaRecorder: ${JSON.stringify(e)}`;
            return;
          }

          console.log('Created MediaRecorder', mediaRecorder, 'with options', options);
          recordButton.textContent = '結束錄影';
          playButton.disabled = true;
          downloadButton.disabled = true;
          mediaRecorder.onstop = (event) => {
            console.log('Recorder stopped: ', event);
          };
          mediaRecorder.ondataavailable = handleDataAvailable;
          mediaRecorder.start(10); // collect 10ms of data
          console.log('MediaRecorder started', mediaRecorder);
        }
        function stopRecording() {
          mediaRecorder.stop();
          console.log('Recorded Blobs: ', recordedBlobs);

        	 $.ajax({
        		 type: "POST",
        		 url: "<%=request.getContextPath()%>/InsertOrDelete_StreamServlet",
        		 data: creatQueryString($(this).val(), ""),
        		 dataType: "json",
        		 success: function (data){
        			aelrt("成功送資料庫囉");
        	     },
                 error: function(){
                	    swal(
                		  '您已完成錄影',
                		  '記得要按儲存影片',
                		  'success'
                		);
                	    downloadButton.disabled = false;
volumeControl.style.opacity = 1;
recordVideo.className = recordVideo.className.replace('stop-recording-video selected', 'stop-recording-video2 selected');
                 }
             })
             
             function creatQueryString(paramGrade, paramClass){
                    document.querySelector('button#record').disabled = true;       		 	
        		    var hostID=$("#hostID").val();
        		 	var lsViewNum=$("#lsViewNum").val();
        			var queryString= {"action":"insert", "hostID":hostID, "lsViewNum":lsViewNum};
        			return queryString;
        	 }

        }

        function handleSuccess(stream) {
          recordButton.disabled = false;
          downloadButton.disabled = true;
          console.log('getUserMedia() got stream:', stream);
          window.stream = stream;

        }

        async function init(constraints) {
            const stream = await navigator.mediaDevices.getUserMedia(constraints);
            handleSuccess(stream);
        }

        document.querySelector('#setup-new-broadcast').addEventListener('click', async () => {
          const hasEchoCancellation = document.querySelector('#echoCancellation').checked;
          const constraints = {
            audio: {
              echoCancellation: {exact: hasEchoCancellation}
            },
            video: {
              width: 1280, height: 720
            }
          };
          console.log('Using media constraints:', constraints);
          await init(constraints);
        });
</script>

<!-- =============================================以下為webSocket聊天室============================================= -->
<script>
    
    var MyPoint = "/MyEchoServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
<%--     var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;   http上線請使用https , webSocket請使用wss --%>
var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;
// var endPointURL = "wss://da106g4.tk/RTCPeerConnection_Ver3/MyEchoServer";
console.log(endPointURL);
	var webSocket;
	
	function connect() {
// 		var rtcroomName = document.getElementById('broadcast-name').value;
		var rtcroomName = '<%=hostID%>';

		alert(rtcroomName);
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL+"/"+rtcroomName);
		document.getElementById('broadcasting-option').style.display = 'none';
        document.getElementById('broadcast-name').style.display = 'none';
        document.getElementById('setup-new-broadcast').style.display = 'none';
		webSocket.onopen = function(event) {
			document.getElementById('sendMessage').disabled = false;
		};

		webSocket.onmessage = function(event) {
		  if (event.data.indexOf('count=') == 0) {
			document.getElementById("WebSocket-count").innerHTML  = "目前在線人數"+" "+event.data.substring(6);
		  } else {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        var showCount = jsonObj.showCount;
	        messagesArea.value = messagesArea.value + message;
	        messagesArea.scrollTop = messagesArea.scrollHeight;
		  }
		};

		webSocket.onclose = function(event) {
			var hostID = document.getElementById("messagesArea");
		     var jsonObj = {"hostID" : userName, "message" : message};
		        webSocket.send(JSON.stringify(jsonObj));
		};
	}

	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendMessage() {
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"type":"sendText","userName" : document.getElementById("userName").value, "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
	}
    
</script>
</html>
