<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.recipe_favorite.model.RecipeFavoriteServiec"%>
<%@page import="com.recipe.model.*"%>
<%@page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String member_id = (String) request.getAttribute("member_id");
	RecipeService recipeService = new RecipeService();
	List<RecipeVO> list = recipeService.getChefCooked(member_id);
	MemberService memberService = new MemberService();
	MemberVO memberVO = memberService.getOneMember(member_id);
	RecipeFavoriteServiec recipeFavoriteServiec = new RecipeFavoriteServiec();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("recipeFavoriteServiec", recipeFavoriteServiec);//取得按讚數
%>
<span class="include-page"><%@ include file="page1.file"%><b>食譜</b></span>
<div class="main-page-card-container">
	<c:forEach var="RecipeVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<div class="main-page-card">
			<div class="main-page-card-pic">
				<img src="${RecipeVO.recipe_photo}">
			</div>
			<div class="main-page-card-info">
				<div>
					<h4>
						<a class="show-one-link"
							href="<%=request.getContextPath()%>/front-end/recipe/recipeHomepage.jsp?pageType=listOneRecipe.jsp&recipe_id=${RecipeVO.recipe_id}">${RecipeVO.recipe_name}</a>
					</h4>
					<span class="followNum">${recipeFavoriteServiec.getFollowedNum(RecipeVO.recipe_id)}</span><span>人收藏</span>
				</div>
				<span class="recipeFollow">
					<button class="recipeFollow_btn">收藏</button> <input type="hidden"
					name="action" value="recipe_follow" class="action"> <!-- 							先假裝有會員810009按讚 -->
					<!-- 							先假裝有會員810009按讚 --> <!-- 							先假裝有會員810009按讚 --> <input
					type="hidden" name="member_id" value="810009" class="member_id">
					<input type="hidden" name="recipe_id" value="${RecipeVO.recipe_id}"
					class="recipe_id">
				</span>
			</div>
		</div>
	</c:forEach>
</div>
<!-- end of main-page-card-container-->
<div class="include-page2">
	<%@ include file="page2.file"%>
</div>