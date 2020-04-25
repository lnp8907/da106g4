<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.recipe.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.recipe_style.model.RecipeStyleVO"%>
<jsp:useBean id="recipeStyleService"
	class="com.recipe_style.model.RecipeStyleService" />


<%
	List<RecipeStyleVO> list = recipeStyleService.getAll();
	pageContext.setAttribute("list", list);
	String oldRCS_id = (String) request.getAttribute("oldRCS_id");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>食譜管理</title>
<style>
#updateStyleForm {
border-radius: 25px; 
font-size: 18px;
padding: 20px;
width: 248px;
height: 240px; 
position: fixed;
right: 48%;
background-color: rgba(255, 87, 87, 0.86);
top: 15%;
z-index: 999;
text-align: center;
color: white;
display:none;
box-shadow: 3px 3px 3px 3px #BBB;
}

#addStyleBtn {
	position: fixed;
	right: 5%;
	top: 87%;
	z-index: 99;
	border-radius: 55px;
	border: 2px solid;
	height: 55px;
	width: 55px;
	font-size: 50px;
	line-height: 40px;
	text-align: center;
	box-shadow: 3px 3px 3px #BBB;
}

#addStyleBtn:hover, #submit:hover, .update:hover,
	#updateStyleForm-close-btn:hover {
	cursor: pointer;
}
</style>
<body>
	<div class="content">
		<div class="menu">
			<!-- 卡片內容上方留白的起始標籤 -->
			<h1>
				食譜風格管理<span class="include-page"> <%@ include
						file="page1.file"%></span> <span id="addStyleBtn"
					title="新增風格">+</span>
			</h1>
		</div>
		<!-- 卡片內容上方留白的結束標籤 -->
		<div class="table100 ver2 m-b-110">
			<div class="table100-head">
				<table>
					<thead>
						<tr class="row100 head">
							<th class="cell100 column4">食譜風格編號</th>
							<th class="cell100 column4">食譜風格名稱</th>
							<th class="cell100 column5"></th>

						</tr>
					</thead>
				</table>
			</div>

			<div class="table100-body js-pscroll">
				<table>

					<tbody>
						<c:forEach var="RecipeStyleVO" items="${list}"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr class="row100 body"
								style="${(oldRCS_id==RecipeStyleVO.rcstyle_no)?'background-color: aliceblue;':''}">
								<td class="cell100 column4 rcstyle_no">${RecipeStyleVO.rcstyle_no}</td>
								<td class="cell100 column4 rcstyle">${RecipeStyleVO.rcstyle}</td>
								<td class="cell100 column5 update">修改</td>
								<input type="hidden" name="whichPage" value="<%=whichPage%>"
									class="whichPage">
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

<script>

		$(function () {
	        $("#updateStyleForm").draggable();
	});
</script>
	<div id="addStyleForm"
		style="display: none; border-radius: 25px; font-size: 18px; padding: 20px; width: 248px; height: 188px; position: fixed; right: 7%; background-color: #ff5757db; top: 67%; z-index: 999; text-align: center; color: white;">
		<h2>風格新增</h2>
		<br>
		<form action="RecipeStyleServlet" name="addStyle">
			<span>風格名稱:<span></span></span><input name="rcstyle" type="text"
				style="display: inline-block; width: 100px; height: 36px; margin-left: 10px; background-color: white; border-radius: 5px; padding: 4px; border: 3px solid #FF5757;">
			<input type="submit" value="確認送出" id="submit"
				style="background: white; width: 70px; height: 45px; display: inline-block; margin-top: 17px; border: none; border-radius: 17px; color: #FF5757;">
			<input type="hidden" name="action" value="insert">
		</form>
	</div>
	<div id="updateStyleForm">
		<h2>風格修改</h2>
		<br>
		<form action="RecipeStyleServlet" name="updateStyle">
			<span id="updateStyleForm-close-btn"
				style="position: absolute; top: 5px; right: 11px; border: white 2px solid; width: 30px; height: 30px; padding-top: 1px; border-radius: 30px; box-shadow: 1px 3px 3px #FF5439;">X</span>
			<span>風格編號:&nbsp;<span id="rcstyle_no_span"></span></span><br> <br>
			<span>風格名稱:<span></span></span><input name="rcstyle" type="text"
				id="rcstyle"
				style="display: inline-block; width: 100px; height: 36px; margin-left: 10px; background-color: white; border-radius: 5px; padding: 4px; border: 3px solid #FF5757;">
			<input type="submit" value="確認送出" id="submit"
				style="background: white; width: 70px; height: 45px; display: inline-block; margin-top: 17px; border: none; border-radius: 17px; color: #FF5757;">
			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="rcstyle_no" value="" id="rcstyle_no"> <input
				type="hidden" name="action" value="update"> <input
				type="hidden" name="whichPage" value="" id="whichPage">
		</form>
	</div>

</body>
<script>
	$("#addStyleBtn").click(function() {
		$("#addStyleForm").toggle();
	})
	$("#submit").click(function() {
		$("#addStyleForm").toggle();
	})

	$(".update").click(function() {
		var target = $(this);
		$("#updateStyleForm").toggle();
		$("#rcstyle").val(target.siblings('.rcstyle').text());
		$("#rcstyle_no_span").text(target.siblings('.rcstyle_no').text());
		$("#rcstyle_no").attr('value', target.siblings('.rcstyle_no').text());
		$("#whichPage").attr('value', target.next().val());
	})
	$("#updateStyleForm-close-btn").click(function() {
		$("#updateStyleForm").hide();
	})
</script>
</html>