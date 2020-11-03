package by.epamtc.aladzyin.dao;

import by.epamtc.aladzyin.bean.User;

public interface UserDAO {
	
	User authentification (String login, String password) throws DAOException;
	
	boolean registration(User user) throws DAOException;
	
	boolean updateUser(User user) throws DAOException;
	
	boolean deleteUser(User user) throws DAOException;
}
