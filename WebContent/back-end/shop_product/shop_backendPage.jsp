<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!--套件-->
<!-- icon -->
 <link rel="shortcut icon" href="<%=request.getContextPath() %>/image/head-logo_nohead.ico"
	type="image/x-icon" />
<script src="<%=request.getContextPath()%>/plugin/jquery-3.4.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/plugin/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
<%--     <script src="<%=request.getContextPath() %>/plugin/Semantic-UI/semantic.js"></script> --%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/bootstrap-4.4.1-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/Semantic-UI/semantic.min.css">


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/shop_product/css/shopmaonback_end.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/mainback_endcss.css">
<link rel="stylesheet" href="css/BEproductCss.css">
<!--套件-->
<link rel="stylesheet" type="text/css"
	href="../../plugin/jquery-ui/jquery-ui.min.css" />

<script src="../../plugin/jquery-ui/jquery-ui.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
.content h2{
color: #E4002B;
line-height: 80px;
	padding-left: 25px
}


</style>
<title>商品管理頁面</title>
<body>
<%-- 	<%="現在地址:" + request.getContextPath()%> --%>
	<div class="container">
		<div class="box">
			<!--這裡是左邊選單-->
			<div class="left-bar">

				<a href="<%=request.getContextPath()%>/backEnd2.jsp"> <span
					class="fa fa-cloud-download"><img
						src="<%=request.getContextPath()%>/image/logo_nohead.png"
						alt="LOGO" width="200" height="200"></span></a>

				<div class="back-endlefecotext">
					<!-- LOGO -->

					<ul id="leftMenu">

						<li class="lialist">
							<div class="iconstatus" style="display: none">OPEN</div> <span
							style="color: #E4002B" class=" productlist"
							title="open or close this section " href="#">商品管理 <i
								class="angle up icon" style="color: #E4002B"></i>
						</span>
							<ul class="BList">
								<li><a href="ShopPageServlet?action=listallEX">查看商品</a></li>
								
								<li><a href="<%=request.getContextPath() %>/back-end/shop_product/addProduct.jsp">新增商品</a></li>
								
								<li><a href="ShopPageServlet?action=listAllReceipe">查看食譜料理包</a></li>
								<li><a href="ShopPageServlet?action=listAllReceipeEXcheck">查看審核中料理包</a></li>
<!-- 								<li><a href="ShopPageServlet?action=IrregularPage">???????????</a></li> -->



							</ul>
						</li>

						<li class="lialist">
							<div class="iconstatus" style="display: none">CLOSE</div> <span
							class=" orderlist" title="open or close this section ">商城訂單管理
								<i class="angle down icon"></i>
						</span>
							<ul class="BList">

								<li><a
									href="<%=request.getContextPath()%>/back-end/shop_order/Order_backendPage.jsp">查看全部訂單</a></li>
								<li><a
									href="<%=request.getContextPath()%>/back-end/shop_order/OrderPage?action=changepage&pagemessage=waitpage">未出貨訂單</a></li>
								<li><a
									href="<%=request.getContextPath()%>/back-end/shop_order/OrderPage?action=changepage&pagemessage=traveling">運送中訂單</a></li>
								<li><a
									href="<%=request.getContextPath()%>/back-end/shop_order/OrderPage?action=changepage&pagemessage=complete">已完成訂單</a></li>
								<li><a
									href="<%=request.getContextPath()%>/back-end/shop_order/OrderPage?action=changepage&pagemessage=cancel">交易取消訂單</a></li>

							</ul>
						</li>

<!-- 						<li class="lialist"> -->
<!-- 							<div class="iconstatus" style="display: none">CLOSE</div> <span -->
<!-- 							class=" Instantorderlist" title="open or close this section ">配送訂單管理 -->
<!-- 								<i class="angle down icon"></i> -->
<!-- 						</span> -->
<!-- 							<ul class="BList"> -->

<!-- 								<li><a -->
<%-- 									href="<%=request.getContextPath()%>/back-end/Instant_order/Instant_order_backendPage.jsp">查看全部訂單</a></li> --%>
<!-- 								<li><a -->
<%-- 									href="<%=request.getContextPath()%>/back-end/Instant_order/Instant_delivery_orderServlet?action=traveling">運送中訂單</a></li> --%>

