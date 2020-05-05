<%@ page language="java" contentType="text/html; charset=UTF-8 "
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="com.shop_order.model.*" %>
    
    
   
    
    <%Shop_orderVO ordvo=null;
    if(request.getAttribute("dialogordvo")!=null){
          ordvo =(Shop_orderVO) request.getAttribute("dialogordvo");
    Map<Integer, String> statusMap = new HashMap<>();
    }
    %>
    <c:set var="ordvo" value="<%=ordvo %>" scope="request"/>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>

 <script src="<%=request.getContextPath() %>/plugin/jquery.twzipcode.min.js"></script>




<style>
input{
    border-radius: 50px;
}

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

<meta charset="UTF-8">
<title>更新地址</title>
</head>
<body>





		

 <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="OrderServlet.do" name="upateform" id="upateform">
<table>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		
		<td>${ordvo.order_no}</td>
		
	</tr>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td>${ordvo.member_id}</td>

	</tr>
	<tr>
		<%
    Map<Integer, String> pay_typemap = new HashMap<>();
						pay_typemap.put(0, "點數");
						pay_typemap.put(1, "信用卡");
%>
<c:set var="pay_typemap" value="<%=pay_typemap %>"/>
		<td>訂單狀態:</td>
	<td>	${pay_typemap[ordvo.pay_type]} 
	
	</td>
	</tr>
		<tr>
		<td>訂單生成日期:<font color=red><b>*</b></font></td>
		<td><%=ordvo.getOrder_time()%></td>
	</tr>
			<tr>
					<%
    Map<Integer, String> Order_statusmap = new HashMap<>();
		Order_statusmap.put(0, "已成立");
		Order_statusmap.put(1, "運送中");
		Order_statusmap.put(0, "已完成");
		Order_statusmap.put(1, "取消訂單");
%>

<c:set var="Order_statusmap" value="<%=Order_statusmap %>"/>

		<td>付款方式：	</td>
				<td>${Order_statusmap[ordvo.order_status]}</td>
		
		
	
	
	
	
	</tr>
	
	
<tr>
		<td>訂單地址:</td>
		
		<td> <div id="zipcode3">
<div class="address1" data-role="county">
</div>
<div class="address2" data-role="district">
</div>
<%
String city="";
String town="";
String address3="";
int addressstr=ordvo.getDv_address().indexOf("/");
if(addressstr == -1){	
}
else{
	String address[]=ordvo.getDv_address().split("/");
	 city=address[0];
	 town=address[1];
	 address3=address[2];	
	
}
%>

</div>
<input class="address3" name="dv_address" type="text" class="f13 address form-control" value="<%=(address3=="")?ordvo.getDv_address():address3%>">
		



		
		<input type="hidden" name="action" value="OrderUpdate">
<input type="hidden" name="order_no" value="<%=ordvo.getOrder_no()%>">
<input type="hidden" name="member_id" value="<%=ordvo.getMember_id() %>">
<input type="hidden" name="total" value="<%=ordvo.getTotal()%>">
<input type="hidden" name="pay_type" value="<%=ordvo.getPay_type()%>">
<input type="hidden" name="order_status" value="${ ordvo.order_status}">
<input type="hidden" name="address1" id="address1">
<input type="hidden" name="address2" id="address2" >
<input  type="submit" value="送出修改" id="send" name="send">
		
		</td>
	</tr>	


</table>

</FORM>



  <FORM METHOD="post" ACTION="OrderServlet.do" >
   <input type="hidden" name="action" class="btnstyle" value="OrderByMmber">
   <input type="hidden" name="member_id"  value="<%=ordvo.getMember_id()%>">
  <input type="submit" value="返回" >
</FORM>
</body>
<script>
var a1;
var a2;
$('#send').click(function () {
	var a1=$(".city").val()
	var a2=$(".town").val()
	$("#address1").val(a1);
	$("#address2").val($(".town").val());
	
});


</script>

<script>

$("#zipcode3").twzipcode({
zipcodeIntoDistrict: true,
css: ["city form-control", "town form-control"],
countySel: "<%=(city==null)?"":city%>", // 指定城市 select name
districtSel: "<%=(town==null)?"":town%>" // 指定地區 select name

});

	

</script>

</body>
</html>