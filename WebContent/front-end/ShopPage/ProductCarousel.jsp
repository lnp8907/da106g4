<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="com.product_browsing_history.model.*"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="java.util.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
    
<!DOCTYPE html>
<%
List<Product_browing_historyVO> list=new LinkedList<Product_browing_historyVO>();
if(session.getAttribute("member_id")!=null){
	String member_id=(String)session.getAttribute("member_id");
	Product_browing_historyService PbhSvc=new Product_browing_historyService();
	list=PbhSvc.getAll(member_id);
 	Collections.shuffle(list);
}



%>
<c:set var="PClist" value="<%=list%>"/>

<html>
<head>


<!--         <script src="../../plugin/jquery-3.4.1.min.js"></script> -->
<!--     <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script> -->


    
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div style="margin: 0px auto 50px" class="productCarouse">
<div class="Carouse" style="width: 1100px;margin: 20px auto;"> <h2  style="color:red ;font-family:Microsoft JhengHei;">瀏覽紀錄</h2></div>

<div class="multiple-items">

 <c:if test="${fn:length(PClist)>0}">

 <c:forEach var="PClist" items="${PClist}">
 <c:set var="hlistpid" value="${PClist.product_id}" scope="request"/>

 <%ProductService psv=new ProductService();
 ProductVO Pvo=psv.getOneProduct((String)request.getAttribute("hlistpid"));
 
 %>
  
 <a href="ProductPage?product_id=${PClist.product_id}&action=goDetailPage">
   <div style="text-align: center; width: 250px;"> <%=Pvo.getProduct_name() %> </div>
  <img style="border-radius: 70px" src="<%=request.getContextPath() %>/back-end/shop_product/Product_photoReader?product_id=${PClist.product_id}" height="250" width="250"/>
  </a>
 
  			</c:forEach>
  </c:if>
  
</div></div>



</body>
<script>
$('.multiple-items').slick({
	  infinite: true,
	  slidesToShow: 5,
	  slidesToScroll: 5
	});


</script>
<style>
.Carouse{

text-align: center;

}

</style>
</html>