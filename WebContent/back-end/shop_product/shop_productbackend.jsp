<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
    <!--套件-->
    <script src="../../plugin/jquery-3.4.1.min.js"></script>
    <script src="../../plugin/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
    <script src="../../plugin/Semantic-UI/semantic.js"></script>

    <link rel="stylesheet" href="../../plugin/bootstrap-4.4.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../plugin/Semantic-UI/semantic.min.css">

    <link rel="stylesheet" href="../css/mainback_endcss.css">
        <link rel="stylesheet" href="../css/BEproductCss.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後端頁面模板</title>

    <script src="js/MainProductPage.js"> </script>

<body>

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
                                                <li><a href="ShopPageServlet?action=listallEX">查看商品</a></li>
                                                <li><a href="product.do?action=addProduct">新增商品</a></li>
                                                <li><a href="ShopPageServlet?action=listAllReceipe">查看食譜料理包</a></li>
                                              
                                                
                                                
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
			<!--  -->
	
	
	<%
	if(session.getAttribute("backendpage")==null){
		
		
	}
	else{
	String includecontext=(String)session.getAttribute("backendpage");
	if(includecontext.equals("listallEX")){%>
		 <jsp:include page="listAllProduct.jsp" />
		
		
		 <% }
	else if(includecontext.equals("listAllReceipe")){%>
		 <jsp:include page="getAllReceipe.jsp" />

		
	<%}}
	
	%>
	

	
	
	
	
	
		
	
	
	


		</div>


	</div>
</div>
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