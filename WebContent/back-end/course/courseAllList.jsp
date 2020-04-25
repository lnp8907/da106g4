<%@page import="com.mycourse.model.MyCourseService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.course.model.*"%>
<%
	CourseService courseService = new CourseService();
	MyCourseService myCourseService = new MyCourseService();
	List<CourseVO> list = courseService.getAll();
	String oldCourse_id = (String)request.getAttribute("oldCourse_id");
	if (oldCourse_id != null){
	pageContext.setAttribute("oldCourse_id", oldCourse_id);	
	}
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("myCourseService", myCourseService);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>課程管理</title>
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
						<th class="cell100 column4">課程編號</th>
						<th class="cell100 column4">廚師編號</th>
						<th class="cell100 column1">課程名稱</th>
						<th class="cell100 column2">開課日期</th>
						<th class="cell100 column4">上限人數</th>
						<th class="cell100 column4">報名人數</th>
						<th class="cell100 column4">課程價格</th>
						<th class="cell100 column4">課程狀態</th>
						<th class="cell100 column4">修改狀態</th>
					</tr>
				</thead>
			</table>
		</div>

		<div class="table100-body js-pscroll">
			<table>

				<tbody>
					<c:forEach var="CourseVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr class="row100 body"  style="${(oldCourse_id==CourseVO.course_id)?'border:2px solid #FF5757;':''}">
							<td class="cell100 column4">${CourseVO.course_id}</td>
							<td class="cell100 column4">${CourseVO.member_id}</td>
							<td class="cell100 column1">${CourseVO.course_name}</td>
							<td class="cell100 column2"><fmt:formatDate
									value="${CourseVO.course_start}" pattern="yyyy/MM/dd HH:mm" /></td>
							<td class="cell100 column4">${CourseVO.num_max}</td>
							<td class="cell100 column4">${myCourseService.appliedNum(CourseVO.course_id)}</td>
							<td class="cell100 column4">${CourseVO.course_price}</td>
							<td class="cell100 column4">${status[CourseVO.course_status]}</td>
							<td class="cell100 column4"><a
								href="CourseServlet?course_id=${CourseVO.course_id}&action=getOne_ForBackEnd_A&from=A&whichPage=<%=whichPage%>"><button
										id="update-info">修改</button></a></td>
						</tr>

					</c:forEach>
					<tr id="page2-tr">
						<td id="page2"><%@ include file="page2.file"%></td>
					</tr>
				</tbody>
			</table>
			<c:if test="${openModal!=null}">

				<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
					aria-labelledby="basicModal" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h2 class="modal-title" id="myModalLabel">課程修改</h2>
							</div>

							<div class="modal-body">
								<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
								<jsp:include page="listOneCourse_ById.jsp" />
								<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary">Save
									changes</button>
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
							$('#changeStatus').submit();
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
				$('.course_status').on(
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