package by.epamtc.aladzyin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.aladzyin.bean.Order;
import by.epamtc.aladzyin.service.ClientService;
import by.epamtc.aladzyin.service.ServiceException;
import by.epamtc.aladzyin.service.ServiceProvider;

public class SaveOrderData implements Command {

	private static final String PARAMETER_ORDER_ID = "order_id";
	private static final String PARAMETER_STATUS = "order_status";
	private static final String USER_PAGE = "controller?command=go_to_user_page";
	private String order_id;
	private String status;
	private String page;
	boolean isChanged;
	private List<Order> orderList;
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("currentOrderId", order_id);

		order_id = request.getParameter(PARAMETER_ORDER_ID);
		status = request.getParameter(PARAMETER_STATUS);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();
		
		try {
			
			isChanged = service.updateOrder(order_id, status);
			
			if(isChanged) {
				orderList = (List<Order>) session.getAttribute("userOrderList");
				
				for( int i = 0; i < orderList.size(); i++) {
					if(orderList.get(i) != null) {
						if(orderList.get(i).getOrder_id().equals(order_id)) {
							orderList.get(i).setStatus(status);
							break;
						}
					}
				}
				
				session.setAttribute( "userOrderList", orderList);
				page = USER_PAGE;
			}else {
				page = USER_PAGE;
			}
					
		}catch (ServiceException e) {
			
			//log
			page = USER_PAGE;
			
		}
	
		response.sendRedirect(USER_PAGE);
	}

}
