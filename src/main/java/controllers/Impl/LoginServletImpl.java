package controllers.Impl;

import common.exceptions.UserDaoException;
import controllers.LoginServlet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import services.UserService;

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
public class LoginServletImpl extends HttpServlet implements LoginServlet{
    @Autowired
    UserService userService;
    private WebApplicationContext webApplicationContext;

    Logger logger = Logger.getLogger(LoginServlet.class);
    public void init(ServletConfig config) throws ServletException {
        webApplicationContext = WebApplicationContextUtils
                .getWebApplicationContext(config.getServletContext());
        userService=(UserService) webApplicationContext.getBean("userService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("onget");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on post");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            if(userService.authorize(login, password)){
                logger.trace("true");
                resp.sendRedirect("/students/list");
            }else{
                logger.trace("false");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
