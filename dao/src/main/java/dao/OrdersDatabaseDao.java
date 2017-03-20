package dao;

import connectionDB.ConnectionHCP;
import daoImpl.OrdersDao;
import entities.Orders;
import entities.Product;
import enums.Status;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AlexFisher on 16.01.2017.
 */
public class OrdersDatabaseDao implements OrdersDao {

    private static final Logger log = Logger.getLogger(OrdersDatabaseDao.class);


    private static final String SQL_FIND_BY_ID_ORDERS_QUERY = "SELECT * FROM orders WHERE id = ?";
    private static final String SQL_ADD_ORDERS_QUERY = "INSERT INTO orders(date_registration, status, buyers_id)" +
            "VALUES(?, ?, ?, ?)";
    private static final String SQL_REDUCING_PRODUCTS_QUERY = "UPDATE products SET remainder = ? WHERE id = ?";
    private static final String SQL_ADD_ORDERS_PRODUCTS_QUERY = "INSERT INTO orders_products(orders_id, products_id," +
            " quantity_products) VALUES(?,?,?)";
    private static final String SQL_UPDATE_ORDERS_QUERY = "UPDATE orders SET date_registration = ?, closing_date = ?, " +
            "status = ?, buyers_id = ? WHERE id = ?";
    private static final String SQL_DELETE_ORDERS_QUERY = "DELETE FROM orders WHERE id = ?";
    private static final String SQL_DELETE_ALL_ORDERS_QUERY = "TRUNCATE TABLE orders";
    private static final String SQL_SELECT_ALL_ORDERS_QUERY = "SELECT * FROM orders";
    private static final String SQL_SELECT_ORDERS_BY_BUYER_ID = "SELECT * FROM orders WHERE buyers_id = ?";
    private static final String SQL_SELECT_ORDERS_BY_PRODUCT_NAME = "select o.id, o.date_registration, o.closing_date," +
            " o.status, o.buyers_id from orders as o join orders_products as op on o.id=op.orders_id join products as p " +
            "on op.products_id=p.id where p.name=?";
    private static final String SQL_DELETE_ORDERS_BY_REGISTRATION_DATE = "DELETE FROM orders WHERE date_registration < ?";
    private static final String SQL_GET_ID_OF_LAST_ORDER = "SELECT id FROM orders ORDER BY id DESC LIMIT 1";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Connection connection;

    public OrdersDatabaseDao() {
        this.connection = ConnectionHCP.getConnection();
    }

    @Override
    public void insert(Orders orders) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ORDERS_QUERY);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now().format(dateTimeFormatter)));
//            preparedStatement.setDate(2, Date.valueOf(orders.getClosingDate()));
            preparedStatement.setString(2, Status.ORDER_PROCESSING.toString().toUpperCase());
            preparedStatement.setLong(3, orders.getBuyerId());
            connection.commit();
            preparedStatement.executeUpdate();

            orders.setId(getIdOfLastOrder());
            preparedStatement = connection.prepareStatement(SQL_REDUCING_PRODUCTS_QUERY);
            for (Product product : orders.getQuantityOfProduct().keySet()) {
                preparedStatement.setLong(1, (product.getRemainder() - orders.getQuantityOfProduct().get(product)));
                preparedStatement.setLong(2, product.getId());
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(SQL_ADD_ORDERS_PRODUCTS_QUERY);
                preparedStatement.setLong(1, orders.getId());
                preparedStatement.setLong(2, product.getId());
                preparedStatement.setLong(3, orders.getQuantityOfProduct().get(product));
                preparedStatement.executeUpdate();
                connection.commit();
            }
            preparedStatement.close();
        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void update(Orders orders) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDERS_QUERY);
            preparedStatement.setLong(5, orders.getId());
            preparedStatement.setDate(1, Date.valueOf(orders.getRegistrationDate()));
            preparedStatement.setDate(2, Date.valueOf(orders.getClosingDate()));
            preparedStatement.setObject(3, orders.getStatus().toString().toUpperCase());
            preparedStatement.setLong(4, orders.getBuyerId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orders getById(long id) {
        Orders orders = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_ORDERS_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                orders = new Orders(resultSet.getLong(1), Status.valueOf(resultSet.getString("status").toUpperCase()),
                        LocalDate.parse(resultSet.getString(2)), LocalDate.parse(resultSet.getString(3)), resultSet.getLong(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public long getIdOfLastOrder() {
        long id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ID_OF_LAST_ORDER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Collection getAll() {
        List<Orders> result = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Orders orders = new Orders(resultSet.getLong(1), Status.valueOf(resultSet.getString("status").toUpperCase()),
                        LocalDate.parse(resultSet.getString(2)), LocalDate.parse(resultSet.getString(3)), resultSet.getLong(5));
                result.add(orders);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection getOrdersByBuyerId() {
        List<Orders> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS_BY_BUYER_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            LocalDate registrationDate = LocalDate.parse(resultSet.getString(3));
            LocalDate closingDate = LocalDate.parse(resultSet.getString(4));
            while (resultSet.next()) {
                Orders orders = new Orders(resultSet.getLong(1), Status.valueOf(resultSet.getString("status").toUpperCase()),
                        registrationDate, closingDate, resultSet.getLong(5));
                result.add(orders);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection getOrdersByProductName() {
        List<Orders> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS_BY_PRODUCT_NAME);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                Orders orders = new Orders(resultSet.getLong(1), ..)
//                result.insert(orders);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteOrdersByRegistrationDate() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDERS_BY_REGISTRATION_DATE);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
