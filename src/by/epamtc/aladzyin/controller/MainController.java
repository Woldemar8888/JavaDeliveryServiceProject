package by.epamtc.aladzyin.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.aladzyin.service.ServiceException;

public class MainController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String CONTROLLER = "controller";
	
	private static final String PARAMETER_COMMAND = "command";
	
	private static final String PARAMETER_LOCAL = "local";
	
	private static final String PREVIOUS_REQUEST = "previous_request";
	
	
	
	private final CommandProvider provider = new CommandProvider();
       
    public MainController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute(PARAMETER_LOCAL) == null) {
            session.setAttribute(PARAMETER_LOCAL, Locale.getDefault());
        }
		
		
		
		String commandName = request.getParameter(PARAMETER_COMMAND);
		Command command = provider.getCommand(commandName);
		
		if(command != null) {
			command.execute(request, response);
		}
		
		String previousRequest = CONTROLLER + "?" + request.getQueryString();
        session.setAttribute(PREVIOUS_REQUEST, previousRequest);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
