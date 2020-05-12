<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>

<script src="<%=request.getContextPath()%>/plugin/jquery-3.4.1.min.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/Semantic-UI/semantic.min.css">




    <link rel="stylesheet" href="css/carFinshPage.css">

<meta charset="UTF-8">
<title>訂單完成</title>
</head>
<body>


<div id="finshField">
<div class="Ftop">

</div>
    <div style="margin-top: 20px" id="FinshTitle">
    
訂單完成
    </div>
<div style="margin-top: 40px"  class="finshcontext">
<p>  感謝您這次的購物，<font style="color:#E4002B ">${memberVO.member_name}</font>  您的訂單資料己確認送出!</p>
<p>請密切注意您的商品，若對商品有任何疑問請聯絡客服以方便訂單的處理</p>
<p>感謝您下次的購物</p>



<p></p>
<br>
<br>



</div>
<div style="float: right;" class="ui animated button" tabindex="0">
<a href="../../front-end/ShopPage/ShopProductPage.jsp">

  <div style="color: #E4002B" class="visible content">返回首頁</div>
  <div class="hidden content">
    <i class="reply all red
icon"></i>
  </div>
</a>

</div>
    
    
</div>

<style>
#FinshTitle{
text-align:center;
color:#E4002B;
font-size: 35px;
width:500px;
margin: 0 auto;

    font-family: 微软雅黑;


}


</style>
</body>
</html>