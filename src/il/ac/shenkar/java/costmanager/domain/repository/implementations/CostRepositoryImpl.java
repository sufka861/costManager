package il.ac.shenkar.java.costmanager.domain.repository.implementations;

import il.ac.shenkar.java.costmanager.domain.model.Category;
import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.repository.interfaces.CostRepository;
import il.ac.shenkar.java.costmanager.domain.util.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CostRepositoryImpl implements CostRepository {
    private Connection connection;

    public CostRepositoryImpl() throws SQLException, IOException {
        connection = DatabaseConnectionManager.getInstance().getConnection();
        DatabaseConnectionManager.getInstance().createTableIfNotExists("COSTS");
    }

    public void addCost(Cost cost) {
        String insertQuery = "INSERT INTO COSTS (category, amount, currency, description, date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cost.getCategoryString());
            preparedStatement.setDouble(2, cost.getSum());
            preparedStatement.setString(3, cost.getCurrency());
            preparedStatement.setString(4, cost.getDescription());
            preparedStatement.setDate(5, new java.sql.Date(cost.getDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cost.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cost> getCosts(Date date) {
        List<Cost> costs = new ArrayList<>();
        String selectQuery = "SELECT * FROM costs WHERE DATE = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setDate(1, new java.sql.Date(date.getTime()));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String categoryIdentifier = resultSet.getString("CATEGORY");
                    CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
                    Category category = categoryRepository.getCategory(categoryIdentifier);

                    String description = resultSet.getString("DESCRIPTION");
                    String currency = resultSet.getString("CURRENCY");
                    Date newDate = resultSet.getDate("DATE");
                    double amount = resultSet.getDouble("AMOUNT");
                    costs.add(new Cost(category, amount, currency, description, newDate));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
   return costs;
    }
}
