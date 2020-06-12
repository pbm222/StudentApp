package com.accenture.StudentApp2.dao;

import com.accenture.StudentApp2.model.Student;

import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> {

    private Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;


    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> findAll() {
        Query theQuery = entityManager.createQuery("from " + clazz.getSimpleName());
        return theQuery.getResultList();
    }


    public Optional<T> getById(Long id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    public void save(T t) {
        entityManager.persist(t);
    }

    public void update(T t) {
        T dbStudent = entityManager.merge(t);
        //t.setId(dbStudent.getId());

    }

    public T getBy(String queryText){
        Query query = entityManager.createQuery(queryText);
        //query.setParameter("Email", queryParameter);
        return (T) query.getSingleResult();
    }

    public List<T> getListBy(String queryText){
        Query query = entityManager.createQuery(queryText);
        return query.getResultList();
    }

    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from " + clazz.getSimpleName() +" where id=:Id");
        query.setParameter("Id", id);
        query.executeUpdate();
    }

}
