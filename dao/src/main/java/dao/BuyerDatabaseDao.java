package dao;

import connectionDB.ConnectionHCP;
import daoImpl.BuyerDao;
import entities.Buyer;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BuyerDatabaseDao implements BuyerDao {

    private static final String SQL_FIND_BY_ID_BUYER_QUERY = "SELECT * FROM buyers WHERE id = ?";
    private static final String SQL_ADD_BUYER_QUERY = "INSERT INTO buyers(name, surname, email, password, phone, address)" +
            "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_BUYER_QUERY = "UPDATE buyers SET name = ?, surname = ?, email = ?, password = ?, phone = ?, address = ? WHERE id = ?";
    private static final String SQL_DELETE_BUYER_QUERY = "DELETE FROM buyers WHERE id = ?";
    private static final String SQL_DELETE_ALL_BUYERS_QUERY = "TRUNCATE TABLE buyers";
    private static final String SQL_SELECT_ALL_BUYERS_QUERY = "SELECT * FROM buyers";

    Connection connection;

    public BuyerDatabaseDao() {
        this.connection = ConnectionHCP.getConnection();
    }

    @Override
    public void insert(Buyer buyer) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_BUYER_QUERY);
            preparedStatement.setString(1, buyer.getName());
            preparedStatement.setString(2, buyer.getSurname());
            preparedStatement.setString(3, buyer.getEmail());
            preparedStatement.setString(4, buyer.getPassword());
            preparedStatement.setString(5, buyer.getPhone());
            preparedStatement.setString(6, buyer.getAddress());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Buyer buyer) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BUYER_QUERY);
            preparedStatement.setLong(7, buyer.getId());
            preparedStatement.setString(1, buyer.getName());
            preparedStatement.setString(2, buyer.getSurname());
            preparedStatement.setString(3, buyer.getEmail());
            preparedStatement.setString(4, buyer.getPassword());
            preparedStatement.setString(5, buyer.getPhone());
            preparedStatement.setString(6, buyer.getAddress());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Buyer getById(long id) {
        Buyer result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_BUYER_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result = new Buyer(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Buyer find(String email, String password) {
        String LOGIN_QUERY = "select * from buyers where email=" + "'" + email + "'" + " and password=" + "'" + password + "'";
        Buyer result = null;
        try {
            PreparedStatement ps = connection.prepareStatement(LOGIN_QUERY);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet != null && resultSet.next()) {
                result = new Buyer(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void delete(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BUYER_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection getAll() {
        List<Buyer> result = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BUYERS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Buyer buyer = new Buyer(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
                result.add(buyer);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
