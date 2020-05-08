<%@ page import="com.order_detail.model.Order_detailVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.ordermanager.shop.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String order_no = (String) request.getAttribute("order_no");
	Order_detailVO order_detailvo = (Order_detailVO) request.getAttribute("detailvo");
	
	ProductService pSvc=new ProductService();
	List<ProductVO> productlist =pSvc.getAll();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增訂單明細品項</title>
</head>
<style>
.title {
	width: 350px;
	height: 100px;
	background-color: blue:
}
</style>
<body bgcolor='white'>
<FORM METHOD="post" ACTION="OrderServlet.do" >
<input type="submit" value="返回<%=order_no%>訂單明細">
 <input type="hidden" name="action" value="getorderdetail">
<input type="hidden" name="order_no" value="<%=order_no%>">
</FORM>


	<div class="title">
		<h3>
			訂單編號:<%=order_no%></h3>
	</div>

	<h3>商品新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" class="ui form" ACTION="detailServlet.do" name="form1">
		<table>
		<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
		<tr>
				<td>商品編號:</td>
				<td><select size="1" name="product_id">
			<c:forEach var="prodcutvo" items="${productSvc.all}">
				<option value="${prodcutvo.product_id}" 
				
				${(order_detailvo.product_id==deptVO.product_id)? 'selected':'' } >
				
				${prodcutvo.product_name}
			</c:forEach>
		</select></td>	
			</tr>
			<tr>
				<td>數量:</td>
				<td><input type="TEXT" name="quantity" size="45"
					value="<%=(order_detailvo == null) ? "" : order_detailvo.getQuantity()%>"
					placeholder="<%=(order_detailvo == null) ? "購買數量" : order_detailvo.getQuantity()%>" /></td>
			</tr>
			<tr>
				<td>單價:</td>
				<td><input type="TEXT" name="price" size="45"
					value="<%=(order_detailvo == null) ? "" : order_detailvo.getPrice()%>"
					placeholder="<%=(order_detailvo == null) ? "輸入單價 請勿輸入小數位或非數字" : order_detailvo.getPrice()%>" /></td>
			</tr>
		</table>
			
		<br>
		 <input type="hidden" name="action" value="sendaddDetail">
		<input type="hidden" name="order_no" value="<%=order_no%>">
		
		 <input type="submit" value="送出新增">
	</FORM>
</body>



</html>