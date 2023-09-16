package il.ac.shenkar.java.costmanager.domain.usecase.implementations;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.repository.implementations.CostRepositoryImpl;
import il.ac.shenkar.java.costmanager.domain.repository.interfaces.CostRepository;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.AddCostUseCase;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The `AddCostUseCaseImpl` class provides methods for adding new costs.
 */
public class AddCostUseCaseImpl implements AddCostUseCase {
    private final CostRepository costRepository = new CostRepositoryImpl();

    /**
     * Constructs a new `AddCostUseCaseImpl` instance.
     *
     * @throws SQLException If there's an issue with the database connection.
     * @throws IOException  If there's an issue with I/O operations.
     */
    public AddCostUseCaseImpl() throws SQLException, IOException {}

    /**
     * Adds a new cost to the repository.
     *
     * @param cost The cost to add.
     */
    @Override
    public void addCost(Cost cost) {
        costRepository.addCost(cost);
    }
}
