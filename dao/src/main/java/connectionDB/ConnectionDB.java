package connectionDB;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Class to get the connection object from the HikariPool
 */
public class ConnectionDB {

    private static final Logger log = Logger.getLogger(ConnectionDB.class);
//    static final String PATH_TO_PROPERTIES = "src.main.resources.config.properties";
    private static HikariDataSource dataSource;
    private static HikariConfig config = new HikariConfig();
    private static Properties properties = new Properties();




    static {
/*        log.info("Creation HikariPool: ");
        * not inputStream
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
        } catch (java.io.IOException e) {
            log.error("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружен");
        }*/

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        config.setUsername("root");
        config.setPassword("root");
        config.setMaximumPoolSize(30);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public ConnectionDB() {
    }

    /**
     * Function to get the connection object
     *
     * @return Returns the connection object
     */
    public static Connection getConnection() {
        try {
            log.info("Getting Connection: ");
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Connection error: " + e);
        }
        return null;
    }


}
