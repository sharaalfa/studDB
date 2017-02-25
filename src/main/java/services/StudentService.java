package services;

import common.exceptions.UserDaoException;
import models.dao.StudentDao;
import models.pojo.Student;

import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
public class StudentService {

    public static List<Student> getAllStudents(){
        return StudentDao.getAllStudents();
    }


    public static Student getStudentById(int id) throws UserDaoException {
        return StudentDao.getStudentById(id);
    }

    public static boolean update(int id, String name, int age)
            throws UserDaoException {
        return StudentDao.update(id, name, age);
    }

    public static boolean delete(int id) throws UserDaoException {
        return StudentDao.delete(id);
    }

    public static boolean create(String name, int age)
            throws UserDaoException {
        return StudentDao.create(name, age);
    }


}
