package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class fetchUser
 */
public class fetchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fetchUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		ServletConfig config=getServletConfig();
		ServletContext context=getServletContext();
		
		//String dbUrl=config.getInitParameter("url");
		String dbUrl=context.getInitParameter("dburl");
		String dbUser=config.getInitParameter("dbuser");
		String dbPwd=config.getInitParameter("dbpwd");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(dbUrl,dbUser,dbPwd);
			System.out.println("Connected..");
			PreparedStatement ps=con.prepareStatement("select * from user");
			
			
			ResultSet rs = ps.executeQuery();;

            // Loop through the result set to 
            // retrieve the individual data items.
            while (rs.next()) {
               
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                
                String password = rs.getString("pass");
               

                out.println("<tr>" + "<td>" + fname + "</td>" + "<td>" + lname + "</td>" + 
                         "<td>" + password + "</td> </tr> <br>");
               

            }
            out.println("</table></body></html>");
			
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
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
