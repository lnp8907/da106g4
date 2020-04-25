<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*" import="java.util.ArrayList"
	import="java.text.DecimalFormat"  import="com.recipe.model.RecipeVO"%>

<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String connUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection conn = DriverManager.getConnection(connUrl, "DA106_G4", "DA106_G4");
	String stmt = "SELECT RECIPE_NAME, RECIPE_PHOTO, RECIPE_CONTENT,carbo_intake, protein_intake, salt_intake, vage_intake,vitamin_b,vitamin_c FROM RECIPE where recipe_ID = " + request.getParameter("recipe_id");
	PreparedStatement rsStmt = conn.prepareStatement(stmt);

	ResultSet rs = rsStmt.executeQuery();


	
	RecipeVO vo = new RecipeVO();
	while (rs.next()) {
		vo.setRecipe_name(rs.getString("RECIPE_NAME"));
		vo.setRecipe_photo(rs.getString("RECIPE_PHOTO"));
		vo.setRecipe_content(rs.getString("RECIPE_CONTENT"));
		vo.setCarbo_intake(rs.getDouble("carbo_intake"));
		vo.setProtein_intake(rs.getDouble("protein_intake"));
		vo.setSalt_intake(rs.getDouble("salt_intake"));
		vo.setVage_intake(rs.getDouble("vage_intake"));
		vo.setVitamin_b(rs.getDouble("vitamin_b"));
		vo.setVitamin_c(rs.getDouble("vitamin_c"));
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
#main {
	width: 1024px;
	margin: 20px auto;
}

#myChart-container {
	width: 500px;
	float: left;
}

.chart--container {
	width: 100%;
	height: 100%;
	min-height: 300px;
	float: left;
}

#recipe-container {
	width: 500px;
	float: left;
	margin-top: 30px;
}

#recipe-container img {
	width: 100%;
}
</style>
<script src="https://cdn.zingchart.com/zingchart.min.js"></script>
</head>
<body>
	<%
		DecimalFormat formatter = new DecimalFormat("##.00");
			String format = formatter.format(vo.getSalt_intake());
			double slat = Double.valueOf(format);
			String format1 = formatter.format(vo.getCarbo_intake());
			double carbo_intake = Double.valueOf(format1);
			String format2 = formatter.format(vo.getProtein_intake());
			double protein_intake = Double.valueOf(format2);
			String format3 = formatter.format(vo.getVage_intake());
			double vage_intake = Double.valueOf(format3);
			String format4 = formatter.format(vo.getVitamin_b());
			double vitamin_b = Double.valueOf(format4);
			String format5 = formatter.format(vo.getVitamin_c());
			double vitamin_c = Double.valueOf(format5);
	%>
	<div id="main" style="margin: 20px auto;">
		<div id="recipe-container">
			<img src=<%=vo.getRecipe_photo()%>>
			<p>
				<%=vo.getRecipe_name()%>
			</p>
		</div>
		<div id="myChart-container">
			<div id="myChart" class="chart--container"></div>
		</div>
	</div>
	<script>
		var myConfig = {
			type : 'radar',
			plot : {
				aspect : 'area',
				animation : {
					effect : 3,
					sequence : 1,
					speed : 700
				}
			},
			scaleV : {
				visible : false
			},
			scaleK : {
				values : '0:5:1',
				labels : [ '鹽含量(mg)', '蛋白質(g)', '食物纖維(g)', '碳水化合物(g)',
						'維生素B(mg)', '維生素C(mg)' ],
				item : {
					fontColor : '#607D8B',
					backgroundColor : "white",
					borderColor : "#aeaeae",
					borderWidth : 1,
					padding : '5 10',
					borderRadius : 10
				},
				refLine : {
					lineColor : '#c10000'
				},
				tick : {
					lineColor : '#59869c',
					lineWidth : 2,
					lineStyle : 'dotted',
					size : 20
				},
				guide : {
					lineColor : "#607D8B",
					lineStyle : 'solid',
					alpha : 0.3,
					backgroundColor : "#AAAAAA #DDDDDD"
				}
			},
			series : [ {
				values : [
	<%=slat%>
		,
	<%=protein_intake%>
		,
	<%=vage_intake%>
		,
	<%=carbo_intake%>
		,
	<%=vitamin_b%>
		,
	<%=vitamin_c%>
		],
				lineColor : '#FF5757',
				backgroundColor : '#FF8888'
			} ]
		};

		zingchart.render({
			id : 'myChart',
			data : myConfig,
			height : '100%',
			width : '100%'
		});
	</script>


</body>
</html>