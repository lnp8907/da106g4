<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*" %>
	
	<% 
Map<Integer,String> ostatus=new HashMap<>();
ostatus.put(0,"已成立");
ostatus.put(1,"運送中");
ostatus.put(2,"已完成");
ostatus.put(3,"取消訂單");
Map<Integer,String> pstatus=new HashMap<>();
pstatus.put(0,"已繳費");
pstatus.put(1,"未繳費");
pstatus.put(3,"異常");



Map<Integer,String> pmethod=new HashMap<>();
pmethod.put(0,"點數支付");
pmethod.put(1,"貨到付款");
pmethod.put(2,"線上支付");
%>

<c:set var="ostatus" value="<%=ostatus %>" scope="request"/>
<c:set var="pstatus" value="<%=pstatus %>" scope="request"/>
<c:set var="pmethod" value="<%=pmethod %>" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <!--套件-->
    <script src="<%=request.getContextPath() %>/plugin/jquery-3.4.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/plugin/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugin/bootstrap-4.4.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugin/Semantic-UI/semantic.min.css">


    <link rel="stylesheet" href="<%=request.getContextPath() %>/back-end/shop_product/css/shopmaonback_end.css">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/back-end/css/mainback_endcss.css">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/back-end/shop_product/css/BEproductCss.css">
    <!--套件-->
<link rel="stylesheet" type="text/css" href="../../plugin/jquery-ui/jquery-ui.min.css"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="../../plugin/jquery-ui/jquery-ui.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>後端頁面模板</title>


<body>
<%="現在地址:"+request.getContextPath() %>
<div class="container">
	<div class="box">
		<!--這裡是左邊選單-->
		<div class="left-bar">
		
                       <a href="<%=request.getContextPath() %>/backEnd.html"> <span class="fa fa-cloud-download"><img src="<%=request.getContextPath() %>/image/logo_nohead.png" alt="LOGO"
                        width="200" height="200"></span></a>
		
		  <div class="back-endlefecotext">
		  <!-- LOGO -->
                                    <ul id="leftMenu">         
                                          <li class="lialist">
                                            <div class="iconstatus" style="display: none">OPEN</div>

                                            <span style="color: #E4002B" class="Instantorderlist" class=" Instantorderlist" title="open or close this section " >配送訂單管理
                                                <i class="angle up icon" style="color: #E4002B"></i>
                                            </span>
                                            <ul class="BList" >

                                                <li><a href="<%=request.getContextPath() %>/back-end/Instant_order/Instant_order_backendPage.jsp">查看全部訂單</a></li>
                                                 <li><a href="<%=request.getContextPath() %>/back-end/Instant_order/Instant_delivery_orderServlet?action=traveling">運送中訂單</a></li>
                                        </li>                                    
                                    </ul>
                                </div>
                                <span class="fa fa-sign-out">登出</span>
                            </div>
                            <div class="wrapper">
			<!--  -->
	
	
<%if(request.getAttribute("pagemessage")==null){ %>
		 <jsp:include page="listAllInstant_order.jsp" />
<%}else if(((String)request.getAttribute("pagemessage")).equals("all")){%>
		 <jsp:include page="listAllInstant_order.jsp" />	
<%}else if(((String)request.getAttribute("pagemessage")).equals("traveling")){%>
		 <jsp:include page="TravelingInstant_order.jsp" />	
<%}else if(((String)request.getAttribute("pagemessage")).equals("getPositon")){%>
<jsp:include page="getPosition.jsp" />	
<%} %>
	
	

	
	
	
	
	
		
	
	
	


		</div>


	</div>
</div>

<!-- 左邊選單 -->
<script>
        $("#leftMenu > li ").children('span').not(".Instantorderlist").find("+ul").slideUp(1);

        $("#leftMenu > li ").children('span').click(function() {
            $(this).find("+ ul").slideToggle("fast");

        });
</script>
<script>
    $(".lialist i").hover(function(){
        $(this).css("color","#E4002B");
    },function(){
        $(this).css("color","#707070");


    });

    $('.lialist span').click(function () {
        if($(this).siblings(".iconstatus").html()=="OPEN"){
            $(this).children('i').removeClass("up");
            $(this).children('i').addClass("down");
            $(this).css("color","#707070");

            $(this).children('i').css("color","#707070");

            $(this).siblings(".iconstatus").html("CLOSE") ;

        }
        else if($(this).siblings(".iconstatus").html()=="CLOSE"){
            $(this).children('i').removeClass("down");
            $(this).children('i').addClass("up");
            $(this).css("color","#E4002B");

            $(this).children('i').css("color","#E4002B");

            $(this).siblings(".iconstatus").html("OPEN") ;

        }


    })


</script>
</body>
</html>