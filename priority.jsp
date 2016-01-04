<%@ page import="javax.json.*" contentType="application/json" %>
<%
        java.io.PrintWriter pw = response.getWriter();
		HttpSession s = request.getSession();
		String priority = (String)s.getAttribute("priority");
    	JsonObjectBuilder p = Json.createObjectBuilder();
		p.add("priority",priority);
        pw.println(p.build().toString());
		
		
		
		
		%>
		