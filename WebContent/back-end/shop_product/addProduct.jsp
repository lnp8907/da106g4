<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.product.model.*"%>
<%
	ProductVO productvo = (ProductVO) request.getAttribute("productvo");
%>
    <%@ page import="java.util.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品新增頁面</title>
</script>
  <!--套件-->
    <script src="<%=request.getContextPath() %>/plugin/jquery-3.4.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/plugin/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/plugin/Semantic-UI/semantic.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugin/bootstrap-4.4.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugin/Semantic-UI/semantic.min.css">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/back-end/css/mainback_endcss.css">
        <link rel="stylesheet" href="css/BEproductCss.css">
    <!--套件-->
            <link rel="stylesheet" type="text/css" href="../../plugin/jquery-ui/jquery-ui.min.css"/>

    <script src="../../plugin/jquery-ui/jquery-ui.min.js"></script>
</head>
<body bgcolor='white'>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container">
	<div class="box">
		<!--這裡是左邊選單-->
		<div class="left-bar">
		  <div class="back-endlefecotext">
                                    <ul id="leftMenu">
                                        <li class="lialist">
                                            <div class="iconstatus" style="display: none">OPEN</div>
                                            <a style="color: #E4002B" class=" productlist" title="open or close this section " href="#">商品管理
                                                <i class="angle up icon" style="color: #E4002B"></i>
                                            </a>
                                            <ul class="BList" >
                                                <li><a  href="ShopPageServlet?action=listallEX">查看商品</a></li>
                                        <li><a  id="Aaddproducr" style="color: red" href="ShopPageServlet?action=addProduct&whichPage=${whichPage}" >新增商品</a></li>
                                                <li><a href="ShopPageServlet?action=listAllReceipe">查看食譜料理包</a></li>
                                                <li><a href="ShopPageServlet?action=listAllReceipeEXcheck">查看審核中料理包</a></li>
                                                <li><a href="ShopPageServlet?action=IrregularPage">???????????</a></li>
                                              
                                            <style>
                                            #Aaddproducr{
                                            pointer-events:none;
                                            
                                            }
                                            </style>
                                            </ul>
                                        </li>

                                        <li class="lialist">
                                            <div class="iconstatus" style="display: none">CLOSE</div>

                                            <a class=" orderlist" title="open or close this section " href="#">管理訂單
                                                <i class="angle down icon"></i>
                                            </a>
                                            <ul class="BList" >

                                                <li><a href="#">查看全部訂單</a></li>
                                                <li><a href="#">查詢訂單</a></li>
                                                <li><a href="#">???????</a></li>
                                            </ul>
                                        </li>
                                    </ul>



                                </div>



                                <span class="fa fa-sign-out">登出</span>
                            </div>
                            <div class="wrapper">
	
	
	
	
	<!-- 注意 -->
	<FORM METHOD="post" ACTION="product.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<!-- 圖片 -->
			<tr>
				<td>商品圖片:</td>
				<!-- 按鈕 -->
				<td><input type="file" id="imgView" name="product_photo"
					size="45" accept="image/gif, image/jpeg, image/png"> <img
					img src="<%=request.getContextPath()%>/image/FoodPron_Logo.png"
					id="preview_progressbarTW_img" width=100px height=100px; src="#" />
				</td>

			</tr>
			<tr>
				<td>商品類別:</td>
				<td><select name="product_type">
							<%List<String>producttype=new ArrayList(); 
		producttype.add("水果類");
		producttype.add("肉類");
		producttype.add("蔬菜類");
		producttype.add("乳品類");
		producttype.add("油脂類");
		producttype.add("魚貝類");
		producttype.add("菇類");
		producttype.add("穀物類");
		producttype.add("澱粉纇");
		producttype.add("酒類");
		producttype.add("調味料及香辛料類");
		producttype.add("料理組合包");
		%>
		<%for(String type:producttype){%>
		
		
		
		<%="<option value='"+type+"'"%>
		<%if(productvo!=null){%>
			<%=((productvo.getProduct_type()).equals(type))?"selected":"" %>
		<% }%>
		<%=">"+type+"</option>" %>
		<%} %>
				</select></td>
			</tr>
			<tr>
				<td>商品名:</td>
				<td><input type="TEXT" name="product_name" size="45"
					placeholder="請商品名"
					value="<%=(productvo == null) ? "" : productvo.getProduct_name()%>" /></td>
			</tr>

			<tr>
				<td>商品價格:</td>
				<td><input type="TEXT" name="product_price" size="45"
					placeholder="請填入價格"
					value="<%=(productvo == null) ? "" : productvo.getProduct_price()%>" /></td>
			</tr>
			<tr>
				<td>是否直接上架:</td>
				<td>
					
					<input type="radio" name="product_status" value=1 checked>否<br>
        <input type="radio" name="product_status" value=0> 是<br>
					
					</td>
			</tr>
			<tr>
				<td>
					<button id="adddetail" type="button">新增產品資訊</button>
				</td>
		</table>

		<table id="productdetail" style="display: none">


			<tr>
				<td>熱量:</td>
				<td><input type="TEXT" name="calorie" size="45"
					placeholder="請填入熱量"
					value="<%=(productvo == null) ? 0.0 : productvo.getCalorie()%>" /></td>
			</tr>
			<tr>
				<td>碳水化合物:</td>
				<td><input type="TEXT" name="carbohydrate" size="45"
					placeholder="請填入碳水化合物"
					value="<%=(productvo == null) ? 0.0 : productvo.getCarbohydrate()%>" /></td>
			</tr>
			<tr>
				<td>蛋白質:</td>
				<td><input type="TEXT" name="protein" size="45"
					placeholder="請填入蛋白質"
					value="<%=(productvo == null) ? 0.0 : productvo.getProtein()%>" /></td>
			</tr>
			<tr>
				<td>脂質:</td>
				<td><input type="TEXT" name="fat" size="45" placeholder="請填入脂質"
					value="<%=(productvo == null) ? 0.0 : productvo.getFat()%>" /></td>
			</tr>
			<tr>
				<td>維生素B:</td>
				<td><input type="TEXT" name="vitamin_B" size="45"
					placeholder="請填入維生素B"
					value="<%=(productvo == null) ? 0.0 : productvo.getVitamin_B()%>" /></td>
			</tr>
			<tr>
				<td>維生素C:</td>
				<td><input type="TEXT" name="vitamin_C" size="45"
					placeholder="請填入維生素C"
					value="<%=(productvo == null) ? 0.0 : productvo.getVitamin_B()%>" /></td>
			</tr>
			<tr>
				<td>鈉含量:</td>
				<td><input type="TEXT" name="salt" size="45"
					placeholder="請填入鈉含量"
					value="<%=(productvo == null) ? 0.0 : productvo.getSalt()%>" /></td>
			</tr>
			<tr>
				<td>膳食纖維:</td>
				<td><input type="TEXT" name="vagetbale" size="45"
					placeholder="請填入膳食纖維"
					value="<%=(productvo == null) ? 0.0 : productvo.getVagetbale()%>" /></td>
			</tr>
			<tr>
				<td>內容:</td>
				<td><input type="TEXT" name="content" size="45"
					placeholder="請填入內容"
					value="<%=(productvo == null) ? 0.0 : productvo.getContent()%>" /></td>
			</tr>
	<% String whichPag=(String)request.getAttribute("whichPage");%>

	<input	type="hidden" name="whichPage" value="<%=whichPag%>"> 

		</table>
		


		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
			</FORM>
				</div>


	</div>
