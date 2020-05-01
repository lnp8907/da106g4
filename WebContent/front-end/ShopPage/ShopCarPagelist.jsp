
<!DOCTYPE html>
<html>
<head>
    <%@ page import="java.util.* ,com.order_detail.model.Order_detailVO,com.recipe.model.RecipeVO" %>

<%@page import="com.recipe.model.RecipeService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<title>購物車畫面</title>
</head>

<body>
 <%
 Vector<Order_detailVO> buyProductlist=null;
 	@SuppressWarnings("unchecked")
  int alltot=0;
 if((Vector<Order_detailVO>)session.getAttribute("productCar")!=null){
	  buyProductlist =(Vector<Order_detailVO>)session.getAttribute("productCar");
	 session.setAttribute("buyProductlist", buyProductlist);	 
	 
 }
 
ProductService Psvc=new ProductService();
 %>
<div id="progress-rate">
    <div class="progesssize ui steps">
        <a class="active step">
            <i class="shopping cart icon" style="color: #e4002b"></i>
            <div class="content">
                <div class="title" style=" color: #e4002b;" >請確認購物車
                </div>
                <div class="description">選擇您的購買清單</div>
            </div>
        </a>
        <a class="step">
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

<div id="tablecontext">
    <div id="checkboxwithpeoduct">
        <table  >
            <tr><td ><input  id="checkproductall" type="checkbox"></td><td >商品名</td><td>價格</td> <td >數量</td><td>小計</td><td>移除</td></tr>
        </table>
    </div>
待結帳列表:<%if(session.getAttribute("selecttlist")!=null){
	Vector<Order_detailVO> selectlist=(Vector<Order_detailVO>)session.getAttribute("selecttlist");
	%>
	<%=selectlist %>
	
<% } %>
<br>
購物車清單列表:<%if(session.getAttribute("productCar")!=null){
	%>
	<%=buyProductlist %>
	
<% } %>
    <div id="productcartList">
        <table id="producttable" >
        <%int index=0; %>
            <c:forEach var="buyProductlist" items="${buyProductlist}">
           <!-- EL寫法的參數傳遞給JSP -->
            				<c:set var="id" value="${buyProductlist.product_id}"/>
            				

           
            
            <tr><td ><input 
            
            <%if(session.getAttribute("selecttlist")!=null){ 
            	Vector<Order_detailVO> selist=(Vector<Order_detailVO>)session.getAttribute("selecttlist");
            	
            	for(Order_detailVO an:selist){
            	if(an.getProduct_id().equals((String)pageContext.getAttribute("id"))){%>
            		<%="checked" %>
            	<% }
            }
      
            %>
  
            <%}%>
            
            
            
            class="listindex" name="Checkbox[]" type="checkbox" >
            
            
            
            
            
            
            <input  class="listmun" type="hidden" value="${buyProductlist.product_id}">
            
            
            </td>
                
                 <%
			            if((String)pageContext.getAttribute("id")!=null){
			            String id =(String)pageContext.getAttribute("id");
			            
			            ProductVO vo=Psvc.getOneProduct(id); 
			            RecipeService Rsvc=new RecipeService();
		            	RecipeVO Rvo;
		            	String recipe="";
		            	if(vo.getRecipe_id()!=null){
		            		recipe=vo.getRecipe_id();
		            	}
			            %>
			
          
            
			 <%String src;
            String srcend="'";
            %>
			<%=pageContext.getAttribute("id") %>
            <td><img width=170px height=150px
            <%
            
            if(vo.getRecipe_id()==null){
            	src="src='Product_photoReader?product_id=";
            	String imgsrc=src+pageContext.getAttribute("id")+srcend;
            %>
            <%=imgsrc %>
            <%}else{
            	
                src="src='";
            	String imgsrc=src+Rsvc.getOneRecipe(recipe).getRecipe_photo()+srcend;
            %>
            	<%=imgsrc
            	%>
            	
            	
            <%} %>
          >
  
           
           
           
   
			<% if(vo.getRecipe_id()==null){%>       
            	
            	   <%=vo.getProduct_name() %>   	
            <% }
            else{Rvo=Rsvc.getOneRecipe(vo.getRecipe_id());
            %>

            	<%=Rvo.getRecipe_name()%>
            	
            	
            <% }}%>
            
            
            
            </td> 
            
            
            
            
            <td>${buyProductlist.price}</td><td class="mun">${buyProductlist.quantity}</td><td class="pcal">${buyProductlist.price*buyProductlist.quantity}</td>
            
            
            
            <td>  <form name="deleteForm" action="ShopCart" method="POST">
            <input type="hidden" name="del" value="<%=index%>">

              <input type="hidden" name="action"  value="REMOVE">
              <div class="ui  icon input"><i class="icon eraser"></i>
              <input class="ui active button" type="submit" style="color:#3a3a3a; width: 60px;height: 40px" value="移除" >  
              </div>
          </form>
          
         
        <% index++;%>
          
          </td></td></tr>
            </c:forEach>
        </table>


    </div>

    <div id="Cardetail">
    
        <div class="calcheck">
       <div class="calcheckmessage"> 共 <font><%=(buyProductlist==null)?"":buyProductlist.size() %></font> 項商品，數量共 <font class="howmany"></font> 個，總金額NT$ <font class="tcal">XXX</font> 元

       </div>


