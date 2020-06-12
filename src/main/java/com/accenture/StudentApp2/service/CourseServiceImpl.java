package com.accenture.StudentApp2.service;

import com.accenture.StudentApp2.dao.GenericDaoImpl;
import com.accenture.StudentApp2.dao.separateDao.CourseDaoImpl;
import com.accenture.StudentApp2.model.Course;
import com.accenture.StudentApp2.model.Student;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements com.accenture.StudentApp2.service.Service<Course> {

    private GenericDaoImpl<Course> courseDao;

    @Override
    @Autowired
    public void setDao(GenericDaoImpl<Course> genericDao) {
        courseDao = genericDao;
        courseDao.setClazz(Course.class);
    }


    @Override
    //@Cacheable("courses")
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public Optional<Course> getById(Long id) {
        return courseDao.getById(id);
    }

    @Override
    @Transactional
    public void save(Course course) {
        courseDao.save(course);
    }

    @Override
    @Transactional
    public void update(Course course) {
        courseDao.update(course);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Course course = courseDao.getById(id).get();
        course.getStudents().forEach(student -> student.getCourses().remove(course));

        courseDao.deleteById(id);
    }


    public List<Course> getListByTitle(String title) {
        String query = "SELECT c FROM Course c WHERE LOWER(c.title) LIKE LOWER('" + title + "%') OR LOWER(c.teacher) LIKE LOWER('"+ title + "%')"; //like '%inna%'---starts with:-
        List<Course> courseList = courseDao.getListBy(query);
        return courseList;
    }

}
