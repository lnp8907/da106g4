<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ page import="com.product.model.*"%>
<html>
<%
ProductVO productvo =null;
if(request.getAttribute("detailProductvo")!=null){
      productvo = (ProductVO)request.getAttribute("detailProductvo"); //EmpServlet.java(Concroller), 存入req的empVO物件
}%>
<c:set var="productvo" value="<%=productvo %>" scope="request"/> 

<%@ page import=" java.util.*"%>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品新增</title>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="">
<FORM class=" ui form" METHOD="post" ACTION="ProductServlet" name="form1" enctype="multipart/form-data">
  <h4 class="ui dividing header">更新資訊:</h4>
    <label>上傳圖片:</label>
		    <div class="two fields">
		      <div class="field">
<img  src="Product_photoReader?product_id=<%=productvo.getProduct_id()%>"
			 id="preview_progressbarTW_img" width=100px height=100px; src="#" />		
		      </div>
		      <div class="field">
		<input id="imgView"style="background-color: transparent;" type="file" name="product_photo" size="45" />		
		      </div>
		      </div>
		    <label>商品編號:<font color=red><b>*</b></font>
	${productvo.product_id} </label>
   
   
    <div class="two fields">
      <div class="field">
            <label>商品類型:</label>
      	<select name="product_type">
						<%List<String>producttype=new ArrayList(); 
		producttype.add("水果類");
		producttype.add("肉類");
		producttype.add("蔬菜類");
		producttype.add("乳品類");
		producttype.add("油脂類");
		producttype.add("魚貝類");
		producttype.add("菇類");
		producttype.add("穀物類");
		producttype.add("澱粉類");
		producttype.add("酒類");
		producttype.add("調味料及香辛料類");
		producttype.add("料理組合包");
		%>
		<%for(String type:producttype){%>
		
		
		
		<%="<option value='"+type+"'"%><%=((productvo.getProduct_type()).equals(type))?"selected":"" %><%=">"+type+"</option>" %>
		<%} %>

				</select>
      
</div>

      <div class="field">
            <label>商品名稱:</label>
      
      <input type="TEXT" name="product_name" size="45" value="<%=productvo.getProduct_name()%>" />
</div>
</div>
	 <div class="two fields">
      <div class="field">
            <label>商品單價:</label>
		<input type="TEXT" name="product_price" size="45"	value="<%=productvo.getProduct_price()%>" />
</div>
      <div class="field">
            <label>商品狀態:</label>
		<select name="product_status" size="1">
			<%List<Integer>productstatus=new ArrayList(); 
			productstatus.add(0);
			productstatus.add(1);

		%>
		<%for(Integer status:productstatus){%>
		
		
		
		<%="<option value='"+status+"'"%><%=(productvo.getProduct_status()==status)?"selected":"" %>
		
		<%=">"%><%=(status==0)?"已上架":"未上架 "%>
		
		<%="</option>" %>
		<%} %>

</select>
</div>
</div>
		<table class="ui celled diettable table" style="background-color: transparent;"  border='0'>
    <tr style="background-color: transparent;"><td >營養成分</td>
    <th ></th>
  </tr>
  <tbody>
   <tr>
      <td>碳水化合物:</td>
      <th><input type="TEXT" name="carbohydrate" size="45" value="<%=productvo.getCarbohydrate() %>" /></th>

    </tr>
   <tr>
      <td >蛋白質:</td>
      <th><input type="TEXT" name="protein" size="45" value="<%=productvo.getProtein()%>" /></th>

    </tr>
       <tr>
      <td >脂質:</td>
      <th><input type="TEXT" name="fat" size="45" value="<%=productvo.getFat()%>" /></th>

    </tr>
       <tr>
      <td>卡洛里:</td>
      <th><input type="TEXT" name="calorie" size="45" value="<%=productvo.getCalorie()%>" /></th>

    </tr>
       <tr>
      <td >維生素B:</td>
      <th><input type="TEXT" name="vitamin_B" size="45" value="<%=productvo.getVitamin_B()%>" /></th>

    </tr>
       <tr>
      <td >維生素C:</td>
      <th><input type="TEXT" name="vitamin_C" size="45" value="<%=productvo.getVitamin_C()%>" /></th>

    </tr>
  <tr>
      <td >鈉:</td>
      <th><input type="TEXT" name="salt" size="45" value="<%=productvo.getSalt()%>" /></th>

    </tr>
      <tr>
      <td >膳食纖維:</td>
      <th><input type="TEXT" name="vagetbale" size="45" value="<%=productvo.getVagetbale()%>" /></th>

    </tr>
    <tr>
      <td >內容:</td>
      <th>
      
      
      
<%--       <input type="TEXT" height=400px name="content" size="45" value="<%=productvo.getContent()%>" /> --%>
      					<textarea name="content" rows="10" cols="50" >
<%=productvo.getContent()%>
</textarea>
				
      
      
      
      </th>

    </tr>
    </tbody>
  
  
</table>
  <button class="ui right labeled icon button" style="float:right;" type="submit"><i class="right arrow icon"></i> 更新商品 </button>

<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="product_id" value="<%=productvo.getProduct_id()%>">
<!-- <input type="submit" value="送出修改" > -->
<input type="hidden" name="whichPage" value="${whichPage}">
<%-- which=${whichPage} --%>

</FORM>
</div>
<script>
if($("input").val()=="null"){
	$("input").val("");
	
}

$("#imgView").change(function(){
 
   readURL(this);  
});

   function readURL(input){
	   if(input.files && input.files[0]){
	     var reader = new FileReader();
	     reader.onload = function (e) {
	        $("#preview_progressbarTW_img").attr('src', e.target.result);
	     }
	     reader.readAsDataURL(input.files[0]);
	   }
	 }
</script>

</body>
<style>

.product-upatefrom{
background-image: linear-gradient(to right, #ff8177 0%, #ff867a 0%, #ff8c7f 21%, #f99185 52%, #cf556c 78%, #b12a5b 100%);


}
.diettable td{
color: white;


}

</style>


</html>