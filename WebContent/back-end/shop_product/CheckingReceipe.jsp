<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.product.model.*"%>
<%
	ProductService Psvc = new ProductService();
	Collection<ProductVO> list = Psvc.gettypelist("料理組合包");
	
	list=list.stream()
			.filter(p->p.getProduct_status()==2)
			.collect(Collectors.toList());
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/CheckingRecipe.css">
    
    

<meta charset="UTF-8">
<title>所有商品處理頁面</title>
</head>

<body>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">省核食譜</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	
	
                        <div class="content">
                            <div class="menu">
                                <!-- 卡片內容上方留白的起始標籤 -->
                            </div><!-- 卡片內容上方留白的結束標籤 -->
                        </div>
                <div class="grid">
                    <!-- 卡片內容起始標籤 -->
                    <table class="ui selectable celled table">
                        <thead >
                        <tr id="Producttitle">
                            <th>商品圖</th>
                            <th>類別</th>
                            <th  class="product_idth" >商品編號</th>
                            <th class="recipe_th">食譜編號</th>
                            <th>商品名稱</th>
                            <th>商品單價</th>
                            <th>商品狀態</th>
                         
                            <th>刪除</th>
                        </tr>

                        		<%@ include file="../file/page1.file"%>
                        <jsp:useBean id="receiprsvc" class="com.recipe.model.RecipeService"/>

                        </thead>
                        <tbody id="ProductContext">
                       		<c:forEach var="productvo" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
                           <td><img width=80px height=70px
					src="${receiprsvc.getOneRecipe(productvo.recipe_id).recipe_photo}"></td>
                       <td>${productvo.product_type}</td>
				<td  class="product_idtd">${productvo.product_id}</td>
								<td class="recipe_td">${productvo.recipe_id}</td>
				
				<td>${receiprsvc.getOneRecipe(productvo.recipe_id).recipe_name}</td>
				<td>${productvo.product_price}</td>
				<%
					Map<Integer, String> map = new HashMap<>();
						map.put(1, "未上架");
						map.put(0, "已上架");
						map.put(2, "審核中");

						request.setAttribute("productstatus", map);
				%>
				<c:set var="status" value="${productvo.product_status}" />
				
				
				
				 <td>
				 <input id="recipeProductid" type="hidden" value="${productvo.product_id}">
				 
			
                      <button class="right attached ui button Checkproduct onRproduct"  ${ productstatus [status]=='審核中'? "


                        style='background-color: #fd2436;color: white'":""} >審核中</button>
                     
                     </td>
                     
                     <!-- 上架按鍵-->
                     
                                
                                
                                
                                


			
			
				<td>
					<!-- 刪除 -->
					<c:set var="id" value="${productvo.product_id}"/>
									<%String id =(String)pageContext.getAttribute("id"); %>
					
					<FORM METHOD="post" ACTION="Productmanage" style="margin-bottom: 0px;">
						<input type="hidden" name="action" value="delete"> 
						<input  class='ui  icon button'  <%=Psvc.isProduct_idFK(id)?"":"disabled='disabled'"   %>
						
					 type="submit" value="刪除"> <input type="hidden"
							name="product_id" value="${productvo.product_id}"> 
							<input	type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">

					
					</FORM>
				</td>
                        </tr>
		</c:forEach>
                    
                        </tbody>
                    </table>
<%@ include file="../file/page2.file"%>
                </div><!-- end of grid -->
      
                
     
<script>
    $( function() {
        $( "#receipeform" ).dialog({

            autoOpen: false,

            show: "Fold",

            hide: "explode",


            });

    $(".onRproduct").click(function () {
    	
    	$('#getproductid').val($(this).prev("#recipeProductid").val());
        $("#receipeform").dialog("open");

        return false;
    });
    $('.cancel-On-Receipe').click(function () {
    	$("#recipePrice").val(1);
        $("#receipeform").dialog("close");

    })

    });

</script>


<div id="receipeform" class="border">
    <form METHOD="post" ACTION="Productmanage">
        <div>
       <font> 請設定價格:</font>
        </div>
        <div class="ui input focus">
            <input id="recipePrice" name="price" type="text" value="1" placeholder="請輸入數字...">
        </div>
        <br>
        <div id="checkRbtn">

        <div class="ui icon input">
            <input class="ui  button cancel-On-Receipe" type="button" value="取消"><i class="undo alternate icon"></i>

        </div>

        <div class="ui icon input">
        <input class="ui  button"  type="submit" value="送出"><i class=" arrow circle right icon"></i>
	<input	type="hidden"  name="status" value="0">

	<input	type="hidden"  name="action" value="onproduct">
	<input	type="hidden" name="onbtn" value="onbtn">	
        <input	type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
	<input	type="hidden" id="getproductid" name="product_id" />
        </div>

        </div>


    </form>

</div>
</body>
</html>