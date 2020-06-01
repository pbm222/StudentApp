package com.accenture.StudentApp2.dao;

import com.accenture.StudentApp2.model.Student;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericDaoImpl<T> extends AbstractDao<T> implements Dao<T>{

}
