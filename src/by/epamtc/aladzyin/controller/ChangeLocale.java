package by.epamtc.aladzyin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLocale implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String newLocale;
		HttpSession session;
		
		newLocale = request.getParameter("locale");
		
		session = request.getSession(true);
		session.setAttribute("locale", newLocale);
		
		String url = (String) request.getSession(false).getAttribute("prev_request");
		
		if(url == null) {
			url = "http://localhost:8081/delivery_dervice/controller?command=go_to_index";
		}
		
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
