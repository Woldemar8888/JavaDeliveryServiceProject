package by.epamtc.aladzyin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut implements Command {

	private static final String ATTRIBUTE_USER = "user";

    private static final String MAIN_PAGE = "controller?command=go_to_main_page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	HttpSession session = request.getSession(false);
        
    	if (session != null) {
            session.removeAttribute(ATTRIBUTE_USER);
        }
        
        response.sendRedirect(MAIN_PAGE);
    }

}
