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


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String answer;
		String userName="";
		
		
		try{
            String url = "jdbc:mysql://localhost/delivery_service?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String dbpassword = "password";
            
            
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, dbpassword) ){
            	
            	Statement statement = conn.createStatement();
            	ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            	while(resultSet.next()){
            		String name = resultSet.getString("name");              
            	    userName = name;
            	    
            	}	
             }  
        } catch(Exception ex){
        	answer = "Connection failed..." + ex +	"<a href=\"/delivery_service/index.jsp\">TO LOGIN PAGE</a>";
        } finally {
        	if( login.equals("boss") && password.equals("boss") ) {
    			answer = "<h1>WELCOME PAGE</h1>"
    					+ "<h2>HELLO " + userName + "</h2>" 
    					+ 	"<a href=\"/delivery_service/index.jsp\">TO LOGIN PAGE</a>";
    		}else {
    			answer = "<h1>INCORRECT</h1>"
    					+ 	"<a href=\"/delivery_service/index.jsp\">TO LOGIN PAGE</a>";
    		}
    		
    		out.println("<!DOCTYPE html>"
    				+ 		"<html>"
    				+ 		"<head>"
    				+ 			"<meta charset=\"UTF-8\">"
    				+ 			"<title>Delivery service</title>"
    				+ 	    	"<link rel=\"stylesheet\" href=\"style.css\"/>"
    				+ 		"</head>"
    				+ 		"<body>"
    				+		answer
    				+ 		"</body>"
    				+ 		"</html>");
        	
            out.close();
        }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
