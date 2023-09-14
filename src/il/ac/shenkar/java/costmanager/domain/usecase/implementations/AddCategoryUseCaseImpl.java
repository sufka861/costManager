package il.ac.shenkar.java.costmanager.domain.usecase.implementations;

import il.ac.shenkar.java.costmanager.domain.model.Category;
import il.ac.shenkar.java.costmanager.domain.repository.implementations.CategoryRepositoryImpl;
import il.ac.shenkar.java.costmanager.domain.repository.interfaces.CategoryRepository;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.AddCategoryUseCase;

import java.io.IOException;
import java.sql.SQLException;

public class AddCategoryUseCaseImpl implements AddCategoryUseCase {

    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    public AddCategoryUseCaseImpl() throws SQLException, IOException {}

    @Override
    public void addCategory(String name) {
        Category category = new Category(name);
        categoryRepository.addCategory(category);
    }
}
