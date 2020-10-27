package by.epamtc.aladzyin.service;

import by.epamtc.aladzyin.bean.User;
import by.epamtc.aladzyin.dao.DAOException;
import by.epamtc.aladzyin.dao.DAOProvider;
import by.epamtc.aladzyin.dao.UserDAO;
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
}
