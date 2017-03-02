package controllers.Impl;

import common.exceptions.UserDaoException;
import controllers.AddServlet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import services.StudentService;

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
public class AddServletImpl extends HttpServlet implements AddServlet {
    Logger logger = Logger.getLogger(AddServlet.class);
    private WebApplicationContext webApplicationContext;
    @Autowired
    StudentService studentService;
    public void init(ServletConfig config) throws ServletException {
        webApplicationContext = WebApplicationContextUtils
                .getWebApplicationContext(config.getServletContext());
        studentService = (StudentService) webApplicationContext.getBean("studentService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("add post");
        String name = req.getParameter("name");
        Integer age = Integer.parseInt(req.getParameter("age"));


        try {
            if (studentService.create(name, age)) {
                resp.sendRedirect("/students/list");
            } else {
                resp.sendRedirect("/error.jsp");
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/error.jsp");
        }
    }
}
