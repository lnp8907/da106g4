<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.stream.Collectors"%>

<%
	ProductService Psvc = new ProductService();
	Collection<ProductVO> list = Psvc.getAll();
	list = list.stream().filter(p -> p.getProduct_status() > 2).collect(Collectors.toList());
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有異常狀態商品</title>
</head>

<body>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>




	<div class="content">
		<div class="menu">
			<!-- 卡片內容上方留白的起始標籤 -->
		</div>
		<!-- 卡片內容上方留白的結束標籤 -->
	</div>
	<div class="grid">
		<!-- 卡片內容起始標籤 -->
		<table class="ui selectable celled table">
			<thead>
				<tr id="Producttitle">
					<th>商品圖</th>
					<th>類別</th>
					<th class="product_idth">商品編號</th>
					<th class="recipe_th">食譜編號</th>
					<th>商品名稱</th>
					<th>商品單價</th>
					<th>商品狀態</th>
					<th>商品詳細成分</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
				<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

				<%@ include file="../file/page1.file"%>
				<div>${fn:length(list)<1?"無異常":"請修正以下錯誤"}</div>

			</thead>



			<tbody id="ProductContext">

				<c:forEach var="productvo" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">

					<tr>
						<td><img width=80px height=70px
							src="Product_photoReader?product_id=${productvo.product_id}
			"></td>
						<td>${productvo.product_type}</td>
						<td class="product_idtd">${productvo.product_id}</td>
						<td class="recipe_td">${productvo.recipe_id}</td>

						<td>${productvo.product_name}</td>
						<td>${productvo.product_price}</td>
						<%
							Map<Integer, String> map = new HashMap<>();
								map.put(1, "未上架");
								map.put(0, "已上架");
								request.setAttribute("productstatus", map);
						%>
						<c:set var="status" value="${productvo.product_status}" />



						<td><input type="hidden" value="${productvo.product_id}">
							${productvo.product_status>2?"異常":productvo.product_status}</td>




						<!-- 按鈕 -->

						<td>
							<!-- 詳細頁面 -->
							<FORM METHOD="post" ACTION="Productmanage"
								style="margin-bottom: 0px;">
								<input type="submit" value="查看更多"> <input type="hidden"
									name="product_id" value="${productvo.product_id}"> <input
									type="hidden" name="action" value="listOneProduct">
							</FORM>
						</td>

						<td>
							<!-- 修改 -->
							<FORM METHOD="post" ACTION="Productmanage"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="product_id" value="${productvo.product_id}"> <input
									type="hidden" name="action" value="ProductUpdatePage">
							</FORM>
						</td>
						<c:set var="id" value="${productvo.product_id}" />
						<td>
							<%
								String id = (String) pageContext.getAttribute("id");
							%> <!-- 刪除 -->


							<FORM METHOD="post" ACTION="Productmanage"
								style="margin-bottom: 0px;">
								<input type="hidden" name="action" value="delete"> <input
									class='ui  icon button'
									<%=Psvc.isProduct_idFK(id) ? "" : "disabled='disabled'"%>
									type="submit" value="刪除"> <input type="hidden"
									name="product_id" value="${productvo.product_id}"> <input
									type="hidden" name="whichPage" value="<%=whichPage%>">
								<input type="hidden" name="requestURL"
									value="<%=request.getServletPath()%>">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="../file/page2.file"%>
	</div>
	<!-- end of grid -->


	<script>
		$(".onproduct")
				.click(
						function() {
							let product_id = $(this).siblings("input").val();

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
							let product_id = $(this).siblings("input").val();
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
		$(".onproduct").click(function() {

		});
		$(".offproduct").click(function() {

		});
	</script>


	<script>
		if ($('.recipe_td').html() == "") {

			$('.recipe_td').css('display', 'none');
			$('.recipe_th').css('display', 'none');
			$('.recipe_th').html("");
			// 	$('.product_idth').attr("colspan", 2);
			// 	$('.product_idtd').attr("colspan", 2);
		} else if ($('.recipe_td').html() == "" && $('.recipe_th').html() != "") {

		}
	</script>
</body>
</html>