<%@ page import="javax.json.*" contentType="application/json" %>
<%@ page import= "java.io.IOException" %>
 <%@page import= "java.io.PrintWriter" %>
 <%@ page import= "java.sql.*"  %>
 <%@ page import= "java.sql.PreparedStatement" %> 
 <%@ page import= "java.util.ArrayList"%>
<% 
java.io.PrintWriter pw = response.getWriter();
		 String[] array = request.getParameterValues("slots[]");
		 String submit = request.getParameter("submit");
		Class.forName("oracle.jdbc.OracleDriver");
         Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
         response.setContentType("text/html");
		for(String a : array){
		if(submit.equals("remove")){
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("update slots set status = 'offline' where slot_number = ?  ");
			ps.setString(1,a);
			int rows = ps.executeUpdate();
			con.commit();
			
			if(rows != 0 ){
			System.out.println("success!!");
			}
			else{
				System.out.println("failed!!");
			}
		}
		else if (submit.equals("equip")){
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("update slots set status = 'online' where slot_number = ?  ");
			ps.setString(1,a);
			int rows = ps.executeUpdate();
			con.commit();
			if(rows != 0 ){
			System.out.println("success!!");
			}
			else{
				System.out.println("failed!!");
			}
		}
             
			}
		JsonObjectBuilder r = Json.createObjectBuilder();
		if(submit.equals("remove")){
			//pw.println("<h3>Selected Cards were Successfully Sent Offline!!!</h3><a href='secured/slot.html'>Click here to go back to the main page</a>");
			r.add("message", "off");
		}
		else if (submit.equals("equip")){
			//pw.println("<h3>Selected Cards are Online!!!</h3><a href='secured/slot.html'>Click here to go back to the main page</a>");
			r.add("message", "on");
		}
		pw.println(r.build());
		}
		
	catch(Exception ex){
		//pw.println("<h1>Something went wrong!!!</h1><a href='slot.html'>Click here to go back to the main page</a>");
		JsonObjectBuilder r = Json.createObjectBuilder();
		r.add("message", "wrong");
		pw.println(r.build());
		ex.printStackTrace(); 
	}
	%>