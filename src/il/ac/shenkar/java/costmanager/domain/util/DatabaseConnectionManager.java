package il.ac.shenkar.java.costmanager.domain.util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;
    Properties properties = new Properties();

    private DatabaseConnectionManager() throws IOException, SQLException {
        String jdbcURL = "jdbc:derby:costManagerDB;create=true";
        try {
            connection = DriverManager.getConnection(jdbcURL);
            System.out.println("Connected to Derby DB");

            listAllTablesAndData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException {
        String shutDownURL = "jdbc:derby:;shutdown=true";
        DriverManager.getConnection(shutDownURL);
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

    private void listAllTablesAndData() throws SQLException {
        if (connection != null) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Tables in the database:");

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(tableName);
                printTableData(tableName);
            }

            resultSet.close();
        }
    }

    private void printTableData(String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet dataResultSet = statement.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData metaData = dataResultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        System.out.println("Data in table '" + tableName + "':");

        while (dataResultSet.next()) {
            StringBuilder rowData = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnValue = dataResultSet.getString(i);
                rowData.append(columnName).append(": ").append(columnValue).append(", ");
            }
            System.out.println(rowData.toString());
        }

        dataResultSet.close();
        statement.close();
}
}