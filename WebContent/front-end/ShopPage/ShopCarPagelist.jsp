
<!DOCTYPE html>
<html>
<head>
<style>
.disa{
cursor:pointer;
pointer-events: none;

}

</style>
<%@ page
	import="java.util.* ,com.order_detail.model.Order_detailVO,com.recipe.model.RecipeVO"%>

<%@page import="com.recipe.model.RecipeService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<title>購物車畫面</title>
</head>

<body>
	<%
		Vector<Order_detailVO> buyProductlist = null;
		@SuppressWarnings("unchecked")
		int alltot = 0;
		if ((Vector<Order_detailVO>) session.getAttribute("productCar") != null) {
			buyProductlist = (Vector<Order_detailVO>) session.getAttribute("productCar");
			session.setAttribute("buyProductlist", buyProductlist);

		}

		ProductService Psvc = new ProductService();
	%>
	<div id="progress-rate">
		<div class="progesssize ui steps">
			<a class="active step"> <i class="shopping cart icon"
				style="color: #e4002b"></i>
				<div class="content">
					<div class="title" style="color: #e4002b;">請確認購物車</div>
					<div class="description">選擇您的購買清單</div>
				</div>
			</a> <a class="step">
				<div class="content">
					<div class="title">結帳</div>
					<div class="description">輸入付款資訊</div>
				</div>
			</a> <a class="step">
				<div class="content">
					<div class="title">完成</div>
					<div class="description">恭喜完成訂單</div>
				</div>
			</a>
		</div>


	</div>
	<div id="showmember">
		敬愛的會員<font class="membername">宏哥</font>你好:
	</div>

	<div id="tablecontext">
		<div id="checkboxwithpeoduct">
			<table>
				<tr>
					<td><input id="checkproductall" type="checkbox"></td>
					<td>商品名</td>
					<td>價格</td>
					<td>數量</td>
					<td>小計</td>
					<td>移除</td>
				</tr>
			</table>
		</div>
		待結帳列表:<%
			if (session.getAttribute("selecttlist") != null) {
				Vector<Order_detailVO> selectlist = (Vector<Order_detailVO>) session.getAttribute("selecttlist");
		%>
		<%=selectlist%>

		<%
			}
		%>
		<br> 購物車清單列表:<%
			if (session.getAttribute("productCar") != null) {
		%>
		<%=buyProductlist%>

		<%
			}
		%>
		<div id="productcartList">
			<table id="producttable">
				<%
					int index = 0;
				%>
				<c:forEach var="buyProductlist" items="${buyProductlist}">
					<!-- EL寫法的參數傳遞給JSP -->
					<c:set var="id" value="${buyProductlist.product_id}" />




					<tr>
						<td><input
							<%if (session.getAttribute("selecttlist") != null) {
					Vector<Order_detailVO> selist = (Vector<Order_detailVO>) session.getAttribute("selecttlist");

					for (Order_detailVO an : selist) {
						if (an.getProduct_id().equals((String) pageContext.getAttribute("id"))) {%>
							<%="checked"%> <%}
					}%> <%}%>
							class="listindex" name="Checkbox[]" type="checkbox"> <input
							class="listmun" type="hidden"
							value="${buyProductlist.product_id}"></td>

						<%
							if ((String) pageContext.getAttribute("id") != null) {
									String id = (String) pageContext.getAttribute("id");

									ProductVO vo = Psvc.getOneProduct(id);
									RecipeService Rsvc = new RecipeService();
									RecipeVO Rvo;
									String recipe = "";
									if (vo.getRecipe_id() != null) {
										recipe = vo.getRecipe_id();
									}
						%>



						<%
							String src;
									String srcend = "'";
						%>
						<%=pageContext.getAttribute("id")%>
						<td><img width=170px height=150px
							<%if (vo.getRecipe_id() == null) {
						src = "src='Product_photoReader?product_id=";
						String imgsrc = src + pageContext.getAttribute("id") + srcend;%>
							<%=imgsrc%>
							<%} else {

						src = "src='";
						String imgsrc = src + Rsvc.getOneRecipe(recipe).getRecipe_photo() + srcend;%>
							<%=imgsrc%> <%}%>> <%
 	if (vo.getRecipe_id() == null) {
 %>

							<%=vo.getProduct_name()%> <%
 	} else {
 				Rvo = Rsvc.getOneRecipe(vo.getRecipe_id());
 %> <%=Rvo.getRecipe_name()%> <%
 	}
 		}
 %></td>



						<!-- 價格 -->
						<%--             <td>${buyProductlist.price}</td> --%>
						<td class="pricetd">

							<div class="oneprice">

								<font class="price">${buyProductlist.price}</font>
							</div>
						</td>
						<!-- 數量 -->
						<%--             <td class="mun">${buyProductlist.quantity}</td> --%>
						<td class="mun">
							<div class="ui input quenity">
								<input class="quenityid" type="hidden" value="${buyProductlist.product_id}"/>
								<input class="price" type="hidden" value="${buyProductlist.price}"/>

								<button style="margin: 0px" class="ui mini icon button decrease">
  <i class="minus icon"></i>
								</button>
								<input style="width: 40px;text-align: center;" type="text" value="${buyProductlist.quantity}"
									class="  inputquenity" />
								<button  class="ui mini icon button increase">
  <i class="plus icon"></i>
								</button>
							</div>
						</td>


						<!-- 小計 -->
						<td class="sum">${buyProductlist.price*buyProductlist.quantity}</td>



						<td>
							<form name="deleteForm" action="ShopCart" method="POST">
								<input class="removebtn" type="hidden" name="del" value="<%=index%>"> <input
									type="hidden" name="action" value="REMOVE">
								<div class="ui  icon input">
									<i class="icon eraser"></i> 
									
									<input class="ui active button"
										type="submit"style="color: #3a3a3a; 
										width: 100px; height: 40px" value="移除">
								</div>
							</form> <%
 	index++;
 %>

						</td>
						</td>
					</tr>
				</c:forEach>
			</table>


		</div>

		<div id="Cardetail">

			<div class="calcheck">
				<div class="calcheckmessage">
					共 <font class="qtotal"></font>
					項商品，數量共 <font class="howmany">0</font> 個，總金額NT$ <font class="tcal">0</font>
					元

				</div>


				<div class="checkbtncontext">
					<div id="backProductpage">
						<a href="ShopProductPage.jsp"><button
								style="border-radius: 0px 0px 0px 15px"
								class="ui left labeled icon button huge green">
								<i class="left arrow icon"></i> 返回繼續購物
							</button></a>
					</div>
					<div id="checkbtn">
						<!-- 轉移至SERVLT -->

						<a  href="ProductCheckoutPage.jsp">
							<button style="border-radius: 0px 0px 15px 0px"
								class="checking ui right labeled icon button huge red">
								<i class="right arrow icon"></i> 結帳
							</button>
						</a>
					</div>

				</div>


			</div>

		</div>


	</div>
	<div id="carbottom"></div>



	<!-- 預設狀態 -->
	<script type="text/javascript">
		function check() {
			var c = 0;
			$('.listindex').each(function() {
				if ($(this).prop('checked')) {
					c++;
				}
				if ($(".listindex").length == c) {
					$('#checkproductall').prop('checked', true);

				} else {
					$('#checkproductall').prop('checked', false);

				}
			})
		}
		check();
	</script>

	<script>
		//全選按鈕
		$("#checkproductall").click(function() {
			calcheckout();
			if ($("#checkproductall").is(":checked")) {
				$.ajax({
					url : 'ShopCart',
					type : "POST",
					data : {
						action : "finallcarlist"

					},
					success : function(data) {
						changecarmun(data);

					}

				});
			} else {
				$.ajax({
					url : 'ShopCart',
					type : "POST",
					data : {
						action : "clearlist"

					},
					success : function(data) {
						changecarmun(data);

					}

				});

			}

		});
		//單選按鈕
		$(".listindex").click(function() {
			check();
			checkbox();
			checkone();
			let index = $(this).siblings(".listmun").val();
			if ($(this).is(":checked") == false) {

				$.ajax({
					url : 'ShopCart',
					type : "POST",
					data : {
						remmoveid : index,
						action : "removelist",

					},
					success : function(data) {

					}

				});
			}
			//點選加入
			else {
				$.ajax({
					url : 'ShopCart',
					type : "POST",
					data : {
						addid : index,
						action : "addlist",

					},
					success : function(data) {

					}

				});

			}

		})
	</script>




	<script>
		function del() {
			var num = parseInt($('#quantity').text()) - 1;
			if (num < 1) {
				$('#quantity').text(1);
			} else {
				$('#quantity').text(num);
			}
		}
		function add() {
			var num = parseInt($('#quantity').text()) + 1;
			$('#quantity').text(num);
		}
	</script>
	<script>
		
	</script>
	<!-- 全選語法 -->
	<script>
		$(document).ready(function() {
			$("#checkproductall").click(function() {
				calcheckout();

				if ($("#checkproductall").prop("checked")) {//如果全選按鈕有被選擇的話（被選擇是true）
					$("input[name='Checkbox[]']").each(function() {
						$(this).prop("checked", true);//把所有的核取方框的property都變成勾選
						checkbox();
						calcheckout()
					})
				} else {
					$("input[name='Checkbox[]']").each(function() {
						$(this).prop("checked", false);//把所有的核方框的property都取消勾選
						checkbox();
						calcheckout()
					})
				}
			})
		});

		function checkbox() {
			if ($("input[name='Checkbox[]']:checked").length == 0) {
				$(".checking").attr('disabled', true);
		        $(".checking").parent().addClass("disa");


			}//判斷有多少個方框被勾選
			else {
				$(".checking").attr("disabled", false);
		        $(".checking").parent().removeClass("disa");



			}
		}
		checkbox();
	</script>
	<script type="text/javascript">
		$(function() {
			//按下減少
			$('.decrease').click(
					function() {
						if($(this).siblings('.inputquenity').val()>1){
						$.ajax({
							url : 'ShopCart',
							type : "POST",
							data : {
								action : "ADD",
								product_id:$(this).siblings('.quenityid').val(),
								quantity:'-1',
								product_price:$(this).siblings('.price').val()

							},
							success : function(data) {

							}

						});
						}
						
						
						var curVal = $(this).siblings('.inputquenity').val();
						if (curVal >= 2) {
							curVal -= 1;
							$(this).siblings('.inputquenity').val(curVal);
						}
						var price = $(this).parents().siblings('.pricetd')
								.find('.price').html()
						var sum = (curVal * price)
						$(this).parents().siblings('.sum').html(sum);
						checkone();						 
					
						 
						 
					})

			$('.increase').click(
					function() {
						$.ajax({
							url : 'ShopCart',
							type : "POST",
							data : {
								action : "ADD",
								product_id:$(this).siblings('.quenityid').val(),
								quantity:'1',
								product_price:$(this).siblings('.price').val()

							},
							success : function(data) {

							}

						});
						var curVal = $(this).siblings('.inputquenity').val();
						curVal = parseInt(curVal);
						curVal += 1;
						$(this).siblings('.inputquenity').val(curVal);

						var price = $(this).parents().siblings('.pricetd')
								.find('.price').html()

						var sum = (curVal * price)
						$(this).parents().siblings('.sum').html(sum);
						checkone();
						})

		});

		function calcheckout(){
			var listlength=0;
		    var caltot=0;
		    var listmun=0;
			if ($("#checkproductall").prop("checked")) {
				
			    $(".sum").each(function(){
			    	
			    	listlength++;
			caltot+=parseInt($(this).html());
			    });
  $(".inputquenity").each(function(){
			    	
	  listmun+=parseInt($(this).val());
			    });
			    
			    
			    
				
			}
			$(".qtotal").html(listlength);
			$(".tcal").html(caltot);
			$(".howmany").html(listmun);
			
			
		}
	    function checkone(){
	        var listlength=0;
	        var caltot=0;
	        var listmun=0;
	        $(".listindex").each(function(){
	            if ($(this).prop("checked")){

	                listlength++;

	                caltot+=parseInt( $(this).parent().siblings(".sum").html());
	                listmun+=parseInt(  $(this).parent().siblings('.mun').find('.inputquenity').val());
	          
	            }
	            $(".qtotal").html(listlength);
	            $(".tcal").html(caltot);
	            $(".howmany").html(listmun);
	        });
	    }

	    $(document).ready(checkone());
	</script>

</body>
</html>