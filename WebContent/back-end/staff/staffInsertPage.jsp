<%@page import="com.staff.model.StaffVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
StaffVO staffVO = (StaffVO) request.getAttribute("staffVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工新增</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
<style>
html, body {
	width: 100%;
	height: 100%;
	font-family: "微軟正黑體", "標楷體", "Lato", sans-serif;
	background-color: pink;
}
.well{
    margin-top: 30px;
    width: 70%;
}
#success_message {
	display: none;
}
</style>
<body>
	<div class="container">

		<form class="well form-horizontal" action="StaffServlet" method="post"
			id="contact_form">
			<fieldset>

				<!-- Form Name -->
				<legend>員工新增</legend>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">員工姓名</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input name="staff_name"
								placeholder="員工姓名" class="form-control" type="text" value="<%=(staffVO == null) ? "" : staffVO.getStaff_name()%>">
						</div>
					</div>
				</div>



				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label">E-Mail</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-envelope"></i></span> <input name="email"
								placeholder="E-Mail Address" class="form-control" type="text" value="<%=(staffVO == null) ? "" : staffVO.getEmail()%>">
						</div>
					</div>
				</div>


				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">Phone</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-earphone"></i></span> <input name="phone"
								placeholder="(0975)555-121" class="form-control" type="text" value="<%=(staffVO == null) ? "" : staffVO.getPhone()%>">
						</div>
					</div>
				</div>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">部門</label>
					<div class="col-md-4 selectContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-list"></i></span> <select name="staff_status"
								class="form-control selectpicker">
								<option value="" <%="selected"%>>Please select the status</option>
								<option value="0">外送員</option>
								<option value="1">一般員工</option>
								<option value="2">最高管理員</option>
							</select>
						</div>
					</div>
				</div>

				<!-- radio checks -->
				<div class="form-group">
					<label class="col-md-4 control-label">性別</label>
					<div class="col-md-4">
						<div class="radio">
							<label> <input type="radio" name="gender" value="0"  <%=(staffVO == null||staffVO.getGender()==0) ? "CHECKED":""%>/>
								男
							</label>
						</div>
						<div class="radio">
							<label> <input type="radio" name="gender" value="1" <%=(staffVO != null&&staffVO.getGender()==1) ? "CHECKED":""%>/>
								女
							</label>
						</div>
					</div>
				</div>



				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<button type="submit" class="btn btn-warning">
							Send <span class="glyphicon glyphicon-send"></span>
						</button>
						<input type="hidden" name="action" value="insert">
						<input type="hidden" name="pageType" value="staffInsertPage.jsp">
					</div>
				</div>

			</fieldset>
		</form>
	</div>
	<!-- /.container -->
	<div class="errorMess">
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>

</body>
<script>
	$(document)
			.ready(
					function() {
						$('#contact_form')
								.bootstrapValidator(
										{
											// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
											feedbackIcons : {
												valid : 'glyphicon glyphicon-ok',
												invalid : 'glyphicon glyphicon-remove',
												validating : 'glyphicon glyphicon-refresh'
											},
											fields : {
												first_name : {
													validators : {
														stringLength : {
															min : 2,
														},
														notEmpty : {
															message : 'Please supply your name'
														}
													}
												},
												email : {
													validators : {
														notEmpty : {
															message : 'Please supply your email address'
														},
														emailAddress : {
															message : 'Please supply a valid email address'
														}
													}
												},
												phone : {
													validators : {
														notEmpty : {
															message : 'Please supply your phone number'
														},
													}
												},
												
												state : {
													validators : {
														notEmpty : {
															message : 'Please select your dpt.'
														}
													}
												},
											}
										})
					});

</script>

</html>