<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.shop_order.model.*" %>
<%@ page import="com.order_detail.model.*" %>
<%@ page import="com.ordermanager.shop.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.*" %>
<%@page import="com.member.model.*"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="java.util.stream.Collectors"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%

OrderService orderSvc=new OrderService();
List<Shop_orderVO>list=null;
MemberVO memberVO=null;
if(session.getAttribute("memberVO")!=null){
	 memberVO=(MemberVO)session.getAttribute("memberVO");
	list=orderSvc.getOrderBYMEMBER(memberVO.getMember_id());
}

%>
<c:set var="memberorderlist" value="<%=list %>"/>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>個人訂單</title>

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
		<th>會員名稱</th>
		<th>狀態</th>
		<th>訂單日期</th>
		<th>總價</th>
		<th>付款方式</th>
		<th>查看訂單詳情</th>	
		<th>修改</th>
		<th>發送訊息</th>
	</tr>
	<%@ include file="file/page1.file" %> 
	
	<c:forEach var="ordervo" items="${memberorderlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<c:set var="member_id" value="${ordervo.member_id}"/>
		
		<div>
		<tr class="ordertr1">
			<td>${ordervo.order_no}</td>
		
			<td>${memberVO.member_name}</td>
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
			
			<c:set var="time" value="${ordervo.order_time}"/>
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
			
						<%
    Map<Integer, String> pay_typemap = new HashMap<>();
						pay_typemap.put(0, "點數");
						pay_typemap.put(1, "信用卡");

    session.setAttribute("pay_type", pay_typemap);
%>



<c:set var="paytype" value="${ordervo.pay_type}" />
			<td>${pay_type[paytype]} </td> 
			<td>
			<!-- 茶愾訂單明細 -->
			     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/OrderServlet.do" >
			    <button class="ui right labeled  icon button"><i class="zoom in icon"></i> 查看更多 </button>
			  <input type="hidden" name="url" value="<%=request.getAttribute("url")%>">
			  <input type="hidden" name="whichPage" value="<%=whichPage%>">			     
			  <input type="hidden" name="order_no"  value="${ordervo.order_no}">
			  <input type="hidden" name="action" value="lookmore">
                        <input style="display: none" type="submit" value="查看訂單明細">
                        
                        
			     </FORM>
			
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/OrderServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="order_no"  value="${ordervo.order_no}">
			     <input type="hidden" name="action"	value="updateAddress"></FORM>
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
			     <input type="hidden" name="empno"  value="${empVO.empno}">
			     <input type="hidden" name="action" value="delete">
			     <input	type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input	type="hidden" name="whichPage" value="<%=whichPage%>"> 
			     
			     
			     </FORM>
			</td>
		  </tr>
        <tr class="orseraddress ordertr2"><td>地址</td><td colspan="9">
        
        
        ${ordervo.dv_address}
<c:if test="${ordervo.dv_address}">
        <c:set var="address" value="${ordervo.dv_address}"/>

        <%String addresstot=(String)pageContext.getAttribute("address");
        
        String city="";
        String town="";
        String address3="";
        int addressstr=addresstot.indexOf("/");
        if(addressstr >2){	
        	String address[]=addresstot.split("/");
       	 city=address[0];
       	 town=address[1];
       	 address3=address[2];	
        }
        addresstot=city+town+address3;
        
        %><%=addresstot %>
        			</c:if>
        
        </td>
 </tr>
	</c:forEach>
</table>

<%@ include file="file/page2.file" %>

<style>
{
background-color: teal;

}



</style>
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