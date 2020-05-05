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
<title>員工資料修改 - update_emp_input.jsp</title>

<style>
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
<body bgcolor='white'>



<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="product.do" name="form1" enctype="multipart/form-data">
<table>
<tr>
		<td><img  src="Product_photoReader?product_id=<%=productvo.getProduct_id()%>"
			 id="preview_progressbarTW_img" width=100px height=100px; src="#" />
			</td>
		<td><input id="imgView" type="file" name="product_photo" size="45" />

		</td>
	</tr>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td>${productvo.product_id} </td>
	</tr>
		<tr>
		<td>商品類型:</td>
		<td><select name="product_type">
						<%List<String>producttype=new ArrayList(); 
		producttype.add("水果類");
		producttype.add("肉類");
		producttype.add("蔬菜類");
		producttype.add("乳品類");
		producttype.add("油脂類");
		producttype.add("魚貝類");
		producttype.add("菇類");
		producttype.add("穀物類");
		producttype.add("澱粉纇");
		producttype.add("酒類");
		producttype.add("調味料及香辛料類");
		producttype.add("料理組合包");
		%>
		<%for(String type:producttype){%>
		
		
		
		<%="<option value='"+type+"'"%><%=((productvo.getProduct_type()).equals(type))?"selected":"" %><%=">"+type+"</option>" %>
		<%} %>

				</select>
		
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="product_name" size="45" value="<%=productvo.getProduct_name()%>" /></td>
	</tr>
	
	<tr>
		<td>商品單價:</td>
		<td><input type="TEXT" name="product_price" size="45"	value="<%=productvo.getProduct_price()%>" />
		</td>
	</tr>
	
	<tr>
		<td>狀態:</td>
	<td><select name="product_status" size="1">
			<%List<Integer>productstatus=new ArrayList(); 
			productstatus.add(0);
			productstatus.add(1);

		%>
		<%for(Integer status:productstatus){%>
		
		
		
		<%="<option value='"+status+"'"%><%=(productvo.getProduct_status()==status)?"selected":"" %>
		
		<%=">"%><%=(status==0)?"已上架":"未上架 "%>
		
		<%="</option>" %>
		<%} %>

</select></td>
	
	</tr>
<tr>
		<td>碳水化合物:</td>
		<td><input type="TEXT" name="carbohydrate" size="45" value="<%=productvo.getCarbohydrate() %>" /></td>
	</tr>
	<tr>
		<td>蛋白質:</td>
		<td><input type="TEXT" name="protein" size="45" value="<%=productvo.getProtein()%>" /></td>
	</tr>
	<tr>
		<td>脂質:</td>
		<td><input type="TEXT" name="fat" size="45" value="<%=productvo.getFat()%>" /></td>
	</tr>
	<tr>
		<td>卡洛里:</td>
		<td><input type="TEXT" name="calorie" size="45" value="<%=productvo.getCalorie()%>" /></td>
	</tr>
	<tr>
		<td>維生素B:</td>
		<td><input type="TEXT" name="vitamin_B" size="45" value="<%=productvo.getVitamin_B()%>" /></td>
	</tr>
	<tr>
		<td>維生素C:</td>
		<td><input type="TEXT" name="vitamin_C" size="45" value="<%=productvo.getVitamin_C()%>" /></td>
	</tr>
	<tr>
		<td>鈉:</td>
		<td><input type="TEXT" name="salt" size="45" value="<%=productvo.getSalt()%>" /></td>
	</tr>
	<tr>
		<td>膳食纖維:</td>
		<td><input type="TEXT" name="vagetbale" size="45" value="<%=productvo.getVagetbale()%>" /></td>
	</tr>
	<tr>
		<td>內容:</td>
		<td><input type="TEXT" height=400px name="content" size="45" value="<%=productvo.getContent()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="product_id" value="<%=productvo.getProduct_id()%>">
<input type="submit" value="送出修改"></FORM>

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


</html>