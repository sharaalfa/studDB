package controllers;

import common.exceptions.UserDaoException;
import org.apache.log4j.Logger;
import services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by innopolis on 23.02.17.
 */
public class AddServlet extends HttpServlet {

    static Logger logger = Logger.getLogger(AddServlet.class);

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
            if (StudentService.create(name, age)) {
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
