<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.* ,com.order_detail.model.Order_detailVO" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>結帳畫面</title>
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





<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>



<button> <a href="ShopProductPage.jsp">回商品頁面</a></button>
 <%
 	@SuppressWarnings("unchecked")
  int alltot=0;
    Vector<Order_detailVO> buyProductlist =(Vector<Order_detailVO>)session.getAttribute("productCar");

    session.setAttribute("location", request.getRequestURI());
    
    %>
 
<%
	if (buyProductlist != null && (buyProductlist.size() > 0)) {
%>
 <font size="+3">目前購物車的內容如下：</font>

<table id="table-1" >
    <tr> 
      <th width="200">商品名</th><th width="100">單價</th><th width="100">數量</th><th>小計</th>
    </tr></table>
    
    
    <table>
    

	<%
		for (int index = 0; index < buyProductlist.size(); index++) {
			 Order_detailVO order = buyProductlist.get(index);
	%>
	
	<tr>
	<%ProductService svc=new ProductService();
	ProductVO productvo=svc.getOneProduct(order.getProduct_id());
	%>
		<td width="200"><%=productvo.getProduct_name()%>     </td>
		<td width="100"><%=order.getPrice()%>   </td>
		<td width="100"><%=order.getQuantity()%></td>
		
		
		
		
		<%
		int tot=order.getPrice()*order.getQuantity();
		alltot+=tot;
		%>
		
		
		<%
		Integer total=buyProductlist.stream()
		.mapToInt(p->p.getPrice()*p.getQuantity())
		.sum();
		
		%>
		<td width="100"><%=tot %></td>
	
	</tr>
	<%}%>
</table>
 

 <form METHOD="post" ACTION="OrderServlet">

請填寫資訊購買資訊:<div id="zipcode3">
<div class="address1" data-role="county" >
</div>
<div class="address2" data-role="district"  >
</div>
</div>
<input name="dv_address" type="text" class="f13 address form-control" value="${dv_address}" >



<br>
   選擇會員:           <select name="menber_id">
  <option value ="810001">會員一</option>
  <option value ="810002">會員二</option>
  <option value ="810003">會員三</option>
  <option value ="810004">會員四</option>
  <option value ="810005">會員五</option>
  <option value ="810006">會員六</option>
  <option value ="810007">會員七</option>
  <option value ="810008">會員八</option>
  <option value ="810009">會員九</option>
</select>

<br>
   支付方式:           <select name="pay_type">
  <option value ="0">點數</option>
  <option value ="1">信用卡</option>

</select>
<h2 style="color:red">總計:<%=alltot %></h2>
<%
		Integer total=buyProductlist.stream()
		.mapToInt(p->p.getPrice()*p.getQuantity())
		.sum();
		
		%>
<h2 style="color:red">總計:<%=total %></h2>

<br>
 <input type="submit" id="send" value="送出訂單">
 <!-- 總價計算 -->
 <input type="hidden" name="order_status" value="0">
<input type="hidden" name="address1" id="address1" value="${address1}">
<input type="hidden" name="address2" id="address2" value="${address2}">
 		 <input type="hidden" name="action" value="addorder">
 
 
<%}%>
          
          
          
          
          
           </form>    

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
var aa1;
var aa2;

if($("#address1").val()==null){
aa1="臺北市"
} else{aa1=$("#address1").val();}
if($("#address2").val()==null){
aa2="中正區"
}else{aa2=$("#address2").val();
}

$("#zipcode3").twzipcode({
	countySel: aa1 , // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
	districtSel: aa2,
"zipcodeIntoDistrict": true,
"css": ["city form-control", "town form-control"],
"countyName": "CITY", // 指定城市 select name
"districtName": "town" // 指定地區 select name
});
</script>

<!-- //////////////////////////////////////儲值//////////////////////////////////////// -->
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







</html>