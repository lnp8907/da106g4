<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.product.model.*"%>
<%@page import="com.order_detail.model.*"%>
<%@ page import="java.util.*"%>

<%
	Vector<Order_detailVO> productlist;
	if ((Vector<Order_detailVO>) session.getAttribute("productCar") == null) {
		productlist = new Vector<Order_detailVO>();

	} else {
		productlist = (Vector<Order_detailVO>) session.getAttribute("productCar");
	}
	session.setAttribute("productCar", productlist);
	ProductService svc = new ProductService();
	String product_id = (String) request.getAttribute("product_id");

	ProductVO productvo = svc.getOneProduct(product_id); 
	/*注意新增*/
	session.setAttribute("productvo", productvo);

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Map<Integer, String> map = new HashMap<>();
	map.put(0, "未上架");
	map.put(1, "已上架");
	request.setAttribute("productstatus", map);
%>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
	crossorigin="anonymous"></script>

<html>
<head>

<title>購買頁面</title>
 <!-- 廣告連播套件 -->
    <script src="../../js/jquery-3.4.1.min.js"></script>
    <script src="../../js/jquery-migrate-1.4.1.min.js"> </script>

    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick-theme.css"/>
    <script type="text/javascript" src="../../css/slick-1.8.1/slick/slick.js"></script>
<link rel="stylesheet" type="text/css"href="css/productDetailPage.css"/>


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
	width: 1100px;
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
<link rel="stylesheet" href="../../css/semantic.min.css">
<script src="../../js/semantic.min.js"></script>
<link rel="stylesheet" href="css/productPage.css">
<script src="../../css/header-sider.css"></script>
<script src="../../css/frontEnd.css"></script>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>	
	<button>
		<a href="ShopCarPage.jsp">回購物車</a>
	</button>




	<script>	
		var r = $('#recipe_td').html()
		if (r == "null") {
			$('#recipe_td').css('display', 'none');
			$('#recipe_th').css('display', 'none');
			$('#product_idth').attr("colspan", 2);
			$('#product_idtd').attr("colspan", 2);
		} else {

		}
	</script>
	<script>
		function del() {
			var num = parseInt($('#quantity').text()) - 1;
			if (num < 1) {
				$('#quantity').text(1);
				$('#inquantity').val($('#quantity').text());

			} else {
				$('#quantity').text(num);
				$('#inquantity').val($('#quantity').text());

			}
		}
		function add() {
			var num = parseInt($('#quantity').text()) + 1;
			$('#quantity').text(num);
			$('#inquantity').val($('#quantity').text());

		}
	</script>
	<!-- 改 -->
		<div id="ShopPathLocation">

<div class="ui breadcrumb">
  <a class="section" href="<%=request.getContextPath() %>/index.html">Foodporn</a>
  <i class="right angle icon divider"></i>
  <a class="section" href="ShopHomePage.jsp">商城首頁</a>
  <i class="right angle icon divider"></i>
    <a class="section" href="ProductPage?product_type=${productvo.product_type}&action=goProductPage">${productvo.product_type} </a>
  <i class="right angle icon divider"></i>
  <div class="active section">${productvo.product_name} 
  
  
  </div>
</div>
</div>

<div id="ShopLsearch">
    <div class="ui icon input">
        <input type="text"  placeholder="你想找甚麼...">
        <i   class="inverted  circular search link icon"></i>
    </div>
</div>





<div id="shopProduct">
<!-- 品名 -->
<div id="producttitle">
    <p >${productvo.product_name} </p>
</div>
    <div id="Productcontext">
        <div id="productpicture">


            <img src="../../back-end/shop_product/Product_photoReader?product_id=${productvo.product_id}
			" alt="">

        </div>
        <div id="productdescript">
            <div id="descript1"><p>${productvo.content}</p></div>
            <div id="descript2"><p>價格</p>${productvo.product_price}</div>
            <!-- 購物車 -->
            		
			
			
<div id="carbtn">
    <div class="ui focus  input">
        <button id="decmun"class="ui  button red"><i class="minus icon "></i>  </button>


    <input type="text" class="ui input"
            id="countmun" value="1">
        <button id="plusmun"class="ui  button red"><i class="plus icon"></i>  </button>

       </div>
</div>


			
			
			
			
            <div id="descript3">
            
            數量：<input type="button" value="-" id="del" onclick="del()" />
					 <span name="quantity"
						id="quantity">1</span> 
						<input type="button" value="+" id="add"
						onclick="add()" />
            
            
            
            
            
            
            
            <FORM METHOD="post" ACTION="ShopCart"
 	style="margin-bottom: 0px;">
						<input type="hidden" id="inquantity" name="quantity" size="3"					
						value=1>  <input
						type="hidden" name="product_id"
						value="<%=productvo.getProduct_id()%>"> <input
						type="hidden" name="product_price"
						value="<%=productvo.getProduct_price()%>"> <input
						type="hidden" name="action" value="ADD">
						<input type="submit" value="加入購物車">
				</FORM></div>
        </div>

     
        <div class="prodictmessage">
            <div class="title">營養成分</div>
            <div class="hrdiv"> <hr></div>

            <div class="context">
                <table>
                

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
	</table>

            </div>
        </div>
   <div class="prodictmessage">
            <div class="title">營養成分</div>
         <div class="hrdiv"> <hr></div>
        <div class="context">
            <table></table>

        </div>
    </div>
    </div>
</div>
	
	
	
	
	

</body>
</html>