<%@page import="com.product.model.*"%>

<%@page import="com.product_browsing_history.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
 		<link rel="stylesheet" href="css/PBHlist.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%Product_browing_historyService PBHSvc=new Product_browing_historyService();
List <Product_browing_historyVO>PBHlist=new LinkedList();
ProductService Psvc=new ProductService();
List <ProductVO> PBHProductlist =new LinkedList();

if(session.getAttribute("member_id")!=null){

	PBHlist=PBHSvc.getAll((String)session.getAttribute("member_id"));

}

%>
<c:set var="PBHlist" value='<%=PBHlist %>'/>
<c:forEach var="PBHlist" items="${PBHlist}">
<c:set var="product_id" value='${PBHlist.product_id}' scope="request"/>
<%
ProductVO PVO=Psvc.getOneProduct((String)request.getAttribute("product_id"));
PBHProductlist.add(PVO);
%>
 		</c:forEach>
 		
<c:set var="PBHProductlist" value='<%=PBHProductlist %>' scope="request"/>

 	
 	

<div id="mainproduct">
 	<%@ include file="file/page1.file" %> 

 <font>共<%=rowNumber%>筆</font>
 
                <ul>
<c:forEach var="PBHProductlist" items="${PBHProductlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<li ><a
				href="ProductPage?product_id=${PBHProductlist.product_id}&action=goDetailPage">
				<div class="PBHProductlist">
				
				
				<img class="ui medium circular image" src="../../back-end/shop_product/Product_photoReader?product_id=${PBHProductlist.product_id}">
					</div>
					<div  >
					${PBHProductlist.product_name}
					</div>
					              
			</a></li>
	
		</c:forEach>
                </ul>
                

            </div>


                 	<%@ include file="file/page2.file" %> 





</body>
</html>