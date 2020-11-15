package by.epamtc.aladzyin.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.epamtc.aladzyin.bean.Order;
import by.epamtc.aladzyin.bean.Product;
import by.epamtc.aladzyin.bean.User;
import by.epamtc.aladzyin.dao.UserDAO;
import by.epamtc.aladzyin.dao.ConnectionPool;
import by.epamtc.aladzyin.dao.exception.ConnectionPoolError;
import by.epamtc.aladzyin.dao.exception.DAOException;

public class SQLUserDAO implements UserDAO {
	
	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private static final String QUERY_CHECK_CREDENTIAL = "SELECT * FROM users WHERE login = ? and password = ?";
	private static final String QUERY_REGISTATION = "INSERT INTO users(name, surname, phone, login, password) VALUES ( ?, ?, ?, ?, ?);";
	private static final String QUERY_UPDATE_USER = "UPDATE users SET name = ?, surname = ?, phone = ?, password = ? WHERE login = ? ;";
	private static final String QUERY_DELETE_USER = "DELETE FROM users WHERE login = ? ;";
	private static final String QUERY_SHOW_USER_ORDERS = "SELECT * FROM orders WHERE sender_id = ?;";
	private static final String QUERY_SHOW_DRIVER_ORDERS = "SELECT * FROM orders WHERE executor_id = ?;";
	private static final String QUERY_SHOW_ADMIN_ORDERS = "SELECT * FROM orders ;";
	private static final String QUERY_SHOW_ORDER_GOODS = "SELECT * FROM goods WHERE order_id = ?;";
	private static final String QUERY_UPDATE_ORDER = "UPDATE orders SET status = ? WHERE order_id = ? ;";
	
	
	@Override
	public User authentification(String login, String password) throws DAOException {
		
		User user = null;
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		
        try {
        	connection = connectionPool.takeConnection();
        	preparedStatement = connection.prepareStatement(QUERY_CHECK_CREDENTIAL);
			preparedStatement.setString(1, login );
	        preparedStatement.setString(2, password );
        	resultSet = preparedStatement.executeQuery();
        	
        	if(resultSet.next()){
        		String dbLogin = resultSet.getString("login");
        		if(login.equals(dbLogin)) {
        			String dbPassword = resultSet.getString("password");
        			if(password.equals(dbPassword)) {
        				String name = resultSet.getString("name");
        				String surname = resultSet.getString("surname");
        				String phone = resultSet.getString("phone");
        				int user_id =  resultSet.getInt("user_id");
        				int role_id =  resultSet.getInt("role_id");
        				
        				user = new User(name, surname, phone, login, password );
        				user.setUser_id(user_id);
        				user.setRole_id(role_id);
        			}
        		}  
        	}
        	
        } catch ( ConnectionPoolError | SQLException e  ) {
        	throw new DAOException();
        } finally {
        	if (connection != null) {
                connectionPool.closeConnection(connection, preparedStatement, resultSet);
            }
        }
		
		return user;
	}
	
	
	@Override
	public boolean registration(User user) throws DAOException {
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
                
        try{
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(QUERY_REGISTATION);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
                   
            int rows = preparedStatement.executeUpdate();
            	
            if(rows == 1) {
            	return true;
            }
             
        } catch(  SQLException e) {
     		throw new DAOException(e);  
        } finally {
        	if (connection != null) {
                connectionPool.closeConnection(connection, preparedStatement);
            }
        }
		
		return false;
	}


	@Override
	public boolean updateUser(User user) throws DAOException {
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
		
        try {
        	connection = connectionPool.takeConnection();	
            preparedStatement = connection.prepareStatement(QUERY_UPDATE_USER);
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
        } finally {
        	if (connection != null) {
                connectionPool.closeConnection(connection, preparedStatement);
            }
        }	
        
		return false;
	}
	
	@Override
	public boolean deleteUser(User user) throws DAOException {
			
		Connection connection = null;
        PreparedStatement preparedStatement = null;
            
        try {
        	connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(QUERY_DELETE_USER);
                
            preparedStatement.setString(1, user.getLogin());
               
            int rows = preparedStatement.executeUpdate();
                
            if(rows == 1) {
            		return true;
            }
        } catch(SQLException e) {
     		throw new DAOException(e);  
        } finally {
        	if (connection != null) {
                connectionPool.closeConnection(connection, preparedStatement);
            }
        }	
		
		return false;
	}

