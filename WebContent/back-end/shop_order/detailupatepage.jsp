<%@ page import="com.order_detail.model.Order_detailVO"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ordermanager.shop.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	Order_detailVO detailvo =(Order_detailVO) request.getAttribute("detailvo");
    ProductService productSvc=new ProductService();%>
    <%=detailvo.getProduct_id()
    
    
    
    %>
    <% ProductVO productvo=productSvc.getOneProduct(detailvo.getProduct_id());
    Integer minprice=productvo.getProduct_price();
%>
<%=productvo.getProduct_price()%>
<!DOCTYPE html>
<script src="./WebContent/css/DEMOJSPDEMO.css"></script>

<html>
<head>
<meta charset="UTF-8">
<title>更改訂單內商品</title>


<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>



</head>
<body>
<FORM METHOD="post" ACTION="detailServlet.do" name="form1">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=detailvo.getorder_no()%></td>
	</tr>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=detailvo.getProduct_id()%></td>
	</tr>
	<% int a=detailvo.getQuantity();
	int b=detailvo.getPrice();
	int c= a*b;
	%>
	<tr>
		<td>數量:</td>
		<td><input type="TEXT" name="quantity" size="45"	value="<%=detailvo.getQuantity()%>" /></td>
	</tr>	
	<tr>
		<td>單價:</td>
		<td><input type="TEXT" name="price" size="45"	value="<%=detailvo.getPrice()%>" /></td>
	</tr>	
	<tr>
		<td>總價:<font color=red><b>*</b></font></td>
		<td><%=c %></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="DetailUpdate">
  <input type="hidden" name="order_no"  value="<%=detailvo.getorder_no()%>">
 <input type="hidden" name="product_id"  value="<%=detailvo.getProduct_id()%>">
 			  <input type="hidden" name="minprice"  value="<%=minprice%>">
<input type="submit" value="送出修改">



</FORM>

<FORM METHOD="post" ACTION="OrderServlet.do" >
			  <input type="hidden" name="order_no"  value="<%=detailvo.getorder_no()%>">
			  <input type="hidden" name="action" value="getorderdetail">
			  
       <input type="submit" value="返回訂單明細">
			     </FORM>

<h2>商品目前價格:<%=minprice





%> 請勿輸入低於此價格</h2>


</body>



</html>