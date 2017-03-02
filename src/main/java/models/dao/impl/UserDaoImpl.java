package models.dao.impl;

import common.exceptions.UserDaoException;
import models.connector.Connector;
import models.dao.UserDao;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by innopolis on 02.03.17.
 */

public class UserDaoImpl implements UserDao {
    //public UserDaoImpl() {
      //  super(User.class);
    //}
    private static Logger logger = Logger.getLogger(UserDao.class);
    private static final String SQL_FIND_USER =
            "SELECT * FROM \"user\" WHERE login=? AND password=?;";
    private static final String SQL_CREATE_USER =
            "INSERT INTO \"user\" (login, password, role) " +
                    "VALUES(?, ?, ?)";

    public  User getUserByLoginAndPassword(String login, String password) throws UserDaoException {

        logger.debug(login + " " + password);
        User user = null;
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find");
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }else{
                logger.debug(login + " " + password + " not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return user;
    }

    public  boolean registrationUser(String login, String password){
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, "user");
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(login + " " + password + " not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }
}
