package by.epamtc.aladzyin;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloServlet() {
        super();   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("register-name");
		String surname = request.getParameter("register-surname");
		String phone = request.getParameter("register-phone");
		String login = request.getParameter("register-login");
		String password = request.getParameter("register-password");
		String answer;
		
		if( !login.equals("vasa") && !password.equals("parole") ) {
			answer = "<h1>WELCOME PAGE</h1>"
					+ "<h2>HELLO " + login + "</h2>";
		}else {
			answer = "<h1>INCORRECT</h1>";
		}
		
		out.println("<!DOCTYPE html>"
				+ 		"<html>"
				+ 		"<head>"
				+ 			"<meta charset=\"UTF-8\">"
				+ 			"<title>Delivery service</title>"
				+ 	    	"<link rel=\"stylesheet\" href=\"style.css\"/>"
				+ 		"</head>"
				+ 		"<body>"
				+ 	    	answer
				+ 		"</body>"
				+ 		"</html>");
		
		
		
		out.println(name);
		out.println(surname);
		out.println(phone);
		out.println(login);
		out.println(password);
		
		out.println("<a href=\"/delivery_service/index.jsp\">TO LOGIN PAGE</a>");
		
		
		try{
            String url = "jdbc:mysql://localhost/delivery_service?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String dbpassword = "password";
            
            
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, dbpassword) ){
            	
            	Statement statement = conn.createStatement();
            	
            	
            	 out.println("connection is ok");
            	 
            	
//            	statement.executeUpdate("insert into users(name, surname, phone, login, password) values ("
//            			+ 		name 
//            			+ "," + surname
//            			+ "," + phone
//            			+ "," + login
//            			+ "," + password
//            			+ ");"
//            			);
             }  
        }
        catch(Exception ex){
            out.println("Connection failed...");
            out.println(ex);
        }
        finally {
            out.close();
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
