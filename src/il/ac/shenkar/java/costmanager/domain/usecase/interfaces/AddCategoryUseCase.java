package il.ac.shenkar.java.costmanager.domain.usecase.interfaces;

/**
 * The `AddCategoryUseCase` interface defines the contract for adding a category.
 */
public interface AddCategoryUseCase {

    /**
     * Adds a new category.
     *
     * @param category The name of the category to add.
     */
    void addCategory(String category);
}
