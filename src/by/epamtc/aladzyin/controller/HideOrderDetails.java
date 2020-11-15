package by.epamtc.aladzyin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.aladzyin.bean.Product;
import by.epamtc.aladzyin.service.ClientService;
import by.epamtc.aladzyin.service.ServiceProvider;

public class HideOrderDetails implements Command {

	private static final String USER_PAGE = "controller?command=go_to_user_page";
	String closed_order_id;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page;
		
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("isShowOrderDelailsMode");
			page = USER_PAGE;
					
		}catch (Exception e) {
			//log
			page = USER_PAGE;
			
		}
		
		response.sendRedirect(page);
	}
}
