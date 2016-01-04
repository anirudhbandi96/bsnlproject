<%@ page import="javax.json.*" contentType="application/json" %>
<%
java.io.PrintWriter pw = response.getWriter();
		
			javax.sql.rowset.CachedRowSet crs = new oracle.jdbc.rowset.OracleCachedRowSet();
		crs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		crs.setUsername("hr");
		crs.setPassword("hr");
		crs.setCommand("select * from slots where status = 'offline' ");
		crs.execute();
        JsonArrayBuilder value = Json.createArrayBuilder();
    while(crs.next())
        {
    	JsonObjectBuilder row = Json.createObjectBuilder();
		row.add("slot_number", crs.getString("slot_number"));
		row.add("card", crs.getString("card"));
		row.add("status",crs.getString("status"));
		value.add(row.build());
        }
    crs.close();
        pw.println(value.build().toString());
		
		
		
		
		%>
	