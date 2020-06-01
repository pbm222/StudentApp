package com.accenture.StudentApp2.service;

import com.accenture.StudentApp2.dao.GenericDaoImpl;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    public void setDao(GenericDaoImpl<T> genericDao);
    public List<T> findAll();
    public Optional<T> getById(Long id);
    public void save(T t);
    public void update(T t);
    public void deleteById(Long id);
}
