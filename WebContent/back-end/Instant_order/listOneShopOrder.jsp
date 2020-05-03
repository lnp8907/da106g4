
<%@page import="com.recipe_order_details.model.RecipeOrderDetailsVO"%>
<%@page import="com.instant_delivery_order.model.InstantDeliveryOrderService"%>
<%@ page import="com.order_detail.model.Order_detailVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%@ page import="com.ordermanager.shop.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	InstantDeliveryOrderService ordSvc = new InstantDeliveryOrderService();

	String ido_no = (String) session.getAttribute("ido_no");

	List<RecipeOrderDetailsVO> list = ordSvc.getdetail(ido_no);
	
	
	session.setAttribute("order_no", ido_no);
	session.setAttribute("list", list);
	//獲取總額

%>
<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath()%>/css/DEMOJSPDEMO.css"></script>

<meta charset="UTF-8">
<title>訂單明細</title>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}
table#table-1 img{
width: 100px;
height: 100px;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>
					編號:<%=ido_no%>
					訂單明細
				</h3>
				<h4>
					<a href="order_manager_page.jsp"><img src="<%=request.getContextPath()%>/image/FoodPron_Logo.png"
						width="100" height="32" border="0">回訂單管理頁面</a>
				</h4>
			</td>
		</tr>
	</table>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<br>
	<form METHOD="POST"  ACTION="<%=request.getContextPath()%>/back-end/shop_order/detailServlet.do">
    <input type="submit" value="✚新增購買商品">
    <input type="hidden" name="order_no"  value="<%=ido_no%>">
    <input type="hidden" name="action" value="addDetail">
	</form>
	<br>

	<table>

		<tr>
		<th>商品圖片</th>
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>購買數量</th>
			<th>單價</th>
			<th>小計</th>
			<th>修改數量</th>
			<th>刪除</th>

		</tr>
		<%@ include file="../file/page1.file"%>
		<c:forEach var="detailvo" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">


			<tr>
			<td><img width=80px height=70px src="<%=request.getContextPath()%>/back-end/shop_product/Product_photoReader?product_id=${detailvo.product_id}
			"></td>
				<td>${detailvo.product_id}</td>
				
				
				<td>
	         </td>
	         
				
				<td>${detailvo.quantity}</td>
				<td>${detailvo.price}</td>
				<td>${detailvo.quantity*detailvo.price}
				</td>
				
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/detailServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="order_no"  value="${detailvo.order_no}">
			     <input type="hidden" name="product_id"  value="${detailvo.product_id}">
			     <input type="hidden" name="action"	value="detailUpdatepage"></FORM>
			</td>
			
			
			
			<!-- 刪除 -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/detailServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			       <input type="hidden" name="order_no"  value="${detailvo.order_no}">
			     <input type="hidden" name="product_id"  value="${detailvo.product_id}">
			     <input type="hidden" name="action" value="detaildelete">
			 
			    </FORM>
			     
			</td>
			</tr>
			
			
		
		</c:forEach>
	</table>

			
			<hr width="610px" color="red" align="left">
		    <p  style="color: red; margin-left:535px" >總計:</p>
	<%@ include file="../file/page2.file"%>
</body>
</html>