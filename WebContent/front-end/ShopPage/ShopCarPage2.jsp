<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.* ,com.order_detail.model.Order_detailVO" %>
    <script
  src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
  integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
  crossorigin="anonymous"></script>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車</title>
</head>
<body>
<button> <a href="ShopProductPage.jsp">回商品頁面</a></button>
 <%
 	@SuppressWarnings("unchecked")
  int alltot=0;
    Vector<Order_detailVO> buyProductlist =(Vector<Order_detailVO>)session.getAttribute("productCar");
 %>
<%
	if (buyProductlist != null && (buyProductlist.size() > 0)) {
%>
 <font size="+3">目前購物車的內容如下：</font>

<table id="table-1" >
    <tr> 
      <th width="200">商品名</th><th width="100">單價</th><th width="100">數量</th><th>小計</th>
    </tr></table><table>

	<%
		for (int index = 0; index < buyProductlist.size(); index++) {
			 Order_detailVO order = buyProductlist.get(index);
	%>
	<tr>
	<%ProductService svc=new ProductService();
	ProductVO productvo=svc.getOneProduct(order.getProduct_id());
	%>
		<td width="200"><%=productvo.getProduct_name()%>     </td>
		<td width="100"><%=order.getPrice()%>   </td>
		<td width="100"><%=order.getQuantity()%></td>
		<%
		int tot=order.getPrice()*order.getQuantity();
		alltot+=tot;
		%>
		<td width="100"><%=tot %></td>
	
		
        <td width="120">
          <form name="deleteForm" action="Shopping.html" method="POST">
              <input type="hidden" name="action"  value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="刪 除" class="button">
          </form></td>
	</tr>
	<%}%>
</table>
<button><a href="ProductCheckoutPage.jsp">前往結帳</a></button>
              
<%}%>
<h2 style="color:red">總計:<%=alltot %></h2>

<script>
function del(){
var num=parseInt($('#quantity').text())-1;
if(num<1){
$('#quantity').text(1);
}else{
$('#quantity').text(num);
}
}
function add(){
var num=parseInt($('#quantity').text())+1;
$('#quantity').text(num);
}
</script>

</body>
</html>