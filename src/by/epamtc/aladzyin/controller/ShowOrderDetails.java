package by.epamtc.aladzyin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.aladzyin.bean.Order;
import by.epamtc.aladzyin.bean.Product;
import by.epamtc.aladzyin.service.ClientService;
import by.epamtc.aladzyin.service.ServiceException;
import by.epamtc.aladzyin.service.ServiceProvider;

public class ShowOrderDetails implements Command {

	private static final String USER_PAGE = "controller?command=go_to_user_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page;
		String order_id;
		List<Product> productList;
				
		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();
		
		try {
			
			HttpSession session = request.getSession();
			
			order_id = request.getParameter("order_id");  
			
			session.setAttribute("isShowOrderDelailsMode", true);  
			session.setAttribute("currentOrderId", order_id);
			
			productList = service.getProductList(order_id);
			
			if(productList.size() > 0) {
				session.setAttribute( "userOrderProductList" , productList );
				page = USER_PAGE;
			}else {
				page = USER_PAGE;
			}
				
		}catch (Exception e) {
			//log
			page = USER_PAGE;
			
		}
		
		response.sendRedirect(page);
	}

}
