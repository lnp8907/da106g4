<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String pageType = (String) request.getParameter("pageType");
	if (pageType == null) {
		pageType = "staffAllList.jsp";
	}
	request.setAttribute("pageType", pageType);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>食譜管理</title>
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
.menu-group>span, .menu-group-a {
	text-decoration: none;
	color: rgba(0, 0, 0, 0.5);
	cursor: pointer;
	transform: scale(1);
	transition: all ease-in-out 150ms;
	font-size: 24px;
	font-weight: 600;
}

.menu-group>span:hover, .menu-group-a:hover {
	transform: scale(1.1, 1.1);
	color: rgba(0, 0, 0, 0.5);
}

@import 'https://fonts.googleapis.com/css?family=Lato';

/*   Variables   */
/*   End of Variables   */
* {
	padding: 0;
	margin: 0;
}

html {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

* {
	text-rendering: optimizeLegibility;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

html, body {
	width: 100%;
	height: 100%;
	font-family: "微軟正黑體", "標楷體", "Lato", sans-serif;
	background-color: pink;
}

*, *:before, *:after {
	-webkit-box-sizing: inherit;
	-moz-box-sizing: inherit;
	box-sizing: inherit;
}

/*  End Resets  */
.container {
	/* background-color: pink; */
	width: 100%;
	height: 100%;
	height: auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.box {
	position: relative;
	width: 1400px;
	height: 850px;
	background-color: white;
	display: flex;
	flex-flow: row nowrap;
	margin-top: 10px;
	padding-left: 250px;
}

.left-bar {
	position: absolute;
	top: 0;
	left: 0;
	/* position: relative; */
	z-index: 5;
	width: 230px;
	height: 100%;
	background: #FF4E50;
	background: -webkit-linear-gradient(180deg, #FF2C55, #FF4E2D);
	background: linear-gradient(180deg, #FF2C55, #FF4E2D);
	display: flex;
	flex-direction: column;
	align-items: center;
}

.left-bar span {
	padding: 5px;
}

.left-bar>span {
	cursor: pointer;
	transform: scale(1, 1);
	transition: all ease-in-out 150ms;
}

.left-bar>span:hover {
	transform: scale(1.2, 1.2);
}

.left-bar>span:first-child {
	margin-top: 20px;
	color: white;
	font-size: 26px;
}

.left-bar>span:last-child {
	color: rgba(241, 212, 195, 0.76);
	margin-top: 200px;
	font-size: 26px;
	font-weight: 900;
}

.menu-group {
	height: 200px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-between;
	margin-top: 70px;
}

.menu-group>span {
	color: rgba(0, 0, 0, 0.5);
	cursor: pointer;
	transform: scale(1);
	transition: all ease-in-out 150ms;
	font-size: 26px;
	font-weight: 600;
}

.menu-group>span:hover {
	transform: scale(1.2, 1.2);
}

.menu-group .active {
	color: white;
}

.menu-group .active:after {
	content: '';
	position: absolute;
	margin-left: 3px;
	margin-top: -4px;
	padding: 4px;
	background-color: yellow;
	border-radius: 50%;
	box-shadow: 0 0 5px 2px rgba(255, 255, 0, 0.5);
}

.wrapper {
	flex: 1;
}

.table100 {
	margin-left: -19px;
	height: 755px;
}

.table100-body {
	height: 700px;
}

.cell100 {
	text-align: center;
}

.menu {
	height: 70px;
	background-color: white;
}

.column1 {
	width: 10px;
}

h1 {
	width: 500px;
	margin: 25px auto;
	font-size: 36px;
	font-weight: 800;
	color: #E4002B;
	padding-left: 120px;
}

#page2 {
	width: 100%;
	position: absolute;
	text-align: center;
}

#page2 a, #page2 font {
	color: #E4002B;
	font-weight: 600;
	font-size: 14px;
	margin-right: 10px;
}

#page2 select {
	margin-left: -15px;
}

#page2 from, #page2 input {
	display: inline-block;
	margin-left: 10px;
}

#page2 input:hover {
	cursor: pointer;
}

.include-page {
	width: 100%;
	font-size: 18px;
	margin-left: 5px;
	color: #999;
}

.row100 {
	box-shadow: 2px 1px rgba(54, 27, 27, 0.2);
}
</style>
<body>
	<div class="container">
		<div class="box">
			<!--這裡是左邊選單-->
			<div class="left-bar">
				<img src="../../image/logo_nohead.png" alt="LOGO" width="200"
					height="200">
				<div class="menu-group">
					<a class="menu-group-a"
						href="<%=request.getContextPath() + "/backEnd2.jsp"%>"><span
						style="font-size: 30px;">回首頁</span></a> <a class="menu-group-a"
						href="<%=request.getContextPath() + "/back-end/staff/staffPage.jsp?pageType=staffAllList.jsp"%>"><span>員工列表</span></a>
					<a class="menu-group-a"
						href="<%=request.getContextPath() + "/back-end/staff/staffPage.jsp?pageType=staffInsertPage.jsp"%>"><span>員工新增
					</span></a>
					<div class="building"></div>
				</div>
				<span class="fa fa-sign-out">登出</span>
			</div>
			<div class="wrapper">
				<jsp:include page="<%=pageType%>" />
			</div>
		</div>
	</div>
	
</body>
</html>