package connectionDB;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Class to get the connection object from the HikariPool
 */
public class ConnectionHCP {

    private static final Logger log = Logger.getLogger(ConnectionHCP.class);
    public static final String PATH_TO_PROPERTIES = "src/main/java/resources/config.properties";
    private static HikariDataSource dataSource;
    private static HikariConfig config = new HikariConfig();
    private static Properties properties = new Properties();
    private static FileInputStream fileInputStream;



    static {
        log.info("Creation HikariPool: ");
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
        } catch (java.io.IOException e) {
            log.error("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружен");
        }
        config.setDriverClassName(properties.getProperty("Driver"));
        config.setJdbcUrl(properties.getProperty("DataURL"));
        config.setUsername(properties.getProperty("user"));
        config.setPassword(properties.getProperty("password"));
        config.setMaximumPoolSize(30);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public ConnectionHCP() {
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
