<!DOCTYPE html>
<%@page pageEncoding="UTF-8" language="java"%>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
	<style>
	table {
	border-collapse: collapse;
	}
	table, td, th {
	border: 1px solid black;
	}
	</style>
	<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
	String driverName = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost:3306/";
	String dbName = "demografija";
	String userId = "root";
	String password = "";

	Connection connection = null;
	Statement statement_take = null;
	Statement statement_change = null;
	ResultSet resultSet = null;
	int resultSetChange;
	
%>
<%
	String[] lent_gyventojai = { "amz_grupes", "flag_gyv_mieste", "id_miesto", "kiekis", "lytis" };
	String[] lauk_gyventojai = new String [ lent_gyventojai.length];
	try{
	     
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		
		
	} catch(Exception e) {}

	try { 
	
		connection = DriverManager.getConnection ( connectionUrl + dbName + "?useUnicode=yes&characterEncoding=UTF-8", userId, password );
		String add; 
		
		if ( ( ( add = request.getParameter("add")  ) != null ) && add.equals ( "papildyti" ) ) {
		
																																					// Miestai miestas = new Miestai ( lent_miestu );
																																					// miestas.takeFromParams ( request );

			for ( int i = 1; i<lent_gyventojai.length; i++ ) {
			
				lauk_gyventojai [ i ] = request.getParameter ( lent_gyventojai [ i ] );
			}

			String sql_ins = "";
			String comma = "";
			
			for ( int i = 1; i < lent_gyventojai.length; i++ ) {
			
				sql_ins =  sql_ins + comma  + "'" + lauk_gyventojai [ i ] + "'";
				comma = ",";																													// sql_ins = sql_ins + "'" + Miestai.value + "'";
			}
			
			sql_ins = 
				"INSERT INTO `gyventojai`"
				+ " ( `amz_grupes`, `flag_gyv_mieste`, `id_miesto`, `kiekis`, `lytis` )"
				+ " VALUES ( "			
				+ sql_ins
				+ " )";

			out.println ( sql_ins );

			statement_change = connection.createStatement();
			resultSetChange = statement_change.executeUpdate(sql_ins);			
			
		 } else {
		 
			if ( add != null ) {

				out.println ( add );
			}
		 } 
		
		statement_take = connection.createStatement();		
		String sql = "SELECT * FROM `gyventojai`  WHERE 1";
		

		resultSet = statement_take.executeQuery(sql);
		 
		while( resultSet.next() ){
		
		int kiekis = resultSet.getInt( "kiekis" );
		int amz_grupes = resultSet.getInt ( "amz_grupes" );
		int id_miesto = resultSet.getInt ("id_miesto");
		String lytis = resultSet.getString( "lytis" );
			 
%>

<tr>
	<td><input type="button" class="record_edit" data-id_miesto="" value="&#9998;"></td>
<%
		for ( int i = 1; i < lauk_gyventojai.length; i++ ) {
%>
	<td><%= resultSet.getString (  lent_gyventojai [ i ]  ) %></td>
<%
		}
%>
</tr>
<% 
		}

	} catch ( Exception e ) {
	
		e.printStackTrace();
	}
%>

</head>
<body>
Gyventojai
<div id="criteria">
<form method ="post" action ="" enctype = "">
<label for="salis">Šalis</label>
<select name="salis" id="salis" value="xxx">
	<option value="xxx">visos</option>
	<option value="ltl">Lietuva</option>
	<option value="lat">Latvija</option>
	<option value="blr">Baltarusija</option>
	<option value="pol">Lenkija</option>
	<option value="rus">Rusija</option>	
</select><br>
<input type="checkbox" name="pagal_lyti" id="pagal_lyti" value="1" checked>
<label for="pagal_lyti">skirstyti pagal lytį</label><br>
<input type="checkbox" name="pagal_gyv_mieste" id="pagal_gyv_mieste" value="1" checked>
<label for="pagal_gyv_mieste">skirstyti pagal gyv. mieste</label><br>
<input type="checkbox" name="sumuoti_pagal_salis" id="sumuoti_pagal_salis" value="1" checked>
<label for="amziu_ribos">sumuoti pagal šalis</label><br>
<select name="amziu_ribos" id="amziu_ribos" value="5">
	<option value="xxx">be ribų</option>
	<option value="5" selected>5m.</option>
	<option value="10">10m.</option>
	<option value="20">20m</option>
	<option value="30">30m</option>
	<option value="50">50m.</option>	
