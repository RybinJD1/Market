package daoImpl;

import entities.Entity;

import java.util.Collection;


public interface GenericDao<T extends Entity> {


    void insert(T entity);

    void update(T entity);

    T getById(long id);

    void delete(long id);

    Collection getAll();

}
