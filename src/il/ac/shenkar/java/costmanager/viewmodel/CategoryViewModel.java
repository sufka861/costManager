package il.ac.shenkar.java.costmanager.viewmodel;

import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.AddCategoryUseCase;

public class CategoryViewModel {
    private AddCategoryUseCase addCategoryUseCase;

    public CategoryViewModel(AddCategoryUseCase addCategoryUseCase)
    {
        this.addCategoryUseCase = addCategoryUseCase;
    }

    public void addCategory(String category)
    {
        addCategoryUseCase.addCategory(category);
    }

    public boolean categoryExists(String categoryName) {
        return false;
    }
}
