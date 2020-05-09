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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 


<%

OrderService orderSvc=new OrderService();
List<Shop_orderVO>list=null;
list= orderSvc.getAll();

// String Message2="";

String Message="";

if(request.getAttribute("Order_statusPage")!=null){
	 Message=(String)request.getAttribute("Order_statusPage");	
}
else{
	if(request.getParameter("Order_statusPage")!=null){
		 Message=(String)request.getParameter("Order_statusPage");					
	}	
}

if(Message.equals("")){	
}
else{
	if(Message.equals("waitpage")){  
		list=list.stream().filter(p->p.getOrder_status()==0)
				.collect(Collectors.toList());
	}
	if(Message.equals("traveling")){  
		list=list.stream().filter(p->p.getOrder_status()==1)
				.collect(Collectors.toList());
	}
	if(Message.equals("complete")){  
		list=list.stream().filter(p->p.getOrder_status()==2)
				.collect(Collectors.toList());
	}
	if(Message.equals("cancel")){  
		list=list.stream().filter(p->p.getOrder_status()==3)
				.collect(Collectors.toList());
	}
}










// 	if((request.getAttribute("Order_statusPage")==null&&request.getParameter("Order_statusPage")==null)||(request.getAttribute("Order_statusPage")==null&&request.getParameter("Order_statusPage").equals(""))){  
// 		list= orderSvc.getAll();
// 	}
// 	else{
// 		if(request.getParameter("Order_statusPage")!=null){
// 			 Message=(String)request.getParameter("Order_statusPage");					
// 		}
// 		else{
// 		 Message=(String)request.getAttribute("Order_statusPage");}
// 	if(Message.equals("waitpage")){  
// 		list= orderSvc.getAll();
// 		list=list.stream().filter(p->p.getOrder_status()==0)
// 				.collect(Collectors.toList());
// 	}
// 	if(Message.equals("traveling")){  
// 		list= orderSvc.getAll();
// 		list=list.stream().filter(p->p.getOrder_status()==1)
// 				.collect(Collectors.toList());
// 	}
// 	if(Message.equals("complete")){  
// 		list= orderSvc.getAll();
// 		list=list.stream().filter(p->p.getOrder_status()==2)
// 				.collect(Collectors.toList());
// 	}
// 	if(Message.equals("cancel")){  
// 		list= orderSvc.getAll();
// 		list=list.stream().filter(p->p.getOrder_status()==3)
// 				.collect(Collectors.toList());
// 	}
	
// 	}
	
request.setAttribute("list",list);
MemberService msvc=new MemberService();

%>

換頁回傳值:<%=Message %>
<c:set var="Order_statusPage" value="<%=Message %>" scope="session"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>所有訂單</title>
		 <c:if test="${fn:length(list)>0}">

<div id="ordertitle">
		 <h3>以下是所有訂單:</h3>
		 <%= list.get(0).getOrder_no() %>
		 
		 <%if(!list.get(0).getOrder_no().equals(list.get(list.size() - 1).getOrder_no())){ %>
		 →<%= list.get(list.size() - 1).getOrder_no() %>
		 <%} %>
	</div>	 
</c:if>		 
		
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
		<th>發送訊息</th>
	</tr>
	<%@ include file="../file/page1.file" %> 
	
	<c:forEach var="ordervo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<c:set var="member_id" value="${ordervo.member_id}"/>
		
		<div>
		<tr class="ordertr1">
			<td>${ordervo.order_no}</td>
			<td>${ordervo.member_id}</td>
			<%
			MemberVO mvo=msvc.getOneMember((String)pageContext.getAttribute("member_id"));

			
			%>
			<td><%=mvo.getMember_name() %></td>
  	<%
    Map<Integer, String> order_status = new HashMap<>();
  	order_status.put(0, "已成立");
  	order_status.put(1, "運送中");
  	order_status.put(2, "已完成");
  	order_status.put(3, "取消訂單");
    request.setAttribute("statusmap", order_status);

