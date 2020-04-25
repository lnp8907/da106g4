<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.shop_order.model.*" %>
<%@ page import="com.order_detail.model.*" %>
<%@ page import="com.ordermanager.shop.*" %>
<%
OrderService orderSvc=new OrderService();
String member_id = (String) request.getAttribute("member_id");
List<Shop_orderVO>list;
if(member_id==null){
	 list = orderSvc.getAll();
}

else{
	list=orderSvc.getOrderBYMEMBER(member_id);
}
pageContext.setAttribute("list",list);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有訂單</title>
<style>
  table#table-1 {
	background-color: #ffa0a5;
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body>
<table id="table-1">
	<tr><td>
		 <h3>以下是所有訂單:
		 <%= list.get(0).getOrder_no() %>→<%= list.get(list.size() - 1).getOrder_no() %>
		 
		 
		 
		 </h3>
		 <h4>
		<a href="order_manager_page.jsp"><img  src="<%=request.getContextPath()%>/image/FoodPron_Logo.png"
		  width="100" height="100" border="0">回訂單管理</a></h4>
	</td></tr>
	<td>
	<a href="../ShopBackendHomePage.jsp">返回管理頁面</a>
	
	
	
	</td>
</table>
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
<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>會員名稱</th>
		<th>狀態</th>
		<th>訂單日期</th>
		<th>總價</th>
		<th>付款方式</th>
		<th>地址</th>
		<th>查看訂單詳情</th>	
		<th>修改</th>
		<th>刪除</th>
		<th>發送訊息</th>
	</tr>
	<%@ include file="../file/page1.file" %> 
	
	<c:forEach var="ordervo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ordervo.order_no}</td>
			<td>${ordervo.member_id}</td>
			<td>會員名稱</td>
			<%
    Map<Integer, String> orderstatusmap = new HashMap<>();
			orderstatusmap.put(0, "已成立");
			orderstatusmap.put(1, "運送中");
			orderstatusmap.put(2, "已完成");
			orderstatusmap.put(3, "取消訂單");
    session.setAttribute("orderstatus", orderstatusmap);
%>
<c:set var="status" value="${ordervo.order_status}" />

			<td>${orderstatus[status]}</td>
			<td>${ordervo.order_time}</td>
			<td>${ordervo.total}</td>
						<%
    Map<Integer, String> pay_typemap = new HashMap<>();
						pay_typemap.put(0, "點數");
						pay_typemap.put(1, "信用卡");

    session.setAttribute("pay_type", pay_typemap);
%>



<c:set var="paytype" value="${ordervo.pay_type}" />
			<td>${pay_type[paytype]} </td> 
			<td>${ordervo.dv_address}</td>
			<td>
			<!-- 茶愾訂單明細 -->
			     <FORM METHOD="post" ACTION="OrderServlet.do" >
			  <input type="hidden" name="order_no"  value="${ordervo.order_no}">
			  <input type="hidden" name="action" value="getorderdetail">
       <input type="submit" value="查看訂單明細">
			     </FORM>
			
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/OrderServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="order_no"  value="${ordervo.order_no}">
			     <input type="hidden" name="action"	value="OrderUpdatepage"></FORM>
			</td>
			<!-- 刪除 -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/OrderServlet.do" style="margin-bottom: 0px;"  onSubmit="return CheckForm();" >
			     <input type="submit" value="刪除" >
			     <input type="hidden" name="order_no"  value="${ordervo.order_no}">
			     <input type="hidden" name="action" value="delete">
			     <input	type="hidden" name="whichPage" value="<%=whichPage%>"> 
			     			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     
			     
			 
			    </FORM>
			     
			</td>
			<!-- 還沒做 -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"   >
			     <input type="submit" value="發送訊息" >
			     <input type="hidden" name="empno"  value="${empVO.empno}">
			     <input type="hidden" name="action" value="delete">
			     <input	type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input	type="hidden" name="whichPage" value="<%=whichPage%>"> 
			     
			     
			     </FORM>
			</td>
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