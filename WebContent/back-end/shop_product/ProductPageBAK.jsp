<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品搜尋</title>
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
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

<table id="table-1">
   <tr><td><h3>商品管理頁面</h3></td></tr>
</table>



<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
<!-- 未完成 -->
  <li><a href='listAllProduct.jsp'>List</a> 全部的商品   <br><br></li>
  
  <!--   <li>
    <FORM METHOD="post" ACTION="ProductServlet" name="form1">
        <b>輸入商品編號 (320001起):</b>
        <input type="text" name="product_id">
        <input type="hidden" name="action" value="getOneProductDisplay">
        <input type="submit" value="送出">
    </FORM>
  </li> -->


  <jsp:useBean id="dao" scope="page" class="com.product.model.ProductService" />
   
  <li>
     <FORM METHOD="post" ACTION="product.do" >
       <b>選擇商品類型:</b>
       <select size="1" name="product_id">
         <c:forEach var="productvo" items="${dao.all}" > 
          <option value="${productvo.product_id}">${productvo.product_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneProductDisplay">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="product.do" >
       <b>選擇商品:</b>
       <select size="1" name="product_id">
         <c:forEach var="productvo" items="${dao.all}" > 
          <option value="${productvo.product_id}">${productvo.product_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneProductDisplay">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
<h3>商品管理</h3>

<ul>
<!-- 未完成 -->
  <li><a href='addProduct.jsp'>Add</a> 新增商品</li>
</ul>


</body>
</html>