</div>
	<script>
		$("#imgView").change(function() {

			readURL(this);
		});

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#preview_progressbarTW_img")
							.attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		$('#adddetail').click(function() {
			$("#productdetail").toggle("slow");
		});
	</script>
	
<script>
        $("#leftMenu > li ").children('a').not(".productlist").find("+ul").slideUp(1);

        $("#leftMenu > li ").children('a').click(function() {
            $(this).find("+ ul").slideToggle("fast");

        });
</script>
<script>
    $(".lialist i").hover(function(){
        $(this).css("color","#E4002B");
    },function(){
        $(this).css("color","#707070");


    });

    $('.lialist').click(function () {
        if($(this).children(".iconstatus").html()=="OPEN"){
            $(this).find('i').removeClass("up");
            $(this).find('i').addClass("down");
            $(this).children('a').css("color","#707070");
            $(this).find('i').css("color","#707070");

            $(this).children(".iconstatus").html("CLOSE") ;

        }
        else if($(this).children(".iconstatus").html()=="CLOSE"){
            $(this).find('i').removeClass("down");
            $(this).find('i').addClass("up");
            $(this).children('a').css("color","#E4002B");
            $(this).find('i').css("color","#E4002B");

            $(this).children(".iconstatus").html("OPEN") ;

        }


    })


</script>
	
</body>


</html>