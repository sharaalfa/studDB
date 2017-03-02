package controllers.Impl;

import controllers.RegistrationServlet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import services.UserService;

import javax.servlet.Registration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by innopolis on 02.03.17.
 */
@Service
public class RegistrationServletImpl extends HttpServlet implements RegistrationServlet {
    @Autowired
    UserService userService;
    private WebApplicationContext webApplicationContext;
    public void init(ServletConfig config) throws ServletException {
        webApplicationContext = WebApplicationContextUtils
                .getWebApplicationContext(config.getServletContext());
        userService = (UserService) webApplicationContext.getBean("studentService");
    }

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
        if(userService.registration(login, password)){
            logger.trace("true");
            resp.sendRedirect("/students/login");
        }else{
            logger.trace("false");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