%>

			<td>${statusmap[ordervo.order_status]}</td>
			
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
			  <input type="hidden" name="pagemessage" value="lookmore">
                        <input style="display: none" type="submit" value="查看訂單明細">
                                                 <input  type="hidden" name="Order_statusPage" value="${Order_statusPage}">
                        
                        
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
			<!-- 還沒做 -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"   >
			     <input  class="ui button" type="submit" value="發送訊息" >
			     <input	type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input	type="hidden" name="whichPage" value="<%=whichPage%>"> 
			     
			     
			     
			     </FORM>
			</td>
		  </tr>
        <tr class="orseraddress ordertr2"><td>地址</td><td colspan="6">
        
        
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
        


        	<td class="selectstatus">
        <select class=selectchange>
        	<c:forEach var="order_status" items="<%=order_status %>">
        
  <option value ="${order_status.key}" ${ordervo.order_status==order_status.key?'selected':''} >${order_status.value}</option>
  </c:forEach>
  			     <input type="hidden" class="selectorder_status" name="selectorder_status"  value="${ordervo.order_status}">
  			     <button class="showbtn" style="display: none">已完成之訂單</button>
  			     
</select>

  <FORM METHOD="post" class="" ACTION="<%=request.getContextPath()%>/back-end/shop_order/OrderServlet.do" style="margin-bottom: 0px;">
			     <input  type="hidden" name="order_status" class="order_status">
			     <input type="hidden" name="order_no"  value="${ordervo.order_no}">
			     <input type="hidden" name="action"	value="changestatus">
			     <input class="sendstatus" type="submit" style="display: none">
                                                 <input  type="hidden" name="Order_statusPage" value="${Order_statusPage}">
			     
			     			  <input type="hidden" name="whichPage" value="<%=whichPage%>">			     
			     
			     
			     
			     </FORM>


        </td>
       
        
        	<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop_order/OrderServlet.do" style="margin-bottom: 0px;">
			 <button class="ui btn labeled icon button"><i class="address card icon"></i> 修改地址 </button>
			   
			  
<!-- 			     <input class="ui  icon button btn" type="submit" value="修改地址"> <i class="cloud icon"></i></input> -->
			   
<!-- 			     <input class="btn " type="submit" style="display: none" value="修改地址"></input> -->
			   
			   
			     <input class="isupate" type="hidden" name="order_status"  value="${ordervo.order_status}">
			     <input type="hidden" name="order_no"  value="${ordervo.order_no}">
			     <input type="hidden" name="action"	value="updateAddress"></FORM>
			                                                      <input  type="hidden" name="Order_statusPage" value="${Order_statusPage}">
			     
			     			  <input type="hidden" name="whichPage" value="<%=whichPage%>">			     
			     
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
$(".isupate").each(function () {
    if($(this).val()>1){
        $(this).siblings(".btn").attr('disabled', 'disabled');
        $(this).siblings(".btn").removeClass("address");
    }
  
})





</script>   
<script>
       
       function checkselectstatus(){
    	   $('.selectstatus').each(function(){
    	       if($(this).find(".selectorder_status").val()
    	       ==3||$(this).find(".selectorder_status").val()==2){
    	           $(this).find('.selectchange').hide();
    	       $(this).find('.showbtn').show();
    	       }
    	       else  if($(this).find(".selectorder_status").val()
    	    	       ==1){
    	    	           $(this).find("option:nth-child(1)").attr('disabled', 'disabled');
//     	    	           $(this).find("option:nth-child(3)").attr('disabled', 'disabled');
    	    	           

    	    	       }
    	   
    	   })
    	       }
       
       checkselectstatus();

       
      $(".selectchange").change(function(){
          var opt=$(this).val();
          $(this).siblings().find('.order_status').val(opt);
          alert($(this).siblings().find('.order_status').val());
          $(this).siblings().find('.sendstatus').click();

          
      });
       
       </script>

</body>
</html>