	@Override
	public List<Order> getOrderList(User user) throws DAOException {
		
		int user_role_id;
		Order order;
		List<Order> orderList = new ArrayList<>();
		
		String order_id;
    	String status;
    	String sender_id;
    	String executor_id;
    	Date date_in; 
    	String town;
    	String route_id;
    	
    	
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
        	
        	connection = connectionPool.takeConnection();
        	
        	user_role_id = user.getRole_id();
        	
        	if(user_role_id == 1) {
        		preparedStatement = connection.prepareStatement(QUERY_SHOW_USER_ORDERS);
                
                preparedStatement.setInt(1, user.getUser_id());
                
                resultSet = preparedStatement.executeQuery();
        	}else if(user_role_id == 2) {
        		
        		preparedStatement = connection.prepareStatement(QUERY_SHOW_DRIVER_ORDERS);
                preparedStatement.setInt(1, user.getUser_id() );
                resultSet = preparedStatement.executeQuery();
        		
        	}else if(user_role_id == 3 || user_role_id == 4) {
        		preparedStatement = connection.prepareStatement(QUERY_SHOW_ADMIN_ORDERS);
//                status = "не подтвержден";
//                preparedStatement.setString(1, status );
                resultSet = preparedStatement.executeQuery();
        	}
 
            while(resultSet.next()) {
            		 
                 order_id = Integer.toString(resultSet.getInt("order_id"));
                 status = resultSet.getString("status");
                 
                 sender_id = Integer.toString(resultSet.getInt("sender_id"));
                 executor_id = Integer.toString(resultSet.getInt("executor_id"));
                 date_in = resultSet.getDate("date_in");
                 town = resultSet.getString("town");
                 route_id = Integer.toString(resultSet.getInt("route_id"));
                 	
                 order = new Order();
                 	
                 order.setOrder_id(order_id);
                 order.setStatus(status);
                 order.setSender_id(sender_id);
                 order.setExecutor_id(executor_id);
                 order.setDate_in(date_in);
                 order.setTown(town);
                 order.setRoute_id(route_id);
                 
                 	
                 orderList.add(order);
            }
            	
            return orderList;
            
        } catch(SQLException e) {
        	throw new DAOException(e);
        } finally {
        	if (connection != null) {
                connectionPool.closeConnection(connection, preparedStatement, resultSet);
            }
        }
	}


	@Override
	public List<Product> getProductList(String order_id) throws DAOException {
		
		Product product;
		List<Product> productList = new ArrayList<>();
		
		String name;
		int count;
		double weight;
		double volume;
    	
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
        	
        	connection = connectionPool.takeConnection();
        	
            preparedStatement = connection.prepareStatement(QUERY_SHOW_ORDER_GOODS);
                
            preparedStatement.setInt(1, Integer.parseInt(order_id));
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
            		 
            	name = resultSet.getString("name");
        		count = resultSet.getInt("count");
        		weight = resultSet.getDouble("weight");
        		volume =  resultSet.getDouble("volume");
                 	
                 product = new Product(name, count, weight, volume);
                 productList.add(product);
            }
            	
            return productList;
            
        } catch(SQLException e) {
        	throw new DAOException(e);
        } finally {
        	if (connection != null) {
                connectionPool.closeConnection(connection, preparedStatement, resultSet);
            }
        }
	}


	@Override
	public boolean updateOrder(String order_id, String status) throws DAOException {
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
		
        try {
        	connection = connectionPool.takeConnection();	
            preparedStatement = connection.prepareStatement(QUERY_UPDATE_ORDER);
            preparedStatement.setString(1, status );
            preparedStatement.setInt(2, Integer.parseInt(order_id));
              
            int rows = preparedStatement.executeUpdate();
                
            if(rows == 1) {
            	return true;
            }
        } catch(SQLException e)   {
     		throw new DAOException(e);  
        } finally {
        	if (connection != null) {
                connectionPool.closeConnection(connection, preparedStatement);
            }
        }	
        
		return false;
	}
}
