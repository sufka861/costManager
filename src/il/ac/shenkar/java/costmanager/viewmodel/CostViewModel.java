package il.ac.shenkar.java.costmanager.viewmodel;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.AddCostUseCase;

/**
 * The `CostViewModel` class represents the view model for managing costs.
 */
public class CostViewModel {

    private AddCostUseCase addCostUseCase;

    /**
     * Constructs a new `CostViewModel`.
     *
     * @param addCostUseCase The use case for adding costs.
     */
    public CostViewModel(AddCostUseCase addCostUseCase) {
        setAddCostUseCase(addCostUseCase);
    }

    /**
     * Gets the use case for adding costs.
     *
     * @return The use case for adding costs.
     */
    public AddCostUseCase getAddCostUseCase() {
        return addCostUseCase;
    }

    /**
     * Sets the use case for adding costs.
     *
     * @param addCostUseCase The use case for adding costs to set.
     */
    public void setAddCostUseCase(AddCostUseCase addCostUseCase) {
        this.addCostUseCase = addCostUseCase;
    }

    /**
     * Adds a new cost.
     *
     * @param cost The cost object to add.
     * @throws IllegalStateException If the `addCostUseCase` is not initialized.
     */
    public void addCost(Cost cost) {
        if (getAddCostUseCase() != null) {
            getAddCostUseCase().addCost(cost);
        } else {
            throw new IllegalStateException("addCostUseCase is not initialized. Please provide a valid AddCostUseCase.");
        }
    }
}
