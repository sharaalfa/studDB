package controllers.Impl;

import common.exceptions.UserDaoException;
import controllers.EditServlet;
import models.pojo.Student;
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
public class EditServletImpl extends HttpServlet implements EditServlet {
    Logger logger = Logger.getLogger(EditServlet.class);
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
        Integer id = Integer.parseInt(req.getParameter("id"));
        logger.trace("редактирование" + id);
        Student student = null;
        try {
            student = studentService.getStudentById(id);
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
            if (studentService.update(id, name, age)) {
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
