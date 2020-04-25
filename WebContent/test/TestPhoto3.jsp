<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*" import="java.util.ArrayList"
	import="java.util.Base64"%>

<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String connUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection conn = DriverManager.getConnection(connUrl, "DA106_G4", "DA106_G4");
	String stmt = "SELECT VIDEO FROM LIVESTREAM WHERE livestream_id ='410012' ";
	PreparedStatement rsStmt = conn.prepareStatement(stmt);

	ResultSet rs = rsStmt.executeQuery();

	rs.next();
	Base64.Encoder encoder = Base64.getEncoder(); 
	byte[] v_byte = rs.getBytes("VIDEO");
	StringBuilder sb = new StringBuilder();
	sb.append("data:video/mp4;base64,");
	sb.append(encoder.encodeToString(rs.getBytes("VIDEO")));
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>

</style>
<script src="https://cdn.zingchart.com/zingchart.min.js"></script>
</head>
<body>

			<video src=<%= sb%>></video>
		
</body>
</html>