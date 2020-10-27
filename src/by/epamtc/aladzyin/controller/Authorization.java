package by.epamtc.aladzyin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.aladzyin.bean.User;
import by.epamtc.aladzyin.service.ClientService;
import by.epamtc.aladzyin.service.ServiceException;
import by.epamtc.aladzyin.service.ServiceProvider;

public class Authorization implements Command {
	
	private static final String PARAMETER_LOGIN = "login";
	private static final String PARAMETER_PASSWORD = "password";
	
	private static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
	private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String login;
		String password;
		String page;
		User user;
		
		login = request.getParameter(PARAMETER_LOGIN);
		password = request.getParameter(PARAMETER_PASSWORD);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();
		
		try {
			
			
			user = service.authorization(login, password);
			page = MAIN_PAGE;
			
			
		}catch(ServiceException e) {
			page =  LOGIN_PAGE;
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
