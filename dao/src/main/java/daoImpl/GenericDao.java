package daoImpl;

import entities.Entity;

import java.util.Collection;

/**
 * Interface with common methods
 * */
public interface GenericDao<T extends Entity> {

    /**
     * This method add a new object
     */
    void insert(T entity);

    /**
     *This method updates the objects
     */
    void update(T entity);

    /**
     * This method of finding an object by id
     * */
    T getById(long id);

    /**
     * This method delete an object by id
     * */
    void delete(long id);

    /**
     * This method of obtaining all objects
     * */
    Collection getAll();

}
