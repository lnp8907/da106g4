<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="java.sql.*" import="java.util.ArrayList" %>
    
<%
Class.forName("oracle.jdbc.driver.OracleDriver");
String connUrl = "jdbc:oracle:thin:@localhost:1521:xe";
Connection conn = DriverManager.getConnection(connUrl,"DA106_G4","DA106_G4");
String stmt = "SELECT RECIPE_NAME, RECIPE_PHOTO FROM RECIPE ";
PreparedStatement rsStmt = conn.prepareStatement(stmt);

ResultSet rs = rsStmt.executeQuery();

class RecipeVO {
	String recipe_name;
	String recipe_photo;	
	
	void setRecipeName(String recipe_name) {
		this.recipe_name = recipe_name;
	}
	void setRecipePhoto(String recipe_photo) {
		this.recipe_photo = recipe_photo;
	}
	
	String getRecipeName() {
		return this.recipe_name;
	}
	String getRecipePhoto() {
		return this.recipe_photo;
	}
		
}

ArrayList<RecipeVO> list = new ArrayList<>();
while(rs.next()){
	RecipeVO vo = new RecipeVO();
 	vo.setRecipeName(rs.getString("RECIPE_NAME"));
 	vo.setRecipePhoto(rs.getString("RECIPE_PHOTO"));
 	list.add(vo);	
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>


<%for(RecipeVO vo:list) {%>

	<div style="display:inline-block; margin:20px auto;width:45%;" >
	     <img src=<%= vo.getRecipePhoto() %>>
		 <p> <%= vo.getRecipeName() %> </p>	
	</div>	

<% } %>


</body>
</html>