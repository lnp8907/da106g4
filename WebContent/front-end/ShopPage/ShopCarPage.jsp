<%@page import="com.recipe.model.RecipeService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <%@ page import="java.util.* ,com.order_detail.model.Order_detailVO,com.recipe.model.RecipeVO" %>
  <script src="../../plugin/jquery-3.4.1.min.js"></script>
    <script src="../../js/jquery-migrate-1.4.1.min.js"> </script>

    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick-theme.css"/>
    <script type="text/javascript" src="../../css/slick-1.8.1/slick/slick.js"></script>
    <link rel="stylesheet" type="text/css" href="../../css/semantic.min.css">
    <script src="../../js/semantic.min.js"></script>
    <meta charset="UTF-8">
    <title>FoodPornChec</title>
    <link rel="stylesheet" href="css/ShopCartPage.css">
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車</title>
</head>
<body>
<div id="shopTitle">
    <div class="foodpronpicture"><a href="ShopProductPage.jsp"><img src="../../image/FoodPron_Logo.png" alt=""></a></div>
</div>
<hr>

<!-- 預設 -->
<%if(request.getAttribute("checktpage").equals("checktpage1")){ %>
 <jsp:include page="ShopCarPagelist.jsp" />
 <% }else if(request.getAttribute("checktpage").equals("checktpage2")){%>
	 
	 <jsp:include page="ProductCheckoutPage.jsp" />
	 
	 
 <% }else{%>
	 <jsp:include page="ShopCarPagelist.jsp" />

	 
	 
 <%}%>
<!-- 轉移結帳2 -->
<%--  <jsp:include page="ProductCheckoutPage.jsp" /> --%>
<!-- 轉移結帳3 -->
 




</body>
</html>