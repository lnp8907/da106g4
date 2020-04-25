<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <%@ page import="java.util.* ,com.order_detail.model.Order_detailVO" %>
  <script src="../../plugin/jquery-3.4.1.min.js"></script>
    <script src="../../js/jquery-migrate-1.4.1.min.js"> </script>

    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick-theme.css"/>
    <script type="text/javascript" src="../../css/slick-1.8.1/slick/slick.js"></script>
    <link rel="stylesheet" type="text/css" href="../../css/semantic.min.css">
    <script src="../../js/semantic.min.js"></script>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/ShopCartPage.css">
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車</title>
</head>
<body>
 <%
 	@SuppressWarnings("unchecked")
  int alltot=0;
 Vector<Order_detailVO> buyProductlist =(Vector<Order_detailVO>)session.getAttribute("productCar");
 session.setAttribute("buyProductlist", buyProductlist);

 %>

<div id="shopTitle">
    <div class="foodpronpicture"><a href="ShopProductPage.jsp"><img src="../../image/FoodPron_Logo.png" alt=""></a></div>
</div>
<hr>
<div id="progress-rate">
    <div class="progesssize ui steps">
        <a class="active step">
            <i class="shopping cart icon" style="color: #e4002b"></i>
            <div class="content">
                <div class="title" style=" color: #e4002b;" >請確認購物車
                </div>
                <div class="description">選擇您的購買清單</div>
            </div>
        </a>
        <a class="step">
            <div class="content">
                <div class="title">結帳</div>
                <div class="description">輸入付款資訊</div>
            </div>
        </a>
        <a class="step">
            <div class="content">
                <div class="title">完成</div>
                <div class="description">恭喜完成訂單</div>
            </div>
        </a>
    </div>


</div>
<div id="showmember">
    敬愛的會員<font class="membername">宏哥</font>你好:</div>

<div id="tablecontext">
    <div id="checkboxwithpeoduct">
        <table  >
            <tr><td ><input type="checkbox" id="checkall"></td><td >商品名</td><td>價格</td> <td >數量</td><td>小計</td><td>移除</td></tr>
        </table>
    </div>


    <div id="productcartList" >
        <table >
        <%int index=0; %>
            <c:forEach var="buyProductlist" items="${buyProductlist}">
            <tr><td ><input type="checkbox" ></td><td>${buyProductlist.product_id}</td> 
            <td>${buyProductlist.price}</td><td class="mun">${buyProductlist.quantity}</td><td class="pcal">${buyProductlist.price*buyProductlist.quantity}</td>
            
            
            
            <td>  <form name="deleteForm" action="ShopCart" method="POST">
            <input type="hidden" name="del" value="<%=index%>">
              <input type="hidden" name="action"  value="REMOVE">
              <input type="submit" value="刪 除" class="button">
          </form>
          
          
        <% index++;%>
          
          </td></td></tr>
            </c:forEach>
        </table>


    </div>

    <div id="Cardetail">
        <div class="calcheck">
            共 <font><%=buyProductlist.size() %></font> 項商品，數量共 <font class="howmany"></font> 個，總金額NT$ <font class="tcal">XXX</font> 元
            <div>

                <a href="ProductCheckoutPage.jsp"><button style="border-radius: 0px 0px 15px 0px" class="ui right labeled icon button huge red">
                <i class="right arrow icon"></i> 結帳 </button></a>
            </div>


        </div>

    </div>


</div>
<div id="carbottom"></div>


<script>

var munTotal = 0;
$('.mun').each(function() {
	  $(this).each(function()
		{
			munTotal += parseInt($(this).html());
		});
	  $(".howmany").html(munTotal);
  });
var calTotal = 0;
$('.pcal').each(function() {
	  $(this).each(function()
		{
			calTotal += parseInt($(this).html());
		});
	  $(".tcal").html(calTotal);
});



</script>








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