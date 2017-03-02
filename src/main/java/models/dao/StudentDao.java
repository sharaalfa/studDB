package models.dao;

import common.exceptions.UserDaoException;
import models.connector.Connector;
import models.pojo.Student;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
public interface StudentDao {
    List<Student> getAllStudents();
    List<Student> getList();
    Student getStudentById(int id) throws UserDaoException;
    boolean update(int id, String name, int age) throws UserDaoException;
    boolean delete(int id) throws UserDaoException;
    boolean create(String name, int age) throws UserDaoException;



}
