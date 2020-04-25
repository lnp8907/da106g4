<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.recipe.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
	RecipeService recipeService = new RecipeService();
	List<RecipeVO> list = recipeService.getAll();
	pageContext.setAttribute("list", list);
%>

<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>食譜管理清單</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="recipeCSS/recipeList.css">
</head>

<body>
	<h1>
		<a href="select_page.jsp">回首頁</a>
	</h1>
	<div class="recipe-main-list">
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
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/recipe/RecipeServlet"
								style="margin-bottom: 0px;">
								<button class="update" value="修改">
									<img src="../../image/icon/update.png" title="修改" alt="修改">
								</button>
								<input type="hidden" name="recipe_id"
									value="${RecipeVO.recipe_id}"> <input type="hidden"
									name="action" value="getOne_For_Update">
							</FORM>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/recipe/RecipeServlet"
								style="margin-bottom: 0px;">
								<button class="delete" value="刪除">
									<img src="../../image/icon/delete.png" title="刪除" alt="刪除">
								</button>
								<input type="hidden" name="recipe_id"
									value="${RecipeVO.recipe_id}"> <input type="hidden"
									name="action" value="delete">
							</FORM>

						</div>
						<p class="recipe-create-time">建立時間:${RecipeVO.recipe_uldate}</p>
						<p class="recipe-item-ingredient">追蹤人數：${RecipeVO.refollow_num}</p>
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
</html>