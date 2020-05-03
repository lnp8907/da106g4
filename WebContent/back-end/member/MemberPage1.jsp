<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>會員搜尋</title>
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
   <tr><td><h3>會員管理頁面</h3></td></tr>
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
  <li><a href='listAllMember.jsp'>List</a> 所有的會員  <br><br></li>
  
  <!--   <li>
    <FORM METHOD="post" ACTION="ProductServlet" name="form1">
        <b>輸入商品編號 (320001起):</b>
        <input type="text" name="product_id">
        <input type="hidden" name="action" value="getOneProductDisplay">
        <input type="submit" value="送出">
    </FORM>
  </li> -->


  <jsp:useBean id="dao" scope="page" class="com.member.model.MemberService" />
   
  <li>
     <FORM METHOD="post" ACTION="member.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="member_id">
         <c:forEach var="membervo" items="${dao.all}" > 
          <option value="${membervo.member_id}">${membervo.member_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneMemberDisplay">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  
  
  
  
  <li>
     <FORM METHOD="post" ACTION="member.do" >
       <b>申請廚師:</b>
          <select size="1" name="member_id">
         <c:forEach var="membervo" items="${dao.all}" > 
          <option value="${membervo.member_id}">${membervo.member_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="memberTestToChef">
       <input type="submit" value="送出申請">
    </FORM>
  </li>
  
  
  
  
    <li>
     <FORM METHOD="post" ACTION="member.do" >
       <b>測試修改會員資料:</b>
          <select size="1" name="member_id">
         <c:forEach var="membervo" items="${dao.all}" > 
          <option value="${membervo.member_id}">${membervo.member_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="memberTestToUpdate">
       <input type="submit" value="送出申請">
    </FORM>
  </li>
  
  
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="member.do" > -->
<!--        <b>選擇商品:</b> -->
<!--        <select size="1" name="member_id"> -->
<%--          <c:forEach var="membervo" items="${dao.all}" >  --%>
<%--           <option value="${membervo.product_name}">${membervo.product_name} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOneProductDisplay"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>

<!-- <h3>會員管理</h3> -->

<!-- <ul> -->
<!-- <!-- 未完成 --> 
<!--   <li><a href='addMember.jsp'>Add</a> 新增會員</li> -->
<!-- </ul> -->


</body>
</html>