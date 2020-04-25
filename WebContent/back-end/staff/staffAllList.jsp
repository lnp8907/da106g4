<%@page import="com.staff.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	StaffService staffService = new StaffService();
// 	MyStaffService myStaffService = new MyStaffService();
	List<StaffVO> list = staffService.getAll();
	String oldSff_id = (String)request.getAttribute("oldSff_id");
	if (oldSff_id != null){
	pageContext.setAttribute("oldSff_id", oldSff_id);	
	}
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("status",new String[] {"外送員","正職員工","最高管理員","已離職"});	

// 	pageContext.setAttribute("myStaffService", myStaffService);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>員工管理</title>
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
			課程總覽<span class="include-page"><%@ include file="page1.file"%>筆</span>
		</h1>
	</div>
	<!-- 卡片內容上方留白的結束標籤 -->
	<div class="table100 ver2 m-b-110">
		<div class="table100-head">
			<table class="table">
				<thead>
					<tr class="row100 head" >
						<th class="cell100 column4">員工編號</th>
						<th class="cell100 column4">員工姓名</th>
						<th class="cell100 column2">電子信箱</th>
						<th class="cell100 column4">連絡電話</th>
						<th class="cell100 column4">職位</th>						
						<th class="cell100 column4">性別</th>
						<th class="cell100 column4">資訊修改</th>
						<th class="cell100 column4">權限確認</th>
					</tr>
				</thead>
			</table>
		</div>

		<div class="table100-body js-pscroll">
			<table>

				<tbody>
					<c:forEach var="StaffVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr class="row100 body"  style="${(oldSff_id==StaffVO.staff_id)?'background-color: aliceblue;':''}">
							<td class="cell100 column4">${StaffVO.staff_id}</td>
							<td class="cell100 column4">${StaffVO.staff_name}</td>
							<td class="cell100 column2">${StaffVO.email}</td>
							<td class="cell100 column4">${StaffVO.phone}</td>
							<td class="cell100 column4">${status[StaffVO.staff_status]}</td>
							<td class="cell100 column4">${StaffVO.gender==0?'男':'女'}</td>
							<td class="cell100 column4"><a
								href="StaffServlet?staff_id=${StaffVO.staff_id}&action=getOne_ForStaff&whichPage=<%=whichPage%>"><button
										id="update-info">查詢</button></a></td>
						<td class="cell100 column4"><a
								href="StaffServlet?staff_id=${StaffVO.staff_id}&action=getOne_ForStaff&whichPage=<%=whichPage%>"><button
										id="update-info">確認</button></a></td>
						</tr>

					</c:forEach>
					<tr id="page2-tr">
						<td id="page2"><%@ include file="page2ForPagetype.file"%></td>
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
									aria-hidden="true">&times;</button>
								<h2 class="modal-title" id="myModalLabel">員工資料修改</h2>
							</div>

							<div class="modal-body">
								<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
								<jsp:include page="listOneStaff.jsp" />
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