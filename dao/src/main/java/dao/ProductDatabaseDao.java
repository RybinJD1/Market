package dao;


import connectionDB.ConnectionHCP;
import daoImpl.ProductDao;

import entities.Product;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ProductDatabaseDao implements ProductDao {

    private static final String SQL_ADD_PRODUCT_QUERY = "INSERT INTO products (name, description, cost, remainder)" +
            "VALUES(?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID_PRODUCT_QUERY = "SELECT * FROM products WHERE id = ?";
    private static final String SQL_UPDATE_PRODUCT_QUERY = "UPDATE products SET name = ?, description=?, cost=?, remainder=? WHERE id = ?";
    private static final String SQL_DELETE_PRODUCT_QUERY = "DELETE FROM products WHERE id = ?";
    private static final String SQL_DELETE_ALL_PRODUCTS_QUERY = "TRUNCATE TABLE products";
    private static final String SQL_SELECT_ALL_PRODUCT_QUERY = "SELECT * FROM products";

    Connection connection ;

    public ProductDatabaseDao() {
        this.connection= ConnectionHCP.getConnection();
    }

    @Override
    public void add(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_PRODUCT_QUERY);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getCost());
            preparedStatement.setInt(4, product.getRemainder());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT_QUERY);
            preparedStatement.setLong(5, product.getId());
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getCost());
            preparedStatement.setInt(4, product.getRemainder());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getById(long id) {
        Product result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_PRODUCT_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void delete(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void deleteAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ALL_PRODUCTS_QUERY);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public Collection getAll() {
        List<Product> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_PRODUCT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getInt(5));
                result.add(product);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
