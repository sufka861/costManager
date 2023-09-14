package il.ac.shenkar.java.costmanager.domain.repository.implementations;

import il.ac.shenkar.java.costmanager.domain.model.Category;
import il.ac.shenkar.java.costmanager.domain.repository.interfaces.CategoryRepository;
import il.ac.shenkar.java.costmanager.domain.util.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private Connection connection;

    public CategoryRepositoryImpl() throws SQLException, IOException {
        connection = DatabaseConnectionManager.getInstance().getConnection();
        DatabaseConnectionManager.getInstance().createTableIfNotExists("CATEGORIES");
    }

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
