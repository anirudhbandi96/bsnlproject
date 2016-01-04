package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.print.DocFlavor.STRING;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

import com.google.gson.Gson;

import oracle.jdbc.rowset.OracleCachedRowSet;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw = response.getWriter();
		String submit = request.getParameter("submit");
		String[] array = request.getParameterValues("slot");
		try{
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
		if(submit.equals("remove")){
			pw.println("<h3>Selected Cards were Successfully Sent Offline!!!</h3><a href='secured/slot.html'>Click here to go back to the main page</a>");
			
		}
		else if (submit.equals("equip")){
			pw.println("<h3>Selected Cards are Online!!!</h3><a href='secured/slot.html'>Click here to go back to the main page</a>");
			
		}
		}
		
	catch(Exception ex){
		pw.println("<h1>Something went wrong!!!</h1><a href='slot.html'>Click here to go back to the main page</a>");						
		ex.printStackTrace();
	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
