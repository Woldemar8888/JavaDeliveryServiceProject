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

public class Registration implements Command {

	private static final String PARAMETER_NAME = "register-name";
	private static final String PARAMETER_SURNAME = "register-surname";
	private static final String PARAMETER_PHONE = "register-phone";
	private static final String PARAMETER_LOGIN = "register-login";
	private static final String PARAMETER_PASSWORD = "register-password";
	
	private static final String REGISTRATION_PAGE = "/WEB-INF/jsp/register.jsp";
	private static final String USER_PAGE = "/WEB-INF/jsp/userPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login;
		String password;
		String phone;
		String name;
		String surname;
		String page;
		boolean isRegistred;
		
		login = request.getParameter(PARAMETER_LOGIN);
		password = request.getParameter(PARAMETER_PASSWORD);
		phone = request.getParameter(PARAMETER_PHONE);
		name = request.getParameter(PARAMETER_NAME);
		surname = request.getParameter(PARAMETER_SURNAME);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();
		
		User user = new User();
		
		user.setLogin(login);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		user.setSurname(surname);
		
		try {
			isRegistred = service.registation(user);
			
			if(isRegistred) {
				request.setAttribute("user", user);
				page = USER_PAGE;
			}else {
				request.setAttribute("error", "something bad with your login");
				page = REGISTRATION_PAGE;
			}
			
		}catch (ServiceException e) {
			request.setAttribute("error", "Error. Try again");
			//log
			page = REGISTRATION_PAGE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	
		
	}

}
