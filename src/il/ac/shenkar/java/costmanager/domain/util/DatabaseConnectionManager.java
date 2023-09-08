package il.ac.shenkar.java.costmanager.domain.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;
    Properties properties = new Properties();

    //        try (FileInputStream fis = new FileInputStream("config.properties")) {
//            properties.load(fis);
//            String dbDirectory = properties.getProperty("derby.db.directory");
//            String jdbcUrl = "jdbc:derby:" + dbDirectory + "/myembeddeddb;create=true";
//
//            try {
//                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            connection = DriverManager.getConnection(jdbcUrl);
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//        }

    private DatabaseConnectionManager() throws IOException, SQLException {
        String jdbcURL = "jdbc:derby:costManagerDB;create=true";
        try {
            connection = DriverManager.getConnection(jdbcURL);
            System.out.println("New Derby DB created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static synchronized DatabaseConnectionManager getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
