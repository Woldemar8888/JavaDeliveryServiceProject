package by.epamtc.aladzyin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToRegistrationPageCommand implements Command {
	
	private static final String REGISTRATION_PAGE = "/WEB-INF/jsp/register.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(REGISTRATION_PAGE);
		dispatcher.forward(request, response);
	}	
}