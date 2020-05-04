<%@page import="com.instant_delivery_order.model.InstantDeliveryOrderVO"%>
<%@page import="com.instant_delivery_order.model.InstantDeliveryOrderService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
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
if(list!=null){
pageContext.setAttribute("list",list);
}



%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/OrderList.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<meta charset="UTF-8">
<title>所有訂單</title>
<div id="ordertitle">
		 <h3>以下是所有訂單:</h3>
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
    <table id="ordertable" class="ui red celled table">

	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>會員名稱</th>
		<th>狀態</th>
		<th>訂單日期</th>
		<th>總價</th>
		<th>付款方式</th>
		<th>配送狀態</th>	

	</tr>
	<%@ include file="../file/page1.file" %> 
	
	<c:forEach var="ordervo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
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
			  <button class="ui right labeled  icon button"><i class="zoom in icon"></i> 查看 </button>	     
			  <input type="hidden" name="ido_no"  value="${ordervo.ido_no}">
			  <input type="hidden" name="action" value="getPositon">
			</FORM>			
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
			
		  </tr>
        <tr class="orseraddress"><td>地址:</td><td colspan="7">${ordervo.d_address}</td>
 </tr>
	</c:forEach>
</table>

<%@ include file="../file/page2.file" %>

	<c:if test="${openModal!=null}">
				<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
					aria-labelledby="basicModal" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h2 class="modal-title" id="myModalLabel">外送員配送位置</h2>
							</div>

							<div class="modal-body">
								<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
								<jsp:include page="googleMap.jsp" />
								<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>

						</div>
					</div>
				</div>

				<script>
					$("#basicModal").modal({
						show : true
					});
					</script>
			</c:if>



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