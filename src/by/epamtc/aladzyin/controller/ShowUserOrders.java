package by.epamtc.aladzyin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import by.epamtc.aladzyin.bean.User;
import by.epamtc.aladzyin.bean.Order;
import by.epamtc.aladzyin.service.ClientService;
import by.epamtc.aladzyin.service.ServiceException;
import by.epamtc.aladzyin.service.ServiceProvider;

public class ShowUserOrders implements Command {

	private static final String USER_PAGE = "controller?command=go_to_user_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login;
		String page;
		User user;
		List<Order> orderList;
				
		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();
		
		try {
			HttpSession session = request.getSession();
			user = (User) session.getAttribute("user");
			
			orderList = service.getOrderList(user);
			
			if(orderList.size() > 0) {
				
				session.setAttribute("isShowOrdersMode", true);
				session.setAttribute( "userOrderList" , orderList);
				page = USER_PAGE;
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
