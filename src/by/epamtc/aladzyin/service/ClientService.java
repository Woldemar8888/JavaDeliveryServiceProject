package by.epamtc.aladzyin.service;

import by.epamtc.aladzyin.bean.User;

public interface ClientService {
	
	User authorization (String login, String password) throws ServiceException;
	
	boolean registation (User user)  throws ServiceException;
	
	boolean updateUser (User user)  throws ServiceException;
	
	boolean deleteUser (User user) throws ServiceException;
	
}
