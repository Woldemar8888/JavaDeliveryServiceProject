package by.epamtc.aladzyin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.aladzyin.bean.User;
import by.epamtc.aladzyin.service.ClientService;
import by.epamtc.aladzyin.service.ServiceException;
import by.epamtc.aladzyin.service.ServiceProvider;

public class GoToAuthorizationPageCommand implements Command {
	
	private static final String AUTHORIZATION_PAGE = "/WEB-INF/jsp/login.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(AUTHORIZATION_PAGE);
		dispatcher.forward(request, response);
	}
	
//	private static final String PARAMETER_LOGIN = "login";
//	private static final String PARAMETER_PASSWORD = "password";
//	
//	private static final String INDEX_PAGE = "/index.jsp";
//	private static final String AUTHORIZATION_PAGE = "/WEB-INF/jsp/login.jsp";
//
//	@Override
//	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		String login;
//		String password;
//		String page ="";
//		
//		login = request.getParameter(PARAMETER_LOGIN);
//		password = request.getParameter(PARAMETER_PASSWORD);
//		
//		ServiceProvider provider = ServiceProvider.getInstance();
//		ClientService service = provider.getClientService();
//		
//		User user = null;
//		
//		HttpSession session = request.getSession(true);
//		String loc = (String) session.getAttribute("local");
		
//		try {
//			user = service.authorization(login, password);
//			
//			if(user == null) {
//				request.setAttribute("error", "login or password error");
//				page = INDEX_PAGE;
//			}
//			
//		} catch (ServiceException e) {
//			request.setAttribute("error", "login or password error");
//			page = INDEX_PAGE;
//			//log
//		}
		
		
		
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher(AUTHORIZATION_PAGE);
//		dispatcher.forward(request, response);
		
		
//	}

}
