<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.recipe.model.*"%>
<%@ page import="com.recipe_style.model.*"%>
<%@page import="com.recipe_favorite.model.RecipeFavoriteServiec"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors" %>







<%
	RecipeService recipeService = new RecipeService();
	RecipeStyleService restyleSvc = new RecipeStyleService();
	MemberService memberService = new MemberService();
	RecipeVO_saved recipeVO = (RecipeVO_saved)request.getAttribute("recipeVO");
	RecipeStyleVO recipeStyleVO = restyleSvc.getOneReStyle(recipeVO.getRcstyle_no());
	MemberVO memberVO = memberService.getOneMember(recipeVO.getMember_id());
	RecipeFavoriteServiec recipeFavoriteServiec = new RecipeFavoriteServiec();
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>詳細食譜內容</title>
<link rel="stylesheet" href="recipeCSS/listOneRecipeCssCopy.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<script src="https://cdn.zingchart.com/zingchart.min.js"></script>
<style>
* {
	outline: none;
}
</style>
</head>

<body>
	<div class="racipe-main">
		<div class="racipe-header">
			<h2><%=recipeVO.getRecipe_name()%></h2>
			<div class="racipe-header-aside">
				<span class="member-id">廚師編號:<%=recipeVO.getMember_id()%></span> <span
					class="racipe-create-time">發表時間:<%=recipeVO.getRecipe_uldate()%></span>
			</div>
		</div>
		<div class="racipe-detal">
			<div class="racipe-detal-left">
				<img src="<%=recipeVO.getRecipe_photo()%>" class="recipe-photo">
			</div>
			<div class="racipe-detal-right">
				<div class="chef-info">
					<div class="chef-info-pic">
						<img
							src="/DA106_G4_Foodporn/front-end/member/photo?member_id=<%=memberVO.getMember_id()%>"
							alt="廚師頭貼">
					</div>
					<div class="chef-info-detal">
						<h4>
							<a
								href="RecipeServlet?action=getChef_For_Display&member_id=<%=recipeVO.getMember_id()%>"><%=memberVO.getMember_name()%></a>
						</h4>
						<span><%=recipeService.getChefCookedNum(recipeVO.getMember_id())%>&nbsp;&nbsp;食譜</span>
						<span>999&nbsp;&nbsp;粉絲</span>
					</div>
					<form method="post" action="RecipeServlet">
						<button class="chef-follow" name="chef_follow">追蹤</button>
						<input type="hidden" value="<%=recipeVO.getMember_id()%>"
							name="chef_id"> <input type="hidden" value="member_id"
							name="member_id">
					</form>
				</div>
				<div class="racipe-detal-main-data">
					<ul>
						<li>烹飪時間<%="&nbsp;&nbsp;" + recipeVO.getCook_time()%>分
						</li>
						<li>食譜類型<%="&nbsp;&nbsp;" + recipeVO.getRecipe_type()%></li>
						<li>食譜風格<%="&nbsp;&nbsp;" + recipeStyleVO.getRcstyle()%>
						</li>
						<li>熱量<%="&nbsp;&nbsp;" + recipeVO.getCalo_intake()%>kcal
						</li>
						<li>收藏人數&nbsp;&nbsp;<span id="followNum"><%=recipeFavoriteServiec.getFollowedNum(recipeVO.getRecipe_id())%></span>人
						</li>
					</ul>
				</div>
				<div class="racipe-detal-button">
					<!-- 		目前採用AJAX因此先保留FORM表單的訪法	<form method="post" action="RecipeServlet" id="recipeFollow"> -->
					<div class="racipe-detal-button-div">
						<button class="recipeFollow_btn">收藏</button>
						<input type="hidden" value="<%=recipeVO.getRecipe_id()%>"
							name="recipe_id">
						<!-- 							先假裝有會員810009按讚 -->
						<!-- 							先假裝有會員810009按讚 -->
						<!-- 							先假裝有會員810009按讚 -->
						<input type="hidden" value="810009" name="member_id">
					</div>
					<!-- 					</form> -->
			<form method="post" action="ShopCartServltR">
					<input type="hidden"  name="quantity" size="3"					
						value=1> 
						<button class="recipe-purchase" name="recipe_purchase"
							title="一鍵購入料理組合包">一鍵購買</button>
						<input type="hidden" value="<%=recipeVO.getRecipe_id()%>"
							name="recipe_id">
							<%ProductService Pscv=new ProductService();
							ProductVO pvo=Pscv.getbyreceipe(recipeVO.getRecipe_id());
							int status=3;
							if(pvo!=null){
								 status=pvo.getProduct_status();
								pageContext.setAttribute("Pstatus", status);
							}
							
							%>
							<c:set var="Pstatus" value="<%=status%>"/>
					
							<input id="istatus" type="hidden" value="${Pstatus}">
							
							<script>
							if($("#istatus").val()==0){
								$(".recipe-purchase").attr("disabled", false);
								$(".recipe-purchase").html("一鍵購買");	
							}else {
								$(".recipe-purchase").attr("disabled", true);
								$(".recipe-purchase").html("料理包準備中");
								
								
															
																
							}
							
							</script>		
							
							
							 <input type="hidden" value="member_id"
							name="member_id">
							 <input
						type="hidden" name="action" value="ADDR">
					</form>
					<script>
					recipe-purchase
					
					
					</script>
					
				</div>
			</div>
			<div class="racipe-detal-description">
				<h2>食譜介紹</h2>
				<br>
				<p id="getRecipe_content"><%=recipeVO.getRecipe_content()%></p>
			</div>
		</div>
		<div class="racipe-detal-info-list">
			<div class="r-detal-info-list-container">
				<div class="r-detal-info-list-hd">
					<h3>食材</h3>
				</div>
				<div class="r-detal-info-list-descript">
					<table>
						<%
							for (int i = 0; i < recipeVO.getUnits().length; i++) {
						%>
						<tr>
							<th class="ingredient-th"><%=recipeVO.getRecipe_ingredients()[i]%></th>
							<td class="ingredient-td"><%=recipeVO.getIngredient_amount()[i]%></td>
							<td class="ingredient-td"><%=recipeVO.getUnits()[i]%></td>
						</tr>
						<%
							}
						%>
					</table>
				</div>
			</div>
			<div class="r-detal-info-list-container">
				<div class="r-detal-info-list-hd">
					<h3>烹調方式</h3>
				</div>
				<div class="r-detal-info-list-descript">
					<table>
						<%
							for (int i = 0; i < recipeVO.getRecipe_steps().length; i++) {
						%>
						<tr>
							<th>
								<h4><%=i + 1%></h4>
							</th>
							<td><%=recipeVO.getRecipe_steps()[i]%></td>
						</tr>
						<%
							}
						%>
					</table>
				</div>
			</div>
		</div>

		<div class="racipe-detal-info-list">
			<div class="r-detal-info-list-container">
				<div class="r-detal-info-list-hd">
					<h3>營養成分</h3>
				</div>
				<div class="r-detal-info-list-descript"
					id="racipe-detal-info-bottom">
					<table>
						<tr>
							<th class="intake-th">蛋白質</th>
							<td class="intake-td"><%=recipeVO.getProtein_intake()%></td>
							<td class="intake-td">公克</td>
						</tr>
						<tr>
							<th class="intake-th">脂肪</th>
							<td class="intake-td"><%=recipeVO.getFat_intake()%></td>
							<td class="intake-td">公克</td>
						</tr>
						<tr>
							<th class="intake-th">碳水化合物</th>
							<td class="intake-td"><%=recipeVO.getCarbo_intake()%></td>
							<td class="intake-td">公克</td>
						</tr>
						<tr>
							<th class="intake-th">膳食纖維</th>
							<td class="intake-td"><%=recipeVO.getVage_intake()%></td>
							<td class="intake-td">公克</td>
						</tr>
						<tr>
							<th class="intake-th">鹽攝取量</th>
							<td class="intake-td"><%=recipeVO.getSalt_intake()%></td>
							<td class="intake-td">公克</td>
						</tr>
						<tr>
							<th class="intake-th">維生素C</th>
							<td class="intake-td"><%=recipeVO.getVitamin_c()%></td>
							<td class="intake-td">毫克</td>
						</tr>
						<tr>
							<th class="intake-th">維生素B2</th>
							<td class="intake-td"><%=recipeVO.getVitamin_b()%></td>
							<td class="intake-td">豪克</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="r-detal-info-list-container">
				<div class="r-detal-info-list-hd">
					<h3>營養攝取視覺圖</h3>
				</div>
				<div class="r-detal-info-list-descript">
					<div id="myChart-container">
						<div id="myChart" class="chart--container"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var myConfig = {
			type : 'radar',
			plot : {
				aspect : 'area',
				animation : {
					effect : 3,
					sequence : 1,
					speed : 700
				}
			},
			scaleV : {
				visible : false
			},
			scaleK : {
				values : '0:5:1',
				labels : [ '蛋白質<br>' +
	<%=recipeVO.getProtein_intake()%>
		,
						'膳食纖維<br>' +
	<%=recipeVO.getVage_intake()%>
		,
						'脂肪<br>' +
	<%=recipeVO.getFat_intake()%>
		,
						'維生素B2<br>' +
	<%=recipeVO.getVitamin_b()%>
		,
						'碳水化合物<br>' +
	<%=recipeVO.getCarbo_intake()%>
		,
						'維生素C<br>' +
	<%=recipeVO.getVitamin_c()%>
		],
				item : {
					fontColor : '#607D8B',
					backgroundColor : "white",
					borderColor : "#aeaeae",
					borderWidth : 1,
					padding : '5 10',
					borderRadius : 10
				},
				refLine : {
					lineColor : '#c10000'
				},
				tick : {
					lineColor : '#59869c',
					lineWidth : 2,
					lineStyle : 'dotted',
					size : 20
				},
				guide : {
					lineColor : "#607D8B",
					lineStyle : 'solid',
					alpha : 0.3,
					backgroundColor : "#AAAAAA #DDDDDD"
				}
			},
			series : [ {
				values : [
	<%=recipeVO.getProtein_intake()%>
		,
	<%=recipeVO.getVage_intake()%>
		,
	<%=recipeVO.getFat_intake()%>
		,
	<%=recipeVO.getVitamin_b()%>
		,
	<%=recipeVO.getCarbo_intake()%>
		,
	<%=recipeVO.getVitamin_c()%>
		],

				lineColor : '#FF5757',
				backgroundColor : '#FF8888'
			} ]
		};

		zingchart.render({
			id : 'myChart',
			data : myConfig,
			height : '100%',
			width : '100%'

		});
	</script>
</body>

<!-- 停止頁面跳轉 -->
<script src="http://malsup.github.com/jquery.form.js"></script>
<script>
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
</script>
<script>
	//練習使用AJAX實現按讚功能
	$(document).ready(function() {
		$(".recipeFollow_btn").click(function() {
			// 		 debugger; debug用
			$.ajax({
				type : "GET",
				url : "ajaxResponse.do",
				data : {
					"action" : "recipe_follow",
					"recipe_id" :
						<%=recipeVO.getRecipe_id()%>,"member_id" : "810009"},
				dataType : "json",
				success : function(data) {
					$("#followNum").text(data);
				},
				error : function() {
					
				}
			})
		})
	})
</script>

</html>