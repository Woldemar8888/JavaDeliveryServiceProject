package by.epamtc.aladzyin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.aladzyin.bean.User;

public class SQLUserDAO implements UserDAO {
	
	private static final String QUERY_CHECK_CREDENTIAL = "SELECT * FROM users WHERE login = ? and password = ?";
	private static final String QUERY_REGISTATION = "INSERT INTO users(name, surname, phone, login, password) VALUES ( ?, ?, ?, ?, ?);";
	private static final String QUERY_UPDATE_USER = "UPDATE users SET name = ?, surname = ?, phone = ?, password = ? WHERE login = ? ;";
	private static final String QUERY_DELETE_USER = "DELETE FROM users WHERE login = ? ;";
	private static final String URL = "jdbc:mysql://localhost/delivery_service?serverTimezone=Europe/Moscow&useSSL=false";
	private static final String USERNAME = "root";
	private static final String DBPASSWORD = "password";
	
	
	@Override
	public User authentification(String login, String password) throws DAOException {
		
		User user = null;
		
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try(Connection conn = DriverManager.getConnection(URL, USERNAME, DBPASSWORD)){
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_CHECK_CREDENTIAL);
				preparedStatement.setString(1, login );
		        preparedStatement.setString(2, password );
            	ResultSet resultSet = preparedStatement.executeQuery();
            	
            	if(resultSet.next()){
            		String dbLogin = resultSet.getString("login");
            		if(login.equals(dbLogin)) {
            			String dbPassword = resultSet.getString("password");
            			if(password.equals(dbPassword)) {
            				String name = resultSet.getString("name");
            				String surname = resultSet.getString("surname");
            				String phone = resultSet.getString("phone");
            				
            				user = new User(name, surname, phone, login, password );
            			}
            		}  
            	} 	
			} catch(SQLException e) {
				throw new DAOException(e);		
			} 	
		
		}catch(ClassNotFoundException e) {
			throw new DAOException(e); 
		}
		
		return user;
	}
	
	
	@Override
	public boolean registration(User user) throws DAOException {
		
		try{ 
                     
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, DBPASSWORD) ){
            	
            	PreparedStatement preparedStatement = conn.prepareStatement(QUERY_REGISTATION);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getPhone());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getLogin());
                
               
                int rows = preparedStatement.executeUpdate();
            	
            	if(rows == 1) {
            		return true;
            	}
             } catch(SQLException e) {
     			throw new DAOException(e);  
             } 	
		
		} catch(ClassNotFoundException e) {
 			throw new DAOException(e);  
        } 
		
		return false;
	}


	@Override
	public boolean updateUser(User user) throws DAOException {
		try{ 
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, DBPASSWORD) ){
            	
            	PreparedStatement preparedStatement = conn.prepareStatement(QUERY_UPDATE_USER);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getPhone());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getLogin());
               
                int rows = preparedStatement.executeUpdate();
                
            	if(rows == 1) {
            		return true;
            	}
             } catch(SQLException e) {
     			throw new DAOException(e);  
             } 	
		
		} catch(ClassNotFoundException e) {
 			throw new DAOException(e);  
        } 
		
		return false;
	}
	
	@Override
	public boolean deleteUser(User user) throws DAOException {
		try{ 
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, DBPASSWORD) ){
            	
            	PreparedStatement preparedStatement = conn.prepareStatement(QUERY_DELETE_USER);
                
                preparedStatement.setString(1, user.getLogin());
               
                int rows = preparedStatement.executeUpdate();
                
            	if(rows == 1) {
            		return true;
            	}
             } catch(SQLException e) {
     			throw new DAOException(e);  
             } 	
		
		} catch(ClassNotFoundException e) {
 			throw new DAOException(e);  
        } 
		
		return false;
	}
}
