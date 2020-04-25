<%@page import="javax.websocket.Extension.Parameter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
				Map<Integer, String> producttype = new HashMap<>();
                producttype.put(0, "所有商品");
				producttype.put(1, "水果類");
				producttype.put(2, "肉類");
				producttype.put(3, "蔬菜類");
				producttype.put(4, "乳品類");
				producttype.put(5, "魚貝類");
				producttype.put(6, "菇類");
				producttype.put(7, "穀物類");
				producttype.put(8, "澱粉類");
				producttype.put(9, "酒類");
				producttype.put(10, "油脂類");
				producttype.put(11, "調味及香辛料類");

				application.setAttribute("producttype2", producttype);

				application.setAttribute("producttype", producttype);

			%>
<%



Collection<ProductVO> productlist=null;
	ProductService Psvc = new ProductService();
	productlist=(Collection<ProductVO>)session.getAttribute("productlist");
	
	String product_type=null;
	
	if((List<ProductVO>) request.getAttribute("Query")!=null){	
		productlist=(List<ProductVO>) request.getAttribute("Query");
		 product_type = (String) request.getAttribute("product_type");

		request.setAttribute("productlist", productlist);
	}
	
	else{
	if((String) session.getAttribute("product_type")!=null){
		 product_type = (String) session.getAttribute("product_type");
	}
	else{
		product_type=(String) request.getAttribute("product_type");
	}
	if(productlist==null){
		if (product_type == null || product_type.equals("all")) {
			productlist = Psvc.getAllProduct(0);
			session.setAttribute("product_type", "所有商品");

			

		} else {
			
			productlist = Psvc.gettypelist(product_type);
			session.setAttribute("product_type", product_type);


		}	
		
	}else{
		if(productlist.size()==Psvc.getsize(Psvc.getAllProduct())){
			session.setAttribute("product_type", "所有商品");

		}else{
			session.setAttribute("product_type", product_type);

		}
		
	}
	session.setAttribute("productlist", productlist);	
	product_type=(String)session.getAttribute("product_type");

	}
	int t=0,t2=0;
%>
<html>
<head>



    <script src="../../js/jquery-3.4.1.min.js"></script>
<!-- UI套件 -->
  <link rel="stylesheet" type="text/css" href="../../css/semantic.min.css">
    <script src="../../js/semantic.min.js"></script>
    
	<link rel="stylesheet" href="css/productPage.css">


<meta charset="UTF-8">
<title>商城頁面</title>
</head>

<body>
 	<%@ include file="Includepage/page.file" %> 

<!-- 位置 -->
		<div id="ShopPathLocation">

<div class="ui breadcrumb">
  <a class="section">Foodporn</a>
  <i class="right angle icon divider"></i>
  <a class="section" href="ShopHomePage.jsp">商城首頁</a>
  <i class="right angle icon divider"></i>
  <div class="active section"><font ><%=(product_type!=null)?product_type:"所有商品"%><%if (pageNumber>0){%>
  <b>第<font color=red><%=whichPage%></font>頁</b>
<%}%> </font>
  
  
  </div>
</div>
</div>
<br>	
	<!--搜尋-->
			<div id="ShopLsearch">
			
			<form METHOD="post" ACTION="ProductPage">
						<div class=" ui icon input">

   
 
			<select class="ui compact selection dropdown" name="product_type">
							<c:forEach var="producttype2" items="${producttype2}">
		<option value="${producttype2.key==0? null:producttype2.value}">${producttype2.value}</option>
			</c:forEach>
			</select>
 <input type="text"  placeholder="你想找甚麼..." name='product_name'>
 		  <i id='searchproduct' class=" inverted search link icon"></i>
	
 
  <input id='sendsearch' type="submit" style='display:none' >
  <input type="hidden" value="searchproduct" name="action">
  <script>
  $('#searchproduct').click(function(){
	  $('#sendsearch').click();	  
  });
  
  
  
  </script>
  </div>
</form>
</div>



        <div id="productPagecontext">
        
        
<div id="leftside">
    <div id="typetitle" class="ui medium header">商品分類</div>
    <ul id="typelist">
      <c:forEach var="producttype" items="${producttype}">
	
			<li>
			<a	href="ProductPage?product_type=<%=producttype.get(t)%>&action=goProductPage">
					<div><img  src="../image/TYPE ICON/<%=t+".png" %>" alt=""></div>
					
					
					<div class="typename"><%=producttype.get(t)%> </div>
					  <i class="clone setting large red outline icon"></i>
					
			</a>
						        <hr>
			
			</li>
			
			<%
				t++;
			%>
		</c:forEach>
	</ul>
</div>







 <div id="mainproduct">
 <font>共<%=rowNumber%>筆</font>
 
                <ul>
<c:forEach var="productlist" items="${productlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<li ><a
				href="ProductPage?product_id=${productlist.product_id}&action=goDetailPage">
				<div class="productlist">
				
				
				<img class="ui medium circular image" src="../../back-end/shop_product/Product_photoReader?product_id=${productlist.product_id}">
					</div>
					<div  >
					${productlist.product_name}
					</div>
					              
			</a></li>
		<%
				t2++;
			%>
		</c:forEach>
                </ul>

            </div>
        </div>
        
        <%@ include file="Includepage/page2.file" %>
        
	        </div>
	

</body>
</html>