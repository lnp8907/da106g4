<%@page import="java.util.List"%>
<%@page import="com.coupon_details.model.CouponDetailsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單筆優惠券清單</title>
</head>
<body>

<jsp:useBean id="productService" scope="request" class="com.product.model.ProductService"/>
<jsp:useBean id="recipeService" scope="request" class="com.recipe.model.RecipeService"/>

<h2>${c_name}適用商品:</h2>
	<table class="table">
		<thead>
			<tr class="row100 head">
				<th class="cell100 column4">商品編號</th>
				<th class="cell100 column4">商品名稱</th>

			</tr>
		</thead>

		<tbody>
		<c:forEach var="couponVO" items="${list}">
			<tr class="row100 body">
				<td class="cell100 column4">${couponVO.product_id}</td>
				<td class="cell100 column4">${recipeService.getOneRecipe(productService.getOneProduct(couponVO.product_id).recipe_id).recipe_name}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>