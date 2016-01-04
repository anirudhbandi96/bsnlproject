package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
//@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String  p = "" ;
		try{
		javax.sql.rowset.CachedRowSet crs = new oracle.jdbc.rowset.OracleCachedRowSet();
		crs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		crs.setUsername("hr");
		crs.setPassword("hr");
		crs.setCommand("select * from users where username ='"+username+"'");
		crs.execute();
		System.out.println("entered login java");
		if(crs.next()){
	     p = crs.getString("password");
		}
		System.out.println(p);
		if(password.equals(p))
				{
			System.out.println("success");
			request.getSession().setAttribute("user", username);
			request.getSession().setAttribute("priority",crs.getString("priority"));
			response.sendRedirect("secured/slot.html");
			
}
		else{
			request.setAttribute("error", "Unknown Login Try Again!!");
			System.out.println("auth failed!!!");
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		}
		
catch(Exception ex){
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
