package by.epamtc.aladzyin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.aladzyin.bean.User;
import by.epamtc.aladzyin.service.ClientService;
import by.epamtc.aladzyin.service.ServiceException;
import by.epamtc.aladzyin.service.ServiceProvider;

public class DeleteProfile implements Command {
	
	private static final String MAIN_PAGE = "controller?command=go_to_main_page";
	private static final String USER_PAGE = "controller?command=go_to_user_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user;
		String page;
		boolean isDeleted;
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();
			
		try {
				
			HttpSession session = request.getSession();
				
			user = (User) session.getAttribute("user");
				
			isDeleted = service.deleteUser(user);
				
			if(isDeleted) {
				session.removeAttribute("user");
				session.removeAttribute("isDeleteUserMode");
				page = MAIN_PAGE;
			}else {
				page = USER_PAGE;
			}
				
			}catch (ServiceException e) {
				//log
				page = USER_PAGE;	
			}
			
			response.sendRedirect(page);
		}
}
