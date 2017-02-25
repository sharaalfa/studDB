package controllers;

import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bot on 23.02.17.
 */
public class RegistrationServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("REG on post");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(UserService.registration(login, password)){
            logger.trace("true");
            resp.sendRedirect("/students/login");
        }else{
            logger.trace("false");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
