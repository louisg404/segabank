package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class PersistenceManager {

    private static final String PROPS_FILE = "./resources/db.properties";
    private static Connection connection;
    private PersistenceManager(){}//Prevents initialization

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {

        if(connection == null || !connection.isValid(2)){
            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream( PROPS_FILE )){
                props.load(fis);
            }
            String driverClass = props.getProperty("jdbc.class.driver");
            String url = props.getProperty("jdbc.db.url");
            String login = props.getProperty("jdbc.db.login");
            String password = props.getProperty("jdbc.db.password");

            Class.forName( driverClass );
            connection = DriverManager.getConnection(url, login, password);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {

        if(connection != null && connection.isValid(2)){
            connection.close();
        }
    }
}