package com.accenture.StudentApp2.service;

import com.accenture.StudentApp2.dao.GenericDaoImpl;
import com.accenture.StudentApp2.dao.separateDao.StudentDaoImpl;
import com.accenture.StudentApp2.model.Course;
import com.accenture.StudentApp2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@org.springframework.stereotype.Service
public class StudentServiceImpl implements Service<Student> {

    private GenericDaoImpl<Student> studentDao;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    public void setDao(GenericDaoImpl<Student> daoToSet) {
        studentDao = daoToSet;
        studentDao.setClazz(Student.class);
    }


    @Override
    //@Cacheable("students")
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return studentDao.getById(id);
    }

    public Student getByEmail(String email) {
        String query = "SELECT s FROM Student s WHERE s.email='" + email + "'";
        Student student = studentDao.getBy(query);
        return student;
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
        Student student = studentDao.getById(id).get();
        student.getCourses().forEach(course -> course.getStudents().remove(student));

        studentDao.deleteById(id);
    }

    @Transactional
    public boolean assignCourseToStudent(Long courseId, String studentEmail) {

        Course course = courseService.getById(courseId).get();
        Student student = this.getByEmail(studentEmail);
        Set<Course> studentCourses = student.getCourses();

        boolean courseExists = studentCourses.stream().anyMatch(course1 -> course1.getId().equals(course.getId()));
        if (courseExists) {
            return true;
        } else {
            studentCourses.add(course);
            student.setCourses(studentCourses);
            this.update(student);
            return false;
        }
    }

    public List<Student> getListByNameOrSurname(String name) {
        String query = "SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER('" + name + "%') OR LOWER(s.surname) LIKE LOWER('"+ name + "%')";
        List<Student> studentList = studentDao.getListBy(query);
        return studentList;
    }

}
