package by.epamtc.aladzyin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HideUserOrders implements Command {

	private static final String USER_PAGE = "controller?command=go_to_user_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("isShowOrdersMode");
		session.removeAttribute("isShowOrderDelailsMode");
		
		response.sendRedirect(USER_PAGE);
	}
}
