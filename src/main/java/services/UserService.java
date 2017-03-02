package services;

import common.exceptions.UserDaoException;
import models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.jsp.jstl.core.Config;


/**
 * Created by bot on 23.02.17.
 */

public interface UserService {
    public  boolean authorize(String login, String password) throws UserDaoException;
    public boolean registration(String login, String password);

}
