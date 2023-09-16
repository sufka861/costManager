package il.ac.shenkar.java.costmanager.domain.usecase.implementations;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.repository.implementations.CostRepositoryImpl;
import il.ac.shenkar.java.costmanager.domain.repository.interfaces.CostRepository;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.GetCostReportUseCase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * The `GetCostReportUseCaseImpl` class provides methods for retrieving cost reports by date.
 */
public class GetCostReportUseCaseImpl implements GetCostReportUseCase {

    private final CostRepository costRepository = new CostRepositoryImpl();

    /**
     * Constructs a new `GetCostReportUseCaseImpl` instance.
     *
     * @throws SQLException If there's an issue with the database connection.
     * @throws IOException  If there's an issue with I/O operations.
     */
    public GetCostReportUseCaseImpl() throws SQLException, IOException {}

    /**
     * Retrieves a list of costs by date.
     *
     * @param date The date for which to retrieve costs.
     * @return A list of costs for the specified date.
     */
    @Override
    public List<Cost> getCostsByDate(Date date) {
        List<Cost> result = costRepository.getCosts(date);
        return result;
    }
}
