<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.recipe.model.*"%>
<%@page import="com.ingredient.model.IngredientVO"%>
<%@page import="com.ingredient.model.IngredientDAO"%>
<%@page import="java.util.List"%>

<%
	RecipeVO_saved recipeVO = (RecipeVO_saved) request.getAttribute("recipeVO");
%>

<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>食譜新增</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" href="recipeCSS/recipe.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

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
						<input type="hidden" name="member_id" placeholder="請輸入廚師編號" value="<%=(recipeVO == null) ? "810003" : recipeVO.getMember_id()%>">
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

	<!-- end of insert-recipe-container-->




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
</body>

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

</html>