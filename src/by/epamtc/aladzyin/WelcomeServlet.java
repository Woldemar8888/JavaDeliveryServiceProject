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


public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WelcomeServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String login = request.getParameter("login").trim();
		String password = request.getParameter("password");
		String answer;
		String userName="";
		String dbLogin;
		String dbPassword;
		boolean isUserExist = false;
		
		
		try{
            String url = "jdbc:mysql://localhost/delivery_service?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String dbpassword = "password";
            
            
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, dbpassword) ){
            	
            	Statement statement = conn.createStatement();
            	ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            	while(resultSet.next()){
            		dbLogin = resultSet.getString("login");
            		if(login.equals(dbLogin)) {
            			dbPassword = resultSet.getString("password");
            			if(password.equals(dbPassword)) {
            				String dbName = resultSet.getString("name");
            				userName = dbName;
            				isUserExist = true;
            				break;
            			}
            		}  
            	}	
             }  
        } catch(Exception ex){
        	answer = "Connection failed..." + ex +	"<a href=\"/delivery_service/index.jsp\">TO LOGIN PAGE</a>";
        } finally {
        	if( isUserExist ) {
    			answer = "<h1>WELCOME PAGE</h1>"
    					+ "<h2>HELLO " + userName + "</h2>" 
    					+ 	"<a href=\"/delivery_service/index.jsp\">TO LOGIN PAGE</a>";
    		}else {
    			answer = "<h1>INCORRECT LOGIN OR PASSWORD</h1>"
    					+ 	"<a href=\"/delivery_service/index.jsp\">TO LOGIN PAGE</a>";
    		}
    		
    		out.println("<!DOCTYPE html>"
    				+ 		"<html>"
    				+ 		"<head>"
    				+ 			"<meta charset=\"UTF-8\">"
    				+ 			"<title>Служба доставки - главная</title>"
    				+ 	    	"<link rel=\"stylesheet\" href=\"style.css\"/>"
    				+ 		"</head>"
    				+ 		"<body>"
    				+			answer
    				+ 		"</body>"
    				+ 		"</html>");
        	
            out.close();
        }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