<div class="checkbtncontext">
 <div id="backProductpage">
                <a href="ShopProductPage.jsp"><button  style="border-radius: 0px 0px 0px 15px" class="ui left labeled icon button huge green">
                <i class="left arrow icon"></i> 返回繼續購物 </button></a>
            </div>
            <div id="checkbtn">
<!-- 轉移至SERVLT -->

                <a href="ProductCheckoutPage.jsp"><button   style="border-radius: 0px 0px 15px 0px" class="checking ui right labeled icon button huge red">
                <i class="right arrow icon"></i> 結帳 </button></a>
            </div>

</div>


        </div>

    </div>


</div>
<div id="carbottom"></div>


<script>

var munTotal = 0;
$('.mun').each(function() {
	  $(this).each(function()
		{
			munTotal += parseInt($(this).html());
		});
	  $(".howmany").html(munTotal);
  });
var calTotal = 0;
$('.pcal').each(function() {
	  $(this).each(function()
		{
			calTotal += parseInt($(this).html());
		});
	  $(".tcal").html(calTotal);
});



</script>
<!-- 預設狀態 -->
<script type="text/javascript">
    function check() {
        var c=0;
        $('.listindex').each(function () {
            if($(this).prop('checked')){
                c++;
            }
            if ($(".listindex").length==c){
                $('#checkproductall').prop('checked',true);

            }
            else{
                $('#checkproductall').prop('checked',false);

            }
        })
    }
    check();



   </script>

<script>
//全選按鈕
$("#checkproductall").click(function(){
if($("#checkproductall").is(":checked")){
	$.ajax({
     	url:'ShopCart',
     	type:"POST",
     	data:{
     		action:"finallcarlist"
     	
     	},
     	success:function(data){
     		changecarmun(data);
    
     	}

});}
else{
	$.ajax({
     	url:'ShopCart',
     	type:"POST",
     	data:{
     		action:"clearlist"
     	
     	},
     	success:function(data){
     		changecarmun(data);
    
     	}

});
	
}

});
//單選按鈕
$(".listindex").click(function(){
    check();

	checkbox();

	let index=$(this).siblings(".listmun").val();
	if($(this).is(":checked")==false){
	$.ajax({
     	url:'ShopCart',
     	type:"POST",
     	data:{
     		remmoveid:index ,
     		action:"removelist",

     	},
     	success:function(data){
     		
     	}

});
	}
	//點選加入
	else{
		$.ajax({
	     	url:'ShopCart',
	     	type:"POST",
	     	data:{
	     		addid:index ,
	     		action:"addlist",

	     	},
	     	success:function(data){
	     		
	     	}

	});	
		
		
		
	}
	
	
})




</script>




<script>





function del(){
var num=parseInt($('#quantity').text())-1;
if(num<1){
$('#quantity').text(1);
}else{
$('#quantity').text(num);
}
}
function add(){
var num=parseInt($('#quantity').text())+1;
$('#quantity').text(num);
}
</script>
<script>


</script>
<!-- 全選語法 -->
<script>
 $(document).ready(function(){
  $("#checkproductall").click(function(){

	  checkbox();

   if($("#checkproductall").prop("checked")){//如果全選按鈕有被選擇的話（被選擇是true）
    $("input[name='Checkbox[]']").each(function(){
     $(this).prop("checked",true);//把所有的核取方框的property都變成勾選
    })
   }else{
    $("input[name='Checkbox[]']").each(function(){
     $(this).prop("checked",false);//把所有的核方框的property都取消勾選
    })
   }
  })
 });
 
 function checkbox(){
		if($("input[name='Checkbox[]']:checked").length==0){
			$(".checking").attr('disabled', true);
			
		}//判斷有多少個方框被勾選
		else{
			$(".checking").attr("disabled", false);
			
			
		}
 }
 checkbox();

 
</script>  

</body>
</html>