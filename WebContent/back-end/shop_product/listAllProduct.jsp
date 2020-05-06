<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%@ page import="java.util.stream.Collectors"%>


<%        	Map<String ,Integer> typemap=new HashMap<>();
        	typemap.put("all",0);
        typemap.put("水果類",1);
        typemap.put("肉類",2);
        typemap.put("蔬菜類",3);
        typemap.put("乳品類",4);
        typemap.put("魚貝類",5);
        typemap.put("菇類",6);
        typemap.put("穀物類",7);
        typemap.put("澱粉類",8);
        typemap.put("酒類",9);
        typemap.put("油脂類",8);
        typemap.put("調味及香辛料類",9); 
       	Map<Integer,String > typemapC=new HashMap<>();
    	typemapC.put(0,"all");
    typemapC.put(1,"水果類");
    typemapC.put(2,"肉類");
    typemapC.put(3,"蔬菜類");
    typemapC.put(4,"乳品類");
    typemapC.put(5,"魚貝類");
    typemapC.put(6,"菇類");
    typemapC.put(7,"穀物類");
    typemapC.put(8,"澱粉類");
    typemapC.put(9,"酒類");
    typemapC.put(10,"油脂類");
    typemapC.put(11,"調味及香辛料類");  
        
        
        
        
        %>
<%
	ProductService Psvc = new ProductService();
List<ProductVO> list = Psvc.getAllProduct();
String product_type ="0";
if(request.getAttribute("product_type")!=null){
	if(((String)request.getAttribute("product_type")).equals("all")){	
		list = Psvc.getAllProduct();
		product_type="0";

	}
	else{
	 final String product_type2=(String)request.getAttribute("product_type");
	list=list.stream()
			.filter(p->p.getProduct_type().equals(product_type2))
			.collect(Collectors.toList());
			product_type=typemap.get((String)request.getAttribute("product_type"))+"";


	}
}

	
	
	if(request.getParameter("product_typeA")!=null){
		Integer key=Integer.valueOf(request.getParameter("product_typeA"));
		
		if(key!=0){
			product_type=key+"";
			 final String product_type3=typemapC.get(key);
				list=list.stream()
						.filter(p->p.getProduct_type().equals(product_type3))
						.collect(Collectors.toList());
			
		}
		else{
			product_type="0";

			list = Psvc.getAllProduct();

			
			
		}
		
	}
	pageContext.setAttribute("Bprodcutlist", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="typemap" value="<%=typemap %>" scope="session" />
<c:set var="typemapC" value="<%=typemapC %>" scope="session" />
<c:set var="product_type" value="<%=product_type %>" scope="request" />

<title>所有商品處理頁面</title>
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
	
類型....${product_type}

	<select class="product_type">
    <option value ="all"${product_type eq '0'?'selected':''} >所有商品</option>
    <option value ="水果類" ${product_type eq'1'?'selected':''} >水果類</option>
    <option value="肉類" ${product_type eq'2'?'selected':''}>肉類</option>
    <option value="蔬菜類" ${product_type eq'3'?'selected':''}>蔬菜類</option>
    <option value ="乳品類" ${product_type eq'4'?'selected':''}>乳品類</option>
    <option value="魚貝類" ${product_type eq'5'?'selected':''}>魚貝類</option>
    <option value="菇類" ${product_type eq'6'?'selected':''}>菇類</option>
    <option value ="穀物類"${product_type eq'7'?'selected':''}>穀物類</option>
    <option value="澱粉類"${product_type eq'8'?'selected':''}>澱粉類</option>
    <option value="酒類" ${product_type eq'9'?'selected':''}>酒類</option>
    <option value ="油脂類"${product_type eq'10'?'selected':''}>油脂類</option>
    <option value="調味及香辛料類" ${product_type eq'11'?'selected':''}>調味及香辛料類</option>
</select>
<form action="ShopPageServlet" method="post">
    <input type="hidden" name="product_type" class="producttype">
<input type="hidden" name="action" value="typeselect">
    <input type="submit" style="display: none" class="producttypebtn">

</form>
	<script>

    $(".product_type").change(function(){
        var opt=$(".product_type").val();
        $(".producttype").val(opt);
        $(".producttypebtn").click();
    });




</script>
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

				</tr>
				<%@ include file="../file/page1.file"%>
						<c:set var="whichPage" value="<%=whichPage %>" scope="request"/>
				
			</thead>
			<tbody id="ProductContext">
				<c:forEach var="productvo" items="${Bprodcutlist}" begin="<%=pageIndex%>"
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



						<td><input class="isrevise" type="hidden"
							value="${productvo.product_status}"> <input type="hidden"
							class="product_id" value="${productvo.product_id}">

							<button class="ui left attached button onproduct"
								${ productstatus [status]=='已上架'? " style='background-color: green;color: white'":""}>上架</button>


							<button class="right attached ui button offproduct"
								${ productstatus [status]=='未上架'? " 
                     
                     
                     style='background-color: blue;color: white'":""}>下架</button>

						</td>




						<!-- 按鈕 -->

						<td>
							<!-- 詳細頁面 -->
							<FORM METHOD="post" ACTION="ProductServlet"
								style="margin-bottom: 0px;">
								<input type="submit" value="查看更多"> <input type="hidden"
									name="product_id" value="${productvo.product_id}"> <input
									type="hidden" name="action" value="detailopen"> <input
									type="hidden" name="whichPage" value="<%=whichPage%>">
<input type="hidden"
									name="product_type" value="${productvo.product_type}">
<input type="hidden"
									name="fproduct_type" value="${product_type}">
							</FORM>
						</td>

						<td class="upate">
							<!-- 修改 -->
							<FORM METHOD="post" ACTION="ProductServlet"
								style="margin-bottom: 0px;">
								<input class="updateproduct" type="submit" value="修改"> <input
									type="hidden" class="inputid" name="product_id"
									value="${productvo.product_id}"> <input type="hidden"
									name="action" value="upateopen">
									<input type="hidden" name="whichPage" value="${whichPage}">
									
							</FORM>
						</td>
						<c:set var="id" value="${productvo.product_id}" />
						<%
							String id = (String) pageContext.getAttribute("id");
						%>


					</tr>
				</c:forEach>

			</tbody>
		</table>
		<%@ include file="file/productpage.file"%>
	</div>
	當前頁面${whichPage}
	<!-- end of grid -->




	<script>
		var r = $('.recipe_td').html()
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