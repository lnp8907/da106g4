<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>驗證帳號</title>
</head>
<body>
<form method="post" action="member.do">
<input name="email" type="email" id="email" placeholder="請輸入你的E-mail" value="<%=(request.getParameter("email")==null)?"":request.getParameter("email") %>">
<input name="code" placeholder="請輸入驗證碼">
<input type="submit" value="確認送出">
<input name="action" value="checkCode" type="hidden">
</form>
<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
		<br>
		<form method="post" action="member.do">
		<input type="submit" onClick=getEmail() value="重寄驗證信">
		<input type="hidden" name="action" value="resetCode" >
		<input type="hidden" id="hiddenEmail" name="email">
		</form>
	</c:if>

</body>
<script >
function getEmail(){
	var email = document.getElementById("email").value;
	if(email===null||email === ""){
		alert('請輸入EMAIL');
		event.preventDefault();
	}else{
		document.getElementById("hiddenEmail").value = email;
	}
}
</script>
</html>