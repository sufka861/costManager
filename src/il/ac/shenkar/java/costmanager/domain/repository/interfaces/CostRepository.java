package il.ac.shenkar.java.costmanager.domain.repository.interfaces;

import il.ac.shenkar.java.costmanager.domain.model.Cost;

import java.util.Date;
import java.util.List;

/**
 * The `CostRepository` interface defines methods for interacting with costs.
 */
public interface CostRepository {
    /**
     * Adds a new cost.
     *
     * @param cost The cost to add.
     */
    void addCost(Cost cost);

    /**
     * Retrieves a list of costs for a specific date.
     *
     * @param date The date for which to retrieve costs.
     * @return A list of costs for the specified date.
     */
    List<Cost> getCosts(Date date);
}