</select><br>
<input type="button" name="do_filter" id="do_filter" value="Atrinkti">
</form>
</div>
<table>
	<tr>
		<th>
			Miestas
		</th>
		<th>
			Lytis
		</th>
		<th>
			Gyv. mieste
		</th>
		<th>
			0-5
		</th>
		<th>
			6-10
		</th>
		<th>
			11-15
		</th>
		<th>
			16-20
		</th>
		<th>
			21-25
		</th>
		<th>
			26-30
		</th>
		<th>
			31-35
		</th>
		<th>
			36-40
		</th>
		<th>
			41-45
		</th>		
		<th>
			46-50
		</th>
		<th>
			51-55
		</th>
		<th>
			56-60
		</th>
		<th>
			61-65
		</th>
		<th>
			66-70
		</th>
		<th>
			71-75
		</th>
		<th>
			76-80
		</th>
		<th>
			81-85
		</th>
		<th>
			>85
		</th>
</tr>
<%
try {
connection = DriverManager.getConnection(
connectionUrl + dbName, userId, password);
statement_take = connection.createStatement();
				
String sql = "SELECT"
		+ "`pavadinimas` AS `Miestas`" 
		+ ", `gyventojai`.`lytis` AS `Lytis`" 
		+ ", `gyventojai`.`flag_gyv_mieste` AS `Gyv. mieste`" 
		+ ", `miestai`.`pavadinimas` AS `miestas`"	
		+ ",SUM(gyventojai.`kiekis`) AS `Kiekis`"
		+ "FROM"
		+ "`miestai`" 
		+ "LEFT JOIN"
		+ "`gyventojai` ON (`miestai`.`id` = `gyventojai`.`id_miesto`)"
		;

resultSet = statement_take.executeQuery(sql);

while (resultSet.next()) {
	
	String group_by = "GROUP BY ";
		String kablelis = "";
		Integer amzius = 0;
		String select_gyventoju_duomenys = 
			"SELECT "
			+ "salys.`pav` AS Salis"
			+ ",`pavadinimas` AS Miestas";
		if(pagal_lyti == 1 ) {
			select_gyventoju_duomenys += ",`gyventojai`.`lytis` AS Lytis";
			group_by += kablelis + "gyventojai.`lytis`";
			kablelis = ",";
		}
		if(pagal_gyv_mieste == 1) {
			select_gyventoju_duomenys += ",`gyventojai`.`flag_gyv_mieste` AS Gyv. mieste";
			group_by += kablelis + "gyventojai.`flag_gyv_mieste`";
			kablelis = ",";
		}
		select_gyventoju_duomenys += ",`gyventojai`.`amz_grupes` AS Amziu grupes"
			+ ",SUM(gyventojai.`kiekis`) AS Kiekis"
			+ " FROM miestai"
			+ " LEFT JOIN gyventojai ON(miestai.`id` = gyventojai.`id_miesto`)"
			+ " LEFT JOIN salys ON(miestai.`id_salies` = salys.`id`)";
		if(pagal_salis == 1) {
			group_by += "salys.`id`";
			kablelis = ",";
		}
		if(salis_id != 0) {
		String group_by = "GROUP BY ";
		String kablelis = "";
		Integer amzius = 0;
		String select_gyventoju_duomenys = 
			"SELECT "
			+ "salys.`pav` AS `Salis`";
		
		if(pagal_salis == 1) {
			group_by += kablelis + "salys.`id`";
			kablelis = ",";
		}
		select_gyventoju_duomenys += ",`pavadinimas` AS `Miestas`";
		group_by += kablelis + "`miestai`.`id`";
		if(pagal_lyti == 1 ) {
			select_gyventoju_duomenys += ",`gyventojai`.`lytis` AS `Lytis`";
			group_by += kablelis + "gyventojai.`lytis`";
			kablelis = ",";
		}
		if(pagal_gyv_mieste == 1) {
			select_gyventoju_duomenys += ",`gyventojai`.`flag_gyv_mieste` AS `Gyv. mieste`";
			group_by += kablelis + "`gyventojai`.`flag_gyv_mieste`";
			kablelis = ",";
		}
		group_by += kablelis + "gyventojai.`amz_grupes`";
		
		select_gyventoju_duomenys += ",`gyventojai`.`amz_grupes` AS Amziu grupes"
			+ ",SUM(gyventojai.`kiekis`) AS Kiekis"
			+ " FROM miestai"
			+ " LEFT JOIN gyventojai ON(miestai.`id` = gyventojai.`id_miesto`)"
			+ " LEFT JOIN salys ON(miestai.`id_salies` = salys.`id`)";

		if(salis_id != 0) {
			select_gyventoju_duomenys += "WHERE `salys`.`id`=" + `salis_id`;
		}
		select_gyventoju_duomenys += group_by;
	
	
%>
<tr bgcolor="#8FBC8F">

<td><%=resultSet.getString("miestas")%></td>
<td><%=resultSet.getString("Lytis")%></td>
<td><%=resultSet.getString("Gyv. Mieste")%></td>
<td><%=resultSet.getString("Kiekis")%></td>
<%
}

} catch (Exception e) {
e.printStackTrace();
}
%>

</tr>	
</table>

</body>
</html>