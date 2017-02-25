package controllers;

import common.exceptions.UserDaoException;
import models.pojo.Student;
import org.apache.log4j.Logger;
import services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitrii on 23.02.17.
 */
public class EditServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(EditServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        logger.trace("редактирование" + id);
        Student student = null;
        try {
            student = StudentService.getStudentById(id);
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/error.jsp");
        }
        req.setAttribute("student", student);
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Integer age = Integer.parseInt(req.getParameter("age"));
        try {
            if (StudentService.update(id, name, age)) {
                resp.sendRedirect("/students/list"); //edit/?id="+id);
            } else {
                resp.sendRedirect("/error.jsp");
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/error.jsp");
        }


    }
}
