<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
	Map<Integer, String> ostatus = new HashMap<>();
	ostatus.put(0, "已成立");
	ostatus.put(1, "運送中");
	ostatus.put(2, "已完成");
	ostatus.put(3, "取消訂單");
	Map<Integer, String> pstatus = new HashMap<>();
	pstatus.put(0, "已繳費");
	pstatus.put(1, "未繳費");
	pstatus.put(3, "異常");

	Map<Integer, String> pmethod = new HashMap<>();
	pmethod.put(0, "點數支付");
	pmethod.put(1, "貨到付款");
	pmethod.put(2, "線上支付");
%>

<c:set var="ostatus" value="<%=ostatus%>" scope="request" />
<c:set var="pstatus" value="<%=pstatus%>" scope="request" />
<c:set var="pmethod" value="<%=pmethod%>" scope="request" />

<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/image/head-logo_nohead.ico"
	type="image/x-icon" />
<!--套件-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script
	src="<%=request.getContextPath()%>/plugin/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/bootstrap-4.4.1-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/Semantic-UI/semantic.min.css">


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/shop_product/css/shopmaonback_end.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/mainback_endcss.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/shop_product/css/BEproductCss.css">
<!--套件-->
<link rel="stylesheet" type="text/css"
	href="../../plugin/jquery-ui/jquery-ui.min.css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="../../plugin/jquery-ui/jquery-ui.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>及時訂單管理頁面</title>
<body style="background-color: pink">
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
							style="color: #E4002B" class="Instantorderlist"
							class=" Instantorderlist" title="open or close this section ">配送訂單管理
								<i class="angle up icon" style="color: #E4002B"></i>
						</span>
							<ul class="BList">

								<li><a
									href="<%=request.getContextPath()%>/back-end/Instant_order/Instant_order_backendPage.jsp">查看全部訂單</a></li>
								<li><a
									href="<%=request.getContextPath()%>/back-end/Instant_order/Instant_order_backendPage.jsp?pagemessage=Traveling">運送中訂單</a></li>
								<li><a
									href="<%=request.getContextPath()%>/back-end/Instant_order/Instant_order_backendPage.jsp?pagemessage=Finish">已完成訂單</a></li>
								<li><a
									href="<%=request.getContextPath()%>/back-end/Instant_order/Instant_order_backendPage.jsp?pagemessage=cancel">已取消訂單</a></li>
						</li>
					</ul>
					<span class="fa fa-sign-out">登出</span>
				</div>
			</div>

			<div class="wrapper">
				<!--  -->


				<%
					if (request.getAttribute("pagemessage") == null) {
				%>
				<jsp:include page="listAllInstant_order.jsp" />

				<%
					} else if (((String) request.getAttribute("pagemessage")).equals("all")) {
				%>
				<jsp:include page="listAllInstant_order.jsp" />
				<%
					} else if (((String) request.getAttribute("pagemessage")).equals("traveling")) {
				%>
				<%-- 				<jsp:include page="TravelingInstant_order.jsp" /> --%>
				<%
					} else if (((String) request.getAttribute("pagemessage")).equals("getPositon")) {
				%>
				<jsp:include page="getPosition.jsp" />
				<%
					}
				%>


			</div>


		</div>
	</div>


	<!-- 跳窗 -->
	<c:if test="${opendialog=='lookmore'}">
		<div class="modal fade" id="basicModal1" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg" style="width: 65%;">
				<div class="modal-content">

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

<c:if test="${openModal!=null}">
		<div class="modal fade" id="basicModal1" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg" style="width: 65%;">
				<div class="modal-content">

					<div class="modal-header">
						<h2 class="modal-title" id="myModalLabel">訂單位置</h2>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>

					<div class="modal-body">
						<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->

						<jsp:include page="googleMap.jsp" />

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







	<!-- 左邊選單 -->
	<script>
		$("#leftMenu > li ").children('span').not(".Instantorderlist").find(
				"+ul").slideUp(1);

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
	</script>
	<style>
	
	#ordertable tr:nth-child(1){
	background-color: #f9f9f9;
	color: #E4002B;}
	.ordertr1{
	background-color: #f9f9f9;}
	
	</style>
</body>
</html>