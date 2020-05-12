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
</head>
<body>
Gyventojai
<div id="criteria">
<form method ="post" action ="" enctype = "">
<label for="salis">Šalis</label>
<select name="salis" id="salis" value="1">
	<option value="xxx">visos</option>
	<option value="1">Lietuva</option>
	<option value="5">Kinija</option>
	<option value="4">Japonija</option>
	<option value="3">Lenkija</option>
	<option value="2">United Kingdom</option>	
</select><br>
<input type="checkbox" name="pagal_lyti" id="pagal_lyti" value="1" checked>
<label for="pagal_lyti">skirstyti pagal lytį</label><br>
<input type="checkbox" name="pagal_gyv_mieste" id="pagal_gyv_mieste" value="1" checked>
<label for="pagal_gyv_mieste">skirstyti pagal gyv. mieste</label><br>
<input type="checkbox" name="sumuoti_pagal_salis" id="sumuoti_pagal_salis" value="1" checked>
<label for="amziu_ribos">sumuoti pagal šalis</label><br>
<select name="amziu_ribos" id="amziu_ribos" value="xxx">
	<option value="xxx">be ribų</option>
	<option value="5" selected>5m.</option>
	<option value="10">10m.</option>
	<option value="20">20m</option>
	<option value="30">30m</option>
	<option value="50">50m.</option>	
</select><br>
<input type="submit" name="do_filter" id="do_filter" value="Atrinkti">
</form>
</div>

<%
	String salis = "nepasirinkta";
	String pagal_lyti = "nepasirinkta";
	String pagal_gyv_mieste = "nepasirinkta";
	String sumuoti_pagal_salis = "nepasirinkta";		
	String amziu_ribos = "nepasirinkta";
	
	try{
	     
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		
		
	} catch(Exception e) {}

	try { 
	
		connection = DriverManager.getConnection ( connectionUrl + dbName + "?useUnicode=yes&characterEncoding=UTF-8", userId, password );
		String add; 
		String sql = "";
		
		String do_filter = "";
		
			
		
		if ( ( ( do_filter = request.getParameter("do_filter")  ) != null ) && do_filter.equals ( "Atrinkti" ) ) {
		
			salis = request.getParameter ( "salis" );
			
			if ( ( pagal_lyti = request.getParameter ( "pagal_lyti" ) ) == null ) {

				pagal_lyti = "nepazymeta";				
			}
			
			if ( ( pagal_gyv_mieste = request.getParameter ( "pagal_gyv_mieste" ) ) == null ) {

				pagal_gyv_mieste = "nepazymeta";				
			}
			
			if ( ( sumuoti_pagal_salis = request.getParameter ( "sumuoti_pagal_salis" ) ) == null ) {

				sumuoti_pagal_salis = "nepazymeta";				
			}
					
			amziu_ribos = request.getParameter ( "amziu_ribos" );		
		
		}
%>		
Pasirinkti paieškos parametrai:<br>
Šalis: <%= salis %><br>
Amziaus grupe: <%= amziu_ribos %><br>
Ar gyvena mieste: <%= ( pagal_gyv_mieste.equals( "1" ) ? "Taip" : "Ne" ) %> <br>
Sumuoti pagal salis: <%= ( sumuoti_pagal_salis.equals( "1" ) ? "Taip" : "Ne" ) %><br>
Skirstyti pagal lyti: <%= ( pagal_lyti.equals( "1" ) ? "Taip" : "Ne" ) %><br>
<%		
		String order_by = " ORDER BY ";
		String kur = " WHERE ";
		String kablelis = "";
		String lentele = 
			"SELECT "
			+ "`salys`.`pav` AS `Salys`\n"
			+ ",`gyventojai`.`id`\n"
			+ ",`salys`.`id`\n"
					+ ", `amz_grupes`\n"
					+ ", `kiekis`\n"
					+ ", `lytis`\n"
					+ ", `miestai`.`pavadinimas` AS `Miestas`\n";
					
		
		if(request.getParameter("pagal_lyti") != null) {
               
          
			order_by += kablelis + "`lytis`";
			kablelis = ",";
		
			lentele += " FROM `miestai`\n"
			+ " LEFT JOIN `gyventojai` ON (`miestai`.`id` = `gyventojai`.`id_miesto`)\n"
			+ " LEFT JOIN `salys` ON (`miestai`.`id_salies` = `salys`.`id`)\n";
					
			lentele += order_by;
		  }
            
		
%>
<pre id="sql">
<%= lentele %>		
</pre>
<%		
		sql = 
				"SELECT "
					+ "`gyventojai`.`id`\n"
					+ ", `amz_grupes`\n"
					+ ", `kiekis`\n"
					+ ", `lytis`\n"
					+ ", `miestai`.`pavadinimas` AS `miestas`\n"
					+ ", `salys`.`pav`\n"
					+ ", `salys`.`valiuta` AS `valiuta`\n"
					+ ", `salys`.`kodas` AS `kodas`\n"
					+ ", `salys`.`id` AS `sid`\n"
				+ "FROM \n" 
					+ "`gyventojai` \n"
				+ "LEFT JOIN\n"
					+ "`miestai` ON `gyventojai`.`id_miesto`=`miestai`.`id`\n" 
				+ "LEFT JOIN\n"
					+ "`salys` ON `miestai`.`id_salies`=`salys`.`id`\n"														
				+ "ORDER BY `salys`.`pav`\n"
				;
%>				
<pre id="sql">
<%= sql %>		
</pre>
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
		statement_take = connection.createStatement();
						
		resultSet = statement_take.executeQuery(lentele);
		
		while( resultSet.next() )	{
	
%>
<tr>
<td><%= resultSet.getString( "miestas" ) %></td>
<td><%= resultSet.getString( "lytis" ) %></td>
<td><%= resultSet.getString( "kiekis" ) %></td>
<td><%= resultSet.getString( "amz_grupes" ) %></td>
</tr>
<%
		}
	} catch ( Exception e ) {
	
		e.printStackTrace();
	}
%>
</table>

</body>
</html>