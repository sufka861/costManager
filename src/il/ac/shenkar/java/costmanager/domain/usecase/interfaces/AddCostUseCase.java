package il.ac.shenkar.java.costmanager.domain.usecase.interfaces;

import il.ac.shenkar.java.costmanager.domain.model.Cost;

/**
 * The `AddCostUseCase` interface defines the contract for adding a cost.
 */
public interface AddCostUseCase {

    /**
     * Adds a new cost.
     *
     * @param cost The cost object to add.
     */
    void addCost(Cost cost);
}
