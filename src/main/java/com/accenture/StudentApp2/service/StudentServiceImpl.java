package com.accenture.StudentApp2.service;

import com.accenture.StudentApp2.dao.GenericDaoImpl;
import com.accenture.StudentApp2.dao.separateDao.StudentDaoImpl;
import com.accenture.StudentApp2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class StudentServiceImpl implements Service<Student> {

    private GenericDaoImpl<Student> studentDao;

    @Autowired
    public void setDao(GenericDaoImpl<Student> daoToSet) {
        studentDao = daoToSet;
        studentDao.setClazz(Student.class);
    }

    @Override
    //@Cacheable("students")
    public List<Student> findAll() {
       // studentDao.setClazz(Student.class);
        return studentDao.findAll();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return studentDao.getById(id);
    }

    @Override
    @Transactional
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    @Transactional
    public void update(Student student) {
    studentDao.update(student);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
    studentDao.deleteById(id);
    }
}