<!-- 							</ul> -->
<!-- 						</li> -->

					</ul>



				</div>



				<span class="fa fa-sign-out">登出</span>
			</div>
			<div class="wrapper" style="background-color: #f9f9f9">
				<!--  -->


				<%
					if (session.getAttribute("backendpage") == null) {
				%>
				<jsp:include page="listAllProduct.jsp" />
				<%
					} else {
						String includecontext = (String) session.getAttribute("backendpage");
				%>
				<%
					if (includecontext.equals("listallEX")) {
				%>
				<jsp:include page="listAllProduct.jsp" />


				<%
					} else if (includecontext.equals("listAllReceipe")) {
				%>
				<jsp:include page="getAllReceipe.jsp" />

				<%
					}

						else if (includecontext.equals("listAllReceipeEXcheck")) {
				%>
				<jsp:include page="CheckingReceipe.jsp" />

				<%
					} else if (includecontext.equals("productAddPage")) {
				%>


				<jsp:include page="addProduct.jsp" />

				<%
					}
					}
				%>
				<!-- 跳窗 -->
				<c:if test="${openMod=='openMod'}">
					<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
						aria-labelledby="basicModal" aria-hidden="true">
						<div class="modal-dialog modal-lg" style="width: 65%;">
							<div class="modal-content">

								<div class="modal-header">
									<h2 class="modal-title" id="myModalLabel">商品訂單管理</h2>
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
								</div>

								<div class="modal-body">
									<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->

									<jsp:include page="listOneProduct.jsp" />

									<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
								</div>

							</div>
						</div>
					</div>

					<script>
						$("#basicModal").modal({
							show : true
						});
						$(document).ready(function() {
							$('.btn-primary').on('click', function() {
								$('#update_form').submit();
							});
						});
					</script>
				</c:if>

				<!-- ---------------- -->
				<c:if test="${openMod=='upate'}">
					<div class="modal fade" id="basicModal2" tabindex="-1"
						role="dialog" aria-labelledby="basicModal" aria-hidden="true">
						<div class="modal-dialog modal-lg" style="width: 65%;">
							<div class="modal-content">

								<div class="modal-header">
<%-- 									<h2 class="modal-title" id="myModalLabel">品名:${productvo.product_name}</h2> --%>
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
								</div>

								<div class="product-upatefrom modal-body">
									<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->

									<jsp:include page="updata_product.jsp" />

									<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
								</div>

							</div>
						</div>
					</div>

					<script>
						$("#basicModal2").modal({
							show : true
						});
						$(document).ready(function() {
							$('.btn-primary').on('click', function() {
								$('#update_form').submit();
							});
						});
					</script>
				</c:if>









			</div>


		</div>
	</div>
	<script>
		$(".onproduct")
				.click(
						function() {
							$(this).siblings(".isrevise").val(0);
							isrevise();
							let product_id = $(this).siblings(".product_id")
									.val();
							$(".checking").parent().siblings("a").addClass(
									".disa")
							$(".checking").siblings("a").removeClass(".disa");

							let urladdress = "ProductChange?product_status=0&product_id="
									+ product_id;
							$(this).removeAttr("style");
							$(this).css({
								"color" : "white",
								"background-color" : 'green'
							});
							$(this).siblings(".offproduct").removeAttr("style");

							$.ajax({
								url : urladdress,
								type : "GET",
								sucess : function() {
								}
							});

						});
		$(".offproduct")
				.click(
						function() {
							$(this).siblings(".isrevise").val(1);
							isrevise();
							let product_id = $(this).siblings(".product_id")
									.val();
							let urladdress = "ProductChange?product_status=1&product_id="
									+ product_id;
							$(this).removeAttr("style");
							$(this).css({
								"color" : "white",
								"background-color" : 'blue'
							});
							$(this).siblings(".onproduct").removeAttr("style");
							$.ajax({
								url : urladdress,
								type : "GET",
								sucess : function() {
								}
							});

						});
	</script>

	<script>
	<!--判斷是否關閉修改功能-->
		function isrevise() {
			$(".isrevise").each(
					function() {
						if ($(this).val() == 1) {
							$(this).parent().siblings(".upate").find(
									".updateproduct").attr('disabled', true);
						} else if ($(this).val() == 0) {
							$(this).parent().siblings(".upate").find(
									".updateproduct").attr('disabled', false);
						}
					})
		}
	</script>
	<!-- 左邊選單 -->
	<script>
	<!--判斷是否關閉修改功能-->
		function isrevise() {
			$(".isrevise").each(
					function() {
						if ($(this).val() == 1) {
							$(this).parent().siblings(".upate").find(
									".updateproduct").attr('disabled', false);
						} else if ($(this).val() == 0) {
							$(this).parent().siblings(".upate").find(
									".updateproduct").attr('disabled', true);
						}
					})
		}
	</script>
	<script>
		$("#leftMenu > li ").children('span').not(".productlist").find("+ul")
				.slideUp(1);

		$("#leftMenu > li ").children('span').click(function() {
			$(this).find("+ ul").slideToggle("fast");

		});
	</script>
	<script>
		$(".lialist i").hover(function() {
			$(this).css("color", "#E4002B");
		}, function() {
			$(this).css("color", "#707070");

		});

		$('.lialist span').click(function() {
			if ($(this).siblings(".iconstatus").html() == "OPEN") {
				$(this).children('i').removeClass("up");
				$(this).children('i').addClass("down");
				$(this).css("color", "#707070");

				$(this).children('i').css("color", "#707070");

				$(this).siblings(".iconstatus").html("CLOSE");

			} else if ($(this).siblings(".iconstatus").html() == "CLOSE") {
				$(this).children('i').removeClass("down");
				$(this).children('i').addClass("up");
				$(this).css("color", "#E4002B");

				$(this).children('i').css("color", "#E4002B");

				$(this).siblings(".iconstatus").html("OPEN");

			}

		})
		//  網頁啟動就執行

		$(document).ready(isrevise());
	</script>
</body>
</html>