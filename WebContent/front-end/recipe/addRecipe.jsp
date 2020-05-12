<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.recipe.model.*"%>
<%@page import="com.ingredient.model.IngredientVO"%>
<%@page import="com.ingredient.model.IngredientDAO"%>
<%@page import="java.util.List"%>
<%
	RecipeVO_saved recipeVO = (RecipeVO_saved) request.getAttribute("recipeVO");
String member_id =(String) session.getAttribute("member_id");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=3.0">
<title>Foodporn</title>
<link rel="stylesheet" href="recipeCSS/recipe.css">
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

</style>
</head>

<body>
	<header>
		<div id="top-logo" class="logo">
<a href="<%=request.getContextPath()%>/" title="回首頁"><img class="logo-photo"
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
			<div id="insert-recipe-container">
		<form action="RecipeServlet" method="post" class="insert-recipe-main"
			enctype="multipart/form-data">
			<div class="insert-recipe-main-form">
				<h2 class="recipe-title">
					<textarea name="recipe_name" rows="1" placeholder="請填寫食譜名稱"><%=(recipeVO == null) ? "" : recipeVO.getRecipe_name()%></textarea>
				</h2>
				<div class="recipe-aside">
<!-- 					<div class="member_id-container"> -->
<!-- 						<h3>會員編號</h3> -->
<!-- 					</div> -->
						<input type="hidden" name="member_id" placeholder="請輸入廚師編號" value="<%=(recipeVO == null) ? "810003" : member_id%>">
					<h3>食譜圖片</h3>
					<figure class="recipe-aside-image">
						<img
							src="<%=(recipeVO == null || recipeVO.getTempPhto() == "")
					? "../../image/icon/uploadPic.png"
					: recipeVO.getRecipe_photo()%>"
							id="uploadFile">
						<input id="imageFile" type="file" name="recipe_photo">
						<input id="temp_recipe_photo" type="hidden"
							name="temp_recipe_photo"
							value="<%=(recipeVO == null) ? "" : recipeVO.getTempPhto()%>">
					</figure>

					<div class="recipe-description">
						<h3>簡介</h3>
						<textarea name="recipe_content" id="content" cols="30" rows="10"
							placeholder="請輸入..."><%=(recipeVO == null) ? "" : recipeVO.getRecipe_content()%></textarea>
					</div>
				</div>
				<!-- end of recipe-aside -->
				<div class="edit-recipe-aside">
					<div class="recipe-info">
						<div class="recipe-type">
							<h3>食譜類型</h3>
							<select name="recipe_type">
								<%
									String[] type = {"請選擇", "其他", "沙拉", "火鍋", "點心", "素食", "蒸", "炒", "烤", "炸", "煮", "滷", "燉", "煎"};
									for (int i = 0; i < type.length; i++) {
										if (((recipeVO == null) ? "請選擇" : recipeVO.getRecipe_type()).equals(type[i])) {
								%>
								<option value=<%=type[i]%> selected><%=type[i]%></option>
								<%
									} else {
								%>
								<option value=<%=type[i]%>><%=type[i]%></option>
								<%
									}
									}
								%>
							</select>
						</div>
						<div class="recipe-style">
							<h3>食譜風格</h3>
							<select name="rcstyle_no">
								<jsp:useBean id="rcstyleSvc" scope="page"
									class="com.recipe_style.model.RecipeStyleService" />
								<option value="">請選擇</option>
								<c:forEach var="recipeStyleVO" items="${rcstyleSvc.all}">
									<option value="${recipeStyleVO.rcstyle_no}"
										${(recipeVO.rcstyle_no==recipeStyleVO.rcstyle_no)? 'selected':'' }>${recipeStyleVO.rcstyle}
									</option>
								</c:forEach>
							</select>


						</div>
						<div class="cook-time">
							<h3>烹飪時間(分鐘)</h3>
							<select name="cook_time" class="cook-time-option">
								<option value="0">請選擇</option>
								<%
									Integer[] time = {10, 20, 25, 30, 35, 40, 45, 50, 55, 60, 90};
									for (int i = 0; i < time.length; i++) {
										if (((recipeVO == null) ? 0 : recipeVO.getCook_time()) == time[i]) {
								%>
								<option value=<%=time[i]%> selected><%=time[i]%></option>

								<%
									} else {
								%>
								<option value=<%=time[i]%>><%=time[i]%></option>
								<%
									}
									}
								%>
							</select>


						</div>
					</div>
					<!-- end of recipe-info-->
					<div class="ingredient-group">
						<h3>食材</h3>
						<%
							String[] units = {"選擇單位", "0.01,公克", "2,杯", "0.15,大匙", "0.05,茶匙", "0.025,適量", "0.0125,少許"};
							String[] names = {"選擇單位", "公克", "杯", "大匙", "茶匙", "適量", "少許"};
							for (int i = 0; i < (recipeVO == null ? 3 : recipeVO.getRecipe_ingredients().length); i++) {
						%>
						<div class="ingredient-group-row">
							<input class="recipe-ingredient-row-name tagsess"
								name="recipe_ingredient" placeholder="食材"
								value="<%=recipeVO == null ? "" : recipeVO.getRecipe_ingredients()[i]%>"><span
								class="recipe-ingredient-row-quantity"> <input
								type="text" placeholder="量" name="ingredient_amount"
								value="<%=recipeVO == null ? "" : recipeVO.getIngredient_amount()[i]%>">
								<select name="unit">
									<%
										for (int j = 0; j < units.length; j++) {
												if (((recipeVO == null) ? "選擇單位" : recipeVO.getUnits()[i]).equals(units[j])) {
									%>
									<option value=<%=units[j]%> selected>
										<%=names[j]%>
									</option>
									<%
										} else {
									%>
									<option value=<%=units[j]%>>
										<%=names[j]%>
									</option>
									<%
										}
											}
									%>
							</select>
							</span> <span class="recipe-ingredient-delete"></span>
						</div>
						<%
							}
						%>
						<span class="recipe-ingredient-add">加入食材</span>
					</div>
					<!-- end of ingredient-group -->
				</div>
				<!-- end of edit-recipe-aside -->
				<div class="reccipe-step">
					<h3>步驟</h3>
					<%
						for (int i = 0; i < (recipeVO == null ? 3 : recipeVO.getRecipe_steps().length); i++) {
					%>
					<div class="reccipe-step-item">
						<div class="reccipe-step-item-header">
							<h4><%=i + 1%></h4>
							<button class="reccipe-step-item-add">+</button>
							<button class="reccipe-step-item-remove">-</button>
						</div>
						<textarea name="recipe_step"
							class="reccipe-step-item-content-textarea" placeholder="請輸入步驟..."><%=(recipeVO == null) ? "" : recipeVO.getRecipe_steps()[i]%></textarea>
					</div>

					<%
						}
					%>
				</div>
			</div>
			<!-- end of insert-recipe-main-form-->
			<div class="navi">
				<input type="hidden" name="pageType" value="addRecipe.jsp">
				<button class="navi-button" id="insert" type="submit" name="action"
					value="insert">新增</button>
				<button class="navi-button" id="reset" type="reset">清空</button>
				<input type="button" class="navi-button"
					onclick="location.href='select_page.jsp'" value="取消">
			</div>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
			<div class="errorMess">
					<font>請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
			</div>
				</c:if>
				<%-- 				<span><%=recipeVO == null%><br> //recipeVO==空值嗎? <br><%=recipeSaved == null%><br> --%>
		</form>
	</div>
	<!-- end of insert-recipe-container-->



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
	<script>
		//reset
		$("#reset").click(function() {
			$("#temp_recipe_photo").val("");
		})

		//新增食材
		$(".ingredient-group")
				.on(
						"click",
						".recipe-ingredient-add",
						function() {
							$(".ingredient-group>div:last")
									.after(
											"<div class='ingredient-group-row'><input name='recipe_ingredient' class='recipe-ingredient-row-name tagsess' placeholder='食材'><span class='recipe-ingredient-row-quantity'><input type='text' placeholder='量' name='ingredient_amount'><select name='unit'><option value=''>選擇單位</option><option value='0.01,公克'>公克</option><option value='2,杯'>杯</option><option value='0.15,大匙'>大匙</option><option value='0.05,茶匙'>茶匙</option><option value='0.025,適量'>適量</option><option value='0.0125,少許'>少許</option></select></span><span class='recipe-ingredient-delete'></span></div>");
						});					

		//移除食材
		$('.ingredient-group').on(
				"click",
				".recipe-ingredient-delete",
				function() {
					var a = $(".ingredient-group")
							.find(".ingredient-group-row").length;
					if (a == 1) {
						alert('先生們,女士們:這已經是最後一個拉><"');
						return;
					} else {
						$(this).parent(".ingredient-group-row").remove();
					}
				});

		//新增步驟

		$(".reccipe-step")
				.on(
						"click",
						".reccipe-step-item-add",
						function() {
							event.preventDefault();
							$(this)
									.parents(".reccipe-step-item")
									.after(
											"<div class='reccipe-step-item'><div class='reccipe-step-item-header'><h4></h4><button class='reccipe-step-item-add'>+</button><button class='reccipe-step-item-remove'>-</button></div><textarea name='recipe_step' class='reccipe-step-item-content-textarea' placeholder='請輸入步驟...'></textarea></div>");
							$(".reccipe-step-item-header h4").each(
									function(i, e) {
										$(e).text(i + 1);
									});
						});

		//移除步驟
		$('.reccipe-step').on("click", ".reccipe-step-item-remove", function() {
			event.preventDefault()
			var a = $(".reccipe-step").find("h4").length;
			if (window.confirm("你確定要刪除嗎?") && (a > 1)) {
				var a = $(".reccipe-step").find("h4");
				$(this).parents(".reccipe-step-item").remove();
				//遍尋:針對每個h4做動作,JQUERY P.48
				$(".reccipe-step-item-header h4").each(function(i, e) {
					$(e).text(i + 1);
				})

			} else if (a == 1) {
				alert('先生們,女士們:這已經是最後一個拉><"');
			} else {
				return;
			}
		});

		//點擊圖片觸發檔案上傳
		$("#uploadFile").click(function() {
			$("#imageFile").click();
		});

		//實現圖片預覽
		$("#imageFile").change(function() {
			readURL(this);
		});
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#uploadFile").attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}

		//清除預覽圖片
		$("#reset").click(function() {
			$("#uploadFile").attr('src', '../../image/icon/uploadPic.png');
		});
	</script>

<script>
var availableTags = [
	<%IngredientDAO inDao = new IngredientDAO();
				List<IngredientVO> list = inDao.getAll();

				for (IngredientVO ingredient : list) {

					out.print("'" + ingredient.getIngredient_name() + "',");

				}%>

	];
$(function() {
     $( ".tagsess" ).autocomplete({ source: availableTags }); });
     
$(".ingredient-group")
.on(
		"focus",
		".tagsess",
		function() {
		     $( ".tagsess" ).autocomplete({ source: availableTags });});
   
</script>
	
	
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
</body>

</html>