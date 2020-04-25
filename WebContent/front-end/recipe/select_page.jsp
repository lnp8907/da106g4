<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Recipe: Home</title>
<link rel="stylesheet" href="recipeCSS/select_pageCSS.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<style>
.errorMsgs{
text-align:right;

}
</style>
</head>

<body>
	<div class="form">
		<fieldset>
			<div class="title">
				<h2>Foodporn Recipe: Home</h2>
				<p>This is the Home page for Foodporn Recipe: Home</p>
				<div class="errorMsgs">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul >
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red; list-style:none;">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				</div>
			</div>
			<h3>資料查詢</h3>
			<div>
				<label class="mid">查詢所有食譜</label> <input type="button" value="查詢"
					onclick="location.href='listAllRecipe.jsp'">
			</div>

			<div>
				<form method="post" action="RecipeServlet">
					<label class=" mid">輸入食譜編號(如510001):</label> <input type="text"
						name="recipe_id"> <input type="hidden" name="action"
						value="getOne_For_Display"> <input type="submit"
						value="確認">
				</form>
			</div>

			<jsp:useBean id="RecipeService" scope="page"
				class="com.recipe.model.RecipeService" />
			<div>
				<form method="post" action="RecipeServlet">
					<label class="mid">選擇食譜編號:</label> <select name="recipe_id"
						id="select-choice-2">
						<option value="">選擇食譜編號</option>
						<c:forEach var="recipeVo" items="${RecipeService.all}">
							<option value="${recipeVo.recipe_id}">${recipeVo.recipe_id}</option>
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
				</form>
			</div>
			<div>
				<form method="post" action="RecipeServlet">
					<label class="mid">選擇食譜名稱:</label> <select name="recipe_id"
						id="select-choice-2">
						<option value="">選擇食譜名稱</option>
						<c:forEach var="recipeVo" items="${RecipeService.all}">
							<option value="${recipeVo.recipe_id}">${recipeVo.recipe_name}</option>
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出" style="margin-top:20px">
				</form>
			</div>
			<h3>食譜管理</h3>
			<div>
				<label class="mid">新增食譜</label> <input type="button" value="新增"
					onclick="location.href='addRecipe.jsp'" >
				<BR>
				<BR>	
				<label class="mid">修改食譜</label> <input type="button" value="修改"
					onclick="location.href='listAllRecipeManagement.jsp'" >
			</div>

		</fieldset>

	</div>

</body>
</html>