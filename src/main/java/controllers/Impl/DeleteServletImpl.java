package controllers.Impl;

import common.exceptions.UserDaoException;
import controllers.DeleteServlet;
import controllers.EditServlet;
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
public class DeleteServletImpl extends HttpServlet implements DeleteServlet {
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
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            if (studentService.delete(id)) {
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
