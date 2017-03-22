package connectionDB;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Class to get the connection object from the HikariPool
 */
public class ConnectionDB {

    private static final Logger log = Logger.getLogger(ConnectionDB.class);
    private static HikariDataSource dataSource;
    private static HikariConfig config = new HikariConfig();
    private static Properties properties = ConnectionDB.getPropertiesFile();

    static {
        log.info("Creation HikariPool: ");

        config.setDriverClassName(properties.getProperty("Driver"));
        config.setJdbcUrl(properties.getProperty("DataURL"));
        config.setUsername(properties.getProperty("user"));
        config.setPassword(properties.getProperty("password"));
        config.setMaximumPoolSize(15);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public ConnectionDB() {
    }

    /**
     * Function to get the properties object
     *
     * @return Returns the properties object
     */
    private static Properties getPropertiesFile() {
        Properties properties = new Properties();
        InputStream inputStream = ConnectionDB.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Ошибка в программе: файл config.properties не обнаружен");
            e.printStackTrace();
        }
        return properties;
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
            e.printStackTrace();
        }
        return null;
    }


}
