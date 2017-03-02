package models.dao;

import common.exceptions.UserDaoException;
import models.connector.Connector;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bot on 23.02.17.
 */
public interface UserDao {

    User getUserByLoginAndPassword(String login, String password) throws UserDaoException;
    boolean registrationUser(String login, String password);

}
