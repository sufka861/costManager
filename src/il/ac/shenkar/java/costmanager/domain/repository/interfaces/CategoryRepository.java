package il.ac.shenkar.java.costmanager.domain.repository.interfaces;

import il.ac.shenkar.java.costmanager.domain.model.Category;

import java.util.List;

/**
 * The `CategoryRepository` interface defines methods for interacting with categories.
 */
public interface CategoryRepository {
    /**
     * Adds a new category.
     *
     * @param category The category to add.
     */
    void addCategory(Category category);

    /**
     * Retrieves a category by its name.
     *
     * @param wantedName The name of the category to retrieve.
     * @return The category with the specified name.
     */
    Category getCategory(String wantedName);

    /**
     * Retrieves a list of all categories.
     *
     * @return A list of all categories.
     */
    List<Category> getAllCategories();
}
