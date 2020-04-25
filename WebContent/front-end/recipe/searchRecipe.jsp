<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.recipe.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
	@SuppressWarnings("unchecked")
	List<RecipeVO_saved> list = (List<RecipeVO_saved>) session.getAttribute("list");	
	pageContext.setAttribute("list", list);
	String keyword = (String) request.getAttribute("keyword");
	String selectedType = (String) request.getAttribute("selectedType");
	String selectedValue = (String) request.getAttribute("selectedValue");
%>

<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>食譜搜尋結果</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="recipeCSS/recipeList.css">
<!-- <link rel="stylesheet" href="recipeCSS/searchRecipeCSS.css"> -->

</head>
<body>
<!-- 	<div class="search"> -->
<!-- 		<form method="post" action="RecipeServlet" name="search"> -->
<!-- 			<div class="search-select"> -->

<!-- 				<select name="selectedType" OnChange="Buildkey(this.selectedIndex);"> -->
<!-- 					<option value="">請選擇</option> -->
<!-- 					<option value="rcstyle_no">風格</option> -->
<!-- 					<option value="recipe_type">類型</option> -->
<!-- 					<option value="calo_intake">卡洛里</option> -->
<!-- 					<option value="cook_time">時間</option> -->
<!-- 				</select> <select name="selectedValue" id="selected"> -->
<!-- 					<option value=""></option> -->
<!-- 				</select> -->
<!-- 			</div> -->
<!-- 			<div class="search-bar"> -->
<!-- 				<input type="search" name="keyword" -->
<!-- 					placeholder="食譜名稱  蔬菜  等....(請以空格隔開)"> <input type="hidden" -->
<!-- 					name="action" value="search"> -->
<!-- 				<button type=submit>食譜搜尋</button> -->
<!-- 			</div> -->
<!-- 		</form> -->
<!-- 	</div> -->
	<!-- end of search-select -->

	<div class="recipe-main-list">
		<div class="recipe-main-list-header" <%=(keyword==null||keyword.isEmpty())?"style='display:none'":""%>>
			<h3>依照<font color='#FF5757'>${keyword}</font>收尋結果為:</h3>
		</div>
		<div class="result-align">
			<div class="result-align-title">排序</div>
			<div>
				<form method="post" action="RecipeServlet">
					<input type="hidden" name="action" value="search">
					<button type="submit">依照人氣</button>
					<input type="hidden" name="sort_type" value="refollow_num">
					<input name="selectedType" value="${selectedType}" type="hidden">
					<input name="selectedValue" value="${selectedValue}" type="hidden">
					<input name="keyword" value="${keyword}" type="hidden">
					<input name="pageType" value="searchRecipe.jsp" type="hidden">
				</form>
			</div>
			<div>
				<form method="post" action="RecipeServlet">
					<input type="hidden" name="action" value="search">
					<button type="submit">依照時間短至長</button>
					<input type="hidden" name="sort_type" value="cook_time"> 
					<input name="selectedType" value="${selectedType}" type="hidden">
					<input name="selectedValue" value="${selectedValue}" type="hidden">
					<input name="keyword" value="${keyword}" type="hidden">
					<input name="pageType" value="searchRecipe.jsp" type="hidden">
				</form>
			</div>
			<div>
				<form method="post" action="RecipeServlet">
					<input type="hidden" name="action" value="search">
					<button type="submit">依照卡洛里低至高</button>
					<input type="hidden" name="sort_type" value="calo_intake">
					<input name="selectedType" value="${selectedType}" type="hidden">
					<input name="selectedValue" value="${selectedValue}" type="hidden">
					<input name="keyword" value="${keyword}" type="hidden">
					<input name="pageType" value="searchRecipe.jsp" type="hidden">
				</form>
			</div>
			<div>
				<form method="post" action="RecipeServlet">
					<input type="hidden" name="action" value="search">
					<button type="submit">依照鹽分攝取低至高</button>
					<input type="hidden" name="sort_type" value="salt_intake">
					<input name="selectedType" value="${selectedType}" type="hidden">
					<input name="selectedValue" value="${selectedValue}" type="hidden">
					<input name="keyword" value="${keyword}" type="hidden">
					<input name="pageType" value="searchRecipe.jsp" type="hidden">
				</form>
			</div>
		</div>
		<span class="include-page"><%@ include file="page1.file"%>
		</span>
		<ul class="recipe=list">
			<c:forEach var="RecipeVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<li class="recipe-item"><img
					src="${(RecipeVO.recipe_photo==null)?'../../image/icon/uploadPic.png':RecipeVO.recipe_photo}"
					alt="">
					<div class="recipe-item-caption">
						<div class="recipe-item-caption-header">
							<h4 class="recipe-item-tile">
								<a class="show-one-link"
									href="<%=request.getContextPath()%>/front-end/recipe/RecipeServlet?action=getOne_For_Display&recipe_id=${RecipeVO.recipe_id}">${RecipeVO.recipe_name}</a>
							</h4>
						</div>
						<p class="recipe-create-time">建立時間:${RecipeVO.recipe_uldate}</p>
						<p class="recipe-item-ingredient">食材：${RecipeVO.ingredients_str}</p>
					</div></li>
			</c:forEach>
		</ul>
		<div class="include-page2">
			<%@ include file="page2.file"%>
		</div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
</body>
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
</html>