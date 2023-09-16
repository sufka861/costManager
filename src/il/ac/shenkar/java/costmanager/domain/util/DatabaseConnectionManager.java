package il.ac.shenkar.java.costmanager.domain.util;

import il.ac.shenkar.java.costmanager.domain.usecase.implementations.AddCategoryUseCaseImpl;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

/**
 * The `DatabaseConnectionManager` class manages the connection to the database and provides utility methods for interacting with it.
 */
public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;
    private final ConfigurationManager configurationManager = new ConfigurationManager();

    /**
     * Private constructor to initialize the database connection.
     *
     * @throws IOException  If there's an issue with I/O operations.
     * @throws SQLException If there's an issue with the database connection.
     */
    private DatabaseConnectionManager() throws IOException, SQLException {
        try {
            setConnection(DriverManager.getConnection(configurationManager.getJdbcURL()));
            System.out.println("Connected to Derby DB");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setInstance(DatabaseConnectionManager instance) {
        DatabaseConnectionManager.instance = instance;
    }

    /**
     * Get the instance of `DatabaseConnectionManager`. Create a new instance if it doesn't exist.
     *
     * @return The `DatabaseConnectionManager` instance.
     * @throws SQLException If there's an issue with the database connection.
     * @throws IOException  If there's an issue with I/O operations.
     */
    public static synchronized DatabaseConnectionManager getInstance() throws SQLException, IOException {
        if (instance == null) {
            setInstance(new DatabaseConnectionManager());
        }
        return instance;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Create a table in the database if it doesn't exist.
     *
     * @param tableName The name of the table to create.
     */
    public void createTableIfNotExists(String tableName) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, tableName, null);
            if (!resultSet.next()) {
                String createTableQuery;
                String[] initialCatFields;
                if (Objects.equals(tableName, "CATEGORIES")) {
                    createTableQuery = "CREATE TABLE CATEGORIES (id INT GENERATED BY DEFAULT AS IDENTITY, name VARCHAR(128))";
                } else if (Objects.equals(tableName, "COSTS")) {
                    createTableQuery = "CREATE TABLE COSTS (id INT GENERATED BY DEFAULT AS IDENTITY, category VARCHAR(128), amount DOUBLE, currency VARCHAR(128), description VARCHAR(256), date DATE)";
                } else {
                    System.out.println("No such table as " + tableName);
                    return;
                }
                Statement statement = connection.createStatement();
                statement.executeUpdate(createTableQuery);
                statement.close();
                if (Objects.equals(tableName, "CATEGORIES")) {
                    initialCatFields = configurationManager.getStarterCategoryNames();
                    AddCategoryUseCaseImpl addCategoryUseCase = new AddCategoryUseCaseImpl();
                    for (String cat : initialCatFields){
                        addCategoryUseCase.addCategory(cat);
                    }
                }
                System.out.println("Created table " + tableName);
            } else {
                System.out.println("Table " + tableName + " already exists.");
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
