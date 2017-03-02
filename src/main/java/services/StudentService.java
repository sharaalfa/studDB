package services;

import common.exceptions.UserDaoException;
import models.dao.StudentDao;
import models.pojo.Student;

import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(int id) throws UserDaoException;
    boolean update(int id, String name, int age) throws UserDaoException;
    boolean delete(int id) throws UserDaoException;
    boolean create(String name, int age) throws UserDaoException;



}
