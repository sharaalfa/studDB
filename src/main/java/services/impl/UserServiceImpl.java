package services.impl;

import common.exceptions.UserDaoException;
import models.dao.UserDao;
import models.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;
import services.UserService;

/**
 * Created by innopolis on 02.03.17.
 */
@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public  boolean authorize(String login, String password) throws UserDaoException {
        if(userDao.getUserByLoginAndPassword(login, password) != null){
            return true;
        }
        return false;
    }

    public boolean registration(String login, String password) {
        return userDao.registrationUser(login, password);
    }
}
