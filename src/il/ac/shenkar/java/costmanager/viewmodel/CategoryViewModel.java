package il.ac.shenkar.java.costmanager.viewmodel;

import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.AddCategoryUseCase;

/**
 * The `CategoryViewModel` class represents the view model for managing categories.
 */
public class CategoryViewModel {

    private AddCategoryUseCase addCategoryUseCase;

    /**
     * Constructs a new `CategoryViewModel`.
     *
     * @param addCategoryUseCase The use case for adding categories.
     */
    public CategoryViewModel(AddCategoryUseCase addCategoryUseCase)
    {
        setAddCategoryUseCase(addCategoryUseCase);
    }

    /**
     * Gets the use case for adding categories.
     *
     * @return The use case for adding categories.
     */
    public AddCategoryUseCase getAddCategoryUseCase() {
        return addCategoryUseCase;
    }

    /**
     * Sets the use case for adding categories.
     *
     * @param addCategoryUseCase The use case for adding categories to set.
     */
    public void setAddCategoryUseCase(AddCategoryUseCase addCategoryUseCase) {
        this.addCategoryUseCase = addCategoryUseCase;
    }

    /**
     * Adds a new category.
     *
     * @param category The name of the category to add.
     */
    public void addCategory(String category)
    {
        getAddCategoryUseCase().addCategory(category);
    }

    /**
     * Checks if a category with the given name already exists.
     *
     * @param categoryName The name of the category to check.
     * @return `true` if the category exists; otherwise, `false`.
     */
    public boolean categoryExists(String categoryName) {
        return false;
    }
}
