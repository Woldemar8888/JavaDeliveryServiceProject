package by.epamtc.aladzyin.service;

import java.util.List;

import by.epamtc.aladzyin.bean.Order;
import by.epamtc.aladzyin.bean.Product;
import by.epamtc.aladzyin.bean.User;
import by.epamtc.aladzyin.dao.DAOProvider;
import by.epamtc.aladzyin.dao.UserDAO;
import by.epamtc.aladzyin.dao.exception.DAOException;
import by.epamtc.aladzyin.service.validation.CredentionalValidator;

public class ClientServiceImpl implements ClientService {

	@Override
	public User authorization(String login, String password) throws ServiceException {
		
		if(!CredentionalValidator.isCorrect(login, password)) {
			throw new ServiceException("incorrect login or password");
		}
	
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
		User user = null;
		
		try {
			user = userDAO.authentification(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		
		return user;
	}

	@Override
	public boolean registation(User user) throws ServiceException {
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
	
		try {
			return userDAO.registration(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateUser(User user) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
		try {
			return userDAO.updateUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public boolean deleteUser(User user) throws ServiceException {
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
		try {
			return userDAO.deleteUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Order> getOrderList(User user) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
		try {
			return userDAO.getOrderList(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> getProductList(String order_id) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
		try {
			return userDAO.getProductList(order_id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateOrder(String order_id, String status) throws ServiceException  {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
		try {
			return userDAO.updateOrder(order_id, status);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
