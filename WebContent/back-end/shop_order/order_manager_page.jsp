<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath()%>/css/DEMOJSPDEMO.css"></script>

<meta charset="BIG5">
<title>訂單管理頁面</title>
</head>
<body>
<style>
  table#title {
    
	width: 450px;
	background-color: #ffa0a5;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="title">
   <tr><td><h3>訂單管理</h3><h4></h4></td></tr>
</table>


<h3>訂單查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if><%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<!-------------->





<ul>
 <jsp:useBean id="ordSvc" scope="page" class="com.ordermanager.shop.OrderService" />
  <li><a href='listAllShopOrder.jsp'>所有訂單</a><br><br></li>
 
    <li>
     <FORM METHOD="post" ACTION="OrderServlet.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="member_id">
         <c:forEach var="ordervom" items="${ordSvc.all}" > 
          <option value="${ordervom.member_id}">${ordervom.member_id}
         </c:forEach>   
       </select>
	  <input type="hidden" name="action" class="btnstyle" value="OrderByMmber">
	         <input type="submit" value="送出">
     </FORM>
  </li>
  
  
  
  
   
   <li>
     <FORM METHOD="post" ACTION="OrderServlet.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="order_no">
         <c:forEach var="ordervo" items="${ordSvc.all}" > 
          <option value="${ordervo.order_no}">${ordervo.order_no}
         </c:forEach>   
       </select>
	  <input type="hidden" name="action" class="btnstyle" value="getorderdetail">
	         <input type="submit" value="送出">
     </FORM>
  </li>












</ul>


<script>



</script>


</body>
</html>