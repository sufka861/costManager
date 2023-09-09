package il.ac.shenkar.java.costmanager.domain.repository.implementations;

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
    public Cost getCostById(int costId) {
        return null;
    }

    @Override
    public List<Cost> getCostsByDate(Date date) {
        return null;
    }

    public List<Cost> getAllCosts() {
        List<Cost> costs = new ArrayList<>();
        String selectQuery = "SELECT * FROM costs";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                costs.add(new Cost(id, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return costs;
    }

    public void updateCost(Cost cost) {
        String updateQuery = "UPDATE costs SET description = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, cost.getDescription());
            preparedStatement.setInt(3, cost.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCost(int costId) {
        String deleteQuery = "DELETE FROM costs WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, costId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
