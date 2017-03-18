package daoImpl;

import entities.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;


public interface GenericDao<T extends Entity> {
    Connection connection = null;

    void add(T entity);

    void update(T entity);

    T getById(long id);

    default void delete(long id) {
        /*try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }


//    void deleteAll();

    Collection getAll();

}
