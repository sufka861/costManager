package il.ac.shenkar.java.costmanager.viewmodel;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.AddCostUseCase;

public class CostViewModel {

    private AddCostUseCase addCostUseCase;

    public CostViewModel(AddCostUseCase addCostUseCase) {
        setAddCostUseCase(addCostUseCase);
    }

    public AddCostUseCase getAddCostUseCase() {
        return addCostUseCase;
    }

    public void setAddCostUseCase(AddCostUseCase addCostUseCase) {
        this.addCostUseCase = addCostUseCase;
    }

    public void addCost(Cost cost) {
        if (getAddCostUseCase() != null) {
            getAddCostUseCase().addCost(cost);
        } else {
            throw new IllegalStateException("addCostUseCase is not initialized. Please provide a valid AddCostUseCase.");
        }
    }
}
