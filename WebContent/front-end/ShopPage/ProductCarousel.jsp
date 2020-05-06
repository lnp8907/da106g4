<%@page import="com.product_browsing_history.model.*"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="java.util.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%
Set<Product_browing_historyVO> list=new LinkedHashSet<Product_browing_historyVO>();
if(session.getAttribute("member_id")!=null){
	String member_id=(String)session.getAttribute("member_id");
	Product_browing_historyService PbhSvc=new Product_browing_historyService();
	list=PbhSvc.getAll(member_id);
}



%>
<c:set var="PClist" value="<%=list%>"/>

<html>
<head>
<!--         <script src="../../plugin/jquery-3.4.1.min.js"></script> -->
<!--     <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script> -->

    <script src="../../plugin/slick-1.8.1/slick/slick.min.js"></script>
    <link rel="stylesheet" href="../../plugin/slick-1.8.1/slick/slick.css">
    <link rel="stylesheet" href="../../plugin/slick-1.8.1/slick/slick-theme.css">
        <link rel="stylesheet" href="css/ProductCarouseCss.css">
    
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="productCarouse">
<div class="multiple-items">
<c:forEach var="PClist" items="${PClist}">
  <div>   <a href="ProductPage?product_id=${PClist.product_id}&action=goDetailPage"> <img src="<%=request.getContextPath() %>/back-end/shop_product/Product_photoReader?product_id=${PClist.product_id}" height="250" width="250"/></a>
  </div>
  			</c:forEach>
  
</div></div>



</body>
<script>
$('.multiple-items').slick({
	  infinite: true,
	  slidesToShow: 4,
	  slidesToScroll: 4
	});


</script>
</html>