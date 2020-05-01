<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.recipe.model.RecipeService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <%@ page import="java.util.* ,com.order_detail.model.Order_detailVO,com.recipe.model.RecipeVO" %>
 
    
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <title>FoodPornCheckPage</title>
     <script src="../../plugin/jquery-3.4.1.min.js"></script>
    <script src="../../js/jquery-migrate-1.4.1.min.js"> </script>

    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick-theme.css"/>
    <script type="text/javascript" src="../../css/slick-1.8.1/slick/slick.js"></script>
       <link rel="stylesheet" type="text/css" href="../../plugin/Semantic-UI/semantic.min.css">
    <script src="../../plugin/Semantic-UI/semantic.min.css"></script>
   
    <link rel="stylesheet" href="css/ShopCartPage.css">
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
	 
	 
 <% }else if(request.getAttribute("checktpage")==null){  %>
	 
	 
	 <jsp:include page="ShopCarPagelist.jsp" />

	 
	 
 <% }%>

	 





</body>
</html>