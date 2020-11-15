package by.epamtc.aladzyin.service;

import by.epamtc.aladzyin.bean.User;

import java.util.List;

import by.epamtc.aladzyin.bean.Order;
import by.epamtc.aladzyin.bean.Product;

public interface ClientService {
	
	User authorization (String login, String password) throws ServiceException;
	
	boolean registation (User user)  throws ServiceException;
	
	boolean updateUser (User user)  throws ServiceException;
	
	boolean deleteUser (User user) throws ServiceException;
	
	List<Order>  getOrderList(User user) throws ServiceException;
	
	List<Product>  getProductList(String order_id) throws ServiceException;

	boolean updateOrder(String order_id, String status) throws ServiceException;
	
}
