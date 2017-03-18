package testDao;


import dao.BuyerDatabaseDao;

public class TestJdbsBuyerDao {


    private BuyerDatabaseDao buyerDatabaseDao = new BuyerDatabaseDao();





//    @Test
//    public void initDb() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        connection = DatabaseConnection.getConnection();
//        Assert.assertNotNull(connection);
//    }

    /*@Test
    public void testBuyersTableCreated() throws SQLException {
        createBuyersTable();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, "buyer", null);
        Assert.assertTrue(resultSet.next());
    }*/



    /*@Test
    public void testAddedBuyer() throws SQLException {

        buyerDatabaseDao.add(new Buyer("Ivan", "Doroshko", "i.doroshko@gmail.com", "12345", "2561524", "Minsk, Kulman 7, 2"));
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM buyers");
        resultSet.next();
        Assert.assertEquals("Doroshko", resultSet.getString(2));
    }

    @Test
    public void testUpdateBuyer() {
        buyerDatabaseDao.update(new Buyer(38, "Ivan", "Doroshko", "i.doroshko@gmail.com", "12345", "2561524", "Minsk,  8, 22"));
        Buyer buyer = buyerDatabaseDao.getById(38L);
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
        buyerDatabaseDao.delete(16);
//        Assert.assertEquals();
    }



    @Test
    public void testGetAll() {
        buyerDatabaseDao.getAll();
        
    }*/

//    @After
//    public void closeConnection() {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    /*private void createBuyersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_BUYERS_TABLE_QUERY);
        statement.close();
    }*/

    /*private void dropBuyerTable() throws SQLException {
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS buyer");
    }*/

    /*
        addBuyer("Андрей", "Баценко", "a.bazenko@gmail.com", "12345", "2563152", "Minsk, Kirova 18, 1");
        addBuyer("Максим", "Ткач", "m.tkach@gmail.com", "12345", "2561523", "Minsk, Kulman 8, 21");
    */


}
