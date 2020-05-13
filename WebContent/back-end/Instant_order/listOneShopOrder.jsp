
<%@page import="com.recipe.model.RecipeService"%>
<%@page import="com.shop_order.model.Shop_orderVO"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@ page import="com.order_detail.model.Order_detailVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.recipe_order_details.model.*"%>

<%@ page import="com.ordermanager.shop.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
OrderService ordSvc = new OrderService();
Integer total=0;
List<RecipeOrderDetailsVO> list=null;
String order_no = null;

if(request.getAttribute("dialoglist")!=null){
list= (List<RecipeOrderDetailsVO>) request.getAttribute("dialoglist");
order_no=list.get(0).getIDO_no();
total =ordSvc.gettotal(order_no);

}
request.setAttribute("order_no",order_no );
request.setAttribute("dialoglist", list);
//獲取總額
int a=0;
int ordertot=0;

ProductService Psv=new ProductService();
RecipeService Rsv=new RecipeService();


%>
<c:set var="order_no" value="<%=order_no %>"/>

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
編號：${order_no} <br>
會員：${mvo.member_name} 
<br>
<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3> -->				
<!-- 					訂單明細 -->
<!-- 				</h3> -->
<!-- 				<h4> -->
<%-- 					<a href="order_manager_page.jsp"><img src="<%=request.getContextPath()%>/image/FoodPron_Logo.png" --%>
<!-- 						width="100" height="32" border="0">回訂單管理頁面</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<!-- 	<br> -->
<%-- 	<form METHOD="POST"  ACTION="<%=request.getContextPath()%>/back-end/shop_order/detailServlet.do"> --%>
<!--     <input type="submit" value="✚新增購買商品"> -->
<%--     <input type="hidden" name="order_no"  value="<%=order_no%>"> --%>
<!--     <input type="hidden" name="action" value="addDetail"> -->
<!-- 	</form> -->
<!-- 	<br> -->

	<table>

		<tr>
		<th>商品圖片</th>
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>購買數量</th>
			<th>單價</th>
			<th>小計</th>
<!-- 			<th>修改數量</th> -->
<!-- 			<th>刪除</th> -->

		</tr >
		<%@ include file="../file/page1.file"%>
		<c:forEach var="detailvo" items="${dialoglist}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

				<c:set var="Pid" value="${detailvo.product_id}" scope="request"/>
<%String Pid=(String)request.getAttribute("Pid");
String Rid=Psv.getOneProduct(Pid).getRecipe_id();
%>	
			<tr class="ordertr1">
			<td>
						<img width=80px height=70px src="<%=Rsv.findByPrimaryKeyForSaved(Rid).getRecipe_photo()%>">
			
			
			</td>
				<td>${detailvo.product_id}</td>
				
				
				
	

		
				<td>
		
<%=Rsv.findByPrimaryKeyForSaved(Rid).getRecipe_name()%>		
				</td>
	         
				
				<td>${detailvo.quantity}</td>
				<td>${detailvo.price}</td>
				<td><%
				a++;
				%>${detailvo.quantity*detailvo.price}
				</td>
				
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/detailServlet.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="order_no"  value="${detailvo.order_no}"> --%>
<%-- 			     <input type="hidden" name="product_id"  value="${detailvo.product_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="detailUpdatepage"></FORM> -->
<!-- 			</td> -->
			
			
			
<!-- 			<!-- 刪除 -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/detailServlet.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			       <input type="hidden" name="order_no"  value="${detailvo.order_no}"> --%>
<%-- 			     <input type="hidden" name="product_id"  value="${detailvo.product_id}"> --%>
<!-- 			     <input type="hidden" name="action" value="detaildelete"> -->
			 
<!-- 			    </FORM> -->
			     
<!-- 			</td> -->
			</tr>
			
			
		
		</c:forEach>
	</table>

			
			<hr width="610px" color="red" align="left">
		    <p  style="color: red; margin-left:535px" >總計:<%=total %></p>
	<%@ include file="../file/page2.file"%>
</body>
</html>