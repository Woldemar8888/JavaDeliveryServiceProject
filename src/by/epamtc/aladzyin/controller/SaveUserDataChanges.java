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

public class SaveUserDataChanges implements Command {
	
	private static final String PARAMETER_NAME = "register-name";
	private static final String PARAMETER_SURNAME = "register-surname";
	private static final String PARAMETER_PHONE = "register-phone";
	private static final String PARAMETER_PASSWORD = "register-password";
	
	private static final String USER_PAGE = "controller?command=go_to_user_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login;
		String password;
		String phone;
		String name;
		String surname;
		String page;
		User oldUser;
		boolean isChanged;
		
		oldUser = (User) request.getSession().getAttribute("user");
		
		login = oldUser.getLogin();
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
			isChanged = service.updateUser(user);
			
			HttpSession session = request.getSession();
			session.setAttribute("isEditMode", false);
			
			if(isChanged) {
				request.getSession().setAttribute("user", user);
				page = USER_PAGE;
			}else {
				request.setAttribute("error", "something bad with your login");
				page = USER_PAGE;
			}
			
		}catch (ServiceException e) {
			request.setAttribute("error", "Error. Try again");
			//log
			page = USER_PAGE;
			
		}
		
		response.sendRedirect(page);
	}
	
}	
