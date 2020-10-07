package by.epamtc.aladzyin;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterController() {
        super();   
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String formName = request.getParameter("register-name").trim();
		String formSurname = request.getParameter("register-surname").trim();
		String formPhone = request.getParameter("register-phone").trim();
		String formLogin = request.getParameter("register-login").trim();
		String formPassword = request.getParameter("register-password").trim();
				
		try{
            String url = "jdbc:mysql://localhost/delivery_service?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String dbpassword = "password";
            
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, dbpassword) ){
            	String query = "INSERT INTO users(name, surname, phone, login, password) VALUES ( ?, ?, ? ,?, ?)";
            	PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, formName);
                preparedStatement.setString(2, formSurname);
                preparedStatement.setString(3, formPhone);
                preparedStatement.setString(4, formLogin);
                preparedStatement.setString(5, formPassword);
               
                int rows = preparedStatement.executeUpdate();
            	
            	if(rows == 1) {
            		out.println("saved successfully");
            	}
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
}
