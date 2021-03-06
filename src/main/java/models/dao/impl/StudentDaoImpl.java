package models.dao.impl;

import common.exceptions.UserDaoException;
import models.connector.Connector;
import models.dao.StudentDao;
import models.pojo.Student;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by innopolis on 02.03.17.
 */
public class StudentDaoImpl implements StudentDao {

    private static final Logger logger = Logger.getLogger(StudentDao.class);

    private static final String SQL_GET_STUDENT = "SELECT * FROM  student WHERE id=?";
    private static final String SQL_ALL_USERS = "SELECT * FROM  \"student\" ORDER BY id";
    private static final String SQL_UPDATE = "UPDATE student SET name=?, age=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM student WHERE id=?";
    private static final String SQL_CREATE = "INSERT INTO student(\"name\", age) " +
            "VALUES(?,?)";
    private static String SQL_ALL_STUDENTS = "SELECT * FROM student";

    public List<Student> getAllStudents(){
        List<Student> studentsList = new ArrayList<>();
        try(Connection connection = Connector.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_ALL_STUDENTS);

            while(resultSet.next()) {
                logger.debug(resultSet.getString("name"));

                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );
                studentsList.add(student);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return studentsList;
    }


    public  List<Student> getList(){
        List<Student> list = new ArrayList<>();
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(
                        new Student(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("age")

                        )
                );
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(e);
            //throw new UserDaoException();
        }

        return list;
    }


    public Student getStudentById(int id) throws UserDaoException {
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_GET_STUDENT);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")

                );
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }

        return null;
    }

    public boolean update(int id, String name, int age)
            throws UserDaoException {
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setInt(3, id);
            int count = statement.executeUpdate();

            if (count > 0) {
                return true;
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }

        return false;
    }

    public boolean delete(int id) throws UserDaoException {
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, id);
            int count = statement.executeUpdate();

            if (count > 0) {
                return true;
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }

        return false;
    }

    public boolean create(String name, int age)
            throws UserDaoException {
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE);
            statement.setString(1, name);
            statement.setInt(2, age);

            int count = statement.executeUpdate();

            if (count > 0) {
                return true;
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }

        return false;
    }
}
