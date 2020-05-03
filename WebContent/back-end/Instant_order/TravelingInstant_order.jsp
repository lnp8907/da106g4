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

<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.text.*" %>
<%@ page import="com.instant_delivery_order.*" %>


<% 
InstantDeliveryOrderService IDSvc=new InstantDeliveryOrderService();
List<InstantDeliveryOrderVO> list=null;
if(IDSvc.getAll()!=null){
	list=IDSvc.getAll();
	list=list.stream()
			.filter(p->p.getO_status()==1)
			.collect(Collectors.toList());
}

pageContext.setAttribute("list",list);




%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/OrderList.css">

<meta charset="UTF-8">
<title>運送中訂單</title>
<div id="ordertitle">
		 <h3>以下是所有訂單:</h3>
		 <%= list.get(0).getIdo_no() %>
		 
		 <%if(!list.get(0).getIdo_no().equals(list.get(list.size() - 1).getIdo_no())){ %>
		 →<%= list.get(list.size() - 1).getIdo_no() %>
		 <% }%>
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
		<th>修改</th>
		<th>發送訊息</th>
	</tr>
	<%@ include file="../file/page1.file" %> 
	
	<c:forEach var="ordervo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
			<td>${ordervo.ido_no}</td>
			<td>${ordervo.member_id}</td>
			<td>會員名稱</td>


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
			     <FORM METHOD="post" ACTION="OrderServlet.do" >
			    <button class="ui right labeled  icon button"><i class="zoom in icon"></i> 查看更多 </button>
			     
			  <input type="hidden" name="order_no"  value="${ordervo.ido_no}">
			  <input type="hidden" name="action" value="getorderdetail">
                        <input style="display: none" type="submit" value="查看訂單明細">
			     </FORM>
			
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/OrderServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="order_no"  value="${ordervo.ido_no}">
			     <input type="hidden" name="action"	value="OrderUpdatepage"></FORM>
			</td>
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
			<!-- 還沒做 -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"   >
			     <input type="submit" value="發送訊息" >
			     <input	type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input	type="hidden" name="whichPage" value="<%=whichPage%>"> 
			     
			     
			     </FORM>
			</td>
		  </tr>
        <tr class="orseraddress"><td>地址</td><td colspan="9">${ordervo.d_address}</td>
 </tr>
	</c:forEach>
</table>

<%@ include file="../file/page2.file" %>


<script language="Javascript">   
function CheckForm()
{

  if(confirm("確認要刪除!? \n整個訂單會不見耶")==true)   {
    return true;
  }
  else  
    return false;

}   
</script>   


</body>
</html>