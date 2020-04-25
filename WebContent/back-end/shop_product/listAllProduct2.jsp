<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
	ProductService Psvc = new ProductService();
	List<ProductVO> list = Psvc.getAll();
	pageContext.setAttribute("list", list);
%>
<script
  src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
  integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
  crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有商品處理頁面</title>
<style>
table#table-1 {
	background-color: #ffa0a5;
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
	width: 800px;
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
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有商品</h3>
				<h4>
					<a href="ProductPage.jsp"><img
						src="<%=request.getContextPath()%>/image/FoodPron_Logo.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
			<td>
	<a href="../ShopBackendHomePage.jsp">返回管理頁面</a>
	
	
	
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
	<form METHOD="POST" ACTION="product.do">
		<input type="submit" value="✚新增商品"> <input type="hidden"
			name="action" value="addProduct">

	</form>
	<br>

	<table>
		<tr>
			<th>圖片</th>
			<th>類別</th>
			<th  class="product_idth" >商品編號</th>
			<th class="recipe_th">食譜編號</th>
			<th>商品名稱</th>
			<th>商品單價</th>
			<th>商品狀態</th>
			<th>詳細</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="../file/page1.file"%>
		<c:forEach var="productvo" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td><img width=80px height=70px
					src="Product_photoReader?product_id=${productvo.product_id}
			">
				</td>
				<td>${productvo.product_type}</td>
				<td  class="product_idtd">${productvo.product_id}</td>
								<td class="recipe_td">${productvo.recipe_id}</td>
				
				<td>${productvo.product_name}</td>
				<td>${productvo.product_price}</td>
				<%
					Map<Integer, String> map = new HashMap<>();
						map.put(1, "未上架");
						map.put(0, "已上架");
						request.setAttribute("productstatus", map);
				%>
				<c:set var="status" value="${productvo.product_status}" />
				<td>${ productstatus [status] }</td>

				<td>
					<!-- 詳細頁面 -->
					<FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
						<input type="submit" value="查看更多"> <input type="hidden"
							name="product_id" value="${productvo.product_id}"> <input
							type="hidden" name="action" value="listOneProduct">
					</FORM>
				</td>

				<td>
					<!-- 修改 -->
					<FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="product_id" value="${productvo.product_id}">
						<!-- 前往新增 -->
						<input type="hidden" name="action" value="ProductUpdatePage">
					</FORM>
				</td>
				<td>
					<!-- 刪除 -->
					<FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
						<input type="hidden" name="action" value="delete"> 
						<input type="submit" value="刪除"> <input type="hidden"
							name="product_id" value="${productvo.product_id}"> 
							<input	type="hidden" name="whichPage" value="<%=whichPage%>"> 
							<input	type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

	<%@ include file="../file/page2.file"%>
<script>
var r=$('.recipe_td').html()
if($('.recipe_td').html()==""){
	
	$('.recipe_td').css('display','none');
	$('.recipe_th').css('display','none');	
$('.recipe_th').html("");
// 	$('.product_idth').attr("colspan", 2);
// 	$('.product_idtd').attr("colspan", 2);
}
else if($('.recipe_td').html()=="" && $('.recipe_th').html()!="")
		{

	
}

</script>
</body>
</html>