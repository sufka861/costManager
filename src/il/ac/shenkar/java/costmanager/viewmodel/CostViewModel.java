package il.ac.shenkar.java.costmanager.viewmodel;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.AddCostUseCase;

public class CostViewModel {
    private final AddCostUseCase addCostUseCase;

    public CostViewModel(AddCostUseCase addCostUseCase) {
        this.addCostUseCase = addCostUseCase;
    }

    public CostViewModel() {
        this.addCostUseCase = null;
    }

    public void addCost(Cost cost) {
        if (addCostUseCase != null) {
            addCostUseCase.addCost(cost);
        } else {
            throw new IllegalStateException("addCostUseCase is not initialized. Please provide a valid AddCostUseCase.");
        }
    }
}
