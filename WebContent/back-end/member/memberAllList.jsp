<%@page import="com.member.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	MemberService memberService = new MemberService();
// 	MyStaffService myStaffService = new MyStaffService();
	List<MemberVO> list = memberService.getAll();
	String oldMem_id = (String)request.getAttribute("oldMem_id");
	if (oldMem_id != null){
	pageContext.setAttribute("oldMem_id", oldMem_id);	
	}
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("member_status",new String[] {"普通會員","廚師","凍結"});	
	pageContext.setAttribute("validation",new String[] {"未驗證","已驗證"});	
// 	pageContext.setAttribute("myStaffService", myStaffService);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員管理</title>
<style>
.cell100 {
    text-align: center;
}
#update-info {
	border: 1px #E4002B solid;
	width: 50px;
	height: 40px;
	border-radius: 20px;
	background-color: #FF5757;
	color: #FFF;
	box-shadow: 2px 3px 3px #AAA
}
</style>
<body>
	<div class="menu">
		<!-- 卡片內容上方留白的起始標籤 -->
		<h1>
			會員總覽<span class="include-page"><%@ include file="page12.file"%></span>
		</h1>
	</div>
	<!-- 卡片內容上方留白的結束標籤 -->
	<div class="table100 ver2 m-b-110">
		<div class="table100-head">
			<table class="table">
				<thead>
					<tr class="row100 head" >
						<th class="cell100 column4">會員編號</th>
						<th class="cell100 column4">帳號</th>
						<th class="cell100 column4">會員姓名</th>
						<th class="cell100 column2">信箱</th>
						<th class="cell100 column4">頭像</th>				
						<th class="cell100 column4">驗證狀態</th>
						<th class="cell100 column4">會員狀態</th>
						<th class="cell100 column4">資訊修改</th>
					
						                                    						              

						
					</tr>
				</thead>
			</table>
		</div>

		<div class="table100-body js-pscroll">
			<table>

				<tbody>
					<c:forEach var="MemberVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr class="row100 body"  style="${(oldMem_id==MemberVO.member_id)?'background-color: aliceblue;':''}">
							<td class="cell100 column4">${MemberVO.member_id}</td>
							<td class="cell100 column4">${MemberVO.account}</td>
							<td class="cell100 column4">${MemberVO.member_name}</td>
							<td class="cell100 column4">${MemberVO.email}</td>
							<td class="cell100 column4"><img src=DBGifReader4.do?photo_type=mempic&member_id=${MemberVO.member_id} id="preview_progressbarTW_img" width=100px height=100px;/></td>
							<td class="cell100 column4">${validation[MemberVO.validation]}</td>
							<td class="cell100 column4">${member_status[MemberVO.member_status]}</td>
							
							
							
<%-- 							<td class="cell100 column4">${MemberVO.gender==0?'男':'女'}</td> --%>
							
							
							
							

							
							<td class="cell100 column4"><a                                    
								href="member.do?member_id=${MemberVO.member_id}&action=getOneMemberDisplay123&whichPage=<%=whichPage%>"><button
										id="update-info">修改</button></a></td>
						
						</tr>

					</c:forEach>
					<tr id="page2-tr">
						<td id="page2"><%@ include file="page22.file"%></td>
					</tr>
				</tbody>
			</table>
			<c:if test="${openModal!=null}">

				<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
					aria-labelledby="basicModal" aria-hidden="true">
					<div class="modal-dialog modal-lg" style="width: 65%;">
						<div class="modal-content" >

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									ari
									a-hidden="true">&times;</button>
								<h2 class="modal-title" id="myModalLabel">會員資料修改</h2>
							</div>

							<div class="modal-body">
								<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
								<jsp:include page="listOneMember1.jsp" />
								<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消修改</button>
								<button type="button" class="btn btn-primary">送出確認</button>
							</div>

						</div>
					</div>
				</div>

				<script>
					$("#basicModal").modal({
						show : true
					});
					$(document).ready(function() {
						$('.btn-primary').on('click', function() {
							$('#update_form').submit();
						});	
					});
				</script>
			</c:if>
		</div>
	</div>
</body>








<script>
	$(document).ready(
			function() {
				$('.Staff_status').on(
						'change',
						function() {
							if (confirm('確定更改為['
									+ $(this).find('option:selected').text()
									+ ']嗎?')) {
								$("#changeStatus").submit();
							}
							return false;
						});
			});
</script>
</html>