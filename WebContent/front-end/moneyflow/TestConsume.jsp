<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*"%>
<%@page import="com.moneyflow.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
MemberVO membervo =(MemberVO) request.getAttribute("memberVO");
MoneyflowVO moneyflowvo =(MoneyflowVO) request.getAttribute("moneyflowVO");


session.setAttribute("member_id", "810001");
%>
<%
				Map<String, Integer> storedValue1 = new HashMap<>();
                storedValue1.put("0", 2000);
//  				storedValue1.put("1", 520);
//  				storedValue1.put("2", 1140);
//  				storedValue1.put("3", 2065);
//  				storedValue1.put("4", 4460);
			
				application.setAttribute("storedValue", storedValue1);

			%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>購物扣款</title>






<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>











<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>






<style>
input {
	border-radius: 50px;
}

``````````````
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
	width: 450px;
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
<body>
<!-- 	<SCRIPT SRC="HTTPS://CODE.JQUERY.COM/JQUERY-3.4.1.JS" -->
<!-- 		INTEGRITY="SHA256-WPOOHJOQMQQYKL9FCCASB9O0KWACQJPFTUBLTYOVVVU=" -->
<!-- 		CROSSORIGIN="ANONYMOUS"></SCRIPT> -->
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="MoneyflowServlet.do" name="upateform" id="changeStatus"
		enctype="multipart/form-data">



		<table>
			<tr>
				
				<!-- 按鈕 -->
				<td>(模擬購物車金額)消費2000點:<input type="submit" class="btn-primary" ></td>
		</table>
		

		
		<br> <input type="hidden" name="action" value="consume3"> 
			
		
			
               <input type="hidden" name="money" value="<%=storedValue1.get("0") %>">

	
	
	
	</FORM>
	
	
	
	
	
	
	
	
	<c:if test="${openModal!=null}">

    <div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
     aria-labelledby="basicModal" aria-hidden="true">
     <div class="modal-dialog modal-lg">
      <div class="modal-content">

       <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
         aria-hidden="true">&times;</button>
        <h2 class="modal-title" id="myModalLabel">儲值</h2>
       </div>

       <div class="modal-body">
        <!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
        <jsp:include page="/front-end/moneyflow/storedValue.jsp" />
        <!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
       </div>

       <div class="modal-footer">
        <button type="button" class="btn btn-default"
         data-dismiss="modal">Close</button>
<!--         <button type="button" class="btn btn-primary">Save -->
<!--          changes</button> -->
       </div>

      </div>
     </div>
    </div>
    
            <script>
    		 $("#basicModal").modal({show: true});
        </script>
    
   </c:if>
	
	    <script>
     $(document).ready(function() {
      $('.btn-primary').on('click', function() {
       $('#changeStatus').submit();
      });
     });
    </script>	

	
	




</body>



</html>















