package by.epamtc.aladzyin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLocale implements Command {
	private static final String PARAMETER_LOCAL = "local";

    private static final String ATTRIBUTE_PREVIOUS_REQUEST = "previous_request";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String local = request.getParameter(PARAMETER_LOCAL);
        String previousRequest = (String) session.getAttribute(ATTRIBUTE_PREVIOUS_REQUEST);
        session.setAttribute(PARAMETER_LOCAL, local);

       
        response.sendRedirect(previousRequest); 		
    }
	
}
