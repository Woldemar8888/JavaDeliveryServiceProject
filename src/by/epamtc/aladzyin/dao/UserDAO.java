package by.epamtc.aladzyin.dao;

import by.epamtc.aladzyin.bean.User;

public interface UserDAO {
	
	User authentification (String login, String password) throws DAOException;
	
	boolean registration(User user) throws DAOException;
}
