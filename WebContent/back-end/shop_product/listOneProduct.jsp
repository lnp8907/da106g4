<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.product.model.*" %>
    <%@ page import="java.util.*"%>
<%
ProductService svc=new ProductService();
String product_id=(String)request.getAttribute("product_id");

     ProductVO productvo = svc.getOneProduct(product_id); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<%
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "未上架");
    map.put(0, "已上架");
    request.setAttribute("productstatus", map);
%>
<script
  src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
  integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
  crossorigin="anonymous"></script>
<html>
<head>
<title> 查看商品詳細資料 </title>

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
<body bgcolor='white'>



	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<table>
	<tr id="productrow1">
		<th>圖片</th>
		
		<th>類別</th>
		<th  id="product_idth" >商品編號</th>
			<th id="recipe_th">食譜編號</th>
		<th>商品名稱</th>
		<th>商品單價</th>

		<th colspan="3">商品狀態</th>
		
	</tr>
	<tr>
		<td><img width=80px height=70px src="Product_photoReader?product_id=<%=product_id%>
			"></td>
		
	<td><%=productvo.getProduct_type()%></td>
		<td  id="product_idtd"><%=productvo.getProduct_id()%></td>
		<!-- 食譜欄位 -->
			<td id="recipe_td"><%=productvo.getRecipe_id() %></td>
		<td><%=productvo.getProduct_name()%></td>
		<td><%=productvo.getProduct_price()%></td>
		
		<td colspan="2"><%=map.get(productvo.getProduct_status())%></td>		
		
	</tr>
	<tr>
	<tr>
	<th>熱量</th>
		<th>碳水化合物</th>
		
		<th>蛋白質</th>
		<th>脂質</th>
		<th>維生素B</th>
		<th>維生素C</th>
		<th>鈉含量</th>
		<th>植物纖維</th>
	</tr>
	<tr>
	<td><%=productvo.getCalorie()%></td>
		<td><%=productvo.getProtein()%></td>
		
		<td><%=productvo.getProtein()%></td>
		<td><%=productvo.getFat()%></td>
		<td><%=productvo.getVitamin_B()%></td>
		<td><%=productvo.getVitamin_C()%></td>
		<td><%=productvo.getSalt()%></td>
		<td><%=productvo.getVagetbale()%></td>
	</tr>
	<tr><th colspan="8">敘述</th><tr>
	<tr><td colspan="8"><%=productvo.getContent()%></td></tr>
	
	
	
	
	
</table>
<table>
<tr><th><FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="product_id"  value="<%=productvo.getProduct_id()%>">
			     <input type="hidden" name="action"	value="ProductUpdatePage">
			     </FORM></th>
			     <th>  <FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="product_id"  value="<%=productvo.getProduct_id()%>">
			     <input type="hidden" name="action" value="delete"></FORM></th>
			     			      
			     			      
			     			      
			     			      
			     	
			     			     
			     			     
			     			     <th><input type="button" value="返回全部訂單" onclick="location.href='listAllProduct.jsp'">
			     			     
			     			     </th>
			     </tr>




</table>

			     
			     
<script>
var r=$('#recipe_td').html()
if(r=="null"){
	$('#recipe_td').css('display','none');
	$('#recipe_th').css('display','none');	
	$('#product_idth').attr("colspan", 2);
	$('#product_idtd').attr("colspan", 2);
}else{
	
}




</script>

</body>
</html>