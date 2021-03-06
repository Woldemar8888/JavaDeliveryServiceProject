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
	private static final String PARAMETER_USER = "user";
	
	private static final String MAIN_PAGE = "controller?command=go_to_main_page";
	private static final String LOGIN_PAGE = "controller?command=go_to_authorization_page";
	

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
			request.getSession().setAttribute(PARAMETER_USER, user);
			page = MAIN_PAGE;
			
			
		}catch(ServiceException e) {
			page =  LOGIN_PAGE;
			
		}
		
		response.sendRedirect(page);
	}

}
