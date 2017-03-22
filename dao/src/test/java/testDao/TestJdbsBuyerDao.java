package testDao;


import connectionDB.ConnectionDB;
import dao.BuyerDatabaseDao;
import entities.Buyer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class TestJdbsBuyerDao {

    private static final String CREATE_BUYERS_TABLE_QUERY = "CREATE TABLE buyer(id int auto_increment, name varchar(60)" +
            " not null, surname varchar(60) not null, email varchar(60) unique not null, password varchar(60) not null," +
            " phone varchar(60) not null, address varchar(60) not null, primary key(id))";

    private Connection connection;
    private BuyerDatabaseDao buyerDatabaseDao = new BuyerDatabaseDao();





    @Before
    public void initDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = ConnectionDB.getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void testBuyersTableCreated() throws SQLException {
        createBuyersTable();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, "buyer", null);
        Assert.assertTrue(resultSet.next());
    }


    @Test
    public void testAddedBuyer() throws SQLException {

        buyerDatabaseDao.insert(new Buyer("Ivan", "Doroshko", "i.doroshko@gmail.com", "12345", "2561524", "Minsk, Kulman 7, 2"));
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM buyer");
        resultSet.next();
        Assert.assertEquals("Doroshko", resultSet.getString(1));
    }

    @Test
    public void testUpdateBuyer() {
        buyerDatabaseDao.update(new Buyer(1, "Ivan", "Doroshko", "i.doroshko@gmail.com", "12345", "2561524", "Minsk,  8, 22"));
        Buyer buyer = buyerDatabaseDao.getById(1L);
        Assert.assertEquals("Doroshko", buyer.getSurname());
    }

    @Test
    public void testGetBuyerByID() throws SQLException {
        Buyer buyer = buyerDatabaseDao.getById(8L);
        Assert.assertEquals("Tkach", buyer.getSurname());
    }

    @Test
    public void testGetBuyerByEmail() {
        Buyer buyer= buyerDatabaseDao.find("d.lotkov@gmail.com", "12345");
        Assert.assertEquals("Lotkov", buyer.getSurname());
    }

    @Test
    public void testDeleteById() {
        buyerDatabaseDao.delete(1);

//        Assert.assertEquals();
    }



    @Test
    public void testGetAll() {
        buyerDatabaseDao.getAll();
        
    }

//    @After
//    public void closeConnection() {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private void createBuyersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_BUYERS_TABLE_QUERY);
        statement.close();
    }

    /*private void dropBuyerTable() throws SQLException {
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS buyer");
    }*/

    /*
        addBuyer("Андрей", "Баценко", "a.bazenko@gmail.com", "12345", "2563152", "Minsk, Kirova 18, 1");
        addBuyer("Максим", "Ткач", "m.tkach@gmail.com", "12345", "2561523", "Minsk, Kulman 8, 21");
    */


}
