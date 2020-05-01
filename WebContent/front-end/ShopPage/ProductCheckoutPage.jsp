<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.* ,com.order_detail.model.Order_detailVO" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       <script src="../../plugin/jquery-3.4.1.min.js"></script>
    <script src="../../js/jquery-migrate-1.4.1.min.js"> </script>

    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick-theme.css"/>
    <script type="text/javascript" src="../../css/slick-1.8.1/slick/slick.js"></script>
    <link rel="stylesheet" type="text/css" href="../../css/semantic.min.css">
    <script src="../../js/semantic.min.js"></script>
    <meta charset="UTF-8">
    <title>FoodPornChec</title>
    <link rel="stylesheet" href="css/ShopCartPage.css">
    
    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>結帳畫面</title>
</head>
<body>

<div id="shopTitle">
    <div class="foodpronpicture"><a href="ShopProductPage.jsp"><img src="../../image/FoodPron_Logo.png" alt=""></a></div>
</div>
<hr>
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



 <%
 	@SuppressWarnings("unchecked")
 
 Vector<Order_detailVO> buyProductlist=null;
 if((Vector<Order_detailVO>)session.getAttribute("selecttlist")!=null){
	 buyProductlist=(Vector<Order_detailVO>)session.getAttribute("selecttlist");
	 
	 
	 
 }
if(((Vector<Order_detailVO>)session.getAttribute("productCar")).size()==buyProductlist.size()){
     buyProductlist =(Vector<Order_detailVO>)session.getAttribute("productCar");
 }
session.setAttribute("checkoutlist", buyProductlist);
    session.setAttribute("location", request.getRequestURI());
    
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
 <div id="progress-rate">
    <div class="progesssize ui steps">
        <a class=" step" href="<%=request.getContextPath() %>/front-end/ShopPage/ProductPage?action=checktpage1">
            <i class="shopping cart icon" style="color: #e4002b"></i>
            <div class="content">
                <div class="title" style=" color: #e4002b;" >請確認購物車
                </div>
                <div class="description">選擇您的購買清單</div>
            </div>
        </a>
        <a class="active step">
            <div class="content">
                <div class="title">結帳</div>
                <div class="description">輸入付款資訊</div>
            </div>
        </a>
        <a class="step">
            <div class="content">
                <div class="title">完成</div>
                <div class="description">恭喜完成訂單</div>
            </div>
        </a>
    </div>


</div>
<div id="showmember">
    敬愛的會員<font class="membername">宏哥</font>你好:</div>
 
 
<div id="orderform">
<form METHOD="post" ACTION="OrderServlet" class="ui form">
    <h4 class="ui dividing header">請填入您的購物資訊</h4>
    <div class="field">
        <label>Name</label>
        <div class=" field">
            <div class="field">
            
       
                <input type="text" disabled="disabled" value="宏哥">
          

            </div>

        </div>
    </div>
    
               <label>地址:</label>  
    
      <div class="fields "id="zipcode3">
     
  <div class="three wide field">
           <div class="address1 field" data-role="county" >
  </div>
   </div>
  <div class="three wide field">
  <div class="address2 field" data-role="district" >
  
  </div></div>
  
  <div class="seven wide field">
  <input name="dv_address" type="text" class="f13 address form-control" value="${dv_address}" >
  </div> 
    </div>

 <select id="pay-type" name="pay_type" class="ui dropdown">
        <option value="">請選擇付款方式</option>
        <option value="1">信用卡</option>
                <option value="0">點數</option>
        
    </select> 

<div  id="paymeth"  style="display:none " class="fields">

            <div  class="seven wide field" >
                <label>信用卡卡號</label>

                <input type="text" name="card[number]" maxlength="16" placeholder="Card #">
            </div>
            <div class="three wide field">
                <label>CVC</label>
                <input type="text" name="card[cvc]" maxlength="3" placeholder="CVC">
            </div>
            <div class="six wide field">
                <label>到期</label>
                <div class="two fields">
                    <div class="field">
                        <select class="ui fluid search dropdown" name="card[expire-month]">
                            <option value="">月份</option>
                            <option value="1">一月</option>
                            <option value="2">二月</option>
                            <option value="3">三月</option>
                            <option value="4">四月</option>
                            <option value="5">五月</option>
                            <option value="6">六月</option>
                            <option value="7">七月</option>
                            <option value="8">八月</option>
                            <option value="9">九月</option>
                            <option value="10">十月</option>
                            <option value="11">十一月</option>
                            <option value="12">十二月</option>
                        </select>
                    </div>
                    <div class="field">
                        <input type="text" name="card[expire-year]" maxlength="4" placeholder="Year">
                    </div>
                </div>
            </div>

        </div>
         <div  id="sendOrder"> 
 <input type="submit" id="send" id="sendOrder"class="ui button " tabindex="0" value="送出訂單">
</div>
<%
		Integer total=buyProductlist.stream()
		.mapToInt(p->p.getPrice()*p.getQuantity())
		.sum();
		
		%>
<h2 style="color:red">總計:<%=total %></h2>

<br>
 <!-- 總價計算 -->
 <input type="hidden" name="order_status" value="0">
<input type="hidden" name="address1" id="address1" value="${address1}">
<input type="hidden" name="address2" id="address2" value="${address2}">
 		 <input type="hidden" name="action" value="addorder">
 		  <input type="hidden" name="member_id" value='<%=session.getAttribute("member_id") %>'>
<%=session.getAttribute("member_id") %> 		  		 
 		 
 

           </form>    

   </div>
 <script>

    $("#pay-type").change(function(){
        if($(this).val()==1){
            $("#paymeth").show();

        }
        else if($(this).val()==0){

            $("#paymeth").hide();

        }

    });



</script>
 






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