package com.accenture.StudentApp2.dao.separateDao;

import com.accenture.StudentApp2.dao.Dao;
import com.accenture.StudentApp2.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

//@Repository
public class StudentDaoImpl implements Dao<Student> {


    @PersistenceContext
    private EntityManager entityManager;


    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {
        Query theQuery = entityManager.createQuery("from " + Student.class.getName());
        return theQuery.getResultList();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public void update(Student student) {
        Student dbStudent = entityManager.merge(student);
        student.setId(dbStudent.getId());

    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Student where id=:studentId");
        query.setParameter("studentId", id);
        query.executeUpdate();
    }
}
