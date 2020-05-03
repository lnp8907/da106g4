<%@page import="com.coupon.model.CouponVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="couponSvc" class="com.coupon.model.CouponService"/>


<%
	List<CouponVO> list = couponSvc.getAll();
	pageContext.setAttribute("list", list);
	String oldC_no = (String) request.getAttribute("oldC_no");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>優惠券管理</title>
<body>
	<div class="content">
		<div class="menu">
			<!-- 卡片內容上方留白的起始標籤 -->
			<h1>
				優惠券管理<span class="include-page"><%@ include file="page1.file"%></span>
			</h1>
		</div>
		<!-- 卡片內容上方留白的結束標籤 -->
		<div class="table100 ver2 m-b-110">
			<div class="table100-head">
				<table>
					<thead>
						<tr class="row100 head">
							<th class="cell100 column4">優惠券編號</th>
							<th class="cell100 column4">優惠券名稱</th>
							<th class="cell100 column4">優惠券圖片</th>
							<th class="cell100 column2">起始日期</th>
							<th class="cell100 column2">截止日期</th>
							<th class="cell100 column4">優惠券代碼</th>
							<th class="cell100 column4">折數</th>
							<th class="cell100 column4">查看詳情</th>
						</tr>
					</thead>
				</table>
			</div>

			<div class="table100-body js-pscroll">
				<table>

					<tbody>
						<c:forEach var="CouponVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr class="row100 body" style="${(oldC_no==CouponVO.c_no)?'background-color: aliceblue;':''}">
								<td class="cell100 column4">${CouponVO.c_no}</td>
								<td class="cell100 column4">${CouponVO.c_name}</td>
								<td class="cell100 column4"><img src="<%=request.getContextPath()%>/back-end/coupon/DBGifReader4.do?c_no=${CouponVO.c_no}"
								style="width:50px;height:50px;"></td>
								<td class="cell100 column2">${CouponVO.start_date}</td>
								<td class="cell100 column2">${CouponVO.end_date}</td>
								<td class="cell100 column4">${CouponVO.coupon_code}</td>
								<td	class="cell100 column4">${CouponVO.discount}</td>
													<td class="cell100 column4"><a
								href="coupon.do?c_no=${CouponVO.c_no}&c_name=${CouponVO.c_name}&action=getOne_For_Display&whichPage=<%=whichPage%>"><button
										id="update-info">查看</button></a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div><div id="page2-tr" style="height: 85px;"><span id="page2"><%@ include file="page2.file"%></span></div>
		<c:if test="${openModal!=null}">

				<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
					aria-labelledby="basicModal" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h2 class="modal-title" id="myModalLabel">優惠券詳細清單</h2>
							</div>

							<div class="modal-body">
								<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
								<jsp:include page="listOneCoupon_ById.jsp" />
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
					$(document).ready(function() {
						$('.btn-primary').on('click', function() {
							$('#changeStatus').submit();
						});
					});
				</script>
			</c:if>
	</div>
</body>
<script>
	$(document).ready(function() {
		$('.recipe_status').on('change', function() {
			$(this).parent('form').submit();
		});
	});
</script>

</html>