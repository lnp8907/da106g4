<%@page import="com.instant_delivery_order.model.InstantDeliveryOrderVO"%>
<%@page import="com.instant_delivery_order.model.InstantDeliveryOrderService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.shop_order.model.*" %>
<%@ page import="com.order_detail.model.*" %>
<%@ page import="com.ordermanager.shop.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.*" %>
<%@ page import="com.instant_delivery_order.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.stream.Collectors" %>


<%
InstantDeliveryOrderService IDSvc=new InstantDeliveryOrderService();
boolean lookMap = false;
List<InstantDeliveryOrderVO> list=null;
if(IDSvc.getAll()!=null){
	list=IDSvc.getAll();
	
}

if(request.getParameter("pagemessage")!=null){
	 if(request.getParameter("pagemessage").equals("Traveling")){
		 	lookMap = true;
			list=list.stream()
					.filter(p->p.getO_status()==1)
					.collect(Collectors.toList());
		}
		else if(request.getParameter("pagemessage").equals("Finish")){
			list=list.stream()
					.filter(p->p.getO_status()==2)
					.collect(Collectors.toList());
			
		}
		else if(request.getParameter("pagemessage").equals("cancel")){
			list=list.stream()
					.filter(p->p.getO_status()==3)
					.collect(Collectors.toList());	
		}	
}
// if(request.getParameter("pagemessage")!=null){
// 	 if(request.getAttribute("pagemessage").equals("Traveling")){
// 			list=list.stream()
// 					.filter(p->p.getO_status()==1)
// 					.collect(Collectors.toList());
// 		}
// 		else if(request.getAttribute("pagemessage").equals("Finish")){
// 			list=list.stream()
// 					.filter(p->p.getO_status()==2)
// 					.collect(Collectors.toList());
			
// 		}
// 		else if(request.getAttribute("pagemessage").equals("Finish")){
// 			list=list.stream()
// 					.filter(p->p.getO_status()==3)
// 					.collect(Collectors.toList());	
// 		}	
	
// }





pageContext.setAttribute("list",list);

%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/OrderList.css">

<meta charset="UTF-8">
<title>所有訂單</title>
<c:set var="pagemessage" value='<%=((String)request.getParameter("pagemessage"))==null?"":(String)request.getParameter("pagemessage") %>'/>

<div id="ordertitle" style="height: 163px;padding-top:30px;color: #E4002B;font-family: 微软雅黑;
background-color: #f9f9f9; padding-left: 30px;
 ">
		 <h3 style="font-size: 40px">及時配送訂單:</h3>
	 <%if(list.size()>1){ %>
		 <%= list.get(0).getIdo_no() %>
		 
		 <%if(!list.get(0).getIdo_no().equals(list.get(list.size() - 1).getIdo_no())){ %>
		 →<%= list.get(list.size() - 1).getIdo_no() %>
		 <% }}%>
	</div>	 
		 
		
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>




<!-- 以下內容 -->


<%-- 現在PAGEMESSAGE:${pagemessage} --%>



    <table id="ordertable" class="ui red celled table">

	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>會員名稱</th>
		<th>狀態</th>
		<th>訂單日期</th>
		<th>總價</th>
		<th>付款方式</th>
		<th>查看訂單詳情</th>	
	</tr>
	<jsp:useBean id="memberService" scope="request" class="com.member.model.MemberService"/>
	<%@ include file="file/IOrderPage1.file" %> 
	
	<c:forEach var="ordervo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr class="ordertr1">
			<td>${ordervo.ido_no}</td>
			<td>${ordervo.member_id}</td>
			<td>${memberService.getOneMember(ordervo.member_id).member_name}</td>

<!-- 狀態 -->
			<td>${ostatus[ordervo.o_status]}</td>
			
			<c:set var="time" value="${ordervo.o_time}"/>
			<%
			String tsStr = "";  

			if(pageContext.getAttribute("time")!=null) {
				Timestamp ts = (Timestamp)pageContext.getAttribute("time");
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");  
				tsStr = sdf.format(ts);  %>
				
			<%}%>
			
			<td><%=tsStr %>				
			</td>
			
			
			
			
			<td>${ordervo.total}</td>
			

			<td>${pstatus[ordervo.p_status]}</td> 
			<td>
			<!-- 茶愾訂單明細 -->
			     <FORM METHOD="post" ACTION="Instant_delivery_orderServlet" >
			    <button  class="ui right labeled  icon button "><i class="zoom in icon"></i> 查看明細 </button>
			     
                <input 	type="hidden" name="ido_no"  value="${ordervo.ido_no}"/>
			  <input type="hidden" name="action" value="lookmore">
<!--                         <input  type="submit" value="查看訂單明細"> -->
                         <input 	type="hidden" name="pagemessage"  value="${pagemessage}"/>
                <input 	type="hidden" name="whichPage"  value="<%=whichPage%>"/>
			     </FORM>
			
			</td>

<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/OrderServlet.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="order_no"  value="${ordervo.ido_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="OrderUpdatepage"></FORM> -->
<!-- 			</td> -->
			<!-- 刪除 -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/OrderServlet.do" style="margin-bottom: 0px;"  onSubmit="return CheckForm();" > --%>
<!-- 			     <input type="submit" value="刪除" > -->
<%-- 			     <input type="hidden" name="order_no"  value="${ordervo.order_no}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"> -->
<%-- 			     <input	type="hidden" name="whichPage" value="<%=whichPage%>">  --%>
<%-- 			     			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
			     
			     
			 
<!-- 			    </FORM> -->
			     
<!-- 			</td> -->
			
		  </tr>
        <tr class="orseraddress"><td>地址:</td>
        <td colspan="5">${ordervo.d_address}</td>
                  				<td>
			<!-- 訂單地圖 -->
			<c:if test="<%=lookMap %>">
			<FORM METHOD="post" ACTION="Instant_delivery_orderServlet" >
			  <button class="ui right labeled  icon  ui primary button"><i class="zoom in icon"></i> 查看地圖 </button>	     
			  <input type="hidden" name="ido_no"  value="${ordervo.ido_no}">
			  <input type="hidden" name="action" value="getPositon">
  <input 	type="hidden" name="pagemessage"  value="${pagemessage}"/>
                <input 	type="hidden" name="whichPage"  value="<%=whichPage%>"/>			  
			</FORM>			
			</td>
              </c:if>
                <td>
                <form method="post" ACTION="Instant_delivery_orderServlet">
<%--                 <a href="<%=request.getContextPath() %>/back-end/Instant_order/Instant_delivery_orderServlet?action=cencelorder&ido_no=${ordervo.ido_no}"> --%>
                <input class="ostatus"	type="hidden" name="" value="${ordervo.o_status}"/>
                <input 	type="hidden" name="pagemessage"  value="${pagemessage}"/>
                <input 	type="hidden" name="whichPage"  value="<%=whichPage%>"/>
                <input 	type="hidden" name="action"  value="cencelorder"/>
                <input 	type="hidden" name="ido_no"  value="${ordervo.ido_no}"/>

                <input type="submit" class="cancelloreder" value="取消訂單">


                
<!--                 </a> -->
                </form>
            </td>
            
 </tr>
	</c:forEach>
</table>

<%@ include file="file/page2.file" %>




<script language="Javascript">

$(".ostatus").each(function () {
    if($(this).val()==3){
        $(this).siblings(".cancelloreder").hide();

    }
})


$(".cancelloreder").click(function(){
	CheckForm();
	
	
})

function CheckForm()
{

  if(confirm("取消訂單將無法再修改")==true)   {
    return true;
  }
  else  
    return false;

}   
</script>   


</body>
</html>