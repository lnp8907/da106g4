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

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
	crossorigin="anonymous">
	
</script>


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
	width: 1000px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>新增商品</h3>
			</td>
			<td>
				<h4>
					<a href="ProductPage.jsp"><img
						src="<%=request.getContextPath()%>/image/FoodPron_Logo.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
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
		// $('#adddetail').click(
		// 	function(){
		// 		$("#productdetail").toggle(1000, function(){
		// 			$("#adddetail").html("關閉並清除");

		// 			},function(){
		// 				$("#adddetail").html("新增產品資訊");

		// 			}
		// 		);
		// 	}
		// );

		$('#adddetail').click(function() {
			$("#productdetail").toggle("slow");
		});
	</script>
</body>


</html>