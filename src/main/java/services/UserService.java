package services;

import common.exceptions.UserDaoException;
import models.dao.UserDao;

/**
 * Created by bot on 23.02.17.
 */
public class UserService {
    public static boolean authorize(String login, String password) throws UserDaoException {
        if(UserDao.getUserByLoginAndPassword(login, password) != null){
            return true;
        }
        return false;
    }

    public static boolean registration(String login, String password){
        return UserDao.registrationUser(login, password);
    }
}
