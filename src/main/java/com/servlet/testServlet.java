package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testServlet
 */
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
    	
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		ServletContext context=getServletContext();
		
		
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String password=request.getParameter("password");
		
		ServletConfig config=getServletConfig();
		//String dbUrl=config.getInitParameter("url");
		String dbUrl=context.getInitParameter("dburl");
		String dbUser=config.getInitParameter("dbuser");
		String dbPwd=config.getInitParameter("dbpwd");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(dbUrl,dbUser,dbPwd);
			System.out.println("Connected..");
			PreparedStatement ps=con.prepareStatement("insert into user values(?,?,?)");
			ps.setString(1,fname);
			ps.setString(2, lname);
			ps.setString(3, password);
			ps.executeUpdate();
			
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
				
				
		
//		String city=request.getQueryString();
//		String kv[]=city.split("&");
//		String place=request.getQueryString();
//		out.println("Hello"+ kv[0]+kv[1]);
		out.println("<h1> Test servlet started " +fname+"</h1>");
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		for(int i=1;i<=5;i++) {
			out.println("<h"+i+">"+ "Hello from servlet!</h"+i+">");
		}
		out.println("<h3>It's "+new java.util.Date()+" @server..</h3>");
		
	}

}
