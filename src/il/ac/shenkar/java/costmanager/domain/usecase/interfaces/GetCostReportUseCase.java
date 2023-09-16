package il.ac.shenkar.java.costmanager.domain.usecase.interfaces;

import il.ac.shenkar.java.costmanager.domain.model.Cost;

import java.util.Date;
import java.util.List;

/**
 * The `GetCostReportUseCase` interface defines the contract for retrieving costs by date.
 */
public interface GetCostReportUseCase {

    /**
     * Retrieves a list of costs for a specified date.
     *
     * @param date The date for which to retrieve costs.
     * @return A list of costs for the specified date.
     */
    List<Cost> getCostsByDate(Date date);
}
