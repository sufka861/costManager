package il.ac.shenkar.java.costmanager.domain.repository.interfaces;

import il.ac.shenkar.java.costmanager.domain.model.Category;

import java.util.List;

public interface CategoryRepository {
    void addCategory(Category category);
    Category getCategoryById(int categoryId);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategory(int categoryId);

}
