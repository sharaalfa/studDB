package services.impl;

import common.exceptions.UserDaoException;
import models.dao.StudentDao;
import models.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import services.StudentService;

import java.util.List;

/**
 * Created by innopolis on 02.03.17.
 */
@Repository
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    public List<Student> getAllStudents(){
        return studentDao.getAllStudents();
    }


    public Student getStudentById(int id) throws UserDaoException {
        return studentDao.getStudentById(id);
    }

    public boolean update(int id, String name, int age)
            throws UserDaoException {
        return studentDao.update(id, name, age);
    }

    public boolean delete(int id) throws UserDaoException {
        return studentDao.delete(id);
    }

    public  boolean create(String name, int age)
            throws UserDaoException {
        return studentDao.create(name, age);
    }

}
