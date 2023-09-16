package il.ac.shenkar.java.costmanager.domain.usecase.implementations;

import il.ac.shenkar.java.costmanager.domain.model.Category;
import il.ac.shenkar.java.costmanager.domain.repository.implementations.CategoryRepositoryImpl;
import il.ac.shenkar.java.costmanager.domain.repository.interfaces.CategoryRepository;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.AddCategoryUseCase;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The `AddCategoryUseCaseImpl` class provides methods for adding new categories.
 */
public class AddCategoryUseCaseImpl implements AddCategoryUseCase {

    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    /**
     * Constructs a new `AddCategoryUseCaseImpl` instance.
     *
     * @throws SQLException If there's an issue with the database connection.
     * @throws IOException  If there's an issue with I/O operations.
     */
    public AddCategoryUseCaseImpl() throws SQLException, IOException {}

    /**
     * Adds a new category with the specified name.
     *
     * @param name The name of the category to add.
     */
    @Override
    public void addCategory(String name) {
        Category category = new Category(name);
        categoryRepository.addCategory(category);
    }
}
