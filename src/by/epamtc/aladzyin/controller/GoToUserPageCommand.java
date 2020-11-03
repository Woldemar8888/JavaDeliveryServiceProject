package by.epamtc.aladzyin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GoToUserPageCommand implements Command {

	private static final String USER_PAGE = "/WEB-INF/jsp/userPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(USER_PAGE);
		dispatcher.forward(request, response);
	}

}
