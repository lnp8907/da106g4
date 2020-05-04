<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
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
		
                        <span class="fa fa-cloud-download"><img src="<%=request.getContextPath() %>/image/logo_nohead.png" alt="LOGO"
                        width="200" height="200"></span>
		
		  <div class="back-endlefecotext">
		  <!-- LOGO -->
		  
                                    <ul id="leftMenu">
                                    
                                        <li class="lialist">
                                            <div class="iconstatus" style="display: none">CLOSE</div>
                                            <span  class=" productlist" title="open or close this section " >商品管理
                                                <i class="angle down icon"></i>
                                            </span>
                                            <ul class="BList" >
                                                <li><a href="<%=request.getContextPath() %>/back-end/shop_product/ShopPageServlet?action=listallEX">查看商品</a></li>
                                        <li><a href="ShopPageServlet?action=addProduct&whichPage=${whichPage}">新增商品</a></li>
                                                <li><a href="ShopPageServlet?action=listAllReceipe">查看食譜料理包</a></li>
                                                <li><a href="ShopPageServlet?action=listAllReceipeEXcheck">查看審核中料理包</a></li>
                                                <li><a href="ShopPageServlet?action=IrregularPage">???????????</a></li>
                                              
                                                
                                                
                                            </ul>
                                        </li>

                                        <li class="lialist active">
                                            <div class="iconstatus" style="display: none">OPEN</div>

                                            <span style="color: #E4002B" class=" orderlist " title="open or close this section " href="#">商城訂單管理
                                                <i class="angle up icon"></i>
                                            </span>
                                            <ul class="BList " >

                                                <li><a href="<%=request.getContextPath() %>/back-end/shop_order/Order_backendPage.jsp">查看全部訂單</a></li>
                                                <li><a href="#">未出貨訂單</a></li>
                                                <li><a href="#">已完成訂單</a></li>
                                            </ul>
                                        </li>
                                 
                                    <li class="lialist">
                                            <div class="iconstatus" style="display: none">CLOSE</div>

                                            <span  class=" Instantorderlist" title="open or close this section " >配送訂單管理
                                                <i class="angle down icon"></i>
                                            </span>
                                            <ul class="BList" >

                                           
                                                <li><a href="<%=request.getContextPath() %>/back-end/Instant_order/Instant_order_backendPage.jsp">查看全部訂單</a></li>
                                                 <li><a href="<%=request.getContextPath() %>/back-end/Instant_order/Instant_delivery_orderServlet?action=traveling">運送中訂單</a></li>
                                    
                                            </ul>
                                        </li>
                                        
                                        
                                        
                                        
                                    </ul>
                                 
                                 
                                 
                                 
                                    </ul>






                                </div>



                                <span class="fa fa-sign-out">登出</span>
                            </div>
                            <div class="wrapper">
			<!--  -->
	<c:set var="url" value="<%=request.getRequestURI() %>" scope="request" />
	
	 <jsp:include page="listAllShopOrder.jsp" />

	
	


		</div>


	</div>
</div>
<!-- 跳窗.................. -->
<c:if test="${opendialog=='addressupdate'}">
				<div class="modal fade" id="basicModal1" tabindex="-1" role="dialog"
					aria-labelledby="basicModal" aria-hidden="true">
					<div class="modal-dialog modal-lg" style="width: 65%;">
						<div class="modal-content" >

							<div class="modal-header">
								<h2 class="modal-title" id="myModalLabel">訂單編號:${ordvo.order_no}</h2>
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							</div>

							<div class="modal-body">
								<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->

 								<jsp:include page="UpateOrderAddress.jsp" /> 

								<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
							</div>

						</div>
					</div>
				</div>

				<script>
					$("#basicModal1").modal({
						show : true
					});
					$(document).ready(function() {
						$('.btn-primary').on('click', function() {
							$('#update_form').submit();
						});	
					});
				</script>
			</c:if>
<!--  -->
<c:if test="${opendialog=='lookmore'}">
				<div class="modal fade" id="basicModal1" tabindex="-1" role="dialog"
					aria-labelledby="basicModal" aria-hidden="true">
					<div class="modal-dialog modal-lg" style="width: 65%;">
						<div class="modal-content" >

							<div class="modal-header">
								<h2 class="modal-title" id="myModalLabel">訂單編號:${ordvo.order_no}</h2>
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							</div>

							<div class="modal-body">
								<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->

 								<jsp:include page="listOneShopOrder.jsp" /> 

								<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
							</div>

						</div>
					</div>
				</div>

				<script>
					$("#basicModal1").modal({
						show : true
					});
					$(document).ready(function() {
						$('.btn-primary').on('click', function() {
							$('#update_form').submit();
						});	
					});
				</script>
			</c:if>





<!-- ..................... -->
<!-- 左邊選單 -->
<script>
        $("#leftMenu > li ").children('span').not(".orderlist").find("+ul").slideUp(1);

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