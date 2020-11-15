package by.epamtc.aladzyin.dao;

import java.util.List;

import by.epamtc.aladzyin.bean.Order;
import by.epamtc.aladzyin.bean.Product;
import by.epamtc.aladzyin.bean.User;
import by.epamtc.aladzyin.dao.exception.DAOException;

public interface UserDAO {
	
	User authentification (String login, String password) throws DAOException;
	
	boolean registration(User user) throws DAOException;
	
	boolean updateUser(User user) throws DAOException;
	
	boolean deleteUser(User user) throws DAOException;
	
	List<Order> getOrderList(User user) throws DAOException;

	List<Product> getProductList(String order_id) throws DAOException;

	boolean updateOrder(String order_id, String status) throws DAOException;
}
