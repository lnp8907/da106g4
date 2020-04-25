<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.recipe.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="recipeService" class="com.recipe.model.RecipeService"/>


<%
	List<RecipeVO> list = recipeService.getAll();
	pageContext.setAttribute("list", list);
	String oldRecipe_id = (String) request.getAttribute("oldRecipe_id");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>食譜管理</title>
<body>
	<div class="content">
		<div class="menu">
			<!-- 卡片內容上方留白的起始標籤 -->
			<h1>
				食譜管理<span class="include-page"><%@ include file="page1.file"%></span>
			</h1>
		</div>
		<!-- 卡片內容上方留白的結束標籤 -->
		<div class="table100 ver2 m-b-110">
			<div class="table100-head">
				<table>
					<thead>
						<tr class="row100 head">
							<th class="cell100 column4">Recipe id</th>
							<th class="cell100 column4">Chef id</th>
							<th class="cell100 column4">Chef name</th>
							<th class="cell100 column2">Recipe name</th>
							<th class="cell100 column4">Create time</th>
							<th class="cell100 column4">Follow</th>
							<th class="cell100 column4">Status</th>
						</tr>
					</thead>
				</table>
			</div>

			<div class="table100-body js-pscroll">
				<table>

					<tbody>
						<c:forEach var="RecipeVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr class="row100 body" style="${(oldRecipe_id==RecipeVO.recipe_id)?'background-color: aliceblue;':''}">
								<td class="cell100 column4">${RecipeVO.recipe_id}</td>
								<td class="cell100 column4">${RecipeVO.member_id}</td>
								<td class="cell100 column4">${memberService.getOneMember(RecipeVO.member_id).member_name}</td>
								<td class="cell100 column2">${RecipeVO.recipe_name}</td>
								<td class="cell100 column4">${RecipeVO.recipe_uldate}</td>
								<td class="cell100 column4">${recipeFavoriteServiec.getFollowedNum(RecipeVO.recipe_id)}</td>
								<td class="cell100 column4">
									<form method="post" action="RecipeServlet.do"
										name="changeStatus">
										<select name="recipe_status" class="recipe_status"">
											<option value="4"
												${(RecipeVO.recipe_status==4)?'selected':''}>上架</option>
											<option value="3"
												${(RecipeVO.recipe_status==3)?'selected':''}>下架</option>
										</select> 
										<input type="hidden" name="recipe_id" value="${RecipeVO.recipe_id}"> 
										<input type="hidden" name="action" value="changeStatus">
										<input type="hidden" name="recipe_id" value="${RecipeVO.recipe_id}"> 
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
									</form>
								</td>
							</tr>

						</c:forEach>
						<tr id="page2-tr">
							<td id="page2"><%@ include file="page2.file"%></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {
		$('.recipe_status').on('change', function() {
			$(this).parent('form').submit();
		});
	});
</script>

</html>