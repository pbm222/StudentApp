package com.accenture.StudentApp2.service;

import com.accenture.StudentApp2.dao.GenericDaoImpl;
import com.accenture.StudentApp2.dao.separateDao.CourseDaoImpl;
import com.accenture.StudentApp2.model.Course;
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
        return Optional.empty();
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
        courseDao.deleteById(id);
    }


    public List<Course> findByName(String name) {
        List<Course> courses = courseDao.findAll();
        List<Course> sortedCourses = new ArrayList<>();

        courses.forEach(course -> {
            if (course.getTitle().contains(name)){
                sortedCourses.add(course);
            }
        });

        return sortedCourses;
    }
}
