package com.accenture.StudentApp2.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> findAll();
    Optional<T> getById(Long id);
    void save(T t);
    void update(T t);
    void deleteById(Long id);


}
