package com.accenture.StudentApp2.dao.separateDao;

import com.accenture.StudentApp2.dao.Dao;
import com.accenture.StudentApp2.model.Course;
import com.accenture.StudentApp2.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


public class CourseDaoImpl implements Dao<Course> {

    @PersistenceContext
    private EntityManager entityManager;

    public CourseDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Course> findAll() {
        Query query = entityManager.createQuery("from Course");
        return query.getResultList();
    }

    @Override
    public Optional<Course> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Course.class, id));
    }

    @Override
    public void save(Course course) {
    entityManager.persist(course);
    }

    @Override
    public void update(Course course) {
        Course dbCourse = entityManager.merge(course);
        course.setId(dbCourse.getId());
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Course where id=:courseId");
        query.setParameter("courseId", id);
        query.executeUpdate();
    }
}
