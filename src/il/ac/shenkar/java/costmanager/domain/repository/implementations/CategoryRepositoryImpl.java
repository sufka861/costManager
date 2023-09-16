package il.ac.shenkar.java.costmanager.domain.repository.implementations;

import il.ac.shenkar.java.costmanager.domain.model.Category;
import il.ac.shenkar.java.costmanager.domain.repository.interfaces.CategoryRepository;
import il.ac.shenkar.java.costmanager.domain.util.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The `CategoryRepositoryImpl` class provides methods for interacting with the 'CATEGORIES' table in the database.
 */
public class CategoryRepositoryImpl implements CategoryRepository {
    private Connection connection;

    /**
     * Constructs a new `CategoryRepositoryImpl` and initializes the database connection.
     *
     * @throws SQLException If there's an issue with the database connection.
     * @throws IOException  If there's an issue with I/O operations.
     */
    public CategoryRepositoryImpl() throws SQLException, IOException {
        connection = DatabaseConnectionManager.getInstance().getConnection();
        DatabaseConnectionManager.getInstance().createTableIfNotExists("CATEGORIES");
    }

    /**
     * Adds a new category to the database.
     *
     * @param category The category to add.
     */
    @Override
    public void addCategory(Category category) {
        String insertQuery = "INSERT INTO categories (name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return A list of all categories.
     */
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String selectQuery = "SELECT * FROM CATEGORIES";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                categories.add(new Category(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * Retrieves a category by its name from the database.
     *
     * @param wantedName The name of the category to retrieve.
     * @return The category with the specified name.
     */
    @Override
    public Category getCategory(String wantedName) {
        String selectQuery = "SELECT * FROM CATEGORIES WHERE NAME = ?";
        String name = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, wantedName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    name = resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Category(name);
    }
